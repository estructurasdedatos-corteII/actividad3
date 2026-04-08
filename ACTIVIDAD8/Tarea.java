import java.time.LocalDate;

enum Prioridad { ALTA, MEDIA, BAJA }

public class Tarea {
    private String descripcion;
    private LocalDate fechaVencimiento;
    private Prioridad prioridad;
    private boolean completada;

    public Tarea(String descripcion, LocalDate fecha, Prioridad prioridad) {
        this.descripcion = descripcion;
        this.fechaVencimiento = fecha;
        this.prioridad = prioridad;
        this.completada = false;
    }

    // Getters y Setters
    public String getDescripcion() { return descripcion; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public Prioridad getPrioridad() { return prioridad; }
    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean estado) { this.completada = estado; }

    @Override
    public String toString() {
        return String.format("[%s] %s - Vence: %s | Prioridad: %s", 
            completada ? "X" : " ", descripcion, fechaVencimiento, prioridad);
    }

    public String toCSV() {
        return descripcion + "," + fechaVencimiento + "," + prioridad + "," + completada;
    }
}
