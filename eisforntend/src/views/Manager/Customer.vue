<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-center wrap>
      <v-flex md12>
        <material-card color="green" title="Customer List" text="Click row to see details.">
          <v-data-table align="left" :headers="headers" :items="konten">
            <template slot="headerCell" slot-scope="{ header }">
              <span
                class="subheading font-weight-light text-success text--darken-3"
                v-text="header.text"
              />
            </template>
            <template v-slot:items="props">
              <tr @click="showAlert(props.item)">
                <td>{{ props.item.custName }}</td>
                <td>{{ props.item.email }}</td>
                <td>{{ props.item.phoneNumber }}</td>
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
  data: () => ({
    headers: [
      {
        text: "Name",
        value: "custName"
      },
      {
        text: "Email",
        value: "email"
      },
      {
        text: "Phone Number",
        value: "phone number"
      }
    ],
    konten: []
  }),
  mounted() {
    Axios.get("http://mciexport.herokuapp.com/api/getAllCustomer")
      .then(response => {
        this.konten = response.data.result;
      })
      .then(console.log(this.response));
  },
  methods: {
    showAlert(a) {
      // alert(a.custName)
      this.$router.push({
        path: "/manager/customer/detail",
        query: { custName: a.custName }
      });
    }
    // addCustomer() {
    //   this.$router.push({ path: "/customer/add" });
    // }
  }
};
</script>