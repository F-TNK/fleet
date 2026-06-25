/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.repository;

import com.senai.frota.model.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Micro
 */
@Repository
public class UserDAO {
    
    public void register(UserDTO u){
        
        try{
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement("insert into users "
                    + "(email, senha, nome, cpf, telefone, endereco, dataNascimento, cargo)"
                    + "values (?, ?, ?, ?, ?, ?, ?, ?)");
            
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getSenha());
            stmt.setString(3, u.getNome());
            stmt.setString(4, u.getCpf());
            stmt.setString(5, u.getTelefone());
            stmt.setString(6, u.getEndereco());
            stmt.setString(7, u.getDataNascimento());
            stmt.setString(8, u.getCargo());
            
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Falha na atualização - Nenhuma linha foi encontrada.");
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    
    public UserDTO login(String email, String senha){
        UserDTO u = new UserDTO();
        
        try{
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "select * from users where email = ? and senha = ?");
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()){
                u.setIdUser(rs.getLong("idUser"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setNome(rs.getString("nome"));
                u.setCpf(rs.getString("cpf"));
                u.setTelefone(rs.getString("telefone"));
                u.setEndereco(rs.getString("endereco"));
                u.setDataNascimento(rs.getString("dataNascimento"));
                u.setCargo(rs.getString("cargo"));
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return u;
    }
    
    
    public List<UserDTO> listUsers(){
        List<UserDTO> users = new ArrayList<>();
        
        try {
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement("select * from users");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                UserDTO u = new UserDTO();
                u.setIdUser(rs.getLong("idUser"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setNome(rs.getString("nome"));
                u.setCpf(rs.getString("cpf"));
                u.setTelefone(rs.getString("telefone"));
                u.setEndereco(rs.getString("endereco"));
                u.setDataNascimento(rs.getString("dataNascimento"));
                u.setCargo(rs.getString("cargo"));
                
                users.add(u);
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return users;
    }
    
    
    public UserDTO addAdmin(Long idUser) {
        UserDTO u = new UserDTO();

        try {
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "update users set cargo = ? where id = ?");
            stmt.setString(1, "administrador");
            stmt.setLong(2, idUser);
            
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                u.setCargo("administrador");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }
    
    public int deleteUser(Long id){
        
        try {
            
            Connection conn = Conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "delete from users where id = ?");
            stmt.setLong(1, id);
            
            return stmt.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return 0;
    }
    
}
