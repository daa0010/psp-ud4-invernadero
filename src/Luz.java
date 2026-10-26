import java.util.concurrent.ThreadLocalRandom;

public class Luz extends Sensor {

    public Luz(String nombre, String unidad, long periodo, int numeroLecturas, double umbralMinimo, double umbralMaximo) {
        super(nombre, unidad, periodo, numeroLecturas, umbralMinimo, umbralMaximo);
    }

    @Override
    public double medir() {
        return ThreadLocalRandom.current().nextDouble(0, 1000);
    }
}
