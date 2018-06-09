package com.zz.bill.service.account.impl;

import com.zz.bill.CommonCode;
import com.zz.bill.entity.account.User;
import com.zz.bill.model.JsonResult;
import com.zz.bill.model.account.UserInfo;
import com.zz.bill.service.account.IAccountService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountServiceImpl implements IAccountService {
    Map<String, User> users = new HashMap<String, User>();
    @Override
    public Boolean checkExist(String accountName) {
        return Boolean.FALSE;
    }

    @Override
    public UserInfo register(User user) {
        users.put(user.getAccount(), user);
        return UserInfo
                .builder()
                .account(user.getAccount())
                .uid(user.getId())
                .authToken("")
                .build();
    }

    @Override
    public JsonResult login(String account, String pwd) {
        if (users.get(account) != null) {
            if (users.get(account).getPwd() != pwd) {
                return JsonResult.builder().code(CommonCode.INVALID_PWD).msg("密码错误").data("").build();
            }
            return JsonResult.builder().code(CommonCode.SUCC).msg("成功").data("").build();
        }
        else {
            return JsonResult.builder().code(CommonCode.ACCOUNT_NOT_EXIST).msg("用户不存在").data("").build();
        }
    }
}
