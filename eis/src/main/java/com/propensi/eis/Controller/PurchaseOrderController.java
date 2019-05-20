package com.propensi.eis.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.propensi.eis.Repository.PurchaseOrderDb;
import com.propensi.eis.Repository.ContainerDb;
import com.propensi.eis.Repository.POProductDetailDb;
import com.propensi.eis.Repository.ProductDb;
import com.propensi.eis.Model.ContainerModel;
import com.propensi.eis.Model.POProductDetailModel;
import com.propensi.eis.Model.ProductModel;
import com.propensi.eis.Model.PurchaseOrderModel;
import com.propensi.eis.Rest.BaseResponse;
import com.propensi.eis.RestModel.POForm;
import com.propensi.eis.RestModel.POProductDetailForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
@RequestMapping("/api")
public class PurchaseOrderController {

    @Autowired
    PurchaseOrderDb purchaseOrderDb;

    @Autowired
    ContainerDb containerDb;

    @Autowired
    POProductDetailDb pOProductDetailDb;

    @Autowired
    ProductDb productDb;

    @GetMapping(value = "/getAllPurchaseOrder")
    @ResponseBody
    public BaseResponse<List<PurchaseOrderModel>> getAllPurchaseOrder() {
        BaseResponse<List<PurchaseOrderModel>> response = new BaseResponse<List<PurchaseOrderModel>>();
        List<PurchaseOrderModel> test = (List<PurchaseOrderModel>) purchaseOrderDb.findAll();
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(test);
        return response;
    }

    // Get Spesifik po
    @GetMapping(value = "/po/")
    @ResponseBody
    public BaseResponse<PurchaseOrderModel> getPO(@RequestParam(value = "id") long id) {
        BaseResponse<PurchaseOrderModel> response = new BaseResponse<PurchaseOrderModel>();
        Optional<PurchaseOrderModel> po = purchaseOrderDb.findById(id);
        if (!po.isPresent()) {
            response.setStatus(404);
            response.setMessage("Purchase Order Tidak Ditemukan");
        } else {
            PurchaseOrderModel poresult = po.get();
            response.setStatus(200);
            response.setMessage("Success");
            response.setResult(poresult);
        }
        return response;
    }
    
