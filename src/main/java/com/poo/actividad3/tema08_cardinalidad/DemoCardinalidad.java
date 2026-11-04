package com.poo.actividad3.tema08_cardinalidad;

import java.util.ArrayList;
import java.util.List;

/**
 * DEMOSTRACIÓN DE:
 * CARDINALIDAD (MULTIPLICIDAD) EN DIAGRAMAS DE CLASES
 * 
 * -------------------------------------------------------------
 * TEORÍA:
 * • La cardinalidad (o multiplicidad) define el número mínimo y máximo de instancias
 *   de una clase que pueden relacionarse con una sola instancia de otra clase.
 * • Notaciones estándar en UML:
 *   - '1' o '1..1' : Exactamente uno.
 *   - '0..1'       : Cero o uno (opcional).
 *   - '*' o '0..*' : Cero a muchos.
 *   - '1..*'       : Uno a muchos (al menos uno obligatorio).
 * • Mapeo a código Java:
 *   - Cardinalidad '1' o '0..1' -> Se implementa como una VARIABLE DE REFERENCIA simple.
 *   - Cardinalidad '*' o '1..*' -> Se implementa mediante una COLECCIÓN (List, Set, Map).
 * -------------------------------------------------------------
 */

// =============================================================
// CASO 1: Cardinalidad 1 a Muchos (1..*) -> Un Cliente tiene muchas Facturas
// =============================================================
class Factura {
    private String numeroFactura;
    private double total;

    public Factura(String numeroFactura, double total) {
        this.numeroFactura = numeroFactura;
        this.total = total;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public double getTotal() {
        return total;
    }
}

class Cliente {
    private String cedula;
    private String nombre;
    // Cardinalidad 1..* (Colección de facturas asociadas a un único cliente)
    private List<Factura> facturas;

    public Cliente(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.facturas = new ArrayList<>();
    }

    public void agregarFactura(Factura factura) {
        facturas.add(factura);
    }

    public void listarFacturas() {
        System.out.printf("Cliente: %s (CC: %s) -> Total facturas asociadas: %d%n", nombre, cedula, facturas.size());
        for (Factura f : facturas) {
            System.out.printf("   - Factura N°: %-12s | Monto: $%,.2f%n", f.getNumeroFactura(), f.getTotal());
        }
    }
}

// =============================================================
// CASO 2: Cardinalidad Muchos a Muchos (*..*) -> Estudiantes y Cursos
// =============================================================
class Curso {
    private String codigo;
    private String nombre;
    // Un curso tiene muchos estudiantes inscritos
    private List<Estudiante> inscritos;

    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.inscritos = new ArrayList<>();
    }

    public void registrarEstudiante(Estudiante est) {
        if (!inscritos.contains(est)) {
            inscritos.add(est);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public List<Estudiante> getInscritos() {
        return inscritos;
    }
}

class Estudiante {
    private String matricula;
    private String nombre;
    // Un estudiante puede inscribir muchos cursos (*..*)
    private List<Curso> cursosInscritos;

    public Estudiante(String matricula, String nombre) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.cursosInscritos = new ArrayList<>();
    }

    public void inscribirCurso(Curso curso) {
        if (!cursosInscritos.contains(curso)) {
            cursosInscritos.add(curso);
            curso.registrarEstudiante(this); // Relación bidireccional (*..*)
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void mostrarHorario() {
        System.out.printf("Estudiante: %s (Matrícula: %s) está cursando:%n", nombre, matricula);
        for (Curso c : cursosInscritos) {
            System.out.println("   * " + c.getNombre());
        }
    }
}

public class DemoCardinalidad {

    public static void ejecutar() {
        System.out.println("==================================================================");
        System.out.println("  TEMA 8: CARDINALIDAD (MULTIPLICIDAD) EN CLASES");
        System.out.println("==================================================================");

        // Demostración Caso 1: 1 a Muchos (1..*)
        System.out.println("\n--- [A] Cardinalidad 1 a Muchos (1..*): Cliente y Facturas ---");
        Cliente cliente = new Cliente("1061789234", "Patricia Salcedo");
        cliente.agregarFactura(new Factura("FAC-001", 185_000.0));
        cliente.agregarFactura(new Factura("FAC-002", 420_000.0));
        cliente.agregarFactura(new Factura("FAC-003", 95_000.0));
        cliente.listarFacturas();

        // Demostración Caso 2: Muchos a Muchos (*..*)
        System.out.println("\n--- [B] Cardinalidad Muchos a Muchos (*..*): Estudiantes y Cursos ---");
        Curso poo = new Curso("POO-101", "Programación Orientada a Objetos");
        Curso bd = new Curso("BD-202", "Bases de Datos Relacionales");

        Estudiante est1 = new Estudiante("EST-01", "Andrés Felipe");
        Estudiante est2 = new Estudiante("EST-02", "Valeria Gómez");

        // Andrés inscribe POO y Bases de Datos
        est1.inscribirCurso(poo);
        est1.inscribirCurso(bd);

        // Valeria inscribe solo POO
        est2.inscribirCurso(poo);

        est1.mostrarHorario();
        est2.mostrarHorario();

        System.out.printf("%nEl curso '%s' tiene %d estudiantes inscritos.%n%n", 
                poo.getNombre(), poo.getInscritos().size());
    }
}
