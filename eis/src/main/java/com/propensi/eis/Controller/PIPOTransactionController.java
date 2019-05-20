package com.propensi.eis.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.propensi.eis.Model.PIPOTransactionModel;
import com.propensi.eis.Model.PIProductDetailModel;
import com.propensi.eis.Model.POProductDetailModel;
import com.propensi.eis.Model.ProductModel;
import com.propensi.eis.Repository.PIPOTransactionDb;
import com.propensi.eis.Repository.PIProductDetailDB;
import com.propensi.eis.Repository.POProductDetailDb;
import com.propensi.eis.Rest.BaseResponse;
import com.propensi.eis.RestModel.PIPOTransForm;
import com.propensi.eis.RestModel.PIPOTransFormOut;

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
public class PIPOTransactionController {

    @Autowired
    PIPOTransactionDb pipoDb;

    @Autowired
    PIProductDetailDB piProdukDetailDb;

    @Autowired
    POProductDetailDb poProdukDetailDb;

    // get all
    @GetMapping(value = "/getAllPIPOTrans")
    @ResponseBody
    public BaseResponse<List<PIPOTransFormOut>> getAllPipoTrans() {
        BaseResponse<List<PIPOTransFormOut>> response = new BaseResponse<List<PIPOTransFormOut>>();
        List<PIPOTransactionModel> allPIPO = pipoDb.findAll();
        Hibernate.initialize(allPIPO);
        List<PIPOTransFormOut> outPIPO = new ArrayList<PIPOTransFormOut>();
        for (int i = 0; i < allPIPO.size(); i++) {
            PIPOTransFormOut output = new PIPOTransFormOut();
            PIProductDetailModel piDetail = allPIPO.get(i).getPiProdukDetail();
            Hibernate.initialize(piDetail);
            Hibernate.initialize(allPIPO.get(i));
            Hibernate.initialize(allPIPO.get(i).getPoProdukDetail().getProduct());
            output.setQuantity(allPIPO.get(i).getQuantity());
            output.setPIPOId(allPIPO.get(i).getId());
            output.setPINum(piDetail.getProformaInvoice().getNumber());
            output.setPONum(allPIPO.get(i).getPoProdukDetail().getPurchaseOrder().getNumber());
            output.setProductName(piDetail.getProduct().getName());
            output.setGrossWeight(piDetail.getProduct().getGrossWeight());
            output.setNetWeight(piDetail.getProduct().getNetWeight());
            output.setproductUnit(piDetail.getProduct().getProductUnit());
            output.setProductDesc(piDetail.getProduct().getProductDesc());
            output.setProductId(piDetail.getProduct().getId());
            output.setQuantityLeft(piDetail.getProduct().getQuantityLeft());
            outPIPO.add(output);
        }
        Hibernate.initialize(outPIPO);
        response.setStatus(200);
        response.setMessage("SUCCESS");
        response.setResult(outPIPO);
        return response;
    }

    // get all
    @GetMapping(value = "/get/pipotrans")
    @ResponseBody
    public BaseResponse<List<PIPOTransFormOut>> getPipoTrans(@RequestParam("product") String productName) {
        BaseResponse<List<PIPOTransFormOut>> response = new BaseResponse<List<PIPOTransFormOut>>();
        List<PIPOTransactionModel> allPIPO = pipoDb.findByPoProdukDetailProductName(productName);
        List<PIPOTransFormOut> outPIPO = new ArrayList<PIPOTransFormOut>();
        for (int i = 0; i < allPIPO.size(); i++) {
            PIPOTransFormOut output = new PIPOTransFormOut();
            PIProductDetailModel piDetail = allPIPO.get(i).getPiProdukDetail();
            Hibernate.initialize(piDetail);
            Hibernate.initialize(allPIPO.get(i));
            Hibernate.initialize(allPIPO.get(i).getPoProdukDetail().getProduct());
            output.setQuantity(allPIPO.get(i).getQuantity());
            output.setPIPOId(allPIPO.get(i).getId());
            output.setPINum(piDetail.getProformaInvoice().getNumber());
            output.setPONum(allPIPO.get(i).getPoProdukDetail().getPurchaseOrder().getNumber());
            output.setProductName(piDetail.getProduct().getName());
            output.setGrossWeight(piDetail.getProduct().getGrossWeight());
            output.setNetWeight(piDetail.getProduct().getNetWeight());
            output.setproductUnit(piDetail.getProduct().getProductUnit());
            output.setProductDesc(piDetail.getProduct().getProductDesc());
            output.setProductId(piDetail.getProduct().getId());
            output.setQuantityLeft(piDetail.getProduct().getQuantityLeft());
            outPIPO.add(output);
        }
        Hibernate.initialize(outPIPO);
        response.setStatus(200);
        response.setMessage("SUCCESS");
        response.setResult(outPIPO);
        return response;
    }

