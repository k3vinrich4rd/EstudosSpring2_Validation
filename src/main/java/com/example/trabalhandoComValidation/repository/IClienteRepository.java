package com.example.trabalhandoComValidation.repository;

import com.example.trabalhandoComValidation.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteModel, Long> {
    //Query (anotação)
    @Query("select c from ClienteModel c")
    public List<ClienteModel> getTodosDadosClientes();

    //Query methods:
    public List<ClienteModel> findByAnoNascimento(Long anoNascimento);

    public List<ClienteModel> findByEmailCliente(String emailCliente);

    public boolean existsByCpfCliente(String cpfCliente);

    public boolean existsByEmailCliente(String emailCliente);

    public boolean existsByPlacaCarro(String placaCarro);
}
