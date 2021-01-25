package com.yyh.article.service;

import com.yyh.article.pojo.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void testFindCommentList(){
        List<Comment> commentList = commentService.findCommentList();
        System.out.println(commentList);
    }

    @Test
    public void testFindCommentById(){
        Comment comment = commentService.findCommentById("2");
        System.out.println(comment);
    }

    @Test
    public void testSaveComment(){
        Comment comment = new Comment();
        comment.setArticleId("100000");
        comment.setContent("测试save方法");
        comment.setCreateDateTime(LocalDateTime.now());
        comment.setUserId("1003");
        comment.setNickname("stupidcat");
        comment.setState("1");
        comment.setLikeNum(1666);
        comment.setReplyNum(17);

        commentService.saveComment(comment);
    }

    @Test
    public void testFindByParentId(){
        Page<Comment> page = commentService.findByParentId("3", 1, 2);
        System.out.println(page.getTotalElements());
        System.out.println(page.getContent());
    }

    @Test
    public void testUpdateLikeNum(){
        commentService.updateLikeNum("1");
    }
}
