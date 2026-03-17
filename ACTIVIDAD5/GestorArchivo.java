import java.io.*;
public class GestorArchivo {
    private static final String RUTA = "Inventario.csv";

    public static void guardar(ListaSimple lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA))) {//abrir y cerra
            Nodo temp = lista.cabeza;//recore la lista
            while (temp != null) {
                pw.println(temp.dato.id + "," + temp.dato.nombre + "," + temp.dato.precio);
                temp = temp.siguiente;//recore asta el otro producto
            }
        } catch (IOException e)
         { System.out.println("Error al guardar."); }
    }

    public static void cargar(ListaSimple lista) {
        File file = new File(RUTA);
        if (!file.exists()) return;//Si el archivo no existe el  termina
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA))) {//abrimos el archivo
            String linea;
            while ((linea = br.readLine()) != null) {//leer asta llegar a su fin
                String[] d = linea.split(",");//Corta la línea cada vez que encuentra una coma y guarda 
                // los trozos en un arreglo de textos llamado d.
                lista.insertarFinal(new Producto(Integer.parseInt(d[0]), d[1], Double.parseDouble(d[2])));//Como el archivo es puro texto, 
                // usa Integer.parseInt para el ID y Double.parseDouble para
                //  el precio para convertirlos de nuevo a números. insertarFinal: Crea un nuevo objeto Producto con esos datos
                //  y lo engancha al final de la lista enlazada actual.
            }
        } catch (Exception e)
        { System.out.println("Error al cargar."); }//captura cualquier problema, como datos 
        // mal escritos en el archivo 
    }
}
