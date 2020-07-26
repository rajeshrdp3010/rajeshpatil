package practise;

import java.util.HashMap;
import java.util.Map;

public class MissingAndRepeating {

    public static void main(String[] args) 
    { 
  
        int[] arr = { 4, 3, 6, 2, 1, 1 }; 
        int repeating = 0,missing = 0;
        Map<Integer, Boolean> numberMap = new HashMap<>(); 
  
        int max = arr.length; 
  
        for (Integer i : arr) { 
            if (numberMap.containsKey(i)) { 
            	repeating = i;
            } else { 
            	numberMap.put(i, true); 
            } 
        } 
        for (int i = 1; i <= max; i++) { 
            if (numberMap.get(i) == null) { 
                missing = i;
            } 
        } 
        
        System.out.println("Repeating = " + repeating);
        System.out.println("Missing = " + missing);
    } 

}
