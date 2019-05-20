package com.propensi.eis.Controller;

import java.util.List;
import java.util.Optional;

import com.propensi.eis.Rest.BaseResponse;
import com.propensi.eis.Model.POProductDetailModel;
import com.propensi.eis.Repository.POProductDetailDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class POProductDetailController {

    @Autowired
    POProductDetailDb pOProductDetailDb;

    @GetMapping(value = "/getAllPOProductDetail")
    @ResponseBody
    public BaseResponse<List<POProductDetailModel>> getAllPOProductDetail() {
        BaseResponse<List<POProductDetailModel>> response = new BaseResponse<List<POProductDetailModel>>();
        List<POProductDetailModel> test = (List<POProductDetailModel>) pOProductDetailDb.findAll();
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(test);
        return response;
    }

    @GetMapping(value = "/product")
    @ResponseBody
    public BaseResponse<POProductDetailModel> getPO(@RequestParam(value = "id") long id){
        BaseResponse<POProductDetailModel> response = new BaseResponse<POProductDetailModel>();
        Optional<POProductDetailModel> po = pOProductDetailDb.findById(id);
        if(!po.isPresent()){
            response.setStatus(404);
            response.setMessage("Detail Produk Purchase Order Tidak Ditemukan");
        } else {
            POProductDetailModel poresult = po.get();
            response.setStatus(200);
            response.setMessage("Success");
            response.setResult(poresult);
        }
        return response;
    }

    @GetMapping(value = "/product/po")
       @ResponseBody
       public BaseResponse<POProductDetailModel> getPOProductDetailByPo(@RequestParam(value = "number") String number){
           BaseResponse<POProductDetailModel> response = new BaseResponse<POProductDetailModel>();
           POProductDetailModel poProductDetail = pOProductDetailDb.findByPurchaseOrderNumber(number);
           if(poProductDetail == null){
               response.setStatus(404);
               response.setMessage("Detail Produk Purchase Order Tidak Ditemukan");
           } else {
               response.setStatus(200);
               response.setMessage("Success");
               response.setResult(poProductDetail);
           }
           return response;
       }
}