package com.propensi.eis.Controller;

import java.util.List;

import com.propensi.eis.Model.VesselModel;
import com.propensi.eis.Repository.VesselDb;
import com.propensi.eis.Rest.BaseResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VesselController {

    @Autowired
    VesselDb vesselDb;

    @GetMapping(value = "/getAllVessel")
    @ResponseBody
    public BaseResponse<List<VesselModel>> getAllVessel() {
        BaseResponse<List<VesselModel>> response = new BaseResponse<List<VesselModel>>();
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(vesselDb.findAll());
        return response;
    }
}