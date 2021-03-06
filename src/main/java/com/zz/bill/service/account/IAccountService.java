package com.zz.bill.service.account;
import com.zz.bill.entity.account.User;
import com.zz.bill.model.JsonResult;
import com.zz.bill.model.account.UserInfo;

public interface IAccountService {
    JsonResult checkExist(String accountName);
    JsonResult register(User user);
    JsonResult login(String account, String pwd);
}
