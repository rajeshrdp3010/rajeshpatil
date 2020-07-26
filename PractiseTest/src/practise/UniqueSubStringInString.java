package practise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueSubStringInString {

	public static void main(String[] args) {
		findUniqueSubStringInString("abdd", 2);
	}

	public static void findUniqueSubStringInString(String s, int k) {
		Set<Character> uniques = new HashSet<Character>();
		for (Character c : s.toCharArray()) {
			uniques.add(c);
		}
		Character[] array = uniques.toArray(new Character[uniques.size()]);
		List<List<Character>> permList = permute(array, k);
		List<String> finalList = new ArrayList<String>();
		for (List<Character> list : permList) {
			StringBuilder builder = new StringBuilder();
			for (Character ch : list) {
				builder.append(ch);
			}
			finalList.add(builder.toString());
		}
		String[] finalStrArray = finalList.toArray(new String[finalList.size()]);
		for (String str : finalStrArray) {
			System.out.println(str);
		}
	}

	public static List<List<Character>> permute(Character[] charArray, int k) {
		List<List<Character>> results = new ArrayList<List<Character>>();
		backtrack(results, new ArrayList<Character>(), charArray, k);
		return results;
	}

	private static void backtrack(List<List<Character>> list, List<Character> tempList, Character[] charArray, int k) {
		if (tempList.size() == k) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int i = 0; i < charArray.length; i++) {
				if (tempList.contains(charArray[i]))
					continue;
				tempList.add(charArray[i]);
				backtrack(list, tempList, charArray, k);
				tempList.remove(tempList.size() - 1);
			}
		}
	}
}
