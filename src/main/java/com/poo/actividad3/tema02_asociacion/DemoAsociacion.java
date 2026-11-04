package com.poo.actividad3.tema02_asociacion;

/**
 * DEMOSTRACIÓN DE:
 * ASOCIACIÓN ENTRE CLASES (Association)
 * 
 * -------------------------------------------------------------
 * TEORÍA:
 * • La asociación es una relación estructural general entre dos clases.
 * • Representa que un objeto "conoce" o "colabora con" otro objeto.
 * • No existe jerarquía de propiedad ni dependencia del ciclo de vida:
 *   ambos objetos tienen vidas independientes.
 * • En UML se representa con una línea continua simple (unidireccional o bidireccional).
 * • En Java se implementa almacenando una referencia al objeto asociado como atributo.
 * -------------------------------------------------------------
 */

// Clase que representa un Conductor
class Conductor {
    private String nombre;
    private String licencia;
    private Vehiculo vehiculoAsignado; // Asociación: el conductor conoce su vehículo

    public Conductor(String nombre, String licencia) {
        this.nombre = nombre;
        this.licencia = licencia;
    }

    public void asignarVehiculo(Vehiculo vehiculo) {
        this.vehiculoAsignado = vehiculo;
    }

    public void conducir() {
        if (vehiculoAsignado != null) {
            System.out.printf("El conductor %s (Lic: %s) está conduciendo el vehículo [%s - Placa: %s]%n",
                    nombre, licencia, vehiculoAsignado.getModelo(), vehiculoAsignado.getPlaca());
        } else {
            System.out.printf("El conductor %s no tiene ningún vehículo asignado.%n", nombre);
        }
    }

    public String getNombre() {
        return nombre;
    }
}

// Clase que representa un Vehículo
class Vehiculo {
    private String placa;
    private String modelo;

    public Vehiculo(String placa, String modelo) {
        this.placa = placa;
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }
}

public class DemoAsociacion {

    public static void ejecutar() {
        System.out.println("==================================================================");
        System.out.println("  TEMA 2: ASOCIACIÓN ENTRE CLASES");
        System.out.println("==================================================================");

        // Los dos objetos se crean de manera completamente independiente
        Conductor conductor1 = new Conductor("Andrés Morales", "LIC-78901");
        Vehiculo vehiculo1 = new Vehiculo("XYZ-456", "Toyota Hilux");

        // Se establece la asociación mediante referencia
        conductor1.asignarVehiculo(vehiculo1);
        conductor1.conducir();

        // Si desasociamos el vehículo, ambos objetos siguen existiendo en el sistema
        conductor1.asignarVehiculo(null);
        conductor1.conducir();
        System.out.printf("El vehículo %s (%s) sigue existiendo independientemente.%n%n", 
                vehiculo1.getModelo(), vehiculo1.getPlaca());
    }
}
