<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-center wrap>
      <v-flex md12>
        <div>
          <v-breadcrumbs :items="bredcrumbs" divider=">"></v-breadcrumbs>
        </div>
        <material-card
          color="green"
          title="Edit Shipping Instruction"
          text="Here is a subtitle for this table"
        >
          <!--  
        ========================================================
        Form Shipping Instruction
        ========================================================
          -->
          <v-container>
            <v-form v-model="valid">
              <v-text-field label="PI No." :value="konten.piNum" v-model="konten.piNum" disabled/>
              <!-- <v-select  
                label="PO No."
                :items="PO"
                item-text="number"
                item-value="id"
                v-model="konten.purchaseOrderId"
                disabled
              />-->
              <v-text-field label="SI No." :value="konten.siNum" v-model="konten.siNum" disabled/>
              <v-text-field
                label="Expected Time Arrival"
                type="date"
                :value="konten.eta"
                v-model="konten.eta"
              />
              <v-text-field
                label="Expected Time Departure*"
                type="date"
                :value="konten.etd"
                v-model="konten.etd"
                :rules="rules.required"
              />
              <v-text-field
                label="Port of Discharge"
                :value="konten.pod"
                v-model="konten.pod"
                :rules="[rules.required, rules.max50]"
                counter
                maxlength="50"
              />
              <v-text-field
                label="Port of Loading"
                :value="konten.pol"
                v-model="konten.pol"
                :rules="[rules.required, rules.max50]"
                counter
                maxlength="50"
              />
              <v-select
                label="Shipment Status"
                :items="status"
                :value="status"
                v-model="konten.shipmentStatus"
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
                :rules="[rules.required, rules.max50]"
                counter
                maxlength="50"
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
                :rules="[rules.required, rules.max50]"
                counter
                maxlength="50"
              />
              <v-select
                label="Staff in Charge"
                :items="staff"
                item-text="name"
                item-value="staffId"
                v-model="konten.staff"
                :rules="rules.required"
              />
              <v-text-field label="Invoice" :value="konten.invoiceNum" v-model="konten.invoiceNum"/>
              <v-text-field
                label="Vessel"
                :value="konten.vesselName"
                v-model="konten.vesselName"
                :rules="[rules.required, rules.max50]"
                counter
                maxlength="50"
              />
              <v-text-field
                label="Connecting Vessel"
                :value="konten.connectingVessel"
                counter
                maxlength="50"
              />
              <!--  
================================================================================================
Form Shipment Product
================================================================================================
              -->
              <div>
                1
                <v-toolbar flat color="white" align-left justify-space-between row fill-height>
                  <v-toolbar-title>Shipping Product</v-toolbar-title>
                  <v-divider class="mx-2" inset vertical></v-divider>
                  <v-spacer></v-spacer>
                  <v-dialog v-model="dialog" max-width="500px">
                    <template v-slot:activator="{ on }">
                      <v-btn color="primary" dark class="mb-2" v-on="on" @click="f">Add New</v-btn>
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
                                :items="pipotrans"
                                item-text="poNum"
                                item-value="pipoid"
                                :value="editedItem.pipoid"
                                v-model="editedItem.pipoid"
                                :rules="rules.required"
                                @change="assign"
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
                    <td class="text-xs-left">{{ props.item.productName }}</td>
                    <td class="text-xs-left">{{ props.item.ponum }}</td>
                    <td class="text-xs-left">{{ props.item.quantity }}</td>
                    <td class="justify-center layout px-0">
                      <v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
                      <v-icon small @click="deleteItem(props.item)">delete</v-icon>
                    </td>
                  </template>
                  <template v-slot:no-data>
                    <v-btn color="primary" @click="initialize">Reset</v-btn>
                  </template>
                </v-data-table>
              </div>
            </v-form>
          </v-container>
          <v-layout>
            <v-flex xs6 class="text-xs-left">
              <v-btn color="warning" round @click="cancel()">Cancel</v-btn>
            </v-flex>
            <v-spacer></v-spacer>
            <v-flex xs6 class="text-xs-right">
              <v-btn color="green" round @click="submitSI(konten)">save</v-btn>
            </v-flex>
          </v-layout>>
        </material-card>
      </v-flex>
      <v-dialog v-model="successDialog" persistent width="400">
        <v-card>
          <v-card-title class="headline grey lighten-2" primary-title>Congratulations!</v-card-title>
          <v-card-text>You have successfull Edit Shipping Instruction.</v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-layout align-center justify-end fill-height>
              <v-btn color="blue darken-1" flat @click="$router.go(-1)">Back</v-btn>
            </v-layout>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout>
  </v-container>
</template>

