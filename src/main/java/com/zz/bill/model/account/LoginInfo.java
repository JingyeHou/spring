package com.zz.bill.model.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginInfo {
    String account;
    String pwd;
}
