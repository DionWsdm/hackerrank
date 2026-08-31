import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'organizingContainers' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts 2D_INTEGER_ARRAY container as parameter.
     */
     
    public static int[][] countEveryType(List<List<Integer>> container)
    {
        int n = container.size();
        
        // [[Total bola tiap kontainer], [Total bola setiap tipe di seluruh kontainer]]
        int[][] result = new int[2][n];
        
        for (int cc = 0; cc < n; cc++)
        {
            List<Integer> cont = container.get(cc);
            for (int tt = 0; tt < n; tt++)
            {
                int typeHere = cont.get(tt);
                result[0][cc] += typeHere;
                result[1][tt] += typeHere;
            }
        }
                
        return result;
    }

    public static String organizingContainers(List<List<Integer>> container) 
    {
        int n = container.size();
        int[][] globalCount = countEveryType(container);
        int[] contCounts = globalCount[0];
        int[] typeCounts = globalCount[1];
        boolean[] usedContainer = new boolean[n];
        int countFound = 0;
        
        for (int type = 0; type < n; type++)
        {
            int typeCount = typeCounts[type];
                     
            boolean contFound = false;
            if (typeCount == 0)
            {
                countFound++;
                contFound = true;
            }
            
            for (int cc = 0; cc < n && !contFound; cc++)
            {    
                int contCount = contCounts[cc];
                if (!usedContainer[cc])
                {
                    List<Integer> cont = container.get(cc);
                    int typeInside = cont.get(type);
                    int typeOutside = typeCount - typeInside;
                    int nonType = contCount - typeInside;
                    
                    if (typeOutside == nonType)
                    {
                        contFound = true;
                        countFound++;
                    }
                }
                usedContainer[cc] = contFound;
            }
        }

        return (countFound == n) ? "Possible" : "Impossible";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<List<Integer>> container = new ArrayList<>();

                IntStream.range(0, n).forEach(i -> {
                    try {
                        container.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                String result = Result.organizingContainers(container);

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
