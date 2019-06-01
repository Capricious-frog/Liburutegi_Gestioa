package biblio;

import obrak.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Liburutegia {
    private final String ALE_FITXATEGIEN_IZENA = "./src/aleak.txt";
    private final String MAILEGUEN_TXOSTENA = "./src/maileguak.txt";

    private int azkenErregistroZenbakia;

    private ArrayList<Obra> katalogoa = new ArrayList<>();
    private ArrayList<Mailegu> maileguZerrenda = new ArrayList<Mailegu>();
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

        if (katalogoa.isEmpty()) {
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
    public void kargatuKatalogoaEtaMaileguakFitxategitik() {

        System.out.println("Aleak kargatzen ari...");

        try (Scanner scanner = new Scanner(new FileReader(ALE_FITXATEGIEN_IZENA))) {
            String lerroa;
            azkenErregistroZenbakia = 0;
            while (scanner.hasNextLine()) {
                lerroa = scanner.nextLine(); //lehenagotik eginda
                String[] lerroOsagaiak = lerroa.split(" "); //lehenagotik eginda
                // Lehen eremua kenduta duen arraya lortu
                String[] lerroOsagaiakKenBat = Arrays.copyOfRange(lerroOsagaiak, 1, lerroOsagaiak.length);
                Obra alea;//zein obra mota den jakiteko string konparaketa


                switch (lerroOsagaiak[0]) {
                    case "LIBURU":
                        txertatuOrdenean(new Liburu(lerroOsagaiakKenBat));
                        if (Boolean.parseBoolean(lerroOsagaiakKenBat[6])) {
                            maileguZerrenda.add(new Mailegu(Integer.parseInt(lerroOsagaiakKenBat[0]), lerroOsagaiakKenBat[7], new SimpleDateFormat("yyyy-MM-dd").parse(lerroOsagaiakKenBat[8]), new SimpleDateFormat("yyyy-MM-dd").parse(lerroOsagaiakKenBat[9])));
                        }
                        break;
                    case "ALDIZKARI":
                        txertatuOrdenean(new Aldizkari(lerroOsagaiakKenBat));
                        break;
                    case "DVD":
                        txertatuOrdenean(new DVD(lerroOsagaiakKenBat));
                        if (Boolean.parseBoolean(lerroOsagaiakKenBat[5])) {
                            maileguZerrenda.add(new Mailegu(Integer.parseInt(lerroOsagaiakKenBat[0]), lerroOsagaiakKenBat[6], new SimpleDateFormat("yyyy-MM-dd").parse(lerroOsagaiakKenBat[7]), new SimpleDateFormat("yyyy-MM-dd").parse(lerroOsagaiakKenBat[8])));
                        }
                        break;
                    case "ENTZIKLOPEDIA":
                        txertatuOrdenean(new Entziklopedia(lerroOsagaiakKenBat));
                        break;
                    case "MUSIKACD":
                        txertatuOrdenean(new MusikaCD(lerroOsagaiakKenBat));
                        if (Boolean.parseBoolean(lerroOsagaiakKenBat[5])) {
                            maileguZerrenda.add(new Mailegu(Integer.parseInt(lerroOsagaiakKenBat[0]), lerroOsagaiakKenBat[6], new SimpleDateFormat("yyyy-MM-dd").parse(lerroOsagaiakKenBat[7]), new SimpleDateFormat("yyyy-MM-dd").parse(lerroOsagaiakKenBat[8])));
                        }
                        break;
                    default:
                        throw new Exception("Obra mota ezezaguna: " + lerroOsagaiak[0]);
                }
            }

            azkenErregistroZenbakia = katalogoa.get(katalogoa.size() - 1).getErregistroZenbakia();

            System.out.println("...kargatu dira katalogoko aleak fitxategitik.");

        } catch (FileNotFoundException e) {
            System.out.println("...fitxategia ez da existitzen.");
            System.out.println();
            e.printStackTrace();
            System.exit(-1);

        } catch (Exception e) {
            System.out.println("...ezin izan dira aleak kargatu fitxategitik.");
            System.out.println();
            e.printStackTrace();
            System.exit(-1);
        }
    }


    /**
     * Emandako erregistro zenbakia duen obra itzultzen du. Emandako erregistro zenbakia duen obrarik ez badago,
     * errore-mezua idatzi irteera estandarretik eta itzuli obra “hutsa” emaitzatzat
     *
     * @param erregZenb Erregistro zenbakia
     * @return Obra erregistro zenbaki hori duena
     */
    public Obra erregZenbDuenAlea(int erregZenb) throws ErregZenbEzezaguna {
        boolean aurkitua = false;
        int pos = 0;

        for (int i = 0; i < katalogoa.size() && !aurkitua; i++) {
            if (katalogoa.get(i).getErregistroZenbakia() == erregZenb) {
                aurkitua = true;
                pos = i;
            }
        }

        if (aurkitua) {
            return katalogoa.get(pos);
        } else {
            throw new ErregZenbEzezaguna(String.valueOf(erregZenb));
        }

    }

    /**
     * @param erregZenb
     * @return
     * @throws ErregZenbEzezaguna
     */
    public Mailegu erregZenbDuenMailegua(int erregZenb) throws ErregZenbEzezaguna {
        for (Mailegu mailegu : this.maileguZerrenda) {
            if ((mailegu.getErregistroZenbakia() == erregZenb))
                return mailegu;
        }
        throw new ErregZenbEzezaguna();
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
    public void kenduObra(int erregZenb) throws ErregZenbEzezaguna {
        boolean aurkitua = false;

        for (int i = 0; i < katalogoa.size() && !aurkitua; i++) {
            if (katalogoa.get(i).getErregistroZenbakia() == erregZenb) {
                aurkitua = true;
                katalogoa.remove(i);
            }
        }

        if (!aurkitua) {
            throw new ErregZenbEzezaguna(String.valueOf(erregZenb));
        }

    }


    /**
     * Erregistro zenbakia emanik, erregistro zenbaki hori duen obramaileguan egotera pasako da.
     * Obra dagoeneko maileguan badago, mezu egokia idatziko dapantailan eta ez da mailegua egingo.
     *
     * @param erregZenb      Erregistro zenbakia
     * @param izena
     * @param date Gaurko data
     * @param itzulketenData Itzulketa data
     */
    public void mailegatuObra(int erregZenb, String izena, Date date, Date itzulketenData) throws ErregZenbEzezaguna {
        if (erregZenbDuenAlea(erregZenb) instanceof IMailegagarri && !((IMailegagarri) erregZenbDuenAlea(erregZenb)).isMaileguanDa()) {
            maileguZerrenda.set(maileguZerrenda.indexOf(erregZenbDuenMailegua(erregZenb)), new Mailegu(erregZenb, izena, date, itzulketenData));
            ((IMailegagarri) erregZenbDuenAlea(erregZenb)).maileguanEman();
        }
    }


    /**
     * Erregistro zenbakia emanik, erregistro zenbaki hori duen obra itzuli dutelaadieraziko du (dagoeneko ez dago maileguan).
     * Obra maileguan ez badago, mezu egokiaidatziko da pantailan.
     *
     * @param erregZenb Erregistro zenbakia
     */
    public void itzuliObra(int erregZenb) throws ErregZenbEzezaguna {
        if (erregZenbDuenAlea(erregZenb) instanceof IMailegagarri && ((IMailegagarri) erregZenbDuenAlea(erregZenb)).isMaileguanDa()) {
            maileguZerrenda.set(maileguZerrenda.indexOf(erregZenbDuenMailegua(erregZenb)), new Mailegu(erregZenb, "", null, null));
            ((IMailegagarri) erregZenbDuenAlea(erregZenb)).maileguaKendu();
        }
    }

    /**
     * Katalogoko obrak bistaratzen ditu pantailan, Obra klasekoinprimatu metodoaz baliatuz
     */
    public void katalogoaBistaratu() {
        System.out.println("====================== KATALOGOA ========================");

        for (Obra obra : katalogoa) {
            obra.inprimatu();
        }

        System.out.println("=========================================================");
    }

    /*
        /**
         * maileguak.txt fitxategian maileguan dauden katalogoko obren informazioa idazten du,
         * Obra klaseko idatziFitxategian metodoaz baliatuz.
         */
    /*
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

            for (Obra obra : katalogoa) {
                if (obra.getMaileguanDago()) {
                    pw.printf("%-12s %-9s %-25s\n", obra.getErregistroZenbakia(), obra.getSignatura(), obra.getIzenburua());
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
    }*/


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

            for (Obra obra : this.katalogoa) {
                String lerroa = obra.toString();
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
     * @return Gaurko data
     */
    public Date maileguenData() {
        return new Date();
    }


    /**
     * @return Itzulketen data
     */
    public Date itzulketenData() {
        Calendar egutegia = Calendar.getInstance();
        egutegia.setTime(new Date()); //gaurko eguna
        egutegia.add(Calendar.DATE, 12); //12 egun maileguan

        return egutegia.getTime();
    }

    /**
     * @return Maileguan dauden obrak
     */
    public int zenbatMailegu() {
        return maileguZerrenda.size();
    }


    /**
     * @return Katalogoen aleen matrizea
     */
    public ArrayList<ArrayList<String>> katalogokoAleenMatrizea() {
        ArrayList<ArrayList<String>> obraBerria = new ArrayList<>();

        for (Obra obra : katalogoa) {
            obraBerria.add(obra.ezaugarrienLista());
        }

        return obraBerria;
    }


    /**
     * @return Mailegagarrien matrizea
     */
    public ArrayList<ArrayList<String>> mailegagarrienMatrizea() {
        ArrayList<ArrayList<String>> matrizea = new ArrayList<ArrayList<String>>();

        System.out.println("Maileguen txostena sortzen ari...");
        try {
            for (Mailegu mailegu : maileguZerrenda) {
                matrizea.add(mailegu.maileguEzaugarriakGehituta(((IMailegagarri) erregZenbDuenAlea(mailegu.getErregistroZenbakia())).maileguEzaugarrienLista()));
            }
        } catch (ErregZenbEzezaguna e) {
            e.printStackTrace();
        }
        return matrizea;
    }

}
