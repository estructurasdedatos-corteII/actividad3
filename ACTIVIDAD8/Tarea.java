package ACTIVIDAD8;
import java.time.LocalDate;

public class  Tarea{
    public enum Prioridad { ALTA, MEDIA, BAJA }

    private String descripcion;
    private LocalDate fecha;
    private Prioridad prioridad;
    private boolean completada;

    public Tarea(String descripcion, LocalDate fecha, Prioridad prioridad) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.prioridad = prioridad;
        this.completada = false;
    }

    // Getters y Setters necesarios
    public String getDescripcion() { return descripcion; }
    public LocalDate getFecha() { return fecha; }
    public Prioridad getPrioridad() { return prioridad; }
    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }

    // Convierte el objeto a una línea de texto para el archivo CSV
    public String toCSV() {
        return descripcion + "," + fecha + "," + prioridad + "," + completada;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-15s | Vence: %s | Prioridad: %s", 
            completada ? "X" : " ", descripcion, fecha, prioridad);
    }
}


