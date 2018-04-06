package FinalDBProj;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchWindow {

	private ResultSet rs;
	private int row = -1;
	private boolean MRS = true;
	private ResultSet MainRS;
	private int sort = 0;
	private String s;
	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private JButton btnReset;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnArtist;
	private JRadioButton rdbtnAlbum;
	private JRadioButton rdbtnNone;
	private JLabel lblSortBy;
	private JButton btnDelete;
	private String delTitle = null,
				   delArtist = null,
				   delAlbum = null;
	private JPanel panel;
	private JLabel lblTitle;
	private JLabel lblArtist;
	private JLabel Album;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchWindow window = new SearchWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SearchWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.control);
		frame.setBounds(100, 100, 1300, 800);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setRequestFocusEnabled(false);
		panel.setFocusable(false);
		panel.setFocusTraversalKeysEnabled(false);
		panel.setEnabled(false);
		panel.setBounds(10, 57, 1264, 40);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitle.setBounds(178, 11, 47, 33);
		panel.add(lblTitle);
		
		lblArtist = new JLabel("Artist");
		lblArtist.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblArtist.setBounds(607, 11, 47, 33);
		panel.add(lblArtist);
		
		Album = new JLabel("Album");
		Album.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Album.setBounds(1039, 11, 47, 33);
		panel.add(Album);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.control);
		scrollPane.setEnabled(false);
		scrollPane.setFocusable(false);
		scrollPane.setBounds(10, 78, 1264, 672);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			};
			
			@Override
			public String getColumnName(int index) {
				switch (index) {
					case 0:
						return "Title";
					case 1:
						return "Artist";
					case 2:
						return "Album";
					default:
						return "Error";
				}
			};
		};
		table.setFocusable(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		MainRS = DBIO.getAllSongs(sort);
		table.setModel(DbUtils.resultSetToTableModel(MainRS));
		DBIO.closeDB();
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				s = textField.getText();
			}
		});
		textField.setBounds(451, 11, 213, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (s != null) {
					rs = DBIO.search(s, sort);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					DBIO.closeDB();
					panel.hide();
					panel.show();
					if (MRS)
						MRS = !MRS;
				}//end if
			}
		});

		btnSearch.setBounds(671, 10, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				MainRS = DBIO.getAllSongs(sort);
				table.setModel(DbUtils.resultSetToTableModel(MainRS));
				DBIO.closeDB();
				panel.hide();
				panel.show();
				textField.setText(null);
				s = null;
				if (!MRS)
					MRS = !MRS;
			}
		});

		btnReset.setBounds(766, 10, 89, 23);
		frame.getContentPane().add(btnReset);
		
		JRadioButton rdbtnTitle = new JRadioButton("Title");
		rdbtnTitle.addItemListener(new ItemListener() {
			@SuppressWarnings("deprecation")
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (sort != 1) {
						sort = 1;
						if (MRS) {
							MainRS = DBIO.getAllSongs(sort);
							table.setModel(DbUtils.resultSetToTableModel(MainRS));
							DBIO.closeDB();
						}
						else {
							rs = DBIO.search(s, sort);
							table.setModel(DbUtils.resultSetToTableModel(rs));
							DBIO.closeDB();
						}	
						panel.hide();
						panel.show();
					}//end if
				}
			}
		});

		buttonGroup.add(rdbtnTitle);
		rdbtnTitle.setBounds(370, 33, 64, 23);
		frame.getContentPane().add(rdbtnTitle);
		
		rdbtnArtist = new JRadioButton("Artist");
		rdbtnArtist.addItemListener(new ItemListener() {
			@SuppressWarnings("deprecation")
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (sort != 2) {
						sort = 2;
						if (MRS) {
							MainRS = DBIO.getAllSongs(sort);
							table.setModel(DbUtils.resultSetToTableModel(MainRS));
							DBIO.closeDB();
						}
						else {
							rs = DBIO.search(s, sort);
							table.setModel(DbUtils.resultSetToTableModel(rs));
							DBIO.closeDB();
						}
						panel.hide();
						panel.show();
					}//end if
				}
			}
		});

		buttonGroup.add(rdbtnArtist);
		rdbtnArtist.setBounds(443, 33, 75, 23);
		frame.getContentPane().add(rdbtnArtist);
		
		rdbtnAlbum = new JRadioButton("Album");
		rdbtnAlbum.addItemListener(new ItemListener() {
			@SuppressWarnings("deprecation")
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (sort != 3) {
						sort = 3;
						if (MRS) {
							MainRS = DBIO.getAllSongs(sort);
							table.setModel(DbUtils.resultSetToTableModel(MainRS));
							DBIO.closeDB();
						}
						else {
							rs = DBIO.search(s, sort);
							table.setModel(DbUtils.resultSetToTableModel(rs));
							DBIO.closeDB();
						}
						panel.hide();
						panel.show();
					}//end if
				}
			}
		});

		buttonGroup.add(rdbtnAlbum);
		rdbtnAlbum.setBounds(520, 33, 79, 23);
		frame.getContentPane().add(rdbtnAlbum);
		
		rdbtnNone = new JRadioButton("None");
		rdbtnNone.addItemListener(new ItemListener() {
			@SuppressWarnings("deprecation")
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (sort != 0) {
						sort = 0;
					}//end if
					if (MRS) {
						MainRS = DBIO.getAllSongs(sort);
						table.setModel(DbUtils.resultSetToTableModel(MainRS));
						DBIO.closeDB();
					}
					else {
						rs = DBIO.search(s, sort);
						table.setModel(DbUtils.resultSetToTableModel(rs));
						DBIO.closeDB();
					}
					panel.hide();
					panel.show();
				}
			}
		});

		buttonGroup.add(rdbtnNone);
		rdbtnNone.setSelected(true);
		rdbtnNone.setBounds(609, 33, 55, 23);
		frame.getContentPane().add(rdbtnNone);
		
		lblSortBy = new JLabel("Sort By:");
		lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSortBy.setBounds(300, 33, 64, 16);
		frame.getContentPane().add(lblSortBy);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				row = table.getSelectedRow();
				if (row != -1) {
					delTitle = table.getValueAt(row, 0).toString();
					delArtist = table.getValueAt(row, 1).toString();
					try {
						delAlbum = table.getValueAt(row, 2).toString();
					}
					catch (Exception e1) {
						delAlbum = null;
					}
					if (delAlbum == null) {
						DBIO.delete(delTitle, delArtist);
					}
					else
						DBIO.delete(delTitle, delArtist, delAlbum);
					
					if (MRS) {
						MainRS = DBIO.getAllSongs(sort);
						table.setModel(DbUtils.resultSetToTableModel(MainRS));
						DBIO.closeDB();
					}
					else {
						rs = DBIO.search(s, sort);
						table.setModel(DbUtils.resultSetToTableModel(rs));
						DBIO.closeDB();
					}
					panel.hide();
					panel.show();
				}
			}
		});

		btnDelete.setBounds(53, 10, 89, 23);
		frame.getContentPane().add(btnDelete);
	}
}
