import java.util.Scanner;

// 1. ESTADO Y NODO: Representan la versión del documento y el enlace doble
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

// 2. CLASE HISTORIAL: Gestor de la lista doblemente enlazada
class Historial {
    private NodoDoble cabeza; // Primer estado (vacío)
    private NodoDoble actual; // Puntero al estado presente

    public Historial() {
        // Inicializamos con un estado vacío
        EstadoDocumento inicial = new EstadoDocumento("");
        this.cabeza = new NodoDoble(inicial);
        this.actual = cabeza;
    }

    // 3. LÓGICA DE TRUNCAMIENTO: Al escribir nuevo texto, se borra el "futuro"
    public void agregarEstado(String nuevoTexto) {
        EstadoDocumento nuevoEstado = new EstadoDocumento(nuevoTexto);
        NodoDoble nuevoNodo = new NodoDoble(nuevoEstado);

        // ALGORITMO DE TRUNCAMIENTO:
        // Rompemos la referencia al 'siguiente' actual para descartar ramas obsoletas
        this.actual.siguiente = nuevoNodo;
        nuevoNodo.anterior = this.actual;
        
        // El puntero se mueve al nuevo nodo (ahora es el último)
        this.actual = nuevoNodo;
        System.out.println(">> [SISTEMA] Estado guardado.");
    }

    // Navegación hacia atrás (Undo)
    public void deshacer() {
        if (this.actual.anterior != null) {
            this.actual = this.actual.anterior;
            System.out.println(">> [SISTEMA] Deshacer ejecutado.");
        } else {
            System.out.println(">> [AVISO] No hay más estados anteriores.");
        }
    }

    // Navegación hacia adelante (Redo)
    public void rehacer() {
        if (this.actual.siguiente != null) {
            this.actual = this.actual.siguiente;
            System.out.println(">> [SISTEMA] Rehacer ejecutado.");
        } else {
            System.out.println(">> [AVISO] No hay estados para rehacer (estás en el punto más reciente).");
        }
    }

    public String obtenerContenidoActual() {
        return this.actual.estado.contenido;
    }
}

// 4. CONSOLA INTERACTIVA: El intérprete de comandos
public class EditorTexto {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        Historial historial = new Historial();
        String entrada;

        System.out.println("==========================================");
        System.out.println("   EDITOR DE TEXTO (LISTA DOBLE)          ");
        System.out.println("==========================================");
        System.out.println(" COMANDOS:");
        System.out.println("  :u -> Deshacer (Undo)");
        System.out.println("  :r -> Rehacer (Redo)");
        System.out.println("  :s -> Salir (Exit)");
        System.out.println(" Escribe cualquier texto para guardar estado.");
        System.out.println("==========================================");

        while (true) {
            System.out.println("\n[DOCUMENTO ACTUAL]: " + historial.obtenerContenidoActual());
            System.out.print("Entrada > ");
            entrada = lector.nextLine().trim();

            // Validación de comandos antes de procesar texto
            if (entrada.equalsIgnoreCase(":s")) {
                System.out.println("Cerrando el editor. ¡Adiós!");
                break;
            } 
            
            if (entrada.equalsIgnoreCase(":u")) {
                historial.deshacer();
            } 
            else if (entrada.equalsIgnoreCase(":r")) {
                historial.rehacer();
            } 
            else if (!entrada.isEmpty()) {
                // Si no es comando y no está vacío, guardamos como nuevo nodo
                historial.agregarEstado(entrada);
            }
        }
        lector.close();
    }
}
