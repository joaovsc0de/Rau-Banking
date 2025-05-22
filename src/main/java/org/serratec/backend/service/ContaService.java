package org.serratec.backend.service;

import org.serratec.backend.entity.Conta;
import org.serratec.backend.exception.ContaException;
import org.serratec.backend.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    public void deletarPorId(Long id) {
        if (repository.existsById(id)) {
            Optional<Conta> contaOp = repository.findById(id);
            contaOp.get().setAtivo(false);
            repository.save(contaOp.get());
        } else {
            throw new ContaException("Conta com ID " + id + " não encontrada");
        }
    }
    public Optional<Conta> findByID(Long id) {
        return repository.findById(id);
    }
    public Conta buscarPorId(Long id){
        Optional<Conta> c = repository.findById(id);
        if(!c.isPresent()){
            throw new ContaException("Conta não encontrada");
        }
        return c.get();
    }

    public Conta inserir (Conta conta) {
        Optional<Conta> con = repository.findByCpf(conta.getCpf());

        if (con.isPresent()) {
            throw new ContaException("Cpf já cadastrado");
        }
        return repository.save(conta);
    }

   public List<Conta> listar() {
        return repository.findAll();
   }

    public Conta salvar(Conta conta){
        return repository.save(conta);
    }
}
