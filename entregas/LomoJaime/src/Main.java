public class Main {
    public static void main(String[] args) {
        ListaLista listaPrincipal = new ListaLista(3);

        ListaInt lista1 = new ListaInt(3);
        lista1.add(10);
        lista1.add(20);
        lista1.add(30);

        ListaInt lista2 = new ListaInt(2);
        lista2.add(40);
        lista2.add(50);

        ListaInt lista3 = new ListaInt(1);
        lista3.add(99);
     
        listaPrincipal.add(lista1);
        listaPrincipal.add(lista2);
        listaPrincipal.add(lista3);
        
        for (int i = 0; i < listaPrincipal.size(); i++) {
            ListaInt sublista = listaPrincipal.get(i).getDato();
            System.out.print("Fila " + i + ": ");
            for (int j = 0; j < sublista.size(); j++) {
                System.out.print(sublista.get(j).getDato() + " ");
            }
            System.out.println();
        }

        int valor = listaPrincipal.get(1).getDato().get(0).getDato();
        System.out.println("\nElemento en [1][0] = " + valor);
    }
}
