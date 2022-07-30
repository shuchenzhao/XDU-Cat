//给MongoDB用的

package com.web.xducatserver.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "News")
public class News {

    @Id
    private String _id;
    private String title;
    private String date;
    private String description;


}
