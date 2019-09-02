import org.junit.*;
import org.junit.rules.ExpectedException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class Task1Test {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String lineSeparator = System.getProperty("line.separator");

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }



    @Test
    public void invalidIpException() throws Exception{
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Invalid ip: 192.168.0" );
        IP ip = new IP("192.168.0");
    }

    @Test
    public void invalidIpPartException() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("some octets are less than 0 or higher than 255: 192.256.0.1");
        IP ip = new IP("192.256.0.1");
    }

    @Test
    public void nonNumericIpPartException() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("some octets are non-numeric: 192.255.absc.1");
        IP ip = new IP("192.255.absc.1");
    }

    @Test
    public void unincrementableIP() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Cannot increment this IP: 255.255.255.255");
        IP ip = new IP("255.255.255.255");
        ip = ip.increase();
    }

    @Test
    public void testIncrement() throws Exception {
        Assert.assertTrue(new IP("192.168.0.1").increase().equals(new IP("192.168.0.2")));
        Assert.assertTrue(new IP("192.168.0.255").increase().equals(new IP("192.168.1.0")));
        Assert.assertTrue(new IP("192.168.255.255").increase().equals(new IP("192.169.0.0")));
    }



    @Test
    public void printIPBetween() throws Exception {
        IP from = new IP("192.168.0.1");
        IP to  = new IP("192.168.0.5");
        List<String> ips =  Task1.getAllIPBetween(from, to);
        List<String> expected = new ArrayList<String>() {{
            add("192.168.0.2");
            add("192.168.0.3");
            add("192.168.0.4");
        }};
        
        Assert.assertEquals(ips, expected);
       // Assert.assertEquals(String.format("192.168.0.2%s192.168.0.3%s192.168.0.4%s", lineSeparator, lineSeparator, lineSeparator), outContent.toString());
        
    }

    @Test
    public void mainWithInvalidArgs () throws Exception {
        String[] arguments = new String[] {"192.168.0.1"};
        Task1.main(arguments);
        Assert.assertEquals(String.format("invalid arguments%s", lineSeparator), outContent.toString());
    }

}