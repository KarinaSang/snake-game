import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Snake {
    private int fruitCount;
    private int level;
    private int score;
    private int vX;
    private int vY;
    private int time;
    private char dir;
    private String msg;
    private Point head;
    private LinkedList<Point> points;


    public Snake(){
        level = 1;
        fruitCount = 0;
        score = 0;
        vX = 20; //start moving right
        vY = 0;
        time = 30;
        dir = 'r';
        head = new Point(100, 100);
        points = new LinkedList<>();
        msg = "";
    }

    public void reset(){
        fruitCount = 0;
        score = 0;
        vX = 20; //start moving right
        vY = 0;
        time = 30;
        dir = 'r';
        head = new Point(100, 100);
        points = new LinkedList<>();
    }

    public void right(){
        dir = 'r';
        vX = 20;
        vY = 0;
    }

    public void left(){
        dir = 'l';
        vX = -20;
        vY = 0;
    }

    public void up(){
        dir = 'u';
        vX = 0;
        vY = -20;
    }

    public void down(){
        dir = 'd';
        vX = 0;
        vY = 20;
    }

    //moving the snake
    public void move(){
        points.addFirst(new Point(head.getX(), head.getY()));
        points.removeLast();
        head = head.translate(vX, vY);
    }

    //after eating a fruit
    public void extend(){
        points.addFirst(new Point(head.getX(), head.getY()));
        head = head.translate(vX, vY);
    }


    //check if snake collides with wall or itself
    public boolean isSafe(){
        boolean bodyHit = false;

        int i = 0;
        while(i < points.size()){
            Point p = points.get(i);
            bodyHit = head.getX() == p.getX() && head.getY() == p.getY();
            if(bodyHit){
                msg = "Oops, you bumped into yourself and you died!";
                return false;
            }
            ++i;
        }

        boolean wallHit = head.getX() >= Grid.getWIDTH() || head.getX() <= 0
                || head.getY() >= Grid.getHEIGHT() || head.getY() <= 0;

        if(wallHit){
            msg = "Oops, you hit a wall and you died!";
        }

        return !wallHit && !bodyHit;
    }

    public boolean eat(Point fruit){
        return head.getX() <= fruit.getX()+10 && head.getX() >= fruit.getX()-10
                && head.getY() <= fruit.getY()+10 && head.getY() >= fruit.getY()-10;
    }



    public int getvX() {
        return vX;
    }

    public void setvX(int vX) {
        this.vX = vX;
    }

    public int getvY() {
        return vY;
    }

    public void setvY(int vY) {
        this.vY = vY;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LinkedList<Point> getPoints() {
        return points;
    }

    public void setPoints(LinkedList<Point> points) {
        this.points = points;
    }

    public char getDir() {
        return dir;
    }

    public void setDir(char dir) {
        this.dir = dir;
    }

    public Point getHead() {
        return head;
    }

    public void setHead(Point head) {
        this.head = head;
    }

    public int getFruitCount() {
        return fruitCount;
    }

    public void setFruitCount(int fruitCount) {
        this.fruitCount = fruitCount;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score){
        this.score += score;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
