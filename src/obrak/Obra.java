package obrak;

import java.io.PrintWriter;

public class Obra {

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


    /**
     * @return Maileguan dagoen edo ez
     */
    public boolean getMaileguanDago() {
        return maileguanDa;
    }


    /**
     * Obra maileguan jartzen du
     */
    public void maileguanEman() {
        if (maileguanDa) {
            // throw exception
        } else {
            maileguanDa = true;
        }
    }


    /**
     * Obra mailegutik kentzen du
     */
    public void maileguaKendu() {
        if (maileguanDa) {
            maileguanDa = false;
        } else {
            // throw exception
        }
    }


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
