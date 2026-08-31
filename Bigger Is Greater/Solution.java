import java.io.*;
import java.util.stream.*;

class Result {

    /*
     * Complete the 'biggerIsGreater' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING w as parameter.
     */
     
    // public static String searchBigger(String w, int index, boolean[][] checkedSwaps)
    // {
        
    // }
    public static void selectionSort(char[] chars, int fromIndex)
    {
        int n = chars.length;
        
        for (int ii = fromIndex; ii < n; ii++)
        {
            int index2 = ii;
            char c1 = chars[ii];
            
            for (int jj = ii+1; jj < n; jj++)
            {
                char c2 = chars[jj];
                
                if (c2 < c1)
                {
                    c1 = c2;
                    index2 = jj;
                }
            }
            
            chars[index2] = chars[ii];
            chars[ii] = c1;
        }
    }

    public static String biggerIsGreater(String w) 
{
    int n = w.length();
    char[] chars = w.toCharArray();

    // 1. Cari pivot dari kanan
    int pivot = n - 2;

    while (pivot >= 0 && chars[pivot] >= chars[pivot + 1])
        pivot--;

    if (pivot < 0)
        return "no answer";

    // 2. Cari karakter terkecil yang lebih besar dari chars[pivot]
    //    Karena suffix sudah non-increasing, scan dari kanan
    //    akan menemukan kandidat yang tepat.
    int swapIndex = n - 1;

    while (chars[swapIndex] <= chars[pivot])
        swapIndex--;

    // 3. Swap pivot dengan kandidat
    char temp = chars[pivot];
    chars[pivot] = chars[swapIndex];
    chars[swapIndex] = temp;

    // 4. Reverse suffix
    int left = pivot + 1;
    int right = n - 1;

    while (left < right)
    {
        temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;

        left++;
        right--;
    }

    return String.valueOf(chars);
}

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, T).forEach(TItr -> {
            try {
                String w = bufferedReader.readLine();

                String result = Result.biggerIsGreater(w);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
