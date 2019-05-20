package com.propensi.eis.Controller;

import java.util.List;


import com.propensi.eis.Model.StaffModel;
import com.propensi.eis.Model.UserModel;
import com.propensi.eis.Repository.StaffDb;
import com.propensi.eis.Repository.UserDb;
import com.propensi.eis.Rest.BaseResponse;
import com.propensi.eis.Rest.AddStaffModel;
import com.propensi.eis.RestModel.StaffUserModel;

import org.springframework.beans.factory.annotation.Autowired;
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
public class StaffController{
    
    @Autowired
    StaffDb staffDb;

    @Autowired
    UserDb userDb;

    
    //get all
    @GetMapping(value = "/getAllStaff")
    @ResponseBody
    public BaseResponse<List<StaffModel>> getAllStaff() {
        BaseResponse<List<StaffModel>> response = new BaseResponse<List<StaffModel>>();
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(staffDb.findAll());
        
        return response;
    }

    //get specific staff
    @GetMapping(value = "/getStaff")
    @ResponseBody
    public BaseResponse<StaffUserModel> getStaff(@RequestParam("name") String name) {
        BaseResponse<StaffUserModel> response = new BaseResponse<StaffUserModel>();
        StaffModel target = staffDb.findByName(name);
        StaffUserModel dataSend = new StaffUserModel();
            if(target == null){
            response.setStatus(404);
            response.setMessage("Staff not found");
        }
        else{
            response.setStatus(200);
            response.setMessage("Success");
            dataSend.setBirthDate(target.getBirthDate());
            dataSend.setBirthplace(target.getBirthplace());
            dataSend.setEmail(target.getEmail());
            dataSend.setGender(target.getGender());
            dataSend.setName(target.getName());
            dataSend.setPhoneNum(target.getPhoneNum());
            dataSend.setRole(target.getUser().getRole());
            dataSend.setUsername(target.getUser().getUsername());
            dataSend.setStaffId(target.getStaffId());
            dataSend.setActiveStatus(target.getUser().getActiveStatus());
            
            response.setResult(dataSend);
        }

        return response;
    }

    @PostMapping(value = "/addStaff")
    @ResponseBody
    public BaseResponse<AddStaffModel> addStaff(@RequestBody AddStaffModel staffAndUser,
                                             BindingResult bindingResult){
        BaseResponse<AddStaffModel> response = new BaseResponse<AddStaffModel>();

        System.out.println("test");
        if(bindingResult.hasErrors()){
            response.setStatus(500);
            response.setMessage("Error");
        }
        else{
            StaffModel newStaff = new StaffModel();
            newStaff.setName(staffAndUser.getName());
            newStaff.setEmail(staffAndUser.getEmail());
            newStaff.setPhoneNum(staffAndUser.getPhoneNum());
            newStaff.setBirthplace(staffAndUser.getBirthplace());
            newStaff.setGender(staffAndUser.getGender());
            newStaff.setBirthDate(staffAndUser.getBirthDate());

            UserModel newUser = new UserModel();
            newUser.setUsername(staffAndUser.getUsername());
            newUser.setPassword(staffAndUser.getPassword());
            newUser.setRole(staffAndUser.getRole());
            newUser.setActiveStatus(true);

            newUser.setStaff(newStaff);


            staffDb.save(newStaff);
            userDb.save(newUser);


            response.setStatus(200);
            response.setMessage("Input Success");
            response.setResult(staffAndUser);
        }


        return response;
    }

    @PostMapping(value = "/editStaff")
    @ResponseBody
    public BaseResponse<AddStaffModel> editStaff(@RequestBody AddStaffModel staffAndUser,
                                             BindingResult bindingResult,
                                             @RequestParam(value = "staffId") long staffId){
        BaseResponse<AddStaffModel> response = new BaseResponse<AddStaffModel>();

        System.out.println(staffAndUser);
        if(bindingResult.hasErrors()){
            response.setStatus(500);
            response.setMessage("Error");
        }
        else{
            StaffModel staff = staffDb.findById(staffId).get();
            staff.setName(staffAndUser.getName());
            staff.setEmail(staffAndUser.getEmail());
            staff.setPhoneNum(staffAndUser.getPhoneNum());
            staff.setBirthplace(staffAndUser.getBirthplace());
            staff.setGender(staffAndUser.getGender());
            staff.setBirthDate(staffAndUser.getBirthDate());

            UserModel user = userDb.findById(staff.getUser().getId()).get();
            user.setUsername(staffAndUser.getUsername());
            user.setPassword(staff.getUser().getPassword());
            user.setRole(staffAndUser.getRole());

            user.setStaff(staff);


            staffDb.save(staff);
            userDb.save(user);


            response.setStatus(200);
            response.setMessage("Edit Success");
            response.setResult(staffAndUser);
        }


        return response;
    }

