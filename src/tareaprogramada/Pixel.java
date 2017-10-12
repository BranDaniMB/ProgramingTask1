package tareaprogramada;

/**
 * Programming task Final Version Date: October 01, 2017.
 *
 * @authors BranDaniMB, Greivin, Carlos
 */
public class Pixel {
    
    //Atributos
    private int X;
    private int Y;

    //Metodos constructores
    public Pixel() {

    }
    public Pixel(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
    
    //sets
    public void setX(int X) {
        this.X = X;
    }
    public void setY(int Y) {
        this.Y = Y;
    }
    
    //gets
    public int getX() {
        return this.X;
    }
    public int getY() {
        return this.Y;
    }
}
