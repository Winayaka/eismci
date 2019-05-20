<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout wrap>
      <v-breadcrumbs :items="items">
        <template v-slot:divider>
          <v-icon>chevron_right</v-icon>
        </template>
      </v-breadcrumbs>
      <v-flex>
        <material-card
          color="green"
          title="Add Proforma Invoice"
          text="Insert required information"
        >
          <v-form class="container" ref="form" v-model="valid" lazy-validation>
            <v-container>
              <v-layout wrap align-center justify-center>
                <v-flex xs12 md10>
                  <v-text-field
                    v-model="PIForm.number"
                    :value="PIForm.number"
                    :rules="[rules.required, rules.max50]"
                    counter
                    maxlength="50"
                    type="text"
                    label="PI Number"
                    :error-messages="errorMessage"
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    v-model="PIForm.date"
                    :value="PIForm.date"
                    :rules="[rules.required]"
                    type="date"
                    label="PI Date"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-select
                    v-model="PIForm.customerID"
                    :value="PIForm.customerID"
                    :rules="[rules.required]"
                    :items="customers"
                    item-text="custName"
                    item-value="id"
                    label="Customer"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    v-model="PIForm.countryOfOrigin"
                    :value="PIForm.countryOfOrigin"
                    :rules="[rules.required, rules.max50]"
                    counter
                    maxlength="50"
                    type="text"
                    label="Country of Origin"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    v-model="PIForm.portOfLoading"
                    :value="PIForm.portOfLoading"
                    :rules="[rules.required, rules.max50]"
                    counter
                    maxlength="50"
                    type="text"
                    label="Port of Loading"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    v-model="PIForm.portOfDestination"
                    :value="PIForm.portOfDestination"
                    :rules="[rules.required, rules.max50]"
                    counter
                    maxlength="50"
                    type="text"
                    label="Port of Destination"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    v-model="PIForm.finalDestination"
                    :value="PIForm.finalDestination"
                    :rules="[rules.required, rules.max50]"
                    counter
                    maxlength="50"
                    type="text"
                    label="Final Destination"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    v-model="PIForm.deliveryTerm"
                    :value="PIForm.deliveryTerm"
                    :rules="[rules.required]"
                    type="date"
                    label="Delivery Term"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    v-model="PIForm.paymentTerm"
                    :value="PIForm.paymentTerm"
                    :rules="[rules.required, rules.max50]"
                    counter
                    maxlength="50"
                    type="text"
                    label="Payment Term"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    v-model="PIForm.termAndCondition"
                    :value="PIForm.termAndCondition"
                    :rules="[rules.max255]"
                    counter
                    maxlength="255"
                    type="text"
                    label="Term and Condition"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    v-model="PIForm.etaDate"
                    :value="PIForm.etaDate"
                    :rules="[rules.required]"
                    type="date"
                    label="ETA Date"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    v-model="PIForm.etdDate"
                    :value="PIForm.etdDate"
                    :rules="[rules.required]"
                    type="date"
                    label="ETD Date"
                    required
                  />
                </v-flex>
              </v-layout>
            </v-container>
            <template>
              <div>
                <v-toolbar flat color="white">
                  <v-toolbar-title>Add Product(s)</v-toolbar-title>
                  <v-spacer></v-spacer>
                  <v-dialog v-model="dialog" max-width="500px">
                    <template v-slot:activator="{ on }">
                      <v-btn color="blue" dark class="mb-2" v-on="on">+Add Product</v-btn>
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
                                v-model="editedItem.product.name"
                                :items="products"
                                item-text="name"
                                item-value="name"
                                label="Select Product"
                                required
                              />
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field v-model="editedItem.quantity" label="Quantity"></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field v-model="editedItem.freightCost" label="Freight cost"></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field v-model="editedItem.pricePerUnit" label="Price/Unit"></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field v-model="editedItem.quantitySent" label="Quantity sent"></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field
                                v-model="editedItem.containerQuantity"
                                label="Container quantity"
                              ></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-select
                                v-model="editedItem.container.name"
                                :items="container"
                                item-text="name"
                                item-value="name"
                                label="Container"
                                required
                              />
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
                <v-data-table :headers="headers" :items="productDetailList" class="elevation-1">
                  <template v-slot:items="props">
                    <td>{{ props.item.product.name }}</td>
                    <td>{{ props.item.quantity }}</td>
                    <td>{{ props.item.freightCost }}</td>
                    <td>{{ props.item.pricePerUnit }}</td>
                    <td>{{ props.item.quantitySent }}</td>
                    <td>{{ props.item.containerQuantity }}</td>
                    <td>{{ props.item.container.name }}</td>
                    <td class="justify-center layout px-0">
                      <v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
                      <v-icon small @click="deleteItem(props.item)">delete</v-icon>
                    </td>
                  </template>
                </v-data-table>
              </div>
            </template>
            <v-spacer></v-spacer>
            <v-layout>
              <v-flex xs6 class="text-xs-left">
                <v-btn color="warning" round @click="cancel()">Cancel</v-btn>
              </v-flex>
              <v-spacer></v-spacer>
              <v-flex xs6 class="text-xs-right">
                <v-btn :disabled="!valid" color="green" round @click="editPI()">Submit</v-btn>
              </v-flex>
            </v-layout>
            <v-flex v-if="!valid" xs12 class="text-xs-left" color="danger">
              <h5 style="color:red">Please fill the form correctly to submit.</h5>
            </v-flex>
          </v-form>
        </material-card>
      </v-flex>
    </v-layout>

    <v-dialog v-model="successDialog" persistent width="500">
      <v-card>
        <v-card-title class="headline grey lighten-2" primary-title>Congratulations!</v-card-title>
        <v-card-text>You have successfull update Proforma Invoice.</v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" flat @click="$router.go(-1)">Back</v-btn>
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
        text: "Proforma Invoice",
        disabled: false,
        href: "/pi"
      },
      {
        text: "Edit Proforma Invoice",
        disabled: true,
        href: "/pi/edit"
      }
    ],
    valid: true,
    rules: {
      required: value => !!value || "The input is required.",
      max50: v => v.length <= 50 || "Input must be less than 50 characters",
      max255: v => v.length <= 50 || "Input must be less than 255 characters"
    },
    errorMessage: "",
    PIForm: [],
    productDetailList: [],
    editedIndex: -1,
    editedItem: {
      id: "0",
      product: {
        name: ""
      },
      quantity: "20",
      freightCost: "2000",
      pricePerUnit: "3000",
      quantitySent: "0",
      containerQuantity: "20",
      container: {
        name: ""
      }
    },
    defaultItem: {
      id: "0",
      product: {
        name: ""
      },
      quantity: "20",
      freightCost: "2000",
      pricePerUnit: "3000",
      quantitySent: "0",
      containerQuantity: "20",
      container: {
        name: ""
      }
    },
    numberExisted: "",
    customers: [],
    container: [],
    products: [],

    dialog: false,
    successDialog: false,
    headers: [
      {
        text: "Name",
        align: "left",
        sortable: false,
        value: "name"
      },
      { text: "Quantity", value: "quantity" },
      { text: "Freight Cost", value: "freightcost" },
      { text: "Price/Unit", value: "pricePerUnit", sortable: false },
      { text: "Quantity Sent", value: "quantitySent" },
      { text: "Container Quantity", value: "containerQuantity" },
      { text: "Container", value: "container" },
      { text: "Actions", value: "actions", sortable: false }
    ]
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Add Product" : "Edit Product";
    }
  },

  watch: {
    dialog(val) {
      val || this.close();
    }
  },

  mounted() {
    // this.$validator.localize("en", this.dictionary);
    var id = this.$route.query.id;
    console.log(id);
    Axios.get("http://mciexport.herokuapp.com/api/pi?id=" + id).then(response => {
      this.PIForm = response.data.result;
      this.productDetailList = response.data.result.detailProduct;
      console.log(this.PIForm);
    });

    Axios.get("http://mciexport.herokuapp.com/api/getAllCustomer").then(response => {
      this.customers = response.data.result;
    });

    Axios.get("http://mciexport.herokuapp.com/api/getAllContainer").then(response => {
      this.container = response.data.result;
    });

    Axios.get("http://mciexport.herokuapp.com/api/getAllProduct").then(response => {
      this.products = response.data.result;
    });
  },

  methods: {
    editPI() {
      var id = this.$route.query.id;
      var PIForm = this.PIForm;
      var productDetailList = this.productDetailList;
      //var pi = this.PIForm;
      // this.$validator.validateAll();
      console.log("PI ID: " + id);
      console.log(PIForm);
      console.log(productDetailList);
      Axios.post("http://mciexport.herokuapp.com/api/pi/update?id=" + id, {
        number: PIForm.number,
        date: PIForm.date,
        customerID: PIForm.customerID,

        countryOfOrigin: PIForm.countryOfOrigin,
        portOfLoading: PIForm.portOfLoading,
        portOfDestination: PIForm.portOfDestination,
        finalDestination: PIForm.finalDestination,

        deliveryTerm: PIForm.deliveryTerm,
        termAndCondition: PIForm.termAndCondition,
        paymentTerm: PIForm.paymentTerm,

        etaDate: PIForm.etaDate,
        etdDate: PIForm.etdDate,
        productDetailList: productDetailList
      }).then(response => {
        console.log(response);
        this.successDialog = true;
      });
    },
    cancel() {
      var r = confirm("are you sure want to cancel?");
      if (r) {
        this.$router.go(-1);
      }
    },

    editItem(item) {
      this.editedIndex = this.productDetailList.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    deleteItem(item) {
      const index = this.productDetailList.indexOf(item);
      confirm("Are you sure you want to delete this item?") &&
        this.productDetailList.splice(index, 1);
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
          this.productDetailList[this.editedIndex],
          this.editedItem
        );
      } else {
        this.productDetailList.push(this.editedItem);
      }
      this.close();
    },

    checkNumber(e) {
      if (e === "") {
        this.errorMessage = "The input is required";
      }
      if (this.proformaInvoicesNumber.includes(e)) {
        this.errorMessage = "This number is already taken";
      } else {
        this.errorMessage = "";
      }
    }
  }
};
</script>