package com.propensi.eis.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.propensi.eis.Model.ShipmentDocumentModel;
import com.propensi.eis.Model.ShipmentDocumentNotesModel;
import com.propensi.eis.Model.ShippingInstructionModel;
import com.propensi.eis.Repository.CustomerDb;
import com.propensi.eis.Repository.ShipmentDocumentDb;
import com.propensi.eis.Repository.ShipmentDocumentNotesDb;
import com.propensi.eis.Repository.ShippingInstructionDb;
import com.propensi.eis.Repository.StaffDb;
import com.propensi.eis.Rest.BaseResponse;
import com.propensi.eis.RestModel.DocumentNotes;
import com.propensi.eis.RestModel.SIDoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api")
public class ShipmentDocumentController {

    @Autowired
    ShipmentDocumentDb shipmentDocumentDb;

    @Autowired
    ShippingInstructionDb sidb;

    @Autowired
    StaffDb staffDb;

    @Autowired
    CustomerDb custDb;

    @Autowired
    ShipmentDocumentNotesDb noteDb;

    @GetMapping(value = "/download")
    @ResponseBody
    public ResponseEntity<Object> downloadFile(@RequestParam("id") Long id) throws IOException{
        Optional<ShipmentDocumentModel> docs = shipmentDocumentDb.findById(id);
        ResponseEntity<Object> res;
        if(!docs.isPresent()){
            res = ResponseEntity.badRequest().body(null);
            return res;
        }
        else{
            System.out.println(".\\document\\"+docs.get().getFileName());
            File file = new File(".\\document\\"+docs.get().getFileName());
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
            return responseEntity;
        }
    }

    @GetMapping(value = "/getAllShipmentDocument")
    @ResponseBody
    public BaseResponse<List<ShipmentDocumentModel>> getAllSD() {
        BaseResponse<List<ShipmentDocumentModel>> response = new BaseResponse<List<ShipmentDocumentModel>>();
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(shipmentDocumentDb.findAll());
        return response;
    }

    @GetMapping(value = "/get/document")
    @ResponseBody
    public BaseResponse<List<ShipmentDocumentModel>> getAllSDbySI(@RequestParam("id") Long id) {
        BaseResponse<List<ShipmentDocumentModel>> response = new BaseResponse<List<ShipmentDocumentModel>>();
        List<ShipmentDocumentModel> listDocument = shipmentDocumentDb.findByShippingInstructionId(id);
        if(listDocument == null){
            response.setStatus(404);
            response.setMessage("SI not Found");
        }
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(listDocument);
        return response;
    }

    @GetMapping(value = "/document")
    @ResponseBody
    public BaseResponse<ShipmentDocumentModel> getAllSDbyid(@RequestParam("id") Long id) {
        BaseResponse<ShipmentDocumentModel> response = new BaseResponse<ShipmentDocumentModel>();
        Optional<ShipmentDocumentModel> docs= shipmentDocumentDb.findById(id);
        if(!docs.isPresent()){
            response.setStatus(404);
            response.setMessage("SI not Found");
        }
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(docs.get());
        return response;
    }

    //add document
    @PostMapping(value ="/add/document")
    @ResponseBody
    public BaseResponse<ShipmentDocumentModel> addSD(@RequestBody SIDoc shipDocForm, BindingResult bindingResult){
        BaseResponse<ShipmentDocumentModel> response = new BaseResponse<ShipmentDocumentModel>();
        if(bindingResult.hasErrors()){
            response.setStatus(500);
            response.setMessage("Internal Server Error");
        } 
        Optional<ShippingInstructionModel> si = sidb.findById(shipDocForm.getSiid());
        if(!si.isPresent()){
            response.setStatus(404);
            response.setMessage("Shipping Instruction Not Found");
            return response;
        } else{   
            ShipmentDocumentModel shipDoc = new ShipmentDocumentModel();
            shipDoc.setFileName(shipDocForm.getFileName());
            shipDoc.setCreatedDate(LocalDate.now());
            shipDoc.setName(shipDocForm.getName());
            shipDoc.setStatus(shipDocForm.getStatus());
            shipDoc.setShippingInstruction(si.get());
            shipmentDocumentDb.save(shipDoc);       
            si.get().getShipmentDocument().add(shipDoc);
        }
        return response;
    }
    //add upload file
    @PostMapping(value ="/upload", consumes = { "multipart/form-data"}, headers = {"content-type=multipart/mixed","content-type=multipart/form-data"})
    @ResponseBody
    public BaseResponse<ShipmentDocumentModel> uploadFile(@RequestPart("file") MultipartFile file, 
    @RequestPart("status") String status, @RequestPart("name") String name, 
    @RequestPart("fileName") String fileName, @RequestPart("siid") String siid) throws IOException{
        BaseResponse<ShipmentDocumentModel> response = new BaseResponse<ShipmentDocumentModel>();
        Optional<ShippingInstructionModel> si = sidb.findById(Long.parseLong(siid));
        ShipmentDocumentModel shipDoc = new ShipmentDocumentModel();
        shipDoc.setFileName(fileName);
        shipDoc.setCreatedDate(LocalDate.now());
        shipDoc.setName(name);
        shipDoc.setStatus(status);
        shipDoc.setShippingInstruction(si.get());
        shipDoc.setLink(file);
        shipmentDocumentDb.save(shipDoc);       
        si.get().getShipmentDocument().add(shipDoc);
        //file created
        File convertFile = new File(".\\document\\" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        // }
        
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(shipDoc);
        return response;
    }
}