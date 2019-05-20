/** 

package com.propensi.eis.Controller;

import com.propensi.eis.Repository.SupplierDb;

import java.util.List;
import javax.xml.ws.Response;

import com.propensi.eis.Model.SupplierModel;
import com.propensi.eis.Rest.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.validation.BindingResult;



@Controller
@RequestMapping("/api")
public class SupplierController<SupplierModel> {
    @Autowired
    SupplierDb supplierDb;

    @GetMapping(value = "/getAllSupplier")
    @ResponseBody
    public BaseResponse<List<SupplierModel>> getAllSupplier() {
        BaseResponse<List<SupplierModel>> response = new BaseResponse<List<SupplierModel>>();
        List<SupplierModel> test = (List<SupplierModel>) supplierDb.findAll();
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(test);
        System.out.println(supplierDb.findAll());
        return response;
    }

}

*/