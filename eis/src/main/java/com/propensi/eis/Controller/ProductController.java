package com.propensi.eis.Controller;

import java.util.List;

import com.propensi.eis.Model.ProductModel;
import com.propensi.eis.Repository.ProductDb;
import com.propensi.eis.Rest.BaseResponse;

import org.hibernate.Hibernate;
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
public class ProductController {

    @Autowired
    ProductDb productDb;

    //get All Product
    @GetMapping(value = "/getAllProduct")
    @ResponseBody
    public BaseResponse<List<ProductModel>> getAllProduct(){
        BaseResponse<List<ProductModel>> response = new BaseResponse<List<ProductModel>>();
        Hibernate.initialize(productDb.findAll());
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(productDb.findAll());
        return response;
    }

    //get by id
    public BaseResponse<ProductModel> getProductById(@RequestParam("id") long id){
        BaseResponse<ProductModel> response = new BaseResponse<ProductModel>();
        if(!productDb.findById(id).isPresent()){
            response.setStatus(404);
            response.setMessage("Product Not Found");
        } else{
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(productDb.findById(id).get());
        }
        return response;
    }

    //get by name
    public BaseResponse<ProductModel> getProductByName(@RequestParam("name") String name){
        BaseResponse<ProductModel> response = new BaseResponse<ProductModel>();
        if(productDb.findByName(name) == null){
            response.setStatus(404);
            response.setMessage("Product Not Found");
        } else{
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(productDb.findByName(name));
        }
        return response;
    }

    //get by PIId
    public BaseResponse<List<ProductModel>> getProductByPIDetail(@RequestParam("id") long id){
        BaseResponse<List<ProductModel>> response = new BaseResponse<List<ProductModel>>();
        if(productDb.findByPiProductDetailId(id) == null){
            response.setStatus(404);
            response.setMessage("Product Not Found");
        }else{
            response.setStatus(200);
            response.setMessage("Success");
            response.setResult(productDb.findByPiProductDetailId(id));
        }
        return response;
    }

    //get by POId
    public BaseResponse<List<ProductModel>> getProductByPODetail(@RequestParam("id") long id){
        BaseResponse<List<ProductModel>> response = new BaseResponse<List<ProductModel>>();
        if(productDb.findByPoProductDetailId(id) == null){
            response.setStatus(404);
            response.setMessage("Product Not Found");
        }else{
            response.setStatus(200);
            response.setMessage("Success");
            response.setResult(productDb.findByPoProductDetailId(id));
        }
        return response;
    }

    //create Product
    @PostMapping(value = "/product/add")
    @ResponseBody
    public BaseResponse<ProductModel> addProduct(@RequestBody ProductModel product, BindingResult bindingResult){
        BaseResponse<ProductModel> response = new BaseResponse<ProductModel>();
        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("Internal Server Error");
        } else {
            productDb.save(product);
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(product);
        }
        return response;
    }

  
}