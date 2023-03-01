package got;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;


/**
	@class GUI
	interfaz gráfica principal de la aplicación
*/
public class GUI extends JFrame {

	private ResourceBundle resources;
	private JPanel contentPane;
	private JTextField txtLoadingFrom;
	private JButton btnStart;
	private JButton btnDir;
	private JButton btnExit;
	private Logic logica;
	private File selectedFile;
	private JLabel lblPercent;
	private JTextPane tpWords;
	private JTextPane tpPercent;

	/**
	 * Create the frame.
	 */
	public GUI(ResourceBundle res) {
		logica = new Logic();
		resources = res;
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/img/icon.png")));
		setTitle(resources.getString("title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 348);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnDir = new JButton(resources.getString("dir"));
		btnDir.setBackground(new Color(153, 204, 255));
		btnDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Leer directorio
				
				if (openFileChooser()) btnStart.setEnabled(true);
				
			}
		});
		btnDir.setFont(new Font("HelvLight", Font.BOLD, 20));
		btnDir.setBounds(42, 147, 249, 35);
		contentPane.add(btnDir);
		
		txtLoadingFrom = new JTextField();
		txtLoadingFrom.setBackground(new Color(153, 204, 255));
		txtLoadingFrom.setFont(new Font("HelvLight", Font.PLAIN, 16));
		txtLoadingFrom.setText(resources.getString("url"));
		txtLoadingFrom.setEditable(false);
		txtLoadingFrom.setBounds(22, 102, 288, 35);
		contentPane.add(txtLoadingFrom);
		txtLoadingFrom.setColumns(10);
		
		btnExit = new JButton(resources.getString("exit"));
		btnExit.setBackground(new Color(153, 204, 255));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Salir de la aplicación
				System.exit(0);
			}
		});
		
		btnStart = new JButton(resources.getString("start"));
		btnStart.setBackground(new Color(153, 204, 255));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Leer archivo de texto
				logica.readTxtFile(selectedFile);
				setTextFields();
			}
		});
		btnStart.setEnabled(false);
		btnStart.setFont(new Font("HelvLight", Font.BOLD, 20));
		btnStart.setBounds(530, 192, 165, 35);
		contentPane.add(btnStart);
		
		btnExit.setFont(new Font("HelvLight", Font.BOLD, 20));
		btnExit.setBounds(563, 266, 165, 35);
		contentPane.add(btnExit);
		
		JLabel lblMostUsed = new JLabel(resources.getString("most_used"));
		lblMostUsed.setForeground(SystemColor.control);
		lblMostUsed.setFont(new Font("HelvLight", Font.BOLD, 17));
		lblMostUsed.setBounds(329, 10, 262, 26);
		contentPane.add(lblMostUsed);
		
		lblPercent = new JLabel(resources.getString("pcnt"));
		lblPercent.setForeground(SystemColor.control);
		lblPercent.setFont(new Font("HelvLight", Font.BOLD, 17));
		lblPercent.setBounds(601, 10, 94, 26);
		contentPane.add(lblPercent);
		
		tpWords = new JTextPane();
		tpWords.setBackground(new Color(153, 204, 255));
		tpWords.setFont(new Font("HelvLight", Font.BOLD, 21));
		tpWords.setBounds(329, 46, 262, 136);
		tpWords.setEditable(false);
		contentPane.add(tpWords);
		
		tpPercent = new JTextPane();
		tpPercent.setBackground(new Color(153, 204, 255));
		tpPercent.setFont(new Font("HelvLight", Font.BOLD, 21));
		tpPercent.setBounds(601, 46, 94, 136);
		tpPercent.setEditable(false);
		contentPane.add(tpPercent);
	}
	
	//Llena los campos de texto con las palabras más utilizadas del archivo de texto leído
	protected void setTextFields() {
		String output = "";
		String percentOutput = "";
		for(String word:logica.mostUsedWords()) {
			output += word+"\n";
			percentOutput += String.format("%.2f", logica.percentageOfUsage(word))+"%\n";
		}
		tpWords.setText(output);
		tpPercent.setText(percentOutput);
	}


	//Inicializa la ventana para seleccionar/abrir un archivo
	//Devuelve verdadero si el usuario seleccionó un archivo
	private boolean openFileChooser() {
		boolean opened = false;
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files", "txt"));
		
		int response = fileChooser.showOpenDialog(null); //Selecciona archivo a abrir
		
		if (response == JFileChooser.APPROVE_OPTION) {
			try {
				selectedFile = new File(fileChooser.getSelectedFile().getCanonicalPath());
				txtLoadingFrom.setText(fileChooser.getSelectedFile().getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			opened = true;
		}
		return opened;
	}
}
