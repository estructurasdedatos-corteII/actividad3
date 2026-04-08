import java.util.Scanner;

// 1. Clase Nodo: Representa cada elemento en la pila
class Nodo {
    String contenido;
    int linea;
    int columna;
    Nodo siguiente;

    public Nodo(String contenido, int linea, int columna) {
        this.contenido = contenido;
        this.linea = linea;
        this.columna = columna;
        this.siguiente = null;
    }
}

// 2. Clase Pila: Implementación manual (LIFO)
class Pila {
    private Nodo cima;

    public void push(String contenido, int linea, int columna) {
        Nodo nuevo = new Nodo(contenido, linea, columna);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public Nodo pop() {
        if (isEmpty()) return null;
        Nodo temp = cima;
        cima = cima.siguiente;
        return temp;
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
        System.out.println("--- Verificador Avanzado (Escribe 'FIN' para procesar) ---");
        
        StringBuilder texto = new StringBuilder();
        String lineaInput;
        while (!(lineaInput = sc.nextLine()).equals("FIN")) {
            texto.append(lineaInput).append("\n");
        }

        verificar(texto.toString());
    }

    public static void verificar(String entrada) {
        Pila pila = new Pila();
        String[] lineas = entrada.split("\n");

        for (int i = 0; i < lineas.length; i++) {
            String fila = lineas[i];
            for (int j = 0; j < fila.length(); j++) {
                char c = fila.charAt(j);
                int numLinea = i + 1;
                int numCol = j + 1;

                // Manejo de Paréntesis, Corchetes y Llaves
                if (c == '(' || c == '[' || c == '{') {
                    pila.push(String.valueOf(c), numLinea, numCol);
                } 
                else if (c == ')' || c == ']' || c == '}') {
                    if (pila.isEmpty()) {
                        System.err.printf("Error: Cierre '%c' sin apertura en Línea %d, Col %d\n", c, numLinea, numCol);
                        return;
                    }
                    Nodo top = pila.pop();
                    if (!esPareja(top.contenido.charAt(0), c)) {
                        System.err.printf("Error de Jerarquía: Se esperaba cierre para '%s' (de L:%d, C:%d) pero se halló '%c' en L:%d, C:%d\n", 
                                          top.contenido, top.linea, top.columna, c, numLinea, numCol);
                        return;
                    }
                }
                
                // Lógica básica para HTML (Ejemplo: <html>...</html>)
                if (c == '<') {
                    int cierreTag = fila.indexOf('>', j);
                    if (cierreTag != -1) {
                        String tag = fila.substring(j + 1, cierreTag);
                        if (!tag.startsWith("/")) { // Apertura
                            pila.push(tag, numLinea, numCol);
                        } else { // Cierre
                            String tagCierre = tag.substring(1);
                            if (pila.isEmpty()) {
                                System.err.printf("Error: Etiqueta </%s> sin apertura en L:%d, C:%d\n", tagCierre, numLinea, numCol);
                                return;
                            }
                            Nodo top = pila.pop();
                            if (!top.contenido.equals(tagCierre)) {
                                System.err.printf("Error: Se esperaba </%s> pero se halló </%s> en L:%d, C:%d\n", top.contenido, tagCierre, numLinea, numCol);
                                return;
                            }
                        }
                        j = cierreTag; // Saltar al final del tag
                    }
                }
            }
        }

        if (!pila.isEmpty()) {
            Nodo huérfano = pila.pop();
            System.err.printf("Error: El elemento '%s' abierto en Línea %d, Col %d nunca se cerró.\n", huérfano.contenido, huérfano.linea, huérfano.columna);
        } else {
            System.out.println("✅ ¡Sintaxis correcta! Todo está balanceado.");
        }
    }

    private static boolean esPareja(char op, char cl) {
        return (op == '(' && cl == ')') || (op == '[' && cl == ']') || (op == '{' && cl == '}');
    }
}
