package cellabsorption;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("SameParameterValue")
public class CellSimulation {

    private CanvasWindow canvas;
    private Random rand = new Random();
    private List<Cell> cells;

    public static void main(String[] args) {
        new CellSimulation();
    }

    public CellSimulation() {
        canvas = new CanvasWindow("Cell Absorption", 800, 800);
        populateCells();
        while (true) {
            Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
            for (int i = 0; i < cells.size(); i++){
               Cell cell = cells.get(i);
                cell.moveAround(canvasCenter);
                handleCellInteraction();
            }
                canvas.draw();
                canvas.pause(10);
        }
    }

    private void populateCells() {
        double size = rand.nextInt(5) + 2;
        this.cells = new ArrayList<>();
        for (int n = 0; n < 200; n++){
            Cell cell = new Cell(
                rand.nextDouble() * (canvas.getWidth() - size),
                rand.nextDouble() * (canvas.getWidth() - size),
                size,
                Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
                canvas.add(cell.getShape());
            cells.add(cell);
        }
    }

    /*
     * takes a cell and runs it against all 
     * other existing cells and runs the interact with method.
     */
    private void handleCellInteraction() {
        for (int i = 0; i < this.cells.size(); i++){
            Cell cell1 = this.cells.get(i);
            for (int j = i + 1; j < this.cells.size(); j++){
                Cell cell2 = this.cells.get(j);
                cell2.interactWith(cell1);
            }
        }
    }
}


