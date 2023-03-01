package got;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JLabel;


public class langSelector extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JLabel lblUK;
	private JLabel lblES;
	private ResourceBundle r = null;

	/**
	 * Create the frame.
	 */
	public langSelector() {
		setTitle("Select language/Seleccione idioma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSelect = new JButton("Start");
		btnSelect.setFont(new Font("HelvLight", Font.PLAIN, 20));
		btnSelect.setBounds(190, 118, 165, 35);
		contentPane.add(btnSelect);
		
		JRadioButton rdbtnEN = new JRadioButton("English");
		rdbtnEN.setSelected(true);
		rdbtnEN.setFont(new Font("HelvLight", Font.PLAIN, 20));
		rdbtnEN.setBounds(164, 77, 99, 35);
		contentPane.add(rdbtnEN);
		
		JRadioButton rdbtnES = new JRadioButton("Espa\u00F1ol");
		rdbtnES.setFont(new Font("HelvLight", Font.PLAIN, 20));
		rdbtnES.setBounds(289, 77, 99, 35);
		contentPane.add(rdbtnES);
		
		ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnEN);
        buttonGroup.add(rdbtnES);
        
        lblUK = new JLabel("");
        lblUK.setBounds(190, 31, 60, 40);
        lblUK.setIcon(new ImageIcon("src/img/en_UK.png"));
        contentPane.add(lblUK);
        
        lblES = new JLabel("");
        lblES.setBounds(295, 31, 60, 40);
        lblES.setIcon(new ImageIcon("src/img/es_ES.png"));
        contentPane.add(lblES);
        
        
        btnSelect.addActionListener(e -> {
            if (rdbtnEN.isSelected()) {
                r = ResourceBundle.getBundle("cfg/resources_EN", Locale.ENGLISH);
            } else if (rdbtnES.isSelected()) {
            	r = ResourceBundle.getBundle("cfg/resources_ES", new Locale("es", "ES"));
            }
            frame = new GUI(r);
            dispose();
            frame.setVisible(true);
        });
	}
}
