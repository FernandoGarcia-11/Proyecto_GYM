package com.gym.service;

import com.gym.model.Cliente;
import com.gym.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    public Cliente guardarCliente(Cliente Cliente) {
        return repository.save(Cliente);
    }

    public void eliminarCliente(Long id) {
        repository.deleteById(id);
    }
}
