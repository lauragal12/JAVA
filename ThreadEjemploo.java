package threadejemploo;
/**
 * @author karen
 */
public class ThreadEjemploo implements Runnable {

    public void run(){
    for (int i=0;i<5; i++)
    System.out.println(i +" "+
    Thread.currentThread().getName());
    System.out.println("Termina thread " +
    Thread.currentThread().getName());
    }

    public static void main (String [] args){
        new Thread (new ThreadEjemploo(), "Lenguajes").start();
        new Thread (new ThreadEjemploo(), "Concurrentes").start();
        System.out.println("Termina thread main");
    }

}
