import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class GestorTareas {
    private LinkedList<Tarea> listaTareas = new LinkedList<>();
    private final String ARCHIVO = "tareas.csv";

    public void agregarAlInicio(Tarea t) { listaTareas.addFirst(t); }
    public void agregarAlFinal(Tarea t) { listaTareas.addLast(t); }
    
    public void eliminarCompletadas() {
        // Uso de removeIf (Eficiente en LinkedList)
        listaTareas.removeIf(Tarea::isCompletada);
    }

    public void ordenarTareas() {
        Collections.sort(listaTareas, new TareaComparator());
    }

    public void mostrarTareas() {
        if (listaTareas.isEmpty()) System.out.println("Lista vacía.");
        for (int i = 0; i < listaTareas.size(); i++) {
            System.out.println(i + ". " + listaTareas.get(i));
        }
    }

    public void guardarCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Tarea t : listaTareas) {
                writer.println(t.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public void cargarCSV() {
        if (!Files.exists(Paths.get(ARCHIVO))) return;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Tarea t = new Tarea(datos[0], LocalDate.parse(datos[1]), Prioridad.valueOf(datos[2]));
                t.setCompletada(Boolean.parseBoolean(datos[3]));
                listaTareas.add(t);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar: " + e.getMessage());
        }
    }
}
