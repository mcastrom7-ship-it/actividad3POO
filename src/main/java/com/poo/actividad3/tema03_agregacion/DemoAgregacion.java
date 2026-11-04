package com.poo.actividad3.tema03_agregacion;

import java.util.ArrayList;
import java.util.List;

/**
 * DEMOSTRACIÓN DE:
 * ASOCIACIÓN DE AGREGACIÓN EN CLASES (Aggregation)
 * 
 * -------------------------------------------------------------
 * TEORÍA:
 * • La agregación es una relación "TODO - PARTE" de acoplamiento DÉBIL.
 * • Principio clave: Las partes pueden existir INDEPENDIENTEMENTE del todo.
 * • Si el objeto contenedor ("todo") se destruye o se elimina, los objetos
 *   contenidos ("partes") NO se destruyen y continúan existiendo en el sistema.
 * • En UML: Se representa con un rombo blanco o vacío (o--) en el extremo del "todo".
 * • En Java: Los objetos parte se crean AFUERA y se pasan al todo (por constructor o métodos add).
 * -------------------------------------------------------------
 */

// Objeto PARTE: Docente
class Docente {
    private String nombre;
    private String especialidad;

    public Docente(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}

// Objeto TODO (Contenedor): Departamento Universitario
class Departamento {
    private String nombreDepartamento;
    // Lista de docentes agregados (el departamento NO es dueño absoluto de su existencia)
    private List<Docente> docentes;

    public Departamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
        this.docentes = new ArrayList<>();
    }

    public void agregarDocente(Docente docente) {
        docentes.add(docente);
    }

    public void listarDocentes() {
        System.out.println("Departamento de: " + nombreDepartamento);
        if (docentes.isEmpty()) {
            System.out.println("  (Sin docentes asignados actualmente)");
        } else {
            for (Docente d : docentes) {
                System.out.printf("  - Profesor: %-20s | Especialidad: %s%n", d.getNombre(), d.getEspecialidad());
            }
        }
    }

    public void cerrarDepartamento() {
        System.out.printf(">> Cerrando y disolviendo el departamento de %s...%n", nombreDepartamento);
        this.docentes.clear(); // Se vacía la lista del departamento
    }
}

public class DemoAgregacion {

    public static void ejecutar() {
        System.out.println("==================================================================");
        System.out.println("  TEMA 3: ASOCIACIÓN DE AGREGACIÓN EN CLASES");
        System.out.println("==================================================================");

        // 1. Los docentes (partes) se crean de manera autónoma
        Docente prof1 = new Docente("Dr. Mario Restrepo", "Inteligencia Artificial");
        Docente prof2 = new Docente("Ing. Clara Benítez", "Bases de Datos");
        Docente prof3 = new Docente("Mg. David Quintana", "Ingeniería de Software");

        // 2. Se crea el departamento (el todo) y se le agregan los docentes
        Departamento dptoSistemas = new Departamento("Ingeniería de Sistemas");
        dptoSistemas.agregarDocente(prof1);
        dptoSistemas.agregarDocente(prof2);
        dptoSistemas.agregarDocente(prof3);

        dptoSistemas.listarDocentes();

        // 3. Demostración del ciclo de vida independiente:
        // Si el departamento cierra, los docentes siguen vivos en la memoria del programa
        dptoSistemas.cerrarDepartamento();
        dptoSistemas.listarDocentes();

        System.out.println("\nComprobación de vida independiente de las partes:");
        System.out.printf("El docente %s sigue activo en la universidad y puede ser asignado a otro departamento.%n%n",
                prof1.getNombre());
    }
}
