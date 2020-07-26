package practise;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class SecondMostRepeatedWord {
	 // Method to find the word 
	private static Set<String> getMostRepeatedWords(Map<String, Integer> sortedMap) {
	    Set<String> mostRepeatedWords = new HashSet<String>();
	    int mostrepeatedWord = Collections.max(sortedMap.values());
	    for (String key : sortedMap.keySet()) {
	    	Integer value = sortedMap.get(key);
	        if (mostrepeatedWord == value) {
	            mostRepeatedWords.add(key);
	        }
	    }

	    return mostRepeatedWords;
	}
      
    // Driver method 
    public static void main(String[] args)  
    { 
        String arr[] = { "ccc", "aaa", "ccc", 
                         "ddd", "aaa", "aaa" };
                		
        Map<String,Integer> map = new HashMap<String,Integer>(); 
        for (String str : arr) {
        	if (map.containsKey(str)) {
        		map.put(str,map.get(str) + 1);
        	} else {
        		map.put(str, 1);
        	}
        }
        Set<String> mostRepeatedWords = getMostRepeatedWords(map);
        map.keySet().removeAll(mostRepeatedWords);
        Set<String> secondMostRepeatedWords = getMostRepeatedWords(map);
        System.out.println(secondMostRepeatedWords);
        
        String paraString = "hi there i am here how are you there how are";
        Pattern space = Pattern.compile(" ");
        String[] splitArray = space.split(paraString);
        Map<String,Integer> strMap = new HashMap<String,Integer>(); 
        for (String str : splitArray) {
        	if (strMap.containsKey(str)) {
        		strMap.put(str,strMap.get(str) + 1);
        	} else {
        		strMap.put(str, 1);
        	}
        }
        
        mostRepeatedWords = getMostRepeatedWords(strMap);
        System.out.println(mostRepeatedWords);
        strMap.keySet().removeAll(mostRepeatedWords);
        secondMostRepeatedWords = getMostRepeatedWords(strMap);
        System.out.println(secondMostRepeatedWords);
    }    
}
