import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Brenda Sanchez Vazquez
 * Clase Board que representa el tablero de juego para Gato
 * Esta clase maneja la lógica de renderización del tablero, la detección de clics y la gestión de turnos entre el jugador humano y la computadora (Inteligencia Artificial).
 */
public class Board extends JPanel {
    private final String[][] board; // Matriz que representa el estado actual del tablero
    private final int size;         // Tamaño del tablero en píxeles
    private final String human = "O";  // Símbolo para el jugador humano
    private final String ai = "X";     // Símbolo para la IA
    private String currentPlayer = human; // Variable para seguir el turno actual
    private GameController gameController; // Controlador para gestionar la lógica del juego
    private JLabel messageLabel;          // Etiqueta para mostrar mensajes al usuario

    /**
     * Constructor de la clase Board. Inicializa el tablero, el controlador y la etiqueta de mensajes.
     * @param size Tamaño del tablero en píxeles.
     * @param gameController Instancia del GameController que gestiona la lógica del juego.
     * @param messageLabel JLabel para mostrar mensajes al usuario.
     */
    public Board(int size, GameController gameController, JLabel messageLabel) {
        this.size = size;
        this.board = new String[3][3];  // Inicialización del tablero de 3x3
        this.gameController = gameController;
        this.messageLabel = messageLabel;

        // Establecer mensaje inicial
        messageLabel.setText("Your Turn");

        // Añadir un MouseListener para manejar clics en el tablero
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                handleMousePress(e);
            }
        });
        setPreferredSize(new Dimension(size, size)); // Establecer tamaño preferido del tablero
    }

    /**
     * Método para obtener el estado actual del tablero.
     * @return Matriz 2D que representa el tablero de juego.
     */
    public String[][] getBoard() {
        return board;
    }

    /**
     * Método para reiniciar el juego. Restablece el tablero, el turno del jugador y el mensaje de estado.
     */
    public void resetGame() {
        gameController.resetBoard(board);  // Limpiar la matriz del tablero
        currentPlayer = human;             // Restablecer el turno al jugador humano
        messageLabel.setText("New Game! Your Turn");  // Actualizar mensaje
        repaint();                         // Repintar el tablero para mostrar el estado limpio
    }

    /**
     * Maneja el evento de clic del mouse en el tablero.
     * Determina la celda donde se hizo clic, realiza la jugada del jugador, verifica el estado del juego y permite que la IA haga su jugada.
     * @param e Evento de clic del mouse.
     */
    private void handleMousePress(MouseEvent e) {
        int i = e.getX() / (size / 3); // Coordenada X en la matriz de tablero
        int j = e.getY() / (size / 3); // Coordenada Y en la matriz de tablero

        // Verificar si es el turno del humano y la celda está vacía
        if (currentPlayer.equals(human) && board[i][j] == null) {
            board[i][j] = human;         // Marcar la jugada del humano en el tablero
            currentPlayer = ai;          // Cambiar el turno a la IA
            messageLabel.setText("AI's Turn");
            repaint();                   // Repintar el tablero para reflejar el cambio

            // Verificar si hay un ganador después de la jugada del humano
            if (gameController.checkWinner(board) == null) {
                gameController.bestMove(board, ai, human); // Realizar la mejor jugada de la IA
                currentPlayer = human;                     // Cambiar el turno de vuelta al humano
                repaint();                                 // Repintar el tablero después de la jugada de la IA
            }

            // Verificar el resultado después de la jugada de la IA
            String result = gameController.checkWinner(board);
            if (result != null) {
                // Mostrar mensaje de resultado
                messageLabel.setText(result.equals("tie") ? "It's a Tie!" : result + " wins!");
                JOptionPane.showMessageDialog(this, result.equals("tie") ? "It's a Tie!" : result + " wins!");
                resetGame(); // Reiniciar el juego al detectar un ganador o empate
            } else {
                messageLabel.setText("Your Turn"); // Actualizar el mensaje si no hay ganador
            }
        }
    }

    /**
     * Sobrescribe el método paintComponent para dibujar el tablero y las marcas de cada jugador (X y O).
     * @param g Objeto Graphics utilizado para dibujar en el componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = size / 3; // Ancho de cada celda
        int h = size / 3; // Altura de cada celda

        // Color de fondo del tablero
        setBackground(Color.WHITE);

        // Dibujar las líneas de la cuadrícula con color y grosor
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(w, 0, 4, size);         // Línea vertical 1
        g.fillRect(2 * w, 0, 4, size);     // Línea vertical 2
        g.fillRect(0, h, size, 4);         // Línea horizontal 1
        g.fillRect(0, 2 * h, size, 4);     // Línea horizontal 2

        // Dibujar X y O con una fuente grande y color distintivo
        g.setFont(new Font("Arial", Font.BOLD, size / 6));
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                int x = i * w + w / 4;
                int y = j * h + h / 2;
                if (board[i][j] != null) {
                    if (board[i][j].equals(human)) {
                        g.setColor(Color.BLUE); // Color para el jugador humano (O)
                        g.drawString("O", x, y);
                    } else if (board[i][j].equals(ai)) {
                        g.setColor(Color.RED);  // Color para la IA (X)
                        g.drawString("X", x, y);
                    }
                }
            }
        }
    }
}