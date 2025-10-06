public class Monitora {

    private String nombre;
    private Ludoteca ludoteca;
    private Monitora organizadora;

    private Object[] cola;
    private int colaCount;

    private Nino[] menoresBackup;
    private int[] menoresBackupPos;
    private int menoresBackupCount;

    private Nino[] cuidado;
    private int cuidadoCount;

    private Nino[] espera;
    private int esperaCount;

    public Monitora(String nombre) {
        this.nombre = nombre;
        this.cola = new Object[200];
        this.colaCount = 0;
        this.menoresBackup = null;
        this.menoresBackupPos = null;
        this.menoresBackupCount = 0;
        this.cuidado = new Nino[200];
        this.cuidadoCount = 0;
        this.espera = new Nino[50];
        this.esperaCount = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void abrirLudoteca(Ludoteca l) {
        this.ludoteca = l;
    }

    public Ludoteca getLudoteca() {
        return ludoteca;
    }

    public void setOrganizadora(Monitora o) {
        this.organizadora = o;
    }

    public Monitora getOrganizadora() {
        return organizadora;
    }

    public void addPersonaACola(Object persona) {
        if (persona instanceof Nino) {
            Nino n = (Nino) persona;
            Pizarra pizarrin = new Pizarra("Pizarrin de " + n.getNombre());
            n.recibirPizarrin(pizarrin);
        }
        if (colaCount >= cola.length) {
            Object[] nuevo = new Object[cola.length * 2];
            for (int i = 0; i < cola.length; i++) nuevo[i] = cola[i];
            cola = nuevo;
        }
        cola[colaCount++] = persona;
        System.out.println(nombre + " encola a " + (persona instanceof Nino ? ((Nino) persona).getNombre() : persona.toString()) + ". Total en cola: " + colaCount);
    }

    public void addNinoToCola(Nino n) {
        addPersonaACola(n);
    }

    public Nino[] getFilaArray() {
        int count = 0;
        for (int i = 0; i < colaCount; i++) if (cola[i] instanceof Nino) count++;
        Nino[] salida = new Nino[count];
        int idx = 0;
        for (int i = 0; i < colaCount; i++) {
            if (cola[i] instanceof Nino) salida[idx++] = (Nino) cola[i];
        }
        return salida;
    }

    public int numeroDeNinosEnCola() {
        int c = 0;
        for (int i = 0; i < colaCount; i++) if (cola[i] instanceof Nino) c++;
        return c;
    }

    public void vaciarCola() {
        for (int i = 0; i < colaCount; i++) cola[i] = null;
        colaCount = 0;
        System.out.println(nombre + " ha vaciado su cola.");
    }

    public void ponerEnCuidado(Nino n) {
        if (cuidadoCount >= cuidado.length) {
            Nino[] nuevo = new Nino[cuidado.length * 2];
            for (int i = 0; i < cuidado.length; i++) nuevo[i] = cuidado[i];
            cuidado = nuevo;
        }
        cuidado[cuidadoCount++] = n;
        System.out.println(nombre + " pone en cuidado a " + n.getNombre() + ". Total en cuidado: " + cuidadoCount);
    }

    public void limpiarCuidado() {
        for (int i = 0; i < cuidadoCount; i++) cuidado[i] = null;
        cuidadoCount = 0;
        System.out.println(nombre + " ha limpiado su cuidado.");
    }

    public Nino[] recuperarCuidadoYVaciar() {
        Nino[] out = new Nino[cuidadoCount];
        for (int i = 0; i < cuidadoCount; i++) out[i] = cuidado[i];
        limpiarCuidado();
        return out;
    }

    public Nino[] extraerMenoresConPosiciones(int edadMax) {
        if (colaCount == 0) return new Nino[0];

        int originalLength = colaCount;
        Nino[] tmpMenores = new Nino[originalLength];
        int[] tmpPos = new int[originalLength];
        int men = 0;

        Object[] nueva = new Object[cola.length];
        int nc = 0;

        for (int i = 0; i < originalLength; i++) {
            Object o = cola[i];
            if (o instanceof Nino) {
                Nino n = (Nino) o;
                if (n.getEdad() < edadMax) {
                    tmpMenores[men] = n;
                    tmpPos[men] = i;
                    men++;
                    continue;
                }
            }
            nueva[nc++] = o;
        }

        cola = nueva;
        colaCount = nc;

        menoresBackup = new Nino[men];
        menoresBackupPos = new int[men];
        for (int k = 0; k < men; k++) {
            menoresBackup[k] = tmpMenores[k];
            menoresBackupPos[k] = tmpPos[k];
        }
        menoresBackupCount = men;

        Nino[] salida = new Nino[men];
        for (int k = 0; k < men; k++) salida[k] = tmpMenores[k];
        return salida;
    }

    public void restaurarMenoresBackup() {
        if (menoresBackupCount == 0) return;

        int originalLength = colaCount + menoresBackupCount;
        Object[] resta = new Object[originalLength];
        int pMen = 0;
        int pCol = 0;

        for (int i = 0; i < originalLength; i++) {
            if (pMen < menoresBackupCount && menoresBackupPos[pMen] == i) {
                resta[i] = menoresBackup[pMen++];
            } else {
                if (pCol < colaCount) {
                    resta[i] = cola[pCol++];
                } else {
                    resta[i] = null;
                }
            }
        }

        cola = resta;
        colaCount = originalLength;

        menoresBackup = null;
        menoresBackupPos = null;
        menoresBackupCount = 0;
    }

    public void recibirNino(Nino n) {
        if (organizadora != null && this != organizadora) {
            if (organizadora.getLudoteca() != null && organizadora.getLudoteca().isJuegoEnCurso()) {
                if (esperaCount >= espera.length) {
                    Nino[] nuevo = new Nino[espera.length * 2];
                    for (int i = 0; i < espera.length; i++) nuevo[i] = espera[i];
                    espera = nuevo;
                }
                espera[esperaCount++] = n;
                System.out.println(nombre + " recibe a " + n.getNombre() + " pero hay un juego en curso: lo deja en espera.");
            } else {
                System.out.println(nombre + " recibe a " + n.getNombre() + " y se lo pasa a " + organizadora.getNombre() + ".");
                organizadora.recibirNino(n);
            }
        } else {
            if (ludoteca != null) {
                System.out.println(nombre + " coloca a " + n.getNombre() + " en la cola.");
                addNinoToCola(n);
            } else {
                System.out.println(nombre + " no puede procesar a " + n.getNombre() + " (no hay ludoteca asociada).");
            }
        }
    }

    public void recibirNinoDirecto(Nino n) {
        addNinoToCola(n);
    }

    public void pasarNinosAOrganizadora() {
        if (esperaCount == 0) return;
        System.out.println(nombre + " pasa " + esperaCount + " niño(s) de la espera a la organizadora (" + (organizadora != null ? organizadora.getNombre() : "N/A") + ").");
        for (int i = 0; i < esperaCount; i++) {
            Nino n = espera[i];
            if (n != null && organizadora != null) {
                organizadora.recibirNino(n);
            } else if (n != null && ludoteca != null) {
                Monitora rec = ludoteca.getReceptora();
                if (rec != null) rec.addNinoToCola(n);
            }
            espera[i] = null;
        }
        esperaCount = 0;
    }

    public void pasarMenoresA(Monitora destino, int edadMax) {
        if (colaCount == 0) {
            System.out.println(nombre + " no tiene cola para pasar menores.");
            return;
        }
        Object[] nueva = new Object[cola.length];
        int nc = 0;
        int movidos = 0;
        for (int i = 0; i < colaCount; i++) {
            Object o = cola[i];
            if (o instanceof Nino) {
                Nino n = (Nino) o;
                if (n.getEdad() < edadMax) {
                    if (destino != null) destino.ponerEnCuidado(n);
                    movidos++;
                    continue;
                }
            }
            nueva[nc++] = o;
        }
        cola = nueva;
        colaCount = nc;
        System.out.println(nombre + " ha pasado " + movidos + " niño(s) menores de " + edadMax + " a " + (destino != null ? destino.getNombre() : "N/A") + ".");
    }

    public void mostrarEstadoCola() {
        System.out.println("\nMonitora: " + nombre);
        System.out.println(" Cola total elementos: " + colaCount);
        for (int i = 0; i < colaCount; i++) {
            Object o = cola[i];
            if (o instanceof Nino) {
                Nino n = (Nino) o;
                System.out.println("  - [" + i + "] " + n.getNombre() + " (" + n.getEdad() + " años)");
            } else if (o != null) {
                System.out.println("  - [" + i + "] Otro: " + o.toString());
            }
        }
        System.out.println(" Cuidado: " + cuidadoCount + " niños.");
        for (int i = 0; i < cuidadoCount; i++) {
            if (cuidado[i] != null) System.out.println("  * cuidado [" + i + "] " + cuidado[i].getNombre() + " (" + cuidado[i].getEdad() + " años)");
        }
        System.out.println(" Niños en espera: " + esperaCount + "\n");
    }

    public void pasarNinosAOtra(Monitora destino) {
        if (this.colaCount == 0) return;
        for (int i = 0; i < this.colaCount; i++) {
            destino.recibirNinoDirecto((Nino) this.cola[i]);
        }
        System.out.println(this.nombre + " transfiere todos los niños a " + destino.getNombre() + ".");
        this.colaCount = 0;
    }

    public void presentarse() {
        System.out.println(nombre + " dice: Hola, soy " + nombre + ".");
    }

    public void pedirPresentacionesTodos() {
        System.out.println(nombre + " pide a todos que se presenten:");
        if (colaCount == 0) {
            System.out.println("(No hay niños en la cola).");
            return;
        }
        for (int i = 0; i < colaCount; i++) {
            Nino n = (Nino) cola[i];
            if (n != null) n.presentarse();
        }
    }

    public void pedirPresentacionesMayoresDe(int edadMin) {
        System.out.println(nombre + " pide que se presenten los niños mayores de " + edadMin + " años:");
        boolean alguno = false;
        for (int i = 0; i < colaCount; i++) {
            Nino n = (Nino) cola[i];
            if (n != null && n.getEdad() > edadMin) {
                n.presentarse();
                alguno = true;
            }
        }
        if (!alguno) System.out.println("(No hay niños mayores de " + edadMin + " años).");
    }

    public void pedirPresentacionesPorLetra(char letra) {
        System.out.println(nombre + " pide que se presenten los niños cuyo nombre empieza por '" + letra + "':");
        boolean alguno = false;
        for (int i = 0; i < colaCount; i++) {
            Nino n = (Nino) cola[i];
            if (n != null && n.nombreEmpiezaPor(letra)) {
                n.presentarse();
                alguno = true;
            }
        }
        if (!alguno) System.out.println("(No hay niños cuyo nombre empiece por '" + letra + "').");
    }

    public void pedirPresentacionesPrimeros(int k) {
        System.out.println(nombre + " pide que se presenten los primeros " + k + " niños:");
        if (colaCount == 0) {
            System.out.println("(No hay niños en la cola).");
            return;
        }
        int lim = Math.min(k, colaCount);
        for (int i = 0; i < lim; i++) {
            Nino n = (Nino) cola[i];
            if (n != null) n.presentarse();
        }
    }

    public void pedirPresentacionesUltimos(int k) {
        System.out.println(nombre + " pide que se presenten los últimos " + k + " niños:");
        if (colaCount == 0) {
            System.out.println("(No hay niños en la cola).");
            return;
        }
        int start = Math.max(0, colaCount - k);
        for (int i = start; i < colaCount; i++) {
            Nino n = (Nino) cola[i];
            if (n != null) n.presentarse();
        }
    }

    public void decirNumeroEnCola() {
        System.out.println(nombre + " dice: Hay " + colaCount + " niños en mi cola.");
    }

    public void decirEdadPromedio() {
        if (colaCount == 0) {
            System.out.println(nombre + " dice: No hay niños en la cola para calcular la edad promedio.");
            return;
        }
        int suma = 0;
        for (int i = 0; i < colaCount; i++) {
            Nino n = (Nino) cola[i];
            if (n != null) suma += n.getEdad();
        }
        double media = (double) suma / colaCount;
        System.out.println(nombre + " dice: La edad promedio de los niños en la cola es " + String.format("%.2f", media) + " años.");
    }

}
