import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {
        if (args.length < 2){
            System.out.println("invalid arguments");
            return;
        }
        try {
            getAllIPBetween(new IP(args[0]), new IP(args[1])).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Something is wrong: " + e.getMessage());
        }
    }

    public static List<String> getAllIPBetween(IP from, IP to) throws Exception{
        List<String> resultList = new ArrayList<>();

        IP current = from;
        while (to.compareTo(current.increase()) > 0) {
            current = current.increase();
            resultList.add(current.toString());
        }
        return resultList;
    }
}
