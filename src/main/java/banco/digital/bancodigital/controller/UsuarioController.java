package banco.digital.bancodigital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import banco.digital.bancodigital.dto.UsuarioDTO;
import banco.digital.bancodigital.service.UsuarioService;

@RestController
@RequestMapping("banco-digital/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;

    @PostMapping
    public void cadastrar(@RequestBody UsuarioDTO usuarioDTO){
         service.cadastrarUsuario(usuarioDTO);
    }

    @PutMapping("/atualizar/{id}")
    public void atualizarCadastro(@RequestBody UsuarioDTO usuarioDTO, @PathVariable int id){
        service.atualizarCadastro(usuarioDTO, id);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> fazerLogin(@RequestBody UsuarioDTO usuarioDTO){
        ResponseEntity<Boolean> acessoLiberado = service.fazerLogin(usuarioDTO.getCpfCnpj(),usuarioDTO.getSenha());
        return acessoLiberado;
    }
}