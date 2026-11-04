package com.poo.actividad3.tema11_hilos;

/**
 * DEMOSTRACIÓN DE:
 * HILOS (MULTITHREADING) EN JAVA
 * 
 * -------------------------------------------------------------
 * TEORÍA:
 * • Un Hilo (Thread) es la unidad más pequeña de procesamiento que puede
 *   ser ejecutada de manera concurrente por el sistema operativo dentro de un proceso.
 * • Permite ejecutar múltiples tareas en paralelo (ej. procesar datos en segundo
 *   plano mientras la interfaz sigue respondiendo).
 * • Mecanismos para crear hilos en Java:
 *   1. Heredando de la clase java.lang.Thread y sobrescribiendo run().
 *   2. Implementando la interfaz java.lang.Runnable (Opción recomendada y flexible).
 *   3. Usando expresiones Lambda con Runnable o ExecutorService (Java moderno).
 * • Métodos clave del ciclo de vida:
 *   - start(): Inicia el hilo en un nuevo hilo de ejecución y llama a run().
 *   - sleep(ms): Pausa el hilo actual por un tiempo determinado.
 *   - join(): Hace que el hilo llamador espere hasta que el hilo objetivo termine.
 *   - synchronized: Previene condiciones de carrera garantizando acceso exclusivo
 *     a recursos compartidos.
 * -------------------------------------------------------------
 */

// 1. Enfoque 1: Heredar de la clase Thread
class HiloDescarga extends Thread {
    private String nombreArchivo;

    public HiloDescarga(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void run() {
        System.out.printf("  [Thread] Iniciando descarga de: %s (Hilo ID: %d)...%n", 
                nombreArchivo, Thread.currentThread().getId());
        try {
            for (int i = 25; i <= 100; i += 25) {
                Thread.sleep(400); // Simula tiempo de red/procesamiento
                System.out.printf("  [Thread - %s]: %d%% completado.%n", nombreArchivo, i);
            }
            System.out.printf("  [Thread] ¡Descarga de %s FINALIZADA!%n", nombreArchivo);
        } catch (InterruptedException e) {
            System.err.println("Hilo interrumpido: " + e.getMessage());
        }
    }
}

// 2. Enfoque 2: Implementar la interfaz Runnable (Mejor práctica)
class TareaProcesamiento implements Runnable {
    private String nombreTarea;

    public TareaProcesamiento(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    @Override
    public void run() {
        System.out.printf("  [Runnable] Ejecutando tarea: %s...%n", nombreTarea);
        try {
            Thread.sleep(800);
            System.out.printf("  [Runnable] Tarea '%s' terminada con éxito.%n", nombreTarea);
        } catch (InterruptedException e) {
            System.err.println("Error en tarea: " + e.getMessage());
        }
    }
}

// 3. Demostración de Sincronización (Thread-Safety)
class CuentaCompartida {
    private double saldo = 1_000_000.0;

    // Método SINCRONIZADO: solo un hilo a la vez puede ejecutarlo
    public synchronized void retirar(String cliente, double monto) {
        if (saldo >= monto) {
            System.out.printf("  >> [%s] Solicitud de retiro de $%,.2f aprobada. Saldo previo: $%,.2f%n", 
                    cliente, monto, saldo);
            try {
                Thread.sleep(200); // Simula demora de cajero
            } catch (InterruptedException ignored) {}
            saldo -= monto;
            System.out.printf("  >> [%s] Retiro completado. Nuevo saldo: $%,.2f%n", cliente, saldo);
        } else {
            System.out.printf("  >> [%s] Retiro rechazado. Saldo insuficiente ($%,.2f).%n", cliente, saldo);
        }
    }

    public double getSaldo() {
        return saldo;
    }
}

public class DemoHilos {

    public static void ejecutar() {
        System.out.println("==================================================================");
        System.out.println("  TEMA 11: HILOS (MULTITHREADING) EN JAVA");
        System.out.println("==================================================================");

        try {
            // [A] Creación con Thread y Runnable en paralelo
            System.out.println("--- [1] Ejecución Concurrente de Tareas con Thread y Runnable ---");
            HiloDescarga hilo1 = new HiloDescarga("VideoTutorial_POO.mp4");
            Thread hilo2 = new Thread(new TareaProcesamiento("Indexación de Base de Datos"));

            // start() arranca ambos hilos en paralelo
            hilo1.start();
            hilo2.start();

            // join() espera a que ambos hilos terminen antes de continuar
            hilo1.join();
            hilo2.join();

            // [B] Demostración de Sincronización y Acceso Concurrente Seguro
            System.out.println("\n--- [2] Sincronización (synchronized) y Prevención de Condiciones de Carrera ---");
            CuentaCompartida cuenta = new CuentaCompartida();

            // Dos personas intentan retirar dinero al mismo tiempo
            Thread cajero1 = new Thread(() -> cuenta.retirar("Cajero Automático Centro", 700_000.0));
            Thread cajero2 = new Thread(() -> cuenta.retirar("Cajero Móvil App", 500_000.0));

            cajero1.start();
            cajero2.start();

            cajero1.join();
            cajero2.join();

            System.out.printf("%nSaldo final seguro en la cuenta: $%,.2f%n%n", cuenta.getSaldo());

        } catch (InterruptedException e) {
            System.err.println("Error durante la ejecución de los hilos: " + e.getMessage());
        }
    }
}
