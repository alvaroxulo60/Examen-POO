package domain;

import exceptions.DatosException;

public class Gorron extends Invitado{
    public static final int MODIFICACION_POR_TARTA = 30;
    public static final int MODIFICACION_POR_MUSICA = 30;
    public static final int MODIFICACION_POR_BAILE = 5;
    public static final int MODIFICACION_POR_ROBAR_COMIDA = 5;
    public static final int MODIFICACION_POR_CHARLITA_COLOQUIAL = 10;
    public static final int HAMBRE = 90;

    public Gorron(String nombre, int aburrimiento)throws DatosException {
        super(nombre, HAMBRE, aburrimiento);
    }

    @Override
    public void reaccionar(Evento evento) {
        switch (evento){
            case CORTE_TARTA -> {
                this.modificarHambre(-MODIFICACION_POR_TARTA);
            }
            case MUSICA_ALTA -> {
                robarComida(MODIFICACION_POR_ROBAR_COMIDA*2);
            }
            case MUSICA_BAJA -> {
                this.modificarAburrimiento(MODIFICACION_POR_MUSICA);
                this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);
            }
            case CHARLITA_COLOQUIAL -> {
                this.modificarAburrimiento(MODIFICACION_POR_CHARLITA_COLOQUIAL);
                this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);
            }
            case BAILE -> {
                this.modificarAburrimiento(-MODIFICACION_POR_BAILE);
                robarComida(MODIFICACION_POR_ROBAR_COMIDA*2);
            }
            case PIÑATA -> {
                this.modificarAburrimiento(MODIFICACION_DIVERSION_POR_PIÑATA);
                this.modificarHambre(-MODIFICACION_HAMBRE_POR_PIÑATA*2);
            }
            case APERTURA_REGALOS -> {
                this.modificarAburrimiento(-MODIFICACION_POR_REGALOS);
                this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);
            }
        }
    }

    public void robarComida(int modificacion){
        this.modificarHambre(-modificacion);
    }

    @Override
    public void irseDeLaFiesta() {
        if (getAburrimiento() ==  100 || getHambre() == 0){
            setEstaEnFiesta(false);
        }
    }
}
