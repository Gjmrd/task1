import org.junit.*;
import org.junit.rules.ExpectedException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



public class Task1Test {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

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
    public void unIncrementableIP() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Cannot increment this IP: 255.255.255.255");
        IP ip = new IP("255.255.255.255");
        ip = ip.increase();
    }

    @Test
    public void main() throws Exception {
        String[] args = new String[] {"192.168.0.1","192.168.0.5"};
        Task1.main(args);
        Assert.assertEquals("192.168.0.2\r\n192.168.0.3\r\n192.168.0.4\r\n", outContent.toString());
    }

    @Test
    public void mainWithInvalidArgs () throws Exception {
        String[] arguments = new String[] {"192.168.0.1"};
        Task1.main(arguments);
        Assert.assertEquals("invalid arguments\r\n", outContent.toString());
    }

}