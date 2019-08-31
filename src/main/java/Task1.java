public class Task1 {
    public static void main (String[] args) throws Exception{
        if (args.length == 2) {
            final long from = getLongFromIp(args[0]);
            final long to = getLongFromIp(args[1]);

            if (to > (from + 1)) {
                for (long longIp = from + 1; longIp < to; longIp++) {
                    System.out.println(getIPFromLong(longIp));
                }
            }
        }
        else {
            System.out.println("invalid arguments");
        }
    }

    static String getIPFromLong(final long ipLong) {
        return String.format("%d.%d.%d.%d",
                (ipLong >>> 24) & 0xff,
                (ipLong >>> 16) & 0xff,
                (ipLong >>>  8) & 0xff,
                (ipLong       ) & 0xff);
    }

    static long getLongFromIp(final String ip) throws Exception {
        long result = 0;

        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            throw new Exception("invalid ip");
        }

        int intPart = 0;
        for (int i = 0; i < 4; i++) {
            intPart = Integer.parseInt(parts[i]);
            if (intPart < 0 || intPart > 255) {
                throw new Exception("invalid ip");
            }
            result += intPart * Math.pow(256, 3 - i);
        }

        return result;
    }
}
