package got;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
	@class Logic
	lógica general de la aplicación, manipula las tareas a resolver por parte de la interfaz
*/
public class Logic {

	private String text;
	private String[] words;
	private HashMap<String, Integer> mostAppearances;
	
	
	//Constructor
	public Logic() {
		text = "";
	}
	
	//Realiza la lectura correspondiente de un archivo de texto, con su directorio pasado por parametro
	//El archivo de texto será convertido a un arreglo de palabras determinado por separación de espacios
	//No distingue mayusculas y minusculas
	//Diferencia palabras con o sin tilde
	public void readTxtFile(File url) {
		text=""; //Reinicia el texto leido de una anterior ejecución del método
		String toRead;
		try {
			FileInputStream fis = new FileInputStream(url);
			InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			BufferedReader inputReader = new BufferedReader(isr);
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
	
	//Devuelve una lista con las palabras más usadas, límite de 5 palabras, puede devolver menos si se presenta la ocasión
	public LinkedList<String> mostUsedWords(){
		mostAppearances = new HashMap<String, Integer>(); //Creo un mapeo con las palabras como clave y su cantidad de apariciones como valor
		for(String word: words) {
			if(!mostAppearances.containsKey(word)) mostAppearances.put(word, 1);
			else mostAppearances.put(word, mostAppearances.get(word)+1);
		}
		return mostUsedWords(mostAppearances); //Devuelvo mi lista generada en un metodo privado con las 5 (como mucho) palabras más usadas
	}
	
	//Devuelve un valor flotante del porcentaje de apariciones de la palabra pasada por parametro en el archivo de texto
	public float percentageOfUsage(String word) {
		float percentage = (mostAppearances.get(word)*100)/(float)words.length;
		return percentage;
	}
	
	//Se encarga de recibir por parametro un texto y elimina todos los signos de puntuación
	private String replaceSigns(String text) {
		return text.replaceAll("\\p{Punct}", "");
	}
	
	//Metodo privado que recibe un mapeo<Palabra, Cantidad de apariciones> para ser ordenado de mayor a menor cantidad de apariciones
	//Devuelve una lista de palabras
	private LinkedList<String> mostUsedWords(HashMap<String, Integer> map){ 
		LinkedList<String> toReturn = new LinkedList<String>();
		LinkedList<Map.Entry<String, Integer>> sorted = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
		sorted.sort(Map.Entry.comparingByValue(Comparator.reverseOrder())); //Compara los valores y ordena la lista de mayor a menor valor
		if (sorted.size()>=5) { 
			for (int i=0; i<5; i++) { //Devuelve los 5 valores más altos
				toReturn.add(sorted.get(i).getKey());
			}
		} else for(int i=0;i<sorted.size();i++) { //Devuelve todos los valores que haya
			toReturn.add(sorted.get(i).getKey());
		}
		return toReturn;
	}
}
