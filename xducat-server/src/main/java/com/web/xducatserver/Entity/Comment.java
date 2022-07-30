package com.web.xducatserver.Entity;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class Comment {
    @Id
    private Integer commentid;
    private Integer id;
    private String name;
    private String comment;
    private Date date;
}
