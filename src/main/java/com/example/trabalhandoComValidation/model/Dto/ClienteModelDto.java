package com.example.trabalhandoComValidation.model.Dto;

import com.example.trabalhandoComValidation.model.ClienteModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteModelDto implements Serializable {

    private Long id;
    private String nomeCliente;
    private String emailCliente;
    private Long anoNascimento;

    public ClienteModelDto(ClienteModel obj) {
        this.id = obj.getId();
        this.nomeCliente = obj.getNomeCliente();
        this.emailCliente = obj.getEmailCliente();
        this.anoNascimento = obj.getAnoNascimento();
    }

}

