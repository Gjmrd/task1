public class Task1 {
    public static void main(String[] args) {
        if (args.length >= 2) {
            try {
                final long from = getLongFromIp(args[0]);
                final long to = getLongFromIp(args[1]);

                if (to > (from + 1)) {
                    for (long longIp = from + 1; longIp < to; longIp++) {
                        System.out.println(getIPFromLong(longIp));
                    }
                }
            } catch (Exception e) {
                System.out.println("something is wrong: " + e.getMessage());
            }

        } else {
            System.out.println("invalid arguments");
        }
    }

    /**
     * Converts integer representation of ip address into string
     *
     * @param ipLong long int representation of ip address
     * @return string representation of ip address
     */
    static String getIPFromLong(final long ipLong) {
        return String.format("%d.%d.%d.%d",
                (ipLong >>> 24) & 0xff,
                (ipLong >>> 16) & 0xff,
                (ipLong >>> 8) & 0xff,
                (ipLong) & 0xff);
    }

    /**
     * @param ip string representation of ip address
     * @return long int representation of ip address
     * @throws Exception if argument is invalid
     */
    static long getLongFromIp(final String ip) throws Exception {
        long result = 0;

        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            throw new Exception("invalid ip: " + ip);
        }

        int intPart = 0;
        for (int i = 0; i < 4; i++) {

            try {
                intPart = Integer.parseInt(parts[i]);
            } catch (NumberFormatException e) {
                throw new Exception("invalid ip: " + ip);
            }

            if (intPart < 0 || intPart > 255) {
                throw new Exception("invalid ip: " + ip);
            }
            result += intPart * Math.pow(256, 3 - i);
        }

        return result;
    }
}
