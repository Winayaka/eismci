<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout wrap>
      <v-breadcrumbs :items="items">
        <template v-slot:divider>
          <v-icon>chevron_right</v-icon>
        </template>
      </v-breadcrumbs>
      <v-flex md12>
        <material-card color="green" title="Proforma Invoice Detail">
          <v-card-text class="text-xs-left">
            <v-layout>
              <v-flex xs12 md6>
                <tr>
                  <td>
                    <b>
                      <p>Number</p>
                    </b>
                  </td>
                  <td>: {{ selectedPI.number }}</td>
                </tr>
              </v-flex>
              <v-flex xs12 md6>
                <tr>
                  <td>
                    <b>
                      <p>Delivery Term</p>
                    </b>
                  </td>
                  <td>: {{ selectedPI.deliveryTerm | formatDate }}</td>
                </tr>
              </v-flex>
            </v-layout>

            <v-layout>
              <v-flex xs12 md6>
                <tr>
                  <td>
                    <b>
                      <p>Date</p>
                    </b>
                  </td>
                  <td>: {{ selectedPI.date | formatDate }}</td>
                </tr>
              </v-flex>
              <v-flex xs12 md6>
                <tr>
                  <td>
                    <b>
                      <p>Term and Condition</p>
                    </b>
                  </td>
                  <td>: {{ selectedPI.termAndCondition }}</td>
                </tr>
              </v-flex>
            </v-layout>

            <v-layout>
              <v-flex xs12 md6>
                <tr>
                  <td>
                    <b>
                      <p>Country of Origin</p>
                    </b>
                  </td>
                  <td>: {{ selectedPI.countryOfOrigin }}</td>
                </tr>
              </v-flex>
              <v-flex xs12 md6>
                <tr>
                  <td>
                    <b>
                      <p>Final Destination</p>
                    </b>
                  </td>
                  <td>: {{ selectedPI.finalDestination }}</td>
                </tr>
              </v-flex>
            </v-layout>

            <v-layout>
              <v-flex xs12 md6>
                <tr>
                  <td>
                    <b>
                      <p>Port of Loading</p>
                    </b>
                  </td>
                  <td>: {{ selectedPI.portOfLoading }}</td>
                </tr>
              </v-flex>
              <v-flex xs12 md6>
                <tr>
                  <td>
                    <b>
                      <p>ETA</p>
                    </b>
                  </td>
                  <td>: {{ selectedPI.etaDate | formatDate }}</td>
                </tr>
              </v-flex>
            </v-layout>

            <v-layout>
              <v-flex xs12 md6>
                <tr>
                  <td>
                    <b>
                      <p>Port of Destination</p>
                    </b>
                  </td>
                  <td>: {{ selectedPI.portOfDestination }}</td>
                </tr>
              </v-flex>
              <v-flex xs12 md6>
                <tr>
                  <td>
                    <b>
                      <p>ETD</p>
                    </b>
                  </td>
                  <td>: {{ selectedPI.etdDate | formatDate }}</td>
                </tr>
              </v-flex>
            </v-layout>

            <h3>Proforma Invoice Product</h3>
            <v-card-text>
              <v-data-table :headers="headers" :items="selectedPI.detailProduct" hide-actions>
                <template slot="headerCell" slot-scope="{ header }">
                  <span
                    class="subheading font-weight-light text-success text--darken-3"
                    v-text="header.text"
                  />
                </template>
                <template v-slot:items="props">
                  <tr>
                    <td>{{ props.index+1 }}</td>
                    <td>{{ props.item.product.name }}</td>
                    <td>{{ props.item.quantity }}</td>
                    <td>{{ props.item.freightCost }}</td>
                    <td>{{ props.item.pricePerUnit }}</td>
                    <td>{{ props.item.quantitySent }}</td>
                    <td>{{ props.item.containerQuantity }}</td>
                    <td>{{ props.item.container.name }}</td>
                  </tr>
                </template>
              </v-data-table>
            </v-card-text>
          </v-card-text>
          <v-btn @click="editPI()" color="green">Edit</v-btn>
        </material-card>

        <material-card
          color="green"
          title="List Used Purchase Order"
          text="Here is a subtitle for this table"
        >
          <v-btn to="/si/add" @click="addSI(konten.id)" color="green">Add SI</v-btn>
          <v-card-text>
            <v-data-table :headers="siHeaders" :items="SI" hide-actions>
              <template slot="headerCell" slot-scope="{ header }">
                <span
                  class="subheading font-weight-light text-success text--darken-3"
                  v-text="header.text"
                />
              </template>
              <template v-slot:items="props">
                <tr @click="SIDetail(props.item)">
                  <td>{{ props.item.number }}</td>
                  <td>{{ props.item.liner }}</td>
                  <td>{{ props.item.finalDestination }}</td>
                  <td>{{ props.item.shipmentStatus }}</td>
                </tr>
              </template>
            </v-data-table>
          </v-card-text>
        </material-card>

        <material-card
          color="green"
          title="List Shipping Instruction"
          text="Here is a subtitle for this table"
        >
          <v-btn to="/si/add" @click="addSI()" color="green">Add SI</v-btn>
          <v-card-text>
            <v-data-table :headers="siHeaders" :items="SI" hide-actions>
              <template slot="headerCell" slot-scope="{ header }">
                <span
                  class="subheading font-weight-light text-success text--darken-3"
                  v-text="header.text"
                />
              </template>
              <template v-slot:items="props">
                <tr @click="SIDetail(props.item)">
                  <td>{{ props.item.number }}</td>
                  <td>{{ props.item.liner }}</td>
                  <td>{{ props.item.finalDestination }}</td>
                  <td>{{ props.item.shipmentStatus }}</td>
                </tr>
              </template>
            </v-data-table>
          </v-card-text>
        </material-card>

        <material-card color="green" title="Staff in Charge">
          <v-card-text>
            <td>{{ selectedPI.userId }}</td>
            <td>{{ selectedPI.id }}</td>
            <td>{{ selectedPI.title }}</td>
            <td>{{ selectedPI.body }}</td>
          </v-card-text>
        </material-card>
        {{SI}}
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import Axios from "axios";
import moment from "moment";
export default {
  props: {
    id: {
      type: String,
      default: "1"
    }
  },

  data: () => ({
    items: [
      {
        text: "Dashboard",
        disabled: false,
        href: "/dashboard"
      },
      {
        text: "Proforma Invoice",
        disabled: false,
        href: "/pi"
      },
      {
        text: "Proforma Invoice Detail",
        disabled: true,
        href: "/pi/detail"
      }
    ],
    selectedPI: [],
    selectedSI: [],
    SI: [],
    headers: [
      {
        text: "No",
        value: "no",
        align: "left",
        sortable: false
      },
      {
        text: "Name",
        value: "name",
        sortable: true
      },
      { text: "Quantity", value: "quantity" },
      { text: "Freight Cost", value: "freightcost" },
      { text: "Price/Unit", value: "pricePerUnit" },
      { text: "Quantity Sent", value: "quantitySent" },
      { text: "Container Quantity", value: "containerQuantity" },
      { text: "Container", value: "container" }
    ],
    siHeaders: [
      {
        sortable: false,
        text: "Number",
        value: "number"
      },
      {
        sortable: false,
        text: "Liner",
        value: "liner"
      },
      {
        sortable: false,
        text: "Vessel",
        value: "vessel.name"
      },
      {
        sortable: false,
        text: "Final Destination",
        value: "finalDestination"
      }
    ]
  }),
  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/");
      console.log("belon login");
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

    if (!this.$session.get("role") === "StafExport") {
      alert(this.$session.get("role"));
      this.$router.push("/");
      console.log("bukan staff");
    }
  },
  mounted() {
    var idPI = this.$route.query.id;
    Axios.get("http://localhost:8099/api/pi?id=" + idPI)
      .then(response => {
        this.selectedPI = response.data.result;
      })
      .then(console.log(this.url));

    Axios.get("http://localhost:8099/api/get/si/?PIId=" + idPI)
      .then(response => {
        this.SI = response.data.result;
      })
      .then(console.log(this.response.message))
      .catch(function error(params) {});
  },
  methods: {
    editPI() {
      var idPI = this.selectedPI.id;
      this.$router.push({
        path: "/StaffAdministrative/pi/edit",
        query: { id: idPI }
      });
    },
    SIDetail(SI) {
      var role = this.$session.get("role");
      var roleUrl = role.toLowerCase();
      this.$router.push({
        path: "/" + roleUrl + "/si/detail/",
        query: { id: SI.id }
      });
    },
    addSI() {
      var idPI = this.$route.query.id;
      this.$router.push({ path: "/manager/si/add", query: { id: idPI } });
    }
  }
};
</script>