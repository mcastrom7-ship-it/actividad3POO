package com.poo.actividad3.tema09_streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * DEMOSTRACIÓN DE:
 * LA API STREAM EN JAVA Y SUS OPERACIONES
 * 
 * -------------------------------------------------------------
 * TEORÍA:
 * • Introducida en Java 8 (paquete java.util.stream).
 * • Un Stream NO es una estructura de datos ni almacena elementos; es un cauce
 *   o tubería (pipeline) para procesar datos de forma declarativa y funcional.
 * • No modifica la fuente original (inmutabilidad).
 * • Se divide en dos tipos de operaciones:
 *   1. Operaciones Intermedias (Lazy / Perezosas):
 *      Retornan un nuevo Stream y no se ejecutan hasta que se invoca una terminal.
 *      Ejemplos: filter(), map(), sorted(), distinct(), limit(), skip().
 *   2. Operaciones Terminales (Eager / Ansiosas):
 *      Cierran el Stream y producen un resultado final (colección, valor, conteo).
 *      Ejemplos: collect(), forEach(), reduce(), count(), findFirst(), anyMatch().
 * -------------------------------------------------------------
 */

class Producto {
    private String nombre;
    private String categoria;
    private double precio;

    public Producto(String nombre, String categoria, double precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Double.compare(producto.precio, precio) == 0 &&
                Objects.equals(nombre, producto.nombre) &&
                Objects.equals(categoria, producto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, categoria, precio);
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-12s | $%,12.2f", nombre, categoria, precio);
    }
}

public class DemoStreams {

    public static void ejecutar() {
        System.out.println("==================================================================");
        System.out.println("  TEMA 9: CLASE STREAM Y OPERACIONES EN JAVA");
        System.out.println("==================================================================");

        List<Producto> inventario = Arrays.asList(
                new Producto("Laptop Gamer", "Tecnología", 4_500_000.0),
                new Producto("Teclado Mecánico", "Tecnología", 250_000.0),
                new Producto("Silla Ergonómica", "Muebles", 650_000.0),
                new Producto("Monitor 27'' 144Hz", "Tecnología", 1_100_000.0),
                new Producto("Escritorio Elevable", "Muebles", 1_200_000.0),
                new Producto("Mouse Inalámbrico", "Tecnología", 120_000.0),
                new Producto("Mouse Inalámbrico", "Tecnología", 120_000.0) // Duplicado intencional para distinct()
        );

        System.out.println("--- [1] Filtrado y Mapeo: filter() + map() + collect() ---");
        // Filtrar productos de Tecnología con precio > $300.000 y extraer sus nombres en mayúsculas
        List<String> nombresTechCaros = inventario.stream()
                .filter(p -> p.getCategoria().equals("Tecnología"))
                .filter(p -> p.getPrecio() > 300_000.0)
                .map(p -> p.getNombre().toUpperCase())
                .distinct() // Elimina duplicados
                .collect(Collectors.toList());

        System.out.println("Tecnología > $300.000: " + nombresTechCaros);

        System.out.println("\n--- [2] Eliminación de duplicados y Ordenamiento: distinct() + sorted() ---");
        // Ordenar productos únicos de menor a mayor precio
        List<Producto> ordenadosPorPrecio = inventario.stream()
                .distinct()
                .sorted(Comparator.comparingDouble(Producto::getPrecio))
                .collect(Collectors.toList());

        ordenadosPorPrecio.forEach(p -> System.out.println("   * " + p));

        System.out.println("\n--- [3] Operación Terminal de Reducción: reduce() y conteo: count() ---");
        // Calcular la suma total del inventario único
        double valorTotalInventario = inventario.stream()
                .distinct()
                .map(Producto::getPrecio)
                .reduce(0.0, Double::sum);

        long cantidadProductos = inventario.stream().distinct().count();

        System.out.printf("Cantidad de productos únicos en catálogo: %d%n", cantidadProductos);
        System.out.printf("Valor total acumulado del catálogo único: $%,.2f%n", valorTotalInventario);

        System.out.println("\n--- [4] Operaciones de búsqueda y verificación: anyMatch() y findFirst() ---");
        boolean hayProductosCostosos = inventario.stream().anyMatch(p -> p.getPrecio() > 4_000_000.0);
        System.out.println("¿Existen productos con precio superior a $4.000.000? -> " + hayProductosCostosos);

        inventario.stream()
                .filter(p -> p.getCategoria().equals("Muebles"))
                .findFirst()
                .ifPresent(primerMueble -> System.out.println("Primer mueble en inventario: " + primerMueble.getNombre()));
        System.out.println();
    }
}
