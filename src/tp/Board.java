package tp;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;


import java.awt.*;
import java.util.*;
import java.util.List;

import static javafx.scene.paint.Color.web;

/**
 * Youssef Amin
 * I'm Just sorry on this one. I learned my lesson.
 * Plan and then execute. It is not easy to fix teh structure of the entire
 * game after it has been written.
 * Board is meant to essentially be the Grid pane.
 * It also handles picking colors and is a bridge from tiles to display.
 * I think this could all go in tiles, but I wasn't sure,and I'm tired
 */

public class Board extends GridPane {

    private final int NUM_ROWS = 4;
    private final int NUM_COLS = 9;
    private final Map<Color, Integer> sqColors = new HashMap<>();
    private final Map<Color, Integer> cirColors = new HashMap<>();
    private final Map<Color, Integer> triColors = new HashMap<>();
    private static GridPane gp;

    /**
     * Constructor for Board.
     * fills each map with respective colors with fillMaps()
     */
    public Board() {
        fillMaps();
    }

    private void fillMaps() {
        //colors taken from uncle Chad
        sqColors.put(web("#D94F4F"), 0); // Deep Red
        sqColors.put(web("#D67D3E"), 0); // Burnt Orange
        sqColors.put(web("#5E8B60"), 0); // Forest Green
        sqColors.put(web("#3C6EAE"), 0); // Deep Blue
        sqColors.put(web("#7A5F9A"), 0); // Muted Purple
        sqColors.put(web("#C04A5A"), 0); // Dark Rose
        sqColors.put(web("#2F4858"), 0); // Stormy Blue
        sqColors.put(web("#7F8A8E"), 0); // Smoky Gray-Blue
        sqColors.put(web("#A05A6F"), 0); // Dusty Plum
        cirColors.put(web("#B03A48"), 0); // Darkened Red
        cirColors.put(web("#AF6338"), 0); // Warm Brown-Orange
        cirColors.put(web("#237A57"), 0); // Deep Teal
        cirColors.put(web("#1D374D"), 0); // Charcoal Blue
        cirColors.put(web("#5E7C4E"), 0); // Olive Green
        cirColors.put(web("#8A5C37"), 0); // Rustic Brown
        cirColors.put(web("#586F7C"), 0); // Slate Blue-Gray
        cirColors.put(web("#8E3B46"), 0); // Rich Burgundy
        cirColors.put(web("#704D5B"), 0); // Dark Mauve
        triColors.put(web("#A12B56"), 0); // Deep Magenta
        triColors.put(web("#673C5E"), 0); // Dark Plum
        triColors.put(web("#345995"), 0); // Midnight Blue
        triColors.put(web("#8F4B38"), 0); // Warm Chestnut
        triColors.put(web("#3E5C58"), 0); // Dark Jade
        triColors.put(web("#A85751"), 0); // Faded Brick
        triColors.put(web("#5B2333"), 0); // Maroon Brown
        triColors.put(web("#41436A"), 0); // Muted Indigo
        triColors.put(web("#6D4E5C"), 0); // Dusky Rosewood
    }

