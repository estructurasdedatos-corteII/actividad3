public class ListaSiemple {
    public class ListaSimple {
    Nodo cabeza;

    // Insertar al final: Recorre hasta el último nulo
    public void insertarFinal(Producto p) {
        Nodo nuevo = new Nodo(p);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) temp = temp.siguiente;
            temp.siguiente = nuevo;
        }
    }

    // ELIMINAR: Cambia el puntero del anterior al siguiente del actual
    public boolean eliminarPorId(int id) {
        if (cabeza == null) return false;
        if (cabeza.dato.id == id) {
            cabeza = cabeza.siguiente;
            return true;
        }
        Nodo temp = cabeza;
        while (temp.siguiente != null && temp.siguiente.dato.id != id) {
            temp = temp.siguiente;
        }
        if (temp.siguiente != null) {
            temp.siguiente = temp.siguiente.siguiente; // Salta el nodo a eliminar
            return true;
        }
        return false;
    }

    // ORDENAMIENTO POR ENLACES (Insertion Sort)
    public void ordenarPorPrecio() {
        if (cabeza == null || cabeza.siguiente == null) return;

        Nodo listaOrdenada = null;
        Nodo actual = cabeza;

        while (actual != null) {
            Nodo siguiente = actual.siguiente; // Guardamos el resto de la lista
            listaOrdenada = insertarOrdenado(listaOrdenada, actual);
            actual = siguiente;
        }
        cabeza = listaOrdenada;
    }

    private Nodo insertarOrdenado(Nodo listaRef, Nodo nuevoNodo) {
        // Caso base: insertar al inicio de la nueva lista ordenada
        if (listaRef == null || nuevoNodo.dato.precio < listaRef.dato.precio) {
            nuevoNodo.siguiente = listaRef;
            return nuevoNodo;
        }
        // Buscar la posición correcta
        Nodo temp = listaRef;
        while (temp.siguiente != null && temp.siguiente.dato.precio < nuevoNodo.dato.precio) {
            temp = temp.siguiente;
        }
        nuevoNodo.siguiente = temp.siguiente; // Conecta el nuevo con el resto
        temp.siguiente = nuevoNodo;          // Conecta el anterior con el nuevo
        return listaRef;
    }

    public void mostrar() {
        Nodo temp = cabeza;
        while (temp != null) {
            System.out.println(temp.dato);
            temp = temp.siguiente;
        }
    }
}
}


