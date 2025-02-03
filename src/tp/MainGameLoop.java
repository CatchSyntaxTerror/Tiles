package tp;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Youssef Amin
 * This is the main class. It handles all updates controls the game.
 */

public class MainGameLoop extends Application {

    private static int score = 0;
    private static int streak = 0;
    private static int longestStreak = 0;

    /**
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage primaryStage) {
        Display display = new Display();
        display.setStage(primaryStage);
    }

    /**
     * starts the game
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * this increments the score and checks for game over condition
     * 75 shapes, 75 points, there will always be enough pairs that you cant
     * lose so the only way the game will end is if you get the high score
     */
    public static void updateScore(){
        score++;
        Display.updateScore(score);
        if(Board.isGameOver()){
            Display.gameOver();
        }

    }

    /**
     * checks if you have another sucessful match and increments streak
     * If match is false then streak set to zero and
     * longest streak set to current streak if its longer
     * Updates streak and longest streak using methods from display
     * @param matched boolean from tiles checkAndRemove() method
     */
    public static void updateStreak(boolean matched){
        if(matched) {
            streak++;
        } else{
            if(streak > longestStreak) {
                longestStreak = streak;
            }
            streak = 0;
            Display.updateLongestStreak(longestStreak);
        }
        Display.updateStreak(streak);
    }

    /**
     * allows other objects to reference longestStreak variable
     * @return longest streak variable
     */
    public static int getLongestStreak(){
        return longestStreak;
    }
}
