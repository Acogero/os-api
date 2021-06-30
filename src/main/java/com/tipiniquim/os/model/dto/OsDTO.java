package com.tipiniquim.os.model.dto;

import com.tipiniquim.os.model.entity.OS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OsDTO implements Serializable {
    private static final long serialVersionUID = -3917492119281856312L;

    private Integer id;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private Integer prioridade;

    @NotEmpty(message = "O campo OBSERVAÇÕES é requerido")
    private String observacoes;
    private Integer status;

//    @NotEmpty(message = "O campo TÉCNICO é requerido")
    private Integer tecnico;

//    @NotEmpty(message = "O campo CLIENTE é requerido")
    private Integer cliente;

    public OsDTO(OS obj) {
        super();

        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade();
        this.observacoes = obj.getObservacoes();
        this.status = obj.getStatus();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
    }
}
