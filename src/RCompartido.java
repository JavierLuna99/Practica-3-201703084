//Javier Enrique Luna Díaz
public class RCompartido {

    private String datoCompartido;
    private Interrupcion i;
    private boolean disponible;
    
    RCompartido(){              //Estado inicial de nuestro recurso compartido
        datoCompartido = "";
        i = new Interrupcion();
        i.activar();
        disponible = true;
    }
    
    public String getDatoCompartido() {
        return datoCompartido;
    }

    public void setDatoCompartido(String datoCompartido) {
        this.datoCompartido = datoCompartido;
    }
    
    public boolean estadoDeLasInterrupciones(){    //Función para saber el estado de las interrupciones, si han sido bloqueadas o no
        return i.estado();
    }
    
    public void desactivarInterrupciones(){     //Manejo de interrupciones
        i.desactivar();        
    }
    public void activarInterrupciones(){    
        i.activar();
    }
   
    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
 
}
