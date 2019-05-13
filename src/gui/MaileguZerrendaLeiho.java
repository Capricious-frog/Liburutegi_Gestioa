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
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.awt.Component;
import java.awt.Dimension;

import biblio.Liburutegia;
import biblio.ErregZenbEzezaguna;
import obrak.IMailegagarri;

@SuppressWarnings("serial")
public class MaileguZerrendaLeiho extends JDialog {

    private JTable taula; //inbentarioko maileguak bistaratzeko taula

    private JLabel lblMaileguZerrenda, lblBehekoOharra; //etiketak

    private JButton btnKatalogoaGorde; //botoiak

    private JPanel botoienPanela = new JPanel();

    private JOptionPane oharra; //ohar-leihoak bistaratzeko panela


    /**
     * klase lagungarria, mailegu zerrenda bistaratzeko
     */
    class MailegagarriTaulaModelo extends AbstractTableModel { //mailegu zerrenda bistaratzeko taularen modeloa

        private final ArrayList<ArrayList<String>> mailegagarrienMatrizea;

        private final ArrayList<String> zutabeIzenak = new ArrayList<String>();

        //	    private final Class[] columnClass = new Class[] {
        //	        Integer.class, String.class, Double.class, Boolean.class
        //	    };

        public MailegagarriTaulaModelo(ArrayList<ArrayList<String>> mailegagarriMatrizea) {
            zutabeIzenak.add("Obra mota");
            zutabeIzenak.add("E.-Zenb.");
            zutabeIzenak.add("Signatura");
            zutabeIzenak.add("Izenburua");
            zutabeIzenak.add("Mailegu-hartzailea");
            zutabeIzenak.add("Mailegu-data");
            zutabeIzenak.add("Itzultze-data");

            this.mailegagarrienMatrizea = mailegagarriMatrizea;
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
            return mailegagarrienMatrizea.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            //			System.out.println("getValueAt: " + rowIndex + ", " + columnIndex + " = " + mailegagarrienMatrizea.get(rowIndex).get(columnIndex)); //***traza
            return mailegagarrienMatrizea.get(rowIndex).get(columnIndex);
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
    public MaileguZerrendaLeiho(ArrayList<ArrayList<String>> aleMatrizea) {

        this.setBounds(100, 100, 650, 400);
        this.getContentPane().setLayout(
                new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        lblMaileguZerrenda = new JLabel("Maileguen zerrenda");
        lblMaileguZerrenda.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(lblMaileguZerrenda);
        lblMaileguZerrenda.setFont(new Font("Tahoma", Font.BOLD, 14));

        this.setTitle("Liburutegia - Maileguen kudeaketa");

        //taulako datuen modeloa:
        MailegagarriTaulaModelo modeloa = new MailegagarriTaulaModelo(aleMatrizea);
        //taula:
        this.taula = new JTable(modeloa);


        //testuinguruko menua (saguaren botoi lagungarria):

        final JPopupMenu testuingurukoMenua = new JPopupMenu();
        JMenuItem mnuMaileguaItzuli = new JMenuItem("Itzuli mailegua");
        JMenuItem mnuMaileguanUtzi = new JMenuItem("Utzi maileguan");

        mnuMaileguaItzuli.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int erantzuna = JOptionPane.YES_OPTION;
                int erregZenb =
                        Integer.parseInt(taula.getModel().
                                getValueAt(taula.getSelectedRow(), 1).toString()); //hautatutako errenkadako erregistro-zenbakia


                // egiaztatu mailegatuta dagoela, bestela ezin da itzuli:
                if (taula.getModel().getValueAt(taula.getSelectedRow(), 4).equals("")) { //ez dago mailegu-hartzailerik
                    JOptionPane.showMessageDialog(
                            null,
                            "Ale hori ezin da itzuli, ez baitago maileguan!",
                            "Liburutegia - Itzulketa ez da zilegi",
                            JOptionPane.OK_OPTION);
                } else { //itzulketa egin
                    erantzuna = JOptionPane.showConfirmDialog(
                            null,
                            erregZenb + " erregistro-zenbakiko alea itzultzera zoaz: ziur zaude?",
                            "Liburutegia - Alea itzuli, baieztapena",
                            JOptionPane.YES_NO_OPTION);

                    Liburutegia lib = Liburutegia.getInstance();

                    if (erantzuna == JOptionPane.YES_OPTION) {
                        try {
                            // erregistro-zenbaki bidezko itzultzea:
                            lib.itzuliObra(erregZenb);
//							lib.ezabatuMailegua(erregZenb); //mailegua ezabatu maileguen zerrendatik
//							((IMailegagarri) lib.erregZenbDuenAlea(erregZenb)).maileguaKendu(); //kendu mailegatua dagoelako ezaugarria aleari berari 
                            freskatu(); //bistaratutako taula freskatzeko
                            JOptionPane.showMessageDialog(taula, erregZenb
                                    + " erregistro-zenbakiko alea itzuli da.");
                        } catch (ErregZenbEzezaguna p) {
                            System.err.println("Ez da aurkitu liburutegian " + erregZenb
                                    + " erregistro-zenbakiko alerik."); //***
                        }
                    }
                }
            }
        });
        testuingurukoMenua.add(mnuMaileguaItzuli);

