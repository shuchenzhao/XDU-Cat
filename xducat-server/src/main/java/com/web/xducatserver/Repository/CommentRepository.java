package com.web.xducatserver.Repository;

import com.web.xducatserver.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Date> {

    @Query(value="select * from comment where id=?1",nativeQuery=true)
    public List<Comment> findCommentByCatId(Integer Id);


//    注意增删改的语句要有这样的注解
    @Modifying
    @Transactional
    @Query(value="insert into comment value(null,?1,?2,?3,?4)",nativeQuery=true)
    public void insertComment(Integer id,String comment,String name,Date date);

    @Modifying
    @Transactional
    @Query(value="delete from comment where id=?1 and commentid=?2",nativeQuery=true)
    public void deleteComment(Integer id,Integer commentId);

    @Query(value="select * from comment where id=?1 and commentid=?2",nativeQuery=true)
    public Comment findCommentExtend(Integer id,Integer commentId);
}
