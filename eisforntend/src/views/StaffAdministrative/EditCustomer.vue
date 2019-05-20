<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-start wrap>
      <v-flex xs12 md4>
        <material-card class="v-card-profile" title="Edit Customer" width="600">
          <p class="style font-italic">Please fill out customer information below.</p>
          <v-card-text class="text-xs-left">
            <v-text-field label="Title" v-model="konten.title" required/>
            <v-text-field label="Name" v-model="konten.custName" required/>
            <v-text-field label="First Name" v-model="konten.firstName" required/>
            <v-text-field label="Last Name" v-model="konten.lastName" required/>
            <v-text-field label="E-Mail" v-model="konten.email" required/>
            <v-text-field label="Phone Number" v-model="konten.phoneNumber" required/>
            <v-text-field label="Street" v-model="konten.street" required/>
            <v-text-field label="Address Number" v-model="konten.addressNumber" required/>
            <v-text-field label="Postal Code" v-model="konten.postalCode" required/>
            <v-text-field label="City" v-model="konten.city" required/>
            <v-text-field label="Region" v-model="konten.region" required/>
            <v-text-field label="Country" v-model="konten.country" required/>
            <v-text-field label="Description" v-model="konten.description"/>

            <v-btn
              color="success"
              round
              class="font-weight-light"
              @click="editCustomer(konten)"
            >Confirm Change</v-btn>
            <v-btn
              color="warning"
              round
              align="right"
              class="font-weight-light"
              @click="cancel()"
            >Cancel</v-btn>
          </v-card-text>
        </material-card>
      </v-flex>
    </v-layout>
    <v-layout justify-start wrap>
      <v-flex xs12 md4>
        <!-- <material-card 
        class="v-card-profile"
        title="User Details"
        width = 400
        >
            <v-flex
              xs12
              md4
            >
              <v-text-field
                label="Username"
                v-model="konten.user.username"
                />
            </v-flex>
            <v-flex
              xs12
              md4
            >
              <v-text-field
                label="Password"
                v-model="konten.user.password"
                :type="show1 ? 'text' : 'password'"
                />
            </v-flex>
            <v-flex
              xs12
              md4
            >
              <v-text-field
                label="Repeat Password"
                v-model="konten.user.repeatPassword"
                :type="show1 ? 'text' : 'password'"
                />
        </v-flex>-->
        <!-- <v-flex
              xs12
              md4
            >
              <v-radio-group v-model="konten.user.role" label="Role" required>
                <v-radio  
                  label="Customer"
                  value= "customer"
                ></v-radio>
              </v-radio-group> 
        </v-flex>-->
        <!-- <v-flex
              xs12
              md4
            >
              <v-radio-group v-model="konten.user.activeStatus" label="Active Status" required>
                <v-radio  
                  label="Active"
                  value= "true"
                ></v-radio>
                <v-radio  
                  label="Not Active"
                  value= "false"
                ></v-radio>
              </v-radio-group> 
        </v-flex>-->
        <!-- </material-card> -->
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axios from "axios";
export default {
  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/login");
      console.log("belum login");
    }

    if (!this.$session.get("role") === "StaffAdministrative") {
      this.$router.push("/");
      console.log("bukan staff admin");
    }
  },

  data: () => ({
    konten: {
      title: "",
      custName: "",
      firstName: "",
      lastName: "",
      email: "",
      phoneNumber: "",
      street: "",
      addressNumber: "",
      postalCode: "",
      city: "",
      region: "",
      country: "",
      description: ""
      // user : {
      //   username : "",
      //   password : "",
      //   repeatPassword : "",
      //   // role : ""
      // },
    },
    // rules : {
    //   nameRules : [v => !!v || "The input is required",
    //     v => /^[a-zA-Z]+$/.test(v) || "Name can only be letters"
    //   ],
    //   emailRules : [
    //     v => !!v || "The input is required",
    //     v => /.+@.+/.test(v) || "E-mail must be valid"
    //   ],
    //   phoneNumRules : [
    //     v => !!v || "The input is required",
    //     v => /^[0-9]\d*$/.test(v) || "Phone number can only be numbers"
    //   ],
    //   birthplaceRules : [
    //     v => !!v || "The input is required",
    //     v => /^[a-zA-Z]+$/.test(v) || "Birth Place can only be letters"
    //   ],
    //   birthDateRules : [v => !!v || "The input is required"],
    //   usernameRules : [
    //     v => !!v || "The input is required",
    //     v => /^[a-zA-Z0-9_]+$/.test(v) || "Username can only be letters and number"
    //   ],
    //   passwordRules : [
    //     v => !!v || "The input is required",
    //     v => /^[a-zA-Z0-9]+$/.test(v) || "Password can only be letters and numbers"
    //   ],
    //   repeatPasswordRules : [
    //     v => !!v || "The input is required",
    //     v => /^[a-zA-Z0-9]+$/.test(v) || "Password can only be letters and numbers"
    //   ],
    //   roleRules : [v => !!v || "The input is required"],
    // },
    // edit : "",
    // namaStaff : '',
    // activeStatus : ""
    oldData: {
      title: "",
      custName: "",
      firstName: "",
      lastName: "",
      email: "",
      phoneNumber: "",
      street: "",
      addressNumber: "",
      postalCode: "",
      city: "",
      region: "",
      country: "",
      description: ""
      // user : {
      //   username : "",
      //   password : "",
      //   repeatPassword : "",
      //   // role : ""
      // },
    },
    editProperty: {
      name: "edit",
      nowEdit: false
    },
    namaCustomer: "",
    activeStatus: ""
  }),

  mounted() {
    var namaCustomer = this.$route.query.custName;
    console.log(this.$route.query.custName);
    var url =
      "http://mciexport.herokuapp.com/api/getCustomer?custName=" + namaCustomer;
    console.log(url);
    axios
      .get(url)
      .then(response => {
        this.konten = response.data.result;
        this.oldData = response.data.result;
      })
      .then(console.log(this.response))
      .catch(function(error) {
        console.log(error);
      });
  },
  methods: {
    // showAlert(a) {
    //  this.$router.push('/customer/detail/' + a.id)
    // },
    onEdit() {
      if (this.editProperty.nowEdit) {
        this.editProperty.nowEdit = false;
        this.editProperty.custName = "edit";
        console.log(this.konten);
        console.log(this.oldData);
        this.konten = this.oldData;
        console.log(this.konten);
        console.log(this.oldData);
      } else {
        this.editProperty.nowEdit = true;
        this.editProperty.custName = "cancel";
      }
    },
    cancel() {
      var r = confirm("are you sure want to cancel?");
      if (r) {
        this.$router.push({ path: "/staffadministrative/customer/" });
      }
    },
    activateDeactivate(customer) {
      var currentStatus = customer.user.activeStatus;
      console.log("tombol aktivasi/deaktivasi ditekan");
      axios
        .post(
          "http://mciexport.herokuapp.com/api/changeCustomerStatus?id=" +
            customer.id,
          {}
        )
        .then(function(response) {
          console.log(response);
          if (currentStatus) {
            this.currentStatus = false;
          } else {
            this.currentStatus = true;
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    editCustomer(customer) {
      //ada yg kalo datanya spasi gak bisa diadd
      console.log("tombol confirm change ditekan");
      console.log(customer);
      var isOk = true;
      //validasi input
      //nama kosong
      if (customer.title == "" || !/^[a-zA-Z]+$/.test(customer.title)) {
        isOk = false;
        console.log("title kosong");
      }
      if (customer.custName == "" || !/^[ a-zA-Z]+$/.test(customer.custName)) {
        isOk = false;
        console.log("nama kosong");
      }
      if (
        customer.firstName == "" ||
        !/^[ a-zA-Z]+$/.test(customer.firstName)
      ) {
        isOk = false;
        console.log("nama depan kosong");
      }
      if (customer.lastName == "" || !/^[ a-zA-Z]+$/.test(customer.lastName)) {
        isOk = false;
        console.log("nama belakang kosong");
      }
      if (customer.email == "" || !/.+@.+/.test(customer.email)) {
        isOk = false;
        console.log("email kosong");
      }
      if (
        customer.phoneNumber == "" ||
        !/^[0-9]\d*$/.test(customer.phoneNumber)
      ) {
        isOk = false;
        console.log("no hp kosong");
      }
      if (customer.street == "" || !/^[ .a-zA-Z0-9]+$/.test(customer.street)) {
        isOk = false;
        console.log("jalan kosong");
      }
      if (
        customer.addressNumber == "" ||
        !/^[a-zA-Z0-9]+$/.test(customer.addressNumber)
      ) {
        isOk = false;
        console.log("no alamat kosong");
      }
      if (
        customer.postalCode == "" ||
        !/^[0-9]\d*$/.test(customer.postalCode)
      ) {
        isOk = false;
        console.log("kode pos kosong");
      }
      if (customer.city == "" || !/^[ a-zA-Z]+$/.test(customer.city)) {
        isOk = false;
        console.log("kota kosong");
      }
      if (customer.region == "" || !/^[ a-zA-Z]+$/.test(customer.region)) {
        isOk = false;
        console.log("region kosong");
      }
      if (customer.country == "" || !/^[ a-zA-Z]+$/.test(customer.country)) {
        isOk = false;
        console.log("negara kosong");
      }
      if (
        customer.description == "" ||
        !/^[ a-zA-Z0-9]+$/.test(customer.description)
      ) {
        isOk = false;
        console.log("deskripsi kosong");
      }
      //  if (customer.user.username == "" || !/^[a-zA-Z0-9_]+$/.test(customer.user.username)){
      //    isOk = false
      //    console.log("username kosong")
      //  }
      //  if (customer.user.password == "" || !/^[a-zA-Z0-9]+$/.test(customer.user.password)){
      //    isOk = false
      //    console.log("password kosong")

      //  if (customer.user.role == ""){
      //    isOk = false
      //    console.log("role kosong")
      //  }
      //  if(customer.user.password != customer.user.repeatPassword){
      //    isOk = false
      //    console.log("password berbeda")
      //  }

      if (isOk) {
        console.log("ok gan");
        console.log(customer);
        axios({
          method: "post",
          url:
            "http://mciexport.herokuapp.com/api/editCustomer?id=" + customer.id,
          data: {
            title: customer.title,
            custName: customer.custName,
            firstName: customer.firstName,
            lastName: customer.lastName,
            email: customer.email,
            phoneNumber: customer.phoneNumber,
            street: customer.street,
            addressNumber: customer.addressNumber,
            postalCode: customer.postalCode,
            city: customer.city,
            region: customer.region,
            country: customer.country,
            description: customer.description
          }
        })
          //   axios.post('http://mciexport.herokuapp.com/api/editCustomer?=' + customer.id,
          //   {
          //   title : customer.title,
          //   custName : customer.custName,
          //   firstName : customer.firstName,
          //   lastName : customer.lastName,
          //   email : customer.email,
          //   phoneNumber : customer.phoneNumber,
          //   street : customer.street,
          //   addressNumber : customer.addressNumber,
          //   postalCode : customer.postalCode,
          //   city : customer.city,
          //   region : customer.region,
          //   country : customer.country,
          //   description : customer.description,
          //   // username : customer.user.username,
          //   // password : customer.user.password,
          //   // role : customer.user.role,
          // })
          .then(response => {
            console.log(response);
            alert("Edit Customer Success");
          })
          .catch(function(error) {
            console.log(error);
            alert(error);
          });
      } else {
        alert("Input data is wrong");
      }
    }
  }
};
</script>