<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-start wrap>
      <v-flex xs12 md4>
        <material-card class="v-card-profile" title="Customer Details" width="600">
          <v-card-text>
            <v-container>
              <tr class="card-description font-weight-light">
                <td class="name font-weight-regular">Name</td>
                <td class="name font-weight-regular">: {{konten.custName}}</td>
              </tr>
              <br>
              <tr class="card-description font-weight-light">
                <td class="name font-weight-regular">Contact Person</td>
                <td class="name font-weight-regular">: {{konten.fullName}}</td>
              </tr>
              <br>
              <tr class="card-description font-weight-light">
                <td class="name font-weight-regular">E-Mail</td>
                <td class="name font-weight-regular">: {{konten.email}}</td>
              </tr>
              <br>
              <tr class="card-description font-weight-light">
                <td class="name font-weight-regular">Phone Number</td>
                <td class="name font-weight-regular">: {{konten.phoneNumber}}</td>
              </tr>
              <br>
              <tr class="card-description font-weight-light">
                <td class="name font-weight-regular">Address</td>
                <td class="name font-weight-regular">: {{konten.fullAddress}}</td>
              </tr>
              <br>
              <tr class="card-description font-weight-light">
                <td class="name font-weight-regular">Description</td>
                <td class="name font-weight-regular">: {{konten.description}}</td>
              </tr>
              <br>
            </v-container>
          </v-card-text>
          <!-- <v-card-text>
            <v-btn
              color="success"
              round
              class="font-weight-light"
            @click="toEdit(konten)"
            >Edit</v-btn>
          </v-card-text>-->
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
                disabled
                />
            </v-flex>
            <v-flex
              xs12
              md4
            >
              <v-text-field
                label="Password"
                v-model="konten.user.password"
                disabled
                />
            </v-flex>
            <v-flex
              xs12
              md4
            >
              <v-text-field
                  label="Role"
                  :value="konten.user.role"
                  disabled
                  />
            </v-flex>
            <v-flex
              xs12
              md4
            >
              <div v-if="konten.user.activeStatus" label="Active Status" required>
                <v-text-field 
                  label="Active"
                  value= "true"
                  disabled
                />
              </div>
                <div v-else>  
                  <v-text-field
                  label="Not Active"
                  value= "false"
                  disabled
                  />
              </div>
            </v-flex>
        
            <v-btn
              color="success"
              round
              class="font-weight-light"
              @click="activeDeactivate(konten)">
              Change Status
              </v-btn>
        </material-card>-->
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
    fullAddress: "",
    edit: "",
    namaCustomer: "",
    activeStatus: "",
    user: []
  }),
  computed: {
    konten() {
      var isi = {};
      isi = this.user;
      isi.fullAddress =
        isi.street +
        " " +
        isi.addressNumber +
        " " +
        isi.postalCode +
        " " +
        isi.city +
        " " +
        isi.region +
        " " +
        isi.country;
      isi.fullName = isi.firstName + " " + isi.lastName;
      console.log(this.isi);
      return isi;
    }
  },
  mounted() {
    var namaCustomer = this.$route.query.custName;
    axios({
      method: "get",
      url: "http://localhost:8099/api/getCustomer?custName=" + namaCustomer
    })
      .then(response => {
        console.log(response.data.result);
        this.user = response.data.result;

        // console.log(this.konten.custName)
        // console.log(this.konten)
      })
      .catch(function(error) {
        console.log(error);
      });
    console.log(this.konten);
  },
  methods: {
    // showAlert (a) {
    //   alert(a.custName)
    //   this.$router.push('/customer/detail/' + a.custName)
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
    // toEdit(){
    //   this.$router.push({path :'/customer/edit', query : {custName : this.konten.custName}} )
    // },
    activeDeactivate(customer) {
      var currentStatus = customer.user.activestatus;
      console.log("tombol aktivasi/deaktivasi ditekan");
      Axios.post(
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
    }
  }
};
</script>