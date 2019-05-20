package com.propensi.eis.Controller;

import java.util.ArrayList;
import java.util.List;

import com.propensi.eis.Model.PIProductDetailModel;
import com.propensi.eis.Model.POProductDetailModel;
import com.propensi.eis.Model.ProformaInvoiceModel;
import com.propensi.eis.Model.PurchaseOrderModel;
import com.propensi.eis.Model.ShipmentProductDetailModel;
import com.propensi.eis.Repository.ProformaInvoiceDB;
import com.propensi.eis.Rest.BaseResponse;
import com.propensi.eis.RestModel.PIPOSpecial1;
import com.propensi.eis.RestModel.PIPOSpecial2;
import com.propensi.eis.RestModel.PIPOSpecial3;
import com.propensi.eis.RestModel.PIPOSpecial4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class PIPOSpecialController {

    @Autowired
    ProformaInvoiceDB piDb;
    
    @GetMapping("/special")
    @ResponseBody
    public BaseResponse<List<PIPOSpecial1>> special(){
        BaseResponse<List<PIPOSpecial1>> response = new BaseResponse<>();
        List<PIPOSpecial1> listSp = new ArrayList<>();
        List<ProformaInvoiceModel> pi = piDb.findAll();
        for(int i = 0 ; i< pi.size() ; i++){
            PIPOSpecial1 sp1 = new PIPOSpecial1();
            sp1.setCustomername(pi.get(i).getCustomer().getCustName());
            sp1.setPiNum(pi.get(i).getNumber());
            sp1.setFinalDestination(pi.get(i).getFinalDestination());
            sp1.setPipoSpecial2(new ArrayList<PIPOSpecial2>());

            for(int j = 0 ; j < pi.get(i).getDetailProduct().size() ; j++){
                PIProductDetailModel pidetail =  pi.get(i).getDetailProduct().get(j);
                PIPOSpecial2 sp2 = new PIPOSpecial2();
                sp2.setProductName(pidetail.getProduct().getName());
                //pake harga total
                sp2.setPrice("$ "+ (pidetail.getPricePerUnit()*pidetail.getQuantity()));
                sp2.setContainer(pidetail.getContainerQuantity()+"");
                sp2.setPipoSpecial3(new ArrayList<PIPOSpecial3>());
                sp1.getPipoSpecial2().add(sp2);         
                
                for(int k = 0 ; k < pidetail.getPIPOTransaction().size(); k++){
                    PIPOSpecial3 sp3 = new PIPOSpecial3();
                    POProductDetailModel po = pidetail.getPIPOTransaction().get(k).getPoProdukDetail();
                    sp3.setPoNum(po.getPurchaseOrder().getNumber());
                    sp3.setPoSupplierNum(po.getPurchaseOrder().getPoNumSupplier());
                    sp3.setSupplierName(po.getPurchaseOrder().getNamaSupplier());
                    // harga total
                    sp3.setPoPrice("$ "+(po.getPricePerUnit()*po.getQuantity()));
                    sp3.setContainerQuantityPO(po.getContainerQuantity()+"");
                    sp3.setPipoSpecial4(new ArrayList<PIPOSpecial4>());
                    sp2.getPipoSpecial3().add(sp3);
                    
                    for(int l = 0 ; l < pidetail.getPIPOTransaction().get(k).getShippingDetail().size(); l++){
                        ShipmentProductDetailModel sidetail = pidetail.getPIPOTransaction().get(k).getShippingDetail().get(l);
                        PIPOSpecial4 sp4 = new PIPOSpecial4();
                        sp4.setInvoiceNum(sidetail.getShippingInstruction().getInvoice());
                        sp4.setNoSI(sidetail.getShippingInstruction().getNumber());
                        sp4.setQuantity(sidetail.getquantity()+"");
                        sp4.setStaffName(sidetail.getShippingInstruction().getStaff().getName());
                        sp3.getPipoSpecial4().add(sp4);
                    }
                }
            }
            listSp.add(sp1);
        }
        response.setMessage("Success");
        response.setResult(listSp);
        response.setStatus(200);
        return response;
    }
}