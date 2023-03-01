package got;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.util.Locale;


public class langSelector extends JFrame {

	private JPanel contentPane;
	private JFrame frame;

	/**
	 * Create the frame.
	 */
	public langSelector() {
		setTitle("Seleccione idioma/Select language");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSelect = new JButton("Start");
		btnSelect.setFont(new Font("HelvLight", Font.PLAIN, 20));
		btnSelect.setBounds(190, 98, 165, 35);
		contentPane.add(btnSelect);
		
		JRadioButton rdbtnEN = new JRadioButton("English");
		rdbtnEN.setSelected(true);
		rdbtnEN.setFont(new Font("HelvLight", Font.PLAIN, 20));
		rdbtnEN.setBounds(168, 46, 99, 35);
		contentPane.add(rdbtnEN);
		
		JRadioButton rdbtnES = new JRadioButton("Espa\u00F1ol");
		rdbtnES.setFont(new Font("HelvLight", Font.PLAIN, 20));
		rdbtnES.setBounds(293, 46, 99, 35);
		contentPane.add(rdbtnES);
		
		ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnEN);
        buttonGroup.add(rdbtnES);
        
        
        btnSelect.addActionListener(e -> {
            if (rdbtnEN.isSelected()) {
                Locale.setDefault(Locale.ENGLISH);
            } else if (rdbtnES.isSelected()) {
                Locale.setDefault(new Locale("es", "ES"));
            }
            dispose();
            frame = new GUI();
            frame.setVisible(true);
        });
	}
}
