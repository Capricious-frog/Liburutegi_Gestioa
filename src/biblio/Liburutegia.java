package biblio;

import obrak.Obra;

import java.io.*;
import java.util.Scanner;

public class Liburutegia {
    private final int OBRA_KOP_MAX = 50;
    private final String ALE_FITXATEGIEN_IZENA = "./src/aleak.txt";
    private final String MAILEGUEN_TXOSTENA = "./src/maileguak.txt";

    private int zenbatObra, azkenErregistroZenbakia;

    private Obra[] katalogoa = new Obra[OBRA_KOP_MAX];
    private static Liburutegia instantzia = null;

    /**
     * Defektuzko eraikitzailea
     */
    private Liburutegia() {
    }


    /**
     * @return Liburutegi motako instantzia
     */
    public static Liburutegia getInstance() {

        if (instantzia == null) {
            instantzia = new Liburutegia();
        }

        return instantzia;
    }


    /**
     * Azken erregistro zenbakiari bat gehitu eta balio berriaitzultzen du.
     *
     * @return Azken erregistroaren hurrengoa
     */
    public int getHurrengoErregZenbakia() {
        return azkenErregistroZenbakia + 1;
    }


    /**
     * @param obra iburutegian rdenatuta sartzeko obra
     */
    private void txertatuOrdenean(Obra obra) {
        boolean txertatua = false;

        if (zenbatObra == 0) {
            katalogoa[zenbatObra] = obra;
            zenbatObra++;
        } else {

            for (int i = 0; i < zenbatObra && !txertatua; i++) {
                
            }
        }

    }


    /**
     * Obrak kargatzen ditu aleak.txt fitxategitik eta ordenan gordeko ditu bere erregistro zenbakiaren arabera
     */
    public void kargatuKatalogoaFitxategitik() {

        zenbatObra = 0;

        System.out.println("Aleak kargatzen ari...");

        try (Scanner scanner = new Scanner(new FileReader(ALE_FITXATEGIEN_IZENA))) {
            String lerroa;
            azkenErregistroZenbakia = 0;

            while (scanner.hasNextLine()) {
                lerroa = scanner.nextLine();
                String[] lerroOsagaiak = lerroa.split(" ");
                txertatuOrdenean(new Obra(Integer.parseInt(lerroOsagaiak[1]), lerroOsagaiak[2], lerroOsagaiak[3], Boolean.parseBoolean(lerroOsagaiak[4])));
            }

            azkenErregistroZenbakia = katalogoa[zenbatObra - 1].getErregistroZenbakia();

            System.out.println("...kargatu dira katalogoko aleak fitxategitik.");

        } catch (Exception e) {
            System.out.println("...ezin izan dira aleak kargatu fitxategitik.");
            System.out.println();
            e.printStackTrace();
        }
    }


    /**
     * Emandako erregistro zenbakia duen obra itzultzen du. Emandakoerregistro zenbakia duen obrarik ez badago,
     * errore-mezua idatzi irteera estandarretik etaitzuli obra “hutsa” emaitzatzat
     *
     * @param erregZenb Erregistro zenbakia
     * @return Obra erregistro zenbaki hori duena
     */
    public Obra erregZenbDuenAlea(int erregZenb) {
        boolean aurkitua = false;
        int pos = 0;

        for (int i = 0; i < zenbatObra && !aurkitua; i++) {
            if (katalogoa[i].getErregistroZenbakia() == erregZenb) {
                aurkitua = true;
                pos = i;
            }
        }

        return aurkitua ? katalogoa[pos] : new Obra();
    }


    /**
     * Obra bat emanik, katalogoan gehitzen du
     *
     * @param obra Gehitzeko obra
     */
    public void gehituObra(Obra obra) {
        katalogoa[zenbatObra] = obra;
        zenbatObra++;
    }


    /**
     * Erregistro zenbaki bat emanik, katalogotik kentzen du erregistro zenbakihori duen obra.
     * Katalogoan ez badago erregistro zenbaki hori duen obrarik errore-mezuaidatzi irteera estandarretik.
     *
     * @param erregZenb Erregistro zenbakia
     */
    public void kenduObra(int erregZenb) {
        boolean aurkitua = false;

        for (int i = 0; i < zenbatObra && !aurkitua; i++) {
            if (katalogoa[i].getErregistroZenbakia() == erregZenb) {
                aurkitua = true;

                ezabatuIGarrenPosizioa(i); // TODO: Esto esta mal, no guarda el cambio
            }
        }

    }