        mnuMaileguanUtzi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Liburutegia lib = Liburutegia.getInstance();
                int erregZenbakia = Integer.parseInt(taula.getModel().getValueAt(taula.getSelectedRow(), 1).toString()); //hautatutako alearen erreg.-zenbakia
                try {
                    if (((IMailegagarri) lib.erregZenbDuenAlea(erregZenbakia)).maileguanDago()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Ale hori lehendik ere maileguan dago, ezin da mailegatu!",
                                "Liburutegia - Mailegua ez da zilegi",
                                JOptionPane.OK_OPTION);
                    } else { //mailegu berria egitera...
                        try {
                            SimpleDateFormat dF = new SimpleDateFormat("yyyy-MM-dd");

                            // mailegu-itzulketen datak eskuratu:
                            String mData = dF.format(lib.maileguenData());
                            String iData = dF.format(lib.itzulketenData());

                            MaileguLeiho dlgMaileguBerria =
                                    //1. zutabean: erregistro-zenbakia; 2. zutabean: signatura; 3. zutabean: izenburua
                                    new MaileguLeiho( //String erregZenb, String sig, String izenb, String mData, String iData
                                            taula.getModel().getValueAt(taula.getSelectedRow(), 1).toString(),
                                            taula.getModel().getValueAt(taula.getSelectedRow(), 2).toString(),
                                            taula.getModel().getValueAt(taula.getSelectedRow(), 3).toString(),
                                            mData, //dF.format(mData),
                                            iData); //dF.format(iData));
                            //deprecated: dlgMaileguBerria.setModal (true);
                            dlgMaileguBerria.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
                            dlgMaileguBerria.setVisible(true);

                            if (dlgMaileguBerria.adosSakaturik()) {
                                //mailegua gehitu maileguen zerrendan:
                                lib.mailegatuObra( //int erregZenb, String bazkIze, String md, String id
                                        dlgMaileguBerria.getTxtErregZenbakia(),
                                        dlgMaileguBerria.getTxtBazkideIzena(),
                                        dF.parse(mData),
                                        dF.parse(iData)); //dlgMaileguBerria.getTxtItzultzeData());
                                freskatu(); //bistaratutako taula freskatzeko
                                JOptionPane.showMessageDialog(dlgMaileguBerria,
                                        "Mailegu berria gehitu da.");
                            }
                            dlgMaileguBerria.dispose(); //'Ados' zein 'Utzi' sakatu
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                } catch (ErregZenbEzezaguna e2) {
                    e2.printStackTrace(); //***AZTERTU nola tratatu
                }

            }
        });
        testuingurukoMenua.add(mnuMaileguanUtzi);

        this.taula.setComponentPopupMenu(testuingurukoMenua);

        //formularioaren gainerako osagaiak:

        this.getContentPane().add(new JScrollPane(this.taula));
        //this.taula.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        zutabeakDoitu();

        this.lblBehekoOharra = new JLabel("Oharra: Hautatutako alea mailegatu edo itzultzeko," +
                " sakatu saguaren botoi lagungarria (eskuina, normalean) testuinguruko menua bistaratzearren. Atzera egiteko, berriz, itxi leihoa.");
        this.getContentPane().add(this.lblBehekoOharra);

        this.botoienPanela.setLayout(new BoxLayout(this.botoienPanela, BoxLayout.LINE_AXIS));
        this.botoienPanela.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        this.botoienPanela.add(Box.createHorizontalGlue());


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

        this.getContentPane().add(this.botoienPanela);


        this.pack();

    }

    /**
     * Leihoko zutabeen zabalerak doitzen ditu (eragiketa lagungarria)
     */
    private void zutabeakDoitu() {
        this.taula.getColumnModel().getColumn(0).setMinWidth(100); //obra mota
        this.taula.getColumnModel().getColumn(0).setMaxWidth(100); //obra mota
        this.taula.getColumnModel().getColumn(1).setMinWidth(50); //erreg.-zenbakia
        this.taula.getColumnModel().getColumn(1).setMaxWidth(50); //erreg.-zenbakia
//		this.taula.getColumnModel().getColumn(1).setPreferredWidth(5);
        this.taula.getColumnModel().getColumn(2).setMinWidth(100); //signatura
        this.taula.getColumnModel().getColumn(2).setMaxWidth(100); //signatura
//		this.taula.getColumnModel().getColumn(3).setMinWidth(100); //izenburua
//		this.taula.getColumnModel().getColumn(3).setMaxWidth(100); //izenburua
//		this.taula.getColumnModel().getColumn(4).setMinWidth(100); //mailegu-hartzailea
//		this.taula.getColumnModel().getColumn(4).setMaxWidth(100); //mailegu-hartzailea
        this.taula.getColumnModel().getColumn(5).setMinWidth(100); //mailegu-data
        this.taula.getColumnModel().getColumn(5).setMaxWidth(100); //mailegu-data
        this.taula.getColumnModel().getColumn(6).setMinWidth(100); //itzultze-data
        this.taula.getColumnModel().getColumn(6).setMaxWidth(100); //itzultze-data
    }

    /**
     * MailegagarriZerrenda leihoa freskatzen du (eragiketa lagungarria)
     */
    private void freskatu() {
        Liburutegia lib = Liburutegia.getInstance();
        taula.setModel(new MailegagarriTaulaModelo(lib.mailegagarrienMatrizea()));
        zutabeakDoitu();
//		this.revalidate();
//		this.repaint();
    }

}



