public class HistorialEditor {
    private NodoDoble actual;

    public HistorialEditor() {
        // Estado inicial vacío
        this.actual = new NodoDoble("");
    }

    public void agregarEstado(String nuevoTexto) {
        NodoDoble nuevoNodo = new NodoDoble(nuevoTexto);
        
        // LÓGICA DE TRUNCAMIENTO:
        // Conectamos el nuevo nodo al actual, rompiendo cualquier "futuro" previo
        nuevoNodo.anterior = actual;
        actual.siguiente = nuevoNodo;
        
        // Movemos el puntero actual al nuevo estado
        actual = nuevoNodo;
        System.out.println("[Sistema]: Estado guardado y futuro truncado.");
    }

    public boolean deshacer() {
        if (actual.anterior != null) {
            actual = actual.anterior;
            return true;
        }
        return false;
    }

    public boolean rehacer() {
        if (actual.siguiente != null) {
            actual = actual.siguiente;
            return true;
        }
        return false;
    }

    public String getContenidoActual() {
        return actual.contenido;
    }
}
