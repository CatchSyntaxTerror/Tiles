package tp;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;
import static javafx.scene.paint.Color.web;

/**
 * Youssef Amin
 * TODO: Write comments
 */


public class Tiles extends StackPane {
    private final Color triColor;
    private final Color cirColor;
    private final Color sqColor;
    private final double SCALE_MULTIPLIER = .9;
    private static Display display;
    private final double[] TRIANGLE_POINTS =
            {0.0, -25.0 * SCALE_MULTIPLIER, -20.0 * SCALE_MULTIPLIER,
                    20.0 * SCALE_MULTIPLIER, 20.0 * SCALE_MULTIPLIER,
                    20.0 * SCALE_MULTIPLIER};
    private Boolean selected = false;
    private Circle pupil;
    private Circle iris;
    private StackPane eyeStack;
    private Rectangle rect;
    private Circle circle;
    private Polygon triangle;
    private GridPane pane;
    private static StackPane tile1 = null;
    private static StackPane tile2 = null;

    private static List<Tiles> clickedTiles = new ArrayList<>();

    private static int score = 74;
    private static int streak = 0;

    private static int longestStreak = 0;


    public Tiles(Color sqColor, Color cirColor, Color triColor, GridPane pane) {
        this.cirColor = cirColor;
        this.triColor = triColor;
        this.sqColor = sqColor;
        this.pane = pane;

        makeTiles();

        this.setOnMouseClicked(e -> handleClicks());


    }

    /**
     * These four methods are here to update score and streak
     * @return
     */
    public static int getScore() {
        if(score >= 70){
            Display.gameOver();
        }
        return score;
    }

    public static int getStreak() {
        return streak;
    }

    public static int getLongestStreak() {
        return longestStreak;
    }

    public static void newLongestStreak() {
        if (streak > longestStreak) {
            longestStreak = streak;
            Display.updateLongestStreak(); // ✅ Now updates when streak is the highest
        }
    }

    public static void setDisplay(Display displayScore) {
        display = displayScore;
    }


    //TODO: Something is broken with the deselection
    public void handleClicks() {
        if (tile1 == null) {
            tile1 = this;
            isSelected(tile1);
            //System.out.println("I, " + tile1 + " am tile 1");
        } else if (tile2 == null && tile1 != this) {
            tile2 = this;
            isSelected(tile2);
            //System.out.println("I, " + tile2 + " am tile 2");

            if (checkAndRemove(tile1, tile2)) {
                score++;
                streak++;
                isUnselected(tile1);
                tile1 = tile2;
                tile2 = null;
                Display.updateStreak();
                Display.updateScore();
//                System.out.println(tile1 + " is now " + tile2);
            } else {
                isUnselected(tile1);
                isUnselected(tile2);
                newLongestStreak();
                tile1 = null;
                tile2 = null;
                streak = 0;
//                System.out.println("No match. Resetting selection.");
            }
        } else if (tile1 == this) {
            isUnselected(tile1);
            tile1 = null;
            streak = 0;
//            System.out.println("tile 1 is now null");
        } else if (tile2 == this) {
            isUnselected(tile2);
            tile2 = null;
//            System.out.println("tile 2 is now null");
        }

    }

    private Boolean checkAndRemove(StackPane tile1, StackPane tile2) {
        Tiles tileShapes1 = (Tiles) tile1;
        Tiles tileShapes2 = (Tiles) tile2;

        boolean matched = false;

        if (tileShapes1.rect.getFill().equals(tileShapes2.rect.getFill())) {
            tileShapes1.rect.setFill(Color.TRANSPARENT);
            tileShapes2.rect.setFill(Color.TRANSPARENT);
            tileShapes1.rect.setStroke(Color.TRANSPARENT);
            tileShapes2.rect.setStroke(Color.TRANSPARENT);
            matched = true;
        }
        if (tileShapes1.circle.getFill().equals(tileShapes2.circle.getFill())) {
            tileShapes1.circle.setFill(Color.TRANSPARENT);
            tileShapes2.circle.setFill(Color.TRANSPARENT);
            tileShapes1.circle.setStroke(Color.TRANSPARENT);
            tileShapes2.circle.setStroke(Color.TRANSPARENT);
            matched = true;
        }
        if (tileShapes1.triangle.getFill().equals(tileShapes2.triangle.getFill())) {
            tileShapes1.triangle.setFill(Color.TRANSPARENT);
            tileShapes2.triangle.setFill(Color.TRANSPARENT);
            tileShapes1.triangle.setStroke(Color.TRANSPARENT);
            tileShapes2.triangle.setStroke(Color.TRANSPARENT);
            matched = true;
        }

        return matched;
    }

