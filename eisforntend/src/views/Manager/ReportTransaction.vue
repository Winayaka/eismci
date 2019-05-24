<template>
  <v-container grid-list-md text-xs-center>
    <v-breadcrumbs :items="bredcrumbs" divider=">"></v-breadcrumbs>
    <v-layout row wrap>
      <v-flex xs12 md12>
        <span class="display-3">Report - Transaction Recapitulation - {{displayPeriod}}</span>
      </v-flex>
    </v-layout>

    <v-layout row wrap>
      <v-flex xs12 md6>
        <v-flex>
          <v-card color="green darken-2" class="white--text">
            <v-card-title>
              <span class="headline">Total of Succceeded Transaction</span>
            </v-card-title>
            <v-card-text>
              <span class="display-1">{{report.numberOfTransaction}}</span>
            </v-card-text>
          </v-card>
        </v-flex>
        <v-flex>
          <v-card color="green darken-2" class="white--text">
            <v-card-title>
              <span class="headline">Total Revenue</span>
            </v-card-title>
            <v-card-text>
              <span class="display-1">$ {{report.revenue}}</span>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-flex>
      <v-flex xs12 md6>
        <v-flex>
          <v-card>
            <v-card-title>
              <span class="headline">Change Period</span>
            </v-card-title>
            <v-card-text>
              <v-text-field label="Date" type="date" v-model="input.date"/>
              <v-select v-model="input.period" :items="periodItem" label="Select date" single-line></v-select>
            </v-card-text>

            <v-card-action>
              <v-btn
                color="success"
                round
                align="right"
                class="font-weight-light"
                @click="changePeriod(input)"
              >Change Period</v-btn>
            </v-card-action>
          </v-card>
        </v-flex>
        <v-flex>
          <v-card color="green darken-2" class="white--text">
            <v-card-title>
              <span class="headline">Most Sold Product</span>
            </v-card-title>
            <v-card-text>
              <span class="display-1">{{report.mostProduct}}</span>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-flex>
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
        text: "Report",
        disabled: false,
        href: "/manager/report"
      },
      {
        text: "Transaction Recapitulation",
        disabled: true
      }
    ],
    input: {
      period: "weekly",
      date: ""
    },
    periodItem: ["weekly", "monthly", "yearly"],
    displayPeriod: "weekly",

    report: {
      numberOfTransaction: "",
      revenue: "",
      mostProduct: ""
    }
  }),
  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/");
      console.log("belon login");
    }

    if (!this.$session.get("role") === "Manager") {
      this.$router.push("/");
      console.log("bukan staff");
    }
  },
  mounted() {
    if (this.$route.query.period === undefined) {
      //baru masuk, artinya default
      var today = new Date();
      var dd = today.getDate();
      var mm = today.getMonth() + 1; //January is 0!
      var yyyy = today.getFullYear();

      if (dd < 10) {
        dd = "0" + dd;
      }

      if (mm < 10) {
        mm = "0" + mm;
      }

      today = yyyy + "-" + mm + "-" + dd;

      var url =
        "http://localhost:8099/api/getReport?date=" + today + "&period=weekly";
      console.log(url);
      Axios.get(url)
        .then(response => {
          console.log(response.data.result);
          this.report = response.data.result;
        })
        .then(console.log(this.report))
        .catch(function(error) {
          console.log(error);
        });
    } else {
      //ada parameter
      var period = this.$route.query.period;
      var date = this.$route.query.date;
      this.displayPeriod = period;
      this.input.date = date;
      var url =
        "http://localhost:8099/api/getReport?period=" +
        period +
        "&date=" +
        date;
      console.log(url);
      Axios.get(url)
        .then(response => {
          console.log(response.data.result);
          this.report = response.data.result;
        })
        .then(console.log(this.report))
        .catch(function(error) {
          console.log(error);
        });
    }
  },
  methods: {
    getToday() {
      var today = new Date();
      var dd = today.getDate();
      var mm = today.getMonth() + 1; //January is 0!
      var yyyy = today.getFullYear();

      if (dd < 10) {
        dd = "0" + dd;
      }

      if (mm < 10) {
        mm = "0" + mm;
      }

      today = yyyy + "-" + mm + "-" + dd;
      return today;
    },
    changePeriod(input) {
      console.log("masuk");
      this.$router.push({
        path: "/manager/report/transaction",
        query: { date: input.date, period: input.period }
      });
      location.reload();
    }
  }
};
</script>
