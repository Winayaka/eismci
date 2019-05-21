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
      <v-flex xs12 md4></v-flex>
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
    },

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
    var url = "http://localhost:8099/api/getCustomer?custName=" + namaCustomer;
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
    showAlert(a) {
      this.$router.push("/customer/detail/" + a.id);
    },
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
        this.$router.push({ path: "/customer/" });
      }
    },
    activateDeactivate(customer) {
      var currentStatus = customer.user.activeStatus;
      console.log("tombol aktivasi/deaktivasi ditekan");
      axios
        .post(
          "http://localhost:8099/api/changeCustomerStatus?id=" + customer.id,
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
      if (customer.street == "" || !/^[ a-zA-Z0-9]+$/.test(customer.street)) {
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

      if (isOk) {
        console.log("ok gan");
        console.log(customer);
        axios({
          method: "post",
          url: "http://localhost:8099/api/editCustomer?id=" + customer.id,
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
          .then(function(response) {
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