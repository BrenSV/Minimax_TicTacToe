# Tic Tac Toe with Minimax AI

This project is a Tic Tac Toe game implemented in Java with a graphical interface and an AI opponent that uses the Minimax algorithm. The game allows a human player to play against an AI that makes optimal moves to either win or force a tie.

## Features

- **Graphical Interface**: The game is built using Java Swing for an interactive GUI.
- **AI Opponent**: The AI uses the Minimax algorithm to calculate the best possible moves, ensuring it never loses.
- **Automatic Reset**: After each game, the board resets automatically, allowing for multiple rounds of play.
- **User Feedback**: Messages displayed to indicate the player's turn, the AI's turn, and the result (win, lose, or tie).

## Classes and Structure

The project consists of the following main classes:

### 1. `Main`
- Initializes the game window, creating the `JFrame` and adding components like the game board, message label, and reset button.
- Sets up the layout and action listeners for the interface.

### 2. `Board`
- Extends `JPanel` to represent the game board.
- Handles mouse clicks to allow the player to make a move.
- Displays the board and updates it after each move by the player or the AI.
- Uses colors and symbols to represent player and AI moves (`"O"` for the human player and `"X"` for the AI).

### 3. `GameController`
- Contains the game logic, including methods to check for a winner and reset the board.
- Uses the `checkWinner` method to determine if there is a winner or if the game is a tie.
- Implements the `bestMove` method to allow the AI to make an optimal move using the Minimax algorithm.

### 4. `Minimax`
- Implements the Minimax algorithm to find the best move for the AI.
- `bestMove` method determines the optimal position on the board for the AI.
- `minimax` method recursively evaluates each move to maximize the AI's score or minimize the human's score, depending on the turn.

## How to Run the Game

1. Clone or download this repository to your local machine.
2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
3. Ensure you have Java SDK installed (Java 8 or higher).
4. Run the `Main` class to start the game.

## Minimax Algorithm Explanation

The Minimax algorithm is a recursive method used in decision-making and game theory. It evaluates each possible move and chooses the one that maximizes the AI's chances of winning while minimizing the chances of losing. In this game:
- A victory for the AI is given a score of `10 - depth`, incentivizing the AI to win quickly.
- A loss for the AI (a victory for the human) is scored as `depth - 10`, penalizing quick losses.
- A tie is scored as `0`, as it is neutral for both players.

The algorithm ensures that the AI never loses, aiming for either a win or a tie, depending on the player's moves.

## Customization

To modify the symbols or colors for the players:
- Update the `human` and `ai` symbols in the `Board` class.
- Change the colors used in the `paintComponent` method of the `Board` class.

## Example Usage

After launching the game, the user clicks on an empty cell to place their move (`"O"`). The AI (`"X"`) will then automatically make its optimal move. The game will display messages indicating whose turn it is and whether there has been a win, a loss, or a tie.

## Future Improvements

- Implement an option to choose difficulty levels by limiting the depth of the Minimax search.
- Add an option to select player symbols.
- Allow two players (human vs. human) mode.

## License

This project is open source and available under the [MIT License](LICENSE).

