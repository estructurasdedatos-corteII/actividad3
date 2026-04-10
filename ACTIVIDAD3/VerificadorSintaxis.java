import java.util.Scanner;

// 1. Clase Nodo: Almacena el símbolo y su ubicación exacta
class Nodo {
    String valor;
    int linea;
    int columna;
    Nodo siguiente;

    public Nodo(String valor, int linea, int columna) {
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
        this.siguiente = null;
    }
}

// 2. Clase Pila: Implementación manual LIFO
class Pila {
    private Nodo cima;

    public Pila() {
        this.cima = null;
    }

    public void push(String valor, int linea, int columna) {
        Nodo nuevo = new Nodo(valor, linea, columna);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public Nodo pop() {
        if (isEmpty()) throw new RuntimeException("Error: Pila vacía");
        Nodo aux = cima;
        cima = cima.siguiente;
        return aux;
    }

    public Nodo peek() {
        return cima;
    }

    public boolean isEmpty() {
        return cima == null;
    }
}

// 3. Lógica del Verificador
public class VerificadorSintaxis {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Verificador de Sintaxis (Matemática y HTML) ---");
        System.out.println("Ingrese el texto a evaluar (Escriba 'FIN' en una línea nueva para terminar):");
        
        StringBuilder textoTotal = new StringBuilder();
        String lineaLeida;
        while (!(lineaLeida = sc.nextLine()).equals("FIN")) {
            textoTotal.append(lineaLeida).append("\n");
        }

        verificar(textoTotal.toString());
    }

    public static void verificar(String codigo) {
        Pila pila = new Pila();
        String[] lineas = codigo.split("\n");

        for (int i = 0; i < lineas.length; i++) {
            String fila = lineas[i];
            for (int j = 0; j < fila.length(); j++) {
                char c = fila.charAt(j);
                String actual = String.valueOf(c);

                // Manejo de Apertura
                if (esApertura(c)) {
                    pila.push(actual, i + 1, j + 1);
                } 
                // Manejo de Cierre
                else if (esCierre(c)) {
                    if (pila.isEmpty()) {
                        System.err.printf("Error: Cierre '%c' sin apertura en Línea %d, Col %d\n", c, i + 1, j + 1);
                        return;
                    }
                    
                    Nodo superior = pila.pop();
                    if (!combinan(superior.valor.charAt(0), c)) {
                        System.err.printf("Error de Jerarquía: Se esperaba cierre para '%s' (Línea %d) pero se encontró '%c' en Línea %d, Col %d\n", 
                                          superior.valor, superior.linea, c, i + 1, j + 1);
                        return;
                    }
                }
                
                // Nota: Para HTML avanzado se requeriría un parser de String (ej. <tag>), 
                // pero la lógica de la pila sigue el mismo principio de "push" al abrir y "pop" al cerrar.
            }
        }

        if (!pila.isEmpty()) {
            Nodo huérfano = pila.pop();
            System.err.printf("Error: Apertura '%s' en Línea %d, Col %d nunca fue cerrada.\n", 
                              huérfano.valor, huérfano.linea, huérfano.columna);
        } else {
            System.out.println("¡Éxito! La sintaxis está correctamente balanceada.");
        }
    }

    private static boolean esApertura(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean esCierre(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private static boolean combinan(char a, char c) {
        return (a == '(' && c == ')') || (a == '[' && c == ']') || (a == '{' && c == '}');
    }
}
