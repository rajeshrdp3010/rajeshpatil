package practise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Find student with highest average grade.
 * @author rpatil
 *
 */
public class AverageGrade {

	public static void main(String[] args) {
		String[][] studentGradeArray = { { "jerry", "65" },
	               { "bob", "91" },
	               { "jerry", "23" },
	               { "Eric", "83" } };
		System.out.println("Student with highest average scrore : " + calculateAverageGrade(studentGradeArray));
	}
	  /*
	  **  Find the best average grade.
	  */
	private static String calculateAverageGrade(String[][] studentGradeArray) {
		String student = "";
		Map<String,List<Integer>> studentMap = new HashMap<String,List<Integer>>();
		for (int i = 0 ; i < studentGradeArray.length ; i++) {
			String studentName = studentGradeArray[i][0];
			String score = studentGradeArray[i][1];
			if (studentMap.containsKey(studentName)) {
				List<Integer> scoreList = studentMap.get(studentName);
				scoreList.add(Integer.valueOf(score));
			} else {
				List<Integer> scoreList = new ArrayList<Integer>();
				scoreList.add(Integer.valueOf(score));
				studentMap.put(studentName,scoreList);
			}
		}
		Integer maxAverage = 0;
		for (String stud : studentMap.keySet()) {
			List<Integer> scoreList = studentMap.get(stud);
			Integer average = 0;
			for (Integer score : scoreList) {
				average += score;
			}
			average = average / scoreList.size();
			if (average > maxAverage) {
				student = stud;
				maxAverage = average;
			}
		}
		return student;
	}

}
