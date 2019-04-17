package obrak;

public class Aldizkari extends Obra {
    private int aleZenbakia;
    private String ISSN;

    public Aldizkari(int eZenb, String sig, String izenb, int aleZenbakia, String ISSN) {
        super(eZenb, sig, izenb);
        this.aleZenbakia = aleZenbakia;
        this.ISSN = ISSN;
    }


    public int getAleZenbakia() {
        return aleZenbakia;
    }

    public void setAleZenbakia(int aleZenbakia) {
        this.aleZenbakia = aleZenbakia;
    }
}
