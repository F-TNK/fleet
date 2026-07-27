/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.service;

import com.senai.frota.model.EquipDTO;
import com.senai.frota.repository.EquipDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (equip.getVidaUtil() == null || equip.getVidaUtil() <= 0){
            throw new IllegalArgumentException(
                    "Vida útil deve ser maior que zero.");
        }
        if(equip.getHorasUso() == null){
            equip.setHorasUso(0.0);
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

    public int deleteById(Long id){
        return edao.deleteById(id);
    }
}
