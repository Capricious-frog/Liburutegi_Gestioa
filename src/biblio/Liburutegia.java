package biblio;

import obrak.Obra;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Liburutegia {
    private final String ALE_FITXATEGIEN_IZENA = "./src/aleak.txt";
    private final String MAILEGUEN_TXOSTENA = "./src/maileguak.txt";

    private int azkenErregistroZenbakia;

    ArrayList<Obra> katalogoa = new ArrayList<>();
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
     * @param obra Liburutegian ordenatuta sartu beharreko obra
     */
    private void txertatuOrdenean(Obra obra) {
        boolean txertatua = false;

        if (katalogoa.size() == 0) {
            katalogoa.add(obra);
        } else {
            for (int i = katalogoa.size(); i > 0 && !txertatua; i--) {
                if (katalogoa.get(i - 1).getErregistroZenbakia() > obra.getErregistroZenbakia()) {
                    katalogoa.add(i, katalogoa.get(i - 1));
                } else {
                    katalogoa.add(i, obra);
                    txertatua = true;
                }
            }

            if (!txertatua) {
                katalogoa.add(obra);
            }
        }

    }


    /**
     * Obrak kargatzen ditu aleak.txt fitxategitik eta ordenan gordeko ditu bere erregistro zenbakiaren arabera
     */
    public void kargatuKatalogoaFitxategitik() {

        System.out.println("Aleak kargatzen ari...");

        try (Scanner scanner = new Scanner(new FileReader(ALE_FITXATEGIEN_IZENA))) {
            String lerroa;
            azkenErregistroZenbakia = 0;
            while (scanner.hasNextLine()) {
                lerroa = scanner.nextLine();
                String[] lerroOsagaiak = lerroa.split(" ");
                txertatuOrdenean(new Obra(Integer.parseInt(lerroOsagaiak[1]), lerroOsagaiak[2], lerroOsagaiak[3], Boolean.parseBoolean(lerroOsagaiak[4])));
            }

            azkenErregistroZenbakia = katalogoa.get(katalogoa.size() - 1).getErregistroZenbakia();

            System.out.println("...kargatu dira katalogoko aleak fitxategitik.");

        } catch (Exception e) {
            System.out.println("...ezin izan dira aleak kargatu fitxategitik.");
            System.out.println();
            e.printStackTrace();
        }
    }


    /**
     * Emandako erregistro zenbakia duen obra itzultzen du. Emandako erregistro zenbakia duen obrarik ez badago,
     * errore-mezua idatzi irteera estandarretik eta itzuli obra “hutsa” emaitzatzat
     *
     * @param erregZenb Erregistro zenbakia
     * @return Obra erregistro zenbaki hori duena
     */
    public Obra erregZenbDuenAlea(int erregZenb) {
        boolean aurkitua = false;
        int pos = 0;

        for (int i = 0; i < katalogoa.size() && !aurkitua; i++) {
            if (katalogoa.get(i).getErregistroZenbakia() == erregZenb) {
                aurkitua = true;
                pos = i;
            }
        }

        return aurkitua ? katalogoa.get(pos) : new Obra();
    }


    /**
     * Obra bat emanik, katalogoan gehitzen du
     *
     * @param obra Gehitzeko obra
     */
    public void gehituObra(Obra obra) {
        katalogoa.add(obra);
    }


    /**
     * Erregistro zenbaki bat emanik, katalogotik kentzen du erregistro zenbakihori duen obra.
     * Katalogoan ez badago erregistro zenbaki hori duen obrarik errore-mezuaidatzi irteera estandarretik.
     *
     * @param erregZenb Erregistro zenbakia
     */
    public void kenduObra(int erregZenb) {
        boolean aurkitua = false;

        for (int i = 0; i < katalogoa.size() && !aurkitua; i++) {
            if (katalogoa.get(i).getErregistroZenbakia() == erregZenb) {
                aurkitua = true;
                ezabatuIGarrenPosizioa(i);
            }
        }

    }


    /**
     * Erregistro zenbakia emanik, erregistro zenbaki hori duen obramaileguan egotera pasako da.
     * Obra dagoeneko maileguan badago, mezu egokia idatziko dapantailan eta ez da mailegua egingo.
     *
     * @param erregZenb Erregistro zenbakia
     */
    public void mailegatuObra(int erregZenb) {
        erregZenbDuenAlea(erregZenb).maileguanEman();
    }


    /**
     * Erregistro zenbakia emanik, erregistro zenbaki hori duen obra itzuli dutelaadieraziko du (dagoeneko ez dago maileguan).
     * Obra maileguan ez badago, mezu egokiaidatziko da pantailan.
     *
     * @param erregZenb Erregistro zenbakia
     */
    public void itzuliObra(int erregZenb) {
        erregZenbDuenAlea(erregZenb).maileguaKendu();
    }

    /**
     * Katalogoko obrak bistaratzen ditu pantailan, Obra klasekoinprimatu metodoaz baliatuz
     */
    public void katalogoaBistaratu() {
        System.out.println("====================== KATALOGOA ========================");

        for (int i = 0; i < katalogoa.size(); i++) {
            katalogoa.get(i).inprimatu();
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

            for (int i = 0; i < katalogoa.size(); i++) {
                if (katalogoa.get(i).getMaileguanDago()) {
                    pw.printf("%-12s %-9s %-25s\n", katalogoa.get(i).getErregistroZenbakia(), katalogoa.get(i).getSignatura(), katalogoa.get(i).getIzenburua());
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
        return katalogoa.size();
    }

    /**
     * aleak.txt fitxategian iraultzen du katalogoa.
     */
    public void gorde() {

        System.out.println("Katalogoa gordetzen...");
        FileWriter fw;
        try {
            fw = new FileWriter(ALE_FITXATEGIEN_IZENA, false);

            for (int i = 0; i < this.katalogoa.size(); i++) {
                String lerroa = this.katalogoa.get(i).toString();
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
        if (katalogoa.size() - i >= 0) {
            System.arraycopy(katalogoa, i + 1, katalogoa, i, katalogoa.size() - i);
        }
    }

}