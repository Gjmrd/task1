public class Task1 {
    public static void main(String[] args) {
        if (args.length >= 2) {
            try {
                IP from = new IP(args[0]);
                IP to = new IP(args[1]);
              
                    while (to.compareTo(from.increase()) > 0) {
                        from = from.increase();
                        System.out.println(from.toString());
                    }
                
            } catch (Exception e) {
                System.out.println("Something is wrong: " + e.getMessage());
            }
        } else  {
            System.out.println("invalid arguments");
        }
    }
}
