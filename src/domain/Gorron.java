package domain;

import exceptions.DatosException;

public class Gorron extends Invitado{

    //Constantes y variables

    public static final int MODIFICACION_POR_TARTA = 30;
    public static final int MODIFICACION_POR_MUSICA = 30;
    public static final int MODIFICACION_POR_BAILE = 5;
    public static final int MODIFICACION_POR_ROBAR_COMIDA = 5;
    public static final int MODIFICACION_POR_CHARLITA_COLOQUIAL = 10;
    public static final int HAMBRE = 90;

    //Constructor

    public Gorron(String nombre, int aburrimiento)throws DatosException {
        super(nombre, HAMBRE, aburrimiento);
    }

    @Override
    public void reaccionar(Evento evento) {
        switch (evento){
            case CORTE_TARTA -> {
                this.modificarHambre(-MODIFICACION_POR_TARTA);// Baja hambre
            }
            case MUSICA_ALTA -> {
                robarComida(MODIFICACION_POR_ROBAR_COMIDA*2);//Baja hambre
            }
            case MUSICA_BAJA -> {
                this.modificarAburrimiento(MODIFICACION_POR_MUSICA);//Sube aburrimiento
                this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);//sube hambre
            }
            case CHARLITA_COLOQUIAL -> {
                this.modificarAburrimiento(MODIFICACION_POR_CHARLITA_COLOQUIAL);//Sube aburrimiento
                robarComida(MODIFICACION_POR_ROBAR_COMIDA);
            }
            case BAILE -> {
                this.modificarAburrimiento(-MODIFICACION_POR_BAILE);//Baja aburrimiento
                robarComida(MODIFICACION_POR_ROBAR_COMIDA*2);//baja hambre
            }
            case PIÑATA -> {
                this.modificarAburrimiento(-MODIFICACION_DIVERSION_POR_PIÑATA);// baja aburrimiento
                this.modificarHambre(-MODIFICACION_HAMBRE_POR_PIÑATA*2);//baja hambre
            }
            case APERTURA_REGALOS -> {
                this.modificarAburrimiento(-MODIFICACION_POR_REGALOS);//baja aburrimiento
                this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);//Sube hambre
            }
        }
    }

    /**
     * Metodo para cambiar el hambre porque roba comida
     * @param modificacion
     */
    public void robarComida(int modificacion){
        this.modificarHambre(-modificacion);
    }

    /**
     * Comprueba los requisitos para que el gorrón se vaya de la fiesta;
     */
    @Override
    public void irseDeLaFiesta() {
        if (getHambre() == 0){
            setEstaEnFiesta(false);
        }
    }
}
