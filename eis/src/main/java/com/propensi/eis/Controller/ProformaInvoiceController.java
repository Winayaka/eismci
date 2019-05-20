package com.propensi.eis.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.propensi.eis.Model.ProformaInvoiceModel;
import com.propensi.eis.Model.ContainerModel;
import com.propensi.eis.Model.CustomerModel;
import com.propensi.eis.Model.PIProductDetailModel;
import com.propensi.eis.Model.ProductModel;

import com.propensi.eis.Repository.ProformaInvoiceDB;
import com.propensi.eis.Repository.CustomerDb;
import com.propensi.eis.Repository.PaymentMethodDb;
import com.propensi.eis.Repository.ProductDb;
import com.propensi.eis.Repository.ContainerDb;
import com.propensi.eis.Repository.PIProductDetailDB;

import com.propensi.eis.Rest.BaseResponse;
import com.propensi.eis.RestModel.PIForm;

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
public class ProformaInvoiceController {

    @Autowired
    ProformaInvoiceDB proformaInvoiceDB;

    @Autowired
    PIProductDetailDB piProductDetailDB;

    @Autowired
    CustomerDb customerDB;

    @Autowired
    PaymentMethodDb paymentMethodDB;

    @Autowired
    ContainerDb containerDB;

    @Autowired
    ProductDb productDb;

    // get All PI
    @GetMapping(value = "/pi/all")
    @ResponseBody
    public BaseResponse<List<ProformaInvoiceModel>> getAllProformaInvoice() {
        BaseResponse<List<ProformaInvoiceModel>> response = new BaseResponse<List<ProformaInvoiceModel>>();
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(proformaInvoiceDB.findAll());
        return response;
    }

    // get Spesific PI
    @GetMapping(value = "/pi")
    @ResponseBody
    public BaseResponse<ProformaInvoiceModel> getProformaInvoice(@RequestParam("id") long id) {
        BaseResponse<ProformaInvoiceModel> response = new BaseResponse<ProformaInvoiceModel>();
        ProformaInvoiceModel target = proformaInvoiceDB.findById(id).get();
        if (target == null) {
            response.setStatus(404);
            response.setMessage("Proforma Invoice not found");
        } else {
            response.setStatus(200);
            response.setMessage("Success");
            response.setResult(target);
        }
        return response;
    }

    // get pi by customer
    @GetMapping(value = "/pi/cust")
    @ResponseBody
    public BaseResponse<List<ProformaInvoiceModel>> getProformaInvoiceByCustomer(@RequestParam("id") long id) {
        BaseResponse<List<ProformaInvoiceModel>> response = new BaseResponse<List<ProformaInvoiceModel>>();
        List<ProformaInvoiceModel> target = proformaInvoiceDB.findByCustomerId(id);
        if (target == null) {
            response.setStatus(404);
            response.setMessage("Proforma Invoice not found");
        } else {
            response.setStatus(200);
            response.setMessage("Success");
            response.setResult(target);
        }
        return response;
    }

