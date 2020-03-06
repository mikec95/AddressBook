import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author Michael Carbonari Address Book Class that allows you to add, remove,
 *         update, and save
 *
 */
public class AddressBook extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Create elements on GUI
	 */
	JTable jt;
	String[] btns = { "Add", "Remove", "Update", "Save" };
	JButton add, remove, edit, save, load;

	/*
	 * Address book constructor
	 */
	AddressBook() {
		/*
		 * Create and initialize empty array for data and column names. initialize table
		 */
		String[] columnName = { "First Name", "Last Name", "Phone Number", "City" };// declare column names in array
		String[][] data = null;
		jt = new JTable(new DefaultTableModel(data, columnName)) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		};

		/*
		 * basic config for table
		 */
		jt.setAutoCreateRowSorter(true);
		jt.setPreferredScrollableViewportSize(new Dimension(450, 390));
		jt.setFillsViewportHeight(true);

		/*
		 * Add scroll window to frame
		 */
		JScrollPane jps = new JScrollPane(jt);
		add(jps);

		/*
		 * create button panel
		 */
		JPanel buttonPanel = new JPanel(new GridLayout());

		/*
		 * create buttons and add buttons to button panel
		 */
		Buttons b = new Buttons();
		add = new JButton("Add");
		add.addActionListener(b);
		buttonPanel.add(add);
		remove = new JButton("Remove");
		remove.addActionListener(b);
		buttonPanel.add(remove);
		save = new JButton("Save");
		save.addActionListener(b);
		buttonPanel.add(save);
		load = new JButton("Load");
		load.addActionListener(b);
		buttonPanel.add(load);

		/*
		 * Add button panel to the bottom of the page.
		 */
		add(buttonPanel, BorderLayout.SOUTH);
	}

	/*
	 * Button handlers
	 */
	class Buttons implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JButton jb = (JButton) ae.getSource();
			if (jb.equals(add)) {
				DefaultTableModel model = (DefaultTableModel) jt.getModel();// case
																			// DefaultTableModle
																			// to
																			// jt
																			// so
																			// we
																			// may
																			// addRow();
				model.addRow(new Object[] { "", "", "" });// adds row with empty
															// strings
			} else if (jb.equals(remove)) {
				int selectedRow = jt.getSelectedRow();// get what row is
														// selected to then
														// delete
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "Select a row first!");// message
																				// if
																				// hit
																				// remove
																				// without
																				// selected
																				// row
				} else {
					((DefaultTableModel) jt.getModel()).removeRow(selectedRow);// remove
																				// selected
																				// row
				}
			} else if (jb.equals(save)) {
				try {
					File file = new File("Contacts.txt");// file to save data in
					if (!file.exists()) {
						/*
						 * if file does not exist, create file and let user know
						 */
						File newFile = new File("Contacts.txt");
						newFile.createNewFile();
					}

					FileWriter fw = new FileWriter(file.getAbsoluteFile());// get
																			// file
																			// to
																			// write
																			// to
																			// .txt
					BufferedWriter bw = new BufferedWriter(fw);// write file to
																// txt

					/*
					 * separate data in .txt file for easier reading
					 */
					for (int i = 0; i < jt.getRowCount(); i++) {
						for (int j = 0; j < jt.getColumnCount(); j++) {
							bw.write(jt.getModel().getValueAt(i, j) + " ");
							bw.write("\n");
						}
						bw.write("\n\n");
					}
					bw.close();// close buffered writer
					fw.close();// close filewriter
				} catch (IOException e) {
					e.printStackTrace();// print exception
				}
			} else if (jb.equals(load)) {
				String filePath = "Contacts.txt";
				File file = new File(filePath);

				try {
					BufferedReader br = new BufferedReader(new FileReader(file));
					// get the first line
					// get the columns name from the first line
					// set columns name to the jtable model
					String firstLine = br.readLine().trim();
					String[] columnsName = firstLine.split(",");
					DefaultTableModel model = (DefaultTableModel) jt.getModel();
					model.setColumnIdentifiers(columnsName);

					// get lines from txt file
					Object[] tableLines = br.lines().toArray();

					// extratct data from lines
					// set data to jtable model
					for (int i = 0; i < tableLines.length; i++) {
						String line = tableLines[i].toString().trim();
						String[] dataRow = line.split("/");
						model.addRow(dataRow);
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}