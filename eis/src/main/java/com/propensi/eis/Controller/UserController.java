package com.propensi.eis.Controller;

import java.util.List;

import com.propensi.eis.Model.StaffModel;
import com.propensi.eis.Model.UserModel;
import com.propensi.eis.Repository.StaffDb;
import com.propensi.eis.Repository.UserDb;
import com.propensi.eis.Rest.BaseResponse;
import com.propensi.eis.Rest.AddStaffModel;
import com.propensi.eis.RestModel.LoginForm;
import com.propensi.eis.RestModel.LoginResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    StaffDb staffDb;

    @Autowired
    UserDb userDb;

    @PostMapping(value = "/login")
    @ResponseBody
    public BaseResponse<LoginResponse> login(@RequestBody LoginForm loginForm,
                                             BindingResult bindingResult){
        BaseResponse<LoginResponse> response = new BaseResponse<LoginResponse>();
        LoginResponse responseObject = new LoginResponse();

        System.out.println("test");
        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("Error");
        } else {
            if (userDb.findByUsername(loginForm.getUsername()) == null) {
                response.setStatus(505);
                response.setMessage("wrong Username");
            } else {
                UserModel target = userDb.findByUsername(loginForm.getUsername());
                String passwordCheck = target.getPassword();

                if (!target.getActiveStatus()) {
                    response.setStatus(502);
                    response.setMessage("Account not active");
                } else {
                    if (passwordCheck.equals(loginForm.getPassword())) {
                        response.setStatus(200);
                        response.setMessage("Login Success");
                        responseObject.setRole(target.getRole());
                        if (target.getStaff() == null){
                            responseObject.setId(target.getCustomer().getId());                            
                        }
                        else{
                            responseObject.setId(target.getStaff().getStaffId());
                        }
                        response.setResult(responseObject);
                        
                    }
                    else{
                        response.setStatus(503);
                        response.setMessage("Wrong Password");
                    }
                }
            }
        }

        return response;
    }

    // get specific user
    @GetMapping(value = "/getUser")
    @ResponseBody
    public BaseResponse<UserModel> getStaff(@RequestParam("staffId") String id) {
        BaseResponse<UserModel> response = new BaseResponse<UserModel>();
        long idLong = Long.parseLong(id);
        StaffModel target = staffDb.findById(idLong).get();
        System.out.print(target.getName());
        System.out.print(target.getUser());
        UserModel targettwo = target.getUser();
        if (target == null) {
            response.setStatus(404);
            response.setMessage("User not found");
        } else {
            response.setStatus(200);
            response.setMessage("Success");
            response.setResult(targettwo);
        }

        return response;
    }

}