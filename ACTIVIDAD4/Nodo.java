 public class Nodo {//atributos
    Producto dato;
    Nodo siguiente; //es un puntero o referencia dentro de una estructura de datos 
    // (como listas enlazadas, árboles o grafos) que 
    // conecta el nodo actual con el próximo elemento en la secuencia.

    public Nodo(Producto producto) {
        this.dato = producto;// estamos guardando la informacion del nodo 
        this.siguiente = null;// Estás diciendo que por ahora este eslabón no está conectado a ningún otro
    }

}
