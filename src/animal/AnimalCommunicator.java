package animal;

import java.io.PrintWriter;
import java.util.Scanner;

public class AnimalCommunicator extends Thread {

    Animal animal ;
    Scanner scanner ;

    public AnimalCommunicator(Animal animal) {
        this.animal=animal ;
        this.scanner= new Scanner(System.in) ;
    }

    @Override
    public void run() {
        super.run();
        while(true){
            String command = scanner.nextLine();
            manage(command);
        }
    }

    void manage(String command){
        if(command.equals("Hi"))
            System.out.println("Hellooo");
        else if(command.startsWith("new Coordinate: ")){

            String[] indexes = command.substring(16).split(",");
            int i= Integer.valueOf(indexes[0]);
            int j= Integer.valueOf(indexes[1]);
            animal.setCoordinates(i,j);
            //System.out.println("I am at "+i+","+j);

        }else if(command.equals("start")){

            animal.startLiving();
            animal.start();

        }

    }

    public void sendMovementRequestFor(int i, int j){
        String string = "Movement Request For: "+i+","+j ;
        System.out.println(string);
    }

}
