import java.util.Comparator;

public class TareaComparator implements Comparator<Tarea> {
    @Override
    public int compare(Tarea t1, Tarea t2) {
        // 1. Comparar por Prioridad (usando el orden natural del Enum: ALTA < MEDIA < BAJA)
        int resPrioridad = t1.getPrioridad().compareTo(t2.getPrioridad());
        
        if (resPrioridad != 0) {
            return resPrioridad;
        }
        
        // 2. Si la prioridad es igual, comparar por Fecha de Vencimiento
        return t1.getFechaVencimiento().compareTo(t2.getFechaVencimiento());
    }
}
