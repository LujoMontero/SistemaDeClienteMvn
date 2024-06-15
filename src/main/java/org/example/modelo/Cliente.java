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
        return "Cliente" + "\n" +
                "Rut= " + runCliente + "\n" +
                "Nombre= " + nombreCliente + "\n" +
                "Apellido= " + apellidoCliente + "\n" +
                "Anio= " + aniosCliente +"\n"+
                "Categoria= " + nombreCategoria;
    }
}
