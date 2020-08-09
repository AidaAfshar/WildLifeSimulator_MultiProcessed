package pseudoAnimal;

import java.io.PrintWriter;
import java.util.Scanner;

public class PseudoAnimalCommunicator extends Thread{

    PseudoAnimal animal ;
    Scanner scanner ;
    PrintWriter printWriter;

    public PseudoAnimalCommunicator(PseudoAnimal animal , Scanner scanner,PrintWriter printWriter) {
        this.animal=animal ;
        this.scanner=scanner ;
        this.printWriter = printWriter;
    }

    @Override
    public void run() {
        super.run();
        while(animal.isAlive()){
            try {
                String command = scanner.nextLine();
                manage(command);
            }catch (Exception e){
                if(animal.isAlive())
                    e.printStackTrace();
                else
                    return;
            }

        }
    }

    void manage(String command){
        if(command.startsWith("Movement Request For: ")){
            String[] indexes = command.substring(22).split(",");
            int i= Integer.valueOf(indexes[0]);
            int j= Integer.valueOf(indexes[1]);
            //System.out.println("Movement Request For: "+i+","+j);
            animal.move(i,j);
        }else{
            System.out.println("animal " + animal.process.pid() + "said: " + command);
        }
    }

    public void send(String msg){
        printWriter.println(msg);
        printWriter.flush();
    }

    public void announceNewCoordinates(int i , int j){
        String str = "new Coordinate: "+i+","+j;
        send(str);
    }

}
