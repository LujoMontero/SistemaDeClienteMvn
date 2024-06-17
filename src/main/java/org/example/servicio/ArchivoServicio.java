package org.example.servicio;

import org.example.modelo.CategoriaEnum;
import org.example.modelo.Cliente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class ArchivoServicio extends Exportador {

    public void cargarDatos(String fileName, ClienteServicio clienteServicio) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Cliente cliente = new Cliente(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]), CategoriaEnum.valueOf(datos[4]));
                clienteServicio.agregarCliente(cliente.getRunCliente(), cliente.getNombreCliente(), cliente.getApellidoCliente(), cliente.getAniosCliente(), cliente.getNombreCategoria());
            }
            System.out.println("Datos cargados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        // Este método puede quedar vacío, ya que la funcionalidad de exportar se maneja por ExportadorCsv y ExportarTxt
    }
}