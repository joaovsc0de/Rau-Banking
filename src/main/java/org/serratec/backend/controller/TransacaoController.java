package org.serratec.backend.controller;

import org.serratec.backend.entity.Transacao;
import org.serratec.backend.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    TransacaoService service;

    @PostMapping("/pix")
    public void fazerPix(@RequestBody Transacao transacao) {
        service.realizarPix(transacao);
    }

    @GetMapping("listar")
    public List<Transacao> listar() {
        return service.listar();
    }

   @GetMapping("/{id}")
    public List<Transacao> listarPorId(@PathVariable Long id) {
        return service.listarPorId(id);
    }


}
