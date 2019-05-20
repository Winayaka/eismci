package com.propensi.eis.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.propensi.eis.Model.PIProductDetailModel;
import com.propensi.eis.Model.POProductDetailModel;
import com.propensi.eis.Model.ProformaInvoiceModel;
import com.propensi.eis.Model.ShippingInstructionModel;
import com.propensi.eis.Repository.CustomerDb;
import com.propensi.eis.Repository.PIProductDetailDB;
import com.propensi.eis.Repository.POProductDetailDb;
import com.propensi.eis.Repository.ProformaInvoiceDB;
import com.propensi.eis.Repository.ShippingInstructionDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SIServiceImpl implements SIService {

    @Autowired
    PIProductDetailDB piDb;

    @Autowired
    POProductDetailDb poDb;

    @Autowired
    ProformaInvoiceDB profDb;

    @Autowired
    ShippingInstructionDb siDb;

    @Override
    public boolean calculateQuantity(int quantity, PIProductDetailModel piDetail, POProductDetailModel poDetail, int maxSent) {
        if(piDetail.getQuantity() < piDetail.getQuantitySent()+quantity){
            return false;
        }else if(poDetail.getQuantityRemaining() == 0){
            return false;
        }else if(poDetail.getQuantityRemaining() < quantity){
            return  false;
        } else if(quantity > maxSent){
            return false;
        }
        else {
            piDetail.setQuantitySent(piDetail.getQuantitySent()+quantity);
            poDetail.setQuantityRemaining(poDetail.getQuantityRemaining()-quantity);
            return true;
        }
    }

    @Override
    public void reverseCalculateQuantity(int quantity, PIProductDetailModel piDetail, POProductDetailModel poDetail) {
        piDetail.setQuantitySent(piDetail.getQuantitySent()-quantity);
        poDetail.setQuantityRemaining(poDetail.getQuantityRemaining()+quantity);
    }

    @Override
    public boolean vlidateCustomerSI(Long cid, Long siid) {
        boolean res = false;
        List<ProformaInvoiceModel> listPI = profDb.findByCustomerId(cid);
        List<ShippingInstructionModel> si = new ArrayList<ShippingInstructionModel>();
        for(int i = 0; i < listPI.size() ;i++){
            si.addAll(listPI.get(i).getShippingInstruction());
        }
        for(int i = 0; i < si.size() ;i++){
            if(si.get(i).getId() == siid){
                res=true;
            }
        }  
        return res;      
    }
    
  }