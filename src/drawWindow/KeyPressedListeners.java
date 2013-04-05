package drawWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class KeyPressedListeners extends MainWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void fieldListeners() {
	    userInputField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent key) {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public void keyPressed(KeyEvent key) {
				// TODO Auto-generated method stub
				if(key.getKeyCode() == 10) {
					try {
						WindowEvents.insert();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
			}
		});
    }
}

