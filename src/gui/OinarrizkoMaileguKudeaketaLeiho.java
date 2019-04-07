package gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

import biblio.Liburutegia;
import obrak.Obra;
import biblio.ErregZenbEzezaguna;

@SuppressWarnings("serial")
public class OinarrizkoMaileguKudeaketaLeiho extends JDialog {

    private JLabel lblMaiKudeaketa; //etiketak

    private JButton btnMaiTxostenaSortu, btnObraMailegatu, btnObraItzuli, btnKatBistaratu, btnKatGorde; //botoiak

    private JOptionPane oharra; //ohar-leihoak bistaratzeko panela


    /**
     * Eraikitzailea
     */
    public OinarrizkoMaileguKudeaketaLeiho() {

        this.getContentPane().setLayout(
                new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        lblMaiKudeaketa = new JLabel("Maileguak");
        lblMaiKudeaketa.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(lblMaiKudeaketa);
        lblMaiKudeaketa.setFont(new Font("Tahoma", Font.BOLD, 14));

        this.setTitle("Liburutegia - Maileguen kudeaketa");


        // menu-barra sortu eta markoan gehitu
        JMenuBar menuBarra = new JMenuBar();
        this.setJMenuBar(menuBarra);

        // Drop down menua sortu eta menu-barrari egokitu
        JMenu menua = new JMenu("Menua");
        menuBarra.add(menua);


        // Menua sortu:
        JMenuItem mnuMaiTxostenaSortu = new JMenuItem("Sortu maileguen txostena");
        JMenuItem mnuObraMailegatu = new JMenuItem("Mailegatu obra");
        JMenuItem mnuObraItzuli = new JMenuItem("Itzuli obra");
        JMenuItem mnuKatBistaratu = new JMenuItem("Bistaratu katalogoa");
        JMenuItem mnuKatGorde = new JMenuItem("Gorde katalogoa");

        menua.add(mnuMaiTxostenaSortu);
        menua.addSeparator();
        menua.add(mnuObraMailegatu);
        menua.add(mnuObraItzuli);
        menua.addSeparator();
        menua.add(mnuKatBistaratu);
        menua.add(mnuKatGorde);

        // Botoiak sortu:
        btnMaiTxostenaSortu = new JButton("Sortu maileguen txostena");
        this.add(btnMaiTxostenaSortu);
        btnObraMailegatu = new JButton("Mailegatu obra");
        this.add(btnObraMailegatu);
        btnObraItzuli = new JButton("Itzuli obra");
        this.add(btnObraItzuli);
        btnKatBistaratu = new JButton("Bistaratu katalogoa");
        this.add(btnKatBistaratu);
        btnKatGorde = new JButton("Gorde katalogoa");
        this.add(btnKatGorde);

        this.pack();


        ActionListener actMaiTxostenaSortu = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Liburutegia lib = Liburutegia.getInstance();
                lib.maileguenTxostenaSortu();
            }
        };

