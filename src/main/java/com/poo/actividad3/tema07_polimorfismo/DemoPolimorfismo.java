package com.poo.actividad3.tema07_polimorfismo;

import java.util.ArrayList;
import java.util.List;

/**
 * DEMOSTRACIÓN DE:
 * POLIMORFISMO (Polymorphism)
 * 
 * -------------------------------------------------------------
 * TEORÍA:
 * • "Poli" = muchas, "Morfos" = formas.
 * • Es la capacidad que tiene una misma referencia o invocación de método
 *   de comportarse de maneras distintas dependiendo del objeto real que
 *   la ejecuta en tiempo de ejecución (Enlace Dinámico / Dynamic Dispatch).
 * • Tipos de Polimorfismo:
 *   1. Polimorfismo Estático (Tiempo de compilación): Sobrecarga de métodos.
 *   2. Polimorfismo Dinámico (Tiempo de ejecución): Sobreescritura mediante
 *      herencia o interfaces.
 * • Ventaja principal: Permite escribir código genérico y extensible
 *   (Principio Abierto/Cerrado de SOLID).
 * -------------------------------------------------------------
 */

// Superclase abstracta
abstract class InstrumentoMusical {
    protected String nombre;

    public InstrumentoMusical(String nombre) {
        this.nombre = nombre;
    }

    // Método polimórfico abstracto
    public abstract void tocar();

    // Método polimórfico abstracto
    public abstract void afinar();
}

// Subclase 1
class Guitarra extends InstrumentoMusical {
    private int cuerdas;

    public Guitarra(String nombre, int cuerdas) {
        super(nombre);
        this.cuerdas = cuerdas;
    }

    @Override
    public void tocar() {
        System.out.printf("  [GUITARRA %s]: Rasgueando acordes armónicos en sus %d cuerdas. (♪♫ strum strum)%n", 
                nombre, cuerdas);
    }

    @Override
    public void afinar() {
        System.out.println("  [GUITARRA]: Ajustando clavijas de las 6 cuerdas a Mi-Si-Sol-Re-La-Mi.");
    }
}

// Subclase 2
class Piano extends InstrumentoMusical {
    private int teclas;

    public Piano(String nombre, int teclas) {
        super(nombre);
        this.teclas = teclas;
    }

    @Override
    public void tocar() {
        System.out.printf("  [PIANO %s]: Martilleando cuerdas internas a través de sus %d teclas. (♫ plink plonk)%n", 
                nombre, teclas);
    }

    @Override
    public void afinar() {
        System.out.println("  [PIANO]: Calibrando tensión armónica con llave de afinación profesional.");
    }
}

// Subclase 3
class Trompeta extends InstrumentoMusical {
    public Trompeta(String nombre) {
        super(nombre);
    }

    @Override
    public void tocar() {
        System.out.printf("  [TROMPETA %s]: Vibrando aire metálico a través de los pistones. (🎺 tuuut tuuu)%n", nombre);
    }

    @Override
    public void afinar() {
        System.out.println("  [TROMPETA]: Deslizando bomba de afinación principal.");
    }
}

public class DemoPolimorfismo {

    public static void ejecutar() {
        System.out.println("==================================================================");
        System.out.println("  TEMA 7: POLIMORFISMO EN PROGRAMACIÓN ORIENTADA A OBJETOS");
        System.out.println("==================================================================");

        // Colección homogénea de referencias abstractas InstrumentoMusical
        List<InstrumentoMusical> orquesta = new ArrayList<>();
        orquesta.add(new Guitarra("Fender Stratocaster", 6));
        orquesta.add(new Piano("Yamaha Acústico", 88));
        orquesta.add(new Trompeta("Bach Stradivarius"));

        System.out.println("--- [1] AFINACIÓN POLIMÓRFICA DE LA ORQUESTA ---");
        for (InstrumentoMusical inst : orquesta) {
            // El compilador solo sabe que es un InstrumentoMusical,
            // pero la JVM ejecuta la versión especializada de cada objeto concreto
            inst.afinar();
        }

        System.out.println("\n--- [2] CONCIERTO EN VIVO (INVOCACIÓN POLIMÓRFICA tocar()) ---");
        for (InstrumentoMusical inst : orquesta) {
            inst.tocar();
        }
        System.out.println();
    }
}
