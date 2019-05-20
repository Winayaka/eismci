package com.propensi.eis.Controller;

import java.util.List;
import com.propensi.eis.Rest.BaseResponse;
import com.propensi.eis.Model.PaymentMethodModel;
import com.propensi.eis.Repository.PaymentMethodDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class PaymentMethodController {

    @Autowired
    PaymentMethodDb paymentMethodDb;

    @GetMapping(value = "/getAllPaymentMethod")
    @ResponseBody
    public BaseResponse<List<PaymentMethodModel>> getAllPaymentMethod() {
        BaseResponse<List<PaymentMethodModel>> response = new BaseResponse<List<PaymentMethodModel>>();
        List<PaymentMethodModel> test = (List<PaymentMethodModel>) paymentMethodDb.findAll();
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(test);
        return response;
    }
}