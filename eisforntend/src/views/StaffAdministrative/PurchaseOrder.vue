<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-center wrap>
      <v-flex md12>
        <material-card
          color="green"
          title="Purchase Orders"
          text="Click row to see Purchase Order Detail"
        >
          <div>
            <v-btn
              to="/staffadministrative/po/add"
              color="success"
              round
              class="font-weight-light"
            >Add Purchase Order</v-btn>
          </div>
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
  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/login");
      console.log("belon login");
    }
    if (!this.$session.get("role") === "Manager") {
      this.$router.push("/");
      console.log("bukan staff");
    }
  },
  data: () => ({
    items: [
      {
        text: "Home",
        disabled: false,
        href: "/Manager"
      },
      {
        text: "Purchase Order",
        disabled: true,
        href: "/po"
      }
    ],
    headers: [
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
      this.$router.push({
        path: "/staffadministrative/po/detail",
        query: { id: a.id }
      });
    },
    addPurchaseOrder() {
      this.$router.push({ path: "/po/add" });
    }
  }
};
</script>