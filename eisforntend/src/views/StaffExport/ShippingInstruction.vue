<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-center wrap>
      <v-flex md12>
        <material-card
          color="green"
          title="Shipping Instruction"
          text="Click row to see Shipping Instruction"
        >
          <v-data-table :headers="headers" :items="konten">
            <template slot="headerCell" slot-scope="{ header }">
              <span
                class="subheading font-weight-light text-success text--darken-3"
                v-text="header.text"
              />
            </template>
            <template v-slot:items="props">
              <tr @click="showAlert(props.item)">
                <td>{{ props.item.number }}</td>
                <td>{{ props.item.invoice }}</td>
                <td>{{ props.item.liner }}</td>
                <td>{{ props.item.finalDestination }}</td>
                <td>{{ props.item.shipmentStatus }}</td>
                <td>{{ props.item.paymentStatus }}</td>
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
  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/login");
      console.log("belon login");
    }

    if (!this.$session.get("role") === "StaffExport") {
      this.$router.push("/");
      console.log("bukan staff");
    }
  },
  data: () => ({
    headers: [
      {
        sortable: false,
        text: "Number",
        value: "number"
      },
      {
        sortable: false,
        text: "Invoice",
        value: "invoice"
      },
      {
        sortable: false,
        text: "Liner",
        value: "liner"
      },
      {
        sortable: false,
        text: "Final Destination",
        value: "finalDestination"
      },
      {
        sortable: false,
        text: "Shipment Status",
        value: "shipmentStatus"
      },
      {
        sortable: false,
        text: "Payment Status",
        value: "paymentStatus"
      }
    ],
    konten: []
  }),
  mounted() {
    //var idStaff = this.$route.query.id;
    var idStaff = this.$session.get("id_role")
    Axios.get("http://localhost:8099/api/get/si/assigned?StaffId=" + idStaff)
      .then(response => {
        this.konten = response.data.result;
      })
      .then(console.log(this.response));
  },
  methods: {
    showAlert(a) {
      this.$router.push({
        path: "/staffexport/si/detail",
        query: { id: a.id }
      });
    }
  }
};
</script>