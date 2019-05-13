package gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class AleLeiho extends JDialog {

    private JPanel edukiPanela; //formulario-osagai grafikoak edukitzeko panela

    /**
     * testu-koadroak
     */
    private JTextField txtErregZenbakia;
    private JTextField txtSignatura;
    private JTextField txtIzenburua;
    private JTextField txtEgZuzInterpr;
    private JTextField txtArgitEkoiz;
    private JTextField txtDeskribapena;
    private JCheckBox chkMaileguanDa;
    private JTextField txtLiburukiKop;
    private JTextField txtAldizkZenbakia;
    private JTextField txtISSN;
    private JTextField txtGaia;
    private JTextField txtHizkuntza;

    /**
     * etiketak
     */
    private JLabel lblAldizkZenbakia;
    private JLabel lblEgZuzInterpr;
    private JLabel lblArgitEkoiz;
    private JLabel lblDeskribapena;
    private JLabel lblMaileguanDa;
    private JLabel lblLiburukiKop;
    private JLabel lblHizkuntza;
    private JLabel lblGaia;
    private JLabel lblISSN;

    private boolean ados = false; //'Ados' botoia sakatu denetz erregistratzeko

    private JComboBox<String> cbxObraMotak; //ale moten lista konbinatu hedagarria


    /**
     * Eraikitzailea
     */
    public AleLeiho() {
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setBounds(100, 100, 610, 313);
        edukiPanela = new JPanel();
        edukiPanela.setBorder(new EmptyBorder(5, 5, 5, 5));
        edukiPanela.setLayout(new FormLayout(new ColumnSpec[]{
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),},
                new RowSpec[]{
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,}));
        setContentPane(edukiPanela);

        this.setTitle("Liburutegia - Alea gehitu");

        JLabel lblAlea = new JLabel("OBRA (Alea)");
        lblAlea.setFont(new Font("Tahoma", Font.BOLD, 14));
        edukiPanela.add(lblAlea, "10, 2");

        JLabel lblErregZenbakia = new JLabel("Erregistro-zenbakia");
        edukiPanela.add(lblErregZenbakia, "2, 4, right, default");

        txtErregZenbakia = new JTextField();
        edukiPanela.add(txtErregZenbakia, "4, 4, fill, default");
        txtErregZenbakia.setColumns(10);
        txtErregZenbakia.setEditable(false);
        txtErregZenbakia.setVisible(true);
        txtErregZenbakia.setEnabled(false);

        JLabel lblSignatura = new JLabel("Signatura");
        edukiPanela.add(lblSignatura, "8, 4, right, default");

        txtSignatura = new JTextField();
        edukiPanela.add(txtSignatura, "10, 4, fill, default");//4, 1,
        txtSignatura.setColumns(10);

        JLabel lblMota = new JLabel("Mota");
        edukiPanela.add(lblMota, "12, 4, right, default");

        cbxObraMotak = new JComboBox<String>();
        cbxObraMotak.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String item = e.getItem().toString();
                    switch (item) {
                        case "Aldizkaria":
                            formularioOsagaiEzKomunakEzkutatu();
                            lblAldizkZenbakia.setVisible(true);
                            txtAldizkZenbakia.setVisible(true);
                            lblISSN.setVisible(true);
                            txtISSN.setVisible(true);
                            break;
                        case "Liburua":
                            formularioOsagaiEzKomunakEzkutatu();
                            lblEgZuzInterpr.setText("Egilea");
                            lblEgZuzInterpr.setVisible(true);
                            txtEgZuzInterpr.setVisible(true);
                            lblArgitEkoiz.setText("Argitaletxea");
                            lblArgitEkoiz.setVisible(true);
                            txtArgitEkoiz.setVisible(true);
                            lblDeskribapena.setVisible(true);
                            txtDeskribapena.setVisible(true);
                            lblMaileguanDa.setVisible(true);
                            chkMaileguanDa.setVisible(true);
                            chkMaileguanDa.setEnabled(false); //berria sortzerako aldi berean ezin da mailegatu
                            break;
                        case "Entziklopedia":
                            formularioOsagaiEzKomunakEzkutatu();
                            lblEgZuzInterpr.setText("Egilea");
                            lblEgZuzInterpr.setVisible(true);
                            txtEgZuzInterpr.setVisible(true);
                            lblArgitEkoiz.setText("Argitaletxea");
                            lblArgitEkoiz.setVisible(true);
                            txtArgitEkoiz.setVisible(true);
                            lblLiburukiKop.setVisible(true);
                            txtLiburukiKop.setVisible(true);
                            lblGaia.setVisible(true);
                            txtGaia.setVisible(true);
                            lblHizkuntza.setText("Hizkuntza");
                            lblHizkuntza.setVisible(false);
                            txtHizkuntza.setVisible(false);
                            break;
                        case "Hiztegia":
                            formularioOsagaiEzKomunakEzkutatu();
                            lblEgZuzInterpr.setText("Egilea");
                            lblEgZuzInterpr.setVisible(true);
                            txtEgZuzInterpr.setVisible(true);
                            lblArgitEkoiz.setText("Argitaletxea");
                            lblArgitEkoiz.setVisible(true);
                            txtArgitEkoiz.setVisible(true);
                            lblLiburukiKop.setVisible(true);
                            txtLiburukiKop.setVisible(true);
                            lblHizkuntza.setText("Hizkuntzak");
                            lblHizkuntza.setVisible(true);
                            txtHizkuntza.setVisible(true);
                            break;
                        case "DVDa":
                            formularioOsagaiEzKomunakEzkutatu();
                            lblEgZuzInterpr.setText("Zuzendaria");
                            lblEgZuzInterpr.setVisible(true);
                            txtEgZuzInterpr.setVisible(true);
                            lblArgitEkoiz.setText("Ekoizlea");
                            lblArgitEkoiz.setVisible(true);
                            txtArgitEkoiz.setVisible(true);
                            lblMaileguanDa.setVisible(true);
                            chkMaileguanDa.setVisible(true);
                            chkMaileguanDa.setEnabled(false); //berria sortzerako aldi berean ezin da mailegatu
                            break;
                        case "Musika-CDa":
                            formularioOsagaiEzKomunakEzkutatu();
                            lblEgZuzInterpr.setText("Interpretea");
                            lblEgZuzInterpr.setVisible(true);
                            txtEgZuzInterpr.setVisible(true);
                            lblArgitEkoiz.setText("Ekoizlea");
                            lblArgitEkoiz.setVisible(true);
                            txtArgitEkoiz.setVisible(true);
                            lblMaileguanDa.setVisible(true);
                            chkMaileguanDa.setVisible(true);
                            chkMaileguanDa.setEnabled(false); //berria sortzerako aldi berean ezin da mailegatu
                            break;
                        default: ////Ez da ezer egin behar, segurua baita kasu guztiak kontuan hartu direla
                    }
                }
            }

        });

        cbxObraMotak.setModel(new DefaultComboBoxModel<String>(
                new String[]{"Aldizkaria", "Liburua", "Entziklopedia", "Hiztegia", "DVDa", "Musika-CDa"}));
        edukiPanela.add(cbxObraMotak, "14, 4, 7, 1, fill, default");

        JLabel lblIzenburua = new JLabel("Izenburua");
        edukiPanela.add(lblIzenburua, "2, 6, right, default");

        txtIzenburua = new JTextField();
        edukiPanela.add(txtIzenburua, "4, 6, 7, 1, fill, default");
        txtIzenburua.setColumns(10);

        lblMaileguanDa = new JLabel("Maileguan?");
        edukiPanela.add(lblMaileguanDa, "12, 6, right, default");

        chkMaileguanDa = new JCheckBox();
        edukiPanela.add(chkMaileguanDa, "14, 6, fill, default");

        lblEgZuzInterpr = new JLabel(); //("Eg./Zuz./Interpr.");
        edukiPanela.add(lblEgZuzInterpr, "2, 8, right, default");

        txtEgZuzInterpr = new JTextField();
        edukiPanela.add(txtEgZuzInterpr, "4, 8, 7, 1, fill, default");
        txtEgZuzInterpr.setColumns(10);

        lblLiburukiKop = new JLabel("Liburuki kop.");
        edukiPanela.add(lblLiburukiKop, "12, 8, right, default");

        txtLiburukiKop = new JTextField();
        edukiPanela.add(txtLiburukiKop, "14, 8, fill, default");
        txtLiburukiKop.setColumns(10);

        lblArgitEkoiz = new JLabel(); //("Ekoizlea/Argit.");
        edukiPanela.add(lblArgitEkoiz, "2, 10, right, default");

        txtArgitEkoiz = new JTextField();
        edukiPanela.add(txtArgitEkoiz, "4, 10, 7, 1, fill, default");
        txtArgitEkoiz.setColumns(10);

        lblDeskribapena = new JLabel("Deskribapena");
        edukiPanela.add(lblDeskribapena, "2, 12, right, default");

        txtDeskribapena = new JTextField();
        edukiPanela.add(txtDeskribapena, "4, 12, 7, 1, fill, default");
        txtDeskribapena.setColumns(10);

        lblGaia = new JLabel("Gaia");
        edukiPanela.add(lblGaia, "2, 14, right, default");

        txtGaia = new JTextField();
        edukiPanela.add(txtGaia, "4, 14, 7, 1, fill, default");
        txtGaia.setColumns(10);

        lblHizkuntza = new JLabel(); //("Hizkuntza(k)");
        edukiPanela.add(lblHizkuntza, "12, 14, right, default");

        txtHizkuntza = new JTextField();
        edukiPanela.add(txtHizkuntza, "14, 14, fill, default");
        txtHizkuntza.setColumns(10);

        lblAldizkZenbakia = new JLabel("Aldizkari-zenb.");
        edukiPanela.add(lblAldizkZenbakia, "2, 16, right, default");

        txtAldizkZenbakia = new JTextField();
        edukiPanela.add(txtAldizkZenbakia, "4, 16, fill, default");
        txtAldizkZenbakia.setColumns(10);

        lblISSN = new JLabel("ISSN");
        edukiPanela.add(lblISSN, "12, 16, right, default");

        txtISSN = new JTextField();
        edukiPanela.add(txtISSN, "14, 16, fill, default");
        txtISSN.setColumns(10);


        JButton btnUtzi = new JButton("Utzi");
        btnUtzi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formularioOsagaiakHasieratu();
                ados = false;
                setVisible(false);
            }
        });

        JButton btnAdos = new JButton("Ados");
        btnAdos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //eremuen balidazioa egin aurrena:
                String mezua = "";

                //derrigorrez bete beharrekoak beteta egon daitezela:
                for (Component kontrola : edukiPanela.getComponents()) {
                    if ((kontrola instanceof JTextField) &&
                            (kontrola.isVisible()) &&
                            (kontrola.isEnabled())) {
                        //						System.out.println(kontrola.getName());
                        if (((JTextField) kontrola).getText().equals("")) {
                            mezua = "Agerian dauden eremu guztietan sartu behar da balioren bat.";
                            break;
                        }
                    }
                }

                //zenbakizko eremuak behar bezala beteta egon daitezela:
                if ((txtLiburukiKop.isVisible() && !zenbOsoaDa(txtLiburukiKop.getText())) ||
                        (txtAldizkZenbakia.isVisible() && !zenbOsoaDa(txtAldizkZenbakia.getText()))) {
                    mezua = mezua + " Zenbakizko eremuetan zenbakizko balio egokiak (osoak edo errealak) sartu behar dira.";
                }

                if (mezua.equals("")) {
                    ados = true;
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, mezua, "Liburutegia - Balidazio-errorea",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        JButton btnGarbitu = new JButton("Garbitu");
        btnGarbitu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formularioOsagaiakHasieratu();
            }
        });

        edukiPanela.add(btnAdos, "4, 20");
        edukiPanela.add(btnGarbitu, "12, 20");
        edukiPanela.add(btnUtzi, "14, 20");

        formularioOsagaiEzKomunakEzkutatu();

        //comboBox.setSelectedIndex(0); //Aldizkaria hautatu eta dagozkion osagaiak bistaratzeko (***ez dabil ondo eraikitzailean)
        //aldizkariei dagozkien formulario-osagaiak bistaratu:
        lblAldizkZenbakia.setVisible(true);
        txtAldizkZenbakia.setVisible(true);
        lblISSN.setVisible(true);
        txtISSN.setVisible(true);

    }

    /**
     * 'Ados' botoia sakatu denetz determinatzen du
     *
     * @return true, 'Ados' botoia sakatu bada; false, bestela.
     */
    public boolean adosSakaturik() {
        return ados;
    }


    //leihoko balioak eskuratzeko metodoak:

    /**
     * @return obra motaren lista konbinatuan hautatutako produktu mota
     */
    public String getObraMota() {
        return cbxObraMotak.getSelectedItem().toString();
    }

    /**
     * @return erregistro-zenbakiaren koadroan sartutako balioa
     */
    public int getTxtErregZenbakia() {
        return Integer.parseInt(txtErregZenbakia.getText());
    }

    /**
     * @return signaturaren koadroan sartutako balioa
     */
    public String getTxtSignatura() {
        return txtSignatura.getText();
    }

    /**
     * @return izenburuaren koadroan sartutako balioa
     */
    public String getTxtIzenburua() {
        return txtIzenburua.getText();
    }

    /**
     * @return egile/zuzendari/interpretearen koadroan sartutako balioa
     */
    public String getTxtEgZuzInterpr() {
        return txtEgZuzInterpr.getText();
    }

    /**
     * @return argitaletxe/ekoizlearen koadroan sartutako balioa
     */
    public String getTxtArgitEkoiz() {
        return txtArgitEkoiz.getText();
    }

    /**
     * @return liburuki kopuruaren koadroan sartutako balioa
     */
    public int getTxtLiburukiKop() {
        return Integer.parseInt(txtLiburukiKop.getText());
    }

    /**
     * @return aldizkari-zenbakiaren koadroan sartutako balioa
     */
    public int getTxtAldizkariZenbakia() {
        return Integer.parseInt(txtAldizkZenbakia.getText());
    }

    /**
     * @return ISSNaren koadroan sartutako balioa
     */
    public String getTxtISSN() {
        return txtISSN.getText();
    }

    /**
     * @return deskribapenaren koadroan sartutako balioa
     */
    public String getTxtDeskribapena() {
        return txtDeskribapena.getText();
    }

    /**
     * @return gaiaren koadroan sartutako balioa
     */
    public String getTxtGaia() {
        return txtGaia.getText();
    }

    /**
     * @return hizkuntzen koadroan sartutako balioa
     */
    public String getTxtHizkuntza() {
        return txtHizkuntza.getText();
    }


    // eragiketa lagungarriak (pribatuak):

    /**
     * Balidazio-eragiketa: string bat zenbaki oso baten adierazpidea denetz determinatzen du
     *
     * @param n balidatu beharreko stringa
     * @return true, n zenbaki oso baten adierazpidea bada; false, bestela.
     */
    private static boolean zenbOsoaDa(String n) {
        try {
            Integer.parseInt(n);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     * Hasieratze-eragiketa: leihoko testu-koadroetan string hutsa ezartzen du
     * eta lista konbinatuan lehen balioa hautatzen du
     */
    private void formularioOsagaiakHasieratu() {
        for (Component kontrola : edukiPanela.getComponents()) {
            if (kontrola instanceof JTextField) {
                ((JTextField) kontrola).setText("");
            } else if (kontrola instanceof JComboBox<?>) {
                ((JComboBox<?>) kontrola).setSelectedIndex(0);
            } else if (kontrola instanceof JCheckBox) {
                ((JCheckBox) kontrola).setSelected(false);
            }
        }
    }

    /**
     * Hasieratze-eragiketa: leihoko testu-koadro gehigarriak ezkutatzen ditu
     */
    private void formularioOsagaiEzKomunakEzkutatu() {
        lblEgZuzInterpr.setVisible(false);
        txtEgZuzInterpr.setVisible(false);
        lblArgitEkoiz.setVisible(false);
        txtArgitEkoiz.setVisible(false);
        lblLiburukiKop.setVisible(false);
        txtLiburukiKop.setVisible(false);
        lblMaileguanDa.setVisible(false);
        chkMaileguanDa.setVisible(false);
        lblAldizkZenbakia.setVisible(false);
        txtAldizkZenbakia.setVisible(false);
        lblISSN.setVisible(false);
        txtISSN.setVisible(false);
        lblDeskribapena.setVisible(false);
        txtDeskribapena.setVisible(false);
        lblGaia.setVisible(false);
        txtGaia.setVisible(false);
        lblHizkuntza.setVisible(false);
        txtHizkuntza.setVisible(false);
    }

}
