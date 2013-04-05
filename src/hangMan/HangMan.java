package hangMan;

import java.io.IOException;

public class HangMan {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		PublicLogic.setWordBank();
		PublicLogic.setDefinitionBank();
		PublicLogic.window.fieldListeners();
		PublicLogic.window.menuActionListeners();
		PublicLogic.window.setLocationRelativeTo(null);
		PublicLogic.window.show();
		PublicLogic.start();
	}
}
