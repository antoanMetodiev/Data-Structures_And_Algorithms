import java.util.ArrayList;
import java.util.List;

public class notes {
    public static void main(String[] args) {
//        System.out.println("Hello DSA!");

        List<String> names = new ArrayList<>();

        names.add("Petar");
        names.add("Lilyan");
        names.add("Aleksandar");

        names.add(0, "Eric");

        System.out.println(names);

    }

}
