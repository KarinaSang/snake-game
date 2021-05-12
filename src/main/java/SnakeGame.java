import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SnakeGame extends Application{
        private static final int WIDTH = 1280;
        private static final int HEIGHT = 800;
        private static final int SNAKEWIDTH = 20;
        private GameTimer timer;
        public static Point [] fruit = new Point [15];


        public static void main(String[] args){
                Application.launch(args);
        }

        @Override
        public void start(Stage stage) throws FileNotFoundException {
                Grid grid = new Grid();

                Canvas canvas = new Canvas(WIDTH, HEIGHT);
                canvas.setFocusTraversable(true);

                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

                Group root = new Group();

                //starting scene
                Button buttonStartGame = new Button("Start Game");
                Text name = new Text("Name: Karina Sang       Userid: ycsang");
                Text text1 = new Text("The objective of this game is for you to control the snake with left" +
                        "and right arrow keys to eat as many fruits as possible.");
                Text text2 = new Text("The length of the snake increases as you eat a fruit.");
                Text text3 = new Text("If you hit a wall or hit yourself, then the game is over.");
                Text text4 = new Text("There are three levels in total, good luck have fun!");
                name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
                text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
                text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
                text3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
                text4.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));

                name.setX(50);
                text1.setX(200);
                text2.setX(450);
                text3.setX(450);
                text4.setX(450);
                name.setY(50);
                text1.setY(100);
                text2.setY(200);
                text3.setY(300);
                text4.setY(400);
                text1.setTextAlignment(TextAlignment.CENTER);
                text2.setTextAlignment(TextAlignment.CENTER);
                text3.setTextAlignment(TextAlignment.CENTER);
                text4.setTextAlignment(TextAlignment.CENTER);
                buttonStartGame.setLayoutX(600);
                buttonStartGame.setLayoutY(700);

                Image snake = new Image(new FileInputStream("src\\main\\resources\\snake.jpg"));
                ImageView snakeImageView = new ImageView(snake);
                snakeImageView.setX(500);
                snakeImageView.setY(400);
                snakeImageView.setFitWidth(300);
                snakeImageView.setFitHeight(300);

                Scene sceneStart = new Scene(new Group(name, text1, text2, text3, text4, buttonStartGame, snakeImageView), WIDTH, HEIGHT);

                //level1
                Scene scene1 = new Scene(root, WIDTH, HEIGHT);

                //buttons what to do
                buttonStartGame.setOnAction(event -> {
                        grid.getSnake().setFruitCount(0);
                        grid.getSnake().setLevel(1);
                        grid.getSnake().setTime(30);
                        stage.setScene(scene1);
                });

                //keyPressed
                scene1.addEventFilter(KeyEvent.KEY_PRESSED,  e -> {
                        switch(e.getCode()) {
                                case LEFT:
                                        if(grid.getSnake().getDir() == 'r'){
                                                grid.getSnake().up();
                                        }
                                        else if(grid.getSnake().getDir() == 'l'){
                                                grid.getSnake().down();
                                        }
                                        else if(grid.getSnake().getDir() == 'u'){
                                                grid.getSnake().left();
                                        }
                                        else if(grid.getSnake().getDir() == 'd'){
                                                grid.getSnake().right();
                                        }
                                        break;
                                case RIGHT:
                                        if(grid.getSnake().getDir() == 'r'){
                                                grid.getSnake().down();
                                        }
                                        else if(grid.getSnake().getDir() == 'l'){
                                                grid.getSnake().up();
                                        }
                                        else if(grid.getSnake().getDir() == 'u'){
                                                grid.getSnake().right();
                                        }
                                        else if(grid.getSnake().getDir() == 'd'){
                                                grid.getSnake().left();
                                        }
                                        break;
                                case DIGIT1:
                                        grid.getSnake().setFruitCount(0);
                                        stage.setScene(scene1);
                                        grid.getSnake().setLevel(1);
                                        grid.getSnake().setTime(30);
                                        break;
                                case DIGIT2:
                                        grid.getSnake().setFruitCount(0);
                                        stage.setScene(scene1);
                                        grid.getSnake().setLevel(2);
                                        grid.getSnake().setTime(30);
                                        break;
                                case DIGIT3:
                                        grid.getSnake().setFruitCount(0);
                                        stage.setScene(scene1);
                                        grid.getSnake().setLevel(3);
                                        grid.getSnake().setTime(30);
                                        break;
                                case P:
                                        grid.togglePause();
                                        break;
                                case R:
                                        grid.getSnake().setFruitCount(0);
                                        stage.setScene(sceneStart);
                                        grid.getSnake().setLevel(0);
                                        grid.getSnake().setTime(30);
                                        grid.getSnake().setMsg("");
                                        break;
                                case Q:
                                        stage.setScene(scene1);
                                        grid.getSnake().setLevel(4);
                                        grid.getSnake().setTime(30);
                                        grid.getSnake().setMsg("");
                                        break;

                        }
                });

                reset(gc, grid);

                root.getChildren().add(canvas);



                //show starting scene
                stage.setTitle("Snake Game");
                stage.setResizable(false);
                stage.setScene(sceneStart);
                stage.show();

                (new Thread(timer)).start();
        }

        public void reset(GraphicsContext gc, Grid grid){
                timer = new GameTimer(gc, grid);
                gc.clearRect(0, 0, WIDTH, HEIGHT);
                Painter.paint(gc, grid);
        }


}
