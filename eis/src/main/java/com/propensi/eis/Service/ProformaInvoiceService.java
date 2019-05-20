package com.propensi.eis.Service;

import java.util.HashMap;

import com.propensi.eis.Model.ProformaInvoiceModel;

public interface ProformaInvoiceService {
    ProformaInvoiceModel addProformaInvoice(ProformaInvoiceModel newPI);

    ProformaInvoiceModel editProformaInvoice(ProformaInvoiceModel editedPI);

    ProformaInvoiceModel findProformaInvoiceByNumber(String PInumber);

    long totalRevenue(String PInumber);
    HashMap<String,Integer> getProducts(String PInumber);
}