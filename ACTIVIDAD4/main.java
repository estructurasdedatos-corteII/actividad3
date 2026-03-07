import java.util.Scanner;
import java.util.linketlist;
// --- CLASES BASE ---


public class Nodo {//atributos
    Producto prod;
    Nodo siguiente; //es un puntero o referencia dentro de una estructura de datos 
    // (como listas enlazadas, árboles o grafos) que 
    // conecta el nodo actual con el próximo elemento en la secuencia.

    public Nodo(Producto dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

//  LÓGICA DE LISTA Y PERSISTENCIA

class ListaSimple {
    private Nodo cabeza;//es el primer nodo de una lista enlazada o la referencia al mismo sirviendo como el punto de entrada 
    // principal para acceder recorrer o manipular la estructura

    public void insertarFinal(Producto p) {
        Nodo nuevo = new Nodo(p);
        if (cabeza == null) {// Si la cabeza es null (la lista no tiene nada) el nuevo nodo se convierte en la cabeza
            cabeza = nuevo;
        } else {
            Nodo temp = cabeza;//Se crea un nodo auxiliar llamado "temp" que empieza en la cabeza.

            while (temp.siguiente != null) temp = temp.siguiente;
            temp.siguiente = nuevo;// El código recorre la lista saltando de nodo en nodo (temp.siguiente) 
            // hasta encontrar el último (aquel el cual el siguiente sea null).
        }
    }

    public void eliminarPorId(int id) {
        if (cabeza == null) return;//Si la lista está vacía (cabeza == null) no hace nada y sale de la función.
        if (cabeza.dato.id == id) {
            cabeza = cabeza.siguiente;// (La Cabeza) revisa si el producto a eliminar es el primero si el id coincide
            //  simplemente mueve la cabeza al siguiente nodo "saltándose" el primero.
            return;
        }
        Nodo actual = cabeza;// Si no es el primero empieza a recorrer la lista con un nodo llamado actual
        while (actual.siguiente != null && actual.siguiente.dato.id != id) {
            actual = actual.siguiente;
        }
        if (actual.siguiente != null) {//Si encontró el id asi se realiza la desconexión
            actual.siguiente = actual.siguiente.siguiente;
        }//Esto hace que el nodo actual apunte al "nieto"dejando el nodo del id fuera de la estructura 
    }

    // ORDENAMIENTO POR PUNTEROS (Insertion Sort)
    public void ordenarPorPrecio() {
        if (cabeza == null || cabeza.siguiente == null) return;

        Nodo listaOrdenada = null;// Crea una nueva variable que servirá como una "lista auxiliar" 
        // donde irá metiendo los productos en el orden correcto de menor a mayor precio
        Nodo actual = cabeza;// Empieza a recorrer tu lista original desde el primer nodo

        while (actual != null) {
            Nodo siguienteNodo = actual.siguiente; // Guardar el resto de la lista
            
            // Insertar 'actual' en la nueva lista ordenada
            if (listaOrdenada == null || actual.dato.precio < listaOrdenada.dato.precio) {//está vacía o si el precio del producto actual 
            // es menor que el del primero de la lista ordenada
                actual.siguiente = listaOrdenada;
                listaOrdenada = actual;
            } else {
                Nodo temp = listaOrdenada;//Si el producto no es el más barato, tiene que buscar su posición intermedia o final
                while (temp.siguiente != null && temp.siguiente.dato.precio < actual.dato.precio) {
                    temp = temp.siguiente;
                }//Recorre la lista ordenada usando un nodo temp mientras el precio del siguiente sea menor al que quieres insertar
                //  Se detiene justo cuando encuentra a alguien más caro o el final.
                actual.siguiente = temp.siguiente;//El nuevo nodo apunta al que sigue
                temp.siguiente = actual;//(El nodo anterior apunta ahora al nuevo
            }//El cierre del ciclo
            actual = siguienteNodo;//El código recupera el resto de la lista original que guardaste 
            // antes para procesar el siguiente producto.
        }
        cabeza = listaOrdenada;//Al terminar todo el proceso actualizas la cabeza original de tu clase 
        // para que apunte a la nueva lista ya organizada.

    }

    public void mostrar() {
        if (cabeza == null) System.out.println("Inventario vacío.");//Antes de  leer nada verifica si la lista existe mira si esta vacida
        Nodo temp = cabeza;//Se crea una variable llamada temp que sirve como un "cursor" o "puntero". Empieza apuntando al primer nodo de la lista.
        while (temp != null) {//Este bucle se repetirá mientras temp esté parado sobre un nodo real. 
        // Cuando llegue al final de la lista, temp valdrá null y el ciclo se detendrá.
            System.out.println(temp.dato);//: Imprime los datos del producto actual
            temp = temp.siguiente;//Esta es la parte más importante. Mueve el cursor al siguiente eslabón de la cadena
            }
        }
    }

    public void guardarCSV(String archivo) {//abrir el archivo
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {//segura que el archivo se cierre automáticamente al terminar
            Nodo temp = cabeza;// recore lista
            while (temp != null) {
                pw.println(temp.dato.toCSV());// devolvemos  los datos del producto en una sola línea
                temp = temp.siguiente;//hay se mueve el cursor al próximo producto
        } catch (IOException e) {
            System.out.println("Error al guardar.");
        }
    }

    public void cargarCSV(String archivo) {
        File file = new File(archivo);
        if (!file.exists()) return;//Si el archivo no existe el  termina
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {// este lo utilizamos para abrir el archivo
            String linea;
            while ((linea = br.readLine()) != null) {//lee el documento renglón por renglón hasta llegar al final.
                String[] d = linea.split(",");//Corta la línea cada vez que encuentra una coma y guarda 
                // los trozos en un arreglo de textos llamado d.

                if (d.length == 3) {//Se asegura de que la línea tenga exactamente los 3 datos esperados (ID, Nombre, Precio).
                    insertarFinal(new Producto(Integer.parseInt(d[0]), d[1], Double.parseDouble(d[2])));
                }//Como el archivo es puro texto, usa Integer.parseInt para el ID y Double.parseDouble para
                //  el precio para convertirlos de nuevo a números.
                //insertarFinal: Crea un nuevo objeto Producto con esos datos y lo engancha al final de la lista enlazada actual.
            }
        } catch (Exception e) {//ptura cualquier problema, como datos mal escritos en el archivo 
            System.out.println("Error al cargar datos.");
        }
    }
}

// --- CLASE PRINCIPAL (MAIN) ---

public class Main {
    public static void main(String[] args) {
        ListaSimple lista = new ListaSimple();//Crea una instancia 
        Scanner sc = new Scanner(System.in);//Captura lo que el usuario escribe por teclado
        String archivo = "inventario.csv";

        lista.cargarCSV(archivo);//Al iniciar, el programa busca automáticamente si ya existen 
        // datos guardados para cargarlos en la lista.


        int op = 0;
        while (op != 5) {//Dependiendo del número que elijas (1-5), llama al método correspondiente de tu clase
            System.out.println(" SISTEMA DE INVENTARIO (LISTAS ENLAZADAS)");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Eliminar Producto (ID)");
            System.out.println("3. Ordenar por Precio (Punteros)");
            System.out.println("4. Ver Inventario");
            System.out.println("5. Salir y Guardar");
            System.out.print("Selección: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("ID: "); int id = sc.nextInt();
                    System.out.print("Nombre: "); String nom = sc.next();
                    System.out.print("Precio: "); double pre = sc.nextDouble();
                    lista.insertarFinal(new Producto(id, nom, pre));
                    break;
                case 2:
                    System.out.print("ID a borrar: ");
                    lista.eliminarPorId(sc.nextInt());
                    break;
                case 3:
                    lista.ordenarPorPrecio();
                    System.out.println("Lista reestructurada por precio.");
                    break;
                case 4:
                    lista.mostrar();
                    break;
                case 5:
                    lista.guardarCSV(archivo);
                    System.out.println("Archivo CSV actualizado. Adiós.");
                    break;
            }
        }
        sc.close();//Cierra el lector de teclado 
    }
}