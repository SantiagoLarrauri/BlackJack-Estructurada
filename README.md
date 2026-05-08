# Blackjack - Juego de Programación Estructurada

Este proyecto es una implementación del clásico juego de cartas **Blackjack** desarrollado en Java. El objetivo principal es demostrar conceptos de **programación estructurada**, modularización y el uso de la biblioteca Swing (`JOptionPane`) para crear una interfaz gráfica interactiva y visual.

## 🃏 Características del Juego

- **Interfaz Visual:** Uso de cuadros de diálogo interactivos con imágenes de cartas reales y animaciones (.gif).
- **Lógica de Juego Completa:**
  - Sistema de apuestas inicial.
  - Reparto de cartas para el jugador y el dealer (Crupier).
  - Manejo de reglas especiales: El As vale 1 u 11 según convenga, y las figuras (J, Q, K) valen 10.
  - El Dealer sigue reglas automáticas (pide cartas hasta intentar superar al jugador).
- **Validación de Entradas:** El programa es robusto ante errores del usuario, validando opciones de menú, respuestas de "si/no" y montos de apuestas numéricos.
- **Flujo de Usuario:** Menú inicial con reglas, valor de las cartas, inicio de juego y opción de salida.

## 🚀 Cómo ejecutar el proyecto

1. **Requisitos:** Tener instalado el JDK (Java Development Kit) versión 8 o superior.
2. **Estructura de carpetas:** Asegúrate de que la carpeta `img` (que contiene los recursos visuales) esté ubicada dentro de la carpeta `src` o en la raíz del proyecto y marcada como *Resources Root* en tu IDE.
3. **Compilación y ejecución:**
   - Abre el proyecto en IntelliJ IDEA, Eclipse o NetBeans.
   - Ejecuta la clase `Blackjack.java`.

## 📁 Estructura del Repositorio

```text
/juego-blackjack
├── /src
│   └── /blackjack
│       └── Blackjack.java   # Código fuente principal
├── /img                     # Recursos visuales (.png, .jpg, .gif)
├── .gitignore               # Archivos omitidos para Git
└── README.md                # Documentación del proyecto
