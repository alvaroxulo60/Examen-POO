package main;

import domain.*;
import exceptions.DatosException;
import io.MiEntradaSalida;

public class Main {
    private static final int ROPA_MAX = 4;
    private static final int INVITADOS_MAX = 10;


    public static void main(String[] args) {
        System.out.println("--- ¡COMIENZA EL CUMPLE DE LOLO! ---");

        //Inicializamos el array y una variable de contador para ir añadiendo a los familiares al array
        Invitado[] invitados = new Invitado[INVITADOS_MAX];
        int contador = 0;


        // TODO 2: Rellena las primeras posiciones:
        //
        try {
            invitados[contador++] = new Familia("Primo Jose", generarHambreFamilia(), generarAburrimiento());
            invitados[contador++] = new Familia("Abuelo", generarHambreFamilia(), generarAburrimiento());
            invitados[contador++] = new Familia("Tia Paqui", generarHambreFamilia(), generarAburrimiento());
            invitados[contador++] = new Colega("Álvaro", generarHambreColegas(), generarAburrimiento());
            invitados[contador++] = new Colega("Moises", generarHambreColegas(), generarAburrimiento());
            invitados[contador++] = new Colega("Javier", generarHambreColegas(), generarAburrimiento());
            invitados[contador] = new Gorron("Bermudo", generarAburrimiento());
        } catch (DatosException e) {
            System.err.println(e.getMessage());
        }

        // - 3 Familiares (con nombres y stats aleatorios)
        // - 3 Colegas (con nombres y stats aleatorios)
        // - 1 Gorrón
        // PISTA: Usa un contador para saber en qué posición del array insertas.


        int ronda = 1;
        boolean fiestaSigue = true;
        boolean yaSeAbrieronRegalos = false;
        int ropaRegalada = 0;
        int invitadosMarchados = 0;

        // Bucle de la fiesta (máximo 10 rondas)
        while (fiestaSigue && ronda <= 10) {
            System.out.println("\n--- RONDA " + ronda + " ---");
            Evento eventoActual = obtenerEventoAleatorio();

            // Evitamos que se abran regalos dos veces
            while (eventoActual == Evento.APERTURA_REGALOS && yaSeAbrieronRegalos) {
                eventoActual = obtenerEventoAleatorio();
            }

            System.out.println("Evento: " + eventoActual);

            // TODO 3: Recorre el array de invitados
            for (int i = 0; i < invitados.length; i++) {
                if (invitados[i] != null) {
                    //Comprobamos que el invitado no sea nulo y luego vemos si se ha salido de la fiesta
                    invitados[i].irseDeLaFiesta();
                    if (invitados[i].isEstaEnFiesta()) {
                        //Si sigue en la fiesta llamamos al metodo reaccionar de cada invitado
                        invitados[i].reaccionar(eventoActual);
                        if (eventoActual.equals(Evento.APERTURA_REGALOS)) { // Si es ronda de abrir regalos imprimimos que regalo le ha llevado cada persona
                            if (invitados[i] instanceof Regalador r) {
                                System.out.println(invitados[i].getNombre() + " Te ha regalado " + r.getRegalo());
                                if (r.getRegalo().equals(Regalos.ROPA)) {
                                    ropaRegalada++;
                                }
                            }

                        }
                    } else {
                        //Si el invitado se ha ido le sumamos uno al contador, ponemos un mensaje por pantalla y ponemos esa posición a null
                        invitadosMarchados++;
                        System.out.println(invitados[i].getNombre() + " se ha ido... Dice que pasa de ti");
                        invitados[i] = null;
                    }
                }
            }
            //Cambiamos el boolean de yaSeAbrieronRegalos a true si la ronda es de abrir regalos
            if (eventoActual.equals(Evento.APERTURA_REGALOS)) {
                yaSeAbrieronRegalos = true;
            }


            //Comprobamos si le han regalado ropa 4 veces y si ha pasado terminamos la fiesta
            if (ropaRegalada == 4 ){
                System.out.println("Que aburrido... Te han regalado mucha ropa...");
                fiestaSigue =  false;
            } else if (invitadosMarchados == contador) { // Comprobamos que los invitados que se han ido sea igual a los que hay en la fiesta y si son iguales terminamos la fiesta
                System.out.println("Parece que nadie te quiere. ¡Tu fiesta de cupleaños ha sido un desastre!");
                fiestaSigue = false;
            }
            // - Si se han regalado 4 prendas de ropa -> Mensaje de decepción.
            // - Si no queda nadie en la fiesta -> Fin con mensaje.

            ronda++;
        }
        System.out.println("--- FIN DE LA FIESTA ---");
    }
    //Generadores aleatorios de aburrimiento y hambre para cada tipo de invitado
    private static int generarAburrimiento() {
        return MiEntradaSalida.generaAleatorioEntre(0, 50, true);
    }

    private static int generarHambreFamilia() {
        return MiEntradaSalida.generaAleatorioEntre(30, 60, true);
    }

    private static int generarHambreColegas() {
        return MiEntradaSalida.generaAleatorioEntre(50, 70, true);
    }

    //Devolvemos un evento aleatorio
    private static Evento obtenerEventoAleatorio() {
        int random = MiEntradaSalida.generaAleatorioEntre(0, Evento.values().length, false);
        return Evento.values()[random];
    }
}
