<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-center wrap>
      <v-flex md12>
        <material-card
          color="green"
          title="Add Shipping Instruction"
          text="Here is a subtitle for this table"
        >
          <v-container>
            <v-form v-model="valid">
              <v-text-field label="PI No." :value="PI.number" v-model="PI.number" disabled/>
              <v-select
                label="PO No."
                :items="PO"
                item-text="number"
                item-value="id"
                v-model="konten.purchaseOrderId"
                required
              />
              <v-text-field
                label="SI No."
                :value="konten.number"
                v-model="konten.number"
                :rules="rules.required"
              />
              <v-text-field label="ETA" type="date" :value="konten.eTA" v-model="konten.eTA"/>
              <v-text-field
                label="ETD"
                type="date"
                :value="konten.etdep"
                v-model="konten.etdep"
                :rules="rules.required"
              />
              <v-text-field
                label="Port of Discharge"
                :value="konten.poD"
                v-model="konten.poD"
                :rules="rules.required"
              />
              <v-text-field
                label="Port of Loading"
                :value="konten.poL"
                v-model="konten.poL"
                :rules="rules.required"
              />
              <v-select
                label="Shipment Status"
                :items="status"
                :value="status"
                v-model="konten.status"
                :rules="rules.required"
              />
              <v-select
                label="Payment Status"
                :items="paymentStatus"
                :value="konten.paymentStatus"
                v-model="konten.paymentStatus"
                :rules="rules.required"
              />
              <v-text-field
                label="Final Destination"
                :value="konten.finalDestination"
                v-model="konten.finalDestination"
                :rules="rules.required"
              />
              <v-layout>
                <v-text-field
                  label="Open Date"
                  type="date"
                  :value="konten.openDate"
                  v-model="konten.openDate"
                />
                <v-text-field
                  label="Open Time"
                  type="time"
                  :value="konten.openTime"
                  v-model="konten.openTime"
                />
              </v-layout>
              <v-layout>
                <v-text-field
                  label="Close Date"
                  type="date"
                  :value="konten.closeDate"
                  v-model="konten.closeDate"
                />
                <v-text-field
                  label="Close Time"
                  type="time"
                  :value="konten.closeTime"
                  v-model="konten.closeTime"
                />
              </v-layout>
              <v-text-field
                label="Liner"
                :value="konten.liner"
                v-model="konten.liner"
                :rules="rules.required"
              />
              <v-select
                label="Staff in Charge"
                :items="staff"
                item-text="name"
                item-value="staffId"
                v-model="konten.staff"
                :rules="rules.required"
              />
              <v-text-field
                label="Vessel"
                :value="konten.vesselName"
                v-model="konten.vesselName"
                :rules="rules.required"
              />
              <v-text-field
                label="Connecting Vessel"
                :value="konten.connectingVessel"
                v-model="konten.connectingVessel"
              />
              <div>
                <v-toolbar flat color="white">
                  <v-toolbar-title>Shipping Product</v-toolbar-title>
                  <v-divider class="mx-2" inset vertical></v-divider>
                  <v-spacer></v-spacer>
                  <v-dialog v-model="dialog" max-width="500px">
                    <template v-slot:activator="{ on }">
                      <v-btn color="primary" dark class="mb-2" v-on="on">Add New</v-btn>
                    </template>
                    <v-card>
                      <v-card-title>
                        <span class="headline">{{ formTitle }}</span>
                      </v-card-title>

                      <v-card-text>
                        <v-container grid-list-md>
                          <v-layout wrap>
                            <v-flex xs12 sm6 md4>
                              <v-select
                                label="Produk"
                                :items="PIPODetail"
                                item-text="pipoid"
                                :value="editedItem.pipoid"
                                v-model="editedItem.pipoid"
                                :rules="rules.required"
                              />
                            </v-flex>

                            <v-flex xs12 sm6 md4>
                              <v-text-field
                                type="number"
                                v-model="editedItem.quantity"
                                label="Quantity"
                                :rules="rules.required"
                              ></v-text-field>
                            </v-flex>
                          </v-layout>
                        </v-container>
                      </v-card-text>

                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" flat @click="close">Cancel</v-btn>
                        <v-btn color="blue darken-1" flat @click="save">Save</v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-toolbar>
                <v-data-table :headers="siProduct" :items="konten.produkDetail" class="elevation-1">
                  <template v-slot:items="props">
                    <td class="text-xs-center">{{ props.item.productName }}</td>
                    <td class="text-xs-center">{{ props.item.ponum }}</td>
                    <td class="text-xs-center">{{ props.item.quantity }}</td>
                    <td class="text-xs-center">{{ props.item.pipoid }}</td>
                    <td class="justify-center layout px-0">
                      <v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
                      <v-icon small @click="deleteItem(props.item)">delete</v-icon>
                    </td>
                  </template>
                  <template v-slot:no-data>
                    <!-- <v-btn color="primary" @click="initialize">Reset</v-btn> -->
                  </template>
                </v-data-table>
              </div>

              <v-btn color="success" @click="submitSI(konten)">Submit</v-btn>
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
    konten: {
      proformaInvoiceId: "",
      number: "",
      purchaseOrderId: "",
      staff: "",
      vesselName: "",
      connectingVessel: "",
      liner: "",
      openDate: "",
      openTime: "",
      closeDate: "",
      closeTime: "",
      poL: "",
      poD: "",
      eTA: "",
      etdep: "",
      finalDestination: "",
      status: "",
      paymentStatus: "",
      produkDetail: []
    },
    PI: [],
    PO: [],
    PIPODetail: [],
    status: ["On Shipment", "Available", "Validating", "Arrived", "Completed"],
    paymentStatus: ["Paid", "Not Paid"],
    staff: [],
    editedItem: {
      pipoid: "",
      quantity: ""
    },
    dialog: false,
    editedIndex: -1,
    siProduct: [
      {
        sortable: false,
        text: "Product Name",
        value: "productName"
      },
      {
        sortable: false,
        text: "PO Number",
        value: "poNum"
      },
      {
        sortable: false,
        text: "Quantity",
        value: "quantity"
      },
      {
        sortable: false,
        text: "Final Destination",
        value: "final destination"
      }
    ],
    rules: {
      required: [v => !!v || "The input is required"]
    }
  }),
  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/");
      console.log("belum login");
    }
    //rolenya sokap?
    if (!this.$session.get("role") === "manager") {
      this.$router.push("/");
      console.log("bukan staff");
    }
  },
  mounted() {
    var idPI = this.$route.query.id;
    this.konten.proformaInvoiceId = idPI;
    // get all purchase order
    Axios.get("http://localhost:8099/api/getAllPurchaseOrder")
      .then(response => {
        this.PO = response.data.result;
      })
      .then(console.log(this.response))
      .catch(function error(params) {});

    // get PI Spesific
    Axios.get("http://localhost:8099/api/pi?id=" + idPI)
      .then(response => {
        this.PI = response.data.result;
      })
      .then(console.log(this.response))
      .catch(function error(params) {});
    //Get all Transaction
    Axios.get("http://localhost:8099/api/getAllPIPOTrans")
      .then(response => {
        this.PIPODetail = response.data.result;
      })
      .then(console.log(this.response))
      .catch(function error(params) {});
    //Get all staff
    Axios.get("http://localhost:8099/api/getAllStaff")
      .then(response => {
        this.staff = response.data.result;
      })
      .then(console.log(this.response))
      .catch(function error(params) {});
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "New Item" : "Edit Item";
    }
  },
  watch: {
    dialog(val) {
      val || this.close();
    }
  },
  methods: {
    editItem(item) {
      this.editedIndex = this.konten.produkDetail.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      const index = this.konten.produkDetail.indexOf(item);
      confirm("Are you sure you want to delete this item?") &&
        this.konten.produkDetail.splice(index, 1);
    },
    close() {
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    save() {
      if (this.editedIndex > -1) {
        Object.assign(
          this.konten.produkDetail[this.editedIndex],
          this.editedItem
        );
      } else {
        this.konten.produkDetail.push(this.editedItem);
        console.log(this.konten.produkDetail);
      }
      this.close();
    },
    addNewProduct() {
      this.konten.produkDetail.push({
        pipoid: "",
        quantity: ""
      });
    },
    cekidot() {
      alert("bambaang");
    },
    delProductForm(index) {
      this.konten.product.splice(index, 1);
    },
    submitSI(SI) {
      console.log(SI);
      Axios.post("http://localhost:8099/api/si/add", {
        proformaInvoiceId: SI.proformaInvoiceId,
        number: SI.number,
        purchaseOrderId: SI.purchaseOrderId,
        staff: SI.staff,
        vesselName: SI.vesselName,
        connectingVessel: SI.connectingVessel,
        liner: SI.liner,
        openDate: SI.openDate,
        openTime: SI.openTime,
        closeDate: SI.closeDate,
        closeTime: SI.closeTime,
        poL: SI.poL,
        poD: SI.poD,
        eTA: SI.eTA,
        etdep: SI.etdep,
        finalDestination: SI.finalDestination,
        status: SI.status,
        paymentStatus: SI.paymentStatus,
        product: SI.produkDetail,
        invoice: null
      })
        .then(response => {
          if (response.data.status == 200) {
            alert("Add  Success");
            this.$router.go(-1);
          } else {
            alert(response.data.status);
            alert(response.data.message);
            alert("error");
          }
        })
        .catch(function(error) {
          console.log(error);
          alert(error);
        });
    }
  }
};
</script>

<style scoped>
.spacing-playground .v-select .v-input__prepend-outer,
.v-text-field,
.spacing-playground .v-select .v-input__append-outer {
  margin-top: 0.55rem;
  margin-right: 0.5rem;
}
</style>