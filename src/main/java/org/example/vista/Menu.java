package org.example.vista;

import org.example.modelo.CategoriaEnum;
import org.example.modelo.Cliente;
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

            System.out.print("Ingrese una Opción: ");
            String input = sc.nextLine();

            int opcion = -1;

            try {
                opcion = Integer.parseInt(input);
                if (opcion < 1 || opcion > 6) {
                    System.out.println("Opción no válida!");
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
            }
        }
    }

    private void listarClientes() {
        clienteServicio.listarClientes();
    }

    private void agregarCliente() {
        System.out.println("------------ Crear Cliente -------------");
        System.out.print("Ingrese RUN del Cliente: ");
        String run = sc.nextLine();
        System.out.print("Ingrese Nombre del Cliente: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese Apellido del Cliente: ");
        String apellido = sc.nextLine();
        System.out.print("Ingrese años como Cliente: ");
        int anios = sc.nextInt();
        sc.nextLine();
        System.out.println("----------------------------------------");

        clienteServicio.agregarCliente(run, nombre, apellido, anios, CategoriaEnum.ACTIVO);
        System.out.println("Cliente agregado correctamente con categoría Activo.");
    }

    private void editarCliente() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("-------------Editar Cliente-------------");
            System.out.println("Seleccione qué desea hacer:");
            System.out.println("1.- Cambiar el estado del Cliente");
            System.out.println("2.- Editar los datos ingresados del Cliente");
            System.out.println("3.- Volver al menú principal");
            System.out.print("Ingrese opción: ");
            String opcionStr = sc.nextLine();

            int opcion = -1;

            try {
                opcion = Integer.parseInt(opcionStr);
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Opción no válida!");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida, debe ingresar un número del 1 al 3. Intente nuevamente.");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                continue;
            }

            switch (opcion) {
                case 1:
                    cambiarEstadoCliente();
                    break;
                case 2:
                    editarDatosCliente();
                    break;
                case 3:
                    continuar = false;
                    break;
            }
        }
    }

    private void cambiarEstadoCliente() {
        System.out.print("Ingrese RUN del Cliente a editar: ");
        String runEstado = sc.nextLine();
        CategoriaEnum estadoActual = clienteServicio.getEstadoCliente(runEstado);
        if (estadoActual == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println("-----Actualizando estado del Cliente----");
        System.out.println("El estado actual es: " + estadoActual);
        System.out.println("1.- Si desea cambiar el estado del Cliente a " + (estadoActual == CategoriaEnum.ACTIVO ? "INACTIVO" : "ACTIVO"));
        System.out.println("2.- Mantener el estado del cliente " + estadoActual);
        System.out.print("Ingrese opción: ");
        String estadoOpcionStr = sc.nextLine();
        int estadoOpcion = Integer.parseInt(estadoOpcionStr);
        if (estadoOpcion == 1) {
            CategoriaEnum nuevoEstado = (estadoActual == CategoriaEnum.ACTIVO) ? CategoriaEnum.INACTIVO : CategoriaEnum.ACTIVO;
            clienteServicio.cambiarEstadoCliente(runEstado, nuevoEstado);
            System.out.println("Estado del cliente actualizado correctamente a " + nuevoEstado + ".");
        } else if (estadoOpcion == 2) {
            System.out.println("El estado del cliente se mantuvo como " + estadoActual + ".");
        } else {
            System.out.println("Opción no válida!");
        }
    }

    private void editarDatosCliente() {
        System.out.print("Ingrese RUN del Cliente a editar: ");
        String run = sc.nextLine();
        Cliente cliente = clienteServicio.buscarClientePorRun(run);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        boolean editarDatos = true;
        while (editarDatos) {
            System.out.println("----Actualizando datos del Cliente-----");
            System.out.println("1.- El RUN del Cliente es: " + cliente.getRunCliente());
            System.out.println("2.- El Nombre del Cliente es: " + cliente.getNombreCliente());
            System.out.println("3.- El Apellido del Cliente es: " + cliente.getApellidoCliente());
            System.out.println("4.- Los años como Cliente son: " + cliente.getAniosCliente() + " años");
            System.out.println("5.- Volver al menú anterior");
            System.out.print("Ingrese opción a editar de los datos del cliente: ");
            String datoOpcionStr = sc.nextLine();
            int datoOpcion = Integer.parseInt(datoOpcionStr);

            switch (datoOpcion) {
                case 1:
                    System.out.print("Ingrese nuevo RUN del Cliente: ");
                    String nuevoRun = sc.nextLine();
                    cliente.setRunCliente(nuevoRun);
                    break;
                case 2:
                    System.out.print("Ingrese nuevo nombre del Cliente: ");
                    String nuevoNombre = sc.nextLine();
                    cliente.setNombreCliente(nuevoNombre);
                    break;
                case 3:
                    System.out.print("Ingrese nuevo apellido del Cliente: ");
                    String nuevoApellido = sc.nextLine();
                    cliente.setApellidoCliente(nuevoApellido);
                    break;
                case 4:
                    System.out.print("Ingrese nuevos años del Cliente: ");
                    int nuevosAnios = sc.nextInt();
                    sc.nextLine(); // Consumir el salto de línea
                    cliente.setAniosCliente(nuevosAnios);
                    break;
                case 5:
                    editarDatos = false;
                    break;
                default:
                    System.out.println("Opción no válida!");
                    break;
            }
        }
        System.out.println("Datos del cliente actualizados correctamente.");
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
        sc.close();
    }
}