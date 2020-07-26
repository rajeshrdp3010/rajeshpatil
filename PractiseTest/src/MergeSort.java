public class MergeSort {

    static long countMergeSortInversions(int[] arr)
    {
        if(arr.length==1)
            return 0;
            
        int[] left=new int[arr.length/2];
        int[] right=new int[arr.length-arr.length/2];
        for(int i=0;i<left.length;i++)
            left[i]=arr[i];
        for(int i=0;i<right.length;i++)
            right[i]=arr[left.length+i];
        
        long x=countMergeSortInversions(left);
        long y=countMergeSortInversions(right);
        long z=merge(left,right,arr);
        
        return x+y+z;
    }
    
    static long merge(int[] left,int[] right,int[] arr)
    {
        
        int l=0;int r=0;int index=0;
        long count=0;
        while(l<left.length && r<right.length)
        {
            if(left[l]>right[r])
            {
                count+=left.length-l;
                arr[index++]=right[r++];    
            }
            else
            {
                arr[index++]=left[l++];    
            }
        }
        while(l<left.length)
            arr[index++]=left[l++];
        while(r<right.length)
            arr[index++]=right[r++];
        
        return count;
    }
  
    public static void main(String[] args) 
    {
    	int[] arr = {12,9,45,78,34,1,8};
        System.out.println(countMergeSortInversions(arr));
    }
}