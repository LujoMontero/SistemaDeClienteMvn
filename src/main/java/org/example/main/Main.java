package org.example.main;

import org.example.modelo.CategoriaEnum;
import org.example.servicio.ClienteServicio;

public class Main {
    public static void main(String[] args) {

        ClienteServicio clienteServicio = new ClienteServicio();

        // Agregar clientes
        clienteServicio.agregarCliente("12345678-9", "Juan", "Perez", 5, CategoriaEnum.Activo);
        clienteServicio.agregarCliente("98765432-1", "Ana", "Gomez", 3, CategoriaEnum.Activo);

        // Listar clientes
        System.out.println("Lista de clientes:");
        clienteServicio.listarClientes();

        // Editar un cliente
        clienteServicio.editarCliente("12345678-9", "Juanito", "Perez", 6, CategoriaEnum.Activo);

        // Listar clientes después de la edición
        System.out.println("Lista de clientes después de la edición:");
        clienteServicio.listarClientes();
    }
}
