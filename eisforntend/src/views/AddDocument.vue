<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-center wrap>
      <v-flex md12>
        <material-card
          color="green"
          title="Add Shipping Instruction"
          text="Here is a subtitle for this table"
        >
          <!--  
        ========================================================
        Form Document
        ========================================================
          -->
          <v-container>
            <v-form>
              <v-text-field label="Name" :value="document.name" v-model="document.name"/>

              <v-select
                label="Status"
                :items="status"
                item-text="number"
                v-model="document.status"
                required
              />

              <input label="File" type="file" @change="onFileSelected">
              <v-btn color="primary" dark class="mb-2" @click="onUpload">Upload</v-btn>
            </v-form>
          </v-container>
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import Axios from "axios";
export default {
  data: () => ({
    document: {
      name: "",
      fileName: "",
      status: "",
      siid: ""
    },
    status: ["Received", "valid", "On Process"],
    selectedFile: null
  }),
  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/login");
      console.log("belum login");
    }
    //rolenya sokap?
    if (!this.$session.get("role") === "staffExport") {
      this.$router.push("/");
      console.log("bukan staff");
    }

    if (!this.$session.get("role") === "Manager") {
      this.$router.push("/");
      console.log("bukan staff");
    }
  },

  mounted() {
    var id = this.$route.query.id;
    this.siid = id;
  },
  methods: {
    onFileSelected(event) {
      console.log(event);
      this.selectedFile = event.target.files[0];
      this.document.fileName = this.selectedFile.name;
      console.log(this.selectedFile);
    },
    onUpload() {
      console.log(this.siid);
      const fd = new FormData();

      fd.append("file", this.selectedFile);
      fd.append("status", this.document.status);
      fd.append("name", this.document.name);
      fd.append("fileName", this.document.fileName);
      fd.append("siid", this.siid);
      console.log(fd);
      Axios({
        method: "post",
        url: "http://mciexport.herokuapp.com/api/upload",
        data: fd,
        headers: {
          "content-type": "multipart/form-data"
        }
      });
    }
  }
};
</script>

