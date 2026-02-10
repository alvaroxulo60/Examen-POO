package domain;

import exceptions.DatosException;

public abstract class Invitado {

    // Constantes y variables

    public static final int MODIFICACION_HAMBRE_POR_PIÑATA = 10;
    public static final int MODIFICACION_HAMBRE_POR_RONDA_SIN_COMER = 10;
    public static final int MODIFICACION_DIVERSION_POR_PIÑATA = 20;
    public static final int MODIFICACION_POR_REGALOS = 10;
    public static final int MODIFICACION_POR_CHARLITA_COLOQUIAL = 20;


    private String nombre;
    private int hambre;
    private int aburrimiento;
    private boolean estaEnFiesta;

    // Constructor de la clase abstracta

    public Invitado(String nombre, int hambre, int aburrimiento) throws DatosException {
        this.nombre = nombre;
        setHambre(hambre);
        setAburrimiento(hambre);
        estaEnFiesta = true;
    }

    private void setHambre(int hambre)throws DatosException  {
        if (hambre < 0 || hambre > 100){
            throw new DatosException("El hambre introducido no es válido");
        }
        this.hambre = hambre;
    }

    public void setEstaEnFiesta(boolean estaEnFiesta) {
        this.estaEnFiesta = estaEnFiesta;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isEstaEnFiesta() {
        return estaEnFiesta;
    }

    public int getHambre() {
        return hambre;
    }

    public int getAburrimiento() {
        return aburrimiento;
    }

    private void setAburrimiento(int aburrimiento) throws DatosException {
        if (aburrimiento < 0 || aburrimiento > 100){
            throw new DatosException("El aburrimiento introducido no es válido");
        }
        this.aburrimiento = aburrimiento;
    }

    /**
     * Dos metodos para modificar tanto el hambre como el aburrimiento de cada invitado
     * @param modificacion la cantidad a modificar
     */
    public void modificarAburrimiento(int modificacion){
        if (aburrimiento+modificacion < 0 || aburrimiento == 0){
            this.aburrimiento = 0;
        }
        if (aburrimiento + modificacion > 100 || aburrimiento == 100){
            this.aburrimiento = 100;
        }
        this.aburrimiento += modificacion;
    }

    public void modificarHambre(int modificacion){
        if (hambre+modificacion < 0 || hambre == 0){
            this.hambre = 0;
        }
        if (hambre + modificacion > 100 || hambre == 100){
            this.hambre = 100;
        }
        this.hambre += modificacion;
    }

    public abstract void reaccionar(Evento evento);

    public abstract void irseDeLaFiesta();
}
