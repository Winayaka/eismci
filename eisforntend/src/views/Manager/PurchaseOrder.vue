<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-center wrap>
      <v-flex xs12 sm12 md12>
        <material-card color="green" title="Purchase Order List" text="Click row to see details.">
          <v-data-table align="left" :headers="headers" :items="konten">
            <template slot="headerCell" slot-scope="{ header }">
              <span
                class="subheading font-weight-light text-success text--darken-3"
                v-text="header.text"
              />
            </template>
            <template v-slot:items="props">
              <tr @click="showAlert(props.item)">
                <td>{{ props.item.id }}</td>
                <td>{{ props.item.number }}</td>
                <td>{{ props.item.date }}</td>
                <td>{{ props.item.shipment }}</td>
                <td>{{ props.item.namaSupplier }}</td>
                <td>{{ props.item.vessel }}</td>
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
        value: "id"
      },
      {
        sortable: false,
        text: "PO Number",
        value: "number"
      },
      {
        sortable: false,
        text: "Data",
        value: "date"
      },
      {
        sortable: false,
        text: "ETA",
        value: "shipment"
      },
      {
        sortable: false,
        text: "Supplier",
        value: "namaSupplier"
      },
      {
        sortable: false,
        text: "Vessel",
        value: "vessel"
      }
    ],
    konten: [],
    datetimebener: ""
  }),
  mounted() {
    Axios.get("http://localhost:8099/api/getAllPurchaseOrder")
      .then(response => {
        this.konten = response.data.result;
        this.datetimebener = this.konten.eta;
      })
      .then(console.log(this.response));
  },
  methods: {
    showAlert(a) {
      this.$router.push({ path: "/manager/po/detail", query: { id: a.id } });
    }
    // addPurchaseOrder() {
    //   this.$router.push({ path: "/po/add" });
    // }
  }
};
</script>
