package noname.dataIMP.accountIMP;

import noname.PO.User;
        import noname.data.account.AccountData;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.jdbc.core.JdbcTemplate;
        import org.springframework.stereotype.Repository;

@Repository
public class AccountDataIMP implements AccountData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createNewAccount(String username, String password) {
        String sql="insert into users(username,passward)"+ "values('2222','1333')";
        return (int) jdbcTemplate.update(sql);

    }

    @Override
    public User getAccountByName(String username) {
        return null;
    }
}
