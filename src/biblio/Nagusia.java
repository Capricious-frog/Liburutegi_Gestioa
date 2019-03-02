package biblio;

import javax.swing.JFrame;

import gui.LeihoNagusi;

public class Nagusia {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Liburutegia lib = Liburutegia.getInstance();
		lib.kargatuKatalogoaFitxategitik(); //kargatu katalogoa aplikazioa abiatzean

		LeihoNagusi leihoNagusia = new LeihoNagusi();
		leihoNagusia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		leihoNagusia.setVisible(true);
	}

}
