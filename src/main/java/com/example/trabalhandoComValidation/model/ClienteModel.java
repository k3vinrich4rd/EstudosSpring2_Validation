package com.example.trabalhandoComValidation.model;

import com.example.trabalhandoComValidation.validationcontrains.PlacaCarro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes_validação")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_cliente", length = 50, nullable = false)
    @NotBlank(message = "Erro, nome do cliente não informado") //Não aceita valores em brancos e nulos
    @Pattern(regexp = "^[A-Z]+(.)*", message = "Erro, o campo nome deve iniciar com uma letra maiúscula")
    //Garante que a primeira letra do nome do meu cliente seja maiúscula
    private String nomeCliente;

    @Column(name = "email_client", length = 50, nullable = false)
    @Email(message = "Erro, email inválido")
    @NotBlank(message = "Erro, e-mail não informado")
    private String emailCliente;

    @Column(name = "cpf_cliente", length = 14, nullable = false)
    @CPF(message = "Cpf inválido")
    @NotBlank(message = "Erro, cpf não informado")
    private String cpfCliente;

    @Column(name = "placa_de_carro_cliente", length = 50, nullable = false)
    @PlacaCarro(message = "Placa inválida")
    @NotBlank(message = "Erro, placa do carro informada")
    private String placaCarro;

    @Column(name = "ano_nascimento_cliente", length = 4, nullable = false)
    @Min(value = 1900, message = "O ano de nascimento deve ser superior a 1899")
    // Garante que a data nascimento do meu cliente tem que ser superior a 1899
    private Long anoNascimento;
}
