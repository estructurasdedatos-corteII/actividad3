# actividad3
# Verificador de Sintaxis Avanzado (Stacks) 

Proyecto desarrollado para la **Actividad Evaluable 3**. Implementación de una estructura de datos tipo Pila (LIFO) desde cero en Java para validar balanceo de símbolos.

## Tecnologías
- Java JDK 17+
- Git/GitHub

## Características
- **Pila Manual**: Implementación propia sin librerías externas.
- **Detección de Errores**: Reporta línea y columna exacta de discrepancias.
- **Multilínea**: Capaz de procesar fragmentos de código extensos.

##  Instrucciones de Ejecución
1. Clonar el repositorio: `git clone [URL-DE-TU-REPOSITO]`
2. Compilar: `javac src/*.java`
3. Ejecutar: `java src.Main`

## 📸 Casos de Prueba

| Caso | Resultado Esperado | Captura |
|------|-------------------|---------|
| `(a + b)` | Válido | [Imagen1] |
| `(a + b]` | Error Jerarquía (L1, C7) | [Imagen2] |