package com.poo.actividad3.app;

import com.poo.actividad3.tema01_sobrecarga_sobreescritura.DemoSobrecargaSobreescritura;
import com.poo.actividad3.tema02_asociacion.DemoAsociacion;
import com.poo.actividad3.tema03_agregacion.DemoAgregacion;
import com.poo.actividad3.tema04_composicion.DemoComposicion;
import com.poo.actividad3.tema05_dependencia.DemoDependencia;
import com.poo.actividad3.tema06_realizacion.DemoRealizacion;
import com.poo.actividad3.tema07_polimorfismo.DemoPolimorfismo;
import com.poo.actividad3.tema08_cardinalidad.DemoCardinalidad;
import com.poo.actividad3.tema09_streams.DemoStreams;
import com.poo.actividad3.tema10_colecciones.DemoArrayListYMap;
import com.poo.actividad3.tema11_hilos.DemoHilos;

import java.util.Scanner;

/**
 * CLASE PRINCIPAL DE EJECUCIÓN (MAIN)
 * 
 * Actividad Tres: Programación Orientada a Objetos
 * Entorno: Apache NetBeans / Consola Java
 * 
 * Permite ejecutar de forma interactiva e individual cualquiera de los 11 temas
 * exigidos en la guía académica, o ejecutar la demostración completa para
 * la grabación del video de YouTube.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;

        System.out.println("==================================================================");
        System.out.println("  UNIVERSIDAD / FACULTAD DE INGENIERÍA");
        System.out.println("  ACTIVIDAD TRES: PROGRAMACIÓN ORIENTADA A OBJETOS");
        System.out.println("  Proyecto Demostrativo en Java para Apache NetBeans y YouTube");
        System.out.println("==================================================================");

        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione una opción (1-13): ");
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    DemoSobrecargaSobreescritura.ejecutar();
                    break;
                case "2":
                    DemoAsociacion.ejecutar();
                    break;
                case "3":
                    DemoAgregacion.ejecutar();
                    break;
                case "4":
                    DemoComposicion.ejecutar();
                    break;
                case "5":
                    DemoDependencia.ejecutar();
                    break;
                case "6":
                    DemoRealizacion.ejecutar();
                    break;
                case "7":
                    DemoPolimorfismo.ejecutar();
                    break;
                case "8":
                    DemoCardinalidad.ejecutar();
                    break;
                case "9":
                    DemoStreams.ejecutar();
                    break;
                case "10":
                    DemoArrayListYMap.ejecutar();
                    break;
                case "11":
                    DemoHilos.ejecutar();
                    break;
                case "12":
                    ejecutarTodosLosTemas();
                    break;
                case "13":
                    salir = true;
                    System.out.println("\n¡Gracias por utilizar el proyecto de la Actividad 3! Cerrando programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor ingrese un número del 1 al 13.");
                    break;
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n----------------- MENÚ DE TEMAS DE LA ACTIVIDAD 3 -----------------");
        System.out.println(" 1. Sobrecarga y Sobreescritura de Métodos");
        System.out.println(" 2. Asociación entre Clases");
        System.out.println(" 3. Asociación de Agregación en Clases");
        System.out.println(" 4. Asociación de Composición en Clases");
        System.out.println(" 5. Asociación de Dependencias");
        System.out.println(" 6. Asociación de Realización (Interfaces)");
        System.out.println(" 7. Polimorfismo");
        System.out.println(" 8. Cardinalidad en Clases (1..*, *..*)");
        System.out.println(" 9. Clase Stream y Operaciones Funcionales");
        System.out.println("10. Colecciones ArrayList y Map");
        System.out.println("11. Hilos en Java (Multithreading)");
        System.out.println("12. [DEMO COMPLETA] Ejecutar todos los temas en secuencia (Para Video)");
        System.out.println("13. Salir");
        System.out.println("-------------------------------------------------------------------");
    }

    private static void ejecutarTodosLosTemas() {
        System.out.println("\n>>>>>>>>>>>> INICIANDO DEMOSTRACIÓN GENERAL DEL PROYECTO <<<<<<<<<<<<\n");
        DemoSobrecargaSobreescritura.ejecutar();
        DemoAsociacion.ejecutar();
        DemoAgregacion.ejecutar();
        DemoComposicion.ejecutar();
        DemoDependencia.ejecutar();
        DemoRealizacion.ejecutar();
        DemoPolimorfismo.ejecutar();
        DemoCardinalidad.ejecutar();
        DemoStreams.ejecutar();
        DemoArrayListYMap.ejecutar();
        DemoHilos.ejecutar();
        System.out.println(">>>>>>>>>> FIN DE LA DEMOSTRACIÓN GENERAL DE LA ACTIVIDAD 3 <<<<<<<<<<\n");
    }
}
