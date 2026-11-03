public class Fase3_Experimentos {
    public static void main(String[] args) throws InterruptedException {
        Temperatura temperatura = new Temperatura("Temperatura", "ºC", 400, 5, 18.0, 30.0);
        Thread temperatura1 = new Thread(temperatura, "sensor-Temperatura");
        temperatura1.start();
        System.out.println(temperatura.informe());
        temperatura1.join();
        System.out.println(temperatura.informe());

        ProgramadorRiego pr = new ProgramadorRiego("riego", 3);
        long tiempoInicial = System.currentTimeMillis();
        pr.run();
        long tiempoFinal = System.currentTimeMillis();
        System.out.println("Tiempo: " + (tiempoFinal - tiempoInicial) + " ms");
        System.out.println(pr.getState());

        pr.start();
        pr.join();
        try {
            pr.start();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
}
