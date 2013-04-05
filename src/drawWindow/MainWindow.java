package drawWindow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MainWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MainWindow() {
		menuBar();
		
		panels();	
	}
	
	public void panels() {
		insertButton.setMnemonic(KeyEvent.VK_ENTER);

		used.setFont(new Font("Serif", Font.PLAIN, 18));
		definitionLable.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		chances.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		
		definitionTextArea.setEditable(false);
		definitionTextArea.setSize(250, 200);
		definitionTextArea.setLineWrap(true);
		definitionTextArea.setWrapStyleWord(true);
		definitionTextArea.setMargin(new Insets(5, 15, 5, 5));
		definitionTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		
		hangPane.setEditable(false);
		hangPane.setMargin(new Insets(32, 50, 0, 0));
		hangPane.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		
		tryField.setColumns(20);
		tryField.setEditable(false);
		tryField.setHorizontalAlignment(JTextField.CENTER);
		tryField.setMargin(new Insets(10, 0, 10, 0));
		tryField.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		
		usedLetters.setColumns(23);
		usedLetters.setEditable(false);
		usedLetters.setMargin(new Insets(5, 15, 5, 5));
		usedLetters.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		
		userInputField.setDocument(new JTextFieldLimit(1));
		userInputField.setColumns(2);
		userInputField.setHorizontalAlignment(JTextField.CENTER);
		userInputField.setBorder(BorderFactory.createLineBorder(Color.black));
		userInputField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		
		JPanel top = new JPanel();
		JPanel uTop = new JPanel();
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JPanel bottom = new JPanel();
		
		top.add(tryField);
		
		uTop.add(used);
		uTop.add(usedLetters);
		uTop.add(Box.createVerticalStrut(40));
		uTop.add(definitionLable);
		uTop.add(Box.createHorizontalStrut(20));
		uTop.add(definitionTextArea);
		uTop.add(Box.createHorizontalStrut(20));
		uTop.add(Box.createVerticalStrut(50));
		uTop.add(userInputField);
		uTop.add(insertButton);
		insertButton.addActionListener(this);
		
		left.add(Box.createHorizontalStrut(2));
		left.add(hangPane);
		left.add(Box.createHorizontalStrut(3));
		
		right.add(Box.createHorizontalStrut(5));
		
		bottom.add(chances);
		
	    getContentPane().add(top, "North");
	    getContentPane().add(uTop, "Center");
	    getContentPane().add(left, "West");
	    getContentPane().add(right, "East");
	    getContentPane().add(bottom, "South");
	    
	    setTitle("Hang Man");

	    setSize(450, 480);
	    addWindowListener(new WindowAdapter() {
	    	public void windowClosing(WindowEvent e) {
	    		System.exit(0);
	    	}
	    });
	}

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if (source == insertButton &&
				userInputField.getText().toString().length() != 0) {
			try {
				WindowEvents.insert();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void menuBar() {
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		
		menuBar.add(fileMenu);
		
		//.File Menu
		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.add(saveAction);
		fileMenu.addSeparator();
		fileMenu.add(exitAction);
	}

	@SuppressWarnings("serial")
	public class JTextFieldLimit extends PlainDocument {
		private int limit;

		  JTextFieldLimit(int limit) {
		   super();
		   this.limit = limit;
		   }

		  public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
		    if (str == null) return;

		    if ((getLength() + str.length()) <= limit) {
		      super.insertString(offset, str, attr);
		    }
		  }
	}
	
	public JButton insertButton = new JButton("Insert");
	
	private JLabel used = new JLabel("Used Letters: ");
	public JLabel chances = new JLabel("Chances left: 6");
	public JLabel definitionLable = new JLabel("Hint:");
	
	public JTextArea usedLetters = new JTextArea();
	public JTextField tryField = new JTextField();
	public JTextField userInputField = new JTextField();
	public JTextArea hangPane = new JTextArea(10,7);
	public JTextArea definitionTextArea = new JTextArea();
	public JScrollPane definitionScrollPane = new JScrollPane(definitionTextArea);
	
	public JMenuBar menuBar = new JMenuBar();
	
	protected JMenuItem newAction = new JMenuItem("New Game");
	public JMenuItem openAction = new JMenuItem("Open");
	public JMenuItem saveAction = new JMenuItem("Save");
	public JMenuItem exitAction = new JMenuItem("Exit");
	
	public JMenuItem cutAction = new JMenuItem("Cut");
	public JMenuItem clearAction = new JMenuItem("Clear");
	public JMenuItem copyAction = new JMenuItem("Copy");
	public JMenuItem pasteAction = new JMenuItem("Paste");
	
}
