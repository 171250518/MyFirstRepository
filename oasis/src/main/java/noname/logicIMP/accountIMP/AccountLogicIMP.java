package noname.logicIMP.accountIMP;

import noname.PO.User;
import noname.VO.Response;
import noname.VO.UserForm;
import noname.VO.UserVO;
import noname.data.account.AccountData;
import noname.logic.account.AccountLogic;
import org.springframework.beans.factory.annotation.Autowired;
import noname.logicIMP.encryptIMP.MyBase64IMP;

/**
 * 账号逻辑接口的实现
/**
 * @author wch
 *@date 2020/2/29
* */

public class AccountLogicIMP implements AccountLogic {
    private final static String ACCOUNT_EXIST="账号已存在";
    @Autowired
    private AccountData accountData;

    public void setAccountData(AccountData accountData) {
        this.accountData = accountData;
    }

    public AccountData getAccountData() {
        return accountData;
    }

    @Override
    public Response registerAccount(UserForm userForm) {
        userForm.setUsername(MyBase64IMP.decode(userForm.getUsername()));
        userForm.setPassword(MyBase64IMP.decode(userForm.getPassword()));
        try {
            System.out.println(userForm.getUsername());
            System.out.println(userForm.getPassword());
            accountData.createNewAccount(userForm.getUsername(), userForm.getPassword());
        } catch (Exception e) {
            return Response.buildFailure(ACCOUNT_EXIST);
        }
        return Response.buildSuccess();


    }

    @Override
    public UserVO login(UserForm userForm) {
        userForm.setUsername(MyBase64IMP.decode(userForm.getUsername()));
        userForm.setPassword(MyBase64IMP.decode(userForm.getPassword()));
        User user = accountData.getAccountByName(userForm.getUsername());
        if (null == user || !user.getPassword().equals(userForm.getPassword())) {
            return null;
        }
        return new UserVO(user);
    }

}
