package com.zz.bill.entity.account;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class User {

    private Integer id;
    @NotNull(message = "account 不能为空a")
    private String account;
    private String nickName;
    private String pwd;
    private String avatar;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
