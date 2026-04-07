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

    public void cargarCSV() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cargarCSV'");
    }

    public void mostrarTareas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarTareas'");
    }

    public void marcarCompletada(int nextInt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'marcarCompletada'");
    }

    public Object guardarCSV() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardarCSV'");
    }
}
