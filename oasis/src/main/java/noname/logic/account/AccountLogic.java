package noname.logic.account;


import noname.VO.Response;
import noname.VO.UserForm;
import noname.VO.UserVO;

/**
 * 账号逻辑接口
/*
 * 登录注册
 * @author wch
 * @date 2020/02/29
 * */
public interface AccountLogic {
    /**
     * 注册账号
     * @return
     */
    public Response registerAccount(UserForm userForm);

    /**
     * 用户登录，登录成功会将用户信息保存再session中
     * @return
     */
    public UserVO login(UserForm userForm);
}
