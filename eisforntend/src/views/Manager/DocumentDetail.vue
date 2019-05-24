<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-center wrap>
      <v-flex md12>
        <material-card color="green" title="Shipping Instruction Details">
          <v-layout>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Document Name</p>
                  </b>
                </td>
                <td>: {{ document.name }}</td>
              </tr>
            </v-flex>
          </v-layout>

          <v-flex xs12 md6>
            <tr>
              <td>
                <b>
                  <p>Created Date</p>
                </b>
              </td>
              <td>: {{ document.createdDate }}</td>
            </tr>
          </v-flex>

          <v-flex xs12 md6>
            <tr>
              <td>
                <b>
                  <p>Last Change</p>
                </b>
              </td>
              <td>: {{ document.lastChange }}</td>
            </tr>
          </v-flex>

          <v-flex xs12 md6>
            <tr>
              <td>
                <b>
                  <p>Status Document</p>
                </b>
              </td>
              <td>: {{ document.status }}</td>
            </tr>
          </v-flex>

          <v-flex xs12 md6>
            <tr>
              <td>
                <b>
                  <p>Document</p>
                </b>
              </td>
              <td>
                <v-btn color="success" dark class="mb-2" @click="download(document)">Download Here</v-btn>
              </td>
            </tr>
          </v-flex>
          <br>
          <h5>Comment</h5>
          <hr>
          <div>
            <div :key="index" v-for="(item, index) in submited">
              <p>{{ item.sendDate }}</p>
              <v-text-field :value="item.content" :label="item.sender" box readonly></v-text-field>
            </div>
            <div :key="index" v-for="(item, index) in notes">
              <p>{{ item.sendDate }}</p>
              <v-text-field :value="item.content" :label="item.sender" box readonly></v-text-field>
            </div>
            <hr>
            <h6>Add Comment</h6>
            <v-text-field
              solo
              label="Sender Name"
              :value="notes.sender"
              v-model="editedNotes.sender"
            />
            <v-textarea
              solo
              name="input-7-1"
              label="Add Comment"
              :value="notes.content"
              v-model="editedNotes.content"
            ></v-textarea>
            <v-btn color="success" dark class="mb-2" @click="send(notes)">Send</v-btn>
          </div>
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
      id: "",
      name: "",
      lastChange: "",
      createdDate: "",
      status: "",
      fileName: ""
    },
    notes: [],
    editedNotes: {
      sender: "",
      content: "",
      createdDate: "",
      createdTime: "",
      sendDate: "",
      roleId: ""
    },
    submitedDoc: {
      sender: "",
      content: "",
      createdDate: "",
      createdTime: "",
      sendDate: "",
      roleId: ""
    },
    docId: "",
    submited: []
  }),

  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/login");
      console.log("belon login");
    }
    //rolenya sokap?
    if (this.$session.get("role") !== "Manager") {
      this.$router.push("/");
      console.log("bukan staff");
    }
  },

  mounted() {
    var id = this.$route.query.id;
    console.log(id);
    this.docId = id;

    Axios.get("http://localhost:8099/api/document?id=" + id)
      .then(response => {
        this.document = response.data.result;
      })
      .then(console.log(this.response))
      .catch(function error(params) {});

    Axios.get("http://localhost:8099/api/note?id=" + id)
      .then(response => {
        this.submited = response.data.result;
      })
      .then(console.log(this.response))
      .catch(function error(params) {});
  },
  methods: {
    download(a) {
      console.log(this.docId);
      Axios({
        url: "http://localhost:8099/api/download?id=" + this.docId, //your url
        method: "GET",
        responseType: "blob" // important
      }).then(response => {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", this.document.fileName); //or any other extension
        document.body.appendChild(link);
        link.click();
      });
    },
    send() {
      this.editedNotes.roleId = this.$session.get("id_role");
      let currdatetime = new Date();
      this.editedNotes.sendDate = currdatetime.toString();

      // this.submitedDoc.sender = this.editedNotes.sender;
      // this.submitedDoc.content = this.editedNotes.content;
      // this.submitedDoc.createdDate = this.editedNotes.createdDate;
      // this.submitedDoc.createdTime = this.editedNotes.createdTime;
      // this.submitedDoc.sendDate = this.editedNotes.sendDate;
      // this.submitedDoc.roleId = this.editedNotes.roleId;

      var a = {
        sender: "",
        content: "",
        createdDate: "",
        createdTime: "",
        sendDate: "",
        roleId: ""
      };

      a.sender = this.editedNotes.sender;
      a.content = this.editedNotes.content;
      a.createdDate = this.editedNotes.createdDate;
      a.createdTime = this.editedNotes.createdTime;
      a.sendDate = this.editedNotes.sendDate;
      a.roleId = this.editedNotes.roleId;

      this.notes = [...this.notes, a];

      console.log(this.docId);
      Axios.post("http://localhost:8099/api/add/note", {
        documentId: this.docId,
        doc: this.notes
      });
    }
  }
};
</script>

