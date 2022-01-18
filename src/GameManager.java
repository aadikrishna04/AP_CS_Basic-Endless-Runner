import java.util.*;

public class GameManager {
    // Private instance variables
    private GamePiece hero;
    private boolean runGame;

    // ArrayList of enemy objects
    private ArrayList<GamePiece> blocks;

    // Constructor
    public GameManager() {
        // Create local x and y variables to offset walls later
        int y = 400;
        int x = 0;

        // Initialize GamePiece object
        this.hero = new GamePiece(250, 250, 15, 15, new int[] {0, 0, 0});

        // Initialize enemy objects
        this.blocks = new ArrayList<GamePiece>();

        // Create enemy objects and add to list of enemy objects
        GamePiece object1 = new GamePiece(25 + x, y, 15, 200, new int[] {120, 0, 0});
        GamePiece object2 = new GamePiece(475 + x, y, 15, 200, new int[] {120, 0, 0});

        for (int i = 0; i < 3; i++) {
            blocks.add(object1);
            blocks.add(object2);

            y += 200;
            x = (int)(Math.random() * (150 - -150) + -150);
        }

        // Set runGame to true
        this.runGame = true;

        // Set canvas size and scale
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(0, 500);
        StdDraw.setYscale(0, 500);
    }

    public void run() {
        while (runGame) {
            // Delete position of object in last frame when drawing new frame
            StdDraw.clear();
            this.drawGamePieces();
            this.collision();

            // Move up
            if (StdDraw.isKeyPressed(87) || StdDraw.isKeyPressed(38)) {
                // Draw game pieces
                this.hero.moveY(16);
            }

            // Move Down
            if (StdDraw.isKeyPressed(65) || StdDraw.isKeyPressed(40)) {
                // Draw game pieces
                this.hero.moveY(-16);
            }

            // Move Left
            if (StdDraw.isKeyPressed(83) || StdDraw.isKeyPressed(37)) {
                // Draw game pieces
                this.hero.moveX(-16);
            }

            // Move Right
            if (StdDraw.isKeyPressed(68) || StdDraw.isKeyPressed(39)) {
                // Draw game pieces
                this.hero.moveX(16);
            }

            // Limit framerate (StdDraw cannot handle high (60) fps)
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void drawGamePieces() {
        // Draw hero piece
        this.hero.drawPiece();
        // Draw enemy pieces
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).drawPiece();
            blocks.get(i).moveY(-3);
        }

        for (int i = 0; i < blocks.size(); i+=2) {
            if (blocks.get(i).returnY() < 0) {
                int x = (int)(Math.random() * 301) - 150;
                int y = (int) (Math.random() * 201) + 500;

                blocks.get(i).setY(y);
                blocks.get(i+1).setY(y);
                blocks.get(i).moveX(x);
                blocks.get(i+1).moveX(x);
            }
        }
    }

    public void collision() {
        for (GamePiece block : blocks) {
            if (this.hero.getLowerBound() < block.getUpperBound()) {
                if (this.hero.getUpperBound() > block.getLowerBound()) {
                    if (this.hero.getLeftBound() < block.getRightBound()) {
                        if (this.hero.getRightBound() > block.getLeftBound()) {
                            this.runGame = false;
                        }
                    }
                }
            }
        }
    }
}
