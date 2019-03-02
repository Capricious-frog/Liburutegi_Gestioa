package gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//
//import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
//import javax.swing.JComboBox;
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
public class OinarrizkoAleLeiho extends JDialog {

	private JPanel edukiPanela; //formulario-osagai grafikoak edukitzeko panela

	/**
	 * testu-koadroak
	 */
	private JTextField txtErregZenbakia;
	private JTextField txtSignatura;
	private JTextField txtIzenburua;
	private JCheckBox chkMaileguanDa;

	/**
	 * etiketak
	 */
	private JLabel lblMaileguanDa;

	
	private boolean ados=false; //'Ados' botoia sakatu denetz erregistratzeko


	/**
	 * Eraikitzailea
	 */
	public OinarrizkoAleLeiho() {
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 610, 313);
		edukiPanela = new JPanel();
		edukiPanela.setBorder(new EmptyBorder(5, 5, 5, 5));
		edukiPanela.setLayout(new FormLayout(new ColumnSpec[] { 
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
				new RowSpec[] {
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

		JLabel lblIzenburua = new JLabel("Izenburua");
		edukiPanela.add(lblIzenburua, "2, 6, right, default");

		txtIzenburua = new JTextField();
		edukiPanela.add(txtIzenburua, "4, 6, 7, 1, fill, default");
		txtIzenburua.setColumns(10);

		lblMaileguanDa = new JLabel("Maileguan?");
		edukiPanela.add(lblMaileguanDa, "12, 6, right, default");

		chkMaileguanDa = new JCheckBox();
		chkMaileguanDa.setVisible(true);
		chkMaileguanDa.setEnabled(false);
		chkMaileguanDa.setSelected(false);
		edukiPanela.add(chkMaileguanDa, "14, 6, fill, default");


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

//				//zenbakizko eremuak behar bezala beteta egon daitezela:
//				if ((txtLiburukiKop.isVisible() && !zenbOsoaDa(txtLiburukiKop.getText())) ||
//						(txtAldizkZenbakia.isVisible() && !zenbOsoaDa(txtAldizkZenbakia.getText()))){
//					mezua = mezua + " Zenbakizko eremuetan zenbakizko balio egokiak (osoak edo errealak) sartu behar dira.";
//				}
//
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

//		formularioOsagaiEzKomunakEzkutatu();

//		//aldizkariei dagozkien formulario-osagaiak bistaratu:
//		lblAldizkZenbakia.setVisible(true);
//		txtAldizkZenbakia.setVisible(true);
//		lblISSN.setVisible(true);
//		txtISSN.setVisible(true);

	}

	/**
	 * 'Ados' botoia sakatu denetz determinatzen du
	 * @return true, 'Ados' botoia sakatu bada; false, bestela.
	 */
	public boolean adosSakaturik () {
		return ados;
	}


	//leihoko balioak eskuratzeko metodoak:

//	/**
//	 * @return obra motaren lista konbinatuan hautatutako produktu mota
//	 */
//	public String getObraMota() {
//		return cbxObraMotak.getSelectedItem().toString();
//	}

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

//	/**
//	 * @return egile/zuzendari/interpretearen koadroan sartutako balioa
//	 */
//	public String getTxtEgZuzInterpr() {
//		return txtEgZuzInterpr.getText();
//	}
//
//	/**
//	 * @return argitaletxe/ekoizlearen koadroan sartutako balioa
//	 */
//	public String getTxtArgitEkoiz() {
//		return txtArgitEkoiz.getText();
//	}
//
//	/**
//	 * @return liburuki kopuruaren koadroan sartutako balioa
//	 */
//	public int getTxtLiburukiKop() {
//		return Integer.parseInt(txtLiburukiKop.getText());
//	}
//
//	/**
//	 * @return aldizkari-zenbakiaren koadroan sartutako balioa
//	 */
//	public int getTxtAldizkariZenbakia() {
//		return Integer.parseInt(txtAldizkZenbakia.getText());
//	}
//
//	/**
//	 * @return ISSNaren koadroan sartutako balioa
//	 */
//	public String getTxtISSN() {
//		return txtISSN.getText();
//	}
//	
//	/**
//	 * @return deskribapenaren koadroan sartutako balioa
//	 */
//	public String getTxtDeskribapena() {
//		return txtDeskribapena.getText();
//	}
//
//	/**
//	 * @return gaiaren koadroan sartutako balioa
//	 */
//	public String getTxtGaia() {
//		return txtGaia.getText();
//	}
//
//	/**
//	 * @return hizkuntzen koadroan sartutako balioa
//	 */
//	public String getTxtHizkuntza() {
//		return txtHizkuntza.getText();
//	}


	// eragiketa lagungarriak (pribatuak):
	
//	/**
//	 * Balidazio-eragiketa: string bat zenbaki oso baten adierazpidea denetz determinatzen du
//	 * @param n balidatu beharreko stringa
//	 * @return true, n zenbaki oso baten adierazpidea bada; false, bestela.
//	 */
//	private static boolean zenbOsoaDa(String n) {
//		try {
//			Integer.parseInt(n);
//			return true;
//		} catch (NumberFormatException e) {
//			return false;
//		}
//	}

	
	/**
	 * Hasieratze-eragiketa: leihoko testu-koadroetan string hutsa ezartzen du
	 *                       eta lista konbinatuan lehen balioa hautatzen du
	 */
	private void formularioOsagaiakHasieratu() { 
		for(Component kontrola : edukiPanela.getComponents()) {
			if(kontrola instanceof JTextField) {
				((JTextField)kontrola).setText("");
			} else if (kontrola instanceof JCheckBox) {
				((JCheckBox)kontrola).setSelected(false);
			}
		}
	}

//	/**
//	 * Hasieratze-eragiketa: leihoko testu-koadro gehigarriak ezkutatzen ditu
//	 */
//	private void formularioOsagaiEzKomunakEzkutatu() {
//		lblEgZuzInterpr.setVisible(false);
//		txtEgZuzInterpr.setVisible(false);
//		lblArgitEkoiz.setVisible(false);
//		txtArgitEkoiz.setVisible(false);
//		lblLiburukiKop.setVisible(false);
//		txtLiburukiKop.setVisible(false);
//		lblMaileguanDa.setVisible(false);
//		chkMaileguanDa.setVisible(false);
//		lblAldizkZenbakia.setVisible(false);
//		txtAldizkZenbakia.setVisible(false);
//		lblISSN.setVisible(false);
//		txtISSN.setVisible(false);
//		lblDeskribapena.setVisible(false);	
//		txtDeskribapena.setVisible(false);
//		lblGaia.setVisible(false);
//		txtGaia.setVisible(false);
//		lblHizkuntza.setVisible(false);
//		txtHizkuntza.setVisible(false);				
//	}

}
