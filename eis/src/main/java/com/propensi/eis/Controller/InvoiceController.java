package com.propensi.eis.Controller;

import java.util.List;

import com.propensi.eis.Model.InvoiceModel;
import com.propensi.eis.Repository.InvoiceDb;
import com.propensi.eis.Rest.BaseResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InvoiceController {

    @Autowired
    InvoiceDb invoiceDb;

    @GetMapping(value = "/getAllInvoice")
    @ResponseBody
    public BaseResponse<List<InvoiceModel>> getAllInvoice() {
        BaseResponse<List<InvoiceModel>> response = new BaseResponse<List<InvoiceModel>>();
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(invoiceDb.findAll());
        return response;
    }
}