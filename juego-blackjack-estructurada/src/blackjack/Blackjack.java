package blackjack;

import javax.swing.*;

public class Blackjack {

    public static void main(String[] args) {
        while (true) {
            mostrarBienvenida();
            int opcion = mostrarMenu();

            // Si el jugador elige salir (opción 4) o cierra la ventana
            if (opcion == 4) {
                break;
            }

            // Si elige jugar (opción 3)
            if (opcion == 3) {
                ejecutarJuego();

                // Después de terminar una partida, pregunta si quiere jugar otra vez
                if (!pedirSiNo("¿Quiere volver a jugar?")) {
                    break;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "¡Gracias por jugar!");
    }

    // --- MÉTODOS DE INTERFAZ Y VALIDACIÓN ---

    private static void mostrarBienvenida() {
        JOptionPane.showMessageDialog(null,
                "Bienvenido al juego BlackJack \nEl objetivo es simple: ganarle al Croupier obteniendo el puntaje más cercano a 21.",
                "BlackJack", JOptionPane.DEFAULT_OPTION,
                new ImageIcon(Blackjack.class.getResource("/img/blackjack.jpg")));
    }

    private static int mostrarMenu() {
        while (true) {
            String input = JOptionPane.showInputDialog(
                    "Ingrese 1 para conocer el valor de las cartas" +
                            "\nIngrese 2 para conocer las reglas" +
                            "\nIngrese 3 para empezar a jugar" +
                            "\nIngrese 4 para salir");

            // Si presiona Cancelar o el botón de cerrar (null), retornamos 4 para salir
            if (input == null) return 4;

            if (input.equals("1")) {
                mostrarMensaje("Las figuras (J, Q, K) valen 10, el As vale 11 o 1 y las demás su valor.", "/img/blackjack.jpg");
            } else if (input.equals("2")) {
                mostrarMensaje("Ganas con BlackJack (10 + AS) o si tu suma es mayor a la del dealer sin pasar de 21.", "/img/comojugar.jpg");
            } else if (input.equals("3")) {
                mostrarMensaje("Barajando los mazos...", "/img/barajando.gif");
                return 3;
            } else if (input.equals("4")) {
                return 4;
            } else {
                JOptionPane.showMessageDialog(null, "Opción inválida. Ingrese 1, 2, 3 o 4.");
            }
        }
    }

    private static boolean pedirSiNo(String pregunta) {
        while (true) {
            String res = JOptionPane.showInputDialog(pregunta + " (si/no)");
            if (res != null && (res.equalsIgnoreCase("si") || res.equalsIgnoreCase("no"))) {
                return res.equalsIgnoreCase("si");
            }
            JOptionPane.showMessageDialog(null, "Por favor, responda con 'si' o 'no'.");
        }
    }

    private static int pedirApuesta() {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog("Ingrese su apuesta:");
                if (input == null) continue;
                int monto = Integer.parseInt(input);
                if (monto > 0) return monto;
                JOptionPane.showMessageDialog(null, "La apuesta debe ser mayor a 0.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido.");
            }
        }
    }

    private static void mostrarMensaje(String texto, String rutaImg) {
        JOptionPane.showMessageDialog(null, texto, "BlackJack",
                JOptionPane.DEFAULT_OPTION, new ImageIcon(Blackjack.class.getResource(rutaImg)));
    }

    // --- LÓGICA DEL JUEGO ---

    private static void ejecutarJuego() {
        int apu = pedirApuesta();
        int puntMaq = 0, puntJug = 0;

        // Turno inicial del Dealer
        for (int i = 0; i < 2; i++) {
            puntMaq += repartirCarta("Dealer", puntMaq);
        }
        mostrarMensaje("El dealer tiene en mano: " + puntMaq, "/img/mano.jpg");

        // Turno inicial del Jugador
        for (int i = 0; i < 2; i++) {
            puntJug += repartirCarta("Jugador", puntJug);
        }
        mostrarMensaje("El Jugador tiene en mano: " + puntJug, "/img/mano.jpg");

        // Lógica de juego
        if (puntJug == 21) {
            mostrarMensaje("¡BlackJack! Ganaste $" + (apu * 2), "/img/ganajug.jpg");
            return;
        }

        // El jugador pide cartas
        while (puntJug < 21 && pedirSiNo("¿Quiere otra carta?")) {
            puntJug += repartirCarta("Jugador", puntJug);
            mostrarMensaje("Tu puntaje actual: " + puntJug, "/img/mano.jpg");
        }

        if (puntJug > 21) {
            mostrarMensaje("Te pasaste de 21. Perdiste la apuesta.", "/img/ganamaq.jpg");
            return;
        }

        // Turno del Dealer (intenta superar al jugador)
        while (puntMaq < puntJug && puntMaq <= 21) {
            puntMaq += repartirCarta("Dealer", puntMaq);
            mostrarMensaje("Dealer: " + puntMaq + " | Jugador: " + puntJug, "/img/mano.jpg");
        }

        // Resolución final
        if (puntMaq > 21 || puntJug > puntMaq) {
            mostrarMensaje("¡Ganaste! Te llevas $" + (apu * 2), "/img/ganajug.jpg");
        } else if (puntJug == puntMaq) {
            mostrarMensaje("Empate. Recuperas tus $" + apu, "/img/empate.jpg");
        } else {
            mostrarMensaje("El Dealer gana. Perdiste la apuesta.", "/img/ganamaq.jpg");
        }
    }

    private static int repartirCarta(String receptor, int puntajeActual) {
        int pal = (int) (Math.random() * 3 + 1);
        int num = (int) (Math.random() * 13 + 1);
        int valor;

        if (num >= 11) {
            valor = 10;
        } else if (num == 1 && puntajeActual < 11) {
            valor = 11;
        } else {
            valor = num;
        }

        JOptionPane.showMessageDialog(null, "Le tocó al " + receptor + " un " + valor, "Blackjack",
                JOptionPane.PLAIN_MESSAGE,
                new ImageIcon(Blackjack.class.getResource("/img/" + num + pal + ".png")));

        return valor;
    }
}