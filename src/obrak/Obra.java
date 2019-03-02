package obrak;

import java.io.PrintWriter;

public class Obra {

    private int erregistroZenbakia;
    private String signatura, izenburua;
    private boolean maileguanDa;


    /**
     * Constructor
     */
    public Obra() {}


    /**
     *
     * @param eZenb
     * @param sig
     * @param izenb
     */
    public Obra(int eZenb, String sig, String izenb) {
        erregistroZenbakia = eZenb;
        signatura = sig;
        izenburua = izenb;
    }


    /**
     *
     * @param eZenb
     * @param sig
     * @param izenb
     * @param mai
     */
    public Obra(int eZenb, String sig, String izenb, boolean mai) {
        erregistroZenbakia = eZenb;
        signatura = sig;
        izenburua = izenb;
        maileguanDa = mai;
    }


    /**
     *
     * @return
     */
    public int erregistroZenbakia() {
        return erregistroZenbakia;
    }


    /**
     *
     * @return
     */
    public String signatura() {
        return signatura;
    }


    /**
     *
     * @return
     */
    public String izenburua() {
        return izenburua;
    }


    /**
     *
     * @return
     */
    public boolean maileguanDago() {
        return maileguanDa;
    }


    /**
     *
     */
    public void maileguanEman() {
        if (maileguanDa) {
            // throw exception
        } else {
            maileguanDa = true;
        }
    }


    /**
     *
     */
    public void maileguaKendu() {
        if (maileguanDa) {
            maileguanDa = false;
        } else {
            // throw exception
        }
    }


    /**
     *
     */
    public void inprimatu() {
        System.out.println("Erreg.-zenb.: " + erregistroZenbakia +
                " / Signatura: " + signatura +
                " / Izenburua: " + izenburua +
                " / Maileguan?: " + maileguanDa);
    }


    /**
     *
     * @param pw
     */
    public void idatziFitxategian(PrintWriter pw){
        pw.println(erregistroZenbakia + " " + signatura + " " + izenburua + " " + maileguanDa);
        pw.close();
    }


    /**
     *
     * @return
     */
    public String toString(){
        return "";
    }
}
