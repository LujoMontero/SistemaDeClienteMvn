package org.example.servicio;

import org.example.modelo.Cliente;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class ExportadorTxt extends Exportador {
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName + ".txt"))) {
            for (Cliente cliente : listaClientes) {
                writer.println(cliente);
            }
            System.out.println(ANSI_WHITE + "Datos exportados correctamente en formato TXT.");
        } catch (Exception e) {
            System.out.println("Error al exportar datos: " + e.getMessage() + ANSI_RESET);
        }
    }
}