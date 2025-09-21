import java.time.LocalDateTime;
import java.util.Objects;

public class Mensaje {

    private final String remitente;
    private final String destinatario;
    private final String contenido;
    private final LocalDateTime fechaHora;

    public Mensaje(String remitente, String destinatario, String contenido, LocalDateTime fechaHora) {
        this.remitente = Objects.requireNonNull(remitente, "Remitente no puede ser nulo");
        this.destinatario = Objects.requireNonNull(destinatario, "Destinatario no puede ser nulo");
        this.contenido = Objects.requireNonNull(contenido, "Contenido no puede ser nulo");
        this.fechaHora = Objects.requireNonNull(fechaHora, "Fecha y hora no puede ser nulo");
    }

    public String getRemitente() {
        return remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "remitente='" + remitente + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", contenido='" + contenido + '\'' +
                ", fechaHora=" + fechaHora +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mensaje)) return false;
        Mensaje mensaje = (Mensaje) o;
        return remitente.equals(mensaje.remitente) &&
                destinatario.equals(mensaje.destinatario) &&
                contenido.equals(mensaje.contenido) &&
                fechaHora.equals(mensaje.fechaHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remitente, destinatario, contenido, fechaHora);
    }
}