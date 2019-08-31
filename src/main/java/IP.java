import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IP implements Comparable<IP> {

    private int octet1;
    private int octet2;
    private int octet3;
    private int octet4;

    IP(String ip) throws Exception {
        String[] octets = ip.split("\\.");

        if (octets.length != 4)
            throw new Exception("Invalid ip: " + ip);


        List<Integer> intOctets;

        try {
            intOctets = Arrays.stream(octets)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new Exception("some octets are non-numeric: " + ip);
        }

        for (Integer octet : intOctets) {
            if (octet < 0 || octet > 255)
                throw new Exception("some octets are less than 0 or higher than 255: " + ip);
        }

        octet1 = intOctets.get(0);
        octet2 = intOctets.get(1);
        octet3 = intOctets.get(2);
        octet4 = intOctets.get(3);
    }

    private IP(int octet1, int octet2, int octet3, int octet4) {
        this.octet1 = octet1;
        this.octet2 = octet2;
        this.octet3 = octet3;
        this.octet4 = octet4;
    }

    public int compareTo(IP other) {
        int comparison = Integer.compare(this.octet1, other.octet1);
        if (comparison != 0)
            return comparison;

        comparison = Integer.compare(this.octet2, other.octet2);
        if (comparison != 0)
            return comparison;

        comparison = Integer.compare(this.octet3, other.octet3);
        if (comparison != 0)
            return comparison;

        comparison = Integer.compare(this.octet4, other.octet4);
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
