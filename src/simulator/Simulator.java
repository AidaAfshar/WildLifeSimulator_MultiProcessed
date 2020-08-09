package simulator;

import jungle.Cell;
import jungle.Jungle;
import logger.Logger;
import pseudoAnimal.PseudoAnimal;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Simulator {

    int r ;
    int s ;
    int t ;
    int n ;
    int m ;
    int k ;


    ArrayList<PseudoAnimal>[] animals;
    Jungle jungle ;

    Timer timer ;
    static int time = 1;
    static Logger logger ;

    public Simulator(int r, int s, int t, int n, int m, int k) {
        this.r=r;
        this.s=s;
        this.t=t;
        this.n=n;
        this.m=m;
        this.k=k;
        initialize();
    }

    void initialize(){
        createLogger();
        createJungle() ;
        createAnimals();
        fillTheJungle();
    }

    void createLogger(){
        logger = new Logger();
    }

    void createJungle(){
        jungle = new Jungle(n,m,k,r,s);
    }

    void createAnimals(){
        animals = new ArrayList[r];
        for(int i=0 ; i<r ; i++)
            animals[i] = new ArrayList<>();
        for(int i=0 ; i<r ; i++)
            for(int j=0 ; j<s ; j++){
                PseudoAnimal animal = new PseudoAnimal(i+1,j+1,n,m,jungle,this);
                animals[i].add(animal) ;
            }
    }

    void fillTheJungle(){
        jungle.place(animals);
    }

    public void start(){

        printJungle();
        for(int i=0 ; i<9*m ; i++)
            System.out.print("#");
        System.out.println();

        giveBirthToAnimals();
        setUpTimer();

    }

    void giveBirthToAnimals(){
        for(int i=0 ; i<r ; i++)
            for(int j=0 ; j<s ;  j++)
                animals[i].get(j).start();
    }

    void setUpTimer(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                kill() ;
                render(time);
                reproduce(time);
                time+=1 ;
            }
        },1000,1000);
    }

    void kill(){
        jungle.balanceCapacity();
        jungle.eatAnimals();
    }

    synchronized void reproduce(int time){
        for(int i=0 ; i<r ; i++){
            if(time%(i+1)==0){
                int size = animals[i].size() ;
                for(int j=0 ; j<size;j++){
                    PseudoAnimal parent = animals[i].get(j) ;
                    if(parent.isAlive()){
                        PseudoAnimal child=new PseudoAnimal(
                                i + 1,
                                size + j,
                                parent.getI(),
                                parent.getJ(),
                                jungle,
                                this
                        );
                        Cell cell=jungle.getCell(parent.getI(), parent.getJ());
                        //synchronized (cell) {
                        animals[i].add(child);
                        cell.add(child);
                        logBirth(parent.getType(), jungle.getCell(parent.getI(), parent.getJ()));
                        //}
                        child.start();
                    }
                }
            }
        }

    }


    public synchronized void removeAnimal(PseudoAnimal animal){
        int type = animal.getType();
        animals[type-1].remove(animal);
    }

    void render(int time){
        if(time%t == 0){
            printJungle();
            for(int i=0 ; i<9*m ; i++)
                System.out.print("#");
            System.out.println();
        }
    }



    public void printJungle(){
        jungle.print();
    }

    public static void logMovement(int animalType, Cell formerCell , Cell targetCell){
        logger.logMovement(animalType,formerCell,targetCell,time);
    }

    public static void logBirth(int animalType, Cell currentCell){
        logger.logBirth(animalType,currentCell,time);
    }

    public static void logDeath(int animalType ,Cell currentCell,String reason){
        logger.logDeath(animalType,currentCell,time,reason);
    }

}
