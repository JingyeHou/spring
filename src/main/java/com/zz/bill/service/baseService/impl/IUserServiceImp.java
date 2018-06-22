package com.zz.bill.service.baseService.impl;

import com.zz.bill.entity.account.User;
import com.zz.bill.exception.UserCreateException;
import com.zz.bill.service.baseService.IUserService;
import com.zz.bill.util.TokenHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class IUserServiceImp implements IUserService {
    private Map<String, User> users;

    @PostConstruct
    private void init(){
        users = new HashMap<String, User>();
    }

    @Override
    public String create(User user) throws UserCreateException{
        users.put(user.getAccount(), user);
        String token = TokenHolder.getTokenByAccount(user.getAccount());
        TokenHolder.setTokenForUserId(token, user.getId());
        return token;
    }

    @Override
    public Boolean checkExist(String account) {
        return users.containsKey(account);
    }

    @Override
    public User getUserByAccount(String account) {
        return users.get(account);
    }
}
