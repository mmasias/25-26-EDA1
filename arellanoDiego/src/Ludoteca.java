public class Ludoteca {
    private static final int UMBRAL_MINIMO_PARA_JUGAR = 6;
    private static final int TIEMPO_POR_PASO = 1;
    private static final int TIEMPO_INICIO = 1;
    private static final int TIEMPO_FINAL = 1;
    private static final String MENSAJE_SEMILLA = "ABCDEFGHIJ";

    private final Monitor lydia = new Monitor("Lydia");
    private final Monitor aisha = new Monitor("Aisha");
    private final Pizarra pizarraSalon = new Pizarra();

    private boolean jugando = false;
    private int minutoDeFase = 0;
    private int indiceActual = -1;
    private int minutosConsumidos = 0;

    private static final int MAX_NOMBRES = 36;
    private final String[] nombres = {
            "Andrés", "Pablo", "Diego", "Aníbal", "Umut", "Javier", "Fernando",
            "Cayetano", "Iker", "Mario", "Adrián", "Paula", "Veronika", "Eduardo",
            "Hugo", "César", "Miguel", "Santiago", "Juan", "Daniel", "Álvaro",
            "Maura", "Neco", "Sergio", "Aurelio", "Jorge", "Raúl", "José Manuel",
            "José Luis", "Óscar", "Rubén", "Gabriel", "Iñaki", "Alejandro", "Andriuw",
            "Sara", "Lourdes"
    };
    private int siguienteNombre = 0;

    private int juegos = 0;
    private int minutosTotales = 0;
    private int totalNiñosProcesados = 0;
    private int sumaDistorsion = 0;

    public void llegarNiño() {
        String nombre = nombres[siguienteNombre % MAX_NOMBRES];
        siguienteNombre++;
        Niño n = new Niño(nombre);
        lydia.recibeNiño(n);
    }

    public void actualizar() {
        if (!jugando && lydia.tieneNiños()) lydia.entregarNiños(aisha);
        if (!jugando && aisha.cuantos() >= UMBRAL_MINIMO_PARA_JUGAR) iniciarJuego();
        tickJuego();
    }

    private void iniciarJuego() {
        jugando = true;
        minutoDeFase = 0;
        indiceActual = -1;
        minutosConsumidos = 0;
    }

    private void tickJuego() {
        if (!jugando) return;
        minutosConsumidos++;

        if (indiceActual == -1) {
            minutoDeFase++;
            if (minutoDeFase == 1) {
                pizarraSalon.limpiar();
                aisha.getCola().forEach(Niño::limpiarPizarrin);
            }
            if (minutoDeFase >= TIEMPO_INICIO) {
                indiceActual = 0;
                Niño primero = aisha.getCola().get(indiceActual);
                if (primero != null) primero.recibirMensaje(MENSAJE_SEMILLA);
                minutoDeFase = 0;
            }
            return;
        }

        minutoDeFase++;
        if (minutoDeFase >= TIEMPO_POR_PASO) {
            int n = aisha.cuantos();
            if (indiceActual + 1 < n) {
                Niño actual = aisha.getCola().get(indiceActual);
                Niño siguiente = aisha.getCola().get(indiceActual + 1);
                if (actual != null && siguiente != null)
                    siguiente.recibirMensaje(actual.mostrarMensaje());
                indiceActual++;
                minutoDeFase = 0;
            } else {
                minutoDeFase = 0;
                indiceActual = Integer.MAX_VALUE;
            }
        }

        if (indiceActual == Integer.MAX_VALUE) {
            minutoDeFase++;
            if (minutoDeFase >= TIEMPO_FINAL) {
                Niño ultimo = aisha.getCola().get(aisha.cuantos() - 1);
                String finalMsg = (ultimo == null) ? "" : ultimo.mostrarMensaje();
                pizarraSalon.escribirMensaje(finalMsg);
                terminarJuego(finalMsg);
            }
        }
    }

    private void terminarJuego(String finalMsg) {
        jugando = false;
        minutoDeFase = 0;
        indiceActual = -1;
        juegos++;
        minutosTotales += minutosConsumidos;
        totalNiñosProcesados += aisha.cuantos();
        sumaDistorsion += distanciaHamming(MENSAJE_SEMILLA, finalMsg);
    }

    private int distanciaHamming(String a, String b) {
        int n = Math.min(a.length(), b.length());
        int d = 0;
        for (int i = 0; i < n; i++) if (a.charAt(i) != b.charAt(i)) d++;
        d += Math.abs(a.length() - b.length());
        return d;
    }

    public String estado() {
        return String.format("Aisha: [%s] | jugando=%s | Pizarra salón: [%s] | METRICAS: juegos=%d, avg_min_juego=%d, avg_niños=%d, avg_distorsion=%d%nLydia: [%s]",
                aisha.getCola().listarNombres(), jugando, pizarraSalon.leerMensaje(),
                juegos, juegos == 0 ? 0 : minutosTotales / juegos,
                juegos == 0 ? 0 : totalNiñosProcesados / juegos,
                juegos == 0 ? 0 : sumaDistorsion / juegos,
                lydia.getCola().listarNombres());
    }
}
