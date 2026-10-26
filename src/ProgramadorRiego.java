public class ProgramadorRiego extends Thread {
    private int ciclosRealizados;
    private double litros;
    private boolean estado;

    public ProgramadorRiego(String nombre) {
        super("riego");
    }

    @Override
    public void run() {
        String valvula;
        for (int i = 1; i <= 5; i++) {
            estado = !estado;
            if (estado) {
                litros += 2.5;
                valvula = "ABIERTA";
            } else {
                valvula = "cerrada";
            }
            ciclosRealizados++;
            System.out.println("Ciclo: " + i + " " + "Nombre: " + getName() + " " + "Válvula: " + valvula);
            esperar(700);
        }
    }

    private void esperar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println(getName() + " ha sido interrumpido");
        }
    }

    public String informe(){
        return "Ciclos: "+ciclosRealizados+" | Litros: "+litros;
    }
}
