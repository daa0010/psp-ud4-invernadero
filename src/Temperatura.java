import java.util.concurrent.ThreadLocalRandom;

public class Temperatura extends Sensor{

    public Temperatura(String nombre, String unidad, long periodo, int numeroLecturas, double umbralMinimo, double umbralMaximo) {
        super(nombre, unidad, periodo, numeroLecturas, umbralMinimo, umbralMaximo);
    }

    @Override
    public double medir() {
        return ThreadLocalRandom.current().nextDouble(15.0 , 35.0);
    }
}
