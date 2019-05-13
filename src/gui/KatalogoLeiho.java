package gui;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.awt.Component;
import java.awt.Dimension;

import biblio.Liburutegia;
import biblio.ErregZenbEzezaguna;
import obrak.*;

@SuppressWarnings("serial")
public class KatalogoLeiho extends JDialog {

    private JTable taula; //inbentarioko produktuak bistaratzeko taula

    private JLabel lblKatalogoa, lblBehekoOharra; //etiketak

    private JButton btnObraGehitu, btnKatalogoaGorde; // , btnLeihoaFreskatu; //botoiak

    private JPanel botoienPanela = new JPanel();

    private JOptionPane oharra; //ohar-leihoak bistaratzeko panela


    /**
     * Klase lagungarria, katalogoa bistaratzeko
     */
    class AleTaulaModelo extends AbstractTableModel { //katalogoa bistaratzeko taularen modeloa

        private final ArrayList<ArrayList<String>> aleenMatrizea;

        private final ArrayList<String> zutabeIzenak = new ArrayList<String>();

        //	    private final Class[] columnClass = new Class[] {
        //	        Integer.class, String.class, Double.class, Boolean.class
        //	    };

        public AleTaulaModelo(ArrayList<ArrayList<String>> aleMatrizea) {
            zutabeIzenak.add("Obra mota");
            zutabeIzenak.add("E.-Zenb.");
            zutabeIzenak.add("Signatura");
            zutabeIzenak.add("Izenburua");
            zutabeIzenak.add("Eg./Zuz./Interpr.");
            zutabeIzenak.add("Ek./Argit.");
            zutabeIzenak.add("Deskrib.");
//			zutabeIzenak.add("Maileguan?");
            zutabeIzenak.add("Bestelakoak");

            this.aleenMatrizea = aleMatrizea;
        }

        @Override
        public String getColumnName(int column) {
            return zutabeIzenak.get(column);
        }

        //	    @Override
        //	    public Class<?> getColumnClass(int columnIndex)
        //	    {
        //	        return columnClass[columnIndex];
        //	    }

        @Override
        public int getColumnCount() {
            return zutabeIzenak.size();
        }

        @Override
        public int getRowCount() {
            return aleenMatrizea.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            //System.out.println("getValueAt: " + rowIndex + ", " + columnIndex + " = " + produktuMatrizea.get(rowIndex).get(columnIndex));
            return aleenMatrizea.get(rowIndex).get(columnIndex);
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        //		@Override
        //		public boolean isCellEditable(int row, int col) {
        //			//Note that the data/cell address is constant,
        //			//no matter where the cell appears onscreen.
        //			if (col == 4) { //***kopurua soilik da editagarria (hala ere, ez da editatzen)
        //				return true;
        //			} else {
        //				return false;
        //			}
        //		}

        //		public void insertData(Object[] values){
        //			produktuMatrizea.add(new ArrayList<String>());
        //			for(int i =0; i<values.length; i++){
        //				((ArrayList<String>) produktuMatrizea.get(produktuMatrizea.size()-1)).add(values[i].toString());
        //			}
        //			fireTableDataChanged();
        //		}

        //		public void removeRow(int row){
        //			aleenMatrizea.remove(row);
        //			fireTableDataChanged();
        //		}

    }


