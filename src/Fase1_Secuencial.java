public class Fase1_Secuencial {
    public static void main(String[] args) {
        Temperatura temperatura = new Temperatura("Temperatura", "ºC", 400, 5, 18.0, 30.0);
        Humedad humedad = new Humedad("Humedad", "%", 600, 5, 50.0, 80.0);
        Luz luz = new Luz("Luz", "lux", 500, 5, 200.0, 900.0);
        ProgramadorRiego pr = new ProgramadorRiego("riego");

        long tiempoInicial = System.currentTimeMillis();

        temperatura.run();
        humedad.run();
        luz.run();
        pr.run();

        long tiempoFinal = System.currentTimeMillis();

        System.out.println(temperatura.informe());
        System.out.println(humedad.informe());
        System.out.println(luz.informe());
        System.out.println(pr.informe());


        System.out.println("Tiempo: " + (tiempoFinal - tiempoInicial) + "ms");
    }
}
