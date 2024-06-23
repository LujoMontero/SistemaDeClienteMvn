package org.example.servicio;

import org.example.modelo.CategoriaEnum;
import org.example.modelo.Cliente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class ArchivoServicio extends Exportador {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    private static final Scanner sc = new Scanner(System.in);

    public void cargarDatos(String filePath, ClienteServicio clienteServicio) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Cliente cliente = new Cliente(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]), CategoriaEnum.valueOf(datos[4].trim()));
                clienteServicio.agregarCliente(cliente.getRunCliente(), cliente.getNombreCliente(), cliente.getApellidoCliente(), cliente.getAniosCliente(),cliente.getNombreCategoria());
            }
            System.out.println(ANSI_RED + "Datos cargados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }

    public void exportarDatos(List<Cliente> listaClientes) {
        System.out.println("---------Exportar Datos-----------");
        int opcionFormato = seleccionarFormatoExportacion();
        if (opcionFormato == 0) {
            System.out.println("Operación de exportación cancelada.");
            return;
        }

        String formato = (opcionFormato == 1) ? "csv" : "txt";
        String rutaArchivo = obtenerRutaExportacion(formato);
        if (rutaArchivo == null) {
            System.out.println("Operación de exportación cancelada.");
            return;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {
            for (Cliente cliente : listaClientes) {
                pw.println(cliente.getRunCliente() + "," + cliente.getNombreCliente() + ","
                        + cliente.getApellidoCliente() + "," + cliente.getAniosCliente() + ","
                        + cliente.getNombreCategoria());
            }
            System.out.println("Datos de clientes exportados correctamente en formato " + formato + ".");
        } catch (IOException e) {
            System.out.println("Error al exportar datos a archivo " + rutaArchivo);
            e.printStackTrace();
        }
    }

    private String obtenerRutaArchivo() {
        String sistemaOperativo = System.getProperty("os.name").toLowerCase();
        String mensaje = sistemaOperativo.contains("win") ? "C:\\ruta\\archivo.csv" : "/home/usuario/DBClientes.csv";
        System.out.println(mensaje);
        String rutaArchivo = sc.nextLine();
        return rutaArchivo;
    }

    private int seleccionarFormatoExportacion() {
        System.out.println("Seleccione el formato a exportar:");
        System.out.println("1.- Formato csv");
        System.out.println("2.- Formato txt");
        System.out.print("Ingrese una opción para exportar: ");
        int opcion = sc.nextInt();
        sc.nextLine();
        return opcion;
    }

    private String obtenerRutaExportacion(String formato) {
        String sistemaOperativo = System.getProperty("os.name").toLowerCase();
        String mensaje = sistemaOperativo.contains("win") ? "C:\\ruta\\" + "clientes." + formato :
                "/home/usuario/" + "clientes." + formato + ANSI_RESET;
        System.out.println(mensaje);
        String rutaArchivo = sc.nextLine();
        return rutaArchivo;
    }

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {

    }
}