    // update 
    @PostMapping("/po/update")
    @ResponseBody
    public BaseResponse<POForm> updatePO(@RequestBody POForm pOForm, BindingResult bindingResult, @RequestParam(value="id") long id){
        boolean update = false;
        BaseResponse<POForm> response = new BaseResponse<POForm>();
        System.out.println("supplier lama "+ pOForm.getSupplierName());
        Optional<PurchaseOrderModel> po = purchaseOrderDb.findById(id);
        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("Internal Server Error");
        }
        if (!po.isPresent()) {
            response.setStatus(404);
            response.setMessage("Purchase Order tidak ditemukan");
        }
        else{
            PurchaseOrderModel updatePo = po.get();
            List<POProductDetailModel> oldPOProduct = updatePo.getPurchaseOrder();
            System.out.println("old " + oldPOProduct.size());
            System.out.println("supp lama " + updatePo.getNamaSupplier());
            System.out.println("supp baru " + pOForm.getSupplierName());
            System.out.println("number po lama " + updatePo.getNumber());
            System.out.println("number po baru " + pOForm.getNumber());
            System.out.println("number supp lama " + updatePo.getPoNumSupplier());
            System.out.println("number supp baru" + pOForm.getPoNumSupplier());
            List<POProductDetailForm> newPOProduct = pOForm.getProduct();
            System.out.println("new " + newPOProduct.size());
            // ada tambahan produk detailnya
            if(oldPOProduct.size() <= newPOProduct.size()){
                for(int i = 0; i < newPOProduct.size(); i++){
                    // edit data yang udah ada
                    if((i<=oldPOProduct.size()-1)){
                        System.out.println("id" + i);
                        System.out.println("kesini ga");
                        //product detail
                        POProductDetailModel produk = updatePo.getPurchaseOrder().get(i);    
                        ProductModel prod = productDb.findByName(pOForm.getProduct().get(i).getProduct().getName());
                        ContainerModel cont = containerDb.findByName(pOForm.getProduct().get(i).getContainer().getName());
                        produk.setProduct(prod);
                        produk.setDate(pOForm.getProduct().get(i).getDate());
                        produk.setQuantity(pOForm.getProduct().get(i).getQuantity());
                        produk.setQuantityRemaining(pOForm.getProduct().get(i).getQuantityRemaining());
                        produk.setPricePerUnit(pOForm.getProduct().get(i).getPricePerUnit());
                        produk.setFreightCost(pOForm.getProduct().get(i).getFreightCost());
                        produk.setContainerQuantity(pOForm.getProduct().get(i).getcontainerQuantity()); // ini yg
                                                                                                        // quantity
                                                                                                        // container yg
                                                                                                        // diminta
                        produk.setContainer(cont);
                        produk.setPurchaseOrder(updatePo);
                        System.out.println(update + "1.1");
                        pOProductDetailDb.save(produk);
                        update = true;
                        System.out.println(update + "1");

                    }
                    // create new
                    else if ((oldPOProduct.size() - (i + 1)) < 0) {
                        ProductModel prod = productDb.findByName(pOForm.getProduct().get(i).getProduct().getName());
                        ContainerModel cont = containerDb
                                .findByName(pOForm.getProduct().get(i).getContainer().getName());
                        POProductDetailModel pODetail = new POProductDetailModel();
                        pODetail.setProduct(prod);
                        pODetail.setDate(pOForm.getProduct().get(i).getDate());
                        pODetail.setQuantity(pOForm.getProduct().get(i).getQuantity());
                        pODetail.setQuantityRemaining(pOForm.getProduct().get(i).getQuantityRemaining());
                        pODetail.setPricePerUnit(pOForm.getProduct().get(i).getPricePerUnit());
                        pODetail.setFreightCost(pOForm.getProduct().get(i).getFreightCost());
                        pODetail.setContainerQuantity(pOForm.getProduct().get(i).getcontainerQuantity()); // ini yg
                                                                                                          // quantity
                                                                                                          // container
                                                                                                          // yg diminta
                        // container
                        pODetail.setContainer(cont);
                        // save
                        pODetail.setPurchaseOrder(updatePo);
                        pOProductDetailDb.save(pODetail);
                        prod.getPoProductDetail().add(pODetail);
                        updatePo.getPurchaseOrder().add(pODetail);

                        update = true;
                        System.out.println(update + "2");

                    }
                }
            } else if (oldPOProduct.size() > newPOProduct.size()) {
                ArrayList<POProductDetailModel> delete = new ArrayList<POProductDetailModel>();
                for (int i = 0; i < oldPOProduct.size(); i++) {
                    POProductDetailModel produk = updatePo.getPurchaseOrder().get(i);
                    if ((newPOProduct.size() - (i + 1)) >= 0) {
                        ContainerModel container = containerDb
                                .findByName(pOForm.getProduct().get(i).getContainerName());
                        // product detail
                        ProductModel prod = productDb.findByName(pOForm.getProduct().get(i).getProduct().getName());
                        ContainerModel cont = containerDb
                                .findByName(pOForm.getProduct().get(i).getContainer().getName());
                        produk.setProduct(prod);
                        produk.setDate(pOForm.getProduct().get(i).getDate());
                        produk.setQuantity(pOForm.getProduct().get(i).getQuantity());
                        produk.setQuantityRemaining(pOForm.getProduct().get(i).getQuantityRemaining());
                        produk.setPricePerUnit(pOForm.getProduct().get(i).getPricePerUnit());
                        produk.setFreightCost(pOForm.getProduct().get(i).getFreightCost());
                        produk.setContainerQuantity(pOForm.getProduct().get(i).getcontainerQuantity()); // ini yg
                                                                                                        // quantity
                                                                                                        // container yg
                                                                                                        // diminta
                        produk.setContainer(cont);
                        produk.setPurchaseOrder(updatePo);
                        pOProductDetailDb.save(produk);

                        update = true;
                        System.out.println(update + "3");

                    } else if ((newPOProduct.size() - (i + 1)) < 0) {
                        System.out.println(newPOProduct.size() + " " + i);
                        pOProductDetailDb.delete(produk);
                        delete.add(oldPOProduct.get(i));
                        update = true;
                        System.out.println(update + "4");
                    }
                }
                if (delete.size() > 0) {
                    System.out.println("brp" + delete.size());
                    // for (int i = 0; i < delete.size(); i++) {
                    //     productDb.findByName(pOForm.getProduct().get(i).getProductName()).getPoProductDetail()
                    //             .remove(i);
                    // }
                    updatePo.getPurchaseOrder().removeAll(delete);
                    delete.clear();

                }
            }

        }
        if (update) {
            PurchaseOrderModel updatePo = po.get();
            updatePo.setNumber(pOForm.getNumber());
            updatePo.setDate(pOForm.getDate());
            updatePo.setNamaSupplier(pOForm.getSupplierName());
            updatePo.setShipment(pOForm.getShipment());
            updatePo.setVessel(pOForm.getVessel());
            updatePo.setPoNumSupplier(pOForm.getPoNumSupplier());
            System.out.println("supplier baru " + pOForm.getSupplierName());
            purchaseOrderDb.save(updatePo);
        } else {
            response.setStatus(500);
            response.setMessage("Soethig Wrong");
        }
        // success
        response.setStatus(200);
        response.setMessage("success");
        response.setResult(pOForm);
        return response;
    }

    @PostMapping("/po/add")
    @ResponseBody
    public BaseResponse<PurchaseOrderModel> addPO(@RequestBody POForm pOForm, BindingResult bindingResult) {
        BaseResponse<PurchaseOrderModel> response = new BaseResponse<PurchaseOrderModel>();
        PurchaseOrderModel po = new PurchaseOrderModel();
        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("Internal Server Error");
        } else {
            // purchase order
            po.setNumber(pOForm.getNumber());
            po.setDate(pOForm.getDate());
            po.setNamaSupplier(pOForm.getSupplierName());
            po.setShipment(pOForm.getShipment());
            po.setVessel(pOForm.getVessel());
            po.setPurchaseOrder(new ArrayList<POProductDetailModel>());
            po.setPoNumSupplier(pOForm.getPoNumSupplier());
            purchaseOrderDb.save(po);

            for (int i = 0; i < pOForm.getProduct().size(); i++) {
                // Optional<ProductModel> product =
                // productDb.findById(pOForm.getProduct().get(i).getIdProduct());
                ProductModel product = productDb.findByName(pOForm.getProduct().get(i).getProductName());
                // ContainerModel container =
                // containerDb.findById(pOForm.getProduct().get(i).getContainerId());
                ContainerModel container = containerDb.findByName(pOForm.getProduct().get(i).getContainerName());
                if (container == null) {
                    response.setStatus(404);
                    response.setMessage("Container Not Found");
                    purchaseOrderDb.delete(po);
                    return response;
                } else {
                    // product detail
                    POProductDetailModel pODetail = new POProductDetailModel();
                    // pODetail.setProduct(productDb.findById(pOForm.getProduct().get(i).getIdProduct()).get());
                    pODetail.setProduct(productDb.findByName(pOForm.getProduct().get(i).getProductName()));
                    pODetail.setDate(pOForm.getProduct().get(i).getDate());
                    pODetail.setQuantity(pOForm.getProduct().get(i).getQuantity());
                    pODetail.setQuantityRemaining(pOForm.getProduct().get(i).getQuantityRemaining());
                    pODetail.setPricePerUnit(pOForm.getProduct().get(i).getPricePerUnit());
                    pODetail.setFreightCost(pOForm.getProduct().get(i).getFreightCost());
                    pODetail.setContainerQuantity(pOForm.getProduct().get(i).getcontainerQuantity()); // ini yg quantity
                                                                                                      // container yg
                                                                                                      // diminta
                    
                    //Container
                    pODetail.setPurchaseOrder(po);
                    pODetail.setContainer(container);
                    
                    pOProductDetailDb.save(pODetail);
                    po.getPurchaseOrder().add(pODetail);
                    product.getPoProductDetail().add(pODetail);
                }
            }        
            //success
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(po);
        }
        return response;
    }
     
    
}   
