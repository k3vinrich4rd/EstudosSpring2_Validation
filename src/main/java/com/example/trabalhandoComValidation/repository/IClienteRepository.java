package com.example.trabalhandoComValidation.repository;

import com.example.trabalhandoComValidation.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteModel, Long> {
    @Query("select c from ClienteModel c")
    public List<ClienteModel> getTodosDadosClientes();

    public List<ClienteModel> findByAnoNascimento(Long anoNascimento);

    public List<ClienteModel> findByEmailCliente(String emailCliente);
}
