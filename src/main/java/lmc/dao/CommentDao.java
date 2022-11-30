package lmc.dao;

import lmc.model.Comment;
import lmc.model.User;
import lmc.util.CommentUtil;
import lmc.util.SqlSessionUtils;
import mapper.CommentMapper;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommentDao {
    public  Boolean addComment(Comment comment){
        Map map=new HashMap();
        map.put("content",comment.getContent());
        map.put("pubdate", new Timestamp(new java.util.Date().getTime()));
        map.put("ipaddress",comment.getIpAddress());
        map.put("hidden",false);
        map.put("likeNum",0);
        map.put("disLikeNum",0);
        if(comment.getReplyFor()!=null){
            System.out.println("不为空");
            map.put("reply_for_id",comment.getReplyFor().getId());
        }else{
            System.out.println("为空");
            map.put("reply_for_id",null);
        }
        map.put("creator",comment.getCreator().getId());
        map.put("news_id",comment.getNews().getId());

        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        Boolean aBoolean = commentMapper.addComment(map);
        return aBoolean;
    }
    public List<Comment> getCommentsByNewsId(int newsId){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        List<Comment> rows = commentMapper.getCommentsByNewsId(newsId);

        List<Comment> comments = new ArrayList<>();
        Map<Long, Comment> commentMap = new ConcurrentHashMap<>(); //保存数据，方便查询，去重 ，使用ConcurrentHashMap是为了解决线程安全问题，防止引发ConcurrentModificationException
        for(Comment comment: rows) {
            commentMap.put(comment.getId(), comment);
        }
        System.out.println(rows);
        //处于所有关联
        for(Comment comment: rows) {
            if(comment.getReplyId() != null) {
                List<Comment> replies = commentMap.get(comment.getId()).getReplies();  //注意:这里必须要以Map中数据为准（List中同一Comment对象可能存在多个）
                if(replies == null) {
                    replies = new ArrayList<>();
                    commentMap.get(comment.getId()).setReplies(replies); //注意：存入Map中的唯一对象
                }
                replies.add(commentMap.get(comment.getReplyId())); // 加入到回复列表中
            }
        }

        //移除回复的评论（只保留只评价）
        for (Long id: commentMap.keySet()) {
            Comment comment = commentMap.get(id);
            if(comment.getReplyFor()!=null) {
                if (comment.getReplyFor().getId() > 0) {
                    commentMap.remove(comment.getId());
                }
            }
        }

        //生成最终结果数据
        for(Long id: commentMap.keySet()) {
            comments.add(commentMap.get(id));
        }

        return comments;
    }
    public int updateLikeNum(int commentId, boolean like){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        int  likenum = commentMapper.selectLikeNum(commentId);
        if(like) {
            likenum++;
        } else if(!like && likenum>0) {
            likenum--;
        }
        commentMapper.updateLikeNum(likenum,commentId);
        return likenum;
    }
    public int updateDislikeNum(int commentId, boolean dislike){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        int  dislikeNum = commentMapper.selectDisLikeNum(commentId);
        if(dislike) {
            dislikeNum++;
        } else if(!dislike && dislikeNum>0) {
            dislikeNum--;
        }
        commentMapper.updateDislikeNum(dislikeNum,commentId);
        return dislikeNum;
    }

    public static void main(String[] args) {
        CommentDao commentDao = new CommentDao();
        System.out.println(CommentUtil.toMap(commentDao.getCommentsByNewsId(3)));
    }
}
