import java.util.Comparator;

public class TareaComparator implements Comparator<Tarea> {
    @Override
    
    public int compare(Tarea t1, Tarea t2) {
        // Primero por prioridad
        int resPrioridad = t1.getPrioridad().compareTo(t2.getPrioridad());
        if (resPrioridad != 0) return resPrioridad;
        
        // Si la prioridad es igual, por fecha
        return t1.getFechaVencimiento().compareTo(t2.getFechaVencimiento());
    }
}


