package com.web.xducatserver.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Cat {
    @Id
    private Integer id;
    private String location;
    private String color;
    private String img;

}
