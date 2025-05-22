package org.serratec.backend.repository;

import org.serratec.backend.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    Optional<Transacao> findById(Long id);
    List<Transacao> findByContaOrigemId(Long id);
    List<Transacao> findByContaDestinoId(Long id);
}
