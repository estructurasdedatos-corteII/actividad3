public void cargarDesdeCSV(String ruta) {
    try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            int id = Integer.parseInt(datos[0]);
            String nombre = datos[1];
            double precio = Double.parseDouble(datos[2]);
            
            // Usamos tu método de insertar al final
            insertarFinal(new Producto(id, nombre, precio));
        }
    } catch (IOException e) {
        System.out.println("Archivo no encontrado, iniciando lista vacía.");
    }
}
