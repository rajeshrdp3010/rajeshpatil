package problems;

import java.util.ArrayList;
import java.util.List;


/*
 * A soup has 2 properties
int ingredientCount and int maxServingSize   which represents that soup uses that many ingredients and makes those many servings.

you have 5 soups with these values

soup    ingredientCount  maxServingSize
1                   2                     4
2                   3                     5
3                   4                     9
4                   5                    10
5                   7                    17


Write an algorithm which will return maximum servings you can make across all the soups given the ingredient count. 
eg. if you have 7 ingredient count, then if u use  (soup1  2 times )  + ( soup2  1 time) =  13 servings or  soup 2 + soup 3  (14 )  or if u use soup 5 then u get 17 servings so output is 17

each soup can be used more than once, but you have to use all ingredient counts 

 */

public class Soup {

    public static void main(String[] args) {
        Soup soup = new Soup();

        List<List<SoupInfo>> result = new ArrayList<>();
        soup.calculateMaxServings(7, soup.populateSoupInfo(), new ArrayList(), result);

        System.out.println(soup.getMaxServiceCount(result));
    }

    private void calculateMaxServings(int ingredientCount, List<SoupInfo> soupInfoList, List<SoupInfo> state,
            List<List<SoupInfo>> result) {

        int currentIngredientCount = getIngredientCount(state);
        if (currentIngredientCount == ingredientCount) {
            result.add(new ArrayList<>(state));
        }

        if (currentIngredientCount < ingredientCount) {
            for (SoupInfo soupInfo : soupInfoList) {
                state.add(soupInfo);
                calculateMaxServings(ingredientCount, soupInfoList, state, result);
                state.remove(state.size() - 1);
            }
        }
    }

    private int getIngredientCount(List<SoupInfo> soupInfoList) {
        int ingredientCount = 0;
        for (SoupInfo soupInfo : soupInfoList) {
            ingredientCount = ingredientCount + soupInfo.getIngredientCount();
        }
        return ingredientCount;
    }

    private int getMaxServiceCount(List<List<SoupInfo>> result) {
        int maxServiceCount = 0;
        for (List<SoupInfo> soupInfoList : result) {
            int totalServingCount = getTotalServingCount(soupInfoList);
            if (totalServingCount > maxServiceCount) {
                maxServiceCount = totalServingCount;
            }
        }
        return maxServiceCount;
    }

    private int getTotalServingCount(List<SoupInfo> soupInfoList) {
        int servingCount = 0;
        for (SoupInfo soupInfo : soupInfoList) {
            servingCount = servingCount + soupInfo.getMaxServingSize();
        }
        return servingCount;
    }

    private List<SoupInfo> populateSoupInfo() {
        List<SoupInfo> soupInfoList = new ArrayList<>();
        soupInfoList.add(new SoupInfo(1, 2, 4));
        soupInfoList.add(new SoupInfo(2, 3, 5));
        soupInfoList.add(new SoupInfo(3, 4, 9));
        soupInfoList.add(new SoupInfo(4, 5, 10));
        soupInfoList.add(new SoupInfo(5, 7, 17));
        return soupInfoList;
    }
}

