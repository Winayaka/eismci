<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout class="container" wrap>
      <v-flex xs12 md6>
        <material-card color="green" title="Transaction Recapitulation">
          <v-card>
            <v-img
              class="white--text"
              height="250px"
              width="250px"
              src="https://image.flaticon.com/icons/svg/1611/1611154.svg"
            >
              <template v-slot:placeholder>
                <v-layout fill-height align-center justify-center ma-0>
                  <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                </v-layout>
              </template>
            </v-img>
            <v-card-actions>
              <v-btn
                color="success"
                class="font-weight-light"
                @click="toTransactionReport()"
              >View report</v-btn>
            </v-card-actions>
          </v-card>
        </material-card>
      </v-flex>
      <v-flex xs12 md6>
        <material-card color="green" title="Priority Transaction">
          <v-card>
            <v-img
              class="white--text"
              height="250px"
              width="250px"
              src="https://image.flaticon.com/icons/svg/1584/1584892.svg"
            >
              <template v-slot:placeholder>
                <v-layout fill-height align-center justify-center ma-0>
                  <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                </v-layout>
              </template>
            </v-img>
            <v-card-actions>
              <v-btn
                color="success"
                class="font-weight-light"
                @click="toPriorityReport()"
              >View report</v-btn>
            </v-card-actions>
          </v-card>
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  props: {
    id: {
      type: String,
      default: "1"
    }
  },
  data: () => ({}),
  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/");
      console.log("belon login");
    }

    if (!this.$session.get("role") === "Manager") {
      this.$router.push("/");
      console.log("bukan manager");
    }
  },

  methods: {
    toTransactionReport() {
      this.$router.push({ path: "/manager/report/transaction" });
    },
    toPriorityReport() {
      this.$router.push({ path: "/manager/report/priority" });
    }
  }
};
</script>