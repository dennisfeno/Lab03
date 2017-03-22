package it.polito.tdp.spellchecker.controller.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {

	private List<String> dictionary ;
	
	public Dictionary() {
		super();
		this.dictionary = new LinkedList<String>();
	}


	/**
	 * Metodo che permette di caricare in memoria il dizionario della lingua desiderata. A questo
	 * proposito, utilizzare i file Italian.txt e English.txt nella cartella rsc. I file contentengono una
	 * parola per riga. Salvare le parole del dizionario in una struttura dati appropriata. Di seguito
	 * viene riportato un esempio della sequenza di operazioni necessarie per leggere le parole dal
	 * file:
	 */
	
	public void loadDictionary(String language){


		try {
			FileReader fr = new
			FileReader("rsc/"+language+".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				// Aggiungere parola alla struttura dati
		    	//word = word.replaceAll("[\\p{Punct}]","");
				word = word.toLowerCase();
				this.dictionary.add(word);
				//System.out.println(word);
			}
			br.close();
		} catch (IOException e){
		System.out.println("Errore nella lettura del file");
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){

		boolean support = false ;
		
		List<RichWord> a = new LinkedList<RichWord>();
		
		for(String str : inputTextList) { // per ogni elemento in input
			
			/*
			support = false; 
			
			for(String j: this.dictionary){
				
				//System.out.println(this.dictionary.size());
				
				if(str.equals(j)){
					
					support = true ;
					
					//System.out.println(str + " " + j );
				}
				
			}
			
			a.add(new RichWord(str,support));
			*/
			
			//ricerca binaria
			
			int i,j,now;
			i=0;
			j=this.dictionary.size()-1;
			String k = null;
			support = false;
			
			while (i<=j && support==false){
				
				now = (int)((j-i)/2+i);
				
				k = this.dictionary.get(now);
				System.out.println(str + "," + k + "," + i + "," + now + "," + j);

				if (str.compareTo(k)==0){
					support=true;
				}
				else if (str.compareTo(k)<0){ //str viene prima di k
					j=now-1;
				}
				else {
					i=now+1;
				}
				
			}
			
			a.add(new RichWord(str,support));

		}
		
		return a;
		
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dictionary == null) ? 0 : dictionary.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dictionary other = (Dictionary) obj;
		if (dictionary == null) {
			if (other.dictionary != null)
				return false;
		} else if (!dictionary.equals(other.dictionary))
			return false;
		return true;
	}
	
}
