package banco.digital.bancodigital.repository;

import banco.digital.bancodigital.dto.ContaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import banco.digital.bancodigital.model.Conta;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM banco_digital.conta WHERE NUMERO = :numero")
    List<ContaDto> findContaByNumConta(int numero);
}