        ActionListener actObraMailegatu = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //eskatu erabiltzaileari erregistro-zenbakia sar dezan:
                String erregZenbakia = JOptionPane.showInputDialog(null, "Sar ezazu mailegatu beharreko alearen erregistro-zenbakia:", "Erregistro-zenbakia?", JOptionPane.QUESTION_MESSAGE);
                if (erregZenbakia != null) { //ez du Cancel sakatu
                    aleaMailegatu(Integer.parseInt(erregZenbakia));
                }
            }
        };


        ActionListener actObraItzuli = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //eskatu erabiltzaileari erregistro-zenbakia sar dezan:
                String erregZenbakia = JOptionPane.showInputDialog(null, "Sar ezazu itzuli beharreko alearen erregistro-zenbakia:", "Erregistro-zenbakia?", JOptionPane.QUESTION_MESSAGE);
                if (erregZenbakia != null) { //ez du Cancel sakatu
                    aleaItzuli(Integer.parseInt(erregZenbakia));
                }
            }
        };

        ActionListener actKatBistaratu = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Liburutegia lib = Liburutegia.getInstance();
                lib.katalogoaBistaratu();
            }
        };

        ActionListener actKatGorde = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Liburutegia lib = Liburutegia.getInstance();
                lib.gorde();
                oharra = new JOptionPane("Gorde da katalogo eguneratua fitxategian.",
                        JOptionPane.INFORMATION_MESSAGE);
                JDialog oharLeihoa = oharra.createDialog("Liburutegia - Info");
                oharLeihoa.setAlwaysOnTop(true);
                oharLeihoa.setVisible(true);
            }
        };


        //menu-aukerak eta botoiak ekintza berberen bidez erantzun dezaten prestatu:
        mnuMaiTxostenaSortu.addActionListener(actMaiTxostenaSortu);
        btnMaiTxostenaSortu.addActionListener(actMaiTxostenaSortu);

        mnuObraMailegatu.addActionListener(actObraMailegatu);
        btnObraMailegatu.addActionListener(actObraMailegatu);

        mnuObraItzuli.addActionListener(actObraItzuli);
        btnObraItzuli.addActionListener(actObraItzuli);

        mnuKatBistaratu.addActionListener(actKatBistaratu);
        btnKatBistaratu.addActionListener(actKatBistaratu);

        mnuKatGorde.addActionListener(actKatGorde);
        btnKatGorde.addActionListener(actKatGorde);

    }


    /**
     * @param erregZenb mailegatu beharreko alearen erregistro-zenbakia
     *                  erregZenbakia erregistro-zenbakidun alea mailegatzen du (maileguan dagoelako ezaugarriari true balioa ematen dio)
     */
    private void aleaMailegatu(int erregZenb) {
        Liburutegia lib = Liburutegia.getInstance();
        try {
            Obra a = lib.erregZenbDuenAlea(erregZenb);
            //if (a.getErregZenbakia() != 0) { //alea existitzen bada...
            if (a.getMaileguanDago()) {
                JOptionPane.showMessageDialog(
                        null,
                        "Ale hori lehendik ere maileguan dago, ezin da mailegatu!",
                        "Liburutegia - Mailegua ez da zilegi",
                        JOptionPane.OK_OPTION);
            } else { //mailegu berria egitera...
                int erantzuna = JOptionPane.showConfirmDialog(
                        null,
                        erregZenb + " erregistro-zenbakiko alea mailegatzera zoaz: ziur zaude?",
                        "Liburutegia - Alea mailegatu, baieztapena",
                        JOptionPane.YES_NO_OPTION);
                if (erantzuna == JOptionPane.YES_OPTION) {
                    // bazkide izena irakurri
                    String izena = JOptionPane.showInputDialog(null, "Sar ezazu bazkide-izena:", "Izena?", JOptionPane.QUESTION_MESSAGE);
                    // erregistro-zenbaki bidezko mailegatzea:
                    lib.mailegatuObra(erregZenb, izena, lib.maileguenData(), lib.itzulketenData());
//					a.setMaileguanDa(true); //ezarri mailegatua dagoelako ezaugarria aleari berari
                    JOptionPane.showMessageDialog(null, erregZenb
                            + " erregistro-zenbakiko alea mailegatu du " + izena + " bazkideak.");
                }
            }
		/*} else {
			JOptionPane.showMessageDialog(
					null,
					"Ale hori ez da existitzen, ezin da mailegatu!",
					"Liburutegia - Mailegua ez da zilegi",
					JOptionPane.OK_OPTION);
		}*/
        } catch (ErregZenbEzezaguna p) {
            System.err.println("Ez da aurkitu liburutegian " + erregZenb
                    + " erregistro-zenbakiko alerik."); //***NOLA TRATATU
        }
    }

    /**
     * @param erregZenb itzuli beharreko alearen erregistro-zenbakia
     *                  erregZenbakia erregistro-zenbakidun alea itzultzen du (maileguan dagoelako ezaugarriari false balioa ematen dio)
     */
    private void aleaItzuli(int erregZenb) {
        Liburutegia lib = Liburutegia.getInstance();
        try {
            Obra a = lib.erregZenbDuenAlea(erregZenb);
            if (a.getErregZenbakia() != 0) { //alea existitzen bada...
                // egiaztatu mailegatuta dagoela, bestela ezin da itzuli:
//			if (a.getMaileguanDa()==false) { //ez dago maileguan
                if (!a.getMaileguanDago()) { //ez dago maileguan
                    JOptionPane.showMessageDialog(
                            null,
                            "Ale hori ezin da itzuli, ez baitago maileguan!",
                            "Liburutegia - Itzulketa ez da zilegi",
                            JOptionPane.OK_OPTION);
                } else { //itzulketa egin
                    int erantzuna = JOptionPane.showConfirmDialog(
                            null,
                            erregZenb + " erregistro-zenbakiko alea itzultzera zoaz: ziur zaude?",
                            "Liburutegia - Alea itzuli, baieztapena",
                            JOptionPane.YES_NO_OPTION);
                    if (erantzuna == JOptionPane.YES_OPTION) {
                        // erregistro-zenbaki bidezko itzultzea:
                        lib.itzuliObra(erregZenb);
//					a.setMaileguanDa(false); //kendu mailegatua dagoelako ezaugarria aleari berari
                        JOptionPane.showMessageDialog(null, erregZenb
                                + " erregistro-zenbakiko alea itzuli da.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Ale hori ez da existitzen, ezin da itzuli!",
                        "Liburutegia - Itzulketa ez da zilegi",
                        JOptionPane.OK_OPTION);
            }
        } catch (ErregZenbEzezaguna p) {
            System.err.println("Ez da aurkitu liburutegian " + erregZenb
                    + " erregistro-zenbakiko alerik."); //***NOLA TRATATU
        }
    }
}


