package banco.digital.bancodigital.repository;

import banco.digital.bancodigital.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao,Integer> {
}
