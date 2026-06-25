/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.repository;

import com.senai.frota.model.EquipDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ftana
 */
@Repository
public class EquipDAO {
    
    public List<EquipDTO> listAll(){
        List<EquipDTO> lista = new ArrayList<>();
        
        try {
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement("Select * from equip");
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()){
                EquipDTO e = new EquipDTO();
                e.setIdEquip(rs.getLong("idEquip"));
                e.setNome(rs.getString("nome"));
                e.setModelo(rs.getString("modelo"));
                e.setDataAquisicao(rs.getString("data_aquisicao"));
                e.setHorasUso(rs.getDouble("horas_uso"));
                e.setVidaUtil(rs.getDouble("vida_util"));
                e.setNivelCombustivel(rs.getDouble("nivel_combustivel"));
                e.setStatus(rs.getString("staus"));
                
                lista.add(e);
            }
            
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return lista;
    }
    
    
    public int addEquip (EquipDTO equip){
        
        try {
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement("insert into equip "
                    + "(nome, modelo, data_aquisicao, horas_uso, vida_util, nivel_combustivel, status)"
                    + "values (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, equip.getNome());
            stmt.setString(2, equip.getModelo());
            stmt.setString(3, equip.getDataAquisicao());
            stmt.setDouble(4, equip.getHorasUso());
            stmt.setDouble(5, equip.getVidaUtil());
            stmt.setDouble(6, equip.getNivelCombustivel());
            stmt.setString(7, equip.getStatus());
            
            return stmt.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return 0;
    }
    
    
    public int editEquip (EquipDTO equip){
        
        try {
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "update equip nome = ?, modelo = ?, data_aquisicao = ?,"
                    + " horas_uso = ?, vida_util = ?, nivel_combustivel = ?, status = ?"
                    + "where idEquip = ?");
            
            stmt.setString(1, equip.getNome());
            stmt.setString(2, equip.getModelo());
            stmt.setString(3, equip.getDataAquisicao());
            stmt.setDouble(4, equip.getHorasUso());
            stmt.setDouble(5, equip.getVidaUtil());
            stmt.setDouble(6, equip.getNivelCombustivel());
            stmt.setString(7, equip.getStatus());
            
            return stmt.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return 0;
    }
    
    
    public int deleteById(Long id){
        
        try {
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "delete from equip where id = ?");
            stmt.setLong(1, id);
            
            return stmt.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return 0;
    }
    
}
