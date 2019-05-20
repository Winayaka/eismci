<template>
  <div>
    <div :key="index" v-for="(item, index) in notes">
      <p>{{ item.createdDate }} {{ item.createdTime }}</p>
      <v-text-field :value="item.content" :label="item.sender" box readonly></v-text-field>
    </div>
    <hr>
    <h6>Add Comment</h6>
    <v-text-field solo label="Sender Name" :value="notes.sender" v-model="editedNotes.sender"/>
    <v-textarea
      solo
      name="input-7-1"
      label="Add Comment"
      :value="editedNotes.content"
      v-model="editedNotes.content"
    ></v-textarea>
    <v-btn color="success" dark class="mb-2" @click="send(notes)">Send</v-btn>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  props: {
    docId: {
      type: Number,
      required: true
    }
  },
  data: () => ({
    notes: [],
    editedNotes: {
      sender: "",
      content: "",
      createdDate: "",
      createdTime: ""
    }
  }),
  mounted() {
    Axios.get("http://mciexport.herokuapp.com/api/note?id=" + this.docId)
      .then(response => {
        this.notes = response.data.result;
      })
      .then(console.log(this.response))
      .catch(function error(params) {});
  },
  methods: {
    send() {
      var date = new Date();
      var dateNow =
        date.getFullYear() + "-" + date.getMonth() + "-" + date.getDay();
      this.editedNotes.createdDate = dateNow;
      var dt = new Date();
      var time = dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds();
      this.editedNotes.createdTime = time;
      this.notes.push(this.editedNotes);
      console.log(this.notes);
      Axios.post("http://mciexport.herokuapp.com/api/add/note", {
        documentId: this.docId,
        doc: this.notes
      });
    }
  },
  data: () => ({
    notes: [],
    editedNotes: {
      sender: "",
      content: "",
      createdDate: "",
      createdTime: ""
    }
  }),
  mounted() {
    Axios.get("http://mciexport.herokuapp.com/api/note?id=" + this.docId)
      .then(response => {
        this.notes = response.data.result;
      })
      .then(console.log(this.response))
      .catch(function error(params) {});
  },
  methods: {
    send() {
      var date = new Date();
      var dateNow =
        date.getFullYear() + "-" + date.getMonth() + "-" + date.getDay();
      this.editedNotes.createdDate = dateNow;
      var dt = new Date();
      var time = dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds();
      this.editedNotes.createdTime = time;
      this.notes.push(this.editedNotes);
      console.log(this.notes);
      Axios.post("http://mciexport.herokuapp.com/api/add/note", {
        documentId: this.docId,
        doc: this.notes
      });
    }
  }
};
</script>
