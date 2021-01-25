package com.yyh.article.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "comment")//可以省略，则默认使用类名小写映射集合
@CompoundIndex(def = "{'userId':1,'nickname':-1}")//此处应该是新建复合索引
public class Comment implements Serializable {

    @Id//主键，自动对于mongodb的"_id"字段，如果该名称就是"id"，则可以省略该注解,此处就可以不写
    private String id;
    @Field("content")//对应mongodb字段的名称，如果一致则可省略该注解
    private String content;
    private Date publishTime;//数据库没有该字段
    @Indexed//单字段索引  复合索引：@CompoundIndex(def = "{'userid:1,'nickname':-1}")
    @Field("userid")//因为大小写不一致（mongodb要求全部小写），所以我得加上注解，强迫症hh
    private String userId;
    private String nickname;
    @Field("createtime")
    private LocalDateTime createDateTime;//评论的日期时间  估计LocalDateTime跟yyyy-MM-ddTHH:mm:ss.SSSZ有关
    @Field("likenum")
    private Integer likeNum;
    @Field("replynum")
    private Integer replyNum;
    private String state;
    @Field("parentid")
    private String parentId;//父id
    @Field("articleid")
    private String articleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", publishTime=" + publishTime +
                ", userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createDateTime=" + createDateTime +
                ", likeNum=" + likeNum +
                ", replyNum=" + replyNum +
                ", state='" + state + '\'' +
                ", parentId='" + parentId + '\'' +
                ", articleId='" + articleId + '\'' +
                '}';
    }
}
