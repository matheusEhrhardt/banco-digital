package banco.digital.bancodigital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import banco.digital.bancodigital.dto.ContaDto;
import banco.digital.bancodigital.service.ContaService;

@RestController
@RequestMapping("banco-digital/conta")
public class ContaController {

    @Autowired
    private ContaService service;
    
    @PutMapping("/adicionar/{idUsuario}")
    public void adicionarConta(@RequestBody ContaDto contaDto,@PathVariable int idUsuario){
     service.adicionarConta(contaDto, idUsuario);   
    }

    @PutMapping("atualizar/{id}")
    public void atualizarConta(@RequestBody ContaDto contaDto,@PathVariable int id){
        service.atualizarConta(contaDto, id);
    }

}
