package got;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JRadioButton;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Toolkit;


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
		setIconImage(Toolkit.getDefaultToolkit().getImage(langSelector.class.getResource("/img/icon.png")));
		setTitle("Select language/Seleccione idioma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSelect = new JButton("Start");
		btnSelect.setBackground(new Color(153, 204, 255));
		btnSelect.setForeground(new Color(0, 0, 0));
		btnSelect.setFont(new Font("HelvLight", Font.BOLD, 20));
		btnSelect.setBounds(185, 118, 165, 35);
		contentPane.add(btnSelect);
		
		JRadioButton rdbtnEN = new JRadioButton("English");
		rdbtnEN.setForeground(new Color(204, 204, 204));
		rdbtnEN.setBackground(new Color(0, 0, 102));
		rdbtnEN.setSelected(true);
		rdbtnEN.setFont(new Font("HelvLight", Font.BOLD, 20));
		rdbtnEN.setBounds(160, 77, 103, 35);
		contentPane.add(rdbtnEN);
		
		JRadioButton rdbtnES = new JRadioButton("Espa\u00F1ol");
		rdbtnES.setForeground(new Color(204, 204, 204));
		rdbtnES.setBackground(new Color(0, 0, 102));
		rdbtnES.setFont(new Font("HelvLight", Font.BOLD, 20));
		rdbtnES.setBounds(274, 77, 103, 35);
		contentPane.add(rdbtnES);
		
		ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnEN);
        buttonGroup.add(rdbtnES);
        
        lblUK = new JLabel("");
        lblUK.setBounds(185, 31, 60, 40);
        lblUK.setIcon(new ImageIcon(langSelector.class.getResource("/img/en_UK.png")));
        contentPane.add(lblUK);
        
        lblES = new JLabel("");
        lblES.setBounds(290, 31, 60, 40);
        lblES.setIcon(new ImageIcon(langSelector.class.getResource("/img/es_ES.png")));
        contentPane.add(lblES);
        
        
        btnSelect.addActionListener(e -> { //Selecciona el lenguaje, toma los archivos de recursos en la carpeta /cfg/
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
