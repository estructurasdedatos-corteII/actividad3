package ACTIVIDAD8;

import java.util.LinkedList;
import java.util.Collections;

public class GestorTareas {
    private LinkedList<Tarea> listaTareas = new LinkedList<>();

    public void agregarAlInicio(Tarea t) {
        listaTareas.addFirst(t);
    }

    public void agregarAlFinal(Tarea t) {
        listaTareas.addLast(t);
    }

    public void ordenarTareas() {
        Collections.sort(listaTareas, new TareaComparator());
    }

    public void eliminarCompletadas() {
        // Uso de removeIf (Java 8+) como pide la guía

        listaTareas.removeIf(Tarea::isCompletada);
    }
}
