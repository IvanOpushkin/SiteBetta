import java.io.File;
import java.io.IOException;

public class Renamilka {


    public static void main(String[] argv) throws IOException {

        File folder = new File("C:\\Users\\Mega\\Downloads\\ResizedPhotos.0 (1)");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {

                //Важен порядок запуска, чтобы Осн не переживало на всех фотках
                String x = listOfFiles[i].getName().replace("основа","osnova");
               x = x.replace(".jpg.jpg.jpg","");

                File f = new File("C:\\Users\\Mega\\Downloads\\ResizedPhotos.0 (1)\\"+listOfFiles[i].getName());

               f.renameTo(new File("C:\\Users\\Mega\\Downloads\\ResizedPhotos.0 (1)\\"+x));
            }
        }

        System.out.println("conversion is done");
    }
}