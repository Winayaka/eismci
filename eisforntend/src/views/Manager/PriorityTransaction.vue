<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout wrap>
      <!-- <v-breadcrumbs :items="items">
        <template v-slot:divider>
          <v-icon>chevron_right</v-icon>
        </template>
      </v-breadcrumbs>-->
      <v-flex>
        <v-breadcrumbs :items="bredcrumbs" divider=">"></v-breadcrumbs>
        <material-card color="green" title="Transaction Priority">
          <v-tabs centered color="transparent" class="dark--text" icons-and-text grow>
            <v-tabs-slider color="green"></v-tabs-slider>

            <v-tab href="#tab-1">High Transaction</v-tab>

            <v-tab href="#tab-2">Normal Transaction</v-tab>

            <v-tab-item :value="'tab-1'">
              <v-card flat>
                <v-card-text>
                  <div id="tab-1">
                    <v-data-table :headers="headers" :items="highPrioritySI" class="elevation-1">
                      <template v-slot:items="props">
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
                            color="red"
                            text-color="white"
                          >{{ props.item.shipmentPriority }}</v-btn>
                        </td>
                      </template>
                    </v-data-table>
                  </div>
                </v-card-text>
              </v-card>
            </v-tab-item>

            <v-tab-item :value="'tab-2'">
              <v-card flat>
                <v-card-text>
                  <div id="tab-2">
                    <v-layout align-center justify-space-between fill-height>
                      <v-spacer></v-spacer>
                      <v-flex xs6 class="text-xs-right">
                        <v-btn
                          :disabled="normalPrioritySI.length == 0"
                          to="/manager/report/priority/add"
                          color="green"
                        >+Set Priority Transaction</v-btn>
                      </v-flex>
                    </v-layout>
                    <v-data-table :headers="headers" :items="normalPrioritySI" class="elevation-1">
                      <template v-slot:items="props">
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
                  </div>
                </v-card-text>
              </v-card>
            </v-tab-item>
          </v-tabs>
        </material-card>
      </v-flex>

      <!-- <v-flex>
        <material-card color="green" title="Priotity Shipping Instructions">
          <br>
          <v-layout>
            <v-flex xs6 class="text-xs-left">
              <v-toolbar-title>High Priority Shipping Instructions</v-toolbar-title>
            </v-flex>
          </v-layout>
          <v-data-table :headers="headers" :items="highPrioritySI" class="elevation-1">
            <template v-slot:items="props">
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
                  color="red"
                  text-color="white"
                >{{ props.item.shipmentPriority }}</v-btn>
              </td>
            </template>
          </v-data-table>
          <br>
          <v-layout align-center justify-space-between fill-height>
            <v-flex xs6 class="text-xs-left">
              <v-toolbar-title>Normal Priority Shipping Instructions</v-toolbar-title>
            </v-flex>
            <v-flex xs6 class="text-xs-right">
              <v-btn
                :disabled="normalPrioritySI.length == 0"
                to="/manager/report/priority/add"
                color="green"
              >+Set Priority Transaction</v-btn>
            </v-flex>
          </v-layout>
          <v-data-table :headers="headers" :items="normalPrioritySI" class="elevation-1">
            <template v-slot:items="props">
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
        </material-card>
      </v-flex>-->
    </v-layout>
  </v-container>
</template>

<script>
import Vue from "vue";
import Axios from "axios";

export default {
  data: () => ({
    active: null,
    text:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
    bredcrumbs: [
      {
        text: "Home",
        disabled: false,
        href: "/Manager"
      },
      {
        text: "Report",
        disabled: false,
        href: "/manager/report"
      },
      {
        text: "Priority Transaction",
        disabled: true
      }
    ],
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
        disabled: true,
        href: "/report/priority"
      }
    ],
    normalPrioritySI: [],
    highPrioritySI: [],
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
        const listHighSI = result.filter(
          si => si["shipmentPriority"] == "High"
        );

        this.normalPrioritySI = listNormalSI;
        this.highPrioritySI = listHighSI;
      })
      .then(console.log(this.response));
  }
};
</script>