    private void isUnselected(StackPane tile) {
        Tiles tileShapes = (Tiles) tile;

        if (!tileShapes.rect.getFill().equals(Color.TRANSPARENT)) {
            tileShapes.rect.setStroke(Color.BLACK);
        }
        if (!tileShapes.circle.getFill().equals(Color.TRANSPARENT)) {
            tileShapes.circle.setStroke(Color.BLACK);
        }
        if (!tileShapes.triangle.getFill().equals(Color.TRANSPARENT)) {
            tileShapes.triangle.setStroke(Color.BLACK);
        }
    }

    private void isSelected(StackPane tile) {
        Tiles tileShapes = (Tiles) tile; // Cast StackPane to Tiles

        if (!tileShapes.rect.getFill().equals(Color.TRANSPARENT)) {
            tileShapes.rect.setStroke(Color.GHOSTWHITE);
        }
        if (!tileShapes.circle.getFill().equals(Color.TRANSPARENT)) {
            tileShapes.circle.setStroke(Color.GHOSTWHITE);
        }
        if (!tileShapes.triangle.getFill().equals(Color.TRANSPARENT)) {
            tileShapes.triangle.setStroke(Color.GHOSTWHITE);
        }

    }


    private void makeTiles() {

        int RECTANGLE_HEIGHT = (int)(100 * SCALE_MULTIPLIER);
        int RECTANGLE_WIDTH = (int)(79 * SCALE_MULTIPLIER);
        rect = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT, sqColor);

        int CIRCLE_SIZE = (int)(35 * SCALE_MULTIPLIER);
        circle = new Circle(CIRCLE_SIZE, cirColor);

        triangle = new Polygon();
        triangle.getPoints().addAll(TRIANGLE_POINTS[0], TRIANGLE_POINTS[1],
                TRIANGLE_POINTS[2], TRIANGLE_POINTS[3], TRIANGLE_POINTS[4],
                TRIANGLE_POINTS[5]);
        triangle.setFill(triColor);

        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        triangle.setStroke(Color.BLACK);
        triangle.setStrokeWidth(2);

        StackPane eyeStack = makeEyes();
        int EYE_TRANSLATE = (int)(10 * SCALE_MULTIPLIER);
        eyeStack.setTranslateY(EYE_TRANSLATE);

        this.getChildren().addAll(rect, circle, triangle, eyeStack);


    }


    private StackPane makeEyes() {
        int OUTER_SIZE = (int)(10 * SCALE_MULTIPLIER);
        Circle outer = new Circle(OUTER_SIZE, Color.WHITE);
        outer.setStroke(Color.BLACK);
        outer.setStrokeWidth(.5);
        int IRISE_SIZE = (int)(6 * SCALE_MULTIPLIER);
        iris = new Circle(IRISE_SIZE, web("#4B8B9B"));
        iris.setStroke(Color.BLACK);
        iris.setStrokeWidth(.5);
        int PUPIL_SIZE = (int)(4 * SCALE_MULTIPLIER);
        pupil = new Circle(PUPIL_SIZE, Color.BLACK);
        pupil.setStroke(Color.BLACK);
        pupil.setStrokeWidth(.5);

        eyeStack = new StackPane(outer, iris, pupil);
        return eyeStack;
    }

    public void updateEyeDirectionTile(double mouseX, double mouseY) {

        double eyeCenterX = eyeStack.localToScene(0, 0).getX();
        double eyeCenterY = eyeStack.localToScene(0, 0).getY();

        double x = mouseX - eyeCenterX;
        double y = mouseY - eyeCenterY;

        double distance = Math.sqrt(x * x + y * y);
        double maxDistance = 4 * SCALE_MULTIPLIER;

        double scale = (distance > 0) ? Math.min(maxDistance / distance, 1) : 0;
        iris.setTranslateX(x * scale);
        iris.setTranslateY(y * scale);
        pupil.setTranslateX(x * scale * 1.2);
        pupil.setTranslateY(y * scale * 1.2);
    }

}
