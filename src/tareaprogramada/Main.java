package tareaprogramada;

//imports
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * Programming task I
 * Final Version
 * October 01, 2017.
 * @authors BranDaniMB, Greivin, Carlos
 */
public class Main {

    public static void main(String[] args) throws IOException {
        boolean ifExit = true;
        String exit;
        do {
            //Create new object Scanner, for data request
            Scanner scanner = new Scanner(System.in);
            //Print
            System.out.println("Bienvenid@, este programa recorre una imagen y colorea la figura mas grande contenida en esta.\nIngresa en la siguiente linea el nombre de tu imagen y su extensión.:\nEjemplo: Imagen.png");
            //Request file path
            System.out.print("Nombre de archivo:");
            String imagePath = scanner.nextLine();
            //Create image
            BufferedImage image = ImageIO.read(new File(imagePath));
            //Create new object Picture
            Picture mainPicture = new Picture(image);

            //Methods calls
            System.out.println("Contamos con dos tipos de recorrido; ¿Cual deseas usar?\n\t1-Recorrido por figura\n\t2-Recorrido lineal");
            int option;
            long timeStart = 0, timeEnd = 0;
            String fileName = "New image.png";
            do {
                option = Integer.parseInt(scanner.nextLine());
            } while (option != 1 && option != 2);

            switch (option) {
                case 1:
                    fileName = "New Image-travel for figure.png";
                    System.out.println("Haz elegido el recorrido por figura, este recorrido sigue los siguientes pasos:\n\t*Selecciona una fila\n\t*Recorre hasta el limite derecho\n\t*Se devuelve hasta el limite izquierdo\n\t*Baja una fila\n\t*Repite lo anterior\n-----------------------------");
                    timeStart = System.currentTimeMillis();
                    mainPicture.travelForFigure();
                    mainPicture.orderFigures();
                    mainPicture.chaseColor();
                    timeEnd = System.currentTimeMillis();
                    break;
                case 2:
                    fileName = "New Image-lineal travel.png";
                    System.out.println("Haz elegido el recorrido lineal, este recorrido sigue los siguientes pasos:\n\t*Recorre la imagen por filas y por columnas.\n\t*Al encontrar un punto diferente a blanco lo gestiona.");
                    timeStart = System.currentTimeMillis();
                    mainPicture.linealTravel();
                    mainPicture.orderFigures();
                    mainPicture.chaseColor();
                    timeEnd = System.currentTimeMillis();
                    break;
            }

            //get file path
            String path = System.getProperty("user.dir");
            //save image
            ImageIO.write(mainPicture.getImage(), "png", new File(path, fileName));
            //create file for check file saved
            File file = new File(path, fileName);
            //Print
            if (file.exists()) {
                System.out.println("El archivo se ha creado en " + ((timeEnd - timeStart) / 1000) + "s.\nSe encuentra en la ruta: " + path + "\nCon el nombre: " + fileName);
            } else {
                System.out.println("Error, el archivo no se ha creado");
            }
            System.out.println("¿Quieres salir? (si/no)");
            exit = (scanner.nextLine()).toLowerCase();
            if (exit.equals("si")) {
                ifExit = false;
                System.out.println("Adios, muchas gracias.");
            }
        } while (ifExit);
    }
}
