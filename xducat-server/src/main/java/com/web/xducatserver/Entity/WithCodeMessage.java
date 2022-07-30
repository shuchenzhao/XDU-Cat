package com.web.xducatserver.Entity;

import lombok.Data;

@Data
//@Entity
public class WithCodeMessage<T> {
//   @Id
    private String code;
    private String msg;
    private T data;
}
