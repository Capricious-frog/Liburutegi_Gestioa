package obrak;

public class Multimedia extends Obra {
    private String ekoizlea;

    public Multimedia(int eZenb, String sig, String izenb, String ekoizlea) {
        super(eZenb, sig, izenb);
        this.ekoizlea = ekoizlea;
    }

    public String getEkoizlea() {
        return ekoizlea;
    }

    public void setEkoizlea(String ekoizlea) {
        this.ekoizlea = ekoizlea;
    }
}
