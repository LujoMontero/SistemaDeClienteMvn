package org.example.servicio;

import org.example.modelo.Cliente;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class ExportadorTxt extends Exportador {

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName + ".txt"))) {
            for (Cliente cliente : listaClientes) {
                writer.println(cliente);
            }
            System.out.println("Datos exportados correctamente en formato TXT.");
        } catch (Exception e) {
            System.out.println("Error al exportar datos: " + e.getMessage());
        }
    }
}