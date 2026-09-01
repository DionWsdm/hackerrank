import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'countLuck' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING_ARRAY matrix
     *  2. INTEGER k
     */
    public static int[][] getStarAndMCoordinate(List<String> matrix)
    {
        int n = matrix.size();
        int m = matrix.get(0).length();
        
        int starX = 0;
        int starY = 0;
        int mX = 0;
        int mY = 0;
        
        for (int yy = 0; yy < n; yy++)
        {
            String row = matrix.get(yy);
            for (int xx = 0; xx < m; xx++)
            {
                if (row.charAt(xx) == '*')
                {
                    starX = xx;
                    starY = yy;
                }
                else if (row.charAt(xx) == 'M')
                {
                    mX = xx;
                    mY = yy;
                }
            }
        }
        return new int[][]{{starX, starY}, {mX, mY}};
    }
    
    public static int searchStar(List<String> matrix, int curX, int curY, int starX, int starY, int count)
    {
        if (curX == starX && curY == starY)
            return count;
            
        // kanan, kiri, atas, bawah
        int countPathOpens = 0;
        boolean[] pathOpens = new boolean[]{
            curY+1
        };
        
    }

    public static String countLuck(List<String> matrix, int k) 
    {
        // Write your code here
        int n = matrix.size();
        int m = matrix.get(0).length();

        int[][] starAndM = getStarAndMCoordinate(matrix);
        
        int starX = starAndM[0][0];
        int starY = starAndM[0][1];
        int mX = starAndM[1][0];
        int mY = starAndM[1][1];

        return (searchStar(matrix, mX, mY, starX, starY, 0) == k) ? "Impressed" : "Oops!";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                List<String> matrix = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                    .collect(toList());

                int k = Integer.parseInt(bufferedReader.readLine().trim());

                String result = Result.countLuck(matrix, k);

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
