package biblio;

public class ErregZenbEzezaguna extends Throwable {
    public ErregZenbEzezaguna() {

    }

    public ErregZenbEzezaguna(String err) {
        System.err.println(err + " erregistro zenbakia duen obra ez da existitzen.");
    }
}
