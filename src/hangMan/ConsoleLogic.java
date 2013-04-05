package hangMan;

import java.util.*;
import java.io.*;

public class ConsoleLogic {
	
	public void start() throws IOException {
		setWordBank();
		player();
	}
	
	private static void setWordBank() throws IOException {
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
        	//System.out.println("Error: The file " + Words.toPath() + " does not exits\n Press the 'Enter' key to exit.");
        	br.read();
        	System.exit(0);
        }
    }
	
	private static void player() throws IOException {
		String word = getWord(); 
		String choice;
		String[] characters = new String[word.length()];
		int wrongCount = 0;
		int tries = 6; 
		
		System.out.println("The word contains " + word.length() + " letters");
		
		
		while(arrayNullCheck(characters) != true && tries != 0) {
			if(tries > 1)
				System.out.println("You have " + tries + " more chances!\n");
			else
				System.out.println("You have " + tries + " more chance!\n");
			
			if(usedLetters != null) {
				System.out.print("Used letters:");
				for(String letters : usedLetters)
					System.out.print(" '" + letters + "' ");
			}
			
			choice = getUserChoice().toLowerCase();
			
			if(word.contains(choice)) {
				
				int j = word.indexOf(choice); //.Is the same as setting to 0
				for(int f = 0; f < countCharacters(word , choice); f++) {
				     j = word.indexOf(choice, j);
				     characters[j] = choice;
				     j++;
				}
						
			}
			else if(usedLetters.contains(choice))
				System.out.println("You already guessed " + choice);
			else {
				System.out.println(choice + ": is not part of the word.");
				hangedMan.append(man[wrongCount]);
				wrongCount++;
				tries--;
				usedLetters.add(choice);
			}
				
			
			System.out.println(hangedMan.toString());
			StringBuilder letterHolder = new StringBuilder();
			
			letterHolder.append("\n");
			for (int i = 0; i < characters.length ; i++) {
	            if (characters[i] != null) 
	            	letterHolder.append(" " + characters[i] + " ");
	            else {
	            	characters[i] = " _ ";
	            	letterHolder.append(characters[i]);
	            	characters[i] = null;
	            }
	        }
			letterHolder.append("\n");
			
			
			System.out.println(letterHolder.toString());
			
			Collections.sort(usedLetters);
			
		}	
		getResult(characters, word);
	}
	
	private static String getUserChoice() {
		String c;
		System.out.print("\nEnter in a letter: ");
			c = scan.next();
			
		if(c.length() == 1)
			return c;
		else {
			System.out.println("Please enter in only one letter");	
			return getUserChoice();
		}
			
	}
	
	private static void getResult(String[] characters , String word) {
		StringBuilder answer = new StringBuilder();
		
		for(short i = 0 ; i < (short)characters.length; i++)
			answer.append(characters[i]);
			
		if(checkWin(word, answer.toString()) == true)
			System.out.println("You won the answer was: " + word);
		else
			System.out.println("you lost");
	}
	
	private static int countCharacters(String word , String choice) {
		int count = 0;
		for(int i =0; i < word.length(); i++)
		    if(word.charAt(i) == parseChar(choice)) {
		        count++;
		    }
		return count;
	}
	
	private static char parseChar(String choice) {
		
		return choice.charAt(0);
	}
	
	private static boolean arrayNullCheck(String[] check) {
		
		for(int i = 0; i < check.length ; i++) {
			if(check[i] == null)
				return false;
		}
		return true;
	}
	
	private static boolean checkWin(String word , String answer) {
		return answer.equals(word);
	}
	
	private static String getWord() throws IOException {
		String[] definitions = new String[words.size()];
		String word = words.get(rand.nextInt(words.size()));
		
        
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
        
        System.out.println("\n" + definitions[words.indexOf(word)] + "\n");
        return word;
        }
        else {
        	//System.out.println("Error: The file " + Definitions.toPath() + " does not exits\n Press the 'Enter' key to exit.");
        	br.read();
        	System.exit(0);
        	return null;
        }
	}
	
	//.objects that are used in the class.//
	private static Scanner scan = new Scanner(System.in);
	private static Random rand = new Random();
	//.Lists that are needed for keeping track of words and letters.//
	private static List<String> usedLetters = new ArrayList<>();
	private static List<String> words = new ArrayList<>();
	//.Needed for file reading.//
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedReader in = null;
    private static FileReader fr = null;
    //.Files that are being worked with.//
    private static File Words = new File("bin\\Word_Bank\\Words.txt");
    private static File Definitions = new File("bin\\Word_Bank\\Definitions.txt"); 
    private static StringBuilder hangedMan = new StringBuilder();
    private static String[] man = new String[]{"  O" , "\n /" , "|" , "\\" , "\n /" , " \\"};
}
