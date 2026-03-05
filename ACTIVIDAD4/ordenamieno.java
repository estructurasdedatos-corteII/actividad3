public void ordenarPorPrecio() {
    if (cabeza == null || cabeza.siguiente == null) return;

    Nodo listaOrdenada = null;
    Nodo actual = cabeza;

    while (actual != null) {
        Nodo siguienteNodo = actual.siguiente; // Guardar el resto de la lista
        
        // Insertar 'actual' en la posición correcta de la nueva lista auxiliar
        if (listaOrdenada == null || listaOrdenada.dato.precio >= actual.dato.precio) {
            actual.siguiente = listaOrdenada;
            listaOrdenada = actual;
        } else {
            Nodo temp = listaOrdenada;
            while (temp.siguiente != null && temp.siguiente.dato.precio < actual.dato.precio) {
                temp = temp.siguiente;
            }
            actual.siguiente = temp.siguiente;
            temp.siguiente = actual;
        }
        actual = siguienteNodo;
    }
    cabeza = listaOrdenada;
}