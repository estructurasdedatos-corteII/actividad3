import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListaSimple inventario = new ListaSimple();
        GestorArchivo.cargar(inventario);
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- TIENDA (PUNTEROS) ---\n1. Agregar\n2. Mostrar\n3. Ordenar por Precio\n4. Eliminar por ID\n5. Salir");
            opcion = sc.nextInt();
            switch(opcion) {
                case 1:
                    System.out.print("ID: "); int id = sc.nextInt();
                    System.out.print("Nombre: "); String n = sc.next();
                    System.out.print("Precio: "); double p = sc.nextDouble();
                    inventario.insertarFinal(new Producto(id, n, p));
                    break;
                case 2: inventario.mostrar(); break;
                case 3: inventario.ordenarPorPrecio(); System.out.println("Ordenado!"); break;
                case 4:
                    System.out.print("ID a borrar: ");
                    int idB = sc.nextInt();
                    if(inventario.eliminarPorId(idB)) System.out.println("Eliminado.");
                    break;
                case 5: GestorArchivo.guardar(inventario); break;
            }
        } while (opcion != 5);
    }
}
