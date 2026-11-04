package com.poo.actividad3.tema06_realizacion;

/**
 * DEMOSTRACIÓN DE:
 * ASOCIACIÓN DE REALIZACIÓN EN CLASES (Realization / Implementation)
 * 
 * -------------------------------------------------------------
 * TEORÍA:
 * • La realización es la relación que vincula una INTERFAZ (especificación abstracta)
 *   con una CLASE CONCRETA que implementa su comportamiento pactado.
 * • La interfaz establece el "QUÉ" (contrato de métodos sin cuerpo).
 * • La clase concreta proporciona el "CÓMO" (el código real que satisface el contrato).
 * • En UML: Se representa con una línea discontinua y una punta de flecha triangular
 *   cerrada y vacía (..|>) apuntando hacia la interfaz.
 * • En Java: Se implementa mediante la palabra reservada 'implements'.
 * -------------------------------------------------------------
 */

// Interfaz (Contrato que debe ser realizado)
interface ProcesadorPago {
    // Métodos abstractos del contrato
    boolean autorizar(double monto);
    void procesarTransaccion(double monto);
    String obtenerComprobante();
}

// Clase Concreta 1 que REALIZA la interfaz ProcesadorPago
class PagoTarjetaCredito implements ProcesadorPago {
    private String numeroTarjetaEnmascarada;
    private String titular;
    private String codigoAprobacion;

    public PagoTarjetaCredito(String numeroTarjeta, String titular) {
        this.numeroTarjetaEnmascarada = "****-****-****-" + numeroTarjeta.substring(Math.max(0, numeroTarjeta.length() - 4));
        this.titular = titular;
    }

    @Override
    public boolean autorizar(double monto) {
        System.out.println("  [TARJETA]: Verificando cupo y autenticando con la franquicia...");
        return monto <= 10_000_000.0; // Cupo máximo simulado
    }

    @Override
    public void procesarTransaccion(double monto) {
        if (autorizar(monto)) {
            this.codigoAprobacion = "AUTH-" + (int)(Math.random() * 900000 + 100000);
            System.out.printf("  [TARJETA]: Cobro de $%.2f aprobado para %s (%s). Cod: %s%n",
                    monto, titular, numeroTarjetaEnmascarada, codigoAprobacion);
        } else {
            System.out.println("  [TARJETA]: Transacción rechazada por fondos insuficientes.");
        }
    }

    @Override
    public String obtenerComprobante() {
        return "Comprobante Tarjeta [" + numeroTarjetaEnmascarada + "] - Auth: " + codigoAprobacion;
    }
}

// Clase Concreta 2 que REALIZA la interfaz ProcesadorPago
class PagoCriptomoneda implements ProcesadorPago {
    private String billeteraDestino;
    private String hashTransaccion;

    public PagoCriptomoneda(String billeteraDestino) {
        this.billeteraDestino = billeteraDestino;
    }

    @Override
    public boolean autorizar(double monto) {
        System.out.println("  [CRYPTO]: Validando firma digital en la red blockchain...");
        return true;
    }

    @Override
    public void procesarTransaccion(double monto) {
        if (autorizar(monto)) {
            this.hashTransaccion = "0x" + Long.toHexString(System.currentTimeMillis()) + "abc99";
            System.out.printf("  [CRYPTO]: Pago de $%.2f verificado en blockchain. Hash: %s%n",
                    monto, hashTransaccion);
        }
    }

    @Override
    public String obtenerComprobante() {
        return "Comprobante Blockchain - Hash: " + hashTransaccion;
    }
}

public class DemoRealizacion {

    public static void ejecutar() {
        System.out.println("==================================================================");
        System.out.println("  TEMA 6: ASOCIACIÓN DE REALIZACIÓN (IMPLEMENTACIÓN DE INTERFACES)");
        System.out.println("==================================================================");

        // Uso del contrato mediante la interfaz (desacoplamiento total)
        ProcesadorPago pago1 = new PagoTarjetaCredito("4532123456789012", "Mariana Gómez");
        ProcesadorPago pago2 = new PagoCriptomoneda("bc1qxy2kgdygjrsqtzq2n0yrf2493p83kkfjhx0wlh");

        System.out.println("\n--- Procesando Pago con Tarjeta de Crédito ---");
        pago1.procesarTransaccion(750_000.0);
        System.out.println("Resultado: " + pago1.obtenerComprobante());

        System.out.println("\n--- Procesando Pago con Criptomonedas ---");
        pago2.procesarTransaccion(1_200_000.0);
        System.out.println("Resultado: " + pago2.obtenerComprobante());
        System.out.println();
    }
}
