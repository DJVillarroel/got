package got;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Logic {

	private String text;
	private String[] words;
	private HashMap<String, Integer> mostAppearances;
	
	public Logic() {
		text = "";
	}
	
	public void readTxtFile(File url) {
		text="";
		String toRead;
		try {
			BufferedReader inputReader = new BufferedReader(new FileReader(url));
			while ((toRead = inputReader.readLine()) != null) {
				text += replaceSigns(toRead);
			}
			inputReader.close();
			text = text.toLowerCase();
			words = text.split(" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public LinkedList<String> mostUsedWords(){
		mostAppearances = new HashMap<String, Integer>(); //Creo un mappeo con las palabras como clave y su cantidad de apariciones como valor
		for(String word: words) {
			if(!mostAppearances.containsKey(word)) mostAppearances.put(word, 1);
			else mostAppearances.put(word, mostAppearances.get(word)+1);
		}
		return mostUsedWords(mostAppearances); //Devuelvo mi lista generada en un metodo privado con las 5 palabras más usadas
	}
	
	public float percentageOfUsage(String word) {
		float percentage = (mostAppearances.get(word)*100)/(float)words.length;
		return percentage;
	}
	
	
	private String replaceSigns(String text) {
		return text.replaceAll("[^\\w\\s]", "");
	}
	
	private LinkedList<String> mostUsedWords(HashMap<String, Integer> map){ 
		LinkedList<String> toReturn = new LinkedList<String>();
		LinkedList<Map.Entry<String, Integer>> sorted = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
		sorted.sort(Map.Entry.comparingByValue(Comparator.reverseOrder())); //Compara los valores y ordena la lista de mayor a menor valor
		if (sorted.size()>=5) {
			for (int i=0; i<5; i++) { //Devuelve los 5 valores más altos
				toReturn.add(sorted.get(i).getKey());
			}
		} else for(int i=0;i<sorted.size();i++) {
			toReturn.add(sorted.get(i).getKey());
		}
		return toReturn;
	}
}
