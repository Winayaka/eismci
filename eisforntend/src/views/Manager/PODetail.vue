<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-center wrap>
      <v-flex md12>
        <material-card color="green" title="Purchase Order Details">
          <v-card-text class="text-xs-left">
            <v-container>
              <tr class="card-description font-weight-normal">
                <td>Number</td>
                <td>: {{konten.number}}</td>
              </tr>
              <br>
              <tr class="card-description font-weight-normal">
                <td>ETA</td>
                <td>: {{konten.shipment}}</td>
              </tr>
              <br>
              <tr class="card-description font-weight-normal">
                <td>Date</td>
                <td>: {{konten.date}}</td>
              </tr>
              <br>
              <tr class="card-description font-weight-normal">
                <td>Supplier</td>
                <td>: {{konten.namaSupplier}}</td>
              </tr>
              <br>
              <tr class="card-description font-weight-normal">
                <td>Vessel</td>
                <td>: {{konten.vessel}}</td>
              </tr>
              <br>
              <tr class="card-description font-weight-normal">
                <td>PO Number (Supplier)</td>
                <td>: {{konten.poNumSupplier}}</td>
              </tr>
              <br>
              <v-card-title>Product Detail</v-card-title>
              <v-card-text>
                <v-data-table :headers="headers" :items="konten.purchaseOrder" hide-actions>
                  <template slot="headerCell" slot-scope="{ header }">
                    <span
                      class="subheading font-weight-light text-success text--darken-3"
                      v-text="header.text"
                    />
                  </template>
                  <template v-slot:items="props">
                    <td>{{ props.item.product.name }}</td>
                    <td>{{ props.item.quantity }}</td>
                    <td>{{ props.item.freightCost }}</td>
                    <td>{{ props.item.pricePerUnit }}</td>
                    <td>{{ props.item.quantityRemaining }}</td>
                    <td>{{ props.item.containerQuantity }}</td>
                    <td>{{ props.item.date }}</td>
                    <td>{{ props.item.container.name }}</td>
                  </template>
                </v-data-table>
              </v-card-text>
            </v-container>
          </v-card-text>
        </material-card>
      </v-flex>
      <!-- <v-btn
        color="warning"
        round
        class="font-weight-light"
        @click="toEdit(konten)"
      >Edit Purchase Order</v-btn>-->
    </v-layout>
  </v-container>
</template>

<script>
import Axios from "axios";
export default {
  props: {
    id: {
      type: String,
      default: "6"
    }
  },
  data: () => ({
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
    headers: [
      {
        sortable: false,
        text: "Name",
        value: "Name"
      },
      {
        sortable: false,
        text: "Quantity",
        value: "Quantity"
      },
      {
        sortable: false,
        text: "Freight Cost",
        value: "Freight Cost"
      },
      {
        sortable: false,
        text: "Price/Unit",
        value: "Price/Unit"
      },
      {
        sortable: false,
        text: "Quantity Remaining",
        value: "Quantity Remaining"
      },
      {
        sortable: false,
        text: "Container Quantity",
        value: "Container Quantity"
      },
      {
        sortable: false,
        text: "Date",
        value: "Date"
      },
      {
        sortable: false,
        text: "Container Name",
        value: "Container Name"
      }
    ],
    konten: {
      number: "",
      shipment: "",
      date: "",
      supplierName: "",
      vessel: "",
      product: []
    },
    ancol: {
      status: "",
      message: "",
      result: []
    }
  }),
  mounted() {
    const idSI = this.$route.query.id;
    this.id = idSI;
    console.log("tes");
    Axios.get("http://localhost:8099/api/po/?id=" + this.id)
      .then(response => {
        this.konten = response.data.result;
      })
      .then(console.log(this.response))
      .catch(function error(params) {});
  },
  methods: {
    // showAlert(a) {
    //   const idSI = this.$route.query.id;
    //   this.id = idSI;
    //   //alert(this.id)
    //   const idProduct = a.id;
    //   //alert("masuk")
    //   //alert(idProduct)
    //   this.$router.push({
    //     path: "/po/detail/product",
    //     query: { id: this.id, idProd: idProduct }
    //   });
    // },
    // toEdit() {
    //   const idSI = this.$route.query.id;
    //   this.id = idSI;
    //   this.$router.push({ path: "/po/edit", query: { id: this.id } });
    // }
  }
};
</script>