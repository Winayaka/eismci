package com.propensi.eis.Controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.propensi.eis.Model.*;
import com.propensi.eis.Repository.*;
import com.propensi.eis.Rest.*;
import com.propensi.eis.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.BindingResult;


@Controller
@RequestMapping("/api")
public class ReportController{

    @Autowired
    ProformaInvoiceDB piDb;

    @Autowired
    ProductDb productDb;

    @Autowired
    ProformaInvoiceService piService;

    //get all
    @GetMapping(value = "/getReport")
    @ResponseBody
    public BaseResponse<ReportModel> getReport(@RequestParam("date") String startDate, 
                                               @RequestParam("period") String period ) {
        BaseResponse<ReportModel> response = new BaseResponse<ReportModel>();
        ReportModel reportAkhir = new ReportModel();
        //inisiasi periode
        int periodCount = 0;
        if (period.equals("weekly")){
            periodCount = 7;
        }
        else if (period.equals("monthly")){
            periodCount = 30;
        }
        else if (period.equals("yearly")){
            periodCount = 365;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDateinLocalDate = LocalDate.parse(startDate, formatter);
        LocalDate targetDate = startDateinLocalDate.minusDays(periodCount);


        //cari transaksi valid
        List<ProformaInvoiceModel> allPi = piDb.findAll();
        if (allPi.size() == 0){
            reportAkhir.setNumberOfTransaction(0);
            reportAkhir.setRevenue(0);
            reportAkhir.setMostProduct("noProduct");
            response.setMessage("no Pi found");
            response.setStatus(400);
                response.setResult(reportAkhir);
            return response;    
        }
        List<ProformaInvoiceModel> validPi = new ArrayList<ProformaInvoiceModel>();
        HashMap<String,Integer> productMap = new HashMap<String,Integer>();
        long totalRev = 0;
        int numberOfTransaction = 0;

        for (ProformaInvoiceModel pi : allPi){
            LocalDate piCompleteDate = pi.getCompleteDate();   

            Boolean isValid = ( piCompleteDate.isBefore( startDateinLocalDate ) ) && ( !piCompleteDate.isBefore( targetDate ) );
            if(isValid){
                validPi.add(pi);
                //hitung revenue
                totalRev += piService.totalRevenue(pi.getNumber());
                numberOfTransaction++;
                //assign tiap produk yang dijual
                HashMap<String,Integer> productSold = piService.getProducts(pi.getNumber());
                for (HashMap.Entry<String,Integer> entry : productSold.entrySet())  {
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                    boolean isPresent = false;
                    for (HashMap.Entry<String,Integer> entryMaster : productMap.entrySet()){
                        System.out.println(entry.getKey());
                        if(entryMaster.getKey().equals(entry.getKey())){
                            isPresent = true;
                        }
                    }
                    if(isPresent){
                        System.out.println("ga masuk");
                        productMap.put(entry.getKey(),productMap.get(entry.getKey()) + entry.getValue());
                    }
                    else{
                        System.out.println("masuk");
                        productMap.put(entry.getKey(),entry.getValue());
                    }
                }
            }

        }
        String mostProduct = "";
        int highestCount = 0;
        for (HashMap.Entry<String,Integer> entry : productMap.entrySet()){
            if(entry.getValue() > highestCount){
                mostProduct = entry.getKey();
                highestCount = entry.getValue();
            }

        }
        
        reportAkhir.setNumberOfTransaction(numberOfTransaction);
        reportAkhir.setRevenue(totalRev);
        reportAkhir.setMostProduct(mostProduct);

        response.setMessage("success");
        response.setStatus(200);
        response.setResult(reportAkhir);


        return response;
    }
    

}
