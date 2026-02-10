package domain;


public class Familia extends Invitado implements Regalador {
    public static final int MODIFICACION_POR_TARTA = 20;
    public static final int MODIFICACION_POR_MUSICA = 20;
    public static final int MODIFICACION_POR_BAILE = 15;


    public Familia(String nombre, int hambre, int aburrimiento)  {
        super(nombre, hambre, aburrimiento);
    }

    @Override
    public void reaccionar(Evento evento) {
       switch (evento){
           case CORTE_TARTA -> {
               this.modificarHambre(-MODIFICACION_POR_TARTA);
           }
           case MUSICA_ALTA -> {
               this.modificarAburrimiento(MODIFICACION_POR_MUSICA);
           }
           case MUSICA_BAJA -> {
               this.modificarAburrimiento(-MODIFICACION_POR_MUSICA);
           }
           case CHARLITA_COLOQUIAL -> {
               this.modificarAburrimiento(-MODIFICACION_POR_CHARLITA_COLOQUIAL);
           }
           case BAILE -> {
               this.modificarAburrimiento(-MODIFICACION_POR_BAILE);
           }
           case PIÑATA -> {
               this.modificarAburrimiento(-MODIFICACION_DIVERSION_POR_PIÑATA);
               this.modificarHambre(-MODIFICACION_HAMBRE_POR_PIÑATA);
           }
           case APERTURA_REGALOS -> {
               this.modificarAburrimiento(-MODIFICACION_POR_REGALOS);
           }
       }
    }


}
