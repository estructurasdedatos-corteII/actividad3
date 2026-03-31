# actividad7
# Editor de Texto con Historial (Lista Doblemente Enlazada)

Este proyecto implementa un sistema de gestión de estados para un editor de texto por línea de comandos utilizando una **Lista Doblemente Enlazada** personalizada el sistema permite navegar por el historial de cambios mediante operaciones de deshacer (*Undo*) y rehacer (*Redo*)

##  Conceptos Técnicos Implementados

### 1. Lista Doblemente Enlazada
A diferencia de una lista simple cada nodo en este sistema mantiene dos referencias:
- `anterior`: Apunta al estado previo del documento (Undo)
- `siguiente`: Apunta al estado posterior del documento (Redo)

### 2. Algoritmo de Truncamiento (Punto Crítico)
Siguiendo la lógica de aplicaciones modernas como Photoshop o VS Code el historial no es lineal si se realizan cambios en el pasado.
- **Lógica:** Si el usuario retrocede en el historial (`undo`) y realiza una nueva escritura, el "futuro" que existía anteriormente se vuelve obsoleto.
- **Implementación:** El puntero `actual.siguiente` se reasigna al nuevo nodo rompiendo el enlace con la rama anterior. En Java el *Garbage Collector* libera automáticamente la memoria de esos nodos huérfanos.

##  Comandos del Editor
Una vez ejecutado el programa, puedes interactuar con los siguientes comandos:


| Comando | Acción |
| :--- | :--- |
| `texto libre` | Guarda cualquier entrada como un nuevo estado en el historial. |
| `:u` | **Undo:** Retrocede al estado anterior. |
| `:r` | **Redo:** Avanza al estado siguiente (si existe). |
| `:q` | **Quit:** Cierra el programa. |