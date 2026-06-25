/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.model;

import java.time.LocalDateTime;

/**
 *
 * @author ftana
 */
public class LiberacaoDTO {
    
    private Long id;
    private Long idOperador;
    private Long idEquip;
    private LocalDateTime dataHoraRetirada;
    private LocalDateTime dataHoraDevolucao;
    private Double horimetroInicial;
    private Double horimetroFinal;
    private Double combustivelInicial;
    private Double combustivelFinal;
    private String localUso;
    private String observacoes;

    public LiberacaoDTO() {
    }

    public LiberacaoDTO(Long id, Long idOperador, Long idEquip, LocalDateTime dataHoraRetirada, LocalDateTime dataHoraDevolucao, Double horimetroInicial, Double horimetroFinal, Double combustivelInicial, Double combustivelFinal, String localUso, String observacoes) {
        this.id = id;
        this.idOperador = idOperador;
        this.idEquip = idEquip;
        this.dataHoraRetirada = dataHoraRetirada;
        this.dataHoraDevolucao = dataHoraDevolucao;
        this.horimetroInicial = horimetroInicial;
        this.horimetroFinal = horimetroFinal;
        this.combustivelInicial = combustivelInicial;
        this.combustivelFinal = combustivelFinal;
        this.localUso = localUso;
        this.observacoes = observacoes;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(Long idOperador) {
        this.idOperador = idOperador;
    }

    public Long getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(Long idEquip) {
        this.idEquip = idEquip;
    }

    public LocalDateTime getDataHoraRetirada() {
        return dataHoraRetirada;
    }

    public void setDataHoraRetirada(LocalDateTime dataHoraRetirada) {
        this.dataHoraRetirada = dataHoraRetirada;
    }

    public LocalDateTime getDataHoraDevolucao() {
        return dataHoraDevolucao;
    }

    public void setDataHoraDevolucao(LocalDateTime dataHoraDevolucao) {
        this.dataHoraDevolucao = dataHoraDevolucao;
    }

    public Double getHorimetroInicial() {
        return horimetroInicial;
    }

    public void setHorimetroInicial(Double horimetroInicial) {
        this.horimetroInicial = horimetroInicial;
    }

    public Double getHorimetroFinal() {
        return horimetroFinal;
    }

    public void setHorimetroFinal(Double horimetroFinal) {
        this.horimetroFinal = horimetroFinal;
    }

    public Double getCombustivelInicial() {
        return combustivelInicial;
    }

    public void setCombustivelInicial(Double combustivelInicial) {
        this.combustivelInicial = combustivelInicial;
    }

    public Double getCombustivelFinal() {
        return combustivelFinal;
    }

    public void setCombustivelFinal(Double combustivelFinal) {
        this.combustivelFinal = combustivelFinal;
    }

    public String getLocalUso() {
        return localUso;
    }

    public void setLocalUso(String localUso) {
        this.localUso = localUso;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    
}
