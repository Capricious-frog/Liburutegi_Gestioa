package obrak;

import java.util.ArrayList;

public class Entziklopedia extends Konposatu {
    private String gaia;

    public Entziklopedia(int eZenb, String sig, String izenb, String egilea, String argitaletxea, int liburukiKopurua, String gaia) {
        super(eZenb, sig, izenb, egilea, argitaletxea, liburukiKopurua);
        this.gaia = gaia;
    }

    public Entziklopedia(String[] alea) {
        super(Integer.parseInt(alea[1]), alea[2], alea[3], alea[4], alea[5], Integer.parseInt(alea[6]));
        this.gaia = alea[7];
    }

    public String getGaia() {
        return gaia;
    }

    public void setGaia(String gaia) {
        this.gaia = gaia;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" +
                super.toString() +
                "gaia='" + gaia + '\'' +
                '}';
    }

    @Override
    public ArrayList<String> ezaugarrienLista() {
        ArrayList<String> eL = super.ezaugarrienLista();
        eL.add(gaia);

        return eL;
    }
}
