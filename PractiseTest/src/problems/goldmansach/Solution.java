package problems.goldmansach;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Instructions to candidate.
 *  1) Run this code in the REPL to observe its behaviour. The
 *     execution entry point is main().
 *  2) Consider adding some additional tests in doTestsPass().
 *  3) Implement findFirst() correctly.
 *  4) If time permits, try to improve the efficiency of your implementation
 */

public class Solution {

  /**
  * char findFirst(String input)
  * Finds the first character that does not repeat anywhere in the input string
  * If all characters are repeated, return 0
  * Given "apple", the answer is "a"
  * Given "racecars", the answer is "e"
  * Given "ababdc", the answer is "d"
  **/
  public static char findFirst(String input)
  {
		Character ch = null;
        // LinkedHashMap maintains insertion order
		Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
		for(Character c : input.toCharArray()) {
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

  /**
   * boolean doTestsPass()
   * Returns true if all tests pass. Otherwise returns false.
   */
  public static boolean doTestsPass()
  {
    // todo: implement more tests, please
    // feel free to make testing more elegant
    String[] inputs = {"apple","racecars", "ababdc"};
    char[] outputs = {'a', 'e', 'd' };

    boolean result = true;
    for(int i = 0; i < inputs.length; i++ )
    {
      result = result && findFirst(inputs[i]) == outputs[i];
      if(!result)
        System.out.println("Test failed for: " + inputs[i]);
      else
        System.out.println("Test passed for: " + inputs[i]);
    }
    return(result);
  }

  public static void main(String args[])
  {
    doTestsPass();
  }
}
