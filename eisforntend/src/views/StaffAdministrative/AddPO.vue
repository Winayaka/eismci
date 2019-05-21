<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout wrap>
      <v-breadcrumbs :items="items">
        <template v-slot:divider>
          <v-icon>chevron_right</v-icon>
        </template>
      </v-breadcrumbs>
      <v-flex>
        <material-card color="green" title="Add Purchase Order" text="Insert required information">
          <v-form class="container">
            <v-container>
              <v-layout wrap align-center justify-center>
                <v-flex xs12 md10>
                  <v-text-field
                    v-model="POForm.number"
                    :value="POForm.number"
                    :rules="[rules.required, rules.max50]"
                    counter
                    maxlength="50"
                    v-on:keyup="checkNumber"
                    type="text"
                    label="PO Number"
                    :items="PO"
                    :error-messages="errorMessage"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    label="Date"
                    type="date"
                    :value="POForm.date"
                    v-model="POForm.date"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    label="ETA"
                    type="date"
                    :value="POForm.shipment"
                    v-model="POForm.shipment"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    label="Supplier"
                    type="text"
                    :rules="[rules.required, rules.max50]"
                    counter
                    maxlength="50"
                    :value="POForm.supplierName"
                    v-model="POForm.supplierName"
                    :error-messages="errorMessage"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    label="Vessel"
                    type="text"
                    :rules="[rules.required, rules.max50]"
                    counter
                    maxlength="50"
                    :value="POForm.vessel"
                    v-model="POForm.vessel"
                    :error-messages="errorMessage"
                    required
                  />
                </v-flex>

                <v-flex xs12 md10>
                  <v-text-field
                    label="PO Number (Supplier)"
                    type="text"
                    :rules="[rules.required, rules.max50]"
                    counter
                    maxlength="50"
                    :value="POForm.poNumSupplier"
                    v-model="POForm.poNumSupplier"
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
                                v-model="editedItem.productName"
                                :items="products"
                                item-text="name"
                                item-value="name"
                                label="Select Product"
                                required
                              />
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field
                                v-model="editedItem.quantity"
                                label="Quantity"
                                :rules="[rules.required, rules.max20]"
                                counter
                                maxlength="20"
                                v-on:keyup="checkNumber"
                                :error-messages="errorMessage"
                                required
                              ></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field
                                v-model="editedItem.freightCost"
                                label="Freight cost"
                                :rules="[rules.required, rules.max20]"
                                counter
                                maxlength="20"
                                v-on:keyup="checkNumber"
                                :error-messages="errorMessage"
                                required
                              ></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field
                                v-model="editedItem.pricePerUnit"
                                label="Price/Unit"
                                :rules="[rules.required, rules.max20]"
                                counter
                                maxlength="20"
                                v-on:keyup="checkNumber"
                                :error-messages="errorMessage"
                                required
                              ></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field
                                v-model="editedItem.quantityRemaining"
                                label="Quantity remaining"
                                :rules="[rules.required, rules.max20]"
                                counter
                                maxlength="20"
                                v-on:keyup="checkNumber"
                                :error-messages="errorMessage"
                                required
                              ></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field
                                v-model="editedItem.containerQuantity"
                                label="Container quantity"
                                :rules="[rules.required, rules.max20]"
                                counter
                                maxlength="20"
                                v-on:keyup="checkNumber"
                                :error-messages="errorMessage"
                                required
                              ></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-select
                                v-model="editedItem.containerName"
                                :items="container"
                                item-text="name"
                                item-value="name"
                                label="Container"
                                required
                              />
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field v-model="editedItem.date" label="Date" type="date"></v-text-field>
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
                <v-data-table :headers="headers" :items="product" class="elevation-1">
                  <template v-slot:items="props">
                    <td>{{ props.item.productName }}</td>
                    <td>{{ props.item.quantity }}</td>
                    <td>{{ props.item.freightCost }}</td>
                    <td>{{ props.item.pricePerUnit }}</td>
                    <td>{{ props.item.quantityRemaining }}</td>
                    <td>{{ props.item.containerQuantity }}</td>
                    <td>{{ props.item.date }}</td>
                    <td>{{ props.item.containerName }}</td>
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
                <v-btn color="green" round @click="addPO()">Submit</v-btn>
              </v-flex>
            </v-layout>
          </v-form>
        </material-card>
      </v-flex>
    </v-layout>
    <v-dialog v-model="successDialog" persistent width="400">
      <v-card>
        <v-card-title class="headline grey lighten-2" primary-title>Congratulations!</v-card-title>
        <v-card-text>You have successfully add Purchase Order.</v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn small color="success" @click="$router.go(-1)">Back to PO List</v-btn>
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
    beforeCreate: function() {
      if (!this.$session.exists()) {
        this.$router.push("/login");
        console.log("belon login");
      }
      if (!this.$session.get("role") === "Manager") {
        this.$router.push("/");
        console.log("bukan staff");
      }
    },
    items: [
      {
        text: "Home",
        disabled: false,
        href: "/Manager"
      },
      {
        text: "Purchase Order",
        disabled: false,
        href: "/po"
      },
      {
        text: "Add Purchase Order",
        disabled: true,
        href: "/po/add"
      }
    ],
    valid: true,
    rules: {
      required: value => !!value || "The input is required.",
      max20: v => v.length <= 20 || "Input must be less than 20 characters",
      max50: v => v.length <= 50 || "Input must be less than 50 characters",
      max255: v => v.length <= 50 || "Input must be less than 255 characters"
    },
    errorMessage: "",
    POForm: {
      number: "",
      shipment: "",
      date: "",
      supplierName: "",
      vessel: "",
      poNumSupplier: ""
    },
    product: [],
    editedIndex: -1,
    editedItem: {
      productName: "",
      quantity: "",
      freightCost: "",
      pricePerUnit: "",
      quantityRemaining: "",
      containerQuantity: "",
      containerName: ""
    },
    defaultItem: {
      productName: "",
      quantity: "",
      freightCost: "",
      pricePerUnit: "",
      quantityRemaining: "",
      containerQuantity: "",
      date: "",
      containerName: ""
    },
    numberExisted: "",
    purchaseOrdersNumber: [],
    container: [],
    products: [],
    dictionary: {
      attributes: {
        email: "E-mail Address"
        // custom attributes
      },
      custom: {
        name: {
          required: () => "Name can not be empty",
          max: "The name field may not be greater than 10 characters"
          // custom messages
        },
        number: {
          required: () => "Number can not be empty"
        },
        countryOfOrigin: {
          required: () => "This field can not be empty"
        },
        select: {
          required: "Select field is required"
        }
      }
    },
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
      { text: "Quantity Remaining", value: "quantityRemaining" },
      { text: "Container Quantity", value: "containerQuantity" },
      { text: "Date", value: "date" },
      { text: "Container Name", value: "containerName" },
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
    Axios.get("http://localhost:8099/api/getAllPurchaseOrder")
      .then(response => {
        var purchaseOrders = response.data.result;
        var purchaseOrdersNumber = [];
        for (var i = 0; i < purchaseOrders.length; i++) {
          purchaseOrdersNumber[i] = purchaseOrders[i]["number"];
        }
        this.purchaseOrdersNumber = purchaseOrdersNumber;
      })
      .then(console.log(this.response));
    Axios.get("http://localhost:8099/api/getAllContainer")
      .then(response => {
        this.container = response.data.result;
      })
      .then(console.log(this.response));
    Axios.get("http://localhost:8099/api/getAllProduct")
      .then(response => {
        this.products = response.data.result;
      })
      .then(console.log(this.response));
  },
  methods: {
    addPO() {
      var POForm = this.POForm;
      var product = this.product;
      console.log(POForm);
      console.log(product);
      console.log(product.productName);
      Axios.post("http://localhost:8099/api/po/add", {
        number: POForm.number,
        shipment: POForm.shipment,
        date: POForm.date,
        supplierName: POForm.supplierName,
        vessel: POForm.vessel,
        poNumSupplier: POForm.poNumSupplier,
        product: product
      }).then(response => {
        console.log(response);
        this.successDialog = true;
      });
    },
    cancel() {
      var r = confirm("are you sure want to cancel?");
      if (r) {
        this.$router.push({ path: "/po" });
      }
    },
    editItem(item) {
      this.editedIndex = this.product.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      const index = this.product.indexOf(item);
      confirm("Are you sure you want to delete this item?") &&
        this.product.splice(index, 1);
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
        Object.assign(this.product[this.editedIndex], this.editedItem);
      } else {
        this.product.push(this.editedItem);
      }
      this.close();
    },
    checkNumber(e) {
      if (e === "") {
        this.errorMessage = "The input is required";
      }
      if (this.purchaseOrdersNumber.includes(e)) {
        this.errorMessage = "This number is already taken";
      } else {
        this.errorMessage = "";
      }
    }
  }
};
</script>