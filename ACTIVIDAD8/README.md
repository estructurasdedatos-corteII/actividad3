# actividad8
Análisis Comparativo: LinkedList vs. ArrayList en Gestores de Tareas
1. Eficiencia Algorítmica (Notación Big O)
En este proyecto, la elección de LinkedList sobre ArrayList se fundamenta en la naturaleza de las operaciones principales de un gestor de tareas dinámico:
Operación	LinkedList (Elegida)	ArrayList	Explicación
Insertar al inicio (addFirst)	O(1)	O(n)	LinkedList solo cambia punteros; ArrayList debe desplazar todos los elementos existentes.
Eliminar por condición (removeIf)	O(n)	O(n)	Ambas requieren recorrer la lista, pero LinkedList no requiere reindexar el arreglo tras borrar.
Acceso por índice (get)	O(n)	O(1)	ArrayList es superior aquí. Sin embargo, en un To-Do List, priorizamos la edición y reubicación sobre la lectura aleatoria.
Ordenamiento (Collections.sort)	O(n log n)	O(n log n)	Ambas usan variaciones de Timsort, aunque LinkedList convierte internamente a array para ordenar.
2. Gestión de Memoria
LinkedList: Cada elemento ("Nodo") consume más memoria porque no solo guarda la Tarea, sino también dos referencias adicionales (punteros al nodo anterior y siguiente).
ArrayList: Es más compacto, pero sufre de "sobre-asignación". Cuando el arreglo se llena, Java debe crear uno nuevo un 50% más grande y copiar todos los datos, lo que genera picos de uso de memoria y CPU.
3. Justificación de la Elección
Se seleccionó java.util.LinkedList para este escenario específico debido a que:
Flexibilidad de Reubicación: Las aplicaciones de tareas suelen permitir "anclar" tareas al principio. La operación addFirst() es instantánea en una lista doblemente enlazada.
Eliminación Dinámica: Al limpiar tareas completadas, LinkedList gestiona mejor la eliminación de nodos dispersos sin necesidad de mover bloques de memoria contiguos.
Cumplimiento de API: Se demuestra el dominio de métodos específicos de la interfaz Deque (Double Ended Queue) que implementa LinkedList.
