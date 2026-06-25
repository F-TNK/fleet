/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.repository;

import com.senai.frota.model.LiberacaoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ftana
 */
@Repository
public class LiberacaoDAO {
    
    public List<LiberacaoDTO> listaLiberacoes(){
        List<LiberacaoDTO> lista = new ArrayList<>();
        
        try {
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement("Select * from liberacoes");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                LiberacaoDTO l = new LiberacaoDTO();

                l.setId(rs.getLong("idLiberacao"));
                l.setIdOperador(rs.getLong("operador"));
                l.setIdEquip(rs.getLong("equip"));
                l.setDataHoraRetirada(rs.getLocalDateTime("data_hora_retirada"));
                l.setDataHoraDevolucao(rs.getLocalDateTime("data_hora_devolucao"));
                l.setHorimetroInicial(rs.getDouble("horimetro_inicial"));
                l.setHorimetroFinal(rs.getDouble("horimetro_final"));
                l.setCombustivelInicial(rs.getDouble("combustivel_inicial"));
                l.setCombustivelFinal(rs.getDouble("combustivel_final"));
                l.setLocalUso(rs.getString("local_uso"));
                l.setObservacoes(rs.getString("observacoes"));

                lista.add(l);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return lista;
    }
    
    
    public int novaLiberacao(LiberacaoDTO lib){
        
        try {
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "insert into liberacoes "
                    + "(operador, equipamento, data_hora_retirada, data_hora_devolucao, horimetro_inicial, "
                    + "horimetro_final, combustivel_inicial, combustivel_final, local_uso, observacoes) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            stmt.setLong(1, lib.getIdOperador());
            stmt.setLong(2, lib.getIdEquip());
            stmt.setString(3, lib.getDataHoraRetirada());
            stmt.setString(4, lib.getDataHoraDevolucao());
            stmt.setDouble(5, lib.getHorimetroInicial());
            stmt.setDouble(6, lib.getHorimetroFinal());
            stmt.setDouble(7, lib.getCombustivelInicial());
            stmt.setDouble(8, lib.getCombustivelFinal());
            stmt.setString(9, lib.getLocalUso());
            stmt.setString(10, lib.getObservacoes());

            return stmt.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return 0;
    }
    
    
    
}
