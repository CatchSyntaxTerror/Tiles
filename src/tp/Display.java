package tp;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.web;

/**
 * Youssef Amin
 * TODO: Write comments
 */


public class Display {
    private final int SCREEN_HEIGHT = 550;
    private final int SCREEN_WIDTH = 700;
    private final Board board;


    public Display() {
        board = new Board();
    }

    public void setStage(Stage stage) {
        stage.setTitle("Tiles");

        Label label = new Label("Score: " + 5);
        label.setTranslateX(250);
        label.setTranslateY(-125);
        label.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 40));
        label.setTextFill(Color.BISQUE);

        Label label2 = new Label("Streak: " + 5);
        label2.setTranslateX(250);
        label2.setTranslateY(125);
        label2.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 40));
        label2.setTextFill(Color.BISQUE);

        StackPane root = new StackPane();
        root.getChildren().add(board.makeGrid());
        root.getChildren().add(label2);
        root.getChildren().add(label);
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK);
        stage.setScene(scene);
        stage.show();

        scene.setOnMouseMoved(e ->
                Board.updateEyeDirection(e.getSceneX(), e.getSceneY()));
    }
}
