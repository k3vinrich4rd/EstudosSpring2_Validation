package com.example.trabalhandoComValidation.repository;

import com.example.trabalhandoComValidation.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteModel, Long> {
}
