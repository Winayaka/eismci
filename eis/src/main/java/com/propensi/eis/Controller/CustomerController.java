package com.propensi.eis.Controller;

import java.util.List;

import com.propensi.eis.Model.CustomerModel;
import com.propensi.eis.Model.UserModel; 
import com.propensi.eis.Repository.CustomerDb;
import com.propensi.eis.Repository.UserDb;
import com.propensi.eis.Rest.BaseResponse;
import com.propensi.eis.Rest.AddCustomerModel;

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
public class CustomerController{
    /**
	 *
	 */
	
	private static final int _500 = 500;

	@Autowired
    CustomerDb customerDb;

    @Autowired
    UserDb userDb;
    
    //Get all customer
    @GetMapping(value = "/getAllCustomer")
    @ResponseBody
    public BaseResponse<List<CustomerModel>> getAllCustomer() {
        BaseResponse<List<CustomerModel>> response = new BaseResponse<List<CustomerModel>>();
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(customerDb.findAll());
        return response;
    }

    //Get specific customer
    @GetMapping(value = "/getCustomer")
    @ResponseBody
    public BaseResponse<CustomerModel> getCustmer(@RequestParam("custName") String custName) {
        BaseResponse<CustomerModel> response = new BaseResponse<CustomerModel>();
        CustomerModel target = customerDb.findByCustName(custName);
            if(target == null){
            response.setStatus(404);
            response.setMessage("Customer not found");
        }
        else{
            response.setStatus(200);
            response.setMessage("Success");
            response.setResult(customerDb.findByCustName(custName));
        }

        return response;
    }

    //Post new customer
    @PostMapping(value = "/addCustomer")
    @ResponseBody
    public BaseResponse<AddCustomerModel> addCustomer(@RequestBody AddCustomerModel cust,
                                             BindingResult bindingResult){
        BaseResponse<AddCustomerModel> response = new BaseResponse<AddCustomerModel>();

        if(bindingResult.hasErrors()){
            response.setStatus(500);
            response.setMessage("Error");
        }
        else{
            CustomerModel newCustomer = new CustomerModel();
            newCustomer.setTitle(cust.getTitle());
            newCustomer.setCustName(cust.getCustName());
            newCustomer.setFirstName(cust.getFirstName());
            newCustomer.setLastName(cust.getLastName());
            newCustomer.setEmail(cust.getEmail());
            newCustomer.setPhoneNumber(cust.getPhoneNumber());
            newCustomer.setStreet(cust.getStreet());
            newCustomer.setPostalCode(cust.getPostalCode());
            newCustomer.setAddressNumber(cust.getAddressNumber());
            newCustomer.setCountry(cust.getCountry());
            newCustomer.setRegion(cust.getRegion());
            newCustomer.setCity(cust.getCity());
            newCustomer.setDescription(cust.getDescription());

            UserModel newUser = new UserModel();
            newUser.setUsername(cust.getUsername());
            newUser.setPassword(cust.getPassword());
            newUser.setRole("customer");
            newUser.setActiveStatus(true);

            newUser.setCustomer(newCustomer);

            customerDb.save(newCustomer);
            userDb.save(newUser);

            response.setStatus(200);
            response.setMessage("Input Success");
            response.setResult(cust);
        }
        return response;
    }

    //Edit specific customer
    @PostMapping(value = "/editCustomer")
    @ResponseBody
    public BaseResponse<AddCustomerModel> editCustomer(@RequestBody AddCustomerModel cust,
                                             BindingResult bindingResult,
                                             @RequestParam(value = "id") Long id){
        BaseResponse<AddCustomerModel> response = new BaseResponse<AddCustomerModel>();

        BaseResponse<AddCustomerModel> response2 = response;
        if (bindingResult.hasErrors()) {
            response2.setStatus(500);
            response2.setMessage("Error");
        } else {
            CustomerModel customer = customerDb.findById(id).get();
            customer.setTitle(cust.getTitle());
            customer.setCustName(cust.getCustName());
            customer.setFirstName(cust.getFirstName());
            customer.setLastName(cust.getLastName());
            customer.setEmail(cust.getEmail());
            customer.setPhoneNumber(cust.getPhoneNumber());
            customer.setStreet(cust.getStreet());
            customer.setPostalCode(cust.getPostalCode());
            customer.setAddressNumber(cust.getAddressNumber());
            customer.setCountry(cust.getCountry());
            customer.setRegion(cust.getRegion());
            customer.setCity(cust.getCity());
            customer.setDescription(cust.getDescription());

            customerDb.save(customer);

            response2.setStatus(200);
            response2.setMessage("Edit Success");
            response2.setResult(cust);
        }
        return response2;
    }

    // Edit status akun customer
    @PostMapping(value = "/changeCustomerStatus")
    @ResponseBody
    public BaseResponse<CustomerModel> changeCustomerStatus(BindingResult bindingResult,
                                             @RequestParam(value = "id") long id){
        BaseResponse<CustomerModel> response = new BaseResponse<CustomerModel>();

        System.out.println("test");
        if(bindingResult.hasErrors()){
            response.setStatus(_500);
            response.setMessage("Error");
        }
        else{
            CustomerModel customer = customerDb.findById(id);
            UserModel user = userDb.findById(customer.getUser().getId()).get();
            if(user.getActiveStatus()){
                user.setActiveStatus(false);
            }
            else{
                user.setActiveStatus(true);
            }
            user.setCustomer(customer);

            userDb.save(user);

            response.setStatus(200);
            response.setMessage("Edit Success");
            response.setResult(customer);
        }
        return response;
    }
}