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

    @GetMapping("/login/{cpfCnpj}/{senha}")
    public ResponseEntity<Boolean> fazerLogin(@PathVariable String cpfCnpj,@PathVariable String senha){

        ResponseEntity<Boolean> acessoLiberado = service.fazerLogin(cpfCnpj,senha);
        return acessoLiberado;
    }
}