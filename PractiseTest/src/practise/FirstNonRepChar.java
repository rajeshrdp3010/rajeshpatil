package practise;

import java.util.LinkedHashMap;
import java.util.Map; 


class FirstNonRepChar 
{ 
	// Driver method 
	public static void main (String[] args) 
	{ 
		String str = "geeksforgeeks"; 
				
		System.out.println("First non-repeating character is " + getFirstNonRepChar(str)); 
	} 
	
	public static Character getFirstNonRepChar(String args) {
		Character ch = null;
        // LinkedHashMap maintains insertion order
		Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
		for(Character c : args.toCharArray()) {
			if(map.containsKey(c)) {
				map.put(c, map.get(c)+1);
			} else {
				map.put(c, 1);
			}
		}
		
		for(Character c : map.keySet())
		{
			if(map.get(c) == 1) {
				ch = c;
				break;
			}
		}
		return ch;
	}
} 
