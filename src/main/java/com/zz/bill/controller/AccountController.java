package com.zz.bill.controller;

        import com.zz.bill.entity.account.User;
        import com.zz.bill.model.JsonResult;
        import com.zz.bill.model.account.LoginInfo;
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
        return accountService.checkExist(accountName);
    }

    @RequestMapping(value = "/register/", method = RequestMethod.POST)
    public JsonResult register(@RequestBody User user) {
        return accountService.register(user);
    }

    @RequestMapping("/login/")
    public JsonResult login(@RequestBody LoginInfo loginInfo) {
        return accountService.login(loginInfo.getAccount(), loginInfo.getPwd());
    }
}
