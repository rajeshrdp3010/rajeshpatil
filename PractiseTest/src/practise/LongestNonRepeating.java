package practise;


import java.util.HashSet;
/**
 * Program to find the length of the longest substring without repeating characters for a given String
 */
public class LongestNonRepeating {
    public static String longestNonRepeating(String inputString){

        HashSet<Character> set = new HashSet<Character>();

        String longestAllTime = "";
        String longestTillNow = "";

        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);

            if (set.contains(c)) {
                longestTillNow = "";
                set.clear();
            }
            longestTillNow += c;
            set.add(c);
            if (longestTillNow.length() > longestAllTime.length()) {
            	longestAllTime = longestTillNow;
            }
        }

        return longestAllTime;
    }

    public static void main(String[] args) {
        String input = "abeefghiabee";
        String longestNonRepeating = longestNonRepeating(input);
        System.out.println("Length of the longest substring without repeating characters is : " + longestNonRepeating.length());
    }
}
