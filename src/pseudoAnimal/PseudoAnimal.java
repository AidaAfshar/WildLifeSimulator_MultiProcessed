package pseudoAnimal;

import jungle.Cell;
import jungle.Jungle;
import processManager.ProcessCreator;
import simulator.Simulator;

import java.io.PrintWriter;
import java.util.Scanner;

public class PseudoAnimal {

    Process process ;

    int type ;
    int index ;
    int i ;
    int j ;
    Jungle jungle ;

    PseudoAnimalCommunicator communicator ;
    Simulator simulator ;

    public PseudoAnimal(int type , int index, int n , int m , Jungle jungle , Simulator simulator){
        //remember to set coordinates
        this.type = type ;
        this.index = index;
        this.jungle = jungle ;
        process = ProcessCreator.createAnimal(type,index,n,m) ;
        this.simulator = simulator ;
        initialize();
    }

    void initialize(){
        PrintWriter printWriter = new PrintWriter(process.getOutputStream());
        Scanner scanner = new Scanner(process.getInputStream());
        communicator = new PseudoAnimalCommunicator(this,scanner,printWriter);

    }

    public void start(){
        send("start");
        communicator.start();
    }

    public void move(int i , int j){
        Cell targetCell = jungle.getCell(i,j);
        boolean successful = jungle.doMovement(this,targetCell);
        if(successful)
            communicator.announceNewCoordinates(i,j);
    }

    public void kill(String reason){
        Simulator.logDeath(type,jungle.getCell(i,j),reason);
        process.destroy();
        simulator.removeAnimal(this);
    }


    public void send(String msg){
        communicator.send(msg);
    }

    public boolean isAlive() {
        return process.isAlive();
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public void setCoordinates(int i, int j){
        this.i= i ;
        this.j= j ;
        communicator.announceNewCoordinates(i,j);
    }



}
