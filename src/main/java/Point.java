public class Point {


    private int x;
    private int y;

    Point(Point p){
        this.x = p.getX();
        this.y = p.getY();
    }
    
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public Point translate(int vX, int vY){
        return new Point(x+vX, y+vY);
    }

}
