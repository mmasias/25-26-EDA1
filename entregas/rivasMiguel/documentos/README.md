# Vista Pública

### Clase Pedido
```java
public class Pedido {

    public enum Tipo {
        BEBIDA, CAFE, COLACAO, BOCADILLO, ENSALADA
    }

    public Pedido(Tipo tipo);

    public Tipo getTipo();
}
```

### Clase ArbolPedidos
```java
public class ArbolPedidos {

    public ArbolPedidos();

    public void insertar(Pedido pedido);
    public Pedido extraerMinimo();
    public Pedido consultarMinimo();

    public boolean estaVacio();
    public int getCantidadPedidos();
    public int getComparaciones();
}
```

### Clase Cocinero
```java
public class Cocinero {

    public Cocinero();

    public void agregarPedido(Pedido pedido);
    public Pedido getPedidoActual();
    public int getPedidosPendientes();
    public int getComparacionesTotales();
}
```

### Clase Simulacion
```java
public class Simulacion {

    public Simulacion(int duracionMinutos);

    public void iniciar();

    public int getPedidosAtendidos();
    public int getPedidosPendientes();
    public int getTiempoTotalEspera();
    public double getTiempoMedioEspera();
    public int getComparacionesTotales();
}
```