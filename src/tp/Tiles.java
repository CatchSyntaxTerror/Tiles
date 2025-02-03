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
    private static MainGameLoop gameLoop;
    private final double SCALE_MULTIPLIER = .9;

    int RECTANGLE_HEIGHT = (int)(100 * SCALE_MULTIPLIER);
    int RECTANGLE_WIDTH = (int)(79 * SCALE_MULTIPLIER);
    private final double[] TRIANGLE_POINTS =
            {0.0, -25.0 * SCALE_MULTIPLIER, -20.0 * SCALE_MULTIPLIER,
                    20.0 * SCALE_MULTIPLIER, 20.0 * SCALE_MULTIPLIER,
                    20.0 * SCALE_MULTIPLIER};
    private Circle pupil;
    private Circle iris;
    private StackPane eyeStack;
    private Rectangle rect;
    private Circle circle;
    private Polygon triangle;
    private static StackPane tile1 = null;
    private static StackPane tile2 = null;
    private boolean matched;
    private double tileSize = RECTANGLE_WIDTH * RECTANGLE_HEIGHT;

    /**
     * constructor for tiles. basically the stack pane.
     * @param sqColor the color of each square
     * @param cirColor the color fo each circle
     * @param triColor the color of each triangle
     */
    public Tiles(Color sqColor, Color cirColor, Color triColor) {

        this.cirColor = cirColor;
        this.triColor = triColor;
        this.sqColor = sqColor;

        makeTiles(tileSize);

        this.setOnMouseClicked(e -> handleClicks());
    }


    /**
     *
     */
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
                isUnselected(tile1);
                tile1 = tile2;
                tile2 = null;
                MainGameLoop.updateScore();
                MainGameLoop.updateStreak(matched);
//                System.out.println(tile1 + " is now " + tile2);
            } else {
                isUnselected(tile1);
                isUnselected(tile2);
                MainGameLoop.updateStreak(matched);
                tile1 = null;
                tile2 = null;
//                System.out.println("No match. Resetting selection.");
            }
        } else if (tile1 == this) {
            isUnselected(tile1);
            tile1 = null;
           MainGameLoop.updateStreak(false);
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

        matched = false;

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


    private void makeTiles(double tileSize) {
        this.getChildren().clear();

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

    /**
     * I tried to make the tiles grow with the screen, but to no avail.
     * @param tileSize the size id like tiles to be.
     */
    public void updateTileSize(double tileSize) {
        this.tileSize = tileSize;
        makeTiles(tileSize);
    }

    /**
     * Calculates strait line from pupil to mouse. and allows the eye to move
     * toward it just enough to stay in its own lane.
     * before I used Math.min() I had some weird behavior
     * It's too late now, but I think some cool mechanics could be added.
     * @param mouseX
     * @param mouseY
     */
    public void updateEyeDirectionTile(double mouseX, double mouseY) {

        double eyeCenterX = eyeStack.localToScene(0, 0).getX();
        double eyeCenterY = eyeStack.localToScene(0, 0).getY();

        double x = mouseX - eyeCenterX;
        double y = mouseY - eyeCenterY;

        double distance = Math.sqrt(x * x + y * y);
        double maxDistance = 4 * SCALE_MULTIPLIER;

        double scale = (distance > 0) ? Math.min(maxDistance / distance, 0.04) : 0;
        iris.setTranslateX(x * scale);
        iris.setTranslateY(y * scale);
        pupil.setTranslateX(x * scale * 1.2);
        pupil.setTranslateY(y * scale * 1.2);
    }
}
