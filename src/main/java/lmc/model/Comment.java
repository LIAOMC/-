package lmc.model;

import java.util.Date;
import java.util.List;

public class Comment {
    private  Long id;
    private String content;
    private Date pubDate;
    private String ipAddress;
    private  News news;
    private  User creator;
    private  Integer likeNum;
    private  Integer disLikeNum;
    private Comment replyFor;
    private Boolean hidden;
    private Long replyId;
    private List<Comment> replies;//回复的列表

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", pubDate=" + pubDate +
                ", ipAddress='" + ipAddress + '\'' +
                ", news=" + news +
                ", creator=" + creator +
                ", likeNum=" + likeNum +
                ", disLikeNum=" + disLikeNum +
                ", replyFor=" + replyFor +
                ", hidden=" + hidden +
                ", replyId=" + replyId +
                ", replies=" + replies +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getDisLikeNum() {
        return disLikeNum;
    }

    public void setDisLikeNum(Integer disLikeNum) {
        this.disLikeNum = disLikeNum;
    }

    public Comment getReplyFor() {
        return replyFor;
    }

    public void setReplyFor(Comment replyFor) {
        this.replyFor = replyFor;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }
}
