# actividad8
Gestor de Tareas con Java LinkedList API
 Descripción del Proyecto
Esta aplicación es un sistema de gestión de tareas (To-Do List) desarrollado en Java que utiliza el Collections Framework de manera avanzada. El sistema permite administrar tareas con prioridades y fechas de vencimiento, garantizando la persistencia de datos mediante archivos CSV y optimizando la manipulación de elementos a través de la clase java.util.LinkedList.
 Análisis Crítico: LinkedList vs. ArrayList
Para este proyecto, se seleccionó LinkedList basándose en los siguientes criterios de eficiencia algorítmica y manejo de memoria:
1. Eficiencia Algorítmica (Notación Big O)
Inserciones/Eliminaciones en los extremos: LinkedList implementa la interfaz Deque, permitiendo operaciones addFirst(), addLast(), removeFirst() y removeLast() en tiempo constante O(1). En un ArrayList, insertar al inicio requiere desplazar todos los elementos existentes, lo que toma tiempo lineal O(n).
Eliminaciones dinámicas: Al usar Iterator o removeIf (como se solicita en la actividad), LinkedList es sumamente eficiente para eliminar elementos mientras se recorre la lista, ya que solo requiere la reasignación de punteros entre nodos adyacentes.
2. Uso de Memoria
LinkedList: Consume más memoria por elemento porque cada "Nodo" debe almacenar tres referencias: el objeto (la Tarea), el puntero al nodo anterior y el puntero al nodo siguiente.
ArrayList: Es más eficiente en uso de memoria contigua, pero sufre de sobrecarga cuando necesita redimensionar el arreglo interno (crear uno nuevo y copiar los datos).

Java 21 API: Uso de java.util.LinkedList, java.util.Collections y java.util.Comparator.
Programación Funcional: Implementación de predicados en removeIf().
Persistencia: Manejo de entrada/salida de datos (I/O) para archivos .csv.
Ordenamiento Compuesto: Comparator jerárquico (Prioridad -> Fecha).
Estructura del Código
Tarea.java: Clase de dominio con atributos, Enums y formato CSV.
TareaComparator.java: Lógica de ordenamiento personalizada.
GestorTareas.java: Motor de la aplicación que administra la LinkedList.
Main.java: Interfaz de consola para el usuario.
