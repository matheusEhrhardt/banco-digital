package banco.digital.bancodigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import banco.digital.bancodigital.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    
}
