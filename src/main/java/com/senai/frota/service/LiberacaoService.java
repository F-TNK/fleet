/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.service;

import com.senai.frota.model.EquipDTO;
import com.senai.frota.model.LiberacaoDTO;
import com.senai.frota.repository.EquipDAO;
import com.senai.frota.repository.LiberacaoDAO;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Micro
 */
@Service
public class LiberacaoService {
    
    @Autowired
    private LiberacaoDAO ldao;
    
    @Autowired
    private EquipDAO edao;

    // ------------------------- ADMIN ------------------------

    public void register(LiberacaoDTO l) {
        String message = "";

        if (l.getIdOperador() == null) {
            message = "Operador não informado.";
        } else if (l.getIdEquip() == null) {
            message = "Equipamento não informado.";
        } else if (l.getDataHoraRetirada() == null) {
            message = "Data e hora de retirada não informadas.";
        } else if (l.getDataHoraDevolucao() == null) {
            message = "Data e hora de devolução não informadas.";
        } else if (l.getLocalUso() == null || l.getLocalUso().trim().isEmpty()) {
            message = "Local de uso não informado.";
        }

        if (message.isEmpty()) {
            LocalDateTime agora = LocalDateTime.now();
            // puxa os dados do equip pro formulario
            EquipDTO equip = edao.findById(l.getIdEquip());
            
            // regras de negocio para logica de horarios
            if (l.getDataHoraRetirada().isBefore(agora)) {
                message = "A data/hora de retirada não pode ser registrada no passado.";
            } else if (l.getDataHoraDevolucao().isBefore(agora)) {
                message = "A data/hora de devolução não pode ser no passado.";
            } else if (l.getDataHoraDevolucao().isBefore(l.getDataHoraRetirada())) {
                message = "A data/hora de devolução não pode ser anterior à data/hora de retirada.";
            } else if (ldao.conflitoHorario(l.getIdEquip(), l.getDataHoraRetirada(), l.getDataHoraDevolucao(), null)) {
                message = "O equipamento já possui um agendamento conflitante para este período.";
            } else if (equip.porcentUso() >= 90) {
                message = "Operação bloqueada: A máquina ultrapassou 90% da sua vida útil";
            }
        }

        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }

        ldao.register(l);
    }

    public void editLiberacao(LiberacaoDTO l) {
        String message = "";

        if (l.getIdOperador() == null) {
            message = "Operador não informado.";
        } else if (l.getIdEquip() == null) {
            message = "Equipamento não informado.";
        } else if (l.getDataHoraRetirada() == null) {
            message = "Data e hora de retirada não informadas";
        } else if (l.getDataHoraDevolucao() == null) {
            message = "Data e hora de devolução não informadas";
        } else if (l.getLocalUso() == null || l.getLocalUso().trim().isEmpty()) {
            message = "Local de uso não informado";
        }else if (l.getDataHoraDevolucao().isBefore(l.getDataHoraRetirada())) {
            message = "A data/hora de devolução não pode ser anterior à data/hora de retirada";
        }
        
        if (message.isEmpty()) {
            EquipDTO equip = edao.findById(l.getIdEquip());
            
            if (ldao.conflitoHorario(l.getIdEquip(), l.getDataHoraRetirada(), l.getDataHoraDevolucao(), l.getId())) {
                message = "O equipamento já possui outro agendamento conflitante para este período.";
            } else if (equip.porcentUso() >= 90) {
                message = "Operação bloqueada: A máquina ultrapassou 90% da sua vida útil";
            }
        }
        
        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }

        ldao.editLiberacao(l);
    }

    public int deleteLiberacao(Long id) {
        return ldao.deleteLiberacao(id);
    }

    // ----------------------- OPERADOR -----------------------

    public void pickUp(LiberacaoDTO l) {
        String message = "";
        LocalDateTime agora = LocalDateTime.now();
        
        if (l.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID da liberação não encontrado.");
        }
        if (l.getIdEquip() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID do equipamento não encontrado.");
        }
        
        // puxa os dados do equip pro formulario
        EquipDTO equip = edao.findById(l.getIdEquip());
        if (equip == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado.");
        }
        
        LiberacaoDTO lib = ldao.findById(l.getId());
        if (lib == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Liberação não encontrada.");
        }
        
        
        if (equip.getStatus().equals("Em Uso") || equip.getStatus().equals("Em Manutenção")) {
            message = "Operação bloqueada: O equipamento está indisponível no momento";
        } else if (equip.getNivelCombustivel() < 25) {
            message = "Operação bloqueada: O nível de combustível do equipamento esá muito baixo";
        } else if (equip.porcentUso() >= 90) {
            message = "Operação bloqueada: A máquina ultrapassou 90% da sua vida útil";
        }

        if (message.isEmpty()) {
            // convertendo atributos de um DTO pro outro
            l.setHorimetroInicial(equip.getHorasUso());
            l.setCombustivelInicial(equip.getNivelCombustivel());
            // nao pode ser retirado antes do horario
            if (agora.isBefore(lib.getDataHoraRetirada())) {
                message = "O equipamento não pode ser retirado antes da data e hora agendadas";
            }
        }
        
        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
        
        // timestamp automatico da retirada 
        l.setDataHoraRetiradaReal(agora);
        // updt de status automatico da retirada 
        equip.setStatus("Em Uso");
        edao.editEquip(equip);

        ldao.pickUp(l);
    }

    public void close(LiberacaoDTO l) {
        String message = "";
        LocalDateTime agora = LocalDateTime.now();
        
        // registro automático - data/hora devolucao
        l.setDataHoraDevolucaoReal(agora);
        
        if (l.getHorimetroFinal() == null) {
            message = "Horímetro final não preenchido";
        } else if (l.getCombustivelFinal() == null) {
            message = "Combustível final não preenchido";
        } 

        if (message.isEmpty()) {
            if (l.getHorimetroFinal() < l.getHorimetroInicial()) {
                message = "O horímetro final não pode ser menor do que o horímetro inicial";
            } 
        }

        // Reportar Problema - observcao obrigatoria
        if (l.isAlerta()) {
            if (l.getObservacoesDevolucao() == null || l.getObservacoesDevolucao().trim().isEmpty()) {
                message = "Ao reportar um problema, as observações de devolução tornam-se obrigatórias";
            }
        }

        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }

        ldao.close(l);
        
        // mandando dados finais automatico num objeto equip
        // Reportar Problema --> setStatus
        // findById --> editEquip (update)
        EquipDTO equip = edao.findById(l.getIdEquip());
        
        if (equip != null) {
            if (l.isAlerta()) {
                equip.setStatus("Em Manutenção"); 
            } else {
                equip.setStatus("Disponível"); 
            }

            equip.setHorasUso(l.getHorimetroFinal());
            equip.setNivelCombustivel(l.getCombustivelFinal());
            edao.editEquip(equip);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado.");
        }
        
    }

    
    // ----------------------- LISTAS -----------------------

    public List<LiberacaoDTO> listAlerta() {
        return ldao.listAlerta();
    }

    public List<LiberacaoDTO> listOpen() {
        return ldao.listOpen();
    }

    public List<LiberacaoDTO> listClose() {
        return ldao.listClose();
    }

    public List<LiberacaoDTO> listById(Long idUser) {
        return ldao.listById(idUser);
        
    }
    
    public List<LiberacaoDTO> listByOp(Long idUser) {
        return ldao.listByOp(idUser);
    }

    public List<LiberacaoDTO> listByEquip(Long idEquip) {
        return ldao.listByEquip(idEquip);
    }

    public void resolve(Long id) {
        ldao.resolve(id);
    }
    
}
