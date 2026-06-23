/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.model;

/**
 *
 * @author Micro
 */
public class EquipDTO {
    
    private Long idEquip;
    private String nome;
    private String modelo;
    private String dataAquisicao;
    private Double horasUso;
    private Double vidaUtil;
    private Double nivelCombustivel;
    private String status;

    public EquipDTO() {
    }

    public EquipDTO(Long idEquip, String nome, String modelo, String dataAquisicao, Double horasUso, Double vidaUtil, Double nivelCombustivel, String status) {
        this.idEquip = idEquip;
        this.nome = nome;
        this.modelo = modelo;
        this.dataAquisicao = dataAquisicao;
        this.horasUso = horasUso;
        this.vidaUtil = vidaUtil;
        this.nivelCombustivel = nivelCombustivel;
        this.status = status;
    }

    public Long getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(Long idEquip) {
        this.idEquip = idEquip;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(String dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public Double getHorasUso() {
        return horasUso;
    }

    public void setHorasUso(Double horasUso) {
        this.horasUso = horasUso;
    }

    public Double getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(Double vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public Double getNivelCombustivel() {
        return nivelCombustivel;
    }

    public void setNivelCombustivel(Double nivelCombustivel) {
        this.nivelCombustivel = nivelCombustivel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    // ----- FUNC -----
    public Double porcentUso(){
        Double porc = (horasUso / vidaUtil * 100);
        return porc;
    }
    
}
