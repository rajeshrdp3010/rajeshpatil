package problems.goldmansach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
**  Instructions:
**
**  Given a list of student test scores, find the best average grade.
**  Each student may have more than one test score in the list.
**
**  Complete the bestAverageGrade function in the editor below.
**  It has one parameter, scores, which is an array of student test scores.
**  Each element in the array is a two-element array of the form [student name, test score]
**  e.g. [ "Bobby", "87" ].
**  Test scores may be positive or negative integers.
**
**  If you end up with an average grade that is not an integer, you should
**  use a floor function to return the largest integer less than or equal to the average.
**  Return 0 for an empty input.
**
**  Example:
**
**  Input:
**  [ [ "Bobby", "87" ],
**    [ "Charles", "100" ],
**    [ "Eric", "64" ],
**    [ "Charles", "22" ] ].
**
**  Expected output: 87
**  Explanation: The average scores are 87, 61, and 64 for Bobby, Charles, and Eric,
**  respectively. 87 is the highest.
*/

public class AverageGradeTest {
	  /*
	  **  Find the best average grade.
	  */
	  public static Integer bestAverageGrade(String[][] scores)
	  {

	        Map<String,List<Integer>> map = new HashMap<String,List<Integer>>();
	        for(int i = 0;i < scores.length ; i ++) {
	        	String studenName = scores[i][0];
	        	Integer score = Integer.valueOf(scores[i][1]);
	        	if (map.containsKey(studenName)) {
	        		map.get(studenName).add(score);
	        	} else {
	        		List<Integer> scoreList = new ArrayList<Integer>();
	        		scoreList.add(score);
	        		map.put(studenName, scoreList);
	        	}
	        }
	        List<Integer> gradeList = map.keySet().stream().map(studentName -> {
	        	List<Integer> scoreList = map.get(studentName);
	        	Double avg = scoreList.stream().mapToInt(i -> i).average().getAsDouble();
	        	return Integer.valueOf(avg.intValue());
	        }).collect(Collectors.toList());
	        

	        Collections.sort(gradeList);

	        return gradeList.get(gradeList.size()-1);
	  }

	  /*
	  **  Returns true if the tests pass. Otherwise, returns false;
	  */
	  public static boolean doTestsPass()
	  {
	    // TODO: implement more test cases
	    String[][] tc1 = { { "Bobby", "87" },
	               { "Charles", "100" },
	               { "Eric", "64" },
	               { "Charles", "22" } };

	    return bestAverageGrade(tc1) == 87;
	  }

	  /*
	  **  Execution entry point.
	  */
	  public static void main(String[] args)
	  {
	    // Run the tests
	    if(doTestsPass())
	    {
	      System.out.println("All tests pass");
	    }
	    else
	    {
	      System.out.println("Tests fail.");
	    }
	  }
}
