package principal;
import java.util.Random;
/**
 *
 * @author karen
 */
public class Principal extends Thread{
  private  static double[] vec= new double[0000000];
  private int inicio, fin;

public Principal (int inicio, int fin) {
  this.inicio = inicio;
  this.fin = fin;
}

  public static void main(String[] args) {
    iniciavec();
    vec_NOconcurrente();
    vec_concurrente();
  }
  private static void iniciavec() {
    Random rand = new Random (System.nanoTime());
    for(int i=0;i<vec.length;i++){
      vec [i]= rand.nextInt();
    }
  }
  //INSTRUCCIONES SECUENCIALES
  private static void vec_NOconcurrente() {
    double tiempo= System.nanoTime();
    for(int i=0;i<vec.length;i++){
      vec [i]/=10;
      vec [i]*=10;
      vec [i]/=10;
    }
    System.out.println("Version NO concurrente: "+((System.nanoTime()-tiempo)/1000000)+ " milisegundos");
  }
  public void run(){
    for(int i=inicio;i<fin;i++){
      vec [i]/=10;
      vec [i]*=10;
      vec [i]/=10;
    }
  }
    //INSTRUCCIONES CONCURRENTES
    private static void vec_concurrente() {
      int nproc= Runtime.getRuntime().availableProcessors();
      int inicio = 0, fin = 0;
      Principal[] prin = new Principal[nproc];
      double tiempo = System.nanoTime();
      
      for(int i=0;i<nproc;i++){
        inicio=fin;
        fin+=vec.length/nproc;
        prin[i]= new Principal (inicio, fin);
        prin[i].start();
      }
      for(int i=0;i<nproc;i++){
        try{
          prin[i].join();
        }catch(Exception e){}
      }
      System.out.println("Versión concurrente: "+((System.nanoTime() - tiempo) / 1000000)+  " milisegundo");
    }
}
    

