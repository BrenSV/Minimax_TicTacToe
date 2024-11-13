/**
 * @author Brenda Sanchez Vazquez
 * Clase Minimax que implementa el algoritmo Minimax para encontrar la mejor jugada de la IA en el juego de Tic Tac Toe.
 * Esta clase determina la mejor jugada en el tablero para la IA, maximizando sus posibilidades de ganar.
 *
 */
public class Minimax {

    /**
     * Método para calcular la mejor jugada para la IA en el tablero actual.
     * Recorre todas las posiciones vacías y utiliza el algoritmo Minimax para evaluar la puntuación de cada jugada.
     * @param board Matriz 2D que representa el tablero de juego.
     * @param ai Símbolo de la IA (por ejemplo, "X").
     * @param human Símbolo del jugador humano (por ejemplo, "O").
     * @return Un arreglo de dos enteros que representan la mejor posición en el tablero (fila y columna).
     */
    public int[] bestMove(String[][] board, String ai, String human) {
        int bestScore = Integer.MIN_VALUE;  // Puntuación inicial mínima para maximizar
        int[] move = new int[2];  // Arreglo para almacenar la mejor jugada

        // Recorrer el tablero para encontrar todas las posiciones vacías
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {  // Verificar si la posición está vacía
                    board[i][j] = ai;  // Simular la jugada de la IA en la posición
                    int score = minimax(board, 0, false, ai, human);  // Evaluar la jugada con Minimax
                    board[i][j] = null;  // Revertir la jugada

                    // Si la puntuación es mejor que la actual, actualizar la mejor jugada
                    if (score > bestScore) {
                        bestScore = score;
                        move[0] = i;
                        move[1] = j;
                    }
                }
            }
        }
        return move;  // Retornar la mejor jugada encontrada
    }

    /**
     * Implementación recursiva del algoritmo Minimax para evaluar las posibles jugadas.
     * Simula todos los movimientos posibles en el tablero y asigna puntuaciones para encontrar la mejor estrategia.
     * @param board Matriz 2D que representa el estado actual del tablero.
     * @param depth Profundidad de la recursión, usada para medir la profundidad de la simulación.
     * @param isMaximizing Booleano que indica si el jugador actual es el que maximiza (IA) o minimiza (humano).
     * @param ai Símbolo de la IA (por ejemplo, "X").
     * @param human Símbolo del jugador humano (por ejemplo, "O").
     * @return Un entero que representa la puntuación de la jugada: 10 para victoria de la IA, -10 para victoria del humano, 0 para empate.
     */
    private int minimax(String[][] board, int depth, boolean isMaximizing, String ai, String human) {
        // Verificar si hay un ganador o empate
        String result = new GameController().checkWinner(board);
       if (result != null) {
            if (result.equals(ai)) return 10 - depth;      // Victoria de la IA
            if (result.equals(human)) return depth - 10;  // Victoria del humano
            return 0;  // Empate
        }

        // Caso en que es el turno del jugador que maximiza (IA)
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;  // Puntuación inicial mínima para maximizar
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == null) {  // Verificar si la posición está vacía
                        board[i][j] = ai;  // Simular jugada de la IA
                        int score = minimax(board, depth + 1, false, ai, human);  // Llamada recursiva con turno minimizador
                        board[i][j] = null;  // Revertir la jugada
                        bestScore = Math.max(score, bestScore);  // Tomar el valor máximo
                    }
                }
            }
            return bestScore;
        }
        // Caso en que es el turno del jugador que minimiza (humano)
        else {
            int bestScore = Integer.MAX_VALUE;  // Puntuación inicial máxima para minimizar
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == null) {  // Verificar si la posición está vacía
                        board[i][j] = human;  // Simular jugada del humano
                        int score = minimax(board, depth + 1, true, ai, human);  // Llamada recursiva con turno maximizador
                        board[i][j] = null;  // Revertir la jugada
                        bestScore = Math.min(score, bestScore);  // Tomar el valor mínimo
                    }
                }
            }
            return bestScore;
        }
    }
}