    // add PI
    @PostMapping(value = "/pi/add")
    @ResponseBody
    public BaseResponse<PIForm> addProformaInvoice(@RequestBody PIForm piForm, BindingResult bindingResult) {
        BaseResponse<PIForm> response = new BaseResponse<PIForm>();
        ProformaInvoiceModel newPI = new ProformaInvoiceModel();
        CustomerModel customer = customerDB.findById(piForm.getCustomerID());
        System.out.println(piForm.getNumber());
        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("Internal Server Error");
            response.setResult(piForm);
        } else {
            newPI.setNumber(piForm.getNumber());
            newPI.setDate(piForm.getDate());
            newPI.setCustomer(customer);
            newPI.setCountryOfOrigin(piForm.getCountryOfOrigin());
            newPI.setPortOfLoading(piForm.getPortOfLoading());
            newPI.setPortOfDestination(piForm.getPortOfDestination());
            newPI.setFinalDestination(piForm.getFinalDestination());
            newPI.setDeliveryTerm(piForm.getDeliveryTerm());
            newPI.setPaymentTerm(piForm.getPaymentTerm());
            newPI.setTermAndCondition(piForm.getTermAndCondition());
            newPI.setEtaDate(piForm.getEtaDate());
            newPI.setEtdDate(piForm.getEtdDate());
            newPI.setCompleted(false);

            proformaInvoiceDB.save(newPI);

            // Product Detail
            newPI.setDetailProduct(new ArrayList<PIProductDetailModel>());
            for (int i = 0; i < piForm.getProductDetailList().size(); i++) {
                ProductModel product = productDb.findByName(piForm.getProductDetailList().get(i).getProductName());
                ContainerModel container = containerDB
                        .findByName(piForm.getProductDetailList().get(i).getContainerName());
                PIProductDetailModel PIProdukDetail = new PIProductDetailModel();

                PIProdukDetail.setProduct(product);
                PIProdukDetail.setQuantity(piForm.getProductDetailList().get(i).getQuantity());
                PIProdukDetail.setFreightCost(piForm.getProductDetailList().get(i).getFreightCost());
                PIProdukDetail.setPricePerUnit(piForm.getProductDetailList().get(i).getPricePerUnit());
                PIProdukDetail.setQuantitySent(piForm.getProductDetailList().get(i).getQuantitySent());
                PIProdukDetail.setContainerQuantity(piForm.getProductDetailList().get(i).getContainerQuantity());
                PIProdukDetail.setContainer(container);
                PIProdukDetail.setProformaInvoice(newPI);
                piProductDetailDB.save(PIProdukDetail);
                product.getPiProductDetail().add(PIProdukDetail);
                newPI.getDetailProduct().add(PIProdukDetail);
                piProductDetailDB.flush();
            }
            customer.getPI().add(newPI);
            response.setStatus(200);
            response.setMessage("Input Success");
            response.setResult(piForm);
        }

