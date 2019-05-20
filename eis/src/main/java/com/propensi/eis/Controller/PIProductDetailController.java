package com.propensi.eis.Controller;

import java.util.List;
import java.util.Optional;

import com.propensi.eis.Model.PIPOTransactionModel;
import com.propensi.eis.Model.PIProductDetailModel;
import com.propensi.eis.Model.POProductDetailModel;
import com.propensi.eis.Repository.PIPOTransactionDb;
import com.propensi.eis.Repository.PIProductDetailDB;
import com.propensi.eis.Repository.POProductDetailDb;
import com.propensi.eis.Rest.BaseResponse;
import com.propensi.eis.RestModel.PIPOTransForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class PIProductDetailController {

    @Autowired
    PIProductDetailDB piProductDetailDB;

    @Autowired
    POProductDetailDb poProductDetailDB;

    @Autowired
    PIPOTransactionDb PIPODb;

    @GetMapping(value = "/getAllPIProductDetail")
    @ResponseBody
    public BaseResponse<List<PIProductDetailModel>> getAllPIProductDetail(){
        BaseResponse<List<PIProductDetailModel>> response = new BaseResponse<List<PIProductDetailModel>>();
        List<PIProductDetailModel> listPIProductDetail = piProductDetailDB.findAll();
        response.setStatus(200);
        response.setMessage("success");
        response.setResult(listPIProductDetail);
        return response;
    }

    @GetMapping(value = "/piProdukDetail")
    @ResponseBody
    public BaseResponse<List<PIProductDetailModel>> getPiProductDetailForPI(@RequestParam(value = "number") String number){
        BaseResponse<List<PIProductDetailModel>> response = new BaseResponse<List<PIProductDetailModel>>();
        List<PIProductDetailModel> piProdukDetail = piProductDetailDB.findByProformaInvoiceNumber(number);
        if(piProdukDetail == null){
            response.setStatus(404);
            response.setMessage("PI Number Not Found");
        }else{
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(piProdukDetail);
        }
        return response;
    }

}
