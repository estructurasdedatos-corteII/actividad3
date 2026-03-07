public class Producto {//atributos 
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(int id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }


    public String toString() {
        return String.format("ID:  | Producto: | Precio: $%", id, nombre, precio,cantidad);
    }

    public String toCSV() {
        return id + "," + nombre + "," + precio + cantidad +",";
    }
}
