package com.propensi.eis.Controller;

import java.util.List;

import com.propensi.eis.Model.ContainerModel;
import com.propensi.eis.Repository.ContainerDb;
import com.propensi.eis.Rest.BaseResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class ContainerController {

    @Autowired
    ContainerDb containerDb;

    @GetMapping(value = "/getAllContainer")
    @ResponseBody
    public BaseResponse<List<ContainerModel>> getAllContainer() {
        BaseResponse<List<ContainerModel>> response = new BaseResponse<List<ContainerModel>>();
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(containerDb.findAll());
        return response;
    }

}