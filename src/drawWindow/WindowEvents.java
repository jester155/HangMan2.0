package drawWindow;

import hangMan.PublicLogic;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;


public class WindowEvents {
	
	public static void insert() throws IOException {
		PublicLogic.mainLogic();
	}
	
	public static void addText() throws IOException {
		if(file.exists()) {
        	try {
            	fr = new FileReader(file.getPath());
            	in = new BufferedReader(fr);
            	String str;
            	
            	while ((str = in.readLine()) != null)
                	fileInput.add(str);

        	} catch (Exception e) {
            	e.printStackTrace();
        	} finally {
            	in.close();
            	fr.close();
        	}
        	StringBuilder input = new StringBuilder();
        	for(String item : fileInput)
        		input
    			.append(item + "\n");
		}
		
		
	}
	public static void saveFile() throws IOException, BadLocationException {
		FileDialog fileSaveDialog = new FileDialog(new Frame(), "Save", FileDialog.SAVE);
        fileSaveDialog.setFilenameFilter(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        fileSaveDialog.setFile("Untitled.txt");
        fileSaveDialog.setVisible(true);
        saveFile = new File(fileSaveDialog.getDirectory() + fileSaveDialog.getFile());
        saveText = new FileWriter(saveFile); 
        buffSaveText = new BufferedWriter(saveText);
        
        StringBuilder save = new StringBuilder();
        
        saveText.write(save.toString());
        
        saveText.close();
        buffSaveText.close();
        
        System.out.println("File: " + saveFile.toString());
		
	}
	
	public static void open() {
		fileInput.removeAll(fileInput);
		
		FileDialog fileOpenDialog = new FileDialog(new Frame(), "Open", FileDialog.LOAD);
		fileOpenDialog.getDirectory();
        fileOpenDialog.setVisible(true);
        file = new File(fileOpenDialog.getDirectory(), fileOpenDialog.getFile());
	}
	
	public static File file = new File("null");
	private static File saveFile = new File("null");
	private static FileReader fr = null;
	private static BufferedReader in = null;
	private static FileWriter saveText = null;
	private static BufferedWriter buffSaveText;
	
	private static List<String> fileInput = new ArrayList<String>();

}
