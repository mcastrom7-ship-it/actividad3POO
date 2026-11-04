package com.poo.actividad3.tema01_sobrecarga_sobreescritura;

/**
 * DEMOSTRACIÓN DE:
 * 1. SOBRECARGA DE MÉTODOS (Method Overloading)
 * 2. SOBREESCRITURA DE MÉTODOS (Method Overriding)
 * 
 * -------------------------------------------------------------
 * TEORÍA:
 * • Sobrecarga (Overloading):
 *   Ocurre dentro de la MISMA clase. Varios métodos tienen el MISMO nombre
 *   pero DIFERENTE lista de parámetros (en número, tipo u orden).
 *   Es polimorfismo en tiempo de compilación (estático).
 * 
 * • Sobreescritura (Overriding):
 *   Ocurre en una relación de HERENCIA (entre superclase y subclase).
 *   La subclase redefine el comportamiento de un método heredado
 *   conservando EXACTAMENTE el mismo nombre, tipo de retorno y parámetros.
 *   Se marca con la anotación '@Override' y es polimorfismo dinámico (en tiempo de ejecución).
 * -------------------------------------------------------------
 */

// 1. EJEMPLO DE SOBRECARGA (Method Overloading)
class CalculadoraGeometria {

    // Sobrecarga 1: Área de un cuadrado (1 parámetro)
    public double calcularArea(double lado) {
        return lado * lado;
    }

    // Sobrecarga 2: Área de un rectángulo (2 parámetros)
    public double calcularArea(double base, double altura) {
        return base * altura;
    }

    // Sobrecarga 3: Área de una figura con factor de escala (3 parámetros)
    public double calcularArea(double base, double altura, double escala) {
        return (base * altura) * escala;
    }
}

// 2. EJEMPLO DE SOBREESCRITURA (Method Overriding)
// Superclase
class Empleado {
    protected String nombre;
    protected double salarioBase;

    public Empleado(String nombre, double salarioBase) {
        this.nombre = nombre;
        this.salarioBase = salarioBase;
    }

    // Método que será sobrescrito por las subclases
    public double calcularSalario() {
        return salarioBase;
    }

    public void mostrarRol() {
        System.out.println("Empleado regular: " + nombre + " | Salario: $" + calcularSalario());
    }
}

// Subclase 1
class Gerente extends Empleado {
    private double bonoGerencial;

    public Gerente(String nombre, double salarioBase, double bonoGerencial) {
        super(nombre, salarioBase);
        this.bonoGerencial = bonoGerencial;
    }

    // SOBREESCRITURA: Redefinición del cálculo para incluir bono
    @Override
    public double calcularSalario() {
        return salarioBase + bonoGerencial;
    }

    @Override
    public void mostrarRol() {
        System.out.println("Gerente: " + nombre + " | Salario con Bono: $" + calcularSalario());
    }
}

// Subclase 2
class VendedorComision extends Empleado {
    private double totalVentas;
    private double porcentajeComision;

    public VendedorComision(String nombre, double salarioBase, double totalVentas, double porcentajeComision) {
        super(nombre, salarioBase);
        this.totalVentas = totalVentas;
        this.porcentajeComision = porcentajeComision;
    }

    // SOBREESCRITURA: Redefinición del cálculo para sumar comisiones
    @Override
    public double calcularSalario() {
        return salarioBase + (totalVentas * (porcentajeComision / 100.0));
    }

    @Override
    public void mostrarRol() {
        System.out.println("Vendedor: " + nombre + " | Salario con Comisiones: $" + calcularSalario());
    }
}

public class DemoSobrecargaSobreescritura {

    public static void ejecutar() {
        System.out.println("==================================================================");
        System.out.println("  TEMA 1: SOBRECARGA Y SOBREESCRITURA DE MÉTODOS");
        System.out.println("==================================================================");

        // --- Demostración de Sobrecarga ---
        System.out.println("\n--- [A] Demostración de SOBRECARGA (Misma clase, diferente firma) ---");
        CalculadoraGeometria calc = new CalculadoraGeometria();
        System.out.println("calcularArea(5.0) [Cuadrado]            : " + calc.calcularArea(5.0));
        System.out.println("calcularArea(6.0, 4.0) [Rectángulo]     : " + calc.calcularArea(6.0, 4.0));
        System.out.println("calcularArea(6.0, 4.0, 1.5) [Con escala]: " + calc.calcularArea(6.0, 4.0, 1.5));

        // --- Demostración de Sobreescritura ---
        System.out.println("\n--- [B] Demostración de SOBREESCRITURA (Herencia y @Override) ---");
        Empleado emp1 = new Empleado("Carlos Torres", 2_000_000.0);
        Empleado emp2 = new Gerente("Laura Mendoza", 4_500_000.0, 1_500_000.0);
        Empleado emp3 = new VendedorComision("Felipe Castro", 1_300_000.0, 8_000_000.0, 5.0);

        emp1.mostrarRol();
        emp2.mostrarRol();
        emp3.mostrarRol();
        System.out.println();
    }
}
