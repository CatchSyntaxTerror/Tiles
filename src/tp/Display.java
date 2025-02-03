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
 * Display handles all UI updates and visualisation accept the tiles
 * in some ways its like the stage and the scene exclusively.
 */


public class Display {

    private static final int SCREEN_HEIGHT = 500;
    private static final int SCREEN_WIDTH = 700;
    private static Label label1;
    private static Label label2;
    private static Label label3;
    private final Board board;

    /**
     * Constructor for the Display
     */
    public Display() {
        board = new Board();
    }

    /**
     * this takes in the stage, sets and makes the scene and adds it
     * @param stage primary stage from MainLoop
     */
    public void setStage(Stage stage) {

        stage.setTitle("Tiles");

        label1 = new Label("Score: 0");
        label1.setTranslateX(-250);
        label1.setTranslateY(SCREEN_HEIGHT/2.7);
        label1.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 30));
        label1.setTextFill(Color.BISQUE);

        label2 = new Label("Streak: 0");
        label2.setTranslateX(250);
        label2.setTranslateY(SCREEN_HEIGHT/2.7);
        label2.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 30));
        label2.setTextFill(Color.BISQUE);

        label3 = new Label("Longest Streak: 0");
        label3.setTranslateX(0);
        label3.setTranslateY(SCREEN_HEIGHT/2.7);
        label3.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 45));
        label3.setTextFill(Color.BISQUE);

        StackPane root = new StackPane();
        root.getChildren().add(board.makeGrid());
        root.getChildren().addAll(label1, label2,label3);
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK);
        stage.setScene(scene);
        stage.show();

        scene.setOnMouseMoved(e ->
                Board.updateEyeDirection(e.getSceneX(), e.getSceneY()));
        scene.widthProperty().addListener((obs, oldWidth, newWidth) -> adjustScene(scene));
        scene.heightProperty().addListener((obs, oldHeight, newHeight) -> adjustScene(scene));

    }

    /**
     * its private so I don't need a comment, but I was trying to make the GUI
     * dynamic with the scene size. I got everything to work but the tiles do not resize
     * ... things started to break.
     * @param scene
     */
    private void adjustScene(Scene scene){

        double width = scene.getWidth();
        double height = scene.getHeight();

        double fontSize = height/5;
        label1.setFont(Font.font("Old English Text MT", FontWeight.BOLD, fontSize));
        label1.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 30));
        label1.setTextFill(Color.BISQUE);

        label2.setFont(Font.font("Old English Text MT", FontWeight.BOLD, fontSize));
        label2.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 30));
        label2.setTextFill(Color.BISQUE);

        label3.setFont(Font.font("Old English Text MT", FontWeight.BOLD, fontSize * 1.5));
        label3.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 45));
        label3.setTextFill(Color.BISQUE);

        label1.setTranslateX(-width / 3.0);
        label1.setTranslateY(height / 2.7);
        label2.setTranslateX(width / 3.0);
        label2.setTranslateY(height / 2.7);
        label3.setTranslateX(0);
        label3.setTranslateY(height / 2.7);

        board.setGridSize(width, height);
    }

    /**
     * the next three methods update score, streak and the longest streak labels
     * @param score, streak, highScore
     *               the score, the streak and the longest streak respectively
     */
    public static void updateScore(int score){
        Platform.runLater(() -> {
            label1.setText("Score: " + score); // ✅ Force UI update
        });
    }
    public static void updateStreak(int streak){
        Platform.runLater(() -> {
            label2.setText("Streak: " + streak); // ✅ Force UI update
        });;
    }

    public static void updateLongestStreak(int highScore){
        Platform.runLater(() -> {
            label3.setText("Longest Streak: " + highScore); // ✅ Force UI update
        });;
    }

    /**
     * The game over screen... Till next time
     */
    public static void gameOver() {
        Platform.runLater(() -> {
            Label gameOverLabel = new Label("You win this time...");
            gameOverLabel.setFont(Font.font("Old English Text MT",
                    FontWeight.BOLD, 60));
            gameOverLabel.setTextFill(Color.BISQUE);
            Label longestLabel = new Label("Longest Streak: "
                    + MainGameLoop.getLongestStreak());
            longestLabel.setFont(Font.font("Old English Text MT",
                    FontWeight.BOLD, 50));
            longestLabel.setTextFill(Color.BISQUE);
            longestLabel.setTranslateY(100);

            StackPane pane = new StackPane();
            pane.getChildren().addAll(gameOverLabel, longestLabel);
            pane.setBackground(new Background(new BackgroundFill(
                    Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

            Scene gameOverScene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
            Stage stage = (Stage) label1.getScene().getWindow();
            stage.setScene(gameOverScene);
        });

    }


}
