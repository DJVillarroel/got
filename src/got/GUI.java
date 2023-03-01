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
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;

public class GUI extends JFrame {

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
	public GUI() {
		logica = new Logic();
		setTitle("Winter is Coming");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnDir = new JButton("Load Directory");
		btnDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (openFileChooser()) btnStart.setEnabled(true);
				
			}
		});
		btnDir.setFont(new Font("HelvLight", Font.BOLD, 20));
		btnDir.setBounds(76, 311, 178, 35);
		contentPane.add(btnDir);
		
		txtLoadingFrom = new JTextField();
		txtLoadingFrom.setFont(new Font("HelvLight", Font.PLAIN, 16));
		txtLoadingFrom.setText("loading from:");
		txtLoadingFrom.setEditable(false);
		txtLoadingFrom.setBounds(48, 266, 255, 35);
		contentPane.add(txtLoadingFrom);
		txtLoadingFrom.setColumns(10);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logica.readTxtFile(selectedFile);
				setTextFields();
			}
		});
		btnStart.setEnabled(false);
		btnStart.setFont(new Font("HelvLight", Font.BOLD, 20));
		btnStart.setBounds(568, 311, 165, 35);
		contentPane.add(btnStart);
		
		btnExit.setFont(new Font("HelvLight", Font.PLAIN, 20));
		btnExit.setBounds(611, 478, 165, 35);
		contentPane.add(btnExit);
		
		JLabel lblMostUsed = new JLabel("5 most used words:");
		lblMostUsed.setFont(new Font("HelvLight", Font.BOLD, 17));
		lblMostUsed.setBounds(367, 129, 262, 26);
		contentPane.add(lblMostUsed);
		
		lblPercent = new JLabel("percentage");
		lblPercent.setFont(new Font("HelvLight", Font.BOLD, 17));
		lblPercent.setBounds(639, 129, 94, 26);
		contentPane.add(lblPercent);
		
		tpWords = new JTextPane();
		tpWords.setFont(new Font("HelvLight", Font.BOLD, 21));
		tpWords.setBounds(367, 165, 262, 136);
		tpWords.setEditable(false);
		contentPane.add(tpWords);
		
		tpPercent = new JTextPane();
		tpPercent.setFont(new Font("HelvLight", Font.BOLD, 21));
		tpPercent.setBounds(639, 165, 94, 136);
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
