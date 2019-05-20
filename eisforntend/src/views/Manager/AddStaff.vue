<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-start wrap>
      <v-flex xs12 md4>
        <material-card class="v-card-profile" title="Add Staff" width="600">
          <v-card-text class="text-xs-left">
            <v-flex xs12 md4>
              <v-text-field
                label="Name"
                :value="konten.name"
                v-model="konten.name"
                :rules="rules.nameRules"
                required
              />
            </v-flex>
            <v-flex xs12 md4>
              <v-text-field
                label="E-Mail"
                :value="konten.email"
                v-model="konten.email"
                :rules="rules.emailRules"
                required
                type="email"
              />
            </v-flex>
            <v-flex xs12 md4>
              <v-text-field
                label="Phone Number"
                :value="konten.phoneNum"
                v-model="konten.phoneNum"
                :rules="rules.phoneNumRules"
                required
              />
            </v-flex>
            <v-flex xs12 md4>
              <v-radio-group v-model="konten.gender" label="Gender" required>
                <v-radio label="Male" value="true"></v-radio>
                <v-radio label="Female" value="false"></v-radio>
              </v-radio-group>
            </v-flex>
            <v-flex xs12 md4>
              <v-text-field
                label="Birth Place"
                :value="konten.birthplace"
                v-model="konten.birthplace"
                :rules="rules.birthplaceRules"
                required
              />
            </v-flex>
            <v-flex xs12 md4>
              <v-text-field
                label="Birth Date"
                type="date"
                :value="konten.birthDate"
                v-model="konten.birthDate"
                :rules="rules.birthDateRules"
                required
              />
            </v-flex>
          </v-card-text>
          <v-btn
            color="success"
            round
            align="right"
            class="font-weight-light"
            @click="addStaff(konten)"
          >Add staff</v-btn>
          <v-btn
            color="warning"
            round
            align="right"
            class="font-weight-light"
            @click="cancel()"
          >Cancel</v-btn>
        </material-card>
      </v-flex>
    </v-layout>

    <v-layout justify-start wrap>
      <v-flex xs12 md4>
        <material-card class="v-card-profile" title="User Details" width="400">
          <v-flex xs12 md4>
            <v-text-field
              label="Username"
              :value="konten.user.username"
              v-model="konten.user.username"
              :rules="rules.usernameRules"
            />
          </v-flex>
          <v-flex xs12 md4>
            <v-text-field
              label="Password"
              :value="konten.user.password"
              v-model="konten.user.password"
              :rules="rules.passwordRules"
              :type="show1 ? 'text' : 'password'"
            />
          </v-flex>
          <v-flex xs12 md4>
            <v-text-field
              label="Repeat Password"
              v-model="konten.user.repeatPassword"
              :rules="rules.repeatPasswordRules"
              :type="show1 ? 'text' : 'password'"
            />
          </v-flex>
          <v-flex xs12 md4>
            <v-radio-group v-model="konten.user.role" label="Role" required>
              <v-radio label="Export Staff" value="StaffExport"></v-radio>
              <v-radio label="Export Manager" value="Manager"></v-radio>
              <v-radio label="Administrative Staff" value="StaffAdministrative"></v-radio>
            </v-radio-group>
          </v-flex>
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import Axios from "axios";
export default {
  data: () => ({
    konten: {
      name: "",
      email: "",
      phoneNum: "",
      gender: "",
      birthplace: "",
      birthDate: "",
      user: {
        username: "",
        password: "",
        repeatPassword: "",
        role: ""
      }
    },
    rules: {
      nameRules: [
        v => !!v || "The input is required",
        v => /^[a-zA-Z]+$/.test(v) || "Name can only be letters"
      ],
      emailRules: [
        v => !!v || "The input is required",
        v => /.+@.+/.test(v) || "E-mail must be valid"
      ],
      phoneNumRules: [
        v => !!v || "The input is required",
        v => /^[0-9]\d*$/.test(v) || "Phone number can only be numbers"
      ],
      birthplaceRules: [
        v => !!v || "The input is required",
        v => /^[a-zA-Z]+$/.test(v) || "Birth Place can only be letters"
      ],
      birthDateRules: [v => !!v || "The input is required"],
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
      ],
      roleRules: [v => !!v || "The input is required"]
    },
    edit: "",
    namaStaff: "",
    activeStatus: ""
  }),
  mounted() {},
  methods: {
    showAlert(a) {
      this.$router.push({
        path: "/manager/staff/detail",
        query: { name: a.name }
      });
    },
    cancel() {
      var r = confirm("are you sure want to cancel?");
      if (r) {
        this.$router.push({ path: "/manager/staff" });
      }
    },
    beforeCreate: function() {
      if (!this.$session.exists()) {
        this.$router.push("/");
        console.log("belon login");
      }
      if (!this.$session.get("role") === "Manager") {
        this.$router.push("/");
        console.log("bukan staff");
      }
    },
    addStaff(staff) {
      console.log("tombol addStaff ditekan");
      var isOk = true;
      //validasi input
      //nama kosong
      if (staff.name == "" || !/^[a-zA-Z]+$/.test(staff.name)) {
        isOk = false;
        console.log("nama kosong");
      }
      if (staff.email == "" || !/.+@.+/.test(staff.email)) {
        isOk = false;
        console.log("email kosong");
      }
      if (staff.phoneNum == "" || !/^[0-9]\d*$/.test(staff.phoneNum)) {
        isOk = false;
        console.log("nohp kosong");
      }
      if (staff.gender == "") {
        isOk = false;
        console.log("gender kosong");
      }
      if (staff.birthplace == "" || !/^[a-zA-Z]+$/.test(staff.birthplace)) {
        isOk = false;
        console.log("birthplace kosong");
      }
      if (staff.birthDate == "") {
        isOk = false;
        console.log("birthDate kosong");
      }
      if (
        staff.user.username == "" ||
        !/^[a-zA-Z0-9_]+$/.test(staff.user.username)
      ) {
        isOk = false;
        console.log("username kosong");
      }
      if (
        staff.user.password == "" ||
        !/^[a-zA-Z0-9]+$/.test(staff.user.password)
      ) {
        isOk = false;
        console.log("password kosong");
      }
      if (staff.user.role == "") {
        isOk = false;
        console.log("role kosong");
      }
      if (staff.user.password != staff.user.repeatPassword) {
        isOk = false;
        console.log("password berbeda");
      }

      if (isOk) {
        var self = this;
        Axios.post("http://mciexport.herokuapp.com/api/addStaff", {
          name: staff.name,
          email: staff.email,
          phoneNum: staff.phoneNum,
          gender: staff.gender,
          birthplace: staff.birthplace,
          birthDate: staff.birthDate,
          username: staff.user.username,
          password: staff.user.password,
          role: staff.user.role
        })
          .then(function(response) {
            console.log(response);
            alert("Add Stafff Success");
            self.$router.push({ path: "/manager/staff" });
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