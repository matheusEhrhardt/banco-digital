//package banco.digital.bancodigital.jwt.services;
//
//import banco.digital.bancodigital.jwt.data.DetalheUsuarioData;
//import banco.digital.bancodigital.model.Usuario;
//import banco.digital.bancodigital.repository.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class DetalheUsuarioImpl implements UserDetailsService {
//
//    @Autowired
//    private UsuarioRepository repository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<Usuario> usuario = repository.findUsuarioByCpfCnpj(username);
//
//        if(usuario.isEmpty()){
//            new UsernameNotFoundException("Usuario com cpf" + username + "não encontrado!");
//        }
//
//        return new DetalheUsuarioData(usuario);
//    }
//}
