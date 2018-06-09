package com.zz.bill.controller;

        import com.zz.bill.CommonCode;
        import com.zz.bill.entity.account.User;
        import com.zz.bill.model.JsonResult;
        import com.zz.bill.model.account.LoginInfo;
        import com.zz.bill.model.account.UserInfo;
        import com.zz.bill.service.account.IAccountService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account/{version}")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/register/check", method = RequestMethod.GET)
    public JsonResult checkAccountExist(@RequestParam String accountName) {
        Boolean exist = accountService.checkExist(accountName);
        if (exist)
            return JsonResult.builder().code(CommonCode.ACCOUNT_ALREADY_EXIST).msg("账户已经存在").data(exist).build();
        return JsonResult.builder().code(CommonCode.SUCC).msg("成功").data(exist).build();
    }

    @RequestMapping(value = "/register/", method = RequestMethod.POST)
    public JsonResult register(@RequestBody User user) {
        UserInfo register = accountService.register(user);
        return JsonResult.builder().code(CommonCode.SUCC).msg("成功").data(register).build();
    }

    @RequestMapping("/login/")
    public JsonResult login(@RequestBody LoginInfo loginInfo) {
        return accountService.login(loginInfo.getAccount(), loginInfo.getPwd());
    }
}
