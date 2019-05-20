package com.propensi.eis.Service;

import com.propensi.eis.Model.PIProductDetailModel;
import com.propensi.eis.Model.POProductDetailModel;

public interface SIService {

    boolean calculateQuantity(int quantity, PIProductDetailModel piDetail, POProductDetailModel poDetail, int maxSent);

    void reverseCalculateQuantity(int quantity, PIProductDetailModel piDetail, POProductDetailModel poDetail);

    boolean vlidateCustomerSI(Long cid, Long siid);

}