import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class Sensor implements Runnable {
    // Atributos generales del sensor
    private final String nombre;
    private final String unidad;
    private final long periodo;
    private final int numeroLecturas;
    private final double umbralMinimo;
    private final double umbralMaximo;

    // Declaro e inicializo las estadísticas necesarias
    // Pongo valorMinimo al mayor número posible para que la primera lectura real siempre sea inferior y quede
    // guardada como mínimo.
    // Hago lo contrario con valorMaximo para que la primera lectura siempre lo supere.
    private int lecturasRealizadas = 0;
    private double media = 0.0;
    private double valorMinimo = Double.MAX_VALUE;
    private double valorMaximo = -Double.MAX_VALUE;
    private int numeroAlertas = 0;
    // Defino el formato horario para que la salida sea legible (HH:mm:ss)
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm:ss");

    // Constructor
    public Sensor(String nombre, String unidad, long periodo, int numeroLecturas, double umbralMinimo,
                  double umbralMaximo) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.periodo = periodo;
        this.numeroLecturas = numeroLecturas;
        this.umbralMinimo = umbralMinimo;
        this.umbralMaximo = umbralMaximo;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public long getPeriodo() {
        return periodo;
    }

    public int getNumeroLecturas() {
        return numeroLecturas;
    }

    public double getUmbralMinimo() {
        return umbralMinimo;
    }

    public double getUmbralMaximo() {
        return umbralMaximo;
    }

    /**
     * Dejo esta función abstracta para que cada sensor concreto genere
     * su propio valor.
     */
    public abstract double medir();

    @Override
    public void run() {
        // Variable local para acumular las mediciones y calcular la media al final
        double sumaTotal = 0.0;

        for (int i = 1; i <= numeroLecturas; i++) {
            // Obtengo la medición actual llamando a mi función abstracta
            double valor = medir();
            lecturasRealizadas++;
            sumaTotal += valor;

            // Actualizo mis registros de mínimo y máximo
            if (valor < valorMinimo) {
                valorMinimo = valor;
            }
            if (valor > valorMaximo) {
                valorMaximo = valor;
            }

            // Compruebo si el valor leído está fuera de mis umbrales
            boolean alerta = valor < umbralMinimo || valor > umbralMaximo;
            if (alerta) {
                numeroAlertas++;
            }

            // Como no heredo de Thread, pregunto qué hilo me está ejecutando
            String nombreHilo = Thread.currentThread().getName();

            // Obtengo la hora actual aplicando mi formateador
            String horaActual = LocalTime.now().format(FORMATO_HORA);
            String aviso = alerta ? " ⚠️ ALERTA" : "";

            //Imprimo el log
            System.out.println("[" + horaActual + "] [" + nombreHilo + "] " + nombre + ": " + String.format("%.2f",
                    valor) + " " + unidad + aviso);

            // Pauso la ejecución el tiempo que marca mi periodo
            try {
                Thread.sleep(periodo);
            } catch (InterruptedException e) {
                // Si el hilo que me ejecuta es interrumpido, salgo de forma limpia
                return;
            }
        }

        // Calculo mi media final con todas las lecturas registradas
        if (lecturasRealizadas > 0) {
            this.media = sumaTotal / lecturasRealizadas;
        }
    }


     // Construyo el informe final en una sola línea reuniendo mis estadísticas calculadas.

    public String informe() {
        return "[" + nombre + "] Lecturas: " + lecturasRealizadas +
                " | Media: " + String.format("%.2f", media) + " " + unidad +
                " | Mín: " + String.format("%.2f", valorMinimo) + " " + unidad +
                " | Máx: " + String.format("%.2f", valorMaximo) + " " + unidad +
                " | Alertas: " + numeroAlertas;
    }
}


