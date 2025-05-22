package org.serratec.backend.service;

import jakarta.transaction.Transactional;
import org.hibernate.TransactionException;
import org.serratec.backend.entity.Conta;
import org.serratec.backend.entity.Transacao;
import org.serratec.backend.exception.ContaException;
import org.serratec.backend.repository.ContaRepository;
import org.serratec.backend.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

@Service
public class TransacaoService {

    @Autowired
    private ContaService cs;

    @Autowired
    private TransacaoRepository repository;

    @Transactional
    public void realizarPix (Transacao transacao) {
        Conta origem = cs.buscarPorId(transacao.getContaOrigem().getId());
        Conta destino = cs.buscarPorId(transacao.getContaDestino().getId());

        if(origem.getSaldo().compareTo(transacao.getValor()) < 0){
            throw new TransactionException("Saldo insuficiente");
        }

        origem.setSaldo(origem.getSaldo().subtract(transacao.getValor()));
        destino.setSaldo(destino.getSaldo().add(transacao.getValor()));

        cs.salvar(origem);
        cs.salvar(destino);
        repository.save(transacao);
    }

    public List<Transacao> listar() {
        return repository.findAll();
    }

    public List<Transacao> listarPorId(Long id){
        if(cs.buscarPorId(id) != null){
            List<Transacao> origem = repository.findByContaOrigemId(id);
            List<Transacao> destino = repository.findByContaDestinoId(id);

            List<Transacao> transacoes = new ArrayList<>();
            if(origem.isEmpty() && destino.isEmpty()){
                throw new ContaException("Conta não possui transações cadastradas");
            }
            transacoes.addAll(origem);
            transacoes.addAll(destino);
            return transacoes;
        }
        throw new ContaException("Conta não existente");
    }

}
