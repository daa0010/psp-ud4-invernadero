public class Fase2_Concurrente {
    public static void main(String[] args) throws InterruptedException {
        Temperatura temperatura = new Temperatura("Temperatura", "ºC", 400, 5, 18.0, 30.0);
        Humedad humedad = new Humedad("Humedad", "%", 600, 5, 50.0, 80.0);
        Luz luz = new Luz("Luz", "lux", 500, 5, 200.0, 900.0);
        ProgramadorRiego pr = new ProgramadorRiego("riego");

        Thread temperatura1 = new Thread(temperatura, "sensor-Temperatura");
        Thread humedad1 = new Thread(humedad,  "sensor-Humedad");
        Thread luz1 = new Thread(luz, "sensor-Luz");

        long tiempoInicial = System.currentTimeMillis();

        temperatura1.start();
        humedad1.start();
        luz1.start();
        pr.start();

        temperatura1.join();
        humedad1.join();
        luz1.join();
        pr.join();

        long tiempoFinal = System.currentTimeMillis();

        System.out.println("Tiempo: " + (tiempoFinal - tiempoInicial) + "ms");

    }
}
