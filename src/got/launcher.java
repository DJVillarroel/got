package got;

import java.awt.EventQueue;

import javax.swing.JFrame;


/**
 * @Class launcher clase que inicializa la aplicación y los respectivos JFrames
*/

public class launcher {
	
	private static JFrame frame;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					crearFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	//Inicializa el frame principal de la aplicación
	private static void crearFrame() {
		frame = new langSelector();
		frame.setVisible(true);
	}
}
