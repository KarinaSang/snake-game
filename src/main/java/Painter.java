import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Painter {
    private static final int SNAKEBODY = 20;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 800;

    public static void paint(GraphicsContext gc, Grid grid){
        gc.setFill(Color.CORNSILK);
        gc.fillRect(0, 100, WIDTH, HEIGHT);

        gc.setFill(Color.AQUA);
        Snake snake = grid.getSnake();
        gc.fillRect(snake.getHead().getX(), snake.getHead().getY(),
                SNAKEBODY, SNAKEBODY);

        snake.getPoints().forEach((p) -> {
            gc.setFill(Color.PURPLE);
            gc.fillRect(p.getX(), p.getY(), SNAKEBODY, SNAKEBODY);
        });

        if(snake.getFruitCount() < 15){
            Point fruit = grid.getFruit(snake.getFruitCount());
            gc.setFill(Color.RED);
            gc.fillOval(fruit.getX(), fruit.getY(), SNAKEBODY, SNAKEBODY);
        }
        else{
            gc.clearRect(0, 0, WIDTH, HEIGHT);
            snake.setLevel(4);
            snake.setMsg("Congratulations! You ate all fruits!");
            paintGameOver(gc, grid);
            return;
        }


        gc.setFill(Color.BLACK);
        gc.fillText("Level: " + snake.getLevel(), 100, 50);
        gc.fillText("Score: " + snake.getScore(), 200, 50);
        gc.fillText("Fruit Count: " + snake.getFruitCount(), 300 , 50);
        if(snake.getLevel() == 1 || snake.getLevel() == 2){
            gc.fillText("Time: " + snake.getTime(), 450, 50);
        }
        else if(snake.getLevel() == 3){
            gc.fillText("Time: Unlimited", 450, 50);
        }


    }

    public static void paintGameOver(GraphicsContext gc, Grid grid){
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText(grid.getSnake().getMsg(), WIDTH/2, 200);
        gc.fillText("GAME OVER", WIDTH/2, 300);
        gc.fillText("Your Highest Score is: " + grid.getSnake().getScore(), WIDTH/2, 400);
    }
}
