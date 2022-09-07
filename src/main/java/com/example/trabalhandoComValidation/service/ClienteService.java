package com.example.trabalhandoComValidation.service;

import com.example.trabalhandoComValidation.model.ClienteModel;
import com.example.trabalhandoComValidation.model.Dto.ClienteModelDto;
import com.example.trabalhandoComValidation.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {


    @Autowired
    private IClienteRepository iClienteRepository;

    public ClienteModel cadastrarCliente(ClienteModel clienteModel) {
        return iClienteRepository.save(clienteModel);
    }

    public List<ClienteModel> exibirClientesCadastrados() {
        return iClienteRepository.findAll();
    }

    public Optional<ClienteModel> exibirClienteViaId(Long id) {
        return iClienteRepository.findById(id);
    }

    public ClienteModel alterarClienteCadastrado(ClienteModel clienteModel) {
        return iClienteRepository.save(clienteModel);
    }

    public void deletarCliente(Long id) {
        iClienteRepository.deleteById(id);
    }

    //Query
    public List<ClienteModel> getTodosDadosClientes(){
        return iClienteRepository.getTodosDadosClientes();
    }
}
