package ACTIVIDAD4;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Random;

// 1. CLASE CLIENTE
class Cliente {
    int id;
    boolean esUrgente;
    int tiempoLlegada;
    int tiempoAtencion; // Segundos que requiere en ventanilla

    public Cliente(int id, boolean esUrgente, int tiempoLlegada) {
        this.id = id;
        this.esUrgente = esUrgente;
        this.tiempoLlegada = tiempoLlegada;
        this.tiempoAtencion = new Random().nextInt(5) + 3; // 3 a 8 segundos
    }
}

// 2. COLA FIFO MANUAL (Para clientes normales)
class NodoCliente {
    Cliente cliente;
    NodoCliente siguiente;
    public NodoCliente(Cliente c) { this.cliente = c; }
}

class ColaManual {
    private NodoCliente frente, fin;

    public void encolar(Cliente c) {
        NodoCliente nuevo = new NodoCliente(c);
        if (fin != null) fin.siguiente = nuevo;
        fin = nuevo;
        if (frente == null) frente = nuevo;
    }

    public Cliente desencolar() {
        if (frente == null) return null;
        Cliente c = frente.cliente;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return c;
    }

    public boolean estaVacia() { return frente == null; }
}

// 3. MOTOR DE SIMULACIÓN
public class SimuladorBanco {
    public static void main(String[] args) {
        // PriorityQueue con Comparator personalizado
        PriorityQueue<Cliente> colaPrioridad = new PriorityQueue<>(
            Comparator.comparing((Cliente c) -> !c.esUrgente) // Urgentes primero (false < true)
                      .thenComparingInt(c -> c.tiempoLlegada) // Luego por orden de llegada
        );

        ColaManual colaNormal = new ColaManual();
        int[] ventanillas = new int[3]; // 3 ventanillas, guardan el tiempo que les queda ocupadas
        int atendidos = 0;
        int tiempoTotalEspera = 0;
        Random rnd = new Random();

        System.out.println("--- Iniciando Simulación (60 segundos) ---");

        for (int t = 0; t < 60; t++) {
            // Llegada aleatoria de clientes (30% de probabilidad por segundo)
            if (rnd.nextDouble() < 0.3) {
                boolean urgente = rnd.nextBoolean();
                Cliente nuevo = new Cliente(atendidos + 1, urgente, t);
                if (urgente) colaPrioridad.add(nuevo);
                else colaNormal.encolar(nuevo);
                System.out.printf("[t:%d] Llegó Cliente %d (%s)%n", t, nuevo.id, urgente ? "URGENTE" : "Normal");
            }

            // Asignación a ventanillas
            for (int v = 0; v < ventanillas.length; v++) {
                if (ventanillas[v] <= 0) { // Ventanilla libre
                    Cliente proximo = null;
                    
                    // Prioridad absoluta a la cola de urgencias
                    if (!colaPrioridad.isEmpty()) proximo = colaPrioridad.poll();
                    else if (!colaNormal.estaVacia()) proximo = colaNormal.desencolar();

                    if (proximo != null) {
                        ventanillas[v] = proximo.tiempoAtencion;
                        tiempoTotalEspera += (t - proximo.tiempoLlegada);
                        atendidos++;
                        System.out.printf(" >> Ventanilla %d atiende Cliente %d (Espera: %ds)%n", v, proximo.id, (t - proximo.tiempoLlegada));
                    }
                } else {
                    ventanillas[v]--; // Reducir tiempo de ocupación
                }
            }
        }

        // Estadísticas
        System.out.println("\n--- RESULTADOS FINALIZADOS ---");
        System.out.println("Total atendidos: " + atendidos);
        System.out.printf("Tiempo promedio de espera: %.2f segundos%n", (double) tiempoTotalEspera / atendidos);
    }
}


