package biblio;

import obrak.Obra;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Liburutegia {
    private final int  OBRA_KOP_MAX = 50;
    private final String ALE_FITXATEGIEN_IZENA = "aleak.txt";
    private final String MAILEGUEN_TXOSTENA = "maileguak.txt";

    private int zenbatObra, azkenErregistroZenbakia;

    private Obra[] katalogoa = new Obra[OBRA_KOP_MAX];
    private static Liburutegia instantzia = null;

    private Liburutegia(){}

    /**
     *
     * @return Liburutegi motako instantzia
     */
    public static Liburutegia getInstance() {

        if (instantzia == null){
            instantzia = new Liburutegia();
        }

        return instantzia;
    }

    public int getHurrengoErregZenbakia() {
        return azkenErregistroZenbakia + 1;
    }

    private void txertatuOrdenean (Obra obra){
        Obra lag;

        /*
         * i posizioko erregistro zenbakia i + 1 -ena baino handiagoa baldin bada
         * posizioak aldatzen dira.
         */
        for (int i = 0; i < azkenErregistroZenbakia; i++) {
            if (katalogoa[i].getErregistroZenbakia() > katalogoa[i + 1].getErregistroZenbakia()) {
                lag = katalogoa[i];
                katalogoa[i] = katalogoa[i + 1];
                katalogoa[i + 1] = lag;
            }
        }
    }

    public void kargatuKatalogoaFitxategitik() {
        String[] lag;
        zenbatObra = 0;

        try {
            Scanner scanner = new Scanner(new FileReader(ALE_FITXATEGIEN_IZENA));

            for (int i = 0; scanner.hasNextLine(); i++) {
                lag = scanner.nextLine().split(" ");
                katalogoa[i] = new Obra(Integer.valueOf(lag[0]), lag[1], lag[2], Boolean.valueOf(lag[3]));
                zenbatObra++;
                azkenErregistroZenbakia = katalogoa[i].getErregistroZenbakia();
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Obra erregZenbDuenAlea(int erregZenb) {
        boolean aurkitua = false;
        int pos = 0;

        for (int i = 0; i < zenbatObra && !aurkitua; i++) {
            if (katalogoa[i].getErregistroZenbakia() == erregZenb ) {
                aurkitua = true;
                pos = i;
            }
        }

        return aurkitua ? katalogoa[pos] : new Obra();
    }

    public void gehituObra (Obra obra) {
        katalogoa[zenbatObra] = obra;
        zenbatObra++;
    }

    public void kenduObra (int erregZenb) {
        zenbatObra--;
    }

    public void mailegatuObra(int erregZenb) {
        erregZenbDuenAlea(erregZenb).maileguanEman();
    }

    public void itzuliObra(int erregZenb) {
        erregZenbDuenAlea(erregZenb).maileguaKendu();
    }

    public void katalogoaBistaratu() {
        System.out.println("====================== KATALOGOA ========================");

        for (int i = 0; i < zenbatObra; i++) {
            katalogoa[i].inprimatu();
        }

        System.out.println("=========================================================");
    }

    public void maileguenTxostenaSortu () {
        System.out.println("Maileguen txostena sortzen ari...");

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

            //OSATU: ...


            pw.close();
            System.out.println("... sortu da maileguen txostena.");
            System.out.println();
        } catch (FileNotFoundException e) { //(IOException e) {
            System.err.println("... ezin izan da maileguen txostena sortu.");
            System.err.println();
            e.printStackTrace();
        }
    }


    public void gorde() {

    }
}
