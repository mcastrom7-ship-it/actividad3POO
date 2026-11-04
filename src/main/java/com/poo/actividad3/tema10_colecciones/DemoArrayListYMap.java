package com.poo.actividad3.tema10_colecciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DEMOSTRACIÓN DE:
 * COLECCIONES EN JAVA: ArrayList<E> Y Map<K, V> (HashMap)
 * 
 * -------------------------------------------------------------
 * TEORÍA Y COMPARATIVA:
 * 
 * 1. ArrayList<E>:
 *    • Implementa la interfaz List<E>.
 *    • Estructura SECUENCIAL y ORDENADA basada en un arreglo dinámico redimensionable.
 *    • Acceso directo mediante ÍNDICE numérico (0, 1, 2...).
 *    • Permite elementos DUPLICADOS y valores null.
 *    • Búsqueda por índice en tiempo constante O(1).
 *    • Métodos clave: add(), get(), set(), remove(), contains(), size(), clear().
 * 
 * 2. Map<K, V> (HashMap<K, V>):
 *    • Estructura ASOCIATIVA de pares CLAVE - VALOR.
 *    • Cada CLAVE (Key) es ÚNICA; los VALORES (Value) pueden repetirse.
 *    • Acceso inmediato por CLAVE mediante función Hash en tiempo promedio O(1).
 *    • NO garantiza un orden secuencial (a diferencia de TreeMap o LinkedHashMap).
 *    • Métodos clave: put(), get(), getOrDefault(), containsKey(), containsValue(),
 *      remove(), keySet(), values(), entrySet().
 * -------------------------------------------------------------
 */

public class DemoArrayListYMap {

    public static void ejecutar() {
        System.out.println("==================================================================");
        System.out.println("  TEMA 10: COLECCIONES EN JAVA (ArrayList<E> y Map<K, V>)");
        System.out.println("==================================================================");

        // =========================================================
        // PARTE 1: DEMOSTRACIÓN DE ArrayList<E>
        // =========================================================
        System.out.println("\n--- [1] ArrayList<String>: Lista secuencial basada en índices ---");
        List<String> listaNombres = new ArrayList<>();

        // Operaciones de inserción
        listaNombres.add("Carlos Gómez");
        listaNombres.add("Mariana Ruiz");
        listaNombres.add("Felipe Jaramillo");
        listaNombres.add("Carlos Gómez"); // Duplicado permitido

        System.out.println("Tamaño de la lista (size): " + listaNombres.size());
        System.out.println("Elemento en el índice 1 (get): " + listaNombres.get(1));

        // Modificación
        listaNombres.set(0, "Carlos Alberto Gómez");
        System.out.println("Elemento modificado en índice 0: " + listaNombres.get(0));

        // Eliminación
        listaNombres.remove("Carlos Gómez"); // Elimina la primera coincidencia
        System.out.println("Contenido actual de ArrayList:");
        for (int i = 0; i < listaNombres.size(); i++) {
            System.out.printf("   [%d] -> %s%n", i, listaNombres.get(i));
        }

        // =========================================================
        // PARTE 2: DEMOSTRACIÓN DE Map<K, V> (HashMap)
        // =========================================================
        System.out.println("\n--- [2] Map<String, Double> (HashMap): Diccionario Clave-Valor ---");
        // Clave: Cédula / Documento (String único) -> Valor: Salario (Double)
        Map<String, Double> mapaEmpleados = new HashMap<>();

        // Inserción de pares Clave-Valor (put)
        mapaEmpleados.put("101725489", 3_500_000.0);
        mapaEmpleados.put("102589741", 2_800_000.0);
        mapaEmpleados.put("109845612", 1_440_000.0);
        mapaEmpleados.put("104587123", 1_750_000.0);

        // Si se inserta una clave existente, se ACTUALIZA su valor
        mapaEmpleados.put("101725489", 3_850_000.0); // Aumento de sueldo

        // Búsqueda inmediata por clave O(1)
        String cedulaBuscar = "102589741";
        System.out.printf("Salario para la cédula '%s': $%,.2f%n", 
                cedulaBuscar, mapaEmpleados.get(cedulaBuscar));

        // Verificación de existencia
        System.out.println("¿Existe la clave '109845612'? -> " + mapaEmpleados.containsKey("109845612"));
        System.out.println("¿Existe la clave '999999999'? -> " + mapaEmpleados.containsKey("999999999"));

        // Recorrido de pares clave-valor usando entrySet()
        System.out.println("\nRecorrido completo del Map (Cédula -> Salario):");
        for (Map.Entry<String, Double> entrada : mapaEmpleados.entrySet()) {
            System.out.printf("   * CC: %-12s => Salario: $%,.2f%n", entrada.getKey(), entrada.getValue());
        }
        System.out.println();
    }
}
