package banco.digital.bancodigital.controller;

import banco.digital.bancodigital.dto.TransacaoDTO;
import banco.digital.bancodigital.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("banco-digital/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping("/deposito-saque")
    public void depositoSaque(@RequestBody TransacaoDTO transacaoDTO){
        service.depositoSaque(transacaoDTO);
    }

    @PostMapping("/transferir")
    public void transferir(@RequestBody TransacaoDTO transacaoDTO){
        service.transferir(transacaoDTO);
    }
}
