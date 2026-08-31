import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'almostSorted' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
    
    public static void swap(List<Integer> arr, int index1, int index2)
    {
        int temp = arr.get(index1);
        
        arr.set(index1, arr.get(index2));
        arr.set(index2, temp);
    }
     
    public static int getSmallestIndex(List<Integer> arr, int fromIndex)
    {
        int result = fromIndex;
        
        int n = arr.size();
        for (int ii = fromIndex; ii < n; ii++)
            if (arr.get(ii) < arr.get(result))
                result = ii;
                
        return result;
    }
    
    public static int getReverseIndex(List<Integer> arr, int fromIndex)
    {
        int constraint = (fromIndex == 0) ? -1 : arr.get(fromIndex-1);
        int endIndex = -1;
        
        int n = arr.size();
        
        for (int ii = fromIndex+1; ii < n && endIndex == -1; ii++)
        {
            if (arr.get(ii) < constraint)
                return -1;
            else if (arr.get(ii) > arr.get(fromIndex))
                endIndex = ii;
            else if (arr.get(ii) > arr.get(ii-1) && ii-1 != fromIndex)
                return -1;
            else if (ii == n-1 && endIndex == fromIndex)
                endIndex = n;
        }
                    
        return endIndex;
    }

    public static void almostSorted(List<Integer> arr) 
    {
        // Write your code here
        int n  = arr.size();
        
        boolean sorted = true;
        boolean swap = false;
        boolean reverse = false;
        int index1 = 0;
        int index2 = 0;
        
        for (int ii = 1; ii < n && sorted; ii++)
        {
            if (ii <= 0)
                ii = 1;
                
            int here = arr.get(ii);
            if (here < arr.get(ii-1))
            {
                if (!swap)
                {
                    int smallestIndex = getSmallestIndex(arr, ii);
                
                    index1 = ii-1;
                    index2 = smallestIndex;
                    swap(arr, index1, index2);
                    
                    swap = true;
                    
                    ii = Math.min(0, ii-2);
                }
                else if (swap)
                {
                    // System.out.println("index1: "+index1+", index2: "+index2);
                    swap(arr, index2, index1);
                    
                    // System.out.println("arr: "+arr.toString());
                    
                    
                    int endIndex = getReverseIndex(arr, index1);
                    
                    // System.out.println("endIndex: "+endIndex);
                    
                    if (endIndex == -1)
                        sorted = false;
                    else
                    {
                        index2 = endIndex;
                        ii = endIndex-1;
                        
                        // System.out.println("index1: "+index1+", index2: "+index2);
                    }
                    
                    reverse = true;
                }
                else if (reverse)
                    sorted = false;
            }   
        }
        
        if (sorted)
        {
            System.out.println("yes");
            if (swap && !reverse)
                System.out.print("swap "+(index1+1)+" "+(index2+1));
            else if (reverse)
                System.out.println("reverse "+(index1+1)+" "+(index2));
        }
        else
            System.out.println("no");
        
        
        
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.almostSorted(arr);

        bufferedReader.close();
    }
}
