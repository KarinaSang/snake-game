public class Grid {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 700;
    private static final int SIZE = 20;
    private Snake snake;
    private Point[] fruit;
    private boolean pause;

    public Grid(){
        snake = new Snake();
        fruit = new Point [15];
        for(int i = 0; i < 15; i++){
            fruit[i] = new Point(100+50*(i+1), 200+35*(i+1));
        }
        pause = false;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Point[] getFruits() {
        return fruit;
    }

    public Point getFruit(int count) {
        return fruit[count];
    }

    public void setFruit(Point[] fruit) {
        this.fruit = fruit;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getSIZE() {
        return SIZE;
    }

    public void togglePause(){
        pause = !pause;
    }

    public boolean getPause(){
        return pause;
    }

}
