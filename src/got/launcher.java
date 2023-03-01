package got;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class launcher {
	
	private static JFrame frame;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					crearFrames();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	private static void crearFrames() {
		frame = new langSelector();
		frame.setVisible(true);
	}
}
