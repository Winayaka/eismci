package com.propensi.eis.Controller;

import java.util.List;

import com.propensi.eis.Model.ShipmentProductDetailModel;
import com.propensi.eis.Repository.ShipmentProductDetailDb;
import com.propensi.eis.Rest.BaseResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShipmentProductDetaController {

    @Autowired
    ShipmentProductDetailDb shipmentProductDetailDb;

    @GetMapping(value = "/getAllShipmentProductDetail")
    @ResponseBody
    public BaseResponse<List<ShipmentProductDetailModel>> getAllSIdetail() {
        BaseResponse<List<ShipmentProductDetailModel>> response = new BaseResponse<List<ShipmentProductDetailModel>>();
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(shipmentProductDetailDb.findAll());
        return response;
    }

}