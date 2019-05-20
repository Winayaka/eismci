<template>
  <v-container
    fill-height
    fluid
    grid-list-xl>
    <v-layout
      justify-start
      wrap
    >
      <v-flex
        xs12
        md4
      >
        <material-card 
          class="v-card-profile"
          title="Staff Details"
          width=600           
        >
          <v-card-text 
          class="text-xs-left"
          >
            <v-flex
              xs12
              md4
            >
              <v-text-field
                label="Name"
                :value= "konten.name"
                v-model="konten.name"
                disabled
                />
            </v-flex>
            <v-flex
              xs12
              md4
            >            
              <v-text-field
                label="E-Mail"
                :value= "konten.email"
                v-model="konten.email"
                disabled
                />
            </v-flex>
            <v-flex
              xs12
              md4
            >            
              <v-text-field
                label="Phone Number"
                :value= "konten.phoneNum"
                v-model="konten.phoneNum"
                disabled
                />
            </v-flex>
            <v-flex
              xs12
              md4
            >
              <div v-if="konten.gender">
                <v-text-field
                label="Gender"
                value= "Male"
                disabled
                />
              </div>
              <div v-else>
                <v-text-field
                label="Gender"
                value= "Female"
                disabled
                />
              </div>
            </v-flex>
            <v-flex
              xs12
              md4
            >            
              <v-text-field
                label="Birth Place"
                :value= "konten.birthplace"
                v-model="konten.birthplace"
                disabled
                />
            </v-flex>
            <v-flex
              xs12
              md4
            >            
              <v-text-field
                label="Birth Date"
                type="date"
                :value= "konten.birthDate"
                v-model="konten.birthDate"
                disabled
                />
            </v-flex>

            <v-btn
              color="warning"
              round
              align = right
              class="font-weight-light"
              @click="toEdit(konten)">
              Edit
              </v-btn>
          </v-card-text>
        </material-card>
      </v-flex>
    </v-layout>

    <v-layout
      justify-start
      wrap
      >
            <v-flex
        xs12
        md4
      >
        <material-card 
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
                :value= "konten.username"
                v-model="konten.username"
                disabled
                />
            </v-flex>
            <v-flex
              xs12
              md4
            >
                <v-text-field
                label="Role"
                :value= "konten.role"
                v-model="konten.role"
                disabled
                />
            </v-flex>        
            <v-flex
              xs12
              md4
            >
              <div v-if="konten.activeStatus">
                <v-text-field
                label="Active Status"
                value= "Active"
                disabled
                />
              </div>
              <div v-else>
                <v-text-field
                label="Active Status"
                value= "Not Active"
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
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import Axios from 'axios'
export default {
  data: () => ({
    konten: {
      staffId : "",
      name : "",
      email : "",
      phoneNum : "",
      gender : "",
      birthplace : "",
      birthDate : "",
      username :"",
      role : ""
    }
  }),
    beforeCreate: function () {
    if (!this.$session.exists()) {
      this.$router.push('/')
      console.log("belon login")
    }
    if (!this.$session.get("role") === "Manager") {
      this.$router.push('/')
      console.log("bukan staff")
    }
    
  },
  mounted () {
    var namaStaff = this.$route.query.name
    console.log(this.$route.query.name)
    var url = 'http://mciexport.herokuapp.com/api/getStaff?name='+namaStaff
    console.log(url)
    Axios.get(url)
      .then(response => { 
      console.log(response.data.result)
        this.konten  = response.data.result
      })
      .then(console.log(this.konten))
      .catch(function (error){
        console.log(error)
      })      
  },
  methods: {
    activeDeactivate(staff){
      console.log("tombol aktivasi/deaktivasi ditekan")
      Axios.post('http://mciexport.herokuapp.com/api/changeStaffStatus?staffId='+ staff.staffId, {
      })
      .then(function (response) {
        console.log(response);
        this.konten.activeStatus = response.data.result.activeStatus
      })
      .catch(function (error) {
        console.log(error)
      })
     
    },
    toEdit(){
      this.$router.push({path :'/manager/staff/edit', query : {staffName : this.konten.name}} )
    }    
  }
}
</script>