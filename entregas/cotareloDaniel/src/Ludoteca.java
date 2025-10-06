public class Ludoteca {
    private static final int CAPACIDAD = 300;
    private static final int MINIMO_JUEGO = 5;
    private Lydia lydia;
    private Aisha aisha;
    private Dalsy dalsy;
    private Nino[] respaldoOrdenAisha;

    public Ludoteca() {
        this.lydia = new Lydia(CAPACIDAD);
        this.aisha = new Aisha(CAPACIDAD);
        this.dalsy = new Dalsy(CAPACIDAD);
        this.respaldoOrdenAisha = new Nino[0];
    }

    public void llegadaNino(String nombre, int edad) {
        Nino ninoNuevo;
        ninoNuevo = new Nino(nombre, edad);
        System.out.println("Llega " + nombre + " (" + edad + " años)");
        System.out.println(nombre + " pasa a la cola de Lydia");
        this.lydia.getCola().encolar(ninoNuevo);
    }

    public void intentoInicioJuego() {
        int cantidadLydia;
        cantidadLydia = this.lydia.getCola().tamano();
        if (cantidadLydia >= MINIMO_JUEGO) {
            System.out.println("Lydia transfiere sus niños a Aisha");
            Nino[] transferidos;
            transferidos = this.lydia.getCola().copiarContenido();
            this.lydia.getCola().limpiar();
            this.aisha.getCola().encolarDesdeArray(transferidos);
            int indice;
            indice = 0;
            while (indice < transferidos.length) {
                System.out.println(
                        "- " + transferidos[indice].getNombre() + " (" + transferidos[indice].getEdad() + " años)");
                indice = indice + 1;
            }
        } else {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos " + MINIMO_JUEGO + " niños");
        }
    }

    public void presentacionesGenerales() {
        System.out.println("Aisha: " + this.aisha.presentacion());
        if (this.aisha.getCola().estaVacia()) {
            System.out.println();
        } else {
            int indice;
            Nino actual;
            indice = 0;
            while (indice < this.aisha.getCola().tamano()) {
                actual = this.aisha.getCola().obtener(indice);
                System.out.println("[" + (indice + 1) + "]: " + actual.presentacion());
                indice = indice + 1;
            }
        }
    }

    public void presentacionesPorEdadMinima(int edadMinimaExclusiva) {
        if (this.aisha.getCola().estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
        } else {
            System.out.println("Aisha pide que se presenten los mayores de " + (edadMinimaExclusiva - 1) + " años:");
            int indice;
            Nino actual;
            indice = 0;
            while (indice < this.aisha.getCola().tamano()) {
                actual = this.aisha.getCola().obtener(indice);
                if (actual.getEdad() >= edadMinimaExclusiva) {
                    System.out.println(actual.presentacion());
                }
                indice = indice + 1;
            }
        }
    }

    public void presentacionesPorInicial(String letra) {
        if (this.aisha.getCola().estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
        } else {
            String letraLimpiada;
            String letraComparacion;
            String nombreActual;
            letraLimpiada = "";
            letraComparacion = "";
            nombreActual = "";
            if (letra != null) {
                letraLimpiada = letra.trim();
            }
            System.out
                    .println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letraLimpiada + "':");
            int indice;
            Nino actual;
            indice = 0;
            while (indice < this.aisha.getCola().tamano()) {
                actual = this.aisha.getCola().obtener(indice);
                nombreActual = actual.getNombre();
                if (nombreActual.length() > 0 && letraLimpiada.length() > 0) {
                    letraComparacion = String.valueOf(nombreActual.charAt(0));
                    if (letrasIguales(letraComparacion, letraLimpiada)) {
                        System.out.println(actual.presentacionSimple());
                    }
                }
                indice = indice + 1;
            }
        }
    }

    private boolean letrasIguales(String a, String b) {
        boolean iguales;
        String aa;
        String bb;
        aa = a.toUpperCase();
        bb = b.toUpperCase();
        iguales = aa.equals(bb);
        return iguales;
    }

    public void presentarPrimerosCinco() {
        if (this.aisha.getCola().estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
        } else {
            System.out.println("Aisha pide que se presenten los primeros 5 niños:");
            int limite;
            int indice;
            Nino actual;
            limite = this.aisha.getCola().tamano();
            if (limite > 5) {
                limite = 5;
            }
            indice = 0;
            while (indice < limite) {
                actual = this.aisha.getCola().obtener(indice);
                System.out.println("[" + (indice + 1) + "]: " + actual.presentacionSimple());
                indice = indice + 1;
            }
        }
    }

    public void presentarUltimosCinco() {
        if (this.aisha.getCola().estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
        } else {
            System.out.println("Aisha pide que se presenten los últimos 5 niños:");
            int total;
            int inicio;
            int indice;
            Nino actual;
            total = this.aisha.getCola().tamano();
            inicio = 0;
            if (total > 5) {
                inicio = total - 5;
            }
            indice = inicio;
            while (indice < total) {
                actual = this.aisha.getCola().obtener(indice);
                System.out.println("[" + (indice + 1) + "]: " + actual.presentacionSimple());
                indice = indice + 1;
            }
        }
    }

    public void conteoAsistencia() {
        int x;
        int y;
        int z;
        int total;
        x = this.lydia.getCola().tamano();
        y = this.aisha.getCola().tamano();
        z = this.dalsy.getCola().tamano();
        total = x + y + z;
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + x + " niños en cola");
        System.out.println("Aisha tiene " + y + " niños en cola");
        System.out.println("Dalsy tiene " + z + " niños en cola");
        System.out.println("Total: " + total + " niños");
    }

    public void edadPromedioAisha() {
        if (this.aisha.getCola().estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
        } else {
            int sumaEdades;
            int cantidad;
            int indice;
            double promedio;
            double promedioRedondeado;
            sumaEdades = 0;
            cantidad = this.aisha.getCola().tamano();
            indice = 0;
            while (indice < cantidad) {
                sumaEdades = sumaEdades + this.aisha.getCola().obtener(indice).getEdad();
                indice = indice + 1;
            }
            promedio = (double) sumaEdades / (double) cantidad;
            promedioRedondeado = Math.round(promedio * 10.0) / 10.0;
            System.out.println("Edad promedio de los niños en la cola de Aisha: " + promedioRedondeado + " años");
        }
    }

    public void intentoJuegoRana() {
        System.out.println("Verificando condiciones para el juego de la rana...");
        int total;
        int mayores;
        total = this.aisha.getCola().tamano();
        mayores = contarMayoresOIgualCinco(this.aisha);
        System.out.println("Total de niños: " + total);
        System.out.println("Niños de 5 años o más: " + mayores);
        if (mayores > total / 2) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
        } else {
            System.out.println("No hay suficientes niños mayores de 5 años");
            System.out.println("No pueden jugar todavía");
        }
    }

    private int contarMayoresOIgualCinco(Monitora monitora) {
        int cantidad;
        int indice;
        cantidad = 0;
        indice = 0;
        while (indice < monitora.getCola().tamano()) {
            if (monitora.getCola().obtener(indice).getEdad() >= 5) {
                cantidad = cantidad + 1;
            }
            indice = indice + 1;
        }
        return cantidad;
    }

    public void separarParaJuegoRana() {
        if (this.aisha.getCola().estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
        } else {
            System.out.println("Separando niños para el juego de la rana...");
            this.respaldoOrdenAisha = this.aisha.getCola().copiarContenido();
            Nino[] menores;
            Nino[] mayores;
            int total;
            int indice;
            int cuentaMenores;
            int cuentaMayores;
            total = this.aisha.getCola().tamano();
            menores = new Nino[total];
            mayores = new Nino[total];
            indice = 0;
            cuentaMenores = 0;
            cuentaMayores = 0;
            while (indice < total) {
                Nino actual;
                actual = this.aisha.getCola().obtener(indice);
                if (actual.getEdad() < 5) {
                    menores[cuentaMenores] = actual;
                    cuentaMenores = cuentaMenores + 1;
                } else {
                    mayores[cuentaMayores] = actual;
                    cuentaMayores = cuentaMayores + 1;
                }
                indice = indice + 1;
            }
            this.aisha.getCola().limpiar();
            this.dalsy.getCola().encolarDesdeArray(recortar(menores, cuentaMenores));
            this.aisha.getCola().encolarDesdeArray(recortar(mayores, cuentaMayores));
            System.out.println("Niños menores de 5 años pasan a Dalsy:");
            imprimirListaConGuiones(recortar(menores, cuentaMenores));
            System.out.println();
            System.out.println("Niños que se quedan con Aisha para jugar:");
            imprimirListaConGuiones(recortar(mayores, cuentaMayores));
            System.out.println();
            System.out.println("NOTA: Al terminar el juego, los niños volverán a su posición original");
        }
    }

    private Nino[] recortar(Nino[] origen, int longitud) {
        Nino[] destino;
        int indice;
        destino = new Nino[longitud];
        indice = 0;
        while (indice < longitud) {
            destino[indice] = origen[indice];
            indice = indice + 1;
        }
        return destino;
    }

    private void imprimirListaConGuiones(Nino[] lista) {
        int indice;
        indice = 0;
        while (indice < lista.length) {
            System.out.println("- " + lista[indice].getNombre() + " (" + lista[indice].getEdad() + " años)");
            indice = indice + 1;
        }
    }

    public void alarmaIncendios() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!");
        System.out.println();
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO");
        System.out.println();
        int transferidosDalsy;
        int transferidosAisha;
        int totalTransferidos;
        transferidosDalsy = moverTodos(this.dalsy, this.lydia);
        transferidosAisha = moverTodos(this.aisha, this.lydia);
        totalTransferidos = transferidosDalsy + transferidosAisha;
        System.out.println("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        System.out.println("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        System.out.println(totalTransferidos + " niños transferidos");
        System.out.println();
        System.out
                .println("Lydia ahora tiene " + this.lydia.getCola().tamano() + " niños listos para evacuar en orden");
    }

    private int moverTodos(Monitora origen, Monitora destino) {
        int cantidadMovidos;
        Nino[] lista;
        cantidadMovidos = origen.getCola().tamano();
        lista = origen.getCola().copiarContenido();
        origen.getCola().limpiar();
        destino.getCola().encolarDesdeArray(lista);
        return cantidadMovidos;
    }

    public void mostrarEstadoActual() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        System.out.println();
        System.out.println("LYDIA:");
        mostrarCola(this.lydia);
        System.out.println();
        System.out.println("AISHA:");
        mostrarCola(this.aisha);
        System.out.println();
        System.out.println("DALSY:");
        mostrarCola(this.dalsy);
        System.out.println();
        System.out.println("========================================");
    }

    private void mostrarCola(Monitora monitora) {
        if (monitora.getCola().estaVacia()) {
            System.out.println("  Cola vacía");
        } else {
            int total;
            int indice;
            total = monitora.getCola().tamano();
            System.out.println("  Niños en cola: " + total);
            indice = 0;
            while (indice < total) {
                Nino actual;
                actual = monitora.getCola().obtener(indice);
                System.out.println("  - " + actual.getNombre() + " (" + actual.getEdad() + " años)");
                indice = indice + 1;
            }
        }
    }
}
