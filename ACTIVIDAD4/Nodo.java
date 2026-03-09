public class Nodo {//atributos
    Producto dato;
    Nodo siguiente; //es un puntero o referencia dentro de una estructura de datos 
    // (como listas enlazadas, árboles o grafos) que 
    // conecta el nodo actual con el próximo elemento en la secuencia.

    public Nodo(Producto producto) {
        this.dato = producto;
        this.siguiente = null;
    }

}
