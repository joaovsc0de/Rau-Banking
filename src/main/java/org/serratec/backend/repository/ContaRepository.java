package org.serratec.backend.repository;

import org.serratec.backend.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByCpf(String cpf);
    Optional<Conta> findById(Long id);
}
