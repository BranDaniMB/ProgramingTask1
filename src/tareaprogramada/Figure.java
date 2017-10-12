package tareaprogramada;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Programming task Final Version Date: October 01, 2017.
 *
 * @authors BranDaniMB, Greivin, Carlos
 */
public class Figure {
    //Atributos
    private Pixel pixels[] = new Pixel[0];
    private int size = pixels.length;
    
    //Metodos constructores
    public Figure() {
    }
    public Figure(Figure figure) {
        this.pixels = figure.pixels;
        this.size = figure.size;
    }
    
    //gets
    public Pixel[] getPixels() {
        return this.pixels;
    }
    public int getSize() {
        return size;
    }
    
    //Metodo de recorrido por figura, llega al limite derecho, luego al izquierdo y baja una fila.
    public void figurePath(BufferedImage image, int column, int row) {
        boolean isConditional = true;
        int startColumn = column;
        int startRow = row;
        do {
            while (image.getRGB(column, row) != Color.WHITE.getRGB() && image.getRGB(column + 1, row) != Color.WHITE.getRGB()) {
                this.addPixel(new Pixel(column,row));
                column++;
            }
            while (image.getRGB(column, row) != Color.WHITE.getRGB() && image.getRGB(column - 1, row) != Color.WHITE.getRGB()) {
                this.addPixel(new Pixel(column, row));
                column--;
            }
            row++;

            while (image.getRGB(column, row) == Color.WHITE.getRGB()) {
                column++;
                if (column == this.maxColumn()) {
                    isConditional = false;
                    break;
                }
            }
        } while (isConditional);

        this.size = this.pixels.length;
    }
    
    //añade un pixel no repetido a la figura
    public void addPixel(Pixel pixel) {
        if (isRepeatPixel(pixel)) {
            Pixel newPixels[] = new Pixel[this.pixels.length + 1];
            if (this.pixels.length > 0) {
                System.arraycopy(this.pixels, 0, newPixels, 0, this.pixels.length);
            }

            newPixels[newPixels.length - 1] = pixel;

            this.pixels = newPixels;
        }
    }
    
    //verifica que el pixel no se encuentre repetido
    public boolean isRepeatPixel(Pixel pixel) {
        if (this.pixels.length > 0) {
            for (int index = 0; index < this.pixels.length; index++) {
                if (pixels[index].getX() == pixel.getX() && pixels[index].getY() == pixel.getY()) {
                    return false;
                }
            }
        }

        return true;
    }
    
    //usado en el recorrido por figura, retorna la maxima X conocida en la figura actual
    public int maxColumn() {
        int max = 0;
        for (int index = 0; index < this.pixels.length; index++) {
            if (this.pixels[index].getX() > max) {
                max = this.pixels[index].getX();
            }
        }

        return max;
    }
    
    //pasa el vector pixeles de una figura a blanco
    public void passToWhite(BufferedImage image) {
        for (int index = 0; index < this.pixels.length; index++) {
            image.setRGB(this.pixels[index].getX(), this.pixels[index].getY(), Color.WHITE.getRGB());
        }
    }
    
    //pasa el vector de pixeles de una figura a negro 
    public void passToBlack(BufferedImage image) {
        for (int index = 0; index < this.pixels.length; index++) {
            image.setRGB(this.pixels[index].getX(), this.pixels[index].getY(), Color.BLACK.getRGB());
        }
    }
    
    //pasa el vector de pixeles de una figura a cyan
    public void passToCyan(BufferedImage image) {
        for (int index = 0; index < this.pixels.length; index++) {
            image.setRGB(this.pixels[index].getX(), this.pixels[index].getY(), Color.CYAN.getRGB());
        }
    }

}
