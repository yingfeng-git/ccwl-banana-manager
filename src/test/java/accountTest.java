import com.ccwl.manager.dao.userDao;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;


public class accountTest {

    @Test
    public void loginTest() {
        userDao u = new userDao();
        String numb = "17B543153";
        String password = "123456";

        String numFromDB = u.getAccountByNum(numb, password).toString();
        Assert.assertEquals(numb, numFromDB);

    }
}
