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
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtLoadingFrom;
	private JButton btnStart;
	private JButton btnDir;
	private JButton btnExit;
	private Logic logica;

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("Winter is Coming");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnStart = new JButton("Start");
		btnStart.setEnabled(false);
		btnStart.setFont(new Font("HelvLight", Font.BOLD, 20));
		btnStart.setBounds(579, 403, 165, 35);
		contentPane.add(btnStart);
		
		btnDir = new JButton("Load Directory");
		btnDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (openFileChooser()) btnStart.setEnabled(true);
				
			}
		});
		btnDir.setFont(new Font("HelvLight", Font.BOLD, 20));
		btnDir.setBounds(75, 301, 178, 35);
		contentPane.add(btnDir);
		
		txtLoadingFrom = new JTextField();
		txtLoadingFrom.setFont(new Font("HelvLight", Font.PLAIN, 16));
		txtLoadingFrom.setText("loading from:");
		txtLoadingFrom.setEditable(false);
		txtLoadingFrom.setBounds(40, 245, 255, 35);
		contentPane.add(txtLoadingFrom);
		txtLoadingFrom.setColumns(10);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("HelvLight", Font.PLAIN, 20));
		btnExit.setBounds(579, 467, 165, 35);
		contentPane.add(btnExit);
	}
	
	private boolean openFileChooser() {
		boolean opened = false;
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files", "txt"));
		
		int response = fileChooser.showOpenDialog(null); //Selecciona archivo a abrir
		
		if (response == JFileChooser.APPROVE_OPTION) {
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			txtLoadingFrom.setText(fileChooser.getSelectedFile().getAbsolutePath());
			opened = true;
		}
		return opened;
	}
}
