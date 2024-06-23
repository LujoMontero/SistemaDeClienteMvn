package org.example.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private String runCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private int aniosCliente;
    private CategoriaEnum nombreCategoria;

    @Override
    public String toString() {
        return ANSI_BLUE + "---------- Datos del Cliente---------------" + "\n" +
                "Rut del Cliente= " + runCliente + "\n" +
                "Nombre del Cliente= " + nombreCliente + "\n" +
                "Apellido del Cliente= " + apellidoCliente + "\n" +
                "Anio como Cliente= " + aniosCliente +"\n"+
                "Categoria del Cliente= " + nombreCategoria+"\n"+
                "--------------------------------------------"+"\u001B[0m";
    }
}
