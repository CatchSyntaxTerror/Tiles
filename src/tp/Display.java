package tp;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Youssef Amin
 * TODO: Write comments
 */


public class Display {

    private static final int SCREEN_HEIGHT = 500;
    private static final int SCREEN_WIDTH = 700;

    private static Label label1;
    private static Label label2;
    private static Label label3;
    private final Board board;


    public Display() {
        board = new Board();
    }

    public void setStage(Stage stage) {
        stage.setTitle("Tiles");

        label1 = new Label("Score: " + Tiles.getScore());
        label1.setTranslateX(-250);
        label1.setTranslateY(225);
        label1.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 30));
        label1.setTextFill(Color.BISQUE);

        label2 = new Label("Streak: " + Tiles.getStreak());
        label2.setTranslateX(250);
        label2.setTranslateY(225);
        label2.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 30));
        label2.setTextFill(Color.BISQUE);

        label3 = new Label("Longest Streak: " + Tiles.getStreak());
        label3.setTranslateX(0);
        label3.setTranslateY(225);
        label3.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 40));
        label3.setTextFill(Color.BISQUE);

        StackPane root = new StackPane();
        root.getChildren().add(board.makeGrid());
        root.getChildren().add(label2);
        root.getChildren().add(label1);
        root.getChildren().add(label3);
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK);
        stage.setScene(scene);
        stage.show();

        Tiles.setDisplay(this);

        scene.setOnMouseMoved(e ->
                Board.updateEyeDirection(e.getSceneX(), e.getSceneY()));

    }

    public static void updateScore(){
        Platform.runLater(() -> {
            label1.setText("Score: " + Tiles.getScore()); // ✅ Force UI update
        });
    }
    public static void updateStreak(){
        Platform.runLater(() -> {
            label2.setText("Streak: " + Tiles.getStreak()); // ✅ Force UI update
        });;
    }

    public static void updateLongestStreak(){
        Platform.runLater(() -> {
            label3.setText("Longest Streak: " + Tiles.getLongestStreak()); // ✅ Force UI update
        });;
    }

    public static void gameOver() {
        Platform.runLater(() -> {
            Label gameOverLabel = new Label("You win this time...");
            gameOverLabel.setFont(Font.font("Old English Text MT",
                    FontWeight.BOLD, 60));
            gameOverLabel.setTextFill(Color.BISQUE);

            StackPane pane = new StackPane();
            pane.getChildren().add(gameOverLabel);
            pane.setBackground(new Background(new BackgroundFill(
                    Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

            Scene gameOverScene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
            Stage stage = (Stage) label1.getScene().getWindow();
            stage.setScene(gameOverScene);
        });
    }


}
