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
    private String nomeOp;
    private Long idEquip;
    private String nomeEquip;
    
    private LocalDateTime dataHoraRetirada;
    private LocalDateTime dataHoraDevolucao;
    private LocalDateTime dataHoraRetiradaReal;
    private LocalDateTime dataHoraDevolucaoReal;
    private Double horimetroInicial;
    private Double horimetroFinal;
    private Double combustivelInicial;
    private Double combustivelFinal;
    private String localUso;
    private String observacoesRetirada;
    private String observacoesDevolucao;
    private boolean alerta;

    public LiberacaoDTO() {
    }

    public LiberacaoDTO(Long id, Long idOperador, String nomeOp, Long idEquip, String nomeEquip, LocalDateTime dataHoraRetirada, LocalDateTime dataHoraDevolucao, LocalDateTime dataHoraRetiradaReal, LocalDateTime dataHoraDevolucaoReal, Double horimetroInicial, Double horimetroFinal, Double combustivelInicial, Double combustivelFinal, String localUso, String observacoesRetirada, String observacoesDevolucao, boolean alerta) {
        this.id = id;
        this.idOperador = idOperador;
        this.nomeOp = nomeOp;
        this.idEquip = idEquip;
        this.nomeEquip = nomeEquip;
        this.dataHoraRetirada = dataHoraRetirada;
        this.dataHoraDevolucao = dataHoraDevolucao;
        this.dataHoraRetiradaReal = dataHoraRetiradaReal;
        this.dataHoraDevolucaoReal = dataHoraDevolucaoReal;
        this.horimetroInicial = horimetroInicial;
        this.horimetroFinal = horimetroFinal;
        this.combustivelInicial = combustivelInicial;
        this.combustivelFinal = combustivelFinal;
        this.localUso = localUso;
        this.observacoesRetirada = observacoesRetirada;
        this.observacoesDevolucao = observacoesDevolucao;
        this.alerta = alerta;
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

    public String getNomeOp() {
        return nomeOp;
    }

    public void setNomeOp(String nomeOp) {
        this.nomeOp = nomeOp;
    }

    public Long getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(Long idEquip) {
        this.idEquip = idEquip;
    }

    public String getNomeEquip() {
        return nomeEquip;
    }

    public void setNomeEquip(String nomeEquip) {
        this.nomeEquip = nomeEquip;
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

    public LocalDateTime getDataHoraRetiradaReal() {
        return dataHoraRetiradaReal;
    }

    public void setDataHoraRetiradaReal(LocalDateTime dataHoraRetiradaReal) {
        this.dataHoraRetiradaReal = dataHoraRetiradaReal;
    }

    public LocalDateTime getDataHoraDevolucaoReal() {
        return dataHoraDevolucaoReal;
    }

    public void setDataHoraDevolucaoReal(LocalDateTime dataHoraDevolucaoReal) {
        this.dataHoraDevolucaoReal = dataHoraDevolucaoReal;
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

    public String getObservacoesRetirada() {
        return observacoesRetirada;
    }

    public void setObservacoesRetirada(String observacoesRetirada) {
        this.observacoesRetirada = observacoesRetirada;
    }

    public String getObservacoesDevolucao() {
        return observacoesDevolucao;
    }

    public void setObservacoesDevolucao(String observacoesDevolucao) {
        this.observacoesDevolucao = observacoesDevolucao;
    }

    public boolean isAlerta() {
        return alerta;
    }

    public void setAlerta(boolean alerta) {
        this.alerta = alerta;
    }
}