        return response;
    }

    // update PI
    @PostMapping(value = "/pi/update")
    @ResponseBody
    public BaseResponse<PIForm> updateProformaInvoice(@RequestParam("id") long id, @RequestBody PIForm piForm,
            BindingResult bindingResult) {
        BaseResponse<PIForm> response = new BaseResponse<PIForm>();
        Optional<ProformaInvoiceModel> oldPI = proformaInvoiceDB.findById(id);
        System.out.println("Check: " + oldPI.isPresent());
        System.out.println(oldPI.get());
        CustomerModel customer = customerDB.findById(piForm.getCustomerID());

        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("Internal Server Error");
        }
        if (!oldPI.isPresent()) {
            response.setStatus(404);
            response.setMessage("Proforma Invoice Not Found");
        } else {
            oldPI.get().setNumber(piForm.getNumber());
            oldPI.get().setDate(piForm.getDate());
            oldPI.get().setCustomer(customer);
            oldPI.get().setCountryOfOrigin(piForm.getCountryOfOrigin());
            oldPI.get().setPortOfLoading(piForm.getPortOfLoading());
            oldPI.get().setPortOfDestination(piForm.getPortOfDestination());
            oldPI.get().setFinalDestination(piForm.getFinalDestination());
            oldPI.get().setDeliveryTerm(piForm.getDeliveryTerm());
            oldPI.get().setPaymentTerm(piForm.getPaymentTerm());
            oldPI.get().setTermAndCondition(piForm.getTermAndCondition());
            oldPI.get().setEtaDate(piForm.getEtaDate());
            oldPI.get().setEtdDate(piForm.getEtdDate());

            proformaInvoiceDB.save(oldPI.get());
            System.out.println("Ukuran Product Detail PIForm " + piForm.getProductDetailList().size());
            for (int i = 0; i < piForm.getProductDetailList().size(); i++) {
                System.out.println("ID " + piForm.getProductDetailList().get(i).getId());
            }
            System.out.println("Ukuran Product Detail DB " + oldPI.get().getDetailProduct().size());
            for (PIProductDetailModel produk : oldPI.get().getDetailProduct()) {
                System.out.println("ID " + produk.getId());
            }
            System.out.println("After Deletion");
            System.out.println("Ukuran Product Detail DB " + oldPI.get().getDetailProduct().size());
            for (PIProductDetailModel produk : oldPI.get().getDetailProduct()) {
                System.out.println("ID " + produk.getId());
            }
            System.out.println("Ukuran Product Detail PIForm " + piForm.getProductDetailList().size());
            for (int i = 0; i < piForm.getProductDetailList().size(); i++) {
                System.out.println("ID " + piForm.getProductDetailList().get(i).getId());
            }
            System.out.println("END");
            piProductDetailDB.deleteAll(oldPI.get().getDetailProduct());

            // Update with New Product Detail
            for (int i = 0; i < piForm.getProductDetailList().size(); i++) {
                if (piForm.getProductDetailList().get(i).getId() != 0) {
                    System.out.println("TEST A");
                    PIProductDetailModel PIProdukDetail = new PIProductDetailModel();
                    ProductModel product = productDb
                            .findByName(piForm.getProductDetailList().get(i).getProduct().getName());
                    ContainerModel container = containerDB
                            .findByName(piForm.getProductDetailList().get(i).getContainer().getName());

                    PIProdukDetail.setId(piForm.getProductDetailList().get(i).getId());
                    PIProdukDetail.setProduct(product);
                    PIProdukDetail.setQuantity(piForm.getProductDetailList().get(i).getQuantity());
                    PIProdukDetail.setFreightCost(piForm.getProductDetailList().get(i).getFreightCost());
                    PIProdukDetail.setPricePerUnit(piForm.getProductDetailList().get(i).getPricePerUnit());
                    PIProdukDetail.setQuantitySent(piForm.getProductDetailList().get(i).getQuantitySent());
                    PIProdukDetail.setContainerQuantity(piForm.getProductDetailList().get(i).getContainerQuantity());
                    PIProdukDetail.setContainer(container);
                    PIProdukDetail.setProformaInvoice(oldPI.get());

                    piProductDetailDB.save(PIProdukDetail);
                    product.getPiProductDetail().add(PIProdukDetail);
                    oldPI.get().getDetailProduct().add(PIProdukDetail);
                    piProductDetailDB.flush();

                } else {
                    System.out.println("TEST B");
                    PIProductDetailModel NewPIProdukDetail = new PIProductDetailModel();
                    ProductModel product = productDb
                            .findByName(piForm.getProductDetailList().get(i).getProduct().getName());
                    ContainerModel container = containerDB
                            .findByName(piForm.getProductDetailList().get(i).getContainer().getName());

                    NewPIProdukDetail.setProduct(product);
                    NewPIProdukDetail.setQuantity(piForm.getProductDetailList().get(i).getQuantity());
                    NewPIProdukDetail.setFreightCost(piForm.getProductDetailList().get(i).getFreightCost());
                    NewPIProdukDetail.setPricePerUnit(piForm.getProductDetailList().get(i).getPricePerUnit());
                    NewPIProdukDetail.setQuantitySent(piForm.getProductDetailList().get(i).getQuantitySent());
                    NewPIProdukDetail.setContainerQuantity(piForm.getProductDetailList().get(i).getContainerQuantity());
                    NewPIProdukDetail.setContainer(container);
                    NewPIProdukDetail.setProformaInvoice(oldPI.get());

                    piProductDetailDB.save(NewPIProdukDetail);
                    product.getPiProductDetail().add(NewPIProdukDetail);
                    oldPI.get().getDetailProduct().add(NewPIProdukDetail);
                    piProductDetailDB.flush();

                }
            }
            customer.getPI().add(oldPI.get());
            response.setStatus(200);
            response.setMessage("Input Success");
            response.setResult(piForm);
        }
        return response;
    }
}