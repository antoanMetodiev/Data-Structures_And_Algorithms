import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        2 45 1 4 1 54 1 4 1 3

        int[] inputNumbers = Arrays.stream(reader.readLine()
                        .split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int[] sorter = new int[100];

        for (int inputNumber : inputNumbers) {
            sorter[inputNumber]++;
        }

        Arrays.stream(sorter).forEach(System.out::println);
    }
}
