import java.util.Scanner;

// 1. Estado y Nodo
class EstadoDocumento {
    String contenido;

    public EstadoDocumento(String contenido) {
        this.contenido = contenido;
    }
}

class NodoDoble {
    EstadoDocumento estado;
    NodoDoble anterior;
    NodoDoble siguiente;

    public NodoDoble(EstadoDocumento estado) {
        this.estado = estado;
        this.anterior = null;
        this.siguiente = null;
    }
}

// 2. Clase Historial con Lógica de Truncamiento
class Historial {
    private NodoDoble cabeza;
    private NodoDoble actual;

    public Historial() {
        // Estado inicial vacío
        EstadoDocumento inicial = new EstadoDocumento("");
        this.cabeza = new NodoDoble(inicial);
        this.actual = cabeza;
    }

    // 3. Lógica de Truncamiento: Rompe referencias futuras si se añade texto nuevo
    public void agregarEstado(String nuevoTexto) {
        EstadoDocumento nuevoEstado = new EstadoDocumento(nuevoTexto);
        NodoDoble nuevoNodo = new NodoDoble(nuevoEstado);

        // Truncamiento: El 'siguiente' del actual ahora es el nuevo nodo
        // Cualquier rama que existiera después de 'actual' se pierde (Garbage Collector)
        this.actual.siguiente = nuevoNodo;
        nuevoNodo.anterior = this.actual;
        
        // Movemos el puntero actual al nuevo estado
        this.actual = nuevoNodo;
        System.out.println("[Sistema] Nuevo estado guardado.");
    }

    public void deshacer() {
        if (actual.anterior != null) {
            actual = actual.anterior;
            System.out.println("[Sistema] Deshacer realizado.");
        } else {
            System.out.println("[Error] No hay más estados previos.");
        }
    }

    public void rehacer() {
        if (actual.siguiente != null) {
            actual = actual.siguiente;
            System.out.println("[Sistema] Rehacer realizado.");
        } else {
            System.out.println("[Error] No hay estados futuros (Rehacer no disponible).");
        }
    }

    public String getContenidoActual() {
        return actual.estado.contenido;
    }
}

// 4. Consola Interactiva
public class EditorTexto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Historial historial = new Historial();
        String comando;

        System.out.println("=== EDITOR DE TEXTO CON LISTA DOBLE ===");
        System.out.println("Comandos: :u (Undo), :r (Redo), :s (Salir)");
        System.out.println("Cualquier otro texto se guardará como nuevo estado.");

        while (true) {
            System.out.println("\nCONTENIDO ACTUAL: [" + historial.getContenidoActual() + "]");
            System.out.print("> ");
            comando = scanner.nextLine();

            if (comando.equalsIgnoreCase(":s")) {
                System.out.println("Cerrando editor...");
                break;
            } else if (comando.equalsIgnoreCase(":u")) {
                historial.deshacer();
            } else if (comando.equalsIgnoreCase(":r")) {
                historial.rehacer();
            } else {
                historial.agregarEstado(comando);
            }
        }
        scanner.close();
    }
}
