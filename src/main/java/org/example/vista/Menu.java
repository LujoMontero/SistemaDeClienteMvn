package org.example.vista;

import org.example.modelo.CategoriaEnum;
import org.example.modelo.Cliente;
import org.example.servicio.ClienteServicio;
import org.example.servicio.ArchivoServicio;
import org.example.servicio.ExportadorCsv;
import org.example.servicio.ExportadorTxt;

import java.util.Scanner;

public class Menu {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private ClienteServicio clienteServicio = new ClienteServicio();
    private ArchivoServicio archivoServicio = new ArchivoServicio();
    private ExportadorCsv exportadorCsv = new ExportadorCsv();
    private ExportadorTxt exportadorTxt = new ExportadorTxt();
    private String fileName = "Clientes";
    private String fileName1 = "DBClientes.csv";
    private Scanner sc = new Scanner(System.in);

    public void iniciarMenu() {
        while (true) {
            System.out.println(ANSI_YELLOW+ "1. Listar Clientes");
            System.out.println("2. Agregar Cliente"+ ANSI_RESET);
            System.out.println(ANSI_BLUE + "3. Editar Cliente");
            System.out.println("4. Cargar Datos" + ANSI_RESET);
            System.out.println(ANSI_RED + "5. Exportar Datos");
            System.out.println("6. Terminar Programa" + ANSI_RESET);

            System.out.print(ANSI_WHITE + "Ingrese una Opción: " + ANSI_RESET);
            String input = sc.nextLine();

            int opcion = -1;

            try {
                opcion = Integer.parseInt(input);
                if (opcion < 1 || opcion > 6) {
                    System.out.println(ANSI_CYAN + "Opción no válida!");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida, debe ingresar un número del 1 al 6. Intente nuevamente.");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" + ANSI_RESET);
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
                    cargarDatos();
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
        System.out.println(ANSI_PURPLE + "------------ Crear Cliente -------------");
        System.out.print("Ingrese RUN del Cliente: ");
        String run = sc.nextLine();
        System.out.print("Ingrese Nombre del Cliente: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese Apellido del Cliente: ");
        String apellido = sc.nextLine();
        System.out.print("Ingrese años como Cliente: ");
        int anios = sc.nextInt();
        sc.nextLine();

        CategoriaEnum categoria = CategoriaEnum.ACTIVO;

        clienteServicio.agregarCliente(run, nombre, apellido, anios, categoria);
        System.out.println("Cliente agregado correctamente con categoría Activo." + ANSI_RESET);
    }

    private void editarCliente() {
        boolean continuar = true;
        while (continuar) {
            System.out.println(ANSI_GREEN + "-------------Editar Cliente-------------");
            System.out.println("Seleccione qué desea hacer:");
            System.out.println("1.- Cambiar el estado del Cliente");
            System.out.println("2.- Editar los datos ingresados del Cliente");
            System.out.println("3.- Volver al menú principal");
            System.out.print("Ingrese opción: " + ANSI_RESET);
            String opcionStr = sc.nextLine();

            int opcion = -1;

            try {
                opcion = Integer.parseInt(opcionStr);
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Opción no válida!");
                    System.out.println(ANSI_CYAN + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida, debe ingresar un número del 1 al 3. Intente nuevamente.");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" + ANSI_RESET);
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print(ANSI_RED + "Ingrese RUN del Cliente a editar: ");
                    String runEstado = sc.nextLine();
                    CategoriaEnum estadoActual = clienteServicio.getEstadoCliente(runEstado);
                    if (estadoActual == null) {
                        System.out.println("Cliente no encontrado.");
                        break;
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
                    break;
                case 2:
                    System.out.print("Ingrese RUN del Cliente a editar: ");
                    String run = sc.nextLine();
                    Cliente cliente = clienteServicio.buscarClientePorRun(run);
                    if (cliente == null) {
                        System.out.println("Cliente no encontrado." + ANSI_RESET);
                        break;
                    }

                    boolean editarDatos = true;
                    while (editarDatos) {
                        System.out.println(ANSI_GREEN + "----Actualizando datos del Cliente-----");
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
                                cliente.setRunCliente(sc.nextLine());
                                break;
                            case 2:
                                System.out.print("Ingrese nuevo nombre del Cliente: ");
                                cliente.setNombreCliente(sc.nextLine());
                                break;
                            case 3:
                                System.out.print("Ingrese nuevo apellido del Cliente: ");
                                cliente.setApellidoCliente(sc.nextLine());
                                break;
                            case 4:
                                System.out.print("Ingrese nuevos años del Cliente: ");
                                cliente.setAniosCliente(sc.nextInt());
                                sc.nextLine(); // Consumir el salto de línea
                                break;
                            case 5:
                                editarDatos = false;
                                break;
                            default:
                                System.out.println("Opción no válida!");
                                break;
                        }
                    }
                    System.out.println("Datos del cliente actualizados correctamente." + ANSI_RESET);
                    break;
                case 3:
                    continuar = false;
                    break;
            }
        }
    }

    private void cargarDatos() {
        System.out.println(ANSI_PURPLE + "---------Cargar Datos-----------");
        System.out.println("Ingrese la ruta donde se encuentra el archivo " + fileName1 + ":");
        String ruta = sc.nextLine();

        archivoServicio.cargarDatos(ruta + "\\" + fileName1, clienteServicio);
    }

    private void exportarDatos() {
        System.out.println("---------Exportar Datos-----------");
        System.out.println("Seleccione el formato a exportar:");
        System.out.println("1. Formato CSV");
        System.out.println("2. Formato TXT");
        System.out.print("Ingrese una opción para exportar: " + ANSI_RESET);
        String opcionStr = sc.nextLine();

        int opcion = -1;

        try {
            opcion = Integer.parseInt(opcionStr);
            if (opcion < 1 || opcion > 2) {
                System.out.println(ANSI_CYAN + "Opción no válida!");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Opción no válida, debe ingresar un número del 1 al 2. Intente nuevamente.");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" + ANSI_RESET);
            return;
        }

        switch (opcion) {
            case 1:
                System.out.println(ANSI_BLUE + "Ingrese la ruta donde desea exportar el archivo " + fileName + ".csv:");
                String rutaCsv = sc.nextLine();
                exportadorCsv.exportar(rutaCsv + "\\" + fileName, clienteServicio.getListaClientes());
                System.out.println("Datos de clientes exportados correctamente en formato CSV.");
                break;
            case 2:
                System.out.println("Ingrese la ruta donde desea exportar el archivo " + fileName + ".txt:");
                String rutaTxt = sc.nextLine();
                exportadorTxt.exportar(rutaTxt + "\\" + fileName, clienteServicio.getListaClientes());
                System.out.println("Datos de clientes exportados correctamente en formato TXT.");
                break;
        }
    }

        private void terminarPrograma() {
        System.out.println("Programa finalizado." + ANSI_RESET);
    }
}