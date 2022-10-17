package mapper;

import lmc.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.Map;

public interface UserMapper {
    User getUser(@Param("account") String account,@Param("password")  String password);
    User isUserExited(@Param("key") String key,@Param("value")  String value);
    Boolean addUser(Map<String,Object> map);
    void updateUser(@Param("nickname") String nickname, @Param("photo") String photo, @Param("birthday") Date birthday,@Param("email") String email,@Param("mobile") String mobile,@Param("account") String account);
    boolean changeUserPassword(@Param("account") String account,@Param("password") String password);
}
