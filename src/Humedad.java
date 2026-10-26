import java.util.concurrent.ThreadLocalRandom;

public class Humedad extends Sensor{

    public Humedad(String nombre, String unidad, long periodo, int numeroLecturas, double umbralMinimo, double umbralMaximo) {
        super(nombre, unidad, periodo, numeroLecturas, umbralMinimo, umbralMaximo);
    }

    @Override
    public double medir() {
        return ThreadLocalRandom.current().nextDouble(40.0, 90.0);
    }
}
