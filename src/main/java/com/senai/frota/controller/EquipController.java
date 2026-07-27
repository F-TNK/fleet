/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.controller;

import com.senai.frota.model.EquipDTO;
import com.senai.frota.service.EquipService;
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
@RequestMapping("/api/equip")
public class EquipController {
    
    @Autowired
    private EquipService eService;

    @GetMapping
    public List<EquipDTO> listAll (){
        return eService.listAll();
    }

    @PostMapping
    public String addEquip(@RequestBody EquipDTO equip) {
        eService.addEquip(equip);
        return "Equipamento salvo com sucesso";
    }
    
    @PutMapping
    public String editEquip(@RequestBody EquipDTO equip) {
        eService.editEquip(equip);
        return "Equipamento editado com sucesso";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        eService.deleteById(id);
        return "Equipamento excluído com sucesso";
    }
}
