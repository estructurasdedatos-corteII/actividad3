import java.util.Scanner;

// 1. CLASE NODO: Almacena el dato y su ubicación exacta
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

// 2. CLASE PILA: Implementación manual (LIFO)
class Pila {
    private Nodo cima;

    public void push(String dato, int l, int c) {
        Nodo nuevo = new Nodo(dato, l, c);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public Nodo pop() {
        if (isEmpty()) return null;
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

// 3. LÓGICA DEL VERIFICADOR
public class VerificadorSintaxis {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Verificador de Sintaxis Avanzado ---");
        System.out.println("Pega tu código/expresión (Escribe 'FIN' en una línea nueva para terminar):");

        StringBuilder texto = new StringBuilder();
        String linea;
        while (!(linea = sc.nextLine()).equals("FIN")) {
            texto.append(linea).append("\n");
        }

        validar(texto.toString());
    }

    public static void validar(String entrada) {
        Pila pila = new Pila();
        int nLinea = 1;
        int nColumna = 1;

        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);

            // Manejo de coordenadas
            if (c == '\n') {
                nLinea++;
                nColumna = 1;
                continue;
            }

            
            if (c == '(' || c == '[' || c == '{') {
                pila.push(String.valueOf(c), nLinea, nColumna);
            } 
            else if (c == ')' || c == ']' || c == '}') {
                if (pila.isEmpty()) {
                    System.err.printf("Error: Cierre '%c' sin apertura en Línea %d, Columna %d%n", c, nLinea, nColumna);
                    return;
                }
                char apertura = pila.pop().contenido.charAt(0);
                if (!esPareja(apertura, c)) {
                    System.err.printf("Error de Jerarquía: Se esperaba cierre para '%c', pero se halló '%c' en L:%d, C:%d%n", apertura, c, nLinea, nColumna);
                    return;
                }
            }

            
            if (c == '<') {
                int cierreTag = entrada.indexOf('>', i);
                if (cierreTag != -1) {
                    String tagCompleta = entrada.substring(i, cierreTag + 1);
                    procesarTag(tagCompleta, pila, nLinea, nColumna);
                    i = cierreTag; // Saltar el cursor al final de la etiqueta
                    nColumna += tagCompleta.length() - 1;
                }
            }
            nColumna++;
        }

        if (!pila.isEmpty()) {
            Nodo huérfano = pila.pop();
            System.err.printf("Error: Elemento '%s' abierto en L:%d, C:%d nunca se cerró.%n", huérfano.contenido, huérfano.linea, huérfano.columna);
        } else {
            System.out.println("¡ÉXITO! El código está correctamente balanceado.");
        }
    }

    private static boolean esPareja(char a, char b) {
        return (a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}');
    }

    private static void procesarTag(String tag, Pila p, int l, int c) {
        if (tag.startsWith("</")) { // Es cierre
            String nombreCierre = tag.replace("/", "");
            if (p.isEmpty() || !p.peek().contenido.equals(nombreCierre)) {
                System.err.printf("Error HTML: Cierre %s inesperado en L:%d, C:%d%n", tag, l, c);
                System.exit(0);
            }
            p.pop();
        } else if (!tag.endsWith("/>")) { 
            p.push(tag, l, c);
        }
    }
}

    

