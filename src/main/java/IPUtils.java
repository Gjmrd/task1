class IPUtils {

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

        int octet = 0;
        for (int i = 0; i < 4; i++) {

            try {
                octet = Integer.parseInt(parts[i]);
            } catch (NumberFormatException e) {
                throw new Exception("invalid ip: " + ip);
            }

            if (octet < 0 || octet > 255) {
                throw new Exception("invalid ip: " + ip);
            }
            result += octet * Math.pow(256, 3 - i);
        }

        return result;
    }
}
