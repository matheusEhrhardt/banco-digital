package banco.digital.bancodigital.service;

import java.util.List;
import java.util.Optional;

import banco.digital.bancodigital.error.handler.ResourseNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import banco.digital.bancodigital.dto.UsuarioDTO;
import banco.digital.bancodigital.model.Usuario;
import banco.digital.bancodigital.repository.UsuarioRepository;

import static org.springframework.http.ResponseEntity.status;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository; 

    @Autowired
    private ContaService contaService;

    @Autowired
    private PasswordEncoder encoder;

    public void cadastrarUsuario(UsuarioDTO usuarioDTO){

        Usuario usuarioExistente = findUsuarioByCpfCnpj(usuarioDTO.getCpfCnpj());

        if (isExists(usuarioExistente)){
            throw new ResourseNotFoundException("Já existe usuário com CPF OU CNPJ: "+ usuarioDTO.getCpfCnpj() );
        }

        Usuario usuario = converteDtoToModel(usuarioDTO);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);

        contaService.criarConta(usuario,usuario.getTipoConta());

    }

    public Usuario findById(int id){
        Optional<Usuario> usuario = repository.findById(id);

        if(usuario.isEmpty()){
            throw new ResourseNotFoundException("Usuário com ID: "+ id + " não encontrado");
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

    public ResponseEntity<Boolean> fazerLogin(String cpfCnpj, String senha){

        Usuario usuario = findUsuarioByCpfCnpj(cpfCnpj);

        if (!isExists(usuario)){
            throw new ResourseNotFoundException("Usuário com CPF OU CNPJ: "+ cpfCnpj + "não encontrado!");
        }

        Boolean acessoLiberado = encoder.matches(senha, usuario.getSenha());
        HttpStatus status = (acessoLiberado) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(acessoLiberado);
    }

    public Usuario findUsuarioByCpfCnpj(String cpfCnpj){
        Optional<Usuario> usuario = repository.findUsuarioByCpfCnpj(cpfCnpj);

        if (!isExists(usuario)){
            return null;
        }

        return usuario.get();
    }

    public Boolean isExists(int id){

        Boolean usuarioExiste = false;

        Optional<Usuario> usuario = repository.findById(id);

        if(usuario.get() != null){
            usuarioExiste = true;
        }

        return usuarioExiste;
    }

    public Boolean isExists(Optional<Usuario> usuarios){
        return !usuarios.isEmpty();
    }

    public Boolean isExists(Usuario usuario){
        return (usuario != null) ? true : false;
    }

    public Usuario converteDtoToModel(UsuarioDTO usuarioDTO){

        Usuario usuario = new ModelMapper().map(usuarioDTO, Usuario.class);
        return usuario;

    }
}
