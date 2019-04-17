package obrak;

import biblio.ErregZenbEzezaguna;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;

public abstract class Obra {

    private int erregistroZenbakia;
    private String signatura, izenburua;
    private boolean maileguanDa;


    /**
     * Eraikitzailea
     */
    public Obra() {
    }


    /**
     * Eraikitzailea
     *
     * @param eZenb erregistro zenbakia
     * @param sig   signatura
     * @param izenb izenburua
     */
    public Obra(int eZenb, String sig, String izenb) {
        erregistroZenbakia = eZenb;
        signatura = sig;
        izenburua = izenb;
    }


    /**
     * Eraikitzailea
     *
     * @param eZenb erregistro zenbakia
     * @param sig   signatura
     * @param izenb izenburua
     * @param mai   mailegua
     */
    public Obra(int eZenb, String sig, String izenb, boolean mai) {
        erregistroZenbakia = eZenb;
        signatura = sig;
        izenburua = izenb;
        maileguanDa = mai;
    }


    /**
     * @return Erregistro zenbakia
     */
    public int getErregistroZenbakia() {
        return erregistroZenbakia;
    }


    /**
     * @return Sinadura
     */
    public String getSignatura() {
        return signatura;
    }


    /**
     * @return Izenburua
     */
    public String getIzenburua() {
        return izenburua;
    }

//
//    /**
//     * @return Maileguan dagoen edo ez
//     */
//    public boolean getMaileguanDago() {
//        return maileguanDa;
//    }


//    /**
//     * Obra maileguan jartzen du
//     */
//    public void maileguanEman() throws ErregZenbEzezaguna {
//        if (maileguanDa) {
//            throw new ErregZenbEzezaguna();
//        } else {
//            maileguanDa = true;
//        }
//    }


//    /**
//     * Obra mailegutik kentzen du
//     */
//    public void maileguaKendu() throws ErregZenbEzezaguna {
//        if (maileguanDa) {
//            maileguanDa = false;
//        } else {
//            throw new ErregZenbEzezaguna();
//        }
//    }


    /**
     * Obraren informazioa inprimatzen du
     */
    public void inprimatu() {
        System.out.println("Erreg.-zenb.: " + erregistroZenbakia +
                " / Signatura: " + signatura +
                " / Izenburua: " + izenburua +
                " / Maileguan?: " + maileguanDa);
    }


    /**
     * @param pw Fitxategiaren PrintWriter -a
     */
    public void idatziFitxategian(PrintWriter pw) {
        pw.write("OBRA " + erregistroZenbakia + " " + signatura + " " + izenburua + " " + maileguanDa);
        pw.close();
    }


    /**
     * @return Obraren datuak bueltatzen ditu string batean.
     */
    public String toString() {
        return erregistroZenbakia + " " + signatura + " " + izenburua.replace(" ", "_") + " " + maileguanDa;
    }
}
