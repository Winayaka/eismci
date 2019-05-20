<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-start wrap>
      <v-flex xs12 md4>
        <material-card class="v-card-profile" title="Staff Details" width="600">
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
            <v-btn
              color="success"
              round
              class="font-weight-light"
              @click="editStaff(konten)"
            >confirm Change</v-btn>

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
          <v-flex xs12 md4>
            <v-text-field
              label="Username"
              :value="konten.username"
              v-model="konten.username"
              :rules="rules.usernameRules"
            />
          </v-flex>
          <v-flex xs12 md4>
            <v-radio-group v-model="konten.role" label="Role" required>
              <v-radio label="Export Staff" value="export staff"></v-radio>
              <v-radio label="Export Manager" value="export manager"></v-radio>
              <v-radio label="Administrative Staff" value="administrative staff"></v-radio>
            </v-radio-group>
          </v-flex>
          <v-flex xs12 md4>
            <v-radio-group v-model="konten.activeStatus" label="Active Status" required>
              <v-radio label="Active" value="true"></v-radio>
              <v-radio label="Not Active" value="false"></v-radio>
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
      username: "",
      role: "",
      activeStatus: "",
      password: "placeholder"
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
      roleRules: [v => !!v || "The input is required"]
    }
  }),

  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/");
      console.log("belum login");
    }

    if (!this.$session.get("role") === "Manager") {
      this.$router.push("/");
      console.log("bukan staff");
    }
  },

  mounted() {
    var namaStaff = this.$route.query.staffName;
    console.log(this.$route.query.staffName);
    var url = "http://mciexport.herokuapp.com/api/getStaff?name=" + namaStaff;
    console.log(url);
    Axios.get(url)
      .then(response => {
        console.log(response.data.result);
        this.konten = response.data.result;
      })
      .then(console.log(this.konten))
      .catch(function(error) {
        console.log(error);
      });
  },
  methods: {
    // showAlert(a) {
    //   this.$router.push("/staff/detail/" + a.id);
    // },
    onEdit() {
      if (this.editProperty.nowEdit) {
        this.editProperty.nowEdit = false;
        this.editProperty.name = "edit";
        console.log(this.konten);
        console.log(this.oldData);
        this.konten = this.oldData;
        console.log(this.konten);
        console.log(this.oldData);
      } else {
        this.editProperty.nowEdit = true;
        this.editProperty.name = "cancel";
      }
    },
    cancel() {
      var r = confirm("are you sure want to cancel?");
      if (r) {
        this.$router.go(-1);
      }
    },
    editStaff(staff) {
      console.log("tombol confirm change ditekan");
      console.log(staff);
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
      if (staff.username == "" || !/^[a-zA-Z0-9_]+$/.test(staff.username)) {
        isOk = false;
        console.log("username kosong");
      }
      if (staff.role == "") {
        isOk = false;
        console.log("role kosong");
      }

      if (isOk) {
        var self = this;
        Axios.post(
          "http://mciexport.herokuapp.com/api/editStaff?staffId=" +
            staff.staffId,
          {
            name: staff.name,
            email: staff.email,
            phoneNum: staff.phoneNum,
            gender: staff.gender,
            birthplace: staff.birthplace,
            birthDate: staff.birthDate,
            username: staff.username,
            password: staff.password,
            role: staff.role,
            activeStatus: staff.activeStatus
          }
        )
          .then(function(response) {
            console.log(response);
            alert("Edit Stafff Success");
            self.$router.push({ path: "/manager/staff" });
          })
          .catch(function(error) {
            console.log(error);
          });
      } else {
        alert("Input Error");
      }
    }
  }
};
</script>
