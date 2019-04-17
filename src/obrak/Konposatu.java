package obrak;

public class Konposatu extends Testu {
    private int liburukiKopurua;

    public Konposatu(int eZenb, String sig, String izenb, String egilea, String argitaletxea, int liburukiKopurua) {
        super(eZenb, sig, izenb, egilea, argitaletxea);
        this.liburukiKopurua = liburukiKopurua;
    }

    public int getLiburukiKopurua() {
        return liburukiKopurua;
    }

    public void setLiburukiKopurua(int liburukiKopurua) {
        this.liburukiKopurua = liburukiKopurua;
    }
}
