package banco.digital.bancodigital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import banco.digital.bancodigital.dto.ContaDTO;
import banco.digital.bancodigital.service.ContaService;

import java.math.BigDecimal;

@RestController
@RequestMapping("banco-digital/conta")
public class ContaController {

    @Autowired
    private ContaService service;
    
    @PutMapping("/adicionar/{idUsuario}")
    public void adicionarConta(@RequestBody ContaDTO contaDto, @PathVariable int idUsuario){
     service.adicionarConta(contaDto, idUsuario);   
    }

    @PutMapping("atualizar/{id}")
    public void atualizarConta(@RequestBody ContaDTO contaDto, @PathVariable int numeroConta){
        service.atualizarConta(contaDto, numeroConta);
    }

    @GetMapping("/deposito-saque/{numeroConta}/{valor}/{operacao}")
    public void depositoSaque(@PathVariable int numeroConta,
                              @PathVariable BigDecimal valor,
                              @PathVariable String operacao){
        service.depositoSaque(numeroConta,valor,operacao);
    }

}
