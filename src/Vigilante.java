public class Vigilante implements Runnable {

    private Thread temperatura;
    private Thread humedad;
    private Thread luz;
    private ProgramadorRiego programadorRiego;

    public Vigilante(Thread humedad, Thread temperatura, Thread luz, ProgramadorRiego programadorRiego) {
        this.humedad = humedad;
        this.temperatura = temperatura;
        this.luz = luz;
        this.programadorRiego = programadorRiego;
    }

    @Override
    public void run() {
        while (true) {
        System.out.println(temperatura.getState());
        System.out.println(humedad.getState());
        System.out.println(luz.getState());
        System.out.println(programadorRiego.getState());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {}
        }

    }
}
