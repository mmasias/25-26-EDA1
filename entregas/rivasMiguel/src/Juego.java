public class Juego {

  private Ludoteca ludoteca;

  public Juego(Ludoteca ludoteca) {
    this.ludoteca = ludoteca;
  }

  public void prepararJuegoRana(Monitora dalsy) {
    Monitora rec = ludoteca.getReceptora();
    if (rec == null) {
      System.out.println("No hay monitora receptora para iniciar el juego de la rana.");
      return;
    }

    Nino[] menores = rec.extraerMenoresConPosiciones(5);
    if (menores.length > 0) {
      for (Nino n : menores) {
        dalsy.ponerEnCuidado(n);
      }
      System.out.println(
          "Se han movido "
              + menores.length
              + " niño(s) al cuidado de "
              + dalsy.getNombre()
              + " para el juego.");
    } else {
      System.out.println("No hay niños menores de 5 años para mover al cuidado.");
    }

    System.out.println("\n==> Jugando al juego de la rana ...\n");
  }

  public void terminarJuegoRana(Monitora dalsy) {
    Monitora rec = ludoteca.getReceptora();
    if (rec == null) {
      System.out.println("No hay monitora receptora para terminar el juego de la rana.");
      return;
    }

    rec.restaurarMenoresBackup();

    dalsy.limpiarCuidado();

    System.out.println("\n==> Fin del juego de la rana. Niños devueltos a su posición original.\n");
  }
}
