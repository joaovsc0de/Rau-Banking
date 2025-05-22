package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.entity.Conta;
import org.serratec.backend.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService service;

    @PostMapping("/inserir")
    @ResponseStatus(HttpStatus.CREATED)
    public Conta inserir(@Valid @RequestBody Conta conta) {
        return service.inserir(conta);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Conta>> listar () {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> alterar(@Valid @PathVariable Long id, @RequestBody Conta conta){
        if(service.findByID(conta.getId()).isPresent()){
            conta.setId(id);
            return ResponseEntity.ok(service.salvar(conta));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Conta> remover(@Valid @PathVariable Long id){
        service.deletarPorId(id);
            return ResponseEntity.noContent().build();

    }
}
