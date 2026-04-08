import java.util.Scanner;

public class ConsolaInterativa {
    public static void main(String[] args) {
        HistorialEditor historial = new HistorialEditor();
        Scanner scanner = new Scanner(System.in);
        String entrada;

        System.out.println("=== EDITOR DE TEXTO JAVA (LISTA DOBLE) ===");
        System.out.println("Instrucciones: Escribe texto para guardar estado.");
        System.out.println("Comandos: :u (Undo), :r (Redo), :q (Salir)");

        while (true) {
           
            System.out.println("CONTENIDO: " + historial.getContenidoActual());
            System.out.print("Entrada > ");
            entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase(":q")) {
                System.out.println("Saliendo del editor...");
                break;
            } else if (entrada.equalsIgnoreCase(":u")) {
                if (!historial.deshacer()) {
                    System.out.println("(!) No hay más cambios para deshacer.");
                }
            } else if (entrada.equalsIgnoreCase(":r")) {
                if (!historial.rehacer()) {
                    System.out.println("(!) No hay cambios para rehacer.");
                }
            } else {
                // Cualquier otra entrada se guarda como un nuevo estado
                historial.agregarEstado(entrada);
            }
        }
        scanner.close();
    }
}
