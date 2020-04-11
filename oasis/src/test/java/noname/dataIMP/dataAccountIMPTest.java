package noname.dataIMP;

import noname.Application;
import noname.data.account.AccountData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class dataAccountIMPTest {
//    @Autowired
//    AccountData accountData;
//
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @Test
//    public void createAccount(){
//        String usern="ski";
//        String userp="xkcvsdfs";
//        accountData.createNewAccount(usern,userp);
//
//        String sql="Select passward from users where username='"+usern+"'";
//        String res=jdbcTemplate.queryForObject(sql,String.class);
//        assertEquals(userp,res);
//
//        sql="delete from users where username='"+usern+"'";
//        jdbcTemplate.update(sql);
//
//    }
//
//
//    @Before
//    public void setUp(){
//
//        String test1usern="skiiix";
//        String test1userp="kxcv1212";
//        String sql="insert into users(username,passward)"+ "values('"+test1usern+"','"+test1userp+"')";
//        jdbcTemplate.update(sql);
//    }
//
//    @After
//    public void tearDown(){
//        String test1usern="skiiix";
//        String sql="delete from users where username='"+test1usern+"'";
//        jdbcTemplate.update(sql);
//    }
//
//
//    @Test
//    public void searchAcoount(){
//        String test1usern="skiiix";
//        String test1userp="kxcv1212";
//        assertEquals(test1userp,accountData.getAccountByName(test1usern).getPassword());
//
//    }


}
