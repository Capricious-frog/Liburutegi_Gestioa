package obrak;

import java.util.ArrayList;

public class MusikaCD extends Multimedia {
    private String interpretea;

    public MusikaCD(int eZenb, String sig, String izenb, String ekoizlea, String interpretea) {
        super(eZenb, sig, izenb, ekoizlea);
        this.interpretea = interpretea;
    }

    public MusikaCD(String[] alea) {
        super(Integer.parseInt(alea[1]), alea[2], alea[3], alea[4]);
        this.interpretea = alea[5];
    }

    public String getInterpretea() {
        return interpretea;
    }

    public void setInterpretea(String interpretea) {
        this.interpretea = interpretea;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" +
                super.toString() +
                "interpretea='" + interpretea + '\'' +
                '}';
    }

    @Override
    public ArrayList<String> ezaugarrienLista() {
        ArrayList<String> eL = super.ezaugarrienLista();
        eL.add(interpretea);

        return eL;
    }
}