    @PostMapping(value = "/changeStaffStatus")
    @ResponseBody
    public BaseResponse<StaffModel> changeStaffStatus(@RequestParam(value = "staffId") long staffId){
        BaseResponse<StaffModel> response = new BaseResponse<StaffModel>();

        System.out.println("test");
        StaffModel staff = staffDb.findById(staffId).get();
        UserModel user = userDb.findById(staff.getUser().getId()).get();
        if(user.getActiveStatus()){
            user.setActiveStatus(false);
        }
        else{
            user.setActiveStatus(true);
        }
        user.setStaff(staff);


        userDb.save(user);
        response.setStatus(200);
        response.setMessage("Edit Success");
        response.setResult(staff);


        return response;
    }


// 	@PostMapping(value="/api/medical-supplies/permintaan")
// 	private BaseResponse<PermintaanModel> addPermintaanSubmit(
// 												@RequestBody PermintaanModel permintaan,
// 												BindingResult bindingresult) {
// 		BaseResponse<PermintaanModel> response = new BaseResponse<PermintaanModel>();
		
// 		if(permintaan.getJumlahMedicalSupplies() == 0 ||
// 		   permintaan.getIdPasien() == 0 ||
// 		   permintaan.getListPermintaanMedicalSupplies() == null) {
// 			response.setMessage("error data");
// 			response.setResult(null);
// 			response.setStatus(500);
// 		}
// 		else {	
// 			//date func
// 			DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
// 			Calendar today = Calendar.getInstance();
// 			df.setTimeZone(today.getTimeZone());
// 			Date tanggalMasuk = new Date(today.getTimeInMillis()); 
// 			String todayStr = df.format(today.getTime());
// 			String[] todaySplit = todayStr.split("-");
// 			String todayTahun = todaySplit[0];
// 			String todayBulan = todaySplit[1];
// 			String todayTanggal = todaySplit[2];
			
// 			//jadwal func
// 			int jamMasuk = today.get(Calendar.HOUR);
			
// 			List<JadwalJagaModel> allJadwal = jadwalJagaDb.findAll();
// 			JadwalJagaModel jadwalDipakai = null;
	
// 			//cari tanggal hari ini
// 			for (JadwalJagaModel jadwalTemp : allJadwal) {
// 				Date tempJadwalDate = jadwalTemp.getTanggal();
// 				Time waktuJadwalMulai = jadwalTemp.getWaktuMulai();
				
// 				String waktuJadwalMulaiStr = waktuJadwalMulai.toString();
// 				waktuJadwalMulaiStr = waktuJadwalMulaiStr.substring(0,2);
// 				int waktuJadwalMulaiInt = Integer.parseInt(waktuJadwalMulaiStr);
	
// 				String jadwalDateStr = df.format(tempJadwalDate);
// 				String[] splitJadwalDateStr = jadwalDateStr.split("-");
// 				String jadwalDateTahun = splitJadwalDateStr[0];
// 				String jadwalDateBulan = splitJadwalDateStr[1];
// 				String jadwalDateTanggal = splitJadwalDateStr[2];
				
				
				
// 				if (todayTanggal.equals(jadwalDateTanggal) &&
// 					todayBulan.equals(jadwalDateBulan) &&
// 					todayTahun.equals(jadwalDateTahun)){
						
// 					if(today.get(Calendar.HOUR_OF_DAY)>=0 && 
// 					   today.get(Calendar.HOUR_OF_DAY)<16 &&
// 					    waktuJadwalMulaiInt == 8) {
// 						jadwalDipakai = jadwalTemp;
// 					}
// 					else if(today.get(Calendar.HOUR_OF_DAY)>=16 && 
// 						today.get(Calendar.HOUR_OF_DAY)<=24 &&
// 						waktuJadwalMulaiInt == 16) {
// 						jadwalDipakai = jadwalTemp;
// 					}
// 				}			
// 			}
					
// 			List<PermintaanMedicalSuppliesModel> tempPMSMlst = permintaan.getListPermintaanMedicalSupplies();
// 			List<PermintaanMedicalSuppliesModel> PMSMAkhir = new ArrayList();
			
// 			//buat list permintaan
	
// 			for(PermintaanMedicalSuppliesModel masukKeDb : tempPMSMlst) {
// 				PermintaanMedicalSuppliesModel tempIterasi = masukKeDb;
// 				//cari obat dari nama
// 				MedicalSuppliesModel tempObat = medicalSuppliesDb.findByNama(masukKeDb.getMedicalSupplies().getNama());
	
// 				//save ke db PMSM
// 				masukKeDb.setMedicalSupplies(tempObat);
				
// 				//masukin PSMS yang udah bener ke list buat permintaan
// 				PMSMAkhir.add(masukKeDb);
// 			}
			
			
			
// 			permintaan.setJadwalJaga(jadwalDipakai);
// 			permintaan.setTanggal(tanggalMasuk);
// 			permintaan.setListPermintaanMedicalSupplies(PMSMAkhir);
// 			permintaan.setStatusPermintaan(statusPermintaanDb.findById(1).get());
// 			permintaanDb.save(permintaan);
			
// 			//save ke tabel antara
// 			for (int i = 0;i < permintaan.getListPermintaanMedicalSupplies().size();i++) {
// 				PermintaanMedicalSuppliesModel temp =permintaan.getListPermintaanMedicalSupplies().get(i);
// 				temp.setPermintaan(permintaan);
// 				permintaanMedicalSuppliesDb.save(temp);
// 			}
			
// 			response.setStatus(200);
// 			response.setMessage("success");	
// 			response.setResult(permintaan);
// 		}
// 		return response;
// 	}
	

}