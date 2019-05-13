package obrak;

import java.util.ArrayList;

public class DVD extends Multimedia {
    private String zuzendaria;

    public DVD(int eZenb, String sig, String izenb, String ekoizlea, String zuzendaria) {
        super(eZenb, sig, izenb, ekoizlea);
        this.zuzendaria = zuzendaria;
    }

    public DVD(String[] alea) {
        super(Integer.parseInt(alea[1]), alea[2], alea[3], alea[4]);
        this.zuzendaria = alea[5];
    }

    public String getZuzendaria() {
        return zuzendaria;
    }

    public void setZuzendaria(String zuzendaria) {
        this.zuzendaria = zuzendaria;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" +
                super.toString() +
                "zuzendaria='" + zuzendaria + '\'' +
                '}';
    }

    @Override
    public ArrayList<String> ezaugarrienLista() {
        ArrayList<String> eL = super.ezaugarrienLista();
        eL.add(zuzendaria);

        return eL;
    }
}
