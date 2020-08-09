package animal;

import java.util.ArrayList;
import java.util.Random;

public class Animal {

    int type;
    int index ;
    int weight ;
    int lifeTime ;
    int i;
    int j;
    boolean alive = false;
    int n ;
    int m ;

    Random random ;

    AnimalCommunicator communicator ;

    public Animal(int type, int index, int i, int j , int n , int m) {
        this.type=type;
        this.index = index ;
        this.weight = type ;
        this.lifeTime = type ;
        this.i=i;
        this.j=j ;
        this.n = n ;
        this.m = m ;
        initialize();
    }

    public Animal(int type, int index, int n , int m) {
        //in case of using this constructor remember to setCoordinates of the animal immediately
        this.type=type ;
        this.index=index ;
        this.weight = type ;
        this.lifeTime = type ;
        this.n = n ;
        this.m = m ;
        initialize();
    }


    void initialize(){
        communicator = new AnimalCommunicator(this);
        communicator.start();
        random = new Random();
    }

    public void requestForMovement(){
        ArrayList<int[]> neighborsIndex = getNeighborCells(i,j,n,m) ;
        int r = random.nextInt(neighborsIndex.size()) ;
        int[] targetIndex = neighborsIndex.get(r) ;
        communicator.sendMovementRequestFor(targetIndex[0],targetIndex[1]);
        //System.out.println(targetIndex[0]+","+targetIndex[1]);
    }

    public void start(){
        try {
            while(alive){
                requestForMovement();
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void startLiving(){
        alive = true ;
    }


    public int getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public int getWeight() {
        return weight;
    }

    public void setCoordinates(int i, int j){
        this.i= i ;
        this.j= j ;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean animalIsAlive(){
        return alive ;
    }


    public static ArrayList<int[]> getNeighborCells(int i , int j, int n , int m ){
        if(i==0)
            return firstRow(j,m) ;
        else if(i==n-1)
            return lastRow(j,n,m) ;
        else
            return others(i,j,m) ;
    }

    private static ArrayList<int[]> firstRow(int j ,int m){
        ArrayList<int[]> indexes=new ArrayList<>();
        if(j==0){
            int[] A1 = {0,1} ;
            int[] A2 = {1,0} ;
            int[] A3 = {1,1} ;
            indexes.add(A1) ;
            indexes.add(A2) ;
            indexes.add(A3) ;
        }else if(j==m-1){
            int[] A1 = {0,m-2} ;
            int[] A2 = {1,m-1} ;
            int[] A3 = {1,m-2} ;
            indexes.add(A1) ;
            indexes.add(A2) ;
            indexes.add(A3) ;
        }else{
            int[] A1 = {0,j-1} ;
            int[] A2 = {0,j+1} ;
            int[] A3 = {1,j-1} ;
            int[] A4 = {1,j} ;
            int[] A5 = {1,j+1} ;
            indexes.add(A1) ;
            indexes.add(A2) ;
            indexes.add(A3) ;
            indexes.add(A4) ;
            indexes.add(A5) ;
        }
        return indexes ;
    }



    private static ArrayList<int[]> lastRow(int j, int n , int m){
        ArrayList<int[]> indexes=new ArrayList<>();
        if(j==0){
            int[] A1 = {n-1,1} ;
            int[] A2 = {n-2,0} ;
            int[] A3 = {n-2,1} ;
            indexes.add(A1) ;
            indexes.add(A2) ;
            indexes.add(A3) ;
        }else if(j==m-1){
            int[] A1 = {n-1,m-2} ;
            int[] A2 = {n-2,m-1} ;
            int[] A3 = {n-2,m-2} ;
            indexes.add(A1) ;
            indexes.add(A2) ;
            indexes.add(A3) ;
        }else{
            int[] A1 = {n-1,j-1} ;
            int[] A2 = {n-1,j+1} ;
            int[] A3 = {n-2,j-1} ;
            int[] A4 = {n-2,j} ;
            int[] A5 = {n-2,j+1} ;
            indexes.add(A1) ;
            indexes.add(A2) ;
            indexes.add(A3) ;
            indexes.add(A4) ;
            indexes.add(A5) ;
        }
        return indexes ;
    }

    private static ArrayList<int[]> others(int i , int j , int m){
        ArrayList<int[]> indexes=new ArrayList<>();
        if(j==0){
            int[] A1={i-1,0};
            int[] A2={i-1,1};
            int[] A3={i,1};
            int[] A4={i+1,0};
            int[] A5={i+1,1};
            indexes.add(A1);
            indexes.add(A2);
            indexes.add(A3);
            indexes.add(A4);
            indexes.add(A5);
        }else if(j==m-1){
            int[] A1={i-1,m-1};
            int[] A2={i-1,m-2};
            int[] A3={i,m-2};
            int[] A4={i+1,m-1};
            int[] A5={i+1,m-2};
            indexes.add(A1);
            indexes.add(A2);
            indexes.add(A3);
            indexes.add(A4);
            indexes.add(A5);
        }else{
            int[] A1={i, j - 1};
            int[] A2={i, j + 1};
            int[] A3={i - 1, j};
            int[] A4={i + 1, j};
            int[] A5={i - 1, j - 1};
            int[] A6={i - 1, j + 1};
            int[] A7={i + 1, j - 1};
            int[] A8={i + 1, j + 1};
            indexes.add(A1);
            indexes.add(A2);
            indexes.add(A3);
            indexes.add(A4);
            indexes.add(A5);
            indexes.add(A6);
            indexes.add(A7);
            indexes.add(A8);
        }
        return indexes ;
    }


    public static void main(String[] args) {

        if(args.length == 6){

            int type = Integer.valueOf(args[0]);
            int index = Integer.valueOf(args[1]);
            int i = Integer.valueOf(args[2]);
            int j = Integer.valueOf(args[3]);
            int n  = Integer.valueOf(args[4]);
            int m  = Integer.valueOf(args[5]);
            Animal animal = new Animal(type,index,i,j,n,m);
            animal.start();

        }else if(args.length==4){

            int type = Integer.valueOf(args[0]);
            int index = Integer.valueOf(args[1]);
            int n = Integer.valueOf(args[2]);
            int m = Integer.valueOf(args[3]);
            Animal animal = new Animal(type,index,n,m);
            animal.start();

        }else{
            //System.out.println("Animal Class : args mismatch");

//            int type = 3;
//            int index = 6 ;
//            int n =7 ;
//            int m =6 ;
//            Animal animal = new Animal(type,index,n,m);
//            animal.start();

            ArrayList<int[]> A = getNeighborCells(1,5,7,6);
            for(int k=0 ; k<A.size() ;k++ ){
                System.out.println(A.get(k)[0]+","+A.get(k)[1]);
            }
        }

    }

}
