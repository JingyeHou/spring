package com.zz.bill.service.baseService;

import com.zz.bill.entity.account.User;
import com.zz.bill.exception.UserCreateException;

public interface IUserService {
    String create(User user) throws UserCreateException;
    Boolean checkExist(String account);
    User getUserByAccount(String account);
}
