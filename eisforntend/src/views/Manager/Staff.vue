<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-center wrap>
      <v-flex md12>
        <material-card color="green" title="Staff List" text="Click staff to view detail">
          <div>
            <v-btn color="success" round class="font-weight-light" @click="addStaff()">Add Staff</v-btn>
          </div>
          <v-data-table :headers="headers" :items="konten" hide-actions>
            <template slot="headerCell" slot-scope="{ header }">
              <span
                class="subheading font-weight-light text-success text--darken-3"
                v-text="header.text"
              />
            </template>
            <template v-slot:items="props">
              <tr @click="showAlert(props.item)">
                <td>{{ props.item.staffId }}</td>
                <td>{{ props.item.name }}</td>
                <td>
                  <div v-if="props.item.gender">Male</div>
                  <div v-else>Female</div>
                </td>
                <td>{{ props.item.email }}</td>
                <td>{{ props.item.phoneNum }}</td>
              </tr>
            </template>
          </v-data-table>
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import Axios from "axios";

export default {
  data: () => ({
    headers: [
      {
        sortable: false,
        text: "Id",
        value: "staffId"
      },
      {
        sortable: false,
        text: "Name",
        value: "name"
      },
      {
        sortable: false,
        text: "Gender",
        value: "gender"
      },
      {
        sortable: false,
        text: "E-mail",
        value: "email"
      },
      {
        sortable: false,
        text: "Phone Number",
        value: "phoneNum"
      }
    ],
    konten: []
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
    Axios.get("http://mciexport.herokuapp.com/api/getAllStaff")
      .then(response => {
        this.konten = response.data.result;
        console.log(response.data.result);
      })
      .then(console.log(this.response));
  },
  methods: {
    showAlert(a) {
      this.$router.push({
        path: "/manager/staff/detail",
        query: { name: a.name }
      });
    },
    addStaff() {
      this.$router.push({ path: "/manager/staff/add" });
    }
  }
};
</script>
