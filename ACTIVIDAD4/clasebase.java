public class Producto {
    int id;
    String nombre;
    double precio;

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    // toString() para mostrar en consola
}

public class Nodo {
    Producto dato;
    Nodo siguiente;

    public Nodo(Producto producto) {
        this.dato = producto;
        this.siguiente = null;
    }
}