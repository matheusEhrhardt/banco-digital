package banco.digital.bancodigital.repository;

import banco.digital.bancodigital.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import banco.digital.bancodigital.model.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM banco_digital.usuario WHERE CPF_CNPJ = :cpfCnpj")
    List<Usuario> findUsuarioByCpfCnpj(String cpfCnpj);
}
