import javafx.scene.canvas.GraphicsContext;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class GameTimer implements Runnable{

    private final GraphicsContext gc;
    private Grid grid;
    private int timer;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 800;

    public GameTimer(final GraphicsContext gc, Grid grid) {
        this.gc = gc;
        this.grid = grid;
        timer = 0;
    }

    @Override
    public void run(){
        Snake snake = grid.getSnake();
        Point[] fruit = grid.getFruits();
        while(true) {
            if (snake.getLevel() != 0 && snake.getLevel() != 4) {

                try {
                    while (grid.getPause()) {
                        Thread.sleep(200);
                    }

                    Thread.sleep(150 / snake.getLevel());
                    ++timer;

                    if (timer == 10 * snake.getLevel()) { //one sec
                        timer = 0;
                        snake.setTime(snake.getTime() - 1);
                    }
                } catch (InterruptedException e) {
                }

                snake.move();

                //timer done
                if (snake.getTime() == 0 && (snake.getLevel() == 1 || snake.getLevel() == 2)) {
                    snake.setTime(30);
                    snake.setFruitCount(0);
                    snake.setLevel(snake.getLevel() + 1);
                }

                if ((snake.getFruitCount() == 5 && snake.getLevel() == 1) ||
                        (snake.getFruitCount() == 10 && snake.getLevel() == 2)) {
                    snake.setFruitCount(0);
                    snake.setTime(30);
                    snake.setLevel(snake.getLevel() + 1);
                }
                else if (snake.getFruitCount() == 15 && snake.getLevel() == 3) {
                    snake.setMsg("Congratulations! You ate all fruits!");
                    snake.setLevel(4);
                }

                if (!snake.isSafe()) {
                    snake.setLevel(4);
                } else if (snake.eat(fruit[snake.getFruitCount()])) {
                    snake.extend();
                    snake.addScore(10 * snake.getLevel());
                    snake.setFruitCount(1 + snake.getFruitCount());
                }

                if(snake.getLevel() == 4){
                    gc.clearRect(0, 0, WIDTH, HEIGHT);
                    Painter.paintGameOver(gc, grid);
                }
                else{
                    gc.clearRect(0, 0, WIDTH, HEIGHT);
                    Painter.paint(gc, grid);
                }

            }
            else{
                snake.reset();
            }
        }
    }
}