    /**
     * Erregistro zenbakia emanik, erregistro zenbaki hori duen obramaileguan egotera pasako da.
     * Obra dagoeneko maileguan badago, mezu egokia idatziko dapantailan eta ez da mailegua egingo.
     *
     * @param erregZenb
     */
    public void mailegatuObra(int erregZenb) {
        erregZenbDuenAlea(erregZenb).maileguanEman();
    }


    /**
     * Erregistro zenbakia emanik, erregistro zenbaki hori duen obra itzuli dutelaadieraziko du (dagoeneko ez dago maileguan).
     * Obra maileguan ez badago, mezu egokiaidatziko da pantailan.
     *
     * @param erregZenb
     */
    public void itzuliObra(int erregZenb) {
        erregZenbDuenAlea(erregZenb).maileguaKendu();
    }

    /**
     * Katalogoko obrak bistaratzen ditu pantailan, Obra klasekoinprimatu metodoaz baliatuz
     */
    public void katalogoaBistaratu() {
        System.out.println("====================== KATALOGOA ========================");

        for (int i = 0; i < zenbatObra; i++) {
            katalogoa[i].inprimatu();
        }

        System.out.println("=========================================================");
    }


    /**
     * maileguak.txt fitxategian maileguan dauden katalogoko obren informazioa idazten du,
     * Obra klaseko idatziFitxategian metodoaz baliatuz.
     */
    public void maileguenTxostenaSortu() {
        System.out.println("Maileguen txostena sortzen...");

        PrintWriter pw;
        try {
            pw = new PrintWriter(MAILEGUEN_TXOSTENA);
            pw.println("------- Liburutegia: Maileguen zerrenda --------");
            pw.println();

            pw.printf("%-12s %-9s %-25s\n",
                    "Erreg.-zenb.",
                    "  Sign.  ",
                    "        Izenburua        ");

            pw.printf("%-12s %-9s %-25s\n\n",
                    "------------",
                    "---------",
                    "-------------------------");

            for (int i = 0; i < zenbatObra; i++) {
                if (katalogoa[i].getMaileguanDago()) {
                    pw.printf("%-12s %-9s %-25s\n", katalogoa[i].getErregistroZenbakia(), katalogoa[i].getSignatura(), katalogoa[i].getIzenburua());
                }
            }

            pw.close();
            System.out.println("Maileguen txostena sortua izan da.");
            System.out.println();
        } catch (FileNotFoundException e) { //(IOException e) {
            System.err.println("Ezin izan da maileguen txostena sortu.");
            System.err.println();
            e.printStackTrace();
        }
    }


    /**
     * Katalogoko obra kopurua itzultzen du.
     *
     * @return Obra kopurua
     */
    public int getZenbatObra() {
        return zenbatObra;
    }

    /**
     * aleak.txt fitxategian iraultzen du katalogoa.
     */
    public void gorde() {

        System.out.println("Katalogoa gordetzen...");
        FileWriter fw;
        try {
            fw = new FileWriter(ALE_FITXATEGIEN_IZENA, false);

            for (int i = 0; i < this.zenbatObra; i++) {
                String lerroa = this.katalogoa[i].toString();
                fw.write(lerroa); //+"\r\n");
                fw.write("\r\n"); //UNIX edo Linuxen, "\n" nahikoa
            }

            fw.close();
            System.out.println("Aleak fitxategian gorde dira.");
            System.out.println();
        } catch (IOException e) {
            System.out.println("Aleak ezin izan dira gorde.");
            System.out.println();
            e.printStackTrace();
        }
    }


    /**
     * Indize bat emanda katalogoko obra kentzen du.
     *
     * @param i Posizioa
     */
    private void ezabatuIGarrenPosizioa(int i) {
        // Array-aren kopia egiten du i-garren elementua gabe
        if (zenbatObra - i >= 0) {
            System.arraycopy(katalogoa, i + 1, katalogoa, i, zenbatObra - i);
        }
        zenbatObra--;
    }

}
