package com.devU.devU.service;

import com.devU.devU.entities.Cliente;
import com.devU.devU.exceptons.ResourceNotFoundException;
import com.devU.devU.repositories.IClienteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final IClienteRepo clienteRepository;


    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }


    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
    }


    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    public void eliminarCliente(Long id) {
        Cliente cliente = obtenerClientePorId(id);
        clienteRepository.delete(cliente);
    }

}
