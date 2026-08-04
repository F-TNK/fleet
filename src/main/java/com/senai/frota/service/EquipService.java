/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.service;

import com.senai.frota.model.EquipDTO;
import com.senai.frota.repository.EquipDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author ftana
 */
@Service
public class EquipService {
    
    @Autowired
    private EquipDAO edao;

    public List<EquipDTO> listAll(){
        return edao.listAll();
    }

    public int addEquip(EquipDTO equip) {
        String message = "";
        
        if (equip.getVidaUtil() == null || equip.getVidaUtil() <= 0){
            message = "Vida útil deve ser maior que zero.";
        }
        if(equip.getHorasUso() == null){
            equip.setHorasUso(0.0);
        }
        if (equip.getHorasUso() > equip.getVidaUtil()){
            message = "Horas de uso não podem exceder a vida útil.";
        }
        
        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }

        return edao.addEquip(equip);
    }

    public int editEquip(EquipDTO equip) {
        if (equip.getVidaUtil() == null || equip.getVidaUtil() <= 0){
            throw new IllegalArgumentException(
                    "Vida útil deve ser maior que zero.");
        }
        if(equip.getHorasUso() == null){
            equip.setHorasUso(0.0);
        }
        return edao.editEquip(equip);
    }
    
    public EquipDTO findById(Long id) {
        return edao.findById(id);
    }

    public int deleteById(Long id){
        return edao.deleteById(id);
    }
}
