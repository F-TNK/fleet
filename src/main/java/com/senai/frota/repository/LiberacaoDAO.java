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
import java.sql.Timestamp;
import java.sql.Types;
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
    
    /**
     * dupla para lidar com campos preenchidos no final de um processo:
     *  - Ler do Banco: (Double) rs.getObject(...)
     *  - Gravar no Banco: stmt.setObject(..., Types.DOUBLE)
     *                                      - ou Types.INT, Types.LONG
     */
    
    // ------------------------- ADMIN ------------------------
    
    public void register(LiberacaoDTO l) {
        try {
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "insert into liberacao (iduser, idequip, data_hora_retirada,"
                    + " data_hora_devolucao, data_hora_retirada_real, "
                    + "horimetro_inicial, combustivel_inicial, local_uso, "
                    + "observacoes_retirada, alerta) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            stmt.setLong(1, l.getIdOperador());
            stmt.setLong(2, l.getIdEquip());
            
            // conversao LocalDateTime (Java) pra Timestamp (SQL)
            stmt.setTimestamp(3, l.getDataHoraRetirada() != null ? Timestamp.valueOf(l.getDataHoraRetirada()) : null);
            stmt.setTimestamp(4, l.getDataHoraDevolucao() != null ? Timestamp.valueOf(l.getDataHoraDevolucao()) : null);
            stmt.setTimestamp(5, l.getDataHoraRetiradaReal() != null ? Timestamp.valueOf(l.getDataHoraRetiradaReal()) : null);
            
            // setObject em vez de setDouble para aceitar valor nulo (se nao vira 0.0)
            // esses dados sao preenchidos em outra funcao
            stmt.setObject(6, l.getHorimetroInicial(), Types.DOUBLE);
            stmt.setObject(7, l.getCombustivelInicial(), Types.DOUBLE);
            
            stmt.setString(8, l.getLocalUso());
            stmt.setString(9, l.getObservacoesRetirada());
            stmt.setBoolean(10, l.isAlerta());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Falha na inserção - Nenhuma linha foi alterada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    // ------------------------- LISTAS ------------------------
    
    public List<LiberacaoDTO> listAlerta() {
        List<LiberacaoDTO> liberacoes = new ArrayList<>();
        
        try {
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT l.*, u.nome AS nomeOp, e.nome AS nomeEquip " +
                "FROM liberacao l " +
                "INNER JOIN users u ON l.iduser = u.iduser " +
                "INNER JOIN equip e ON l.idequip = e.idequip " +
                "WHERE l.alerta = true");
            ResultSet rs = stmt.executeQuery();
            // INNER JOIN pra nomes de usuario e equipamento

            while (rs.next()) {
                LiberacaoDTO l = new LiberacaoDTO();
                
                l.setId(rs.getLong("id"));
                l.setIdOperador(rs.getLong("iduser"));
                l.setNomeOp(rs.getString("nomeOp")); 
                l.setIdEquip(rs.getLong("idequip"));
                l.setNomeEquip(rs.getString("nomeEquip")); 
                // conversao Timestamp (SQL) pra LocalDateTime (Java) 
                if (rs.getTimestamp("data_hora_retirada") != null){
                    l.setDataHoraRetirada(rs.getTimestamp("data_hora_retirada").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_devolucao") != null){
                    l.setDataHoraDevolucao(rs.getTimestamp("data_hora_devolucao").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_retirada_real") != null){
                    l.setDataHoraRetiradaReal(rs.getTimestamp("data_hora_retirada_real").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_devolucao_real") != null){
                    l.setDataHoraDevolucaoReal(rs.getTimestamp("data_hora_devolucao_real").toLocalDateTime());
                }
                
                // getObject caso ainda esteja registrado como nulo
                l.setHorimetroInicial((Double) rs.getObject("horimetro_inicial"));
                l.setCombustivelInicial((Double) rs.getObject("combustivel_inicial"));
                l.setHorimetroFinal((Double) rs.getObject("horimetro_final"));
                l.setCombustivelFinal((Double) rs.getObject("combustivel_final"));
                
                l.setLocalUso(rs.getString("local_uso"));
                l.setObservacoesRetirada(rs.getString("observacoes_retirada"));
                l.setObservacoesDevolucao(rs.getString("observacoes_devolucao"));
                l.setAlerta(rs.getBoolean("alerta"));
                
                liberacoes.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liberacoes;
    }
    
    public List<LiberacaoDTO> listOpen() {
        List<LiberacaoDTO> liberacoes = new ArrayList<>();
        
        try {
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT l.*, u.nome AS nomeOp, e.nome AS nomeEquip " +
                "FROM liberacao l " +
                "INNER JOIN users u ON l.iduser = u.iduser " +
                "INNER JOIN equip e ON l.idequip = e.idequip " +
                "WHERE l.data_hora_devolucao_real IS NULL");
            ResultSet rs = stmt.executeQuery();
            // INNER JOIN pra nomes de usuario e equipamento

            while (rs.next()) {
                LiberacaoDTO l = new LiberacaoDTO();
                
                l.setId(rs.getLong("id"));
                l.setIdOperador(rs.getLong("iduser"));
                l.setNomeOp(rs.getString("nomeOp")); 
                l.setIdEquip(rs.getLong("idequip"));
                l.setNomeEquip(rs.getString("nomeEquip")); 
                // conversao Timestamp (SQL) pra LocalDateTime (Java) 
                if (rs.getTimestamp("data_hora_retirada") != null){
                    l.setDataHoraRetirada(rs.getTimestamp("data_hora_retirada").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_devolucao") != null){
                    l.setDataHoraDevolucao(rs.getTimestamp("data_hora_devolucao").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_retirada_real") != null){
                    l.setDataHoraRetiradaReal(rs.getTimestamp("data_hora_retirada_real").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_devolucao_real") != null){
                    l.setDataHoraDevolucaoReal(rs.getTimestamp("data_hora_devolucao_real").toLocalDateTime());
                }
                
                // getObject pq esses estarao registrados como nulo 
                // estarao vazios ate o fechamento, sem essa funcao retorna 0.0 ao inve de null
                // NULLPOINTEREXCEPTION
                l.setHorimetroInicial((Double) rs.getObject("horimetro_inicial"));
                l.setCombustivelInicial((Double) rs.getObject("combustivel_inicial"));
                l.setHorimetroFinal((Double) rs.getObject("horimetro_final"));
                l.setCombustivelFinal((Double) rs.getObject("combustivel_final"));
                
                l.setLocalUso(rs.getString("local_uso"));
                l.setObservacoesRetirada(rs.getString("observacoes_retirada"));
                l.setObservacoesDevolucao(rs.getString("observacoes_devolucao"));
                l.setAlerta(rs.getBoolean("alerta"));
                
                liberacoes.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liberacoes;
    }
    
    public List<LiberacaoDTO> listClose() {
        List<LiberacaoDTO> liberacoes = new ArrayList<>();
        try {
            Connection conn = Conexao.connect();
            
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT l.*, u.nome AS nomeOp, e.nome AS nomeEquip " +
                "FROM liberacao l " +
                "INNER JOIN users u ON l.iduser = u.iduser " +
                "INNER JOIN equip e ON l.idequip = e.idequip " +
                "WHERE l.data_hora_devolucao_real IS NOT NULL AND l.alerta = false");
            ResultSet rs = stmt.executeQuery();
            // INNER JOIN pra nomes de usuario e equipamento

            while (rs.next()) {
                LiberacaoDTO l = new LiberacaoDTO();
                
                l.setId(rs.getLong("id"));
                l.setIdOperador(rs.getLong("iduser"));
                l.setNomeOp(rs.getString("nomeOp")); 
                l.setIdEquip(rs.getLong("idequip"));
                l.setNomeEquip(rs.getString("nomeEquip")); 
                // conversao Timestamp (SQL) pra LocalDateTime (Java) 
                if (rs.getTimestamp("data_hora_retirada") != null){
                    l.setDataHoraRetirada(rs.getTimestamp("data_hora_retirada").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_devolucao") != null){
                    l.setDataHoraDevolucao(rs.getTimestamp("data_hora_devolucao").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_retirada_real") != null){
                    l.setDataHoraRetiradaReal(rs.getTimestamp("data_hora_retirada_real").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_devolucao_real") != null){
                    l.setDataHoraDevolucaoReal(rs.getTimestamp("data_hora_devolucao_real").toLocalDateTime());
                }
                
                // getObject caso estejam null
                l.setHorimetroInicial((Double) rs.getObject("horimetro_inicial"));
                l.setCombustivelInicial((Double) rs.getObject("combustivel_inicial"));
                l.setHorimetroFinal((Double) rs.getObject("horimetro_final"));
                l.setCombustivelFinal((Double) rs.getObject("combustivel_final"));
                
                l.setLocalUso(rs.getString("local_uso"));
                l.setObservacoesRetirada(rs.getString("observacoes_retirada"));
                l.setObservacoesDevolucao(rs.getString("observacoes_devolucao"));
                l.setAlerta(rs.getBoolean("alerta"));
                
                liberacoes.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liberacoes;
    }
    
    
    // ------------------------- LISTAS FILTRO ------------------------
    
    public List<LiberacaoDTO> listByOp(Long idUser) {
        List<LiberacaoDTO> liberacoes = new ArrayList<>();
        
        try {
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT l.*, u.nome AS nomeOp, e.nome AS nomeEquip " +
                "FROM liberacao l " +
                "INNER JOIN users u ON l.iduser = u.iduser " +
                "INNER JOIN equip e ON l.idequip = e.idequip " +
                "WHERE l.iduser = ? ORDER BY l.data_hora_retirada DESC");
            stmt.setLong(1, idUser);
            ResultSet rs = stmt.executeQuery();
            // INNER JOIN pra nomes de usuario e equipamento, restringir por idUser
            //  E ORDEM DECRESCENTE data

            while (rs.next()) {
                LiberacaoDTO l = new LiberacaoDTO();
                
                l.setId(rs.getLong("id"));
                l.setIdOperador(rs.getLong("iduser"));
                l.setNomeOp(rs.getString("nomeOp")); 
                l.setIdEquip(rs.getLong("idequip"));
                l.setNomeEquip(rs.getString("nomeEquip")); 
                // conversao Timestamp (SQL) pra LocalDateTime (Java) 
                if (rs.getTimestamp("data_hora_retirada") != null){
                    l.setDataHoraRetirada(rs.getTimestamp("data_hora_retirada").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_devolucao") != null){
                    l.setDataHoraDevolucao(rs.getTimestamp("data_hora_devolucao").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_retirada_real") != null){
                    l.setDataHoraRetiradaReal(rs.getTimestamp("data_hora_retirada_real").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_devolucao_real") != null){
                    l.setDataHoraDevolucaoReal(rs.getTimestamp("data_hora_devolucao_real").toLocalDateTime());
                }
                
                // getObject para double caso estejam null
                l.setHorimetroInicial((Double) rs.getObject("horimetro_inicial"));
                l.setCombustivelInicial((Double) rs.getObject("combustivel_inicial"));
                l.setHorimetroFinal((Double) rs.getObject("horimetro_final"));
                l.setCombustivelFinal((Double) rs.getObject("combustivel_final"));
                
                l.setLocalUso(rs.getString("local_uso"));
                l.setObservacoesRetirada(rs.getString("observacoes_retirada"));
                l.setObservacoesDevolucao(rs.getString("observacoes_devolucao"));
                l.setAlerta(rs.getBoolean("alerta"));
                
                liberacoes.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liberacoes;
    }
    
    public List<LiberacaoDTO> listByEquip(Long idEquip) {
        List<LiberacaoDTO> liberacoes = new ArrayList<>();
        
        try {
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT l.*, u.nome AS nomeOp, e.nome AS nomeEquip " +
                "FROM liberacao l " +
                "INNER JOIN users u ON l.iduser = u.iduser " +
                "INNER JOIN equip e ON l.idequip = e.idequip " +
                "WHERE l.idequip = ? ORDER BY l.data_hora_retirada DESC");
            stmt.setLong(1, idEquip);
            ResultSet rs = stmt.executeQuery();
            // INNER JOIN pra nomes de usuario e equipamento, restringir por idEquip
            //  E ORDEM DECRESCENTE data  
            
            while (rs.next()) {
                LiberacaoDTO l = new LiberacaoDTO();
                
                l.setId(rs.getLong("id"));
                l.setIdOperador(rs.getLong("iduser"));
                l.setNomeOp(rs.getString("nomeOp")); 
                l.setIdEquip(rs.getLong("idequip"));
                l.setNomeEquip(rs.getString("nomeEquip")); 
                // conversao Timestamp (SQL) pra LocalDateTime (Java) 
                if (rs.getTimestamp("data_hora_retirada") != null){
                    l.setDataHoraRetirada(rs.getTimestamp("data_hora_retirada").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_devolucao") != null){
                    l.setDataHoraDevolucao(rs.getTimestamp("data_hora_devolucao").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_retirada_real") != null){
                    l.setDataHoraRetiradaReal(rs.getTimestamp("data_hora_retirada_real").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_devolucao_real") != null){
                    l.setDataHoraDevolucaoReal(rs.getTimestamp("data_hora_devolucao_real").toLocalDateTime());
                }
                
                // getObject para double caso estejam null
                l.setHorimetroInicial((Double) rs.getObject("horimetro_inicial"));
                l.setCombustivelInicial((Double) rs.getObject("combustivel_inicial"));
                l.setHorimetroFinal((Double) rs.getObject("horimetro_final"));
                l.setCombustivelFinal((Double) rs.getObject("combustivel_final"));
                
                l.setLocalUso(rs.getString("local_uso"));
                l.setObservacoesRetirada(rs.getString("observacoes_retirada"));
                l.setObservacoesDevolucao(rs.getString("observacoes_devolucao"));
                l.setAlerta(rs.getBoolean("alerta"));
                
                liberacoes.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liberacoes;
    }
    
    // --- RESTO CRUD ---
    public int editLiberacao(LiberacaoDTO l) {
        try {
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "update liberacao set iduser = ?, idequip = ?, data_hora_retirada = ?, "
                    + "data_hora_devolucao = ?, data_hora_retirada_real = ?, "
                    + "horimetro_inicial = ?, combustivel_inicial = ?, local_uso = ?, "
                    + "observacoes_retirada = ?, alerta = ? where id = ?");

            stmt.setLong(1, l.getIdOperador());
            stmt.setLong(2, l.getIdEquip());
            // Conversao Java --> SQL
            stmt.setTimestamp(3, l.getDataHoraRetirada() != null ? Timestamp.valueOf(l.getDataHoraRetirada()) : null);
            stmt.setTimestamp(4, l.getDataHoraDevolucao() != null ? Timestamp.valueOf(l.getDataHoraDevolucao()) : null);
            stmt.setTimestamp(5, l.getDataHoraRetiradaReal() != null ? Timestamp.valueOf(l.getDataHoraRetiradaReal()) : null);
            // Metodo reverso do list
            // Trata como Object o que vem nulo e transforma em Double
            stmt.setObject(6, l.getHorimetroInicial(), Types.DOUBLE);
            stmt.setObject(7, l.getCombustivelInicial(), Types.DOUBLE);
            
            stmt.setString(8, l.getLocalUso());
            stmt.setString(9, l.getObservacoesRetirada());
            stmt.setBoolean(10, l.isAlerta());
            stmt.setLong(11, l.getId());
            
            return stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
        
    }

    public int deleteLiberacao(Long id){
        try {
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "delete from liberacao where id = ?");
            stmt.setLong(1, id);

            return stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    
    // ----------------------- OPERADOR -----------------------

    public List<LiberacaoDTO> listById(Long idUser) {
        List<LiberacaoDTO> liberacoes = new ArrayList<>();
        
        try {
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT l.*, u.nome AS nomeOp, e.nome AS nomeEquip " +
                "FROM liberacao l " +
                "INNER JOIN users u ON l.iduser = u.iduser " +
                "INNER JOIN equip e ON l.idequip = e.idequip " +
                "WHERE l.iduser = ? AND l.data_hora_devolucao_real IS NULL");
            stmt.setLong(1, idUser);
            ResultSet rs = stmt.executeQuery();
            // INNER JOIN pra nomes de usuario e equipamento

            while (rs.next()) {
                LiberacaoDTO l = new LiberacaoDTO();
                
                l.setId(rs.getLong("id"));
                l.setIdOperador(rs.getLong("iduser"));
                l.setNomeOp(rs.getString("nomeOp")); 
                l.setIdEquip(rs.getLong("idequip"));
                l.setNomeEquip(rs.getString("nomeEquip")); 
                // conversao Timestamp (SQL) pra LocalDateTime (Java) 
                if (rs.getTimestamp("data_hora_retirada") != null){
                    l.setDataHoraRetirada(rs.getTimestamp("data_hora_retirada").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_devolucao") != null){
                    l.setDataHoraDevolucao(rs.getTimestamp("data_hora_devolucao").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_retirada_real") != null){
                    l.setDataHoraRetiradaReal(rs.getTimestamp("data_hora_retirada_real").toLocalDateTime());
                }
                if (rs.getTimestamp("data_hora_devolucao_real") != null){
                    l.setDataHoraDevolucaoReal(rs.getTimestamp("data_hora_devolucao_real").toLocalDateTime());
                }
                
                // getObject para double caso estejam null
                l.setHorimetroInicial((Double) rs.getObject("horimetro_inicial"));
                l.setCombustivelInicial((Double) rs.getObject("combustivel_inicial"));
                l.setHorimetroFinal((Double) rs.getObject("horimetro_final"));
                l.setCombustivelFinal((Double) rs.getObject("combustivel_final"));
                
                l.setLocalUso(rs.getString("local_uso"));
                l.setObservacoesRetirada(rs.getString("observacoes_retirada"));
                l.setObservacoesDevolucao(rs.getString("observacoes_devolucao"));
                l.setAlerta(rs.getBoolean("alerta"));
                
                liberacoes.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liberacoes;
    }
    
    
    // --- Acoes (updates) ---
    
    public int pickUp(LiberacaoDTO l) {
        try {
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "update liberacao set data_hora_retirada_real = ?, horimetro_inicial = ?, "
                    + "combustivel_inicial = ? where id = ?");
            
            
            stmt.setTimestamp(1, l.getDataHoraRetiradaReal() != null ? Timestamp.valueOf(l.getDataHoraRetiradaReal()) : null);
            // Metodo reverso do list
            // Trata como Object o que vem nulo e transforma em Double
            stmt.setObject(2, l.getHorimetroInicial(), Types.DOUBLE);
            stmt.setObject(3, l.getCombustivelInicial(), Types.DOUBLE);
            
            stmt.setLong(4, l.getId());

            return stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public int close(LiberacaoDTO l) {
        try {
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "update liberacao set data_hora_devolucao_real = ?, horimetro_final = ?, "
                    + "combustivel_final = ?, observacoes_devolucao = ?, alerta = ? where id = ?");
            
            stmt.setTimestamp(1, l.getDataHoraDevolucaoReal() != null ? Timestamp.valueOf(l.getDataHoraDevolucaoReal()) : null);
            // Metodo reverso do list
            // Trata como Object o que vem nulo e transforma em Double
            stmt.setObject(2, l.getHorimetroFinal(), Types.DOUBLE);
            stmt.setObject(3, l.getCombustivelFinal(), Types.DOUBLE);
            
            stmt.setString(4, l.getObservacoesDevolucao());
            stmt.setBoolean(5, l.isAlerta());
            stmt.setLong(6, l.getId());

            return stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int resolve(Long id) {
        try {
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "update liberacao set alerta = false where id = ?");
            stmt.setLong(1, id);
            
            return stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    public boolean conflitoHorario(Long idEquip, LocalDateTime novaRetirada, LocalDateTime novaDevolucao, Long idLiberacaoAtual) {
        try {
            Connection conn = Conexao.connect();
            String sql = "SELECT COUNT(*) FROM liberacao WHERE idequip = ? "
                       + "AND data_hora_devolucao_real IS NULL "
                       + "AND data_hora_retirada < ? AND data_hora_devolucao > ?";
            
            // Se estiver editando ignora a própria liberação na contagem
            if (idLiberacaoAtual != null) {
                sql += " AND id != ?";
            }
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, idEquip);
            stmt.setTimestamp(2, Timestamp.valueOf(novaDevolucao));
            stmt.setTimestamp(3, Timestamp.valueOf(novaRetirada));
            
            if (idLiberacaoAtual != null) {
                stmt.setLong(4, idLiberacaoAtual);
            }
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; 
                // true se tiver 1 ou mais conflitos
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false; 
        // False se nao tiver conflito
    }
    
}
