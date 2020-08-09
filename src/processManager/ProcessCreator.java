package processManager;

import java.io.File;
import java.io.IOException;

public class ProcessCreator {



    public static Process createAnimal(int type, int index, int i, int j , int n , int m){
        try {

            ProcessBuilder builder = new ProcessBuilder("java","animal.Main",
                    String.valueOf(type),
                    String.valueOf(index),
                    String.valueOf(i),
                    String.valueOf(j),
                    String.valueOf(n),
                    String.valueOf(m)
                    );
            File file = new File("out/production/WildLifeSimulator2");
            builder.directory(file);
            Process process=builder.start();
            return process ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null ;
    }

    public static Process createAnimal(int type, int index, int n , int m){
        try {

            ProcessBuilder builder = new ProcessBuilder("java","animal.Animal",
                    String.valueOf(type),
                    String.valueOf(index),
                    String.valueOf(n),
                    String.valueOf(m)
            );
            File file = new File("out/production/MultiProcessing");
            builder.directory(file);
            Process process=builder.start();
            return process ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null ;
    }

}
