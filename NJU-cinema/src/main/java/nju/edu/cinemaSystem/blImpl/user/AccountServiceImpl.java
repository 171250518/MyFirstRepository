package nju.edu.cinemaSystem.blImpl.user;

import nju.edu.cinemaSystem.bl.user.AccountService;
import nju.edu.cinemaSystem.data.user.AccountMapper;
import nju.edu.cinemaSystem.po.User;
import nju.edu.cinemaSystem.vo.UserForm;
import nju.edu.cinemaSystem.vo.ResponseVO;
import nju.edu.cinemaSystem.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huwen
 * @date 2019/3/23
 */
@Service
public class AccountServiceImpl implements AccountService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseVO registerAccount(UserForm userForm) {
        try {
            System.out.println(userForm.getUsername());
            System.out.println(userForm.getPassword());
            accountMapper.createNewAccount(userForm.getUsername(), userForm.getPassword());
        } catch (Exception e) {
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public UserVO login(UserForm userForm) {
        User user = accountMapper.getAccountByName(userForm.getUsername());
        if (null == user || !user.getPassword().equals(userForm.getPassword())) {
            return null;
        }
        return new UserVO(user);
    }


}
