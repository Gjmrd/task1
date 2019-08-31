public class Task1 {
    public static void main(String[] args) {
        if (args.length >= 2) {
            try {
                printAllIpBetween(args[0], args[1]);
            } catch (Exception e) {
                System.out.println("Something is wrong: " + e.getMessage());
            }
        } else  {
            System.out.println("invalid arguments");
        }

    }

    /**
     * Print all Ip between two ip in console
     *
     * @param ipFrom start ip
     * @param ipTo end ip
     * @throws Exception is arguments are null or invalid
     */
    static void printAllIpBetween(String ipFrom, String ipTo) throws Exception {
        final long from = getLongFromIp(ipFrom);
        final long to = getLongFromIp(ipTo);

        if (to > (from + 1)) {
            for (long longIp = from + 1; longIp < to; longIp++) {
                System.out.println(getIPFromLong(longIp));
            }
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
     * Converts string ip to its integer representation
     *
     * @param ip string representation of ip address
     * @return long int representation of ip address
     * @throws Exception if argument is invalid
     */
    static long getLongFromIp(final String ip) throws Exception {
        long result = 0;

        if (ip == null) {
            throw new Exception("argument is null");
        }

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
