public class Vigilante implements Runnable {

    private Thread temperatura;
    private Thread humedad;
    private Thread luz;
    private ProgramadorRiego programadorRiego;

    public Vigilante(Thread temperatura, Thread humedad, Thread luz, ProgramadorRiego programadorRiego) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.luz = luz;
        this.programadorRiego = programadorRiego;
    }

    @Override
    public void run() {
        while (true) {
        System.out.println(temperatura.getName() + " " + temperatura.getState());
        System.out.println(humedad.getName() + " " + humedad.getState());
        System.out.println(luz.getName() + " " + luz.getState());
        System.out.println(programadorRiego.getName()+ " " + programadorRiego.getState());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            return;
        }
        }

    }
}
