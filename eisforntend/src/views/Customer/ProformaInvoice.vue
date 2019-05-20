<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-center wrap>
      <v-flex md12>
        <material-card color="green" title="Proforma Invoice List" text="Click row to see details.">
          <!-- <div>
            <v-btn
              to="/manager/pi/add"
              color="success"
              round
              class="font-weight-light"
            >Add Proforma Invoice</v-btn>
          </div>-->
          <v-data-table align="left" :headers="headers" :items="konten" hide-actions>
            <template slot="headerCell" slot-scope="{ header }">
              <span
                class="subheading font-weight-light text-success text--darken-3"
                v-text="header.text"
              />
            </template>
            <template v-slot:items="props">
              <tr @click="showAlert(props.item)">
                <td>{{ props.index+1 }}</td>
                <td>{{ props.item.number }}</td>
                <td>{{ props.item.date }}</td>
                <td>{{ props.item.finalDestination }}</td>
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
    role: "",
    headers: [
      {
        sortable: false,
        text: "No",
        value: "no"
      },
      {
        sortable: false,
        text: "Number",
        value: "number"
      },
      {
        sortable: false,
        text: "Date",
        value: "date"
      },
      {
        sortable: false,
        text: "Final Destination",
        value: "final destination"
      }
    ],
    konten: []
  }),

  mounted() {
    var self = this;
    Axios.get("http://mciexport.herokuapp.com/api/pi/all")
      .then(response => {
        this.konten = response.data.result;
        console.log(this.$session.get.role);
      })
      .then(console.log(this.response));
  },

  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/");
      console.log("belum login");
    }

    if (!this.$session.get("role") === "Manager") {
      alert(this.$session.get("role"));
      this.$router.push("/");
      console.log("bukan staff");
    }

    if (!this.$session.get("role") === "StaffAdministrative") {
      alert(this.$session.get("role"));
      this.$router.push("/");
      console.log("bukan staff");
    }

    if (!this.$session.get("role") === "Customer") {
      alert(this.$session.get("role"));
      this.$router.push("/");
      console.log("bukan staff");
    }
  },

  methods: {
    showAlert(a) {
      var role = this.$session.get("role");
      var roleUrl = role.toLowerCase();
      this.$router.push({ path: "/customer/pi/detail", query: { id: a.id } });
    }
  }
};
</script>