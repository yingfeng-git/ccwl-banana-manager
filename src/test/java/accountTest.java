import com.ccwl.manager.service.StudentServiceImpl;
import org.junit.Test;


public class accountTest {

    @Test
    public void loginTest() {
        StudentServiceImpl s1 = new StudentServiceImpl();
        s1.AccountLogin("s101", "123");
    }
}
