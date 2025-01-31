import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


import java.util.*;

import static javafx.scene.paint.Color.web;

/**
 * Youssef Amin
 * TODO: Write comments
 */

public class Board extends GridPane {

    private final int NUM_ROWS = 5;
    private final int NUM_COLS = 6;
    private final Map<Color, Integer> colors = new HashMap<>();

    private static GridPane gp;

    private List<Tiles> selectedTiles = new ArrayList<>();

    public Board() {
        fillMap();
    }

    private void fillMap() {
        //colors taken from uncle Chad
        colors.put(Color.web("#E57373"), 0); // Rose
        colors.put(Color.web("#81C784"), 0); // Green
        colors.put(Color.web("#64B5F6"), 0); // Blue
        colors.put(Color.web("#BA68C8"), 0); // Purple
        colors.put(Color.web("#F28C28"), 0); // Distinct Orange
        colors.put(Color.web("#4DB6AC"), 0); // Teal
        colors.put(Color.web("#9575CD"), 0); // Deep Purple
        colors.put(Color.web("#F06292"), 0); // Pink
        colors.put(Color.web("#7986CB"), 0); // Indigo
        colors.put(Color.web("#D32F2F"), 0); // Vibrant Red
        colors.put(Color.web("#AED581"), 0); // Light Green
        colors.put(Color.web("#00838F"), 0); // Dark Cyan
        colors.put(Color.web("#546E7A"), 0); // Steel Grey
        colors.put(Color.web("#007FFF"), 0); // Electric Blue
    }

    public GridPane makeGrid() {

        gp = new GridPane();
        gp.setBackground(new Background(new BackgroundFill(
                web("#2E3B4E"), CornerRadii.EMPTY, Insets.EMPTY)));
        gp.setPadding(new Insets(10, 5, 5, 5));
        gp.setHgap(4);
        gp.setVgap(4);

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                List<Color> colors = getColors();
                Tiles tile = new Tiles(colors.get(0), colors.get(1), colors.get(2));
                gp.add(tile, col, row);


            }
        }
        return gp;
    }

    private List<Color> getColors() {
        List<Color> newColors = new ArrayList<>();

        for (Map.Entry<Color, Integer> entry : colors.entrySet()) {
            if (entry.getValue() < 7) {
                newColors.add(entry.getKey());
            }
        }
        Collections.shuffle(newColors);

        if (newColors.size() < 3) {
            System.out.println("Not enough colors available!");
            return Arrays.asList(null, null, null);
        }

        Color sqColor = newColors.get(0);
        Color cirColor = newColors.get(1);
        Color triColor = newColors.get(2);
        colors.put(sqColor, colors.get(sqColor) + 1);
        colors.put(cirColor, colors.get(cirColor) + 1);
        colors.put(triColor, colors.get(triColor) + 1);

        return Arrays.asList(sqColor, cirColor, triColor);
    }

    public static void updateEyeDirection(double mouseX, double mouseY) {
        for (Node node : gp.getChildren()) {
            if (node instanceof Tiles tile) {
                tile.updateEyeDirectionTile(mouseX, mouseY);
            }
        }
    }

    //TODO: Implement toggle selection.
    public boolean pickTile(Tiles tile) {
        return false;
    }

    //TODO: Implement removeSimilar.
    private void removeSimilar() {

    }


}
