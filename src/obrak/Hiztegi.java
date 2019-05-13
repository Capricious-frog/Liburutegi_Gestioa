package obrak;

import java.util.ArrayList;

public class Hiztegi extends Konposatu {
    private String hizkuntzak;

    public Hiztegi(int eZenb, String sig, String izenb, String egilea, String argitaletxea, int liburukiKopurua, String hizkuntzak) {
        super(eZenb, sig, izenb, egilea, argitaletxea, liburukiKopurua);
        this.hizkuntzak = hizkuntzak;
    }

    public Hiztegi(String[] alea) {
        super(Integer.parseInt(alea[1]), alea[2], alea[3], alea[4], alea[5], Integer.parseInt(alea[6]));
        this.hizkuntzak = alea[7];
    }

    public String getHizkuntzak() {
        return hizkuntzak;
    }

    public void setHizkuntzak(String hizkuntzak) {
        this.hizkuntzak = hizkuntzak;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" +
                super.toString() +
                "hizkuntzak='" + hizkuntzak + '\'' +
                '}';
    }

    @Override
    public ArrayList<String> ezaugarrienLista() {
        ArrayList<String> eL = super.ezaugarrienLista();
        eL.add(hizkuntzak);

        return eL;
    }
}