    /**
     * Eraikitzailea
     */
    public KatalogoLeiho(ArrayList<ArrayList<String>> aleMatrizea) {

        this.setBounds(100, 100, 800, 400);
        this.getContentPane().setLayout(
                new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        lblKatalogoa = new JLabel("Liburutegiaren katalogoa");
        lblKatalogoa.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(lblKatalogoa);
        lblKatalogoa.setFont(new Font("Tahoma", Font.BOLD, 14));

        this.setTitle("Liburutegia - Katalogoaren kudeaketa");

        //taulako datuen modeloa:
        AleTaulaModelo modeloa = new AleTaulaModelo(aleMatrizea);
        //taula:
        this.taula = new JTable(modeloa);
        //taula = new JTable(produktuMatrizea, zutabeIzenak);

        //testuinguruko menua (saguaren botoi lagungarria):

        final JPopupMenu testuingurukoMenua = new JPopupMenu();
//		JMenuItem mnuMaileguanUtzi = new JMenuItem("Utzi maileguan");
        JMenuItem mnuAleaEzabatu = new JMenuItem("Ezabatu alea");

        mnuAleaEzabatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int erantzuna = JOptionPane.YES_OPTION;
                int erregZenbakia =
                        Integer.parseInt(taula.getModel().
                                getValueAt(taula.getSelectedRow(), 1).toString()); //hautatutako errenkadako erregistro-zenbakia

                Liburutegia lib = Liburutegia.getInstance();
                Obra a;
                try {
                    a = lib.erregZenbDuenAlea(erregZenbakia);
                    if (a instanceof IMailegagarri &&
                            ((IMailegagarri) a).maileguanDago()) { //maileguan badago, baieztarazi ezabatu nahia
                        erantzuna = JOptionPane.showConfirmDialog(
                                null,
                                "Ale hori maileguan dago: aurrera egin nahi duzu?",
                                "Liburutegia - Ale mailegatua, ezabaketa baieztarazi",
                                JOptionPane.YES_NO_OPTION);
                        if (erantzuna == JOptionPane.YES_OPTION) { //ezabatu mailegua eta alea
                            erantzuna = JOptionPane.showConfirmDialog(
                                    null,
                                    erregZenbakia + " erregistro-zenbakiko ale mailegatua ezabatzera zoaz: ziur zaude?",
                                    "Liburutegia - Ale mailegatua ezabatu, baieztapena",
                                    JOptionPane.YES_NO_OPTION);
                            if (erantzuna == JOptionPane.YES_OPTION) {
                                lib.itzuliObra(erregZenbakia);
                                aleaEzabarazi(erregZenbakia);
                            }
                        }

                    } else { //ezabatu alea
                        erantzuna = JOptionPane.showConfirmDialog(
                                null,
                                erregZenbakia + " erregistro-zenbakiko alea ezabatzera zoaz: ziur zaude?",
                                "Liburutegia - Alea ezabatu, baieztapena",
                                JOptionPane.YES_NO_OPTION);
                        if (erantzuna == JOptionPane.YES_OPTION) {
                            aleaEzabarazi(erregZenbakia);
                        }
                    }
                } catch (ErregZenbEzezaguna e1) {
                    e1.printStackTrace(); //***aztertu zer egin hemen
                }
            }
        });
        testuingurukoMenua.add(mnuAleaEzabatu);

        this.taula.setComponentPopupMenu(testuingurukoMenua);


        //formularioaren gainerako osagaiak:

        this.getContentPane().add(new JScrollPane(this.taula));
//		this.taula.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        zutabeakDoitu();

        this.lblBehekoOharra = new JLabel("Oharra: Hautatutako alea ezabatu edota maileguan uzteko," +
                " sakatu saguaren botoi lagungarria (eskuina, normalean) testuinguruko menua bistaratzearren. Atzera egiteko, berriz, itxi leihoa.");
        this.getContentPane().add(this.lblBehekoOharra);

        this.botoienPanela.setLayout(new BoxLayout(this.botoienPanela, BoxLayout.LINE_AXIS));
        this.botoienPanela.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        this.botoienPanela.add(Box.createHorizontalGlue());

        //obra gehitzeko botoia eta dagozkionak:

        this.btnObraGehitu = new JButton("Gehitu obra");
        //		this.getContentPane().add(this.btnObraGehitu);
        this.botoienPanela.add(this.btnObraGehitu);

