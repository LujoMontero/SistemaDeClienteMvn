package org.example.servicio;

import org.example.modelo.CategoriaEnum;
import org.example.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteServicio {
    private List<Cliente> listaClientes = new ArrayList<>();

    public void listarClientes() {
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente);
        }
    }

    public void agregarCliente(String runCliente, String nombreCliente, String apellidoCliente, int aniosCliente, CategoriaEnum nombreCategoria) {
        Cliente nuevoCliente = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, nombreCategoria);
        listaClientes.add(nuevoCliente);
    }

    public void editarCliente(String runCliente, String nombreCliente, String apellidoCliente, int aniosCliente, CategoriaEnum nombreCategoria) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getRunCliente().equals(runCliente)) {
                cliente.setNombreCliente(nombreCliente);
                cliente.setApellidoCliente(apellidoCliente);
                cliente.setAniosCliente(aniosCliente);
                cliente.setNombreCategoria(nombreCategoria);
                break;
            }
        }
    }

    public CategoriaEnum getEstadoCliente(String runCliente) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getRunCliente().equals(runCliente)) {
                return cliente.getNombreCategoria();
            }
        }
        return null; // Si el cliente no se encuentra, retornamos null o podríamos lanzar una excepción.
    }

    public void cambiarEstadoCliente(String runCliente, CategoriaEnum nuevoEstado) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getRunCliente().equals(runCliente)) {
                cliente.setNombreCategoria(nuevoEstado);
                break;
            }
        }
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public Cliente buscarClientePorRun(String runCliente) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getRunCliente().equals(runCliente)) {
                return cliente;
            }
        }
        return null; // Si el cliente no se encuentra, retornamos null o podríamos lanzar una excepción.
    }
}