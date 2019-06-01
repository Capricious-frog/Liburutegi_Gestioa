package biblio;

public class ErregZenbEzezaguna extends Throwable {
    ErregZenbEzezaguna() {
        super();
    }

    ErregZenbEzezaguna(String err) {
        System.err.println(err + " erregistro zenbakia duen obra ez da existitzen.");
    }
}
