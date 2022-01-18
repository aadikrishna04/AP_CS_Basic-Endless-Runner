public class GamePiece {
    // Points for center of object
    private int centerX;
    private int centerY;
    // How much object goes left to right, up to down
    private int height;
    private int width;
    // Setting colors to be used in StdDraw
    private int[] color = {0, 0, 0};


    // Constructor for GamePiece object 
    public GamePiece(int centerX, int centerY, int height, int width, int[] color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.height = height;
        this.width = width;
        this.color = color;
    }

    // Accessor methods to get position of GamePiece
    public int returnX() {
        return this.centerX;
    }

    public int returnY() {
        return this.centerY;
    }

    // Mutator methods to change position of GamePiece
    public void moveX(int amount) {
        this.centerX += amount;
    }

    public void moveY(int amount) {
        this.centerY += amount;
    }

    public void setX(int position) {
        this.centerX = position;
    }

    public void setY(int position) {
        this.centerY = position;
    }

    // Draw GamePiece
    public void drawPiece() {
        // Set pen color
        StdDraw.setPenColor(color[0], color[1], color[2]);
        // Draw GamePiece with size {width, height} at center {centerX, centerY}
        StdDraw.filledRectangle(centerX, centerY, width, height);

    }

    // Get upper bound
    public int getUpperBound() {
        return this.returnY() + height;
    }

    // Get lower bound
    public int getLowerBound() {
        return this.returnY() - height;
    }

    // Get left bound
    public int getLeftBound() {
        return this.returnX() - width;
    }

    // Get right bound
    public int getRightBound() {
        return this.returnX() + width;
    }
}
