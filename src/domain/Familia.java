package domain;


import exceptions.DatosException;
import exceptions.MiEntradaSalidaException;
import io.MiEntradaSalida;

public class Familia extends Invitado implements Regalador {
    public static final int MODIFICACION_POR_TARTA = 20;
    public static final int MODIFICACION_POR_MUSICA = 20;
    public static final int MODIFICACION_POR_BAILE = 15;

    private Regalos regalo;


    public Familia(String nombre, int hambre, int aburrimiento) throws DatosException {
        super(nombre, hambre, aburrimiento);
        setRegalo();
    }
    @Override
    public void setRegalo() {
        int random = MiEntradaSalida.generaAleatorioEntre(1,2,true);
        switch (random){
            case 1 ->{
                regalo = Regalos.DINERO;
            }
            case 2 ->{
                regalo = Regalos.ROPA;
            }
        }
    }

    @Override
    public void reaccionar(Evento evento) {
       switch (evento){
           case CORTE_TARTA -> {
               this.modificarHambre(-MODIFICACION_POR_TARTA);
           }
           case MUSICA_ALTA -> {
               this.modificarAburrimiento(MODIFICACION_POR_MUSICA);
               this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);
           }
           case MUSICA_BAJA -> {
               this.modificarAburrimiento(-MODIFICACION_POR_MUSICA);
               this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);
           }
           case CHARLITA_COLOQUIAL -> {
               this.modificarAburrimiento(-MODIFICACION_POR_CHARLITA_COLOQUIAL);
               this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);
           }
           case BAILE -> {
               this.modificarAburrimiento(-MODIFICACION_POR_BAILE);
               this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);
           }
           case PIÑATA -> {
               this.modificarAburrimiento(-MODIFICACION_DIVERSION_POR_PIÑATA);
               this.modificarHambre(-MODIFICACION_HAMBRE_POR_PIÑATA);
           }
           case APERTURA_REGALOS -> {
               this.modificarAburrimiento(-MODIFICACION_POR_REGALOS);
               this.modificarHambre(MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER);
           }
       }
    }


}
