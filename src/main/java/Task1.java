public class Task1 {
    public static void main(String[] args) {
        if (args.length >= 2) {
            try {
                IPUtils.printAllIpBetween(args[0], args[1]);
            } catch (Exception e) {
                System.out.println("Something is wrong: " + e.getMessage());
            }
        } else  {
            System.out.println("invalid arguments");
        }
    }

}
