package biblio;

import obrak.Obra;

public class Liburutegia {
    private final int  OBRA_KOP_MAX = 50;
    private final String ALE_FITXATEGIEN_IZENA = "aleak.txt";
    private final String MAILEGUEN_TXOTENA = "maileguak.txt";

    private int zenbatObra, azkenErregistroZenbakia;

    private Obra[] katalogoa;
    private Liburutegia instantzia;

    private void Liburutegia(){}

    public Liburutegia getInstance() {
        return instantzia;
    }

    public int getHurrengoErregZenbakia() {
        return 0;
    }

    private void txertatuOrdenean (Obra obra){

    }

    public void kargatuKatalogoaFitxategitik(){

    }

    public Obra erregZenbDuenAlea(int erregZenb) {

        return new Obra();
    }

    public void gehituObra (Obra obra) {

    }

    public void kenduObra (int erregZenb) {

    }

    public void mailegatuObra(int erregZenb) {

    }

    public void itzuliObra(int erregZenb) {

    }

    public void katalogoaBistaratu() {

    }

    public void maileguenTxostenaSortu() {

    }

    public void gorde() {

    }
}
