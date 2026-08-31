import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'stockmax' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY prices as parameter.
     */
     
    public static long[] getMaxIndex(List<Integer> prices, long fromIndex)
    {
        int n = prices.size();
        int maxIndex = (int) fromIndex;
        
        long totalBuy = 0;
        long buyToMaxIndex = 0;
        
        for (int ii = (int) fromIndex; ii < n; ii++)
        {
            if (prices.get(ii) > prices.get(maxIndex))
            {
                buyToMaxIndex = totalBuy;
                maxIndex = ii;
            }
            
            totalBuy += prices.get(ii);
        }
        
        return new long[]{buyToMaxIndex, maxIndex};
    }

    public static long stockmax(List<Integer> prices) 
    {
        // Write your code here
        long result = 0;
        
        int n = prices.size();
        
        for (long fromIndex = 0; fromIndex < n; fromIndex++)
        {
            long[] infos = getMaxIndex(prices, fromIndex);
            
            long buyToMaxIndex = infos[0];
            int maxIndex = (int) infos[1];
            
            if (maxIndex != fromIndex)
            {
                result += (maxIndex - fromIndex)*prices.get(maxIndex);
                result -= buyToMaxIndex;
                
                fromIndex = maxIndex;
            }
        }
        
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> prices = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                long result = Result.stockmax(prices);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
 
