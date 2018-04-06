package FinalDBProj;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertWNDW {
	
	private JFrame frmInsertSong;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private String s = null;
	private String a = null;
	private String al = null;
	private boolean dup = false;
	private boolean KeepArtist = false;
	private boolean KeepAlbum = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertWNDW window = new InsertWNDW();
					window.frmInsertSong.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InsertWNDW() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInsertSong = new JFrame();
		frmInsertSong.setTitle("Insert Song");
		frmInsertSong.setBounds(100, 100, 450, 300);
		frmInsertSong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmInsertSong.getContentPane().add(panel, BorderLayout.WEST);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				s = textField.getText();
				if (s.equals("") || s.equals(" ") || s.equals("\t") || s.equals("\n"))
					s = null;
			}
		});
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				a = textField_1.getText();
				if (a.equals("") || a.equals(" ") || a.equals("\t") || a.equals("\n"))
					a = null;
			}
		});
		textField_1.setColumns(10);
		
		
		textField_2 = new JTextField();
		textField_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				al = textField_2.getText();
				if (al.equals("") || al.equals(" ") || al.equals("\t") || al.equals("\n"))
					al = null;
			}
		});
		textField_2.setColumns(10);
		
		JLabel lblSong = new JLabel("Song");
		
		JLabel lblArtist = new JLabel("Artist");
		
		JLabel lblAlbum = new JLabel("Album");
		
		JPanel panel_1 = new JPanel();	
		final JLabel lblStatus = new JLabel("");
		panel_1.add(lblStatus);
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((s != null) && (a != null)) {
					lblStatus.setText("...");
					if (al != null) {
						dup = DBIO.duplicate(s, a, al);
						if (dup) {
							lblStatus.setText("Error: This entry already exists...");
						}
						else {
							DBIO.Insert(s, a, al);
							lblStatus.setText("Success!");
						}//end else
					}//end if al != null
					else {
						dup = DBIO.duplicate(s, a);
						if (dup) {
							lblStatus.setText("Error: This entry already exists...");
						}
						else {
							DBIO.Insert(s, a);
							lblStatus.setText("Success!");
						}//end else
					}//end else
				}//end if s != null and a != null
				else {
					lblStatus.setText("Error: Song and Artist required...");
				}//end else
				textField.setText("");
				s = null;
				
				if (!KeepArtist) {
					textField_1.setText("");
					a = null;
				}//end if
				if (!KeepAlbum) {
					textField_2.setText("");
					al = null;
				}//end if
			}
		});
		
		JCheckBox chckbxKeepArtist = new JCheckBox("Keep Artist");
		chckbxKeepArtist.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				KeepArtist = ! KeepArtist;
			}
		});
		
		JCheckBox chckbxKeepAlbum = new JCheckBox("Keep Album");
		chckbxKeepAlbum.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				KeepAlbum = ! KeepAlbum;
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxKeepArtist)
						.addComponent(chckbxKeepAlbum))
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addComponent(btnSubmit)
					.addGap(146))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(68)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSong)
								.addComponent(lblArtist)
								.addComponent(lblAlbum))
							.addGap(34)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
								.addComponent(textField_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
							.addContainerGap(68, GroupLayout.PREFERRED_SIZE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSong)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblArtist)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAlbum)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(btnSubmit))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxKeepArtist)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxKeepAlbum)))
					.addGap(5)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
}
