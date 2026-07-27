/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.controller;

import com.senai.frota.model.LiberacaoDTO;
import com.senai.frota.service.LiberacaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Micro
 */
@RestController
@RequestMapping("/api/admin/liberacao")
public class LiberacaoController {
    
    @Autowired
    private LiberacaoService lservice;

    @PostMapping
    public String register(@RequestBody LiberacaoDTO l){
        lservice.register(l);
        return "Liberação registrada com sucesso";
    }

    @PutMapping
    public String editLiberacao(@RequestBody LiberacaoDTO l){
        lservice.editLiberacao(l);
        return "Liberação editada com sucesso";
    }

    @DeleteMapping("/{id}")
    public String deleteLiberacao(@PathVariable Long id) {
        lservice.deleteLiberacao(id);
        return "Liberação excluída com sucesso";
    }

    // ---------------- Listas  ----------------

    @GetMapping("/alerta")
    public List<LiberacaoDTO> listAlerta() {
        return lservice.listAlerta();
    }

    @GetMapping("/open")
    public List<LiberacaoDTO> listOpen() {
        return lservice.listOpen();
    }

    @GetMapping("/close")
    public List<LiberacaoDTO> listClose() {
        return lservice.listClose();
    }
    
    @GetMapping("/operador/{idUser}")
    public List<LiberacaoDTO> listByOp(@PathVariable Long idUser) {
        return lservice.listByOp(idUser);
    }

    @GetMapping("/equipamento/{idEquip}")
    public List<LiberacaoDTO> listByEquip(@PathVariable Long idEquip) {
        return lservice.listByEquip(idEquip);
    }

    // ---------------- Acoes ----------------

    @PutMapping("/resolve/{id}")
    public String resolve(@PathVariable Long id){
        lservice.resolve(id);
        return "Alerta resolvido com sucesso";
    }
    
}