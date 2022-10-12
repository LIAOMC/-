package mapper;

import lmc.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {
    User getUser(@Param("account") String account,@Param("password")  String password);
    User isUserExited(@Param("key") String key,@Param("value")  String value);

    Boolean addUser(Map<String,Object> map);
}
