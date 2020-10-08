//Javier Enrique Luna Díaz
import javax.swing.JTextArea;

public class Hilo extends Thread {
    private JTextArea area;
    private RCompartido rc;
    private boolean pausa;
    private boolean detener;
    private boolean desactivar;
    
    Hilo(JTextArea area, RCompartido rc){
        this.area = area;
        this.rc = rc;
        this.pausa = false;
        this.detener = false;
    }
    
    public synchronized void pausar(){
        pausa = true;
    }
    
    public synchronized void reanudar(){
        pausa = false;
        this.notify();
    }
    
    public synchronized void parar(){
        detener = true;
    }
    
    public synchronized void desactivar(){   //Se agregó para simular la falla que tiene nuestto algoritmo
        rc.desactivarInterrupciones();       //Desactivamos nuestras interrupciones sin necesidad del uso de un proceso
        desactivar = true;                   //Inhabilitando el acceso al recurso para todos los procesos        
        rc.setDisponible(false);
    }
    
    public void run(){
        while(!this.detener){                   //Seguimos las indicaciones de nuestro algoritmo
            if(rc.estadoDeLasInterrupciones()){ //Verificamos nuestro estado de nuestras interrupciones
                rc.desactivarInterrupciones();      //Desactivamos nuestras interrupciones antes de entrar a la sección crítica
                System.out.println(rc.getDatoCompartido()+" desactivo las interrupciones");
                try{
                    synchronized(this){
                        if (pausa || !rc.isDisponible() || rc.estadoDeLasInterrupciones() || desactivar){               //Verificamos si es necesario hacer una pausa
                            wait();                                                 //Ya sea porque el usuario lo solicita o por las interrupciones
                        }
                    }
                    rc.setDatoCompartido(this.getName());
                    area.append(rc.getDatoCompartido()+ "\n");
                    rc.activarInterrupciones();                 //Activamos las interrupciones una vez salimos de la sección crítica
                    sleep(1500);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
