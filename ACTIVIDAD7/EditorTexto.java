import java.util.Scanner;

// 1. Estructuras de Datos
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

// 2. Gestor de Historial
class Historial {
    private NodoDoble actual;

    public Historial() {
        // Estado inicial vacío para evitar errores de puntero nulo
        this.actual = new NodoDoble(new EstadoDocumento(""));
    }

    // 3. Lógica de Truncamiento
    public void agregarEstado(String nuevoTexto) {
        NodoDoble nuevoNodo = new NodoDoble(new EstadoDocumento(nuevoTexto));
        
        // Truncamiento: Cortamos cualquier rama de futuro obsoleta
        this.actual.siguiente = nuevoNodo; 
        nuevoNodo.anterior = this.actual;
        
        // El puntero se mueve a la nueva versión
        this.actual = nuevoNodo;
        System.out.println(">> [Guardado]");
    }

    public void deshacer() {
        if (actual.anterior != null) {
            actual = actual.anterior;
            System.out.println(">> [Undo]");
        } else {
            System.out.println(">> [Inicio del historial]");
        }
    }

    public void rehacer() {
        if (actual.siguiente != null) {
            actual = actual.siguiente;
            System.out.println(">> [Redo]");
        } else {
            System.out.println(">> [Fin del historial]");
        }
    }

    public String obtenerTexto() { return actual.estado.contenido; }
}

// 4. Consola Interactiva Corregida
public class EditorTexto {
    public static void main(String[] args) {
        // Forzamos el uso de System.in para evitar bloqueos en IDEs
        Scanner lector = new Scanner(System.in);
        Historial historial = new Historial();
        
        System.out.println("=== EDITOR DE TEXTO (LISTA DOBLE) ===");
        System.out.println("Instrucciones:");
        System.out.println("- Escribe texto para guardar un estado.");
        System.out.println("- Usa :u para DESHACER.");
        System.out.println("- Usa :r para REHACER.");
        System.out.println("- Usa :s para SALIR.");

        while (true) {
            System.out.print("\nDocumento actual: [" + historial.obtenerTexto() + "]\nEntrada > ");
            
            // Verificamos si hay una línea disponible para evitar que el programa se cierre solo
            if (!lector.hasNextLine()) break;
            
            String entrada = lector.nextLine().trim();

            // Prioridad a los comandos
            if (entrada.equalsIgnoreCase(":s")) {
                System.out.println("Cerrando...");
                break;
            } else if (entrada.equalsIgnoreCase(":u")) {
                historial.deshacer();
            } else if (entrada.equalsIgnoreCase(":r")) {
                historial.rehacer();
            } else if (!entrada.isEmpty()) {
                // Si hay texto real, se guarda el estado
                historial.agregarEstado(entrada);
            }
        }
        lector.close();
    }
}
