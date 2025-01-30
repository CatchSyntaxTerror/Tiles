import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.web;

/**
 * Youssef Amin
 * This class is meant to update the display of the game
 */


public class Display {
    private final int SCREEN_HEIGHT = 500;
    private final int SCREEN_WIDTH = 500;
    private final Board board;


    public Display() {
        board = new Board();
    }

    public void setStage(Stage stage) {
        stage.setTitle("Tiles");
        StackPane root = new StackPane();
        root.getChildren().add(board.makeGrid());
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
}
