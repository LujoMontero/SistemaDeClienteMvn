package org.example.servicio;

import org.example.modelo.Cliente;
import org.example.modelo.CategoriaEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

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

    // Método para editar cliente
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
}