package noname.data.account;

import noname.PO.User;

/**
 * @author wch
 * @date 2020/2/29
 *
 * */
public interface AccountData {
    /**
     * 创建一个新的账号
     * @param username
     * @param password
     * @return
     */
    public int createNewAccount(String username,String password);

    /**
     * 根据用户名查找账号
     * @param username
     * @return
     */
    public User getAccountByName(String username);

}
