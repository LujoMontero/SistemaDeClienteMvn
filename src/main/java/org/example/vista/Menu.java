package org.example.vista;

import org.example.modelo.CategoriaEnum;
import org.example.servicio.ClienteServicio;
import org.example.servicio.ArchivoServicio;
import org.example.servicio.ExportadorCsv;
import org.example.servicio.ExportadorTxt;

import java.util.Scanner;

public class Menu {
    private ClienteServicio clienteServicio = new ClienteServicio();
    private ArchivoServicio archivoServicio = new ArchivoServicio();
    private ExportadorCsv exportadorCsv = new ExportadorCsv();
    private ExportadorTxt exportarTxt = new ExportadorTxt();
    private String fileName = "Clientes";
    private String fileName1 = "DBClientes.csv";
    private Scanner sc = new Scanner(System.in);

    public void iniciarMenu() {
        while (true) {
            System.out.println("1. Listar Clientes");
            System.out.println("2. Agregar Cliente");
            System.out.println("3. Editar Cliente");
            System.out.println("4. Cargar Datos");
            System.out.println("5. Exportar Datos");
            System.out.println("6. Terminar Programa");


            System.out.println("Ingrese una Opcion");
            String input = sc.nextLine();

            int opcion = -1;

            try {
                opcion = Integer.parseInt(input);
                if (opcion < 1 || opcion > 6) {
                    System.out.println("Opcion no valida!");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida, debe ingresar un número del 1 al 6. Intente nuevamente.");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            }

            switch (opcion) {
                case 1:
                    listarClientes();
                    break;
                case 2:
                    agregarCliente();
                    break;
                case 3:
                    editarCliente();
                    break;
                case 4:
                    importarDatos();
                    break;
                case 5:
                    exportarDatos();
                    break;
                case 6:
                    terminarPrograma();
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private void listarClientes() {
        clienteServicio.listarClientes();
    }

    private void agregarCliente() {
        System.out.print("Ingrese RUN del Cliente: ");
        String run = sc.nextLine();
        System.out.print("Ingrese nombre del Cliente: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese apellido del Cliente: ");
        String apellido = sc.nextLine();
        System.out.print("Ingrese años del Cliente: ");
        int anios = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese categoría del Cliente (Activo/Inactivo): ");
        String categoria = sc.nextLine();
        CategoriaEnum nombreCategoria = CategoriaEnum.valueOf(categoria);

        clienteServicio.agregarCliente(run, nombre, apellido, anios, nombreCategoria);
    }

    private void editarCliente() {
        System.out.print("Ingrese RUN del Cliente a editar: ");
        String run = sc.nextLine();
        System.out.print("Ingrese nuevo nombre del Cliente: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese nuevo apellido del Cliente: ");
        String apellido = sc.nextLine();
        System.out.print("Ingrese nuevos años del Cliente: ");
        int anios = sc.nextInt();
        sc.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese nueva categoría del Cliente (Activo/Inactivo): ");
        String categoria = sc.nextLine();
        CategoriaEnum nombreCategoria = CategoriaEnum.valueOf(categoria);

        clienteServicio.editarCliente(run, nombre, apellido, anios, nombreCategoria);
    }

    private void importarDatos() {
        archivoServicio.cargarDatos(fileName1, clienteServicio);
    }

    private void exportarDatos() {
        System.out.print("Seleccione formato de exportación (csv/txt): ");
        String formato = sc.nextLine();
        if ("csv".equalsIgnoreCase(formato)) {
            exportadorCsv.exportar(fileName, clienteServicio.getListaClientes());
        } else if ("txt".equalsIgnoreCase(formato)) {
            exportarTxt.exportar(fileName, clienteServicio.getListaClientes());
        } else {
            System.out.println("Formato no reconocido. Intente nuevamente.");
        }
    }

    private void terminarPrograma() {
        System.out.println("Programa finalizado.");
    }
}