import javax.swing.*;
import java.awt.*;

/**
 * @author Brenda Sanchez Vazquez
 * Clase principal para ejecutar el juego de Tic Tac Toe con una interfaz gráfica de usuario (GUI).
 * Este programa crea una ventana de juego con un tablero de Gato y un botón de reinicio.
 */
public class Main {
    public static void main(String[] args) {
        // Crear un marco (ventana) principal para el juego
        JFrame frame = new JFrame("Tic Tac Toe");

        // Instancia del controlador de juego, que maneja la lógica del juego
        GameController gameController = new GameController();

        // Crear un JLabel para mostrar mensajes al usuario (por ejemplo, quién debe jugar)
        JLabel messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Opensans", Font.BOLD, 24));
        messageLabel.setForeground(Color.DARK_GRAY);

        // Crear el tablero del juego y asociarlo al controlador y al mensaje
        Board board = new Board(400, gameController, messageLabel);

        // Crear el botón de reinicio y establecer su acción para reiniciar el juego
        JButton resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Opensans", Font.PLAIN, 18));
        resetButton.addActionListener(e -> {
            // Reiniciar el tablero llamando al método en el controlador y actualizar la GUI
            gameController.resetBoard(board.getBoard());
            board.repaint();  // Refrescar el tablero para que se muestren los cambios
            messageLabel.setText("New Game! Your Turn"); // Actualizar el mensaje
        });

        // Crear el panel principal y añadir los componentes
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(messageLabel, BorderLayout.NORTH);  // Añadir etiqueta de mensaje en la parte superior
        mainPanel.add(board, BorderLayout.CENTER);        // Añadir tablero en el centro
        mainPanel.add(resetButton, BorderLayout.SOUTH);   // Añadir botón de reinicio en la parte inferior

        // Configurar el marco principal
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Salir del programa al cerrar la ventana
        frame.add(mainPanel);      // Añadir el panel principal al marco
        frame.pack();              // Ajustar tamaño de ventana automáticamente
        frame.setVisible(true);    // Hacer visible la ventana del juego
    }
}