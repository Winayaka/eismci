<template>
  <div class="center-div">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-card-title class="justify-center primary white--text">
                <v-toolbar-title>Login form</v-toolbar-title>
              </v-card-title>
              <v-card-text>
                <v-form>
                  <v-text-field
                    single-line
                    prepend-icon="person"
                    label="Username"
                    v-model="loginForm.username"
                    :rules="rules.usernameRules"
                    required
                  ></v-text-field>
                  <v-text-field
                    single-line
                    prepend-icon="lock"
                    :append-icon="showPassword ? 'visibility' : 'visibility_off'"
                    @click:append="showPassword = !showPassword"
                    label="Password"
                    v-model="loginForm.password"
                    :rules="rules.passwordRules"
                    :type="showPassword ? 'text' : 'password'"
                    required
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="success"
                  round
                  align="right"
                  class="font-weight-light"
                  @click="login(loginForm)"
                >Login</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
    <v-dialog transition="slide-y-transition" v-model="successDialog" persistent width="400">
      <v-card>
        <v-card-title class="headline grey lighten-2" primary-title>Congratulations!</v-card-title>
        <v-card-text>You have successfull logged to system.</v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-layout align-center justify-end fill-height>
            <v-btn color="blue darken-1" flat @click="successDialog = false">Ok</v-btn>
          </v-layout>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<style>
.center-div {
  position: absolute;
  margin: auto;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  height: 50vh;
}
</style>

<script>
import Axios from "axios";
export default {
  data: () => ({
    successDialog: false,
    showPassword: false,
    loginForm: {
      username: "",
      password: ""
    },
    rules: {
      usernameRules: [
        v => !!v || "The input is required",
        v =>
          /^[a-zA-Z0-9_]+$/.test(v) || "Username can only be letters and number"
      ],
      passwordRules: [
        v => !!v || "The input is required",
        v =>
          /^[a-zA-Z0-9]+$/.test(v) || "Password can only be letters and numbers"
      ]
    }
  }),
  beforeCreate: function() {
    if (this.$session.exists()) {
      this.$router.push("/" + this.$session.get("role") + "/");
    }
  },
  mounted() {},
  methods: {
    login: function(loginForm) {
      var self = this;
      Axios.post("http://mciexport.herokuapp.com/api/login", {
        username: loginForm.username,
        password: loginForm.password
      })
        .then(response => {
          console.log(response.data.status);
          if (response.data.status === 200) {
            var role = response.data.result.role.split(" ").join("");
            this.$session.start();
            this.$session.set("role", role);
            this.$session.set("id_role", response.data.result.id);
            this.$session.set("name", response.data.result.name);

            console.log("data", response.data);
            console.log("name", response.data.result.name);
            console.log("status", response.data.status);
            console.log("role", response.data.result.role);
            console.log("role", response.data.result.id);
            console.log("ROLE", this.$session.get("role"));
            this.successDialog = true;

            this.$router.push("/" + this.$session.get("role"));
          } else {
            alert("Username or password is wrong1");
          }
        })
        .catch(function(error) {
          alert("Username or password is wrong");
        });
    }
  }
  // console.log("hai awl")
  // this.$session.start()
  // this.$session.set('jwt', this.tempToken)
  // this.$session.set('role', "staffExport")
  // Vue.http.headers.common['Authorization'] = 'StaffExport' + this.tempToken
  // this.$router.push('/')
  //   this.$http.post('http://somehost/user/login', {
  //     password: this.password,
  //     email: this.email
  //   }).then(function (response) {
  //     if (response.status === 200 && 'token' in response.body) {
  //       this.$session.start()
  //       this.$session.set('jwt', response.body.token)
  //       Vue.http.headers.common['Authorization'] = 'Bearer ' + response.body.token
  //       this.$router.push('/panel/search')
  //     }
  //   }, function (err) {
  //     console.log('err', err)
  //   })
};
</script>
