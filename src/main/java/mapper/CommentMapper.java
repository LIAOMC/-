package mapper;

import lmc.model.Comment;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface CommentMapper {
    Boolean addComment(Map map);
    List<Comment> getCommentsByNewsId(@Param("newsId") int newsId);
    void updateLikeNum(@Param("likenum") int likenum,@Param("commentId") int commentId);
    void updateDislikeNum(@Param("dislikenum") int dislikenum, @Param("commentId") int commentId);
    int selectLikeNum(@Param("commentId")int commentId);
    int selectDisLikeNum(@Param("commentId")int commentId);
}
