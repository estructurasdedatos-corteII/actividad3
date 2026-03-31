import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorTareas gestor = new GestorTareas();
        gestor.cargarCSV();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- GESTOR DE TAREAS (LinkedList) ---");
            gestor.mostrarTareas();
            System.out.println("\n1. Agregar al inicio\n2. Agregar al final\n3. Ordenar (Prioridad/Fecha)\n4. Marcar completada\n5. Eliminar completadas\n6. Salir");
            System.out.print("Seleccione: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1,2 -> {
                    System.out.print("Descripción: "); String desc = sc.nextLine();
                    System.out.print("Prioridad (ALTA, MEDIA, BAJA): "); 
                    Tarea.Prioridad p = Tarea.Prioridad.valueOf(sc.nextLine().toUpperCase());
                    Tarea nueva = new Tarea(desc, LocalDate.now().plusDays((int)(Math.random()*10)), p);
                    if (opcion == 1) gestor.agregarAlInicio(nueva); else gestor.agregarAlFinal(nueva);
                }
                case 3 -> gestor.ordenar();
                case 4 -> {
                    System.out.print("Índice de la tarea: ");
                    gestor.marcarCompletada(sc.nextInt());
                }
                case 5 -> gestor.eliminarCompletadas();
                case 6 -> gestor.guardarCSV();
            }
        } while (opcion != 6);
    }
}