    // get by id
    @GetMapping(value = "/get/pipoTrans")
    @ResponseBody
    public BaseResponse<PIPOTransFormOut> getPIPOTrans(@RequestParam(value = "id") Long id) {
        BaseResponse<PIPOTransFormOut> response = new BaseResponse<PIPOTransFormOut>();
        Optional<PIPOTransactionModel> pipoTrans = pipoDb.findById(id);
        if (!pipoTrans.isPresent()) {
            response.setStatus(404);
            response.setMessage("Transaction Not Found");
        } else {
            PIPOTransFormOut pipoTransFormOut = new PIPOTransFormOut();
            ProductModel product = pipoTrans.get().getPoProdukDetail().getProduct();
            pipoTransFormOut.setPIPOId(pipoTrans.get().getId());
            pipoTransFormOut.setPINum(pipoTrans.get().getPiProdukDetail().getProformaInvoice().getNumber());
            pipoTransFormOut.setPONum(pipoTrans.get().getPoProdukDetail().getPurchaseOrder().getNumber());
            pipoTransFormOut.setQuantity(pipoTrans.get().getQuantity());
            pipoTransFormOut.setProductName(product.getName());
            response.setStatus(200);
            response.setMessage("SUCCESS");
            response.setResult(pipoTransFormOut);
        }
        return response;
    }

    // Create PIPOTrans
    @PostMapping(value = "/add/pipoTrans")
    @ResponseBody
    public BaseResponse<PIPOTransactionModel> addPIPOTrans(@RequestBody PIPOTransForm pipo,
            BindingResult bindingResult) {
        BaseResponse<PIPOTransactionModel> response = new BaseResponse<PIPOTransactionModel>();
        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("Internal Server Error");
        } else {
            PIPOTransactionModel pipoSave = new PIPOTransactionModel();
            Optional<PIProductDetailModel> piProdDetail = piProdukDetailDb.findById(pipo.getPiProductDetailId());
            Optional<POProductDetailModel> poProdDetail = poProdukDetailDb.findById(pipo.getPoProductDetailId());
            System.out.println(pipo.getPoProductDetailId());
            System.out.println(pipo.getPiProductDetailId());
            System.out.println(pipo.getQuantity());
            if (!piProdDetail.isPresent()) {
                response.setStatus(404);
                response.setMessage("Proforma Invoice Product Not Found");
            } else if (!poProdDetail.isPresent()) {
                response.setStatus(404);
                response.setMessage("Purchase Order Product Not Found");
            } else {
                System.out.println(!piProdDetail.isPresent());
                pipoSave.setPiProdukDetail(piProdDetail.get());

                pipoSave.setPoProdukDetail(poProdDetail.get());
                pipoSave.setQuantity(pipo.getQuantity());
                pipoDb.save(pipoSave);
                piProdDetail.get().getPIPOTransaction().add(pipoSave);
                poProdDetail.get().getPIPOtransaction().add(pipoSave);

                response.setStatus(200);
                response.setMessage("SUCCESS");
                response.setResult(pipoSave);
            }
        }
        return response;
    }

}