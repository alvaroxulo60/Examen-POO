package domain;


import exceptions.DatosException;
import io.MiEntradaSalida;

public class Familia extends Invitado implements Regalador {

    // Constantes y una variable

    public static final int MODIFICACION_POR_TARTA = 20;
    public static final int MODIFICACION_POR_MUSICA = 20;
    public static final int MODIFICACION_POR_BAILE = 15;

    private Regalos regalo;

    //Constructor de la clase

    public Familia(String nombre, int hambre, int aburrimiento) throws DatosException {
        super(nombre, hambre, aburrimiento);
        setRegalo();
    }

    /*
    Seteamos un regalo aleatorio entre las dos opciones disponibles porque los familiares
    porque los familiares no regalan juguetes
     */

    @Override
    public void setRegalo() {
        int random;
        do {
            random = MiEntradaSalida.generaAleatorioEntre(0,Regalos.values().length,false);
        }while (Regalos.values()[random].equals(Regalos.JUGUETES));
        regalo = Regalos.values()[random];
    }

    //get de regalo

    @Override
    public Regalos getRegalo() {
        return regalo;
    }

    /**
     * Metodo para reaccionar al evento del cumpleaños
     * @param evento
     */

    @Override
    public void reaccionar(Evento evento) {
       switch (evento){
           case CORTE_TARTA -> {
               this.modificarHambre(-MODIFICACION_POR_TARTA); //Baja su hambre 20 puntos
           }
           case MUSICA_ALTA -> {
               this.modificarAburrimiento(MODIFICACION_POR_MUSICA); // Sube su aburrimiento
               this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER); // Sube su hambre en 10 porque en todas las rondas que no se come sube el hambre
           }
           case MUSICA_BAJA -> {
               this.modificarAburrimiento(-MODIFICACION_POR_MUSICA);//Baja el aburrimiento
               this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);
           }
           case CHARLITA_COLOQUIAL -> {
               this.modificarAburrimiento(-MODIFICACION_POR_CHARLITA_COLOQUIAL);//Baja el aburrimiento
               this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);
           }
           case BAILE -> {
               this.modificarAburrimiento(-MODIFICACION_POR_BAILE);//Baja el aburrimiento
               this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);
           }
           case PIÑATA -> {
               this.modificarAburrimiento(-MODIFICACION_DIVERSION_POR_PIÑATA);//baja el aburrimiento
               this.modificarHambre(-MODIFICACION_HAMBRE_POR_PIÑATA);//baja el hambre
           }
           case APERTURA_REGALOS -> {
               this.modificarAburrimiento(-MODIFICACION_POR_REGALOS);//Baja el aburrimiento
               this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);
           }
       }
    }

    //Comprueba los requisitos para que el familiar se vaya de la fiesta
    @Override
    public void irseDeLaFiesta() {
        if (getAburrimiento() ==  100 || getHambre() == 100){
            setEstaEnFiesta(false);
        }
    }


}
