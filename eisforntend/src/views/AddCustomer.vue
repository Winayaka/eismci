<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-start wrap>
      <v-flex xs12 md4>
        <material-card class="v-card-profile" title="Add Customer" width="600">
          <p class="style font-italic">Please fill out new customer information below.</p>
          <v-card-text class="text-xs-left">
            <v-text-field label="Title" v-model="konten.title" :rules="rules.titleRules" required/>
            <v-text-field label="Name" v-model="konten.custName" :rules="rules.nameRules" required/>
            <v-text-field
              label="First Name"
              v-model="konten.firstName"
              :rules="rules.nameRules"
              required
            />
            <v-text-field
              label="Last Name"
              v-model="konten.lastName"
              :rules="rules.nameRules"
              required
            />
            <v-text-field label="E-Mail" v-model="konten.email" :rules="rules.emailRules" required/>
            <v-text-field
              label="Phone Number"
              v-model="konten.phoneNumber"
              :rules="rules.phoneNumberRules"
              required
            />
            <v-text-field label="Street" v-model="konten.street" :rules="rules.nameRules" required/>
            <v-text-field
              label="Address Number"
              v-model="konten.addressNumber"
              :rules="rules.addressNumberRules"
              required
            />
            <v-text-field
              label="Postal Code"
              v-model="konten.postalCode"
              :rules="rules.postalCodeRules"
              required
            />
            <v-text-field label="City" v-model="konten.city" :rules="rules.nameRules" required/>
            <v-text-field label="Region" v-model="konten.region" :rules="rules.nameRules" required/>
            <v-text-field
              label="Country"
              v-model="konten.country"
              :rules="rules.nameRules"
              required
            />
            <v-text-field label="Description" v-model="konten.description"/>

            <v-btn color="success" round class="font-weight-light" @click="addCustomer(konten)">Add</v-btn>
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
        <material-card class="v-card-profile" title="User Details" width="400">
          <v-text-field
            label="Username"
            v-model="konten.user.username"
            :rules="rules.usernameRules"
          />
          <v-text-field
            label="Password"
            v-model="konten.user.password"
            :rules="rules.passwordRules"
            :type="show1 ? 'text' : 'password'"
          />
          <v-text-field
            label="Repeat Password"
            v-model="konten.user.repeatPassword"
            :rules="rules.repeatPasswordRules"
            :type="show1 ? 'text' : 'password'"
          />
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axios from "axios";
export default {
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
      description: "",
      user: {
        username: "",
        password: "",
        repeatPassword: ""
        // role : ""
      }
    },
    rules: {
      titleRules: [
        v => !!v || "The input is required",
        v => /^[a-zA-Z]+$/.test(v) || "Title can only be letters"
      ],
      nameRules: [
        v => !!v || "The input is required",
        v => /^[a-zA-Z]+$/.test(v) || "This field can only be letters"
      ],
      emailRules: [
        v => !!v || "The input is required",
        v => /.+@.+/.test(v) || "E-mail must be valid"
      ],
      phoneNumberRules: [
        v => !!v || "The input is required",
        v => /^[0-9]\d*$/.test(v) || "Phone number can only be numbers"
      ],
      streetRules: [
        v => !!v || "The input is required",
        v =>
          /^[a-zA-Z0-9_]+$/.test(v) || "Street can only be letters and number"
      ],
      addressNumberRules: [
        v => !!v || "The input is required",
        v => /^[0-9]\d*$/.test(v) || "Address number can only be numbers"
      ],
      postalCodeRules: [
        v => !!v || "The input is required",
        v => /^[0-9]\d*$/.test(v) || "Postal code can only be numbers"
      ],
      usernameRules: [
        v => !!v || "The input is required",
        v =>
          /^[a-zA-Z0-9_]+$/.test(v) || "Username can only be letters and number"
      ],
      passwordRules: [
        v => !!v || "The input is required",
        v =>
          /^[a-zA-Z0-9]+$/.test(v) || "Password can only be letters and numbers"
      ],
      repeatPasswordRules: [
        v => !!v || "The input is required",
        v =>
          /^[a-zA-Z0-9]+$/.test(v) || "Password can only be letters and numbers"
      ]
    },
    edit: "",
    namaStaff: "",
    activeStatus: ""
  }),
  mounted() {},
  methods: {
    showAlert(a) {
      this.$router.push({
        path: "/customer/detail",
        query: { custName: a.custName }
      });
    },
    cancel() {
      var r = confirm("are you sure want to cancel?");
      if (r) {
        this.$router.push({ path: "/customer/" });
      }
    },
    addCustomer(customer) {
      console.log("tombol addCustomer ditekan");

      var isOk = true;

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
      if (customer.street == "" || !/^[ a-zA-Z0-9]+$/.test(customer.street)) {
        isOk = false;
        console.log("jalan kosong");
      }
      if (
        customer.addressNumber == "" ||
        !/^[ a-zA-Z0-9]+$/.test(customer.addressNumber)
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
      if (
        customer.user.username == "" ||
        !/^[a-zA-Z0-9_]+$/.test(customer.user.username)
      ) {
        isOk = false;
        console.log("username kosong");
      }
      if (
        customer.user.password == "" ||
        !/^[a-zA-Z0-9]+$/.test(customer.user.password)
      ) {
        isOk = false;
        console.log("password kosong");
      }

      if (customer.user.password != customer.user.repeatPassword) {
        isOk = false;
        console.log("password berbeda");
      }

      if (isOk) {
        console.log("ok gan");
        axios
          .post("http://localhost:8099/api/addCustomer", {
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
            description: customer.description,
            username: customer.user.username,
            password: customer.user.password
          })
          .then(function(response) {
            console.log(response);
            alert("Add Customer Success");
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