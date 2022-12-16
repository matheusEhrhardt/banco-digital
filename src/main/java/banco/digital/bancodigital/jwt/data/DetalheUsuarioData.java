//package banco.digital.bancodigital.jwt.data;
//
//import banco.digital.bancodigital.model.Usuario;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Optional;
//
//public class DetalheUsuarioData implements UserDetails {
//
//    private final Optional<Usuario> usuario;
//
//    public DetalheUsuarioData(Optional<Usuario> usuario) {
//        this.usuario = usuario;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return new ArrayList<>();
//    }
//
//    @Override
//    public String getPassword() {
//        return usuario.orElse(new Usuario()).getSenha();
//    }
//
//    @Override
//    public String getUsername() {
//        return usuario.orElse(new Usuario()).getCpfCnpj();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
