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
                    <p>Provorma Invoice No.</p>
                  </b>
                </td>
                <td>: {{ konten.piNum }}</td>
              </tr>
            </v-flex>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Shipping Instruction</p>
                  </b>
                </td>
                <td>: {{ konten.siNum }}</td>
              </tr>
            </v-flex>
          </v-layout>

          <v-layout>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Expected Time Departure</p>
                  </b>
                </td>
                <td>: {{ konten.etd }}</td>
              </tr>
            </v-flex>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Expexted Time Arrival</p>
                  </b>
                </td>
                <td>: {{ konten.eta }}</td>
              </tr>
            </v-flex>
          </v-layout>

          <v-layout>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Port of Discharge</p>
                  </b>
                </td>
                <td>: {{ konten.pod }}</td>
              </tr>
            </v-flex>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Port of Loading</p>
                  </b>
                </td>
                <td>: {{ konten.pol }}</td>
              </tr>
            </v-flex>
          </v-layout>

          <v-layout>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Open Date</p>
                  </b>
                </td>
                <td>: {{ konten.openDate }}</td>
              </tr>
            </v-flex>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Open Time</p>
                  </b>
                </td>
                <td>: {{ konten.openTime }}</td>
              </tr>
            </v-flex>
          </v-layout>

          <v-layout>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Close Date</p>
                  </b>
                </td>
                <td>: {{ konten.closeDate }}</td>
              </tr>
            </v-flex>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Close Time</p>
                  </b>
                </td>
                <td>: {{ konten.closeTime }}</td>
              </tr>
            </v-flex>
          </v-layout>

          <v-layout>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Inovice Number</p>
                  </b>
                </td>
                <td>: {{ konten.invoiceNum }}</td>
              </tr>
            </v-flex>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Liner</p>
                  </b>
                </td>
                <td>: {{ konten.liner }}</td>
              </tr>
            </v-flex>
          </v-layout>

          <v-layout>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Staff in Charge</p>
                  </b>
                </td>
                <td>: {{ konten.staff }}</td>
              </tr>
            </v-flex>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Payment Status</p>
                  </b>
                </td>
                <td>: {{ konten.paymentStatus }}</td>
              </tr>
            </v-flex>
          </v-layout>

          <v-layout>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Vessel</p>
                  </b>
                </td>
                <td>: {{ konten.vesselName }}</td>
              </tr>
            </v-flex>
            <v-flex xs12 md6>
              <tr>
                <td>
                  <b>
                    <p>Connecting Vessel</p>
                  </b>
                </td>
                <td>: {{ konten.connectingVessel }}</td>
              </tr>
            </v-flex>
          </v-layout>
          <!-- ==================================== Shipment Product ============================================ -->
          <br>
          <h3>Shipment Product</h3>
          <hr>
          <v-card-text>
            <v-data-table
              :headers="headers"
              :items="konten.produkDetail"
              :loading="true"
              class="elevation-1"
            >
              <v-progress-linear v-slot:progress color="blue" indeterminate></v-progress-linear>
              <template slot="headerCell" slot-scope="{ header }">
                <span
                  class="subheading font-weight-light text-success text--darken-3"
                  v-text="header.text"
                />
              </template>
              <template v-slot:items="props">
                <tr @click="showAlert(props.item)">
                  <td class="text-xs-right">{{ props.item.ponum }}</td>
                  <td class="text-xs-right">{{ props.item.productName }}</td>
                  <td class="text-xs-right">{{ props.item.quantity }}</td>
                  <td class="text-xs-right">{{ props.item.productUnit }}</td>
                  <td class="text-xs-right">{{ props.item.productDesc }}</td>
                  <td class="text-xs-right">{{ props.item.grossWeight }}</td>
                  <td class="text-xs-right">{{ props.item.netWeight }}</td>
                </tr>
              </template>
            </v-data-table>
          </v-card-text>

          <!-- ==================================== Shipment Document ============================================ -->

          <br>
          <v-layout>
            <h3>Shipment Document</h3>
          </v-layout>
          <hr>
          <v-card-text>
            <v-data-table
              :headers="headersDoc"
              :items="document"
              :loading="true"
              class="elevation-1"
            >
              <v-progress-linear v-slot:progress color="blue" indeterminate></v-progress-linear>
              <template slot="headerCell" slot-scope="{ header }">
                <span
                  class="subheading font-weight-light text-success text--darken-3"
                  v-text="header.text"
                />
              </template>
              <template v-slot:items="props">
                <tr @click="docDetail(props.item)">
                  <td class="text-xs-center">{{ props.item.name }}</td>
                  <td class="text-xs-center">{{ props.item.createdDate }}</td>
                  <td class="text-xs-center">{{ props.item.status }}</td>
                </tr>
              </template>
            </v-data-table>
          </v-card-text>
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
        text: "PO Number",
        value: "poNum"
      },
      {
        sortable: false,
        text: "Name",
        value: "Name"
      },
      {
        sortable: false,
        text: "Quantity",
        value: "quantity",
        align: "right"
      },
      {
        sortable: false,
        text: "Product Unit",
        value: "productUnit"
      },
      {
        sortable: false,
        text: "Product Description",
        value: "productUnit"
      },
      {
        sortable: false,
        text: "Gross Weight",
        value: "grossWeight"
      },
      {
        sortable: false,
        text: "Net Weight",
        value: "netWeight"
      }
    ],
    konten: {
      piNum: "",
      poNum: "",
      siNum: "",
      liner: "",
      eta: "",
      etd: "",
      poD: "",
      poL: "",
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
      paymentStatus: ""
    },
    status: ["On Shipment", "Available", "Validating", "Arrived"],
    document: [],
    headersDoc: [
      {
        sortable: false,
        text: "Name",
        value: "name"
      },
      {
        sortable: false,
        text: "Created Date",
        value: "createdDate"
      },
      {
        sortable: false,
        text: "Status",
        value: "status"
      }
    ]
  }),
  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/login");
      console.log("belon login");
    }
    //rolenya sokap?
    if (!this.$session.get("role") === "Manager") {
      alert(this.$session.get("role"));
      this.$router.push("/");
      console.log("bukan staff");
    }
    if (!this.$session.get("role") === "StafExport") {
      alert(this.$session.get("role"));
      this.$router.push("/");
      console.log("bukan staff");
    }
  },
  mounted() {
    var idSI = this.$route.query.id;
    Axios.get("http://mciexport.herokuapp.com/api/si/?id=" + idSI)
      .then(response => {
        this.konten = response.data.result;
      })
      .then(console.log(this.response))
      .catch(function error(params) {});
    Axios.get("http://mciexport.herokuapp.com/api/get/document?id=" + idSI)
      .then(response => {
        this.document = response.data.result;
      })
      .the(console.log(this.response))
      .catch(function error(params) {});
  },
  methods: {
    // editSI(a){
    //   var role = this.$session.get("role");
    //   var roleUrl = role.toLowerCase();
    //   this.$router.push({ path:"/"+roleUrl+"/si/edit", query: { id: a.shipmentId } });
    // },
    addDoc(a) {
      var role = this.$session.get("role");
      var roleUrl = role.toLowerCase();
      this.$router.push({
        path: "/" + roleUrl + "/document/add",
        query: { id: a.shipmentId }
      });
    },
    docDetail(a) {
      var role = this.$session.get("role");
      var roleUrl = role.toLowerCase();
      this.$router.push({
        path: "/" + roleUrl + "/document/detail",
        query: { id: a.id }
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