package domain;

import exceptions.DatosException;
import io.MiEntradaSalida;

public class Colega extends Invitado implements Regalador{

    //Constantes y variables

    public static final int MODIFICACION_POR_TARTA = 20;
    public static final int MODIFICACION_POR_MUSICA = 30;
    public static final int MODIFICACION_POR_BAILE = 30;

    private Regalos regalo;

    //Constructor de colega

    public Colega(String nombre, int hambre, int aburrimiento)throws DatosException {
        super(nombre, hambre, aburrimiento);
        setRegalo();
    }



    @Override
    public void reaccionar(Evento evento) {
        switch (evento){
            case CORTE_TARTA -> {
                this.modificarHambre(-MODIFICACION_POR_TARTA);//Baja el hambre
            }
            case MUSICA_ALTA -> {
                this.modificarAburrimiento(-MODIFICACION_POR_MUSICA);//baja el aburrimiento
                this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);// Sube el hambre por ronda sin comer
            }
            case MUSICA_BAJA -> {
                this.modificarAburrimiento(MODIFICACION_POR_MUSICA);//Sube el aburrimiento
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
                this.modificarAburrimiento(-MODIFICACION_DIVERSION_POR_PIÑATA);//Baja el aburrimiento
                this.modificarHambre(-MODIFICACION_HAMBRE_POR_PIÑATA);
            }
            case APERTURA_REGALOS -> {
                this.modificarAburrimiento(-MODIFICACION_POR_REGALOS);//Baja el aburrimiento
                this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);
            }
        }
    }

    /**
     * Coger un regalo aleatorio de los 2 disponibles porque los colegas no ganan dinero
     */
    @Override
    public void setRegalo() {
        int random;
        do {
            random = MiEntradaSalida.generaAleatorioEntre(0,Regalos.values().length,false);
        }while (Regalos.values()[random].equals(Regalos.DINERO));
        regalo = Regalos.values()[random];
    }

    /**
     * get del regalo
     * @return devuelve el regalo
     */
    @Override
    public Regalos getRegalo() {
        return regalo;
    }

    /**
     * Comprueba los requisitos para irse de la fiesta
     */
    @Override
    public void irseDeLaFiesta() {
        if (getAburrimiento() ==  100 || getHambre() == 100){
            setEstaEnFiesta(false);
        }
    }
}
