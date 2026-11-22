# Calculadora de Matrices — TF Grupo 06

Aplicación de consola en Java para operar con matrices:
suma/resta encadenada, producto por escalar, determinante 2x2 e inversa 2x2.

## Arquitectura

```
src/
├── TF_Grupo06.java      # Punto de entrada (inyección de dependencias)
├── modelo/              # Lógica matemática pura: Matriz, Signo (Strategy)
├── ui/                  # Consola, cajas ANSI, vista de matrices, lector con validación
├── operaciones/         # Operacion (Command), OperacionBase (Template Method) y las 5 opciones
└── app/                 # Menu principal
```

Principios y patrones: SRP, OCP, DIP · Command, Strategy, Template Method.

**Agregar una operación nueva:** crear una clase que extienda `OperacionBase` y añadirla a la lista en `TF_Grupo06.java`.

## Requisitos

- JDK 11 o superior
- VS Code con la extensión *Extension Pack for Java* (se sugiere al abrir la carpeta)

## Ejecutar

**Desde VS Code:** abrir la carpeta, ir a *Run and Debug* (F5) y elegir *Calculadora de Matrices*.

**Desde la terminal:**

```bash
javac -encoding UTF-8 -d bin $(find src -name "*.java")
java -Dstdout.encoding=UTF-8 -cp bin TF_Grupo06
```

En Windows (PowerShell):

```powershell
javac -encoding UTF-8 -d bin (Get-ChildItem -Recurse src -Filter *.java).FullName
java -Dstdout.encoding=UTF-8 -cp bin TF_Grupo06
```

> Los decimales se escriben con punto (.).
