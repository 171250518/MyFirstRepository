package nju.edu.cinemaSystem.bl.user;

import nju.edu.cinemaSystem.vo.UserForm;
import nju.edu.cinemaSystem.vo.ResponseVO;
import nju.edu.cinemaSystem.vo.UserVO;

/**
 * @author huwen
 * @date 2019/3/23
 */
public interface AccountService {

    /**
     * 注册账号
     * @return
     */
    public ResponseVO registerAccount(UserForm userForm);

    /**
     * 用户登录，登录成功会将用户信息保存再session中
     * @return
     */
    public UserVO login(UserForm userForm);

}
