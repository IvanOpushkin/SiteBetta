import java.io.File;
import java.io.IOException;

public class Renamilka {


    public static void main(String[] argv) throws IOException {

        File folder = new File("C:\\Users\\Mega\\Downloads\\Фотки под сайт (МАЛЕНЬКИЕ)\\PhotoCablesMendeleev");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {

                String x = listOfFiles[i].getName().replace("осн","osnova");
               x = x.replace(".jpg.jpg.jpg","");

                File f = new File("C:\\Users\\Mega\\Downloads\\Фотки под сайт (МАЛЕНЬКИЕ)\\PhotoCablesMendeleev\\"+listOfFiles[i].getName());

               f.renameTo(new File("C:\\Users\\Mega\\Downloads\\Фотки под сайт (МАЛЕНЬКИЕ)\\PhotoCablesMendeleev\\"+x));
            }
        }

        System.out.println("conversion is done");
    }
}