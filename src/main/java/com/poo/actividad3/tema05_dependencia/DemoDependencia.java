package com.poo.actividad3.tema05_dependencia;

/**
 * DEMOSTRACIÓN DE:
 * ASOCIACIÓN DE DEPENDENCIA EN CLASES (Dependency)
 * 
 * -------------------------------------------------------------
 * TEORÍA:
 * • La dependencia es la relación más débil y efímera entre clases ("USA UN" / uses-a).
 * • Ocurre cuando una clase depende de otra para realizar una operación concreta,
 *   pero NO guarda una referencia permanente a ella como atributo de instancia.
 * • Manifestaciones típicas en código Java:
 *   1. La clase proveedora se recibe únicamente como PARÁMETRO de un método.
 *   2. Se crea como una VARIABLE LOCAL dentro de un método y luego se descarta.
 *   3. Se invoca un método estático de la otra clase.
 * • Si la especificación de la clase proveedora cambia, la clase dependiente puede verse afectada.
 * • En UML: Se representa con una línea discontinua con flecha abierta (..>).
 * -------------------------------------------------------------
 */

// Clase que representa un Documento (Proveedor de datos)
class Documento {
    private String titulo;
    private String contenido;

    public Documento(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }
}

// Dispositivo externo (Proveedor de servicio físico)
class ImpresoraLaser {
    public void imprimirFisico(String texto) {
        System.out.println("  [IMPRESORA LÁSER]: Imprimiendo en papel -> " + texto);
    }
}

// Clase dependiente: ServicioImpresion
// Nota: Observa que NO tiene atributos 'Documento' ni 'ImpresoraLaser'.
// Solo depende de ellos de forma TRANSITORIA durante la invocación del método.
class ServicioImpresion {

    // DEPENDENCIA: Documento e ImpresoraLaser son parámetros del método
    public void procesarImpresion(Documento documento, ImpresoraLaser impresora) {
        System.out.println("ServicioImpresion: Preparando cola de impresión...");
        String contenidoFormateado = String.format("=== %s ===\n%s", 
                documento.getTitulo().toUpperCase(), documento.getContenido());
        
        // Uso efímero
        impresora.imprimirFisico(contenidoFormateado);
        System.out.println("ServicioImpresion: Impresión finalizada con éxito.");
    }

    // DEPENDENCIA: Creación de variable local
    public void generarReporteRapido() {
        // Variable local temporal que vive y muere dentro del método
        Documento reporte = new Documento("Reporte Diario", "Ventas totales del día: $15.000.000");
        System.out.println("Reporte generado temporalmente: " + reporte.getTitulo());
    }
}

public class DemoDependencia {

    public static void ejecutar() {
        System.out.println("==================================================================");
        System.out.println("  TEMA 5: ASOCIACIÓN DE DEPENDENCIAS EN CLASES");
        System.out.println("==================================================================");

        ServicioImpresion servicio = new ServicioImpresion();
        Documento contrato = new Documento("Contrato Laboral", "El empleado se compromete a cumplir las normas...");
        ImpresoraLaser hpLaser = new ImpresoraLaser();

        // El servicio utiliza a los otros dos objetos solo en el momento de la llamada
        servicio.procesarImpresion(contrato, hpLaser);
        servicio.generarReporteRapido();
        System.out.println();
    }
}
