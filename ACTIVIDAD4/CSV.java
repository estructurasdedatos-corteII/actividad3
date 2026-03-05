public void guardarCSV() {
    try (PrintWriter writer = new PrintWriter(new FileWriter("inventario.csv"))) {
        Nodo aux = cabeza;
        while (aux != null) {
            // Escribimos línea por línea: ID,Nombre,Precio
            writer.println(aux.dato.id + "," + aux.dato.nombre + "," + aux.dato.precio);
            aux = aux.siguiente;
        }
    } catch (IOException e) {
        System.out.println("Error al guardar.");
    }
}