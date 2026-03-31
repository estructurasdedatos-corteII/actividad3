public class NodoDoble {
    String contenido;
    NodoDoble siguiente;
    NodoDoble anterior;

    public NodoDoble(String contenido) {
        this.contenido = contenido;
        this.siguiente = null;
        this.anterior = null;
    }
}