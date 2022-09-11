package com.example.trabalhandoComValidation.controller;

import com.example.trabalhandoComValidation.model.ClienteModel;
import com.example.trabalhandoComValidation.model.Dto.ClienteModelDto;
import com.example.trabalhandoComValidation.repository.IClienteRepository;
import com.example.trabalhandoComValidation.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Validated // Avisa que as validações que estão dentro dessa classe
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private IClienteRepository iClienteRepository;


    //Validação para cadastrar
    @PostMapping
    public ResponseEntity<Object> cadastrarCliente(@Valid @RequestBody ClienteModel clienteModel) {
        if (clienteService.existsByCpfCliente(clienteModel.getCpfCliente())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Cpf já cadastrado");
        } else if (clienteService.existsByEmailCliente(clienteModel.getEmailCliente())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: E-mail já cadastrado");
        } else if (clienteService.existsByPlacaDeCarro(clienteModel.getPlacaCarro())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Placa de carro já cadastrada");

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.cadastrarCliente(clienteModel));
    }

    //Dto:
    @GetMapping
    public ResponseEntity<List<ClienteModelDto>> exibirClientesCadastrados() {
        List<ClienteModel> list = clienteService.exibirClientesCadastrados();
        List<ClienteModelDto> listClienteDto = list.stream().map(obj -> new ClienteModelDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listClienteDto);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<ClienteModel>> exibirClienteViaId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.exibirClienteViaId(id));
    }

    //Query Method (Método de consulta)
    @GetMapping(path = "/anoNascimento/{anoNascimento}")
    public ResponseEntity<List<ClienteModel>> exibirDataDeNascimentoCliente(@PathVariable Long anoNascimento) {
        return ResponseEntity.ok(iClienteRepository.findByAnoNascimento(anoNascimento));
    }

    //Query Method (Método de consulta)
    @GetMapping(path = "/emailCliente/{emailCliente}")
    public ResponseEntity<List<ClienteModel>> exibirEmailCliente(@PathVariable String emailCliente) {
        return ResponseEntity.ok(iClienteRepository.findByEmailCliente(emailCliente));
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<ClienteModel> alterarClienteViaId(@Valid @RequestBody ClienteModel clienteModel) {
        return ResponseEntity.ok(clienteService.alterarClienteCadastrado(clienteModel));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Retorna o 204
    public ResponseEntity deletar(@PathVariable Long id) {
        if (!iClienteRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: id não encontrado");

        }
        clienteService.deletarCliente(id);
        return null;
    }

    //Query
    @GetMapping(path = "/find-all")
    public ResponseEntity<List<ClienteModel>> exibirTodosDadosDosClientes() {
        return ResponseEntity.ok(clienteService.getTodosDadosClientes());
    }



}
