package obrak;

import java.util.ArrayList;

public class Multimedia extends Obra implements IMailegagarri {
    private String ekoizlea;
    private boolean maileguanDa;

    public Multimedia(int eZenb, String sig, String izenb, String ekoizlea) {
        super(eZenb, sig, izenb);
        this.ekoizlea = ekoizlea;
    }

    public String getEkoizlea() {
        return ekoizlea;
    }

    public void setEkoizlea(String ekoizlea) {
        this.ekoizlea = ekoizlea;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" +
                super.toString() +
                "ekoizlea='" + ekoizlea + '\'' +
                '}';
    }

    @Override
    public ArrayList<String> ezaugarrienLista() {
        ArrayList<String> eL = super.ezaugarrienLista();
        eL.add(ekoizlea);

        return eL;
    }

    @Override
    public void maileguanEman() {
        maileguanDa = true;
    }

    @Override
    public void maileguaKendu() {
        maileguanDa = false;
    }

    @Override
    public boolean maileguanDago() {
        return maileguanDa;
    }

    @Override
    public void maileguEzaugarrienLista() {

    }
}
