package org.example.utilidades;

public class Utilidad {

    public static void limpiarPantalla() {

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}