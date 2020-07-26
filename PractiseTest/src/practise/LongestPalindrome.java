package practise;

public class LongestPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestPalindrome pal = new LongestPalindrome();
		System.out.println(pal.longestPalindrome("babadgjddvbbbbaaa"));
	}
	public String longestPalindrome(String s) {
        int curLen = 0;
        int start = -1;
        char[] array = s.toCharArray();
        for(int i = 0; i < array.length; i++) {
            if(isPalindrome(array, i - curLen - 1, i)) {
                start = i - curLen - 1;
                curLen += 2;
                System.out.println("odd loop :" + new String(array, start, curLen));
            } else if (isPalindrome(array, i - curLen, i)) {
                start = i - curLen;
                curLen += 1;
                System.out.println("even loop :" +new String(array, start, curLen));
            }
        }
        return new String(array, start, curLen);
    }
    private boolean isPalindrome(char[] array, int low, int high) {
        if(low < 0) {
            return false;
        }
        while(low < high) {
            if(array[low++] != array[high--]) {
                return false;
            }
        }
        return true;
    }
    public String longestPalindrome1(String s) {
        int curLeng = 0;
        int start = -1;
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length ; i++) {
        	if (isPalindrome(array, i - curLeng - 1, i)) {
        		start = i - curLeng - 1;
        		curLeng += 2;
        	} else if (isPalindrome(array, i - curLeng, i)) {
        		start = i - curLeng;
        		curLeng += 1;
        	}
        }
        return new String(array,start,curLeng);
    }
}
