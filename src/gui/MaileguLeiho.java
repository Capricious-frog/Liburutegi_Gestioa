package gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class MaileguLeiho extends JDialog {

    private JPanel edukiPanela; //formulario-osagai grafikoak edukitzeko panela


    /**
     * testu-koadroak
     */
    private JTextField txtErregZenbakia;
    private JTextField txtSignatura;
    private JTextField txtIzenburua;
    private JTextField txtBazkideIzena;
    private JTextField txtMaileguData;
    private JTextField txtItzultzeData;

//	private JCheckBox chkMaileguanDa;

    /**
     * etiketak
     */
//	private JLabel lblMaileguanDa;

    private boolean ados = false; //'Ados' botoia sakatu denetz erregistratzeko

    /**
     * 'Ados' botoia sakatu denetz determinatzen du
     *
     * @return true, 'Ados' botoia sakatu bada; false, bestela.
     */
    public boolean adosSakaturik() {
        return ados;
    }

    /**
     * Eraikitzailea
     */
    public MaileguLeiho(String erregZenb, String sig, String izenb, String mData, String iData) {
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

        this.setTitle("Liburutegia - Mailegua egin");

        JLabel lblAlea = new JLabel("Mailegua");
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
        txtErregZenbakia.setText(erregZenb);

        JLabel lblSignatura = new JLabel("Signatura");
        edukiPanela.add(lblSignatura, "8, 4, right, default");

        txtSignatura = new JTextField();
        edukiPanela.add(txtSignatura, "10, 4, fill, default");//4, 1,
        txtSignatura.setColumns(10);
        txtSignatura.setEditable(false);
        txtSignatura.setVisible(true);
        txtSignatura.setEnabled(false);
        txtSignatura.setText(sig);

        JLabel lblIzenburua = new JLabel("Izenburua");
        edukiPanela.add(lblIzenburua, "2, 6, right, default");

        txtIzenburua = new JTextField();
        edukiPanela.add(txtIzenburua, "4, 6, 7, 1, fill, default");
        txtIzenburua.setColumns(10);
        txtIzenburua.setEditable(false);
        txtIzenburua.setVisible(true);
        txtIzenburua.setEnabled(false);
        txtIzenburua.setText(izenb);

        JLabel lblBazkIzena = new JLabel("Bazkidearen izena");
        edukiPanela.add(lblBazkIzena, "2, 8, right, default");

        txtBazkideIzena = new JTextField();
        edukiPanela.add(txtBazkideIzena, "4, 8, 7, 1, fill, default");
        txtBazkideIzena.setColumns(10);
        txtBazkideIzena.setVisible(true);
        txtBazkideIzena.setEnabled(true);

//		SimpleDateFormat dF = new SimpleDateFormat("yyyy-MM-dd");

        JLabel lblMaileguData = new JLabel("Mailegu-data");
        edukiPanela.add(lblMaileguData, "2, 10, right, default");

        txtMaileguData = new JTextField();
        edukiPanela.add(txtMaileguData, "4, 10, 2, 1, fill, default");
        txtMaileguData.setColumns(10);
        txtMaileguData.setEditable(false);
        txtMaileguData.setVisible(true);
        txtMaileguData.setEnabled(false);
        txtMaileguData.setText(mData);

        JLabel lblItzultzeData = new JLabel("Itzultze-data");
        edukiPanela.add(lblItzultzeData, "2, 12, right, default");

        txtItzultzeData = new JTextField();
        edukiPanela.add(txtItzultzeData, "4, 12, 2, 1, fill, default");
        txtItzultzeData.setColumns(10);
        txtItzultzeData.setEditable(false);
        txtItzultzeData.setVisible(true);
        txtItzultzeData.setEnabled(false);
        txtItzultzeData.setText(iData);

//		lblMaileguanDa = new JLabel("Maileguan?");
//		edukiPanela.add(lblMaileguanDa, "12, 6, right, default");
//
//		chkMaileguanDa = new JCheckBox();
//		edukiPanela.add(chkMaileguanDa, "14, 6, fill, default");

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

    }

    /**
     * Hasieratze-eragiketa: leihoko testu-koadroetan string hutsa ezartzen du
     * eta lista konbinatuan lehen balioa hautatzen du
     */
    private void formularioOsagaiakHasieratu() {
        for (Component kontrola : edukiPanela.getComponents()) {
            if (kontrola instanceof JTextField) {
                ((JTextField) kontrola).setText("");
            }
        }
    }


    //leihoko balioak eskuratzeko metodoak:

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
     * @return bazkide-izenaren koadroan sartutako balioa
     */
    public String getTxtBazkideIzena() {
        return txtBazkideIzena.getText();
    }

    /**
     * @return mailegu-dataren koadroan sartutako balioa
     */
    public String getTxtMaileguData() {
        return txtMaileguData.getText();
    }

    /**
     * @return itzultze-dataren koadroan sartutako balioa
     */
    public String getTxtItzultzeData() {
        return txtItzultzeData.getText();
    }


}
