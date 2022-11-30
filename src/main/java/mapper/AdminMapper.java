package mapper;

import lmc.model.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    Boolean login(@Param("account") String account, @Param("password") String password);
}
