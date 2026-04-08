import java.time.LocalDate;
import java.util.Scanner;

public class Maei {
    public static void maei(String[] args) {
        GestorTareas gestor = new GestorTareas();
        gestor.cargarCSV();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- GESTOR DE TAREAS ---");
            gestor.mostrarTareas();
            System.out.println("\n1. Agregar (Inicio)\n2. Agregar (Final)\n3. Ordenar (Prioridad/Fecha)\n4. Limpiar Completadas\n5. Guardar y Salir");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            if (opcion == 1 || opcion == 2) {
                System.out.print("Descripción: "); String desc = sc.nextLine();
                System.out.print("Prioridad (ALTA, MEDIA, BAJA): "); Prioridad p = Prioridad.valueOf(sc.next().toUpperCase());
                Tarea nueva = new Tarea(desc, LocalDate.now().plusDays(2), p);
                if (opcion == 1) gestor.agregarAlInicio(nueva);
                else gestor.agregarAlFinal(nueva);
            } else if (opcion == 3) gestor.ordenarTareas();
            else if (opcion == 4) gestor.eliminarCompletadas();

        } while (opcion != 5);

        gestor.guardarCSV();
        System.out.println("Datos sincronizados. ¡Adiós!");
    }
}
