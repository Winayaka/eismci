package com.propensi.eis.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import com.propensi.eis.Model.PIProductDetailModel;
import com.propensi.eis.Model.ProformaInvoiceModel;
import com.propensi.eis.Model.ShipmentProductDetailModel;
import com.propensi.eis.Model.ShippingInstructionModel;
import com.propensi.eis.Repository.ProformaInvoiceDB;

@Service
public class ProformaInvoiceServiceImpl implements ProformaInvoiceService {
    @Autowired
    private ProformaInvoiceDB proformaInvoiceDB;

    @Override
    public ProformaInvoiceModel addProformaInvoice(ProformaInvoiceModel newPI) {
        return proformaInvoiceDB.save(newPI);
    }

    @Override
    public ProformaInvoiceModel editProformaInvoice(ProformaInvoiceModel editedPI) {
        ProformaInvoiceModel PI = proformaInvoiceDB.findById(editedPI.getId()).get();
        PI = editedPI;
        return proformaInvoiceDB.save(PI);
    }

    @Override
    public ProformaInvoiceModel findProformaInvoiceByNumber(String PInumber) {
        List<ProformaInvoiceModel> ListOfPI = proformaInvoiceDB.findAll();
        ProformaInvoiceModel foundPI = null;
        for (ProformaInvoiceModel PI : ListOfPI) {
            if (PI.getNumber().equals(PInumber)) {
                foundPI = PI;
                break;
            }
        }
        return foundPI;
    }
    @Override
    public long totalRevenue(String PInumber){
        ProformaInvoiceModel target = proformaInvoiceDB.findByNumber(PInumber);
        List<PIProductDetailModel> piDetails = target.getDetailProduct();
        long totalRev = 0;
        for (PIProductDetailModel detail : piDetails){
            int quantity = detail.getQuantity();
            int price = detail.getPricePerUnit();
            totalRev = totalRev + (quantity*price);
        }

        return totalRev;
    }
    @Override
    public HashMap<String,Integer> getProducts(String PInumber){
        ProformaInvoiceModel target = proformaInvoiceDB.findByNumber(PInumber);
        List<PIProductDetailModel> piDetails = target.getDetailProduct();
        HashMap<String,Integer> productMap = new HashMap<String,Integer>();
        for (PIProductDetailModel detail : piDetails){
            String productName = detail.getProduct().getName();
            Integer productQuantity = detail.getQuantity();
            productMap.put(productName,productQuantity);
        }

        return productMap;

    }
}

