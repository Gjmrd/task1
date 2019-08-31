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
    public void getIPFromLong() {
        Assert.assertEquals(Task1.getIPFromLong(3232235521L), "192.168.0.1");
    }

    @Test
    public void getLongFromIp() throws Exception {
        Assert.assertEquals(Task1.getLongFromIp("192.168.0.1"), 3232235521L);

    }

    @Test
    public void testInvalidIpException() throws Exception{
        expectedException.expect(Exception.class);
        expectedException.expectMessage("invalid ip");
        Task1.getLongFromIp("192.168.0");
    }

    @Test
    public void testInvalidIpPartException() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("invalid ip");
        Task1.getLongFromIp("192.256.0.1");
    }

    @Test
    public void main() {
        String[] arguments = new String[]{"192.168.0.1", "192.168.0.5"};
        Task1.main(arguments);
        Assert.assertEquals("192.168.0.2\r\n192.168.0.3\r\n192.168.0.4\r\n", outContent.toString());
    }

    @Test
    public void mainWithWrongArgs() {
        String[] arguments = new String[]{"192.168.0.1"};
        Task1.main(arguments);
        Assert.assertEquals("invalid arguments\r\n", outContent.toString());
    }

}