package com.tipiniquim.os.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tipiniquim.os.model.entity.OS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TecnicoDTO implements Serializable {

    private static final long serialVersionUID = 1769380424610269653L;

    private Integer id;

    @NotEmpty(message = "O campo NOME é requerido")
    private String nome;

    @CPF
    @NotEmpty(message = "O campo CPF é requerido")
    private String cpf;

    @NotEmpty(message = "O campo TELEFONE é requerido")
    private String telefone;

    @JsonIgnore
    private List<OS> list = new ArrayList<>();
}
