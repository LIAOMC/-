package lmc.model;

import java.util.Date;

/**
 * 新闻
 */
public class News {
    private int id; //编号

    private String title; // 标题

    private String img; // 图片路径

    private Date pubdate; // 发布时间

    private String content; // 内容

    private Boolean stick; //是否置顶

    private int categoryId; //分类编号

    private int edior; //编辑（关联管理员）

    private int clickCount; // 点击计数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getStick() {
        return stick;
    }

    public void setStick(Boolean stick) {
        this.stick = stick;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getEdior() {
        return edior;
    }

    public void setEdior(int edior) {
        this.edior = edior;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", pubdate=" + pubdate +
                ", content='" + content + '\'' +
                ", stick=" + stick +
                ", categoryId=" + categoryId +
                ", edior=" + edior +
                ", clickCount=" + clickCount +
                '}';
    }
}
