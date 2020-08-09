package logger;

import jungle.Cell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    String filePath = "src/logger/log";
    File log ;
    FileWriter writer ;

    public Logger() {
        log= new File(filePath);

        try {
            writer = new FileWriter(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void logMovement(int animalType, Cell formerCell , Cell targetCell,int time){
        try {
            writer.write(time+": ");
            writer.write(
                    "Animal Type "+animalType+" moved from ["+(formerCell.getI()+1)+","+(formerCell.getJ()+1)+"] to ["+(targetCell.getI()+1)+","+(targetCell.getJ()+1)+" ]\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void logBirth(int animalType, Cell currentCell, int time){
        try {
            writer.write(time+": ");
            writer.write(
                    "Animal Type "+animalType+" reproduced at ["+(currentCell.getI()+1)+","+(currentCell.getJ()+1)+"]\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void logDeath(int animalType, Cell currentCell, int time , String reason){
        try {
            writer.write(time+": ");
            writer.write(
                    "Animal Type "+animalType+" died at ["+(currentCell.getI()+1)+","+(currentCell.getJ()+1)+"] because of "+reason +"\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
