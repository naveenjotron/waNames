package waNames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.xml.bind.ParseConversionEvent;

import waNamesCode.WAnames;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;

public class waNamesGUI {
	
	WAnames objwa;
	private JFrame frame;
	private JTextField textFieldConvName;
	private JTextField textFieldPersName;
	private JTextField textFieldCount;
	private String ListName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					waNamesGUI window = new waNamesGUI();
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
	public waNamesGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel ConversationName = new JLabel("Conversation Name");
		ConversationName.setBounds(24, 52, 334, 14);
		frame.getContentPane().add(ConversationName);
		
		textFieldConvName = new JTextField();
		textFieldConvName.setToolTipText("Type the Exact group name or persons name to send message");
		textFieldConvName.setForeground(Color.BLACK);
		textFieldConvName.setBounds(24, 66, 371, 20);
		frame.getContentPane().add(textFieldConvName);
		textFieldConvName.setColumns(10);
		
		JLabel personName = new JLabel("Person's Name");
		personName.setBounds(24, 97, 371, 14);
		frame.getContentPane().add(personName);
		
		textFieldPersName = new JTextField();
		textFieldPersName.setBounds(24, 111, 371, 20);
		frame.getContentPane().add(textFieldPersName);
		textFieldPersName.setColumns(10);
		
		JLabel listName = new JLabel("List Name");
		listName.setBounds(24, 142, 371, 14);
		frame.getContentPane().add(listName);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					if(comboBox.getSelectedItem().toString().equals("AnimalNames")) {
						ListName = "AnimalNames";
						System.out.println(ListName);
					}
					else if(comboBox.getSelectedItem().toString().equals("swearWords")) {
						ListName = "swearWords";
						System.out.println(ListName);
					}
				}
			}
		});
		comboBox.setToolTipText("AnimalNames\r\nswearWords");
		comboBox.setMaximumRowCount(2);
		comboBox.setBounds(24, 155, 186, 22);
		frame.getContentPane().add(comboBox);
		
		comboBox.addItem("AnimalNames");
		comboBox.addItem("swearWords");
	
		
		JButton btnNewButtonStart = new JButton("Start");
		btnNewButtonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objwa =new WAnames();
				String convNmae = textFieldConvName.getText();
				String PersName = textFieldPersName.getText();
				
				String Count = textFieldCount.getText();
				int countNum = Integer.parseInt(Count);
				objwa.i_want_to_write_a_step_with_precondition();
				objwa.loaded_all_the_messages();
				try {
					objwa.open_specified_conversation(convNmae);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					objwa.read_person_name_and_read_list_name(PersName, ListName, countNum);
					System.out.println(ListName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			try {
				objwa.type_defined_message_and_send();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		btnNewButtonStart.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButtonStart.setBounds(60, 231, 89, 23);
		frame.getContentPane().add(btnNewButtonStart);
		
		JButton btnNewButtonExit = new JButton("Exit");
		btnNewButtonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnNewButtonExit.setBounds(306, 231, 89, 23);
		frame.getContentPane().add(btnNewButtonExit);
		
		JLabel lblNewLabel = new JLabel("WhatsApp Name Call Automation");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(70, 11, 325, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label_count = new JLabel("No:of words");
		label_count.setBounds(24, 188, 371, 14);
		frame.getContentPane().add(label_count);
		
		textFieldCount = new JTextField();
		textFieldCount.setBounds(24, 200, 96, 20);
		frame.getContentPane().add(textFieldCount);
		textFieldCount.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("by naveenjtrn");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setBounds(251, 35, 96, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		
	}
}
