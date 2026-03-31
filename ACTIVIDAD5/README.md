# actividad5
Gestión de Inventario - Lista Enlazada Simple
Este proyecto implementa un sistema de inventario utilizando una Lista Simplemente Enlazada (Singly Linked List) construida desde cero en Java. El enfoque principal es la manipulación directa de nodos y punteros para el ordenamiento y la persistencia de datos.
 Funcionalidades (CRUD)
Insertar: Al inicio, al final o en una posición específica.
Buscar: Por ID o por Nombre del producto.
Eliminar: Localización y reasignación de punteros por ID.
Ordenar: Reestructuración física de la lista (cambio de referencias next).
Persistencia: Carga y guardado automático en formato CSV.
 Estructura del Proyecto
Producto.java: Modelo de datos (ID, Nombre, Precio, Cantidad).
Nodo.java: Estructura que envuelve al objeto Producto y la referencia al siguiente nodo.
ListaSimple.java: Lógica de control de flujo de punteros.
InventarioApp.java: Interfaz de consola (Menú interactivo).
datos_inventario.csv: Archivo plano para la persistencia.
 Justificación del Algoritmo de Ordenamiento
Para este proyecto se seleccionó el algoritmo de Ordenamiento de Burbuja (Bubble Sort) adaptado para enlaces.
¿Por qué este algoritmo?
A diferencia de un arreglo, en una lista enlazada no tenemos acceso indexado rápido (



). El intercambio de nodos físicos (no de sus datos internos) garantiza que:
No se use memoria extra creando copias de objetos.
Se cumpla la restricción de no usar colecciones de Java (como ArrayList o Sort).
El proceso sea puramente de reasignación de punteros siguiente.
Nota técnica: Durante el intercambio, se mantiene una referencia al nodoAnterior para asegurar que la cadena no se rompa al mover un nodo hacia adelante o atrás.
 Manual de Usuario
Ejecución: Corre la clase InventarioApp.
Carga Inicial: Al arrancar, el programa busca el archivo .csv y reconstruye la lista en memoria.
Menú:
Opción 1: Agregar producto (Se solicita ID único).
Opción 2: Eliminar (Se reconecta el puntero anterior con el siguiente del eliminado).
Opción 3: Ordenar (Elige entre ID, Nombre o Precio).
Guardar: Al salir del programa (Opción Salir), la lista se recorre nodo a nodo para sobrescribir el archivo .csv.