public class Producto {//atributos 
     int id;
    String nombre;
    double precio;
    

    public Producto(int id, String nombre, double precio ) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    
    }


    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Precio: $" + precio;
    }
}
