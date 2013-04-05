package hangMan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import drawWindow.PopUpWindow;
import drawWindow.WindowActionListeners;

public class PublicLogic { 
	
	@SuppressWarnings("deprecation")
	public static void mainLogic() {
			
		choice = window.userInputField.getText().toLowerCase();
		
		if(word.contains(choice.toLowerCase()) && 
				(usedLetters.contains(choice) != true)) {
			int j = word.indexOf(choice); //.Gets the first index of choice in the word
			for(int i = 0 ; i < countCharacters(word , choice) ; i++) {
			     j = word.indexOf(choice, j);
			     characters[j] = choice;
			     j++;
			}
			usedLetters.add(choice.toLowerCase());
		} else if(usedLetters.contains(choice.toLowerCase())) {
			PopUpWindow alreadyUsed = new PopUpWindow();
			alreadyUsed.show();
		} else if((usedLetters.contains(choice) != true)) {
			hangedMan.append(man[wrongCount]);
			wrongCount++;
			tries--;
			usedLetters.add(choice);
		}
				
		window.hangPane.setText(hangedMan.toString());
		
		StringBuilder letterHolder = new StringBuilder();
		for (int i = 0 ; i < characters.length ; i++) {
			if (characters[i] != null) 
				letterHolder.append(" " + characters[i] + " ");
			else {
				characters[i] = " _ ";
				letterHolder.append(characters[i]);
				characters[i] = null;
			}
		}
		
		window.tryField.setText(letterHolder.toString());
		window.userInputField.setText("");
		Collections.sort(usedLetters);
		StringBuilder usedLetterBuilder = new StringBuilder();
		
		if(usedLetters != null) {
			for(String letter : usedLetters)
				usedLetterBuilder.append(" " + letter + " ");
			
			window.usedLetters.setText(usedLetterBuilder.toString().toUpperCase());
		}
		window.chances.setText("Chances left: " + tries);
		winLogic(characters);
	}
	
	@SuppressWarnings("deprecation")
	private static void windowChooser(boolean win) {
		PopUpWindow popUpWindow = new PopUpWindow(win);
		popUpWindow.setLocationRelativeTo(null);
		popUpWindow.show();
	}
	
	private static void winLogic(String[] characters) {
		if(arrayNullCheck(characters) == true)
			windowChooser(true);
		else if(tries == 0)
			windowChooser(false);
	}
	
	private static boolean arrayNullCheck(String[] characters) {
		
		for(int i = 0; i < characters.length ; i++) {
			if(characters[i] == null)
				return false;
		}
		return true;
	}
	
	public static String getWord() throws IOException {
		
		String word = words.get(rand.nextInt(words.size()));
        characters = new String[word.length()];
        return word;   
	}
	
	public static void getDefinition() {
		window.definitionTextArea.setText(definitions[words.indexOf(word)]);
	}
	
	public static void setWordBank() throws IOException {
        if(Words.exists()) {
        	try {
            	fr = new FileReader(Words.getPath());
            	in = new BufferedReader(fr);
            	String str;
            	
            	while ((str = in.readLine()) != null)
                	words.add(str);

        	} catch (Exception e) {
            	e.printStackTrace();
        	} finally {
            	in.close();
            	fr.close();
        	} 
        }
        else {
        	System.out.println("Error: The file " + Words.toPath() + " does not exits\n Press the 'Enter' key to exit.");
        	br.read();
        	System.exit(0);
        }
    }
	
	public static void setDefinitionBank() throws IOException {
		definitions = new String[words.size()];
        if(Definitions.exists()){
        	int i = 0;
        	try { 
        		fr = new FileReader(Definitions.getPath());
        		in = new BufferedReader(fr);
            	String str;
        		while ((str = in.readLine()) != null) {
            		definitions[i] = str;
            		i++;
            	}
        	} catch (Exception e) {
            	e.printStackTrace();
        	} finally {
        		in.close();
            	fr.close();
        	}
        }
        else {
        	System.out.println("Error: The file " + Definitions.toPath() + " does not exits\n Press the 'Enter' key to exit.");
        	br.read();
        	System.exit(0);
        }
	}
	
	public static int countCharacters(String word , String choice) {
		
		int count = 0;
		for(int i = 0 ; i < word.length() ; i++)
		    if(word.charAt(i) == parseChar(choice)) {
		        count++;
		    }
		return count;
	}
	
	private static char parseChar(String choice) {
		
		return choice.charAt(0);
	}
	
	public static void start() throws IOException {
		StringBuilder holder = new StringBuilder();
		word = getWord();
		getDefinition();
		for(int i = 0 ; i < word.length() ; i++)
			holder.append(" _ ");
		
		window.tryField.setText(holder.toString());
	}
	
	public static void reset() throws IOException {
		wrongCount = 0;
		usedLetters.removeAll(usedLetters);
		tries = 6;
		word = getWord();
		hangedMan = new StringBuilder();
		characters = new String[word.length()];
		window.usedLetters.setText("");
		window.hangPane.setText("");
		window.tryField.setText("");
		PublicLogic.window.userInputField.setEditable(true);
		start();
	}
	
	public static WindowActionListeners window = new WindowActionListeners();
	
	public static String choice;
	
	public static int wrongCount = 0;
	public static List<String> usedLetters = new ArrayList<>();
	public static int tries = 6;
	
	public static String word;
	public static String def;
	private static Random rand = new Random();
	
	private static List<String> words = new ArrayList<>();
	public static String[] definitions;
	//.Needed for file reading.//
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedReader in = null;
    private static FileReader fr = null;
    private static File Words = new File("bin\\Word_Bank\\Words.txt");
    private static File Definitions = new File("bin\\Word_Bank\\Definitions.txt");
	
    public static String[] characters;
    public static StringBuilder hangedMan = new StringBuilder();
    public static String[] man = new String[]{"   O" , "\n /" , " | " , "\\" , "\n  /" , " \\"};
}