        ActionListener actObraGehitu = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Liburutegia lib = Liburutegia.getInstance();
                //				if (!inb.kargatutaDago()) {
                //					oharra = new JOptionPane("Produktu bat gehitu aurretik, inbentarioa kargatu egin behar da.",
                //							JOptionPane.WARNING_MESSAGE);
                //					oharLeihoa = oharra.createDialog("Super Online - Oharra");
                //					oharLeihoa.setAlwaysOnTop(true);
                //					oharLeihoa.setVisible(true);
                //				} else {
                try {
                    AleLeiho dlgAleBerria = new AleLeiho();
                    //deprecated: dlgAleBerria.setModal (true);
                    dlgAleBerria.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
                    dlgAleBerria.setVisible(true);

                    if (dlgAleBerria.adosSakaturik()) {
                        //alea eraiki eta gehitu katalogoan, motaren arabera:
                        int erregZenbakia = lib.getHurrengoErregZenbakia();
                        switch (dlgAleBerria.getObraMota()) {
                            case "Aldizkaria":
                                lib.gehituObra( //int eZenb, String sig, String izenb, int zb, String ISSN
                                        new Aldizkari(erregZenbakia,
                                                dlgAleBerria.getTxtSignatura(),
                                                dlgAleBerria.getTxtIzenburua(),
                                                dlgAleBerria.getTxtAldizkariZenbakia(),
                                                dlgAleBerria.getTxtISSN()));
                                break;
                            case "Liburua":
                                lib.gehituObra( //int eZenb, String sig, String izenb, String eg, String arg, String deskr
                                        new Liburu(erregZenbakia,
                                                dlgAleBerria.getTxtSignatura(),
                                                dlgAleBerria.getTxtIzenburua(),
                                                dlgAleBerria.getTxtEgZuzInterpr(),
                                                dlgAleBerria.getTxtArgitEkoiz(),
                                                dlgAleBerria.getTxtDeskribapena()));
                                break;
                            case "Entziklopedia":
                                lib.gehituObra( //int eZenb, String sig, String izenb, String eg, String arg, int lKop, String hizk, String g
                                        new Entziklopedia(erregZenbakia,
                                                dlgAleBerria.getTxtSignatura(),
                                                dlgAleBerria.getTxtIzenburua(),
                                                dlgAleBerria.getTxtEgZuzInterpr(),
                                                dlgAleBerria.getTxtArgitEkoiz(),
                                                dlgAleBerria.getTxtLiburukiKop(),
                                                //dlgAleBerria.getTxtHizkuntza(),
                                                dlgAleBerria.getTxtGaia()));
                                break;
                            case "Hiztegia":
                                lib.gehituObra( //int eZenb, String sig, String izenb, String eg, String arg, int lKop, String hizk
                                        new Hiztegi(erregZenbakia,
                                                dlgAleBerria.getTxtSignatura(),
                                                dlgAleBerria.getTxtIzenburua(),
                                                dlgAleBerria.getTxtEgZuzInterpr(),
                                                dlgAleBerria.getTxtArgitEkoiz(),
                                                dlgAleBerria.getTxtLiburukiKop(),
                                                dlgAleBerria.getTxtHizkuntza()));
                                break;
                            case "DVDa":
                                lib.gehituObra( //int eZenb, String sig, String izenb, String ek, String zuz
                                        new DVD(erregZenbakia,
                                                dlgAleBerria.getTxtSignatura(),
                                                dlgAleBerria.getTxtIzenburua(),
                                                dlgAleBerria.getTxtArgitEkoiz(),
                                                dlgAleBerria.getTxtEgZuzInterpr()));
                                break;
                            case "Musika-CDa":
                                lib.gehituObra( //int eZenb, String sig, String izenb, String ek, String interpr
                                        new MusikaCD(erregZenbakia,
                                                dlgAleBerria.getTxtSignatura(),
                                                dlgAleBerria.getTxtIzenburua(),
                                                dlgAleBerria.getTxtArgitEkoiz(),
                                                dlgAleBerria.getTxtEgZuzInterpr()));
                                break;
                            default: //Ez da ezer egin behar, segurua baita kasu guztiak kontuan hartu direla
                        }
                        freskatu(); //bistaratutako taula freskatzeko
//						taula.setModel(new AleTaulaModelo(lib.katalogokoAleenMatrizea())); //bistaratutako taula freskatzeko
                        JOptionPane.showMessageDialog(dlgAleBerria,
                                erregZenbakia + " erregistro-zenbakiko alea gehitu da.");
                    }
                    dlgAleBerria.dispose(); //'Ados' zein 'Utzi' sakatu
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        this.btnObraGehitu.addActionListener(actObraGehitu);


        this.botoienPanela.add(Box.createRigidArea(new Dimension(10, 0)));


        //katalogoa gordetzeko botoia eta dagozkionak:

        this.btnKatalogoaGorde = new JButton("Gorde katalogoa");
        //		this.getContentPane().add(this.btnKatalogoaGorde);
        this.botoienPanela.add(this.btnKatalogoaGorde);

        ActionListener actKatalogoaGorde = new ActionListener() {
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
        this.btnKatalogoaGorde.addActionListener(actKatalogoaGorde);


        this.botoienPanela.add(Box.createRigidArea(new Dimension(10, 0)));


//		//leihoa freskatzeko botoia eta dagozkionak:
//
//		this.btnLeihoaFreskatu = new JButton("Freskatu leihoa");
//		//		this.getContentPane().add(this.btnKatalogoaGorde);
//		this.botoienPanela.add(this.btnLeihoaFreskatu);
//
//		ActionListener actLeihoaFreskatu = new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				freskatu();
//			}
//		};
//		this.btnLeihoaFreskatu.addActionListener(actLeihoaFreskatu);


        this.getContentPane().add(this.botoienPanela);


        this.pack();

    }


    /**
     * @param erregZenbakia ezabarazi beharreko alearen erregistro-zenbakia
     *                      erregZenbakia erregistro-zenbakidun alea ezabarazten du katalogotik
     */
    private void aleaEzabarazi(int erregZenbakia) {
        Liburutegia lib = Liburutegia.getInstance();
        try {
            // erregistro-zenbaki bidezko ezabaketa:
            lib.kenduObra(erregZenbakia);
            freskatu(); //bistaratutako taula freskatzeko
            JOptionPane.showMessageDialog(taula, erregZenbakia
                    + " erregistro-zenbakiko alea ezabatu da.");
        } catch (ErregZenbEzezaguna p) {
            System.err.println("Ez da aurkitu liburutegian " + erregZenbakia
                    + " erregistro-zenbakiko alerik."); //***tratamendurik???
        }
    }


    /**
     * Katalogo-leihoko zutabeen zabalerak doitzen ditu (eragiketa lagungarria)
     */
    private void zutabeakDoitu() {
        this.taula.getColumnModel().getColumn(0).setMinWidth(100); //obra mota
        this.taula.getColumnModel().getColumn(0).setMaxWidth(100); //obra mota
        this.taula.getColumnModel().getColumn(1).setMinWidth(50); //erreg.-zenbakia
        this.taula.getColumnModel().getColumn(1).setMaxWidth(50); //erreg.-zenbakia
        this.taula.getColumnModel().getColumn(2).setMinWidth(100); //signatura
        this.taula.getColumnModel().getColumn(2).setMaxWidth(100); //signatura
        this.taula.getColumnModel().getColumn(4).setMinWidth(100); //signatura
        this.taula.getColumnModel().getColumn(4).setMaxWidth(100); //signatura
//		this.taula.getColumnModel().getColumn(5).setPreferredWidth(5); //argitaletxea/ekoizlea		
        this.taula.getColumnModel().getColumn(5).setMinWidth(100); //argitaletxea/ekoizlea
        this.taula.getColumnModel().getColumn(5).setMaxWidth(100); //argitaletxea/ekoizlea
        this.taula.getColumnModel().getColumn(6).setMinWidth(150); //deskribapena
        this.taula.getColumnModel().getColumn(6).setMaxWidth(150); //deskribapena
    }

    /**
     * Katalogo-leihoa freskatzen du (eragiketa lagungarria)
     */
    private void freskatu() {
        Liburutegia lib = Liburutegia.getInstance();
        taula.setModel(new AleTaulaModelo(lib.katalogokoAleenMatrizea()));
        zutabeakDoitu();
//		this.revalidate();
//		this.repaint();
    }

}



