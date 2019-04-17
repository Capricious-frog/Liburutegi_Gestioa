package obrak;

public class Hiztegi extends Konposatu {
    private String hizkuntzak;

    public Hiztegi(int eZenb, String sig, String izenb, String egilea, String argitaletxea, int liburukiKopurua) {
        super(eZenb, sig, izenb, egilea, argitaletxea, liburukiKopurua);
    }

    public String getHizkuntzak() {
        return hizkuntzak;
    }

    public void setHizkuntzak(String hizkuntzak) {
        this.hizkuntzak = hizkuntzak;
    }
}
