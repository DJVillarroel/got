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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;

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
		setTitle(resources.getString("title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnDir = new JButton(resources.getString("dir"));
		btnDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (openFileChooser()) btnStart.setEnabled(true);
				
			}
		});
		btnDir.setFont(new Font("HelvLight", Font.BOLD, 20));
		btnDir.setBounds(30, 192, 215, 35);
		contentPane.add(btnDir);
		
		txtLoadingFrom = new JTextField();
		txtLoadingFrom.setFont(new Font("HelvLight", Font.PLAIN, 16));
		txtLoadingFrom.setText(resources.getString("url"));
		txtLoadingFrom.setEditable(false);
		txtLoadingFrom.setBounds(10, 147, 255, 35);
		contentPane.add(txtLoadingFrom);
		txtLoadingFrom.setColumns(10);
		
		btnExit = new JButton(resources.getString("exit"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnStart = new JButton(resources.getString("start"));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logica.readTxtFile(selectedFile);
				setTextFields();
			}
		});
		btnStart.setEnabled(false);
		btnStart.setFont(new Font("HelvLight", Font.BOLD, 20));
		btnStart.setBounds(530, 192, 165, 35);
		contentPane.add(btnStart);
		
		btnExit.setFont(new Font("HelvLight", Font.PLAIN, 20));
		btnExit.setBounds(563, 266, 165, 35);
		contentPane.add(btnExit);
		
		JLabel lblMostUsed = new JLabel(resources.getString("most_used"));
		lblMostUsed.setFont(new Font("HelvLight", Font.BOLD, 17));
		lblMostUsed.setBounds(329, 10, 262, 26);
		contentPane.add(lblMostUsed);
		
		lblPercent = new JLabel(resources.getString("pcnt"));
		lblPercent.setFont(new Font("HelvLight", Font.BOLD, 17));
		lblPercent.setBounds(601, 10, 94, 26);
		contentPane.add(lblPercent);
		
		tpWords = new JTextPane();
		tpWords.setFont(new Font("HelvLight", Font.BOLD, 21));
		tpWords.setBounds(329, 46, 262, 136);
		tpWords.setEditable(false);
		contentPane.add(tpWords);
		
		tpPercent = new JTextPane();
		tpPercent.setFont(new Font("HelvLight", Font.BOLD, 21));
		tpPercent.setBounds(601, 46, 94, 136);
		tpPercent.setEditable(false);
		contentPane.add(tpPercent);
	}
	
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
