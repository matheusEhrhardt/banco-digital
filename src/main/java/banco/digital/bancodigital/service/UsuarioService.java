package banco.digital.bancodigital.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banco.digital.bancodigital.dto.UsuarioDTO;
import banco.digital.bancodigital.model.Usuario;
import banco.digital.bancodigital.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository; 

    @Autowired
    private ContaService contaService;

    public void cadastrarUsuario(UsuarioDTO usuarioDTO){

        Usuario usuario = converteDtoToModel(usuarioDTO);
        repository.save(usuario);

        contaService.criarConta(usuario,usuario.getTipoConta());

    }

    public Usuario findById(int id){
        Optional<Usuario> usuario = repository.findById(id);

        if(usuario.get() == null){

        }

        return usuario.get();
    }

    public void atualizarCadastro(UsuarioDTO usuarioDTO, int id){

        Boolean usuarioExiste = isExists(id);

        if(!usuarioExiste){
            System.out.println("usuaria não encontrado");
        }

        usuarioDTO.setId(id);
        Usuario usuario = converteDtoToModel(usuarioDTO);
        repository.save(usuario);
    }

    public Boolean isExists(int id){

        Boolean usuarioExiste = false;

        Optional<Usuario> usuario = repository.findById(id);

        if(usuario.get() != null){
            usuarioExiste = true;
        }

        return usuarioExiste;
    }

    public Usuario converteDtoToModel(UsuarioDTO usuarioDTO){

        Usuario usuario = new ModelMapper().map(usuarioDTO, Usuario.class);
        return usuario;

    }
}
