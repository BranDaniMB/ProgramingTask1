package tareaprogramada;

//imports
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Programming task Final Version Date: October 01, 2017.
 *
 * @authors BranDaniMB, Greivin, Carlos
 */
public class Picture {

    //atributos
    private Figure figures[] = new Figure[0];
    private BufferedImage image;

    //metodos constructores
    public Picture() {
    }
    public Picture(BufferedImage image) {
        this.image = image;
    }
    public Picture(Figure figuras[], BufferedImage image) {
        this.figures = figuras;
        this.image = image;
    }
    
    //sets
    public void setFiguras(Figure[] figures) {
        this.figures = figures;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    //gets
    public Figure[] getFiguras() {
        return this.figures;
    }
    public BufferedImage getImage() {
        return this.image;
    }
    
    //añadir figuras al vector de figuras de la clase Picture
    public void addFigure(Figure newFigure) {
        Figure newFigures[] = new Figure[this.figures.length + 1];
        if (this.figures.length > 0) {
            System.arraycopy(this.figures, 0, newFigures, 0, this.figures.length);
        }
        newFigures[newFigures.length - 1] = newFigure;

        this.figures = newFigures;
    }
    
    //ordena las figuras de manera descendente
    public void orderFigures() {
        Figure temp;
        for (int i = 0; i < this.figures.length; i++) {
            for (int j = 1; j < (this.figures.length - i); j++) {
                if (this.figures[j - 1].getSize() < this.figures[j].getSize()) {
                    temp = this.figures[j - 1];
                    this.figures[j - 1] = this.figures[j];
                    this.figures[j] = temp;
                }
            }
        }
    }
    
    //colorea la figura mas grande
    public void chaseColor() {
        for (int index = 0; index < this.figures.length; index++) {
            if (index == 0) {
                this.figures[index].passToCyan(this.image);
            } else {
                this.figures[index].passToBlack(this.image);
            }
        }
    }
    //// METODOS PARA EL RECORRIDO POR FIGURA
    //metodo de recorrido principal para la opcion 1
    public void travelForFigure() {
        for (int row = 0; row < this.image.getHeight(); row++) {
            for (int column = 0; column < this.image.getWidth(); column++) {
                if (this.image.getRGB(column, row) != Color.WHITE.getRGB() && this.image.getRGB(column, row) == Color.BLACK.getRGB()) {
                    Figure figure = new Figure();
                    figure.figurePath(image, column, row);
                    addFigure(figure);
                    figure.passToWhite(image);
                }
            }
        }
    }
    ////METODOS PARA RECORRIDO LINEAL
    //Metodo de recorrido lineal
    public void linealTravel() {
        for (int row = 0; row < this.image.getHeight(); row++) {
            for (int column = 0; column < this.image.getWidth(); column++) {
                if (this.image.getRGB(column, row) != Color.WHITE.getRGB()) {
                    this.newPixel(new Pixel(column, row));
                }
            }
        }
    }
    //gestiona el nuevo punto para crear con el una nueva figura o agregarlo a una ya creada.
    public void newPixel(Pixel newPixel) {
        boolean isParent = false;
        int figure = 0;
        if (this.figures.length > 0) {
            for (int indexOfFigures = 0; indexOfFigures < this.figures.length; indexOfFigures++) {
                for (int indexOfPixels = 0; indexOfPixels < this.figures[indexOfFigures].getPixels().length; indexOfPixels++) {
                    if (getParents(newPixel, this.figures[indexOfFigures].getPixels()[indexOfPixels])) {
                        isParent = true;
                        figure = indexOfFigures;
                        break;
                    }
                }
                if (isParent) {
                    break;
                }
            }
        }
        if (isParent) {
            this.figures[figure].addPixel(newPixel);
            addParents(newPixel, figure);
        } else {
            Figure newFigure = new Figure();
            newFigure.addPixel(newPixel);
            addFigure(newFigure);
        }
    }
    
    //busca si el pixel nuevo es pariente de uno contenido en alguna figura vieja
    public boolean getParents(Pixel newPixel, Pixel pixel) {
        if ((pixel.getX() == newPixel.getX()) && (pixel.getY() == newPixel.getY())) {
            return false;
        } else if ((pixel.getX() == newPixel.getX() - 1) && (pixel.getY() == newPixel.getY() - 1)) {
            return true;
        } else if ((pixel.getX() == newPixel.getX()) && (pixel.getY() == newPixel.getY() - 1)) {
            return true;
        } else if ((pixel.getX() == newPixel.getX() + 1) && (pixel.getY() == newPixel.getY() - 1)) {
            return true;
        } else if ((pixel.getX() == newPixel.getX() - 1) && (pixel.getY() == newPixel.getY())) {
            return true;
        } else if ((pixel.getX() == newPixel.getX() + 1) && (pixel.getY() == newPixel.getY())) {
            return true;
        } else if ((pixel.getX() == newPixel.getX() - 1) && (pixel.getY() == newPixel.getY() + 1)) {
            return true;
        } else if ((pixel.getX() == newPixel.getX()) && (pixel.getY() == newPixel.getY() + 1)) {
            return true;
        } else if ((pixel.getX() == newPixel.getX() + 1) && (pixel.getY() == newPixel.getY() + 1)) {
            return true;
        }
        return false;
    }
    
    //Agrega los parientes diferentes a blanco del pixel nuevo a la figura correspondiente
    public void addParents(Pixel newPixel, int figure) {
        Pixel[] parents = {
            new Pixel(newPixel.getX() - 1, newPixel.getY() - 1),
            new Pixel(newPixel.getX(), newPixel.getY() - 1),
            new Pixel(newPixel.getX() + 1, newPixel.getY() - 1),
            new Pixel(newPixel.getX() - 1, newPixel.getY()),
            new Pixel(newPixel.getX() + 1, newPixel.getY()),
            new Pixel(newPixel.getX() - 1, newPixel.getY() + 1),
            new Pixel(newPixel.getX(), newPixel.getY() + 1),
            new Pixel(newPixel.getX() + 1, newPixel.getY() + 1),
        };
        for (int index = 0; index < parents.length; index++) {
            if (this.image.getRGB(parents[index].getX(), parents[index].getY()) != Color.WHITE.getRGB()) {
                this.figures[figure].addPixel(parents[index]);
            }
        }
    }
}
