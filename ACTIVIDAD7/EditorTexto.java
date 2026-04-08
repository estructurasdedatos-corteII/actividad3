import java.util.Scanner;

class EstadoDocumento {
    String contenido;
    public EstadoDocumento(String contenido) { this.contenido = contenido; }
}

class NodoDoble {
    EstadoDocumento estado;
    NodoDoble anterior, siguiente;
    public NodoDoble(EstadoDocumento estado) { 
        this.estado = estado; 
        this.anterior = null;
        this.siguiente = null;
    }
}

class Historial {
    private NodoDoble actual;

    public Historial() {
        this.actual = new NodoDoble(new EstadoDocumento(""));
    }

    public void agregarEstado(String nuevaEntrada) {
        String previo = actual.estado.contenido;
        String nuevoContenido = previo.isEmpty() ? nuevaEntrada : previo + " " + nuevaEntrada;
        
        NodoDoble nuevoNodo = new NodoDoble(new EstadoDocumento(nuevoContenido));
        
        // TRUNCAMIENTO: Se corta el futuro aquí
        this.actual.siguiente = nuevoNodo;
        nuevoNodo.anterior = this.actual;
        this.actual = nuevoNodo;
        System.out.println(">> [SISTEMA]: Guardado correctamente.");
    }

    public void deshacer() {
        if (actual.anterior != null) {
            actual = actual.anterior;
            System.out.println(">> [SISTEMA]: Deshacer ejecutado.");
        } else {
            System.out.println(">> [AVISO]: Inicio del historial.");
        }
    }

    public void rehacer() {
        if (actual.siguiente != null) {
            actual = actual.siguiente;
            System.out.println(">> [SISTEMA]: Rehacer ejecutado.");
        } else {
            System.out.println(">> [AVISO]: No hay nada que rehacer.");
        }
    }

    public String obtenerTexto() {
        return actual.estado.contenido.isEmpty() ? "(Vacío)" : actual.estado.contenido;
    }
}

public class EditorTexto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Historial historial = new Historial();
        
        System.out.println("=== EDITOR DE TEXTO ACTIVO ===");
        System.out.println("COMANDOS: :u (Deshacer), :r (Rehacer), :s (Salir)");

        while (true) {
            System.out.println("\nTEXTO ACTUAL: [" + historial.obtenerTexto() + "]");
            System.out.print("ESCRIBE AQUÍ > ");
            
            if (!sc.hasNextLine()) break;
            String entrada = sc.nextLine().trim();

            // Validación ultra-estricta de comandos
            if (entrada.equalsIgnoreCase(":s")) {
                System.out.println("Cerrando...");
                break;
            } else if (entrada.equalsIgnoreCase(":u")) {
                historial.deshacer();
            } else if (entrada.equalsIgnoreCase(":r")) {
                historial.rehacer();
            } else if (!entrada.isEmpty()) {
                historial.agregarEstado(entrada);
            }
        }
        sc.close();
    }
}
