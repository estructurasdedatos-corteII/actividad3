# actividad4
## Simulador de Atención Bancaria: FIFO vs. PriorityQueue
## Descripción del Proyecto
Este simulador modela el flujo de atención en una sucursal bancaria utilizando dos estructuras de datos fundamentales
*Cola FIFO (Manual): Implementada desde cero para gestionar clientes de categoría Estándar.
*PriorityQueue (Java Collections): Utilizada para gestionar clientes Urgentes, priorizando su atención independientemente del orden de llegada.
El sistema simula múltiples ventanillas concurrentes y genera reportes de eficiencia al finalizar la ejecución.
Conceptos Teóricos

1. Cola FIFO (First-In, First-Out)
Es una estructura lineal donde el primer elemento en entrar es el primero en salir. En este proyecto, se implementó manualmente usando nodos y referencias (frente y final), garantizando operaciones de encolar y desencolar en tiempo constante 

2. PriorityQueue (Cola de Prioridad)
*A diferencia de la FIFO estándar, los elementos se retiran según su prioridad.
*Funcionamiento: Se utilizó un Comparator personalizado.
*Lógica de Prioridad
*Tipo de Cliente (Urgente > Estándar).
*Tiempo de llegada (a igual tipo, el que llegó primero tiene prioridad)