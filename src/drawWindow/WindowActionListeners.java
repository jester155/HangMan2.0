package drawWindow;

import hangMan.MenuActions;
import hangMan.PublicLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WindowActionListeners extends KeyPressedListeners {

	private static final long serialVersionUID = 1L;
	public void menuActionListeners() {
		//.FILE MENU ACTIONS
		newAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					PublicLogic.reset();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		openAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MenuActions.openAction();
			}
		});
		saveAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MenuActions.saveAction();
				
			}
		});
		exitAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuActions.closeAction();
			}
		});
	}
}	

