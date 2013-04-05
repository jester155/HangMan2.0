package hangMan;

import java.io.IOException;
import javax.swing.text.BadLocationException;
import drawWindow.PopUpWindow;
import drawWindow.WindowEvents;

public class MenuActions {
	
	public static void newGameAction() throws IOException {
		PublicLogic.reset();
	}
	
	public static void saveAction() {
		try {
			WindowEvents.saveFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void openAction() {
		PopUpWindow box = new PopUpWindow();
		box.setLocationRelativeTo(null);
		box.show();
	}
	
	public static void closeAction() {
		System.exit(0);
	}

}
