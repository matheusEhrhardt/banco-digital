package banco.digital.bancodigital.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import banco.digital.bancodigital.error.handler.ResourseNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banco.digital.bancodigital.dto.ContaDto;
import banco.digital.bancodigital.model.Conta;
import banco.digital.bancodigital.model.Usuario;
import banco.digital.bancodigital.repository.ContaRepository;

@Service
public class ContaService {
    
    @Autowired
    private ContaRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public void criarConta(Usuario usuario, String tipoConta){

        Conta conta = new Conta();

        conta.setNumero(gerarNumero());
        conta.setSaldo(new BigDecimal(0));
        conta.setTipoConta(tipoConta);
        conta.setUsuario(usuario);

        repository.save(conta);
    }

    public void adicionarConta(ContaDto contaDto, int idUsuario){

        Usuario usuario = usuarioService.findById(idUsuario);
        contaDto.setNumero(gerarNumero());
        contaDto.setUsuario(usuario);
        contaDto.setSaldo(new BigDecimal(0));

        Conta conta = converteDtoToModel(contaDto);
        repository.save(conta);
    }

    public void atualizarConta(ContaDto contaDto, int id){

        Conta conta = findById(id);
        contaDto.setId(id);

        Conta contaNova = converteDtoToModel(contaDto);
        repository.save(contaNova);

    }

    public Conta findById(int id){
        Optional<Conta> conta = repository.findById(id);

        if(conta.get() == null){
            throw new ResourseNotFoundException("Conta com ID: "+ id + " não encontrada");
        }

        return conta.get();
    }

    public void depositoSaque(int numeroConta, BigDecimal valor, String operacao){

        List<ContaDto> conta = repository.findContaByNumConta(numeroConta);
        if (conta.isEmpty()){
            throw new ResourseNotFoundException("Conta de numero: "+ numeroConta + " não encontrada");
        }

//        if(operacao.equals("SAQUE") &&
//                conta.get(0).getSaldo().compareTo(valor) ){
//
//        }
    }

    public Boolean isExists(int numeroConta){

        Boolean contaExiste = true;

        List<ContaDto> conta = repository.findContaByNumConta(numeroConta);

        if(conta.isEmpty()){
            contaExiste = false;
        }

        return contaExiste;
    }

    private int gerarNumero(){

        int numero = 0;
        int i = 0;

        while(i == 0){
            numero = ( int ) ( 1000 + Math.random() * 999999 );
            i = 1;
            Boolean contaExiste = isExists(numero);

            if(!contaExiste){
                i = 1;
            }
        }

        return numero;
    }

    public Conta converteDtoToModel(ContaDto contaDto){

        Conta conta = new ModelMapper().map(contaDto, Conta.class);
        return conta;
    }
}
