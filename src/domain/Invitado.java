package domain;

public abstract class Invitado {
    public static final int MODIFICACION_HAMBRE_POR_PIÑATA = 10;
    public static final int MODIFICACION_DIVERSION_POR_PIÑATA = 20;
    public static final int MODIFICACION_POR_REGALOS = 10;
    public static final int MODIFICACION_POR_CHARLITA_COLOQUIAL = 20;


    private String nombre;
    private int hambre;
    private int aburrimiento;

    public Invitado(String nombre, int hambre, int aburrimiento)  {
        this.nombre = nombre;
        setHambre(hambre);
        setAburrimiento(hambre);
    }

    private void setHambre(int hambre)  {
        this.hambre = hambre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHambre() {
        return hambre;
    }

    public int getAburrimiento() {
        return aburrimiento;
    }

    private void setAburrimiento(int aburrimiento)  {
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
        this.aburrimiento += modificacion;
    }

    public void modificarHambre(int modificacion){
        if (hambre+modificacion < 0 || hambre == 0){
            this.hambre = 0;
        }
        this.hambre += modificacion;
    }

    public abstract void reaccionar(Evento evento);
}
