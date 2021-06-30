package com.tipiniquim.os.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cliente extends Pessoa implements Serializable {

    private static final long serialVersionUID = -5907463304962469748L;

    @OneToMany(mappedBy = "cliente")
    private List<OS> list = new ArrayList<>();

    public Cliente(Integer id, String nome, @CPF String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }
}
