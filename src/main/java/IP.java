

public class IP implements Comparable<IP>{

    private int octet1;
    private int octet2;
    private int octet3;
    private int octet4;

    IP (String ip) throws Exception {
        String[] octets = ip.split("\\.");

        if (octets.length != 4)
            throw new Exception("Invalid ip: " + ip);


        try {
            octet1 = Integer.parseInt(octets[0]);
            octet2 = Integer.parseInt(octets[1]);
            octet3 = Integer.parseInt(octets[2]);
            octet4 = Integer.parseInt(octets[3]);
        } catch (NumberFormatException e) {
            throw new Exception("some octets are non-numeric: " + ip);
        }


        for (int i = 0; i < 4; i++) {
            int intOctet = Integer.parseInt(octets[i]);
            if (intOctet < 0 || intOctet > 255)
                throw new Exception("some octets are less than 0 or higher than 255: " + ip);
        }
    }

    private IP(int octet1,  int octet2, int octet3, int octet4) {
        this.octet1 = octet1;
        this.octet2 = octet2;
        this.octet3 = octet3;
        this.octet4 = octet4;
    }

    public int compareTo(IP other) {
        int comparison = Integer.compare(this.octet1,other.octet1);
        if (comparison != 0)
            return comparison;

        comparison = Integer.compare(this.octet2,other.octet2);
        if (comparison != 0)
            return comparison;

        comparison = Integer.compare(this.octet3,other.octet3);
        if (comparison != 0)
            return comparison;

        comparison = Integer.compare(this.octet4,other.octet4);
        return comparison;
    }

    IP increase() throws Exception {
        if (octet4 < 255)
            return new IP(octet1, octet2, octet3, octet4 + 1);

        if (octet3 < 255)
            return new IP(octet1, octet2, octet3 + 1, 0);

        if (octet2 < 255)
            return new IP(octet1, octet2 + 1, 0, 0);

        if (octet1 < 255)
            return new IP(octet1 + 1, 0, 0, 0);

        throw new Exception("Cannot increment this IP: " + this.toString());
    }

    @Override
    public String toString() {
        return String.format("%d.%d.%d.%d", octet1, octet2, octet3, octet4);
    }


    public boolean equals(IP other) {
        if (octet1 != other.octet1)
            return false;
        if (octet2 != other.octet2)
            return false;
        if (octet3 != other.octet3)
            return false;
        if (octet4 != other.octet4)
            return false;

        return true;
    }

}
