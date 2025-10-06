public class Monitora {
    private static final int CAPACIDAD_MAXIMA_COLA = 1000;

    private String nombreMonitora;
    private Nino[] colaDeNinos;
    private int indiceInicioCola;
    private int cantidadNinosEnCola;

    public Monitora(String nombreMonitora) {
        this.nombreMonitora = nombreMonitora;
        this.colaDeNinos = new Nino[CAPACIDAD_MAXIMA_COLA];
        this.indiceInicioCola = 0;
        this.cantidadNinosEnCola = 0;
    }

    public String getNombre() {
        return nombreMonitora;
    }

    public boolean estaVacia() {
        return cantidadNinosEnCola == 0;
    }

    private int calcularIndiceFisico(int desplazamientoRelativo) {
        return (indiceInicioCola + desplazamientoRelativo) % CAPACIDAD_MAXIMA_COLA;
    }

    public void encolar(Nino nuevoNino) {
        int indiceInsercion = calcularIndiceFisico(cantidadNinosEnCola);
        colaDeNinos[indiceInsercion] = nuevoNino;
        cantidadNinosEnCola = cantidadNinosEnCola + 1;
    }

    public int contar() {
        return cantidadNinosEnCola;
    }

    public double edadPromedio() {
        if (cantidadNinosEnCola == 0) return 0.0;
        int sumaTotalEdades = 0;
        int posicionRelativa = 0;
        while (posicionRelativa < cantidadNinosEnCola) {
            int indiceFisicoActual = calcularIndiceFisico(posicionRelativa);
            sumaTotalEdades = sumaTotalEdades + colaDeNinos[indiceFisicoActual].getEdad();
            posicionRelativa = posicionRelativa + 1;
        }
        return (double) sumaTotalEdades / (double) cantidadNinosEnCola;
    }

    public int contarMayoresIgualQue(int edadMinimaParaContar) {
        int cantidadMayoresOIguales = 0;
        int posicionRelativa = 0;
        while (posicionRelativa < cantidadNinosEnCola) {
            int indiceFisicoActual = calcularIndiceFisico(posicionRelativa);
            if (colaDeNinos[indiceFisicoActual].getEdad() >= edadMinimaParaContar) {
                cantidadMayoresOIguales = cantidadMayoresOIguales + 1;
            }
            posicionRelativa = posicionRelativa + 1;
        }
        return cantidadMayoresOIguales;
    }

    public int transferirTodoA(Monitora monitoraDestino) {
        int cantidadTransferida = cantidadNinosEnCola;
        int posicionRelativa = 0;
        while (posicionRelativa < cantidadNinosEnCola) {
            int indiceFisicoActual = calcularIndiceFisico(posicionRelativa);
            monitoraDestino.encolar(colaDeNinos[indiceFisicoActual]);
            posicionRelativa = posicionRelativa + 1;
        }
        indiceInicioCola = 0;
        cantidadNinosEnCola = 0;
        return cantidadTransferida;
    }

    public void presentacionesGenerales() {
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
        int posicionRelativa = 0;
        while (posicionRelativa < cantidadNinosEnCola) {
            int indiceFisicoActual = calcularIndiceFisico(posicionRelativa);
            colaDeNinos[indiceFisicoActual].presentarse();
            posicionRelativa = posicionRelativa + 1;
        }
    }

    public void presentacionesMayoresQue(int edadMinimaExclusiva) {
        System.out.println("Aisha pide que se presenten los mayores de " + edadMinimaExclusiva + " años:");
        System.out.println();
        int posicionRelativa = 0;
        while (posicionRelativa < cantidadNinosEnCola) {
            int indiceFisicoActual = calcularIndiceFisico(posicionRelativa);
            if (colaDeNinos[indiceFisicoActual].getEdad() > edadMinimaExclusiva) {
                colaDeNinos[indiceFisicoActual].presentarse();
            }
            posicionRelativa = posicionRelativa + 1;
        }
    }

    public void presentacionesPorInicial(char letraInicialBuscada) {
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letraInicialBuscada + "':");
        System.out.println();
        int posicionRelativa = 0;
        while (posicionRelativa < cantidadNinosEnCola) {
            int indiceFisicoActual = calcularIndiceFisico(posicionRelativa);
            String nombreActual = colaDeNinos[indiceFisicoActual].getNombre();
            boolean comienzaConLetra = nombreActual.length() > 0 && nombreActual.charAt(0) == letraInicialBuscada;
            if (comienzaConLetra) {
                System.out.println("[" + nombreActual + "]: Hola, soy " + nombreActual);
            }
            posicionRelativa = posicionRelativa + 1;
        }
    }

    public void presentarPrimeros(int cantidadSolicitada) {
        System.out.println("Aisha pide que se presenten los primeros " + cantidadSolicitada + " niños:");
        System.out.println();
        int cantidadMaximaAPresentar = cantidadSolicitada;
        if (cantidadMaximaAPresentar > cantidadNinosEnCola) cantidadMaximaAPresentar = cantidadNinosEnCola;
        int posicionRelativa = 0;
        while (posicionRelativa < cantidadMaximaAPresentar) {
            int indiceFisicoActual = calcularIndiceFisico(posicionRelativa);
            String nombreActual = colaDeNinos[indiceFisicoActual].getNombre();
            System.out.println("[" + nombreActual + "]: Hola, soy " + nombreActual);
            posicionRelativa = posicionRelativa + 1;
        }
    }

    public void presentarUltimos(int cantidadSolicitada) {
        System.out.println("Aisha pide que se presenten los últimos " + cantidadSolicitada + " niños:");
        System.out.println();
        int posicionInicialUltimos = 0;
        if (cantidadSolicitada < cantidadNinosEnCola) posicionInicialUltimos = cantidadNinosEnCola - cantidadSolicitada;
        int posicionRelativa = posicionInicialUltimos;
        while (posicionRelativa < cantidadNinosEnCola) {
            int indiceFisicoActual = calcularIndiceFisico(posicionRelativa);
            String nombreActual = colaDeNinos[indiceFisicoActual].getNombre();
            System.out.println("[" + nombreActual + "]: Hola, soy " + nombreActual);
            posicionRelativa = posicionRelativa + 1;
        }
    }

    public int separarMenoresHacia(Monitora monitoraDestino, int edadCorteExclusiva) {
        int cantidadTransferidos = 0;

        Nino[] nuevaColaFiltrada = new Nino[CAPACIDAD_MAXIMA_COLA];
        int nuevoIndiceInicioCola = 0;
        int nuevaCantidadFiltrada = 0;

        int posicionRelativa = 0;
        while (posicionRelativa < cantidadNinosEnCola) {
            int indiceFisicoActual = calcularIndiceFisico(posicionRelativa);
            Nino ninoActual = colaDeNinos[indiceFisicoActual];
            boolean esMenorQueCorte = ninoActual.getEdad() < edadCorteExclusiva;

            if (esMenorQueCorte) {
                monitoraDestino.encolar(ninoActual);
                cantidadTransferidos = cantidadTransferidos + 1;
            } else {
                int indiceDestinoFiltrado = (nuevoIndiceInicioCola + nuevaCantidadFiltrada) % CAPACIDAD_MAXIMA_COLA;
                nuevaColaFiltrada[indiceDestinoFiltrado] = ninoActual;
                nuevaCantidadFiltrada = nuevaCantidadFiltrada + 1;
            }
            posicionRelativa = posicionRelativa + 1;
        }

        colaDeNinos = nuevaColaFiltrada;
        indiceInicioCola = nuevoIndiceInicioCola;
        cantidadNinosEnCola = nuevaCantidadFiltrada;

        return cantidadTransferidos;
    }

    public void imprimirListadoDetallado() {
        if (cantidadNinosEnCola == 0) {
            System.out.println("Cola vacía");
            return;
        }
        System.out.println("Niños en cola: " + cantidadNinosEnCola);
        int posicionRelativa = 0;
        while (posicionRelativa < cantidadNinosEnCola) {
            int indiceFisicoActual = calcularIndiceFisico(posicionRelativa);
            System.out.println("- " + colaDeNinos[indiceFisicoActual].getNombre() + " (" + colaDeNinos[indiceFisicoActual].getEdad() + " años)");
            posicionRelativa = posicionRelativa + 1;
        }
    }
}