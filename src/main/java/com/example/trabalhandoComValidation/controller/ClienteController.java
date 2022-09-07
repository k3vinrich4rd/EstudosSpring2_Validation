package com.example.trabalhandoComValidation.controller;

import com.example.trabalhandoComValidation.model.ClienteModel;
import com.example.trabalhandoComValidation.model.Dto.ClienteModelDto;
import com.example.trabalhandoComValidation.repository.IClienteRepository;
import com.example.trabalhandoComValidation.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private IClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<ClienteModel> cadastrarCliente(@RequestBody @Valid ClienteModel clienteModel) {
        ClienteModel cliente = clienteService.cadastrarCliente(clienteModel);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED); // Retorna 201
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

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClienteModel> alterarClienteViaId(@RequestBody ClienteModel clienteModel) {
        return ResponseEntity.ok(clienteService.alterarClienteCadastrado(clienteModel));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Retorna o 204
    public ResponseEntity deletar(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
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

    //Tratamento de mensagens de erro:
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

}
