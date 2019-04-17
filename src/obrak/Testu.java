package obrak;

public class Testu extends Obra {
    private String egilea;
    private String argitaletxea;

    public Testu(int eZenb, String sig, String izenb, String egilea, String argitaletxea) {
        super(eZenb, sig, izenb);
        this.egilea = egilea;
        this.argitaletxea = argitaletxea;
    }

    public String getEgilea() {
        return egilea;
    }

    public void setEgilea(String egilea) {
        this.egilea = egilea;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" +
                super.toString() +
                "egilea='" + egilea + '\'' +
                ", argitaletxea='" + argitaletxea + '\'' +
                '}';
    }
}
