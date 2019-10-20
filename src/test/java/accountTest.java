import com.ccwl.manager.dao.UserDao;
import org.junit.Test;


public class accountTest {

    @Test
    public void loginTest() {
        UserDao user = new UserDao();
        String numb = "17B543153";
        String password = "123456";

        String numFromDB = user.getAccountByNum(numb, password).toString();
        System.out.println(numFromDB);

    }
}
