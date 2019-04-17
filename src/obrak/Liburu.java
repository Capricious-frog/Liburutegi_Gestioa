package obrak;

public class Liburu extends Testu {
    private String deskribapena;

    public Liburu(int eZenb, String sig, String izenb, String egilea, String argitaletxea, String deskribapena) {
        super(eZenb, sig, izenb, egilea, argitaletxea);
        this.deskribapena = deskribapena;
    }

    public String getDeskribapena() {
        return deskribapena;
    }

    public void setDeskribapena(String deskribapena) {
        this.deskribapena = deskribapena;
    }
}