<script>
import Axios from "axios";
export default {
  data: () => ({
    bredcrumbs: [
      {
        text: "Home",
        disabled: false,
        href: "/Manager"
      },
      {
        text: "Proforma Invoice",
        disabled: false,
        href: "/manager/pi"
      },
      {
        text: "Proforma Invoice Detail",
        disabled: false,
        href: ""
      },
      {
        text: "Shipping Instruction Detail",
        disabled: false,
        href: ""
      },
      {
        text: "Edit Shipping Instruction",
        disabled: true
      }
    ],
    successDialog: false,
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
      }
    ],
    konten: {
      piNum: "",
      poNum: "",
      siNum: "",
      liner: "",
      eta: "",
      etd: "",
      pod: "",
      pol: "",
      shipmentStatus: "",
      finalDestination: "",
      openTime: "",
      openDate: "",
      closeTime: "",
      closeDate: "",
      invoiceNum: "",
      vesselName: "",
      connectingVessel: "",
      produkDetail: [],
      shipmentId: "",
      staff: "",
      paymentStatus: "",
      piid: "",
      poid: ""
    },
    oldSI: {
      piNum: "",
      poNum: "",
      siNum: "",
      liner: "",
      eta: "",
      etd: "",
      pod: "",
      pol: "",
      shipmentStatus: "",
      finalDestination: "",
      openTime: "",
      openDate: "",
      closeTime: "",
      closeDate: "",
      invoiceNum: "",
      vesselName: "",
      connectingVessel: "",
      produkDetail: [],
      shipmentId: "",
      staff: "",
      paymentStatus: "",
      piid: "",
      poid: ""
    },
    status: ["On Shipment", "Available", "Validating", "Arrived", "Completed"],
    paymentStatus: ["Paid", "Not Paid"],
    staff: [],
    editedItem: {
      pipoid: "",
      productName: "",
      quantity: "",
      ponum: ""
    },
    pipotrans: [],
    rules: {
      required: [v => !!v || "The input is required"],
      max50: [v => v.length <= 50 || "Input must be less than 50 characters"],
      max255: [v => v.length <= 50 || "Input must be less than 255 characters"]
    }
  }),

  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/");
      console.log("belon login");
    }
    //rolenya sokap?
    if (!this.$session.get("role") === "Manager") {
      this.$router.push("/");
      console.log("bukan staff");
    }
  },

  mounted() {
    var idSi = this.$route.query.id;
    this.bredcrumbs[this.bredcrumbs.length - 2].href =
      " /manager/si/detail?id=" + idSi;

    Axios.get("http://localhost:8099/api/si?id=" + idSi)
      .then(response => {
        console.log(response.data.result);
        this.konten = response.data.result;
        this.oldSI = response.data.result;
        this.bredcrumbs[this.bredcrumbs.length - 3].href =
          "/manager/pi/detail?id=" + response.data.result.piid;
      })
      .catch(function error(params) {});

    Axios.get("http://localhost:8099/api/getAllStaff")
      .then(response => {
        this.staff = response.data.result;
      })
      .catch(function error(params) {});

    Axios.get("http://localhost:8099/api/getAllPIPOTrans")
      .then(response => {
        this.pipotrans = response.data.result;
      })
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
    cancel() {
      var r = confirm("are you sure want to cancel?");
      if (r) {
        this.$router.go(-1);
      }
    },
    initialize() {
      this.konten.produkDetail = this.oldSI.produkDetail;
    },
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

    //id=array pipotrans
    addId(id) {
      alert(id[0]);
      return this.pipotrans.filter(e => {
        return;
        e.pipoid.indexOf(id.pipoid) > -1;
      });
      alert(this.pipotrans.pipoid);
      console.log(this.pipotrans);
      //this.editedItem.pipoid = temp.pipoid
    },

    f() {
      var i;
      console.log(this.pipotrans);
      for (i = 0; i < this.pipotrans.length; i++) {
        //alert("a");
        var a = this.pipotrans[i].ponum + " - " + this.pipotrans[i].productName;
        this.pipotrans[i].poNum = a;
        //alert(a)
      }
    },

    assign(item) {
      var temp;
      var i;
      console.log(item);

      for (i = 0; i < this.pipotrans.length; i++) {
        if (this.pipotrans[i].pipoid == item) temp = this.pipotrans[i];
      }

      this.editedItem.ponum = temp.poNum.split(" - ")[0];
      this.editedItem.productName = temp.poNum.split(" - ")[1];
      console.log(this.editedItem);
    },

    reInitialize() {
      alert("reInitialize");
      Axios.get("http://localhost:8099/api/getAllPIPOTrans")
        .then(response => {
          this.pipotrans = response.data.result;
        })
        .catch(function error(params) {});
    },
    submitSI(SI) {
      var idSi = this.$route.query.id;
      console.log(SI.invoiceNum);
      var url = "http://localhost:8099/api/si/update?id=" + SI.shipmentId;
      console.log(url);
      var ok = true;
      if (SI.number == "") {
        ok = false;
      }
      if (SI.staff == "" || !/^[0-9]\d*$/.test(SI.staff)) {
        ok = false;
      }
      if (SI.vesselName == "") {
        ok = false;
      }
      if (SI.liner == "") {
        console.log("liner kosong");
        ok = false;
      }
      if (SI.poL == "") {
        ok = false;
      }
      if (SI.poD == "") {
        ok = false;
      }
      if (SI.finalDestination == "") {
        ok = false;
      }
      var i;
      for (i = 0; i < SI.produkDetail.length; i++) {
        if (SI.produkDetail[i].quantity == "") {
          ok = false;
        }
      }
      if (ok) {
        console.log(SI);
        Axios.post(url, {
          proformaInvoiceId: SI.piid,
          number: SI.siNum,
          purchaseOrderId: SI.poid,
          staff: SI.staff,
          vesselName: SI.vesselName,
          connectingVessel: SI.connectingVessel,
          liner: SI.liner,
          openDate: SI.openDate,
          openTime: SI.openTime,
          closeDate: SI.closeDate,
          closeTime: SI.closeTime,
          poL: SI.pol,
          poD: SI.pod,
          eTA: SI.eta,
          etdep: SI.etd,
          finalDestination: SI.finalDestination,
          status: SI.shipmentStatus,
          paymentStatus: SI.paymentStatus,
          product: SI.produkDetail,
          invoice: SI.invoiceNum
        })
          .then(console.log(this.product))
          .then(response => {
            if (response.data.status == 200) {
              this.successDialog = true;
              console.log(response.data);
              // this.$router.go(-1);
            } else {
              alert(response.data.status);
              console.log(response.data);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      } else {
        alert("Input data is wrong");
      }
    }
  }
};
</script>
