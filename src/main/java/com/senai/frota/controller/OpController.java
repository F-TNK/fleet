/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.controller;

import com.senai.frota.model.LiberacaoDTO;
import com.senai.frota.service.LiberacaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Micro
 * 
 * Funcoes do Operador para as Liberacoes
 */
@RestController
@RequestMapping("/api/op/liberacao")
public class OpController {
    
    @Autowired
    private LiberacaoService lservice;

    @GetMapping("/user/{idUser}")
    public List<LiberacaoDTO> listById(@PathVariable Long idUser) {
        return lservice.listById(idUser);
    }

    @PutMapping("/pickup")
    public String pickUp(@RequestBody LiberacaoDTO l){
        lservice.pickUp(l);
        return "Retirada registrada com sucesso";
    }

    @PutMapping("/close")
    public String close(@RequestBody LiberacaoDTO l){
        lservice.close(l);
        return "Devolução registrada com sucesso";
    }
    
}
