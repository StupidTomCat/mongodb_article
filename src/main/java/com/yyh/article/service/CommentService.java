package com.yyh.article.service;

import com.yyh.article.dao.CommentRepository;
import com.yyh.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;//动态代理生成的对象SimpleMongoRepository ctrl+alt+b可查看

    @Autowired
    private MongoTemplate mongoTemplate;

    //保存一个评论
    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }
    //更新评论
    public void  updateComment(Comment comment){
        commentRepository.save(comment);
    }
    //根据id删除评论
    public void deleteCommentById(String id){
        commentRepository.deleteById(id);
    }
    //查询所有评论
    public List<Comment> findCommentList(){
        return commentRepository.findAll();
    }
    //根据id查询评论
    public Comment findCommentById(String id){
        return commentRepository.findById(id).get();
    }

    //根据父id查询文章评论的分页列表
    public Page<Comment> findByParentId(String parentId,int page,int size){//page当前页数
        return commentRepository.findByParentId(parentId, PageRequest.of(page-1,size));//PageRequest实现了Pageable
                                                                                              // page-1意思是换成下标
    }

    //实现评论点赞,即likenum加一
    public void updateThumbup(String id){
        Comment comment = commentRepository.findById(id).get();
        comment.setLikeNum(comment.getLikeNum()+1);
        commentRepository.save(comment);
    }

    //MongoTemplate实现对某列的操作:likenum加一
    public void updateLikeNum(String id){

        //查询条件
        Query query = Query.query(Criteria.where("_id").is(id));//都是import org.springframework.data.mongodb.core.query.Query;
        //更新内容
        Update update = new Update();
        update.inc("likeNum");

        mongoTemplate.updateFirst(query,update,Comment.class);//updateFirst更新符合条件的第一个记录，updateMulti则更新符合条件的多个文档
    }
}
