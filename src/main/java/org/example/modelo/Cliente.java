package org.example.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {
    private String runCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private int aniosCliente;
    private CategoriaEnum nombreCategoria;

    @Override
    public String toString() {
        return "---------- Datos del Cliente---------------" + "\n" +
                "Rut del Cliente= " + runCliente + "\n" +
                "Nombre del Cliente= " + nombreCliente + "\n" +
                "Apellido del Cliente= " + apellidoCliente + "\n" +
                "Anio como Cliente= " + aniosCliente +"\n"+
                "Categoria del Cliente= " + nombreCategoria+"\n"+
                "--------------------------------------------";
    }
}
