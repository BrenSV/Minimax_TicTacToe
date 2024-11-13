/**
 * @author Brenda Sanchez Vazquez
 * Clase GameController que gestiona la lógica del juego de Tic Tac Toe.
 * Esta clase verifica si hay un ganador, determina la mejor jugada de la IA,
 * y permite reiniciar el tablero.
 */
public class GameController {
    private Minimax minimax = new Minimax(); // Instancia de Minimax para calcular la mejor jugada de la IA

    /**
     * Método para verificar si hay un ganador en el tablero o si el juego termina en empate.
     * Revisa todas las filas, columnas y diagonales para ver si hay tres símbolos iguales.
     * @param board Matriz 2D que representa el tablero de juego.
     * @return El símbolo ganador ("X" o "O"), "tie" si hay empate, o null si el juego aún no ha terminado.
     */
    public String checkWinner(String[][] board) {
        // Comprobar filas y columnas para tres símbolos iguales
        for (int i = 0; i < 3; i++) {
            if (equals3(board[i][0], board[i][1], board[i][2])) return board[i][0]; // Fila i
            if (equals3(board[0][i], board[1][i], board[2][i])) return board[0][i]; // Columna i
        }
        // Comprobar las diagonales para tres símbolos iguales
        if (equals3(board[0][0], board[1][1], board[2][2])) return board[0][0]; // Diagonal principal
        if (equals3(board[2][0], board[1][1], board[0][2])) return board[2][0]; // Diagonal secundaria

        // Comprobar si hay celdas vacías; si no, es un empate
        for (String[] row : board)
            for (String cell : row)
                if (cell == null) return null; // Hay celdas vacías; el juego continúa

        return "tie"; // No hay celdas vacías y no hay ganador; es un empate
    }

    /**
     * Método auxiliar para verificar si tres celdas contienen el mismo símbolo (y no son null).
     * @param a Primer símbolo.
     * @param b Segundo símbolo.
     * @param c Tercer símbolo.
     * @return true si los tres símbolos son iguales y no son null, false en caso contrario.
     */
    private boolean equals3(String a, String b, String c) {
        return a != null && a.equals(b) && b.equals(c);
    }

    /**
     * Método para realizar la mejor jugada de la IA en el tablero.
     * Utiliza el algoritmo Minimax para calcular la mejor posición y coloca el símbolo de la IA en esa posición.
     * @param board Matriz 2D que representa el tablero de juego.
     * @param ai Símbolo de la IA (por ejemplo, "X").
     * @param human Símbolo del jugador humano (por ejemplo, "O").
     */
    public void bestMove(String[][] board, String ai, String human) {
        int[] move = minimax.bestMove(board, ai, human); // Obtener la mejor jugada usando Minimax
        board[move[0]][move[1]] = ai; // Colocar el símbolo de la IA en la mejor posición
    }

    /**
     * Método para reiniciar el tablero de juego, estableciendo todas las celdas como null.
     * @param board Matriz 2D que representa el tablero de juego.
     */
    public void resetBoard(String[][] board) {
        // Establecer todas las celdas en null para vaciar el tablero
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = null;
    }
}