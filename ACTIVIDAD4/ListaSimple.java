public class ListaSimple {// LÓGICA DE LISTA Y PERSISTENCIA
    
    Nodo cabeza;//es el primer nodo de una lista enlazada o la referencia al mismo sirviendo como el punto de entrada 
    // principal para acceder recorrer o manipular la estructura

    public void insertarFinal(Producto p) {
        Nodo nuevo = new Nodo(p);
        if (cabeza == null) {// Si la cabeza es null (la lista no tiene nada) el nuevo nodo se convierte en la cabeza
            cabeza = nuevo;
        } else {
            Nodo temp = cabeza;//Se crea un nodo auxiliar llamado "temp" que empieza en la cabeza.
            System.out.println("else insertarFinal");

            while (temp.siguiente != null){
                temp = temp.siguiente;
                System.out.println(temp.dato);
                System.out.println(temp.siguiente);
            }
             
            temp.siguiente = nuevo;// El código recorre la lista saltando de nodo en nodo (temp.siguiente) 
            // hasta encontrar el último (aquel el cual el siguiente sea null).
        }
    }

    public boolean eliminarPorId(int id) {
        if (cabeza == null) 
            return false;//Si la lista está vacía (cabeza == null) no hace nada y sale de la función.
        if (cabeza.dato.id == id) {
            cabeza = cabeza.siguiente;// (La Cabeza) revisa si el producto a eliminar es el primero si el id coincide
            //  simplemente mueve la cabeza al siguiente nodo "saltándose" el primero.
            System.out.println(cabeza.dato);
            return false;

        }
        Nodo actual = cabeza;// Si no es el primero empieza a recorrer la lista con un nodo llamado actual
        while (actual.siguiente != null && actual.siguiente.dato.id != id) {//que el siguente tenga id
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {//Si encontró el id asi se realiza la desconexión
            actual.siguiente = actual.siguiente.siguiente;
        }//Esto hace que el nodo actual apunte al "nieto"dejando el nodo del id fuera de la estructura 
        return false;
    }

    // ORDENAMIENTO POR PUNTEROS (Insertion Sort)
    public void ordenarPorPrecio() {
        if (cabeza == null || cabeza.siguiente == null) return;

        Nodo listaOrdenada = null;// Crea una nueva variable que servirá como una "lista auxiliar" 
        // donde irá metiendo los productos en el orden correcto de menor a mayor precio
        Nodo actual = cabeza;// Empieza a recorrer tu lista original desde el primer nodo

        while (actual != null) {
            Nodo siguienteNodo = actual.siguiente; // Guardas la dirección del próximo eslabón an
            // tes de hacerle cualquier cambio al nodo
            
            // Insertar 'actual' en la nueva lista ordenada
            if (listaOrdenada == null || actual.dato.precio < listaOrdenada.dato.precio) {//está vacía o si el precio del producto actual 
            // es menor que el del primero de la lista ordenada
                actual.siguiente = listaOrdenada;
                listaOrdenada = actual;
            } else {
                Nodo temp = listaOrdenada;//Si el producto no es el más barato, tiene que buscar su posición intermedia o final
                while (temp.siguiente != null && temp.siguiente.dato.precio < actual.dato.precio) {
                    temp = temp.siguiente;
                }//Recorre la lista ordenada usando un nodo temp mientras el precio del siguiente sea menor al que quieres insertar
                //  Se detiene justo cuando encuentra a alguien más caro o el final.
                actual.siguiente = temp.siguiente;//El nuevo nodo apunta al que sigue
                temp.siguiente = actual;//(El nodo anterior apunta ahora al nuevo
            }//El cierre del ciclo
            actual = siguienteNodo;//El código recupera el resto de la lista original que guardaste 
            // antes para procesar el siguiente producto.
        }
        cabeza = listaOrdenada; //Al terminar todo el proceso actualizas la cabeza original de tu clase 
        // para que apunte a la nueva lista ya organizada.

    }

    public void mostrar() {
        if (cabeza == null) System.out.println("Inventario vacío.");//Antes de  leer nada verifica si la lista existe mira si esta vacida
        Nodo temp = cabeza;//Se crea una variable llamada temp que sirve como un "cursor" o "puntero". Empieza apuntando al primer nodo de la lista.
        while (temp != null) {//garantiza que el siguente nodo o sino llega a su fin 
            System.out.println(temp.dato);//: Imprime los datos del producto actual
            temp = temp.siguiente;//Esta es la parte más importante. Mueve el cursor al siguiente nodo de la lista
            }
        }
    }

    

