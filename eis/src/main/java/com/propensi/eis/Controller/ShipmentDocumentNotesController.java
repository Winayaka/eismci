package com.propensi.eis.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.propensi.eis.Model.ShipmentDocumentModel;
import com.propensi.eis.Model.ShipmentDocumentNotesModel;
import com.propensi.eis.Repository.ShipmentDocumentDb;
import com.propensi.eis.Repository.ShipmentDocumentNotesDb;
import com.propensi.eis.Rest.BaseResponse;
import com.propensi.eis.RestModel.DocumentNotes;
import com.propensi.eis.RestModel.ListNotes;

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
public class ShipmentDocumentNotesController {

    @Autowired
    ShipmentDocumentNotesDb notesDb;

    @Autowired
    ShipmentDocumentDb documentDb;

    @GetMapping("/note")
    @ResponseBody
    public BaseResponse<List<ShipmentDocumentNotesModel>> getAllNotesInDoc(@RequestParam("id") Long id){
        BaseResponse<List<ShipmentDocumentNotesModel>> response = new BaseResponse<List<ShipmentDocumentNotesModel>>();
        Optional<ShipmentDocumentModel> doc = documentDb.findById(id);
        if(!doc.isPresent()){
            response.setStatus(404);
            response.setMessage("Document Not Found");
        } else {
            List<ShipmentDocumentNotesModel> listNotes = notesDb.findByShipmentDocumentId(id);
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(listNotes);
        }
        return response;
    }

    @PostMapping("/add/note")
    @ResponseBody
    public BaseResponse<List<ShipmentDocumentNotesModel>> addNotes(@RequestBody ListNotes notes, BindingResult bindingResult){
        BaseResponse<List<ShipmentDocumentNotesModel>> response = new BaseResponse<List<ShipmentDocumentNotesModel>>();
        Optional<ShipmentDocumentModel> doc = documentDb.findById(notes.getDocumentId());
        if(!doc.isPresent()){
            response.setStatus(404);
            response.setMessage("Document Not Found");
        }
        else{
            for(int i = 0; i < notes.getDoc().size(); i++){
                ShipmentDocumentNotesModel saveNote = new ShipmentDocumentNotesModel();
                DocumentNotes note = notes.getDoc().get(i);
                saveNote.setContent(note.getContent());
                //LocalDate localDate = LocalDate.parse(note.getCreatedDate().toString());
                saveNote.setCreatedDate(LocalDate.now());
                //LocalTime localTime = LocalTime.parse(note.getCreatedTime().toString());
                saveNote.setCreatedTime(LocalTime.now());
                saveNote.setSender(note.getSender());
                saveNote.setShipmentDocument(doc.get());
                notesDb.save(saveNote);
                System.out.println(saveNote.getId()+"<================");
                doc.get().getDocumentNotes().add(saveNote);
            }
        }
        return response;
    }


}