package noname.logic.account;

import noname.PO.User;
import noname.VO.Response;
import noname.VO.UserForm;
import noname.VO.UserVO;
import noname.data.account.AccountData;
import noname.logicIMP.accountIMP.AccountLogicIMP;
import noname.logicIMP.encryptIMP.Base64IMP;
import noname.logicIMP.encryptIMP.MyBase64IMP;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;

public class AccountLogicTest {
//
//    private AccountLogic accountLogic = null;
//
//    @Before
//    public void setUp(){
//        accountLogic = new AccountLogicIMP();
//       // User user = new User("admin","password");
//    }
//
//    @After
//    public void tearDown(){
//        accountLogic = null;
//    }
//
//    @Test
//    public void registerAccount() {
//        UserForm userForm = new UserForm();
//        userForm.setUsername("dXNlcm5hbWU=");//username的加密形式
//        userForm.setPassword("cGFzc3dvcmQ=");//password的加密形式
//        Response response = accountLogic.registerAccount(userForm);
//        assertTrue(response.getSuccess());
//        //todo: 把这个账户从数据库删除
//    }
//
//    @Test(expected = Exception.class)
//    public void registerAccountFail() throws Exception {
//        UserForm userForm = new UserForm();
//        userForm.setUsername("dXNlcm5hbWU=");//username的加密形式
//        userForm.setPassword("cGFzc3dvcmQ=");//password的加密形式
//        Response response = accountLogic.registerAccount(userForm);
//        assertTrue(response.getSuccess());
//        response = accountLogic.registerAccount(userForm);
//        assertEquals("账号已存在",response.getMsg());
//        //todo: 把这个账户从数据库删除
//    }
//
//    @Test
//    public void login() {
//        UserForm userForm = new UserForm();
//        userForm.setUsername("dXNlcm5hbWU=");//username的加密形式
//        userForm.setPassword("cGFzc3dvcmQ=");//password的加密形式
//        Response response = accountLogic.registerAccount(userForm);
//        UserVO userVO = accountLogic.login(userForm);
//        assertEquals("password",userVO.getPassword());
//        //todo: 把这个账户从数据库删除
//    }
//
//    @Test
//    public void loginFail() {
//        UserForm userForm = new UserForm();
//        userForm.setUsername("dXNlcm5hbWU=");//username的加密形式
//        userForm.setPassword("cGFzc3dvcmQ=");//password的加密形式
//        UserVO userVO = accountLogic.login(userForm);
//        assertNull(userVO);
//    }

}