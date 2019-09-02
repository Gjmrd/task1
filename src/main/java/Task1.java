public class Task1 {
    public static void main(String[] args) {
        if (args.length < 2){
            System.out.println("invalid arguments");
            return;
        }
        try {
            printIPBetween(new IP(args[0]), new IP(args[1]));
        } catch (Exception e) {
            System.out.println("Something is wrong: " + e.getMessage());
        }
    }

    public static void printIPBetween(IP from, IP to) throws Exception{
        while (to.compareTo(from.increase()) > 0) {
            from = from.increase();
            System.out.println(from.toString());
        }
    }
}
