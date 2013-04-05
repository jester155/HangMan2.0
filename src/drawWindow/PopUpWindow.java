package drawWindow;

import hangMan.PublicLogic;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class PopUpWindow extends JFrame implements ActionListener {
	
	public PopUpWindow() {
		
		JLabel messege = new JLabel("You already guessed that.");
		infoButton.addActionListener(this);
		infoButton.setFont(new Font("Ariel", Font.PLAIN, 14));
		messege.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		JPanel mainPanel = new JPanel();
		JPanel optionsPanel = new JPanel();
		
		mainPanel.add(Box.createVerticalStrut(50));
		mainPanel.add(messege);
		optionsPanel.add(infoButton);
	
		getContentPane().add(mainPanel, "Center");
		getContentPane().add(optionsPanel, "South");
		setLocationRelativeTo(null);
		setSize(230,150);
	
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		infoButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent src) {
				// TODO Auto-generated method stub
				if(src.getKeyCode() == 10 || src.getExtendedKeyCode() == 32)
					dispose();
				
			}
		});
	}
	
	public PopUpWindow(boolean win) {
		if(win == true) {
			playAgainButton.addActionListener(this);
			closeButton.addActionListener(this);
			playAgainButton.setFont(new Font("Ariel", Font.PLAIN, 14));
			closeButton.addActionListener(this);
			closeButton.setFont(new Font("Ariel", Font.PLAIN, 14));
			
			JLabel messege = new JLabel("You Won!");
			messege.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
			JPanel mainPanel = new JPanel();
			JPanel optionsPanel = new JPanel();
		
			mainPanel.add(Box.createVerticalStrut(50));
			mainPanel.add(messege);
			optionsPanel.add(playAgainButton);
			optionsPanel.add(closeButton);
		
			getContentPane().add(mainPanel, "Center");
			getContentPane().add(optionsPanel, "South");
			setSize(230,150);
		
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		} else if(win == false) {
			playAgainButton.addActionListener(this);
			playAgainButton.setFont(new Font("Ariel", Font.PLAIN, 14));
			closeButton.addActionListener(this);
			closeButton.setFont(new Font("Ariel", Font.PLAIN, 14));
			
			JLabel messege = new JLabel("You lost...");
			messege.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
			JPanel mainPanel = new JPanel();
			JPanel optionsPanel = new JPanel();
		
			mainPanel.add(Box.createVerticalStrut(50));
			mainPanel.add(messege);
			optionsPanel.add(playAgainButton);
			optionsPanel.add(closeButton);
		
			getContentPane().add(mainPanel, "Center");
			getContentPane().add(optionsPanel, "South");
		
			setSize(230,150);
		
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}
		closeButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == 10 || e.getKeyCode() == 32)
					PublicLogic.window.userInputField.setEditable(false);
					dispose();
				
			}
		});
		playAgainButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent evt) {
				// TODO Auto-generated method stub
				int code = evt.getKeyCode();
				if((code == 10) || (code == 32)) {
					try {
						PublicLogic.reset();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dispose();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent EVENT) {
		// TODO Auto-generated method stub
		Object src = EVENT.getSource();
		if (src.equals(playAgainButton)) {
			try {
				PublicLogic.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispose();
		} else if(src.equals(closeButton)) { 
			PublicLogic.window.userInputField.setEditable(false);
			dispose();
		} else if(src.equals(infoButton))
			dispose();
	}
	
	private JButton playAgainButton = new JButton("Play Again?");
	private JButton closeButton = new JButton("Close");
	private JButton infoButton = new JButton("Close");
}
