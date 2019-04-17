package obrak;

public class Entziklopedia extends Konposatu {
    private String gaia;

    public Entziklopedia(int eZenb, String sig, String izenb, String egilea, String argitaletxea, int liburukiKopurua) {
        super(eZenb, sig, izenb, egilea, argitaletxea, liburukiKopurua);
    }

    public String getGaia() {
        return gaia;
    }

    public void setGaia(String gaia) {
        this.gaia = gaia;
    }
}
