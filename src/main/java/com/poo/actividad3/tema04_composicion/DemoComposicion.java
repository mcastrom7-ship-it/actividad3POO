package com.poo.actividad3.tema04_composicion;

import java.util.ArrayList;
import java.util.List;

/**
 * DEMOSTRACIÓN DE:
 * ASOCIACIÓN DE COMPOSICIÓN EN CLASES (Composition)
 * 
 * -------------------------------------------------------------
 * TEORÍA:
 * • La composición es una relación "TODO - PARTE" de acoplamiento FUERTE.
 * • Principio clave: La vida de las partes depende ESTRICTAMENTE de la vida del todo.
 * • Las partes NO tienen sentido de existencia fuera del contenedor.
 * • Si el objeto contenedor ("todo") se destruye, todas sus partes se destruyen
 *   automáticamente con él.
 * • En UML: Se representa con un rombo negro o relleno (*--) en el extremo del "todo".
 * • En Java: El objeto "todo" es responsable de instanciar internamente a sus partes
 *   (generalmente dentro de su propio constructor) y no se comparten fuera.
 * -------------------------------------------------------------
 */

// Objeto PARTE: Procesador
class Procesador {
    private String modelo;
    private double frecuenciaGHz;

    public Procesador(String modelo, double frecuenciaGHz) {
        this.modelo = modelo;
        this.frecuenciaGHz = frecuenciaGHz;
    }

    public void ejecutarCiclo() {
        System.out.printf("  [CPU]: Procesando instrucciones a %.2f GHz (%s)...%n", frecuenciaGHz, modelo);
    }
}

// Objeto PARTE: Memoria RAM
class MemoriaRAM {
    private int capacidadGB;
    private String tipo;

    public MemoriaRAM(int capacidadGB, String tipo) {
        this.capacidadGB = capacidadGB;
        this.tipo = tipo;
    }

    public void cargarDatos() {
        System.out.printf("  [RAM]: %d GB %s cargados en memoria de trabajo.%n", capacidadGB, tipo);
    }
}

// Objeto TODO (Contenedor Fuerte): Computadora
class Computadora {
    private String numeroSerie;
    // Partes que nacen y mueren con la Computadora (composición fuerte)
    private Procesador procesador;
    private MemoriaRAM memoriaRAM;

    public Computadora(String numeroSerie, String modeloCPU, double freqCPU, int capacidadRAM, String tipoRAM) {
        this.numeroSerie = numeroSerie;
        // COMPOSICIÓN: Las partes se crean INTERNAMENTE aquí.
        // No fueron creadas afuera ni existen antes de la computadora.
        this.procesador = new Procesador(modeloCPU, freqCPU);
        this.memoriaRAM = new MemoriaRAM(capacidadRAM, tipoRAM);
    }

    public void encenderComputadora() {
        System.out.printf("Encendiendo Computadora Serial: [%s]%n", numeroSerie);
        procesador.ejecutarCiclo();
        memoriaRAM.cargarDatos();
        System.out.println(">> Computadora lista y operativa.");
    }

    public void destruirComputadora() {
        System.out.printf(">> Desmantelando y desechando Computadora [%s]...%n", numeroSerie);
        // Al anular las referencias, el Garbage Collector destruirá procesador y ram
        this.procesador = null;
        this.memoriaRAM = null;
        System.out.println(">> Las partes (CPU y RAM) han sido destruidas junto con la computadora.");
    }
}

public class DemoComposicion {

    public static void ejecutar() {
        System.out.println("==================================================================");
        System.out.println("  TEMA 4: ASOCIACIÓN DE COMPOSICIÓN EN CLASES");
        System.out.println("==================================================================");

        // Se instancia el todo, y este internamente crea y gobierna sus componentes
        Computadora pcGamer = new Computadora(
                "SN-2026-X99", "Intel Core i9-14900K", 5.8, 64, "DDR5");

        pcGamer.encenderComputadora();

        System.out.println();
        // Si se destruye el todo, las partes mueren con él
        pcGamer.destruirComputadora();
        System.out.println();
    }
}
