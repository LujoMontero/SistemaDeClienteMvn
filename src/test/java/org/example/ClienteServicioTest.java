package org.example.test;

import org.example.modelo.CategoriaEnum;
import org.example.servicio.ClienteServicio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClienteServicioTest {

    @Test
    public void agregarClienteTest() {
        ClienteServicio clienteServicio = new ClienteServicio();
        clienteServicio.agregarCliente("12345678-9", "Juan", "Perez", 5, CategoriaEnum.ACTIVO);

        assertEquals(1, clienteServicio.getListaClientes().size());
        assertEquals("Juan", clienteServicio.getListaClientes().get(0).getNombreCliente());
    }

    @Test
    public void agregarClienteNullTest() {
        ClienteServicio clienteServicio = new ClienteServicio();
        clienteServicio.agregarCliente(null, null, null, 0, null);

        assertEquals(1, clienteServicio.getListaClientes().size());
        assertNull(clienteServicio.getListaClientes().get(0).getRunCliente());
    }
}