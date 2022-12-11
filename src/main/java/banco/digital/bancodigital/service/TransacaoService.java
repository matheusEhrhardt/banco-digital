package banco.digital.bancodigital.service;

import banco.digital.bancodigital.dto.TransacaoDTO;
import banco.digital.bancodigital.error.handler.ResourseNotFoundException;
import banco.digital.bancodigital.model.Conta;
import banco.digital.bancodigital.model.Transacao;
import banco.digital.bancodigital.repository.TransacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private ContaService contaService;

    public void salvarTransacao(Transacao transacao){
        repository.save(transacao);
    }

    public void depositoSaque(TransacaoDTO transacaoDTO){

        Conta contaRemetente = contaService.findContaByNumConta(transacaoDTO.numContaRemetente);

        if(!contaService.isExists(contaRemetente)){
            throw new ResourseNotFoundException("Conta: " + transacaoDTO.numContaRemetente + " não encontrada!");
        }
        else if(transacaoDTO.tipo.equals("S") &&
                contaRemetente.getSaldo().doubleValue() < transacaoDTO.valor.doubleValue()){
            throw new ResourseNotFoundException("Saldo insuficiente!");
        }

        BigDecimal saldoAtualizado = contaRemetente.getSaldo().add(transacaoDTO.valor);
        contaRemetente.setSaldo(saldoAtualizado);

        transacaoDTO.setContaRemetente(contaRemetente);
        transacaoDTO.setDataHora(gerarDataHora());
        Transacao transacao = converteDtoToModel(transacaoDTO);
        salvarTransacao(transacao);
        contaService.atualizarConta(contaRemetente);

    }

    public void transferir(TransacaoDTO transacaoDTO){

        Conta contaRemetente = contaService.findContaByNumConta(transacaoDTO.numContaRemetente);
        Conta contaDestinatario = contaService.findContaByNumConta(transacaoDTO.numContaDestinatario);

        if(!contaService.isExists(contaRemetente) || !contaService.isExists(contaDestinatario)){
            throw new ResourseNotFoundException("Conta: " + transacaoDTO.numContaRemetente
                    + "ou" + transacaoDTO.contaDestinatario
                    +" não encontrada!");
        }
        else if(contaRemetente.getSaldo().doubleValue() < transacaoDTO.valor.doubleValue()){
            throw new ResourseNotFoundException("Saldo insuficiente!");
        }

        BigDecimal saldoRemetenteAtualizado = contaRemetente.getSaldo().subtract(transacaoDTO.valor);
        contaRemetente.setSaldo(saldoRemetenteAtualizado);
        BigDecimal saldoDestinatarioAtualizado = contaDestinatario.getSaldo().add(transacaoDTO.valor);
        contaDestinatario.setSaldo(saldoDestinatarioAtualizado);

        transacaoDTO.setContaDestinatario(contaDestinatario);
        transacaoDTO.setContaRemetente(contaRemetente);
        transacaoDTO.setDataHora(gerarDataHora());
        Transacao transacao = converteDtoToModel(transacaoDTO);

        salvarTransacao(transacao);
        contaService.atualizarConta(contaRemetente);
        contaService.atualizarConta(contaDestinatario);
    }

    public String gerarDataHora(){

        DateTimeFormatter formataDataHora = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime dataHora = LocalDateTime.now();
        String dataHoraAtualFormatada = formataDataHora.format(dataHora);

        return dataHoraAtualFormatada;
    }

    public Transacao converteDtoToModel(TransacaoDTO transacaoDTO){

        Transacao transacao = new ModelMapper().map(transacaoDTO, Transacao.class);
        return transacao;
    }
}
