package obrak;

public class Liburu extends Testu {
    private String deskribapena;

    public Liburu(int eZenb, String sig, String izenb, String egilea, String argitaletxea, String deskribapena) {
        super(eZenb, sig, izenb, egilea, argitaletxea);
        this.deskribapena = deskribapena;
    }

    public Liburu(String[] alea) {
        super(Integer.parseInt(alea[1]), alea[2], alea[3], alea[4], alea[5]);
        this.deskribapena = alea[6];
    }

    public String getDeskribapena() {
        return deskribapena;
    }

    public void setDeskribapena(String deskribapena) {
        this.deskribapena = deskribapena;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" +
                super.toString() +
                "deskribapena='" + deskribapena + '\'' +
                '}';
    }
}
