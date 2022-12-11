package banco.digital.bancodigital.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import banco.digital.bancodigital.error.handler.ResourseNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banco.digital.bancodigital.dto.ContaDTO;
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

    public void adicionarConta(ContaDTO contaDto, int idUsuario){

        Usuario usuario = usuarioService.findById(idUsuario);
        contaDto.setNumero(gerarNumero());
        contaDto.setUsuario(usuario);
        contaDto.setSaldo(new BigDecimal(0));

        Conta conta = converteDtoToModel(contaDto);
        repository.save(conta);
    }

    public void atualizarConta(ContaDTO contaDto, int numero){

        Conta conta = findContaByNumConta(numero);

        if(!isExists(conta)){
            throw new ResourseNotFoundException("Conta: " + numero + " não encontrada!");
        }

        contaDto.setNumero(numero);
        Conta contaNova = converteDtoToModel(contaDto);
        repository.save(contaNova);

    }

    public void atualizarConta(Conta conta){
        repository.save(conta);

    }

    public Conta findContaByNumero(int numero){
        List<Conta> conta = repository.findContaByNumConta(numero);

        if(isExists(conta)){
            throw new ResourseNotFoundException("Conta: " + numero + " não encontrada!");
        }

        return conta.get(0);
    }

    public Boolean isExists(int numeroConta){

        Conta conta = findContaByNumConta(numeroConta);
        return isExists(conta);
    }

    public Boolean isExists(List<Conta> conta){
        return !conta.isEmpty();
    }

    public Boolean isExists(Conta conta){
        return (conta != null) ? true : false;
    }

    public Conta findContaByNumConta(int numeroConta){

        List<Conta> conta = repository.findContaByNumConta(numeroConta);

        if(!isExists(conta)){
            return null;
        }

        return conta.get(0);
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

    public Conta converteDtoToModel(ContaDTO contaDto){

        Conta conta = new ModelMapper().map(contaDto, Conta.class);
        return conta;
    }
}
