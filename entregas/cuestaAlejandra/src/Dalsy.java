class Dalsy {
    List<Nino> ninosPequenos;

    public Dalsy(List<Nino> ninosPequenos) {
        this.ninosPequenos = ninosPequenos;
    }

    public void cuidar() {
        System.out.println("\nDalsy: Yo cuidaré a los pequeños:");
        ninosPequenos.forEach(System.out::println);
    }
}