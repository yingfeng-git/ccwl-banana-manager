import com.ccwl.manager.dao.userDao;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;


public class accountTest {

    @Test
    public void loginTest() {
        userDao user = new userDao();
        String numb = "17B543153";
        String password = "123456";

        String numFromDB = user.getAccountByNum(numb, password).toString();
        System.out.println(numFromDB);

    }
}
