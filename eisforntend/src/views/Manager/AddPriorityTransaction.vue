<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout wrap>
      <!-- <v-breadcrumbs :items="items">
        <template v-slot:divider>
          <v-icon>chevron_right</v-icon>
        </template>
      </v-breadcrumbs>-->
      <v-flex>
        <material-card color="green" title="Add Priotity Shipping Instructions">
          <br>
          <p>Please select transaction(s) you want to prioritize below.</p>
          <v-divider></v-divider>
          <v-data-table
            v-model="selectedshippingInstructions"
            :headers="headers"
            :items="shippingInstructions"
            select-all
            class="elevation-1"
          >
            <template v-slot:items="props">
              <td>
                <v-checkbox v-model="props.selected" success hide-details></v-checkbox>
              </td>
              <td>{{ props.item.number }}</td>
              <td>{{ props.item.createDate }}</td>
              <td>{{ props.item.openDate }}</td>
              <td>{{ props.item.closeDate }}</td>
              <td>{{ props.item.finalDestination }}</td>
              <td>{{ props.item.shipmentStatus }}</td>
              <td>
                <v-btn
                  disable
                  right
                  depressed
                  small
                  round
                  color="green"
                  text-color="white"
                >{{ props.item.shipmentPriority }}</v-btn>
              </td>
            </template>
          </v-data-table>
          <v-layout>
            <v-spacer></v-spacer>
            <v-flex xs6 class="text-xs-right">
              <v-btn color="green" round @click="setPriorityTransaction()">Submit</v-btn>
            </v-flex>
          </v-layout>
        </material-card>
      </v-flex>
    </v-layout>
    <v-dialog v-model="successDialog" persistent width="400">
      <v-card>
        <v-card-title class="headline grey lighten-2" primary-title>Congratulations!</v-card-title>
        <v-card-text>You have successfull prioritize the transactions.</v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <!-- <v-spacer></v-spacer>
          <v-btn small color="blue darken-1" @click="successDialog = false">Add another PI</v-btn>-->
          <v-layout align-center justify-end fill-height>
            <v-btn
              color="blue darken-1"
              flat
              @click="$router.push({ path: '/manager/report/priority' })"
            >Back</v-btn>
          </v-layout>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import Vue from "vue";
import Axios from "axios";

export default {
  data: () => ({
    items: [
      {
        text: "Dashboard",
        disabled: false,
        href: "/dashboard"
      },
      {
        text: "Report",
        disabled: false,
        href: "/report"
      },
      {
        text: "Priority Transaction",
        disabled: false,
        href: "/report/priority"
      },
      {
        text: "Set Priority Transaction",
        disabled: true,
        href: "/report/priority/set"
      }
    ],
    successDialog: false,
    shippingInstructions: [],
    selectedshippingInstructions: [],
    headers: [
      {
        text: "Number",
        align: "left",
        sortable: false,
        value: "name"
      },
      { text: "Created Date", value: "CreatedDate" },
      { text: "Open Date", value: "OpenDate" },
      { text: "Close Date", value: "Close Date", sortable: false },
      { text: "Final Destination", value: "FinalDestination" },
      { text: "Shipment Status", value: "ShipmentStatus" },
      { text: "Priority", value: "priority" }
    ]
  }),

  mounted() {
    // this.$validator.localize("en", this.dictionary);
    Axios.get("http://localhost:8099/api/getAllSI")
      .then(response => {
        const result = response.data.result;
        const listNormalSI = result.filter(
          si => si["shipmentPriority"] == "Normal"
        );
        this.shippingInstructions = listNormalSI;
      })
      .then(console.log(this.response));
  },

  methods: {
    setPriorityTransaction() {
      var ids = [];
      for (var i = 0; i < this.selectedshippingInstructions.length; i++) {
        ids[i] = this.selectedshippingInstructions[i]["id"];
      }

      console.log(ids);
      Axios.post(
        "http://localhost:8099/api/si/set-priority?ids=" + ids,
        {}
      ).then(response => {
        console.log(response);
        this.successDialog = true;
      });
    }
  }
};
</script>