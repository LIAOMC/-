package mapper;

import lmc.model.Tag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface TagMapper {
    public List<Tag> getAllTags();
    Set<Tag> getTagByNewsId(@Param("newsId") int newsId);
}