    /**
     * this creats the gridPane and adds all the tiles to it.
     *
     * @return the grid used to hold tiles.
     */
    public GridPane makeGrid() {

        gp = new GridPane();
        gp.setBackground(new Background(new BackgroundFill(
                web("#2E3B4E"), CornerRadii.EMPTY, Insets.EMPTY)));
        gp.setPadding(new Insets(10, 5, 5, 5));
        gp.setHgap(4);
        gp.setVgap(4);

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                List<Color> colors = setColors();
                Tiles tile = new Tiles(colors.get(0), colors.get(1), colors.get(2));
                gp.add(tile, col, row);
            }
        }

        return gp;
    }

    /**
     * This method was basically the Hydra.
     * Everytime I thought I finished it two more problems came up.
     * Essentially I did some math to get the appropriate amount of pairs if colors
     * made three separate maps to hold respective color pools to each list
     * then added them to respective lists and shuffled the list. This way there
     * would always be an even number of each color.
     * the maps also link an integer value with each color. I incremented it
     * as to not allow more than 2 pairs of each color.
     * I picked the first color from each shuffled list until all the colors
     * needed are picked. this always results in even pairs but more math would have to
     * be done if I needed to add more tiles. I'm sure I could make an equation but um....
     *
     * @return List of three colors, one for each shape.
     */
    private List<Color> setColors() {

        List<Color> newSqColors = new ArrayList<>();
        List<Color> newCirColors = new ArrayList<>();
        List<Color> newTriColors = new ArrayList<>();


        for (Map.Entry<Color, Integer> entry : sqColors.entrySet()) {
            if (entry.getValue() < 4) {
                newSqColors.add(entry.getKey());
            }
        }
        for (Map.Entry<Color, Integer> entry : cirColors.entrySet()) {
            if (entry.getValue() < 4) {
                newCirColors.add(entry.getKey());
            }
        }
        for (Map.Entry<Color, Integer> entry : triColors.entrySet()) {
            if (entry.getValue() < 4) {
                newTriColors.add(entry.getKey());
            }
        }
        Collections.shuffle(newSqColors);
        Collections.shuffle(newCirColors);
        Collections.shuffle(newTriColors);

        Random random = new Random();

        Color sqColor = newSqColors.get(random.nextInt(newSqColors.size()));
        Color cirColor = newCirColors.get(random.nextInt(newCirColors.size()));
        Color triColor = newTriColors.get(random.nextInt(newTriColors.size()));


        sqColors.put(sqColor, sqColors.get(sqColor) + 1);
        cirColors.put(cirColor, cirColors.get(cirColor) + 1);
        triColors.put(triColor, triColors.get(triColor) + 1);

        return Arrays.asList(sqColor, cirColor, triColor);
    }

    /**
     * this is a bridge for the eye's movement. In order to get the eyes to
     * all follow the mouse as if they were one I had to set the
     * mouse listener to the scene, but locateToScene(), used in Tiles,
     * does not provide the points for the scene only the node or eye in this case
     * This method allows display to get the points or eyes' placement.
     *
     * @param mouseX the mouse's x component
     * @param mouseY the mouse's y component
     */
    public static void updateEyeDirection(double mouseX, double mouseY) {
        for (Node node : gp.getChildren()) {
            if (node instanceof Tiles tile) {
                tile.updateEyeDirectionTile(mouseX, mouseY);
            }
        }
    }

    /**
     * this method dynamically changes the size of the grid with the size of the
     * scene it works well but the tile sizes don't change, so it basically just spaces
     * out the tiles so the whole screen is filled. works for full screen.
     *
     * @param sceneWidth  the width of the scene from Display
     * @param sceneHeight the height of the scene from Display
     */
    public void setGridSize(double sceneWidth, double sceneHeight) {
        double tileSize = ((sceneWidth / NUM_COLS) * (sceneHeight / NUM_ROWS));
        gp.setHgap(sceneWidth * 0.07);
        gp.setVgap(sceneHeight * 0.05);

        for (Node node : gp.getChildren()) {
            if (node instanceof Tiles tile) {
                tile.updateTileSize(tileSize);
            }
        }
    }

    /**
     * Assumes game is over and then checks for any none Transparent
     * shapes.
     * Relavent shapes are the first 3 shapes in the stack pane.
     * @return boolean all gone. True until proven false
     */
    public static boolean isGameOver() {
        boolean allGone = true;
        for (Node node : gp.getChildren()) {
            if (node instanceof Tiles tile) {
                for (int i = 0; i < 3; i++) {
                    Node node1 = tile.getChildren().get(i);
                    if (node1 instanceof Shape shape &&
                            !shape.getFill().equals(Color.TRANSPARENT)) {
                        allGone = false;
                    }
                }
            }
        }
        return allGone;
    }
}
