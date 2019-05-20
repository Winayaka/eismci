package com.propensi.eis.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.propensi.eis.Model.InvoiceModel;
import com.propensi.eis.Model.PIPOTransactionModel;
import com.propensi.eis.Model.ProductModel;
import com.propensi.eis.Model.ProformaInvoiceModel;
import com.propensi.eis.Model.PurchaseOrderModel;
import com.propensi.eis.Model.ShipmentDocumentModel;
import com.propensi.eis.Model.ShipmentProductDetailModel;
import com.propensi.eis.Model.ShippingInstructionModel;
import com.propensi.eis.Model.StaffModel;
import com.propensi.eis.Model.VesselModel;
import com.propensi.eis.Repository.InvoiceDb;
import com.propensi.eis.Repository.PIPOTransactionDb;
import com.propensi.eis.Repository.PIProductDetailDB;
import com.propensi.eis.Repository.POProductDetailDb;
import com.propensi.eis.Repository.ProformaInvoiceDB;
import com.propensi.eis.Repository.PurchaseOrderDb;
import com.propensi.eis.Repository.ShipmentProductDetailDb;
import com.propensi.eis.Repository.ShippingInstructionDb;
import com.propensi.eis.Repository.StaffDb;
import com.propensi.eis.Repository.VesselDb;
import com.propensi.eis.Rest.BaseResponse;
import com.propensi.eis.RestModel.PIPOTransFormOut;
import com.propensi.eis.RestModel.SIForm;
import com.propensi.eis.RestModel.SIOutput;
import com.propensi.eis.RestModel.SIOutputCustomer;
import com.propensi.eis.Service.SIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class ShippingInstructionController {

    @Autowired
    ShippingInstructionDb SIDb;

    @Autowired
    ProformaInvoiceDB PIDb;

    @Autowired
    PurchaseOrderDb PODb;

    @Autowired
    PIProductDetailDB piProductDetailDb;

    @Autowired
    POProductDetailDb poProductDetailDb;

    @Autowired
    VesselDb vesselDb;

    @Autowired
    StaffDb staffDb;

    @Autowired
    ShipmentProductDetailDb siProdukDb;

    @Autowired
    SIService siService;

    @Autowired
    PIPOTransactionDb PIPODb;

    @Autowired
    InvoiceDb invoiceDb;

    // get all
    @GetMapping(value = "/getAllSI")
    @ResponseBody
    public BaseResponse<List<ShippingInstructionModel>> getAllSI() {
        BaseResponse<List<ShippingInstructionModel>> response = new BaseResponse<List<ShippingInstructionModel>>();
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(SIDb.findAll());
        return response;
    }

    @GetMapping(value = "/get/si")
    @ResponseBody
    public BaseResponse<List<ShippingInstructionModel>> getSiByPi(@RequestParam(value = "PIId") long PIId) {
        BaseResponse<List<ShippingInstructionModel>> response = new BaseResponse<List<ShippingInstructionModel>>();
        Optional<ProformaInvoiceModel> pi = PIDb.findById(PIId);
        if (!pi.isPresent()) {
            response.setStatus(404);
            response.setMessage("Shipping Instruction Tidak Ditemukan");
        } else {
            List<ShippingInstructionModel> listSI = SIDb.findByProformaInvoiceId(PIId);
            if (listSI == null) {
                response.setStatus(404);
                response.setMessage("Shipping Instruction Tidak Ditemukan");
            } else {
                // System.out.println(listSI.get(0).getId()+" asdasdasdsad ");
                response.setStatus(200);
                response.setMessage("Success");
                response.setResult(listSI);
            }
        }
        return response;
    }

    // Get SI berdasarkan staff
    @GetMapping(value = "/get/si/assigned")
    @ResponseBody
    public BaseResponse<List<ShippingInstructionModel>> getSiByStaff(@RequestParam(value = "StaffId") long StaffId) {
        BaseResponse<List<ShippingInstructionModel>> response = new BaseResponse<List<ShippingInstructionModel>>();
        Optional<StaffModel> staff = staffDb.findById(StaffId);
        if (!staff.isPresent()) {
            response.setStatus(404);
            response.setMessage("Shipping Instruction Tidak Ditemukan");
        } else {
            List<ShippingInstructionModel> listSI = SIDb.findByStaffStaffId(StaffId);
            if (listSI == null) {
                response.setStatus(404);
                response.setMessage("Shipping Instruction Tidak Ditemukan");
            } else {
                response.setStatus(200);
                response.setMessage("Success");
                response.setResult(listSI);
            }
        }
        return response;
    }

    // Get Spesifik SI
    @GetMapping(value = "/si")
    @ResponseBody
    public BaseResponse<SIOutput> getSI(@RequestParam(value = "id") long id) {
        BaseResponse<SIOutput> response = new BaseResponse<SIOutput>();
        SIOutput output = new SIOutput();
        Optional<ShippingInstructionModel> si = SIDb.findById(id);
        if (!si.isPresent()) {
            response.setStatus(404);
            response.setMessage("Shipping Instruction Tidak Ditemukan");
        } else {
            ShippingInstructionModel SIresult = si.get();
            String piNum = SIresult.getProformaInvoice().getNumber();
            String poNum = SIresult.getPurchaseOrder().getNumber();

            // output
            output.setShipmentId(SIresult.getId());
            output.setPiNum(piNum);
            output.setPiid(SIresult.getProformaInvoice().getId());
            output.setPoid(SIresult.getPurchaseOrder().getId());
            output.setSiNum(SIresult.getNumber());
            output.setLiner(SIresult.getLiner());
            output.setEta(SIresult.getETA());
            output.setEtd(SIresult.getETD());
            output.setPod(SIresult.getPoD());
            output.setPol(SIresult.getPoL());
            output.setShipmentStatus(SIresult.getShipmentStatus());
            output.setFinalDestination(SIresult.getFinalDestination());
            output.setOpenDate(SIresult.getOpenDate());
            output.setOpenTime(SIresult.getOpenTime());
            output.setCloseDate(SIresult.getCloseDate());
            output.setCloseTime(SIresult.getCloseTime());
            output.setInvoiceNum(SIresult.getInvoice());
            output.setVesselName(SIresult.getVessel().getName());
            output.setConnectingVessel(SIresult.getVessel().getConnectingVessel());
            output.setProdukDetail(new ArrayList<PIPOTransFormOut>());
            output.setPaymentStatus(SIresult.getPaymentStatus());
            output.setStaff(SIresult.getStaff().getName());
            output.setBookingConfirmation(SIresult.getBookingConfirmation());
            output.setSiDocument(SIresult.getSiDocument());
            for (int i = 0; i < SIresult.getShipmentProductDetail().size(); i++) {
                ShipmentProductDetailModel siDetailRes = SIresult.getShipmentProductDetail().get(i);
                ProductModel productRes = siDetailRes.getPIPOTransaction().getPiProdukDetail().getProduct();
                PIPOTransFormOut siDetail = new PIPOTransFormOut();
                siDetail.setPIPOId(siDetailRes.getPIPOTransaction().getId());
                siDetail.setQuantity(siDetailRes.getquantity());
                siDetail.setProductName(productRes.getName());
                siDetail.setProductId(productRes.getId());
                siDetail.setGrossWeight(productRes.getGrossWeight());
                siDetail.setNetWeight(productRes.getNetWeight());
                siDetail.setproductUnit(productRes.getProductUnit());
                siDetail.setProductDesc(productRes.getProductDesc());
                siDetail.setSiDetailId(siDetailRes.getId());
                siDetail.setPONum(siDetailRes.getPIPOTransaction().getPoProdukDetail().getPurchaseOrder().getNumber());
                output.getProdukDetail().add(siDetail);
            }

            // response
            response.setStatus(200);
            response.setMessage("Success");
            response.setResult(output);
        }
        return response;
    }

    // add new SI
    @PostMapping("/si/add")
    @ResponseBody
    public BaseResponse<ShippingInstructionModel> addSI(@RequestBody SIForm siForm, BindingResult bindingResult) {
        BaseResponse<ShippingInstructionModel> response = new BaseResponse<ShippingInstructionModel>();

        // crete new
        ShippingInstructionModel newSI = new ShippingInstructionModel();
        // InvoiceModel invoice = new InvoiceModel();
        ShipmentDocumentModel shippingDocument = new ShipmentDocumentModel();

        // find in DB
        Optional<PurchaseOrderModel> POId = PODb.findById((Long) siForm.getPurchaseOrderId());
        Optional<ProformaInvoiceModel> PI = PIDb.findById(siForm.getProformaInvoiceId());
        Optional<StaffModel> staff = staffDb.findById(siForm.getStaff());

        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("Internal Server Error");
        } else if (!POId.isPresent()) {
            response.setStatus(404);
            response.setMessage("Purchase Order Not Found");
        } else if (!staff.isPresent()) {
            response.setStatus(404);
            response.setMessage("Staff Not Found");
        } else if (!PI.isPresent()) {
            response.setStatus(404);
            response.setMessage("Proforma Invoice Not Found");
        } else {
            // vessel
            VesselModel vessel = new VesselModel();
            vessel.setName(siForm.getVesselName());
            vessel.setConnectingVessel(siForm.getConnectingVessel());

            // SI
            newSI.setVessel(vessel);
            newSI.setNumber(siForm.getNumber());
            newSI.setProformaInvoice(PI.get());// cari findbyId
            newSI.setPurchaseOrder(POId.get());// find byid
            // invoiceDb.save(invoice); // save invoice
            // newSI.setInvoice(invoice); //create new invoice
            newSI.setLiner(siForm.getLiner());
            newSI.setOpenTime(siForm.getOpenTime());
            newSI.setOpenDate(siForm.getOpenDate());
            newSI.setCloseDate(siForm.getCloseDate());
            newSI.setCloseTime(siForm.getCloseTime());
            newSI.setPoD(siForm.getPoD());
            newSI.setPoL(siForm.getPoL());
            newSI.setETA(siForm.getETA());
            newSI.setETD(siForm.getEtdep());
            newSI.setFinalDestination(siForm.getFinalDestination());
            newSI.setShipmentStatus(siForm.getStatus());
            newSI.setStaff(staff.get());
            newSI.setPaymentStatus(siForm.getPaymentStatus());
            // newSI.setShipmentPriority(siForm.getShipmentPriority());
            newSI.setCreateDate(LocalDate.now());

            // save to DB
            vesselDb.save(vessel);
            SIDb.save(newSI);

            // add to pi list
            PI.get().getShippingInstruction().add(newSI);

            // Set SI produk detail
            newSI.setShipmentProductDetail(new ArrayList<ShipmentProductDetailModel>());
            for (int i = 0; i < siForm.getProduct().size(); i++) {
                long PIPOId = siForm.getProduct().get(i).getPIPOId();
                Optional<PIPOTransactionModel> detailPIPO = PIPODb.findById(PIPOId);
                if (!detailPIPO.isPresent()) {
                    System.out.println("PIPO ga kje temu" + detailPIPO.isPresent());
                    response.setStatus(404);
                    response.setMessage("Transaction Not Found");
                    // invoiceDb.delete(newSI.getInvoice());
                    SIDb.delete(newSI);
                    vesselDb.delete(vessel);
                    return response;
                } else if (!siService.calculateQuantity(siForm.getProduct().get(i).getQuantity(),
                        detailPIPO.get().getPiProdukDetail(), detailPIPO.get().getPoProdukDetail(),
                        detailPIPO.get().getQuantity())) {
                    System.out.println("Quantity Salah");
                    response.setStatus(404);
                    response.setMessage("Quantity Error, Please Cek the Item Quantity");
                    SIDb.delete(newSI);
                    vesselDb.delete(vessel);
                    return response;
                } else {
                    ShipmentProductDetailModel SIProdukDetail = new ShipmentProductDetailModel();
                    SIProdukDetail.setPIPOTransaction(detailPIPO.get());
                    SIProdukDetail.setquantity(siForm.getProduct().get(i).getQuantity());
                    SIProdukDetail.setShippingInstruction(newSI);
                    siProdukDb.save(SIProdukDetail);
                    newSI.getShipmentProductDetail().add(SIProdukDetail);
                    detailPIPO.get().getShippingDetail().add(SIProdukDetail);
                    siProdukDb.flush();
                }
            }
            SIDb.save(newSI);
            // success
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(newSI);
        }
        return response;
    }

    // Update SI
    @PostMapping("/si/update")
    @ResponseBody
    public BaseResponse<ShippingInstructionModel> updateSI(@RequestParam(value = "id") long id,
            @RequestBody SIForm siForm, BindingResult bindingResult) {
        boolean update = false;
        BaseResponse<ShippingInstructionModel> response = new BaseResponse<ShippingInstructionModel>();
        Optional<ShippingInstructionModel> si = SIDb.findById(id);
        System.out.println(siForm.getPurchaseOrderId() + " <=======");
        Optional<PurchaseOrderModel> POId = PODb.findById(siForm.getPurchaseOrderId());
        Optional<StaffModel> staff = staffDb.findById(siForm.getStaff());
        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("Internal Server Error");
        }
        if (!POId.isPresent()) {
            response.setStatus(404);
            response.setMessage("Purchase Order Not Found");
        }
        if (!staff.isPresent()) {
            response.setStatus(404);
            response.setMessage("Staff Not Found");
        }
        if (!si.isPresent()) {
            response.setStatus(404);
            response.setMessage("Shipping Insruction Not Found");
        } else {
            Optional<ProformaInvoiceModel> pi = PIDb.findById(siForm.getProformaInvoiceId());
            ShippingInstructionModel uptSI = si.get();
            if (!pi.isPresent()) {
                response.setStatus(404);
                response.setMessage("Proforma Invoice Not Found");
            } else {
                // Set SI produk detail
                List<ShipmentProductDetailModel> oldSIdetail = uptSI.getShipmentProductDetail();
                System.out.println(siForm.getProduct().get(0).getQuantity());
                if (oldSIdetail.size() <= siForm.getProduct().size()) {
                    for (int i = 0; i < siForm.getProduct().size(); i++) {
                        long PIPOId = siForm.getProduct().get(i).getPIPOId();
                        System.out.println(PIPOId);
                        Optional<PIPOTransactionModel> detailPIPO = PIPODb.findById(PIPOId);
                        if ((oldSIdetail.size() - (i + 1)) >= 0) {
                            siService.reverseCalculateQuantity(oldSIdetail.get(i).getquantity(),
                                    oldSIdetail.get(i).getPIPOTransaction().getPiProdukDetail(),
                                    oldSIdetail.get(i).getPIPOTransaction().getPoProdukDetail());
                            if (siService.calculateQuantity(siForm.getProduct().get(i).getQuantity(),
                                    detailPIPO.get().getPiProdukDetail(), detailPIPO.get().getPoProdukDetail(),
                                    detailPIPO.get().getQuantity())) {
                                oldSIdetail.get(i).setPIPOTransaction(detailPIPO.get());
                                oldSIdetail.get(i).setquantity(siForm.getProduct().get(i).getQuantity());
                                siProdukDb.save(oldSIdetail.get(i));
                                update = true;
                            } else {
                                response.setStatus(500);
                                response.setMessage(
                                        "Quantity Error, Please Cek the Item Quantity or Max Quantity Sent");
                                siService.calculateQuantity(oldSIdetail.get(i).getquantity(),
                                        oldSIdetail.get(i).getPIPOTransaction().getPiProdukDetail(),
                                        oldSIdetail.get(i).getPIPOTransaction().getPoProdukDetail(),
                                        detailPIPO.get().getQuantity());
                                // tambahin dia ngebalikin isinya
                                return response;
                            }
                        } else if ((oldSIdetail.size() - (i + 1)) < 0) {
                            if (siService.calculateQuantity(siForm.getProduct().get(i).getQuantity(),
                                    detailPIPO.get().getPiProdukDetail(), detailPIPO.get().getPoProdukDetail(),
                                    detailPIPO.get().getQuantity())) {
                                ShipmentProductDetailModel SIDetail = new ShipmentProductDetailModel();
                                SIDetail.setPIPOTransaction(detailPIPO.get());
                                SIDetail.setquantity(siForm.getProduct().get(i).getQuantity());
                                SIDetail.setShippingInstruction(uptSI);
                                siProdukDb.save(SIDetail);
                                // add to list
                                uptSI.getShipmentProductDetail().add(SIDetail);
                                detailPIPO.get().getShippingDetail().add(SIDetail);
                                update = true;
                            } else {
                                response.setStatus(500);
                                response.setMessage("Quantity Error, Please Cek the Item Quantity");
                                // tambahin dia ngebalikin isinya
                                return response;
                            }
                        } else {
                            response.setStatus(500);
                            response.setMessage("Something Wrong");
                            // tambahin dia ngebalikin isinya
                            return response;
                        }
                    }
                } else if (oldSIdetail.size() > siForm.getProduct().size()) {
                    List<ShipmentProductDetailModel> delete = new ArrayList<ShipmentProductDetailModel>();
                    for (int i = 0; i < oldSIdetail.size(); i++) {
                        if ((siForm.getProduct().size() - (i + 1)) >= 0) {
                            long PIPOId = siForm.getProduct().get(i).getPIPOId();
                            Optional<PIPOTransactionModel> detailPIPO = PIPODb.findById(PIPOId);
                            System.out.println("pipoId: " + (PIPOId));
                            System.out.println(detailPIPO.get().getQuantity());
                            siService.reverseCalculateQuantity(oldSIdetail.get(i).getquantity(),
                                    oldSIdetail.get(i).getPIPOTransaction().getPiProdukDetail(),
                                    oldSIdetail.get(i).getPIPOTransaction().getPoProdukDetail());
                            if (siService.calculateQuantity(siForm.getProduct().get(i).getQuantity(),
                                    detailPIPO.get().getPiProdukDetail(), detailPIPO.get().getPoProdukDetail(),
                                    detailPIPO.get().getQuantity())) {
                                oldSIdetail.get(i).setPIPOTransaction(detailPIPO.get());
                                oldSIdetail.get(i).setquantity(siForm.getProduct().get(i).getQuantity());
                                siProdukDb.save(oldSIdetail.get(i));
                                update = true;
                            } else {
                                response.setStatus(500);
                                response.setMessage("Quantity Error, Please Cek the Item Quantity");
                                siService.calculateQuantity(oldSIdetail.get(i).getquantity(),
                                        oldSIdetail.get(i).getPIPOTransaction().getPiProdukDetail(),
                                        oldSIdetail.get(i).getPIPOTransaction().getPoProdukDetail(),
                                        detailPIPO.get().getQuantity());
                                // tambahin dia ngebalikin isinya
                                return response;
                            }
                        } else if ((siForm.getProduct().size() - (i + 1)) < 0) {
                            Optional<PIPOTransactionModel> PIPO = PIPODb
                                    .findById(oldSIdetail.get(i).getPIPOTransaction().getId());
                            siService.reverseCalculateQuantity(oldSIdetail.get(i).getquantity(),
                                    oldSIdetail.get(i).getPIPOTransaction().getPiProdukDetail(),
                                    oldSIdetail.get(i).getPIPOTransaction().getPoProdukDetail());
                            delete.add(oldSIdetail.get(i));

                            System.out.println("eh kedelet");
                            System.out.println(
                                    oldSIdetail.get(i).getPIPOTransaction().getId() + " <=======id piponya segini");
                            siProdukDb.delete(oldSIdetail.get(i));
                            System.out
                                    .println(oldSIdetail.get(i).getPIPOTransaction().getId() + " <======= masih ada?");

                            update = true;
                        } else {
                            response.setStatus(500);
                            response.setMessage("Something Wrong");
                            siService.calculateQuantity(oldSIdetail.get(i).getquantity(),
                                    oldSIdetail.get(i).getPIPOTransaction().getPiProdukDetail(),
                                    oldSIdetail.get(i).getPIPOTransaction().getPoProdukDetail(),
                                    oldSIdetail.get(i).getPIPOTransaction().getQuantity());
                            // tambahin dia ngebalikin isinya
                            // delete dari list detailPIPO
                            return response;
                        }
                    }
                    if (delete.size() > 0) {
                        for (int i = (delete.size() - 1); i < 0; i--) {
                            // System.out.println(PIPODb.findById(delete.get(i).getPIPOTransaction().getId()).get().getShippingDetail().size());

                            PIPODb.findById(delete.get(i).getPIPOTransaction().getId()).get().getShippingDetail()
                                    .removeAll(delete);
                            // System.out.println("Salah DI SIni Produk size= "+
                            // productDb.findById(delete.get(i).getProduct().getId()).get().getPiProductDetail().size());
                        }
                        oldSIdetail.removeAll(delete);
                        delete.clear();
                    }
                }
                if (update) {
                    // vesel
                    uptSI.getVessel().setName(siForm.getVesselName());
                    uptSI.getVessel().setConnectingVessel(siForm.getConnectingVessel());

                    // SI
                    uptSI.setProformaInvoice(pi.get());// cari findbyId
                    uptSI.setPurchaseOrder(POId.get());// find byid
                    uptSI.setInvoice(siForm.getInvoice());
                    // if(siForm.getInvoice() != null){
                    // uptSI.getInvoice().setNumber(siForm.getInvoice());
                    // uptSI.getInvoice().setDate(LocalDate.now());
                    // }
                    uptSI.setLiner(siForm.getLiner());
                    uptSI.setOpenTime(siForm.getOpenTime());
                    uptSI.setOpenDate(siForm.getOpenDate());
                    uptSI.setCloseDate(siForm.getCloseDate());
                    uptSI.setCloseTime(siForm.getCloseTime());
                    uptSI.setPoD(siForm.getPoD());
                    uptSI.setPoL(siForm.getPoL());
                    uptSI.setETA(siForm.getETA());
                    uptSI.setETD(siForm.getEtdep());
                    uptSI.setFinalDestination(siForm.getFinalDestination());
                    uptSI.setShipmentStatus(siForm.getStatus());
                    uptSI.setStaff(staff.get());
                    uptSI.setPaymentStatus(siForm.getPaymentStatus());
                    uptSI.setSiDocument(siForm.getSiDocument());
                    uptSI.setBookingConfirmation(siForm.getBookingConfirmation());
                    // uptSI.setShipmentPriority(siForm.getShipmentPriority());

                    // save
                    SIDb.save(uptSI);
                    vesselDb.save(uptSI.getVessel());
                }
                SIDb.save(uptSI);
                // success
                System.out.println(uptSI.getShipmentProductDetail().size());
                response.setStatus(200);
                response.setMessage("success");
                response.setResult(uptSI);
            }
        }
        return response;
    }

    @GetMapping("/cust/{cid}/si")
    @ResponseBody
    public BaseResponse<SIOutputCustomer> ouputCustomer(@RequestParam("id") Long id, @PathVariable("cid") Long cid) {
        BaseResponse<SIOutputCustomer> response = new BaseResponse<SIOutputCustomer>();
        Optional<ShippingInstructionModel> si = SIDb.findById(id);
        if (!si.isPresent()) {
            response.setMessage("Shipping Instruction Not Found");
            response.setStatus(404);
        } else if (!siService.vlidateCustomerSI(cid, id)) {
            response.setMessage("Shipping Instruction Not Found");
            response.setStatus(404);
        } else {
            SIOutputCustomer siCustOut = new SIOutputCustomer();
            siCustOut.setEta(si.get().getETA());
            siCustOut.setEtd(si.get().getETD());
            siCustOut.setFinalDestination(si.get().getFinalDestination());
            siCustOut.setInvoice(si.get().getInvoice());
            siCustOut.setPaymentStatus(si.get().getPaymentStatus());
            siCustOut.setPod(si.get().getPoD());
            siCustOut.setPol(si.get().getPoL());
            siCustOut.setShipmentStatus(si.get().getShipmentStatus());
            siCustOut.setStaffName(si.get().getStaff().getName());
            siCustOut.setShipmentId(si.get().getId());
            siCustOut.setProdukDetail(new ArrayList<PIPOTransFormOut>());
            for (int i = 0; i < si.get().getShipmentProductDetail().size(); i++) {
                ShipmentProductDetailModel siDetailRes = si.get().getShipmentProductDetail().get(i);
                ProductModel productRes = siDetailRes.getPIPOTransaction().getPiProdukDetail().getProduct();
                PIPOTransFormOut siDetail = new PIPOTransFormOut();
                siDetail.setPIPOId(siDetailRes.getPIPOTransaction().getId());
                siDetail.setQuantity(siDetailRes.getquantity());
                siDetail.setProductName(productRes.getName());
                siDetail.setProductId(productRes.getId());
                siDetail.setGrossWeight(productRes.getGrossWeight());
                siDetail.setNetWeight(productRes.getNetWeight());
                siDetail.setproductUnit(productRes.getProductUnit());
                siDetail.setProductDesc(productRes.getProductDesc());
                siDetail.setSiDetailId(siDetailRes.getId());
                siDetail.setPONum(siDetailRes.getPIPOTransaction().getPoProdukDetail().getPurchaseOrder().getNumber());
                siCustOut.getProdukDetail().add(siDetail);
            }

            response.setMessage("Success");
            response.setResult(siCustOut);
            response.setStatus(200);
        }
        return response;
    }

    @PostMapping("/si/confirm")
    @ResponseBody
    public BaseResponse<ShippingInstructionModel> confirmSI(@RequestParam(value = "id") long id,
            @RequestBody SIForm siForm, BindingResult bindingResult) {
        BaseResponse<ShippingInstructionModel> response = new BaseResponse<ShippingInstructionModel>();
        Optional<ShippingInstructionModel> si = SIDb.findById(id);
        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("Internal Server Error");
        }
        if (!si.isPresent()) {
            response.setStatus(404);
            response.setMessage("Shipping Insruction Not Found");
        } else {
            ShippingInstructionModel uptSI = si.get();
            // uptSI.setNoInvoice(siForm.getNoInvoice());
            uptSI.setInvoice(siForm.getInvoice());
            uptSI.setShipmentStatus(siForm.getStatus());
            uptSI.setSiDocument(siForm.getSiDocument());
            uptSI.setBookingConfirmation(siForm.getBookingConfirmation());
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(uptSI);
        }
        return response;

    }

    @PostMapping("/si/set-priority")
    @ResponseBody
    public BaseResponse<ShippingInstructionModel> setPriority(@RequestParam(value = "ids") List<Long> ids,
            @RequestBody SIForm siForm, BindingResult bindingResult) {
        BaseResponse<ShippingInstructionModel> response = new BaseResponse<ShippingInstructionModel>();

        for (Long idSI : ids) {
            ShippingInstructionModel SI = SIDb.findById(idSI).get();
            SI.setShipmentPriority("High");
            SIDb.save(SI);
        }
        System.out.println(ids);
        response.setStatus(200);
        response.setMessage("success");
        return response;
    }

}