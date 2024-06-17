package org.example.servicio;

import org.example.modelo.Cliente;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class ExportadorCsv extends Exportador {

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName + ".csv"))) {
            for (Cliente cliente : listaClientes) {
                writer.println(cliente.getRunCliente() + "," + cliente.getNombreCliente() + "," + cliente.getApellidoCliente() + "," + cliente.getAniosCliente() + "," + cliente.getNombreCategoria());
            }
            System.out.println("Datos exportados correctamente en formato CSV.");
        } catch (Exception e) {
            System.out.println("Error al exportar datos: " + e.getMessage());
        }
    }
}