package com.zz.bill.service.account.impl;

import com.zz.bill.CommonCode;
import com.zz.bill.entity.account.User;
import com.zz.bill.exception.UserCreateException;
import com.zz.bill.model.JsonResult;
import com.zz.bill.model.account.UserInfo;
import com.zz.bill.service.baseService.IUserService;
import com.zz.bill.service.account.IAccountService;
import com.zz.bill.util.TokenHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    IUserService userService;
    @Override
    public JsonResult checkExist(String accountName) {
       if (userService.checkExist(accountName))
           return JsonResult
                   .builder()
                   .code(CommonCode.SUCC)
                   .msg("success")
                   .data(true)
                   .build();
       return JsonResult
               .builder()
               .code(CommonCode.ACCOUNT_ALREADY_EXIST)
               .msg("failure")
               .data(false)
               .build();
    }

    @Override
    public JsonResult register(User user) {
        String token = "";
        try {
            token = userService.create(user);
        } catch (UserCreateException e) {
            e.printStackTrace();
            return JsonResult
                    .builder()
                    .code(CommonCode.SYS_ERR)
                    .msg("the account already exists")
                    .data(false)
                    .build();
        }
        UserInfo userInfo = UserInfo
                .builder()
                .account(user.getAccount())
                .uid(user.getId())
                .authToken(token)
                .build();
        return JsonResult
                .builder()
                .code(CommonCode.SUCC)
                .msg("success")
                .data(userInfo)
                .build();
    }

    @Override
    public JsonResult login(String account, String pwd) {
        User user = userService.getUserByAccount(account);
        if (user != null) {
            if (user.getPwd() != pwd) {
                return JsonResult.builder().code(CommonCode.INVALID_PWD).msg("密码错误").data("").build();
            }
            UserInfo userInfo = UserInfo
                    .builder()
                    .account(account)
                    .uid(user.getId())
                    .authToken(TokenHolder.getTokenByAccount(account))
                    .build();
            return JsonResult.builder().code(CommonCode.SUCC).msg("成功").data(userInfo).build();
        }
        else {
            return JsonResult.builder().code(CommonCode.ACCOUNT_NOT_EXIST).msg("用户不存在").data("").build();
        }
    }
}
