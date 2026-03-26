package ACTIVIDAD6;
 import java.util.Random;

// 1. MODELO DE DATOS
class Jugador {
    String nombre;
    int puntos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }
}

class Nodo {
    Jugador jugador;
    Nodo siguiente;

    public Nodo(Jugador j) {
        this.jugador = j;
    }
}

// 2. LISTA CIRCULAR (El Anillo)
class ListaCircular {
    private Nodo actual;
    private int tamaño = 0;

    public void insertar(Jugador j) {
        Nodo nuevo = new Nodo(j);
        if (actual == null) {
            actual = nuevo;
            actual.siguiente = actual; // Se apunta a sí mismo
        } else {
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamaño++;
    }

    public void avanzar() {
        if (actual != null) actual = actual.siguiente;
    }

    // 3. LÓGICA DE ELIMINACIÓN (Puentear el nodo)
    public void eliminarActual() {
        if (actual == null || tamaño == 0) return;

        if (tamaño == 1) {
            actual = null;
        } else {
            // Buscamos el nodo ANTERIOR al actual para poder puentear
            Nodo anterior = actual;
            while (anterior.siguiente != actual) {
                anterior = anterior.siguiente;
            }
            
            // El anterior ahora apunta al que seguía después del actual
            anterior.siguiente = actual.siguiente;
            System.out.println("!!! Jugador " + actual.jugador.nombre + " eliminado de la ronda.");
            actual = anterior.siguiente; // El turno pasa al siguiente automáticamente
        }
        tamaño--;
    }

    public Jugador getJugadorActual() { return actual != null ? actual.jugador : null; }
    public int getTamaño() { return tamaño; }
}

// 4. SIMULADOR DEL JUEGO
public class JuegoLaRonda {
    public static void main(String[] args) {
        ListaCircular ronda = new ListaCircular();
        Random rnd = new Random();

        // Inicializar jugadores
        String[] nombres = {"Ana", "Beto", "Carla", "David", "Elena"};
        for (String n : nombres) ronda.insertar(new Jugador(n));

        System.out.println("--- QUE COMIENCE LA RONDA ---");

        while (ronda.getTamaño() > 1) {
            Jugador j = ronda.getJugadorActual();
            
            // Simular evento aleatorio: sumar puntos
            int puntosTurno = rnd.nextInt(10) + 1;
            j.puntos += puntosTurno;
            
            System.out.printf("Turno de %s | Puntos totales: %d%n", j.nombre, j.puntos);

            // Regla de eliminación: si supera los 25 puntos, queda fuera
            if (j.puntos > 25) {
                ronda.eliminarActual(); 
            } else {
                ronda.avanzar();
            }

            // Pausa breve para visualizar la consola
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }

        System.out.println("\n🏆 EL GANADOR ES: " + ronda.getJugadorActual().nombre);
    }
}

