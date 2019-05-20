<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout wrap>
      <v-breadcrumbs :items="items">
        <template v-slot:divider>
          <v-icon>chevron_right</v-icon>
        </template>
      </v-breadcrumbs>
      <v-flex>
        <material-card color="green" title="Edit Purchase Order" text="Insert required information">
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
                                v-model="editedItem.product.name"
                                :items="products"
                                item-text="name"
                                item-value="name"
                                label="Select Product"
                                required
                              />/>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field
                                v-model="editedItem.quantity"
                                label="Quantity"
                                counter
                                maxlength="20"
                                v-on:keyup="checkNumber"
                              ></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field
                                v-model="editedItem.freightCost"
                                label="Freight cost"
                                counter
                                maxlength="20"
                                v-on:keyup="checkNumber"
                              ></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field
                                v-model="editedItem.pricePerUnit"
                                label="Price/Unit"
                                counter
                                maxlength="20"
                                v-on:keyup="checkNumber"
                              ></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field
                                v-model="editedItem.quantityRemaining"
                                label="Quantity remaining"
                                counter
                                maxlength="20"
                                v-on:keyup="checkNumber"
                              ></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <v-text-field
                                v-model="editedItem.containerQuantity"
                                label="Container quantity"
                                counter
                                maxlength="20"
                                v-on:keyup="checkNumber"
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
                    <td>{{ props.item.product.name }}</td>
                    <td>{{ props.item.quantity }}</td>
                    <td>{{ props.item.freightCost }}</td>
                    <td>{{ props.item.pricePerUnit }}</td>
                    <td>{{ props.item.quantityRemaining }}</td>
                    <td>{{ props.item.containerQuantity }}</td>
                    <td>{{ props.item.date }}</td>
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
                <v-btn color="green" round @click="editPO()">Submit</v-btn>
              </v-flex>
            </v-layout>
          </v-form>
        </material-card>
      </v-flex>
    </v-layout>
    <v-dialog v-model="successDialog" persistent width="500">
      <v-card>
        <v-card-title class="headline grey lighten-2" primary-title>Congratulations!</v-card-title>
        <v-card-text>You have successfully update Purchase Order.</v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" flat @click="$router.go(-1)">Back to Detail PO</v-btn>
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
        text: "Dashboard",
        disabled: false,
        href: "/dashboard"
      },
      {
        text: "Purchase Order",
        disabled: false,
        href: "/po"
      },
      {
        text: "Edit Purchase Order",
        disabled: true,
        href: "/po/edit"
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
    POForm: [],
    product: [],
    editedIndex: -1,
    editedItem: {
      id: "0",
      product: {
        name: ""
      },
      quantity: "",
      freightCost: "",
      pricePerUnit: "",
      quantityRemaining: "",
      containerQuantity: "",
      container: {
        name: ""
      }
    },
    defaultItem: {
      id: "0",
      product: {
        name: ""
      },
      quantity: "",
      freightCost: "",
      pricePerUnit: "",
      quantityRemaining: "",
      containerQuantity: "",
      container: {
        name: ""
      }
    },
    numberExisted: "",
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
    var id = this.$route.query.id;
    Axios.get("http://mciexport.herokuapp.com/api/po/?id=" + id)
      .then(response => {
        this.POForm = response.data.result;
        this.product = response.data.result.purchaseOrder;
        this.POForm.supplierName = response.data.result.namaSupplier;
      })
      .then(console.log(this.url));
    Axios.get("http://mciexport.herokuapp.com/api/getAllContainer")
      .then(response => {
        this.container = response.data.result;
      })
      .then(console.log(this.response));
    Axios.get("http://mciexport.herokuapp.com/api/getAllProduct")
      .then(response => {
        this.products = response.data.result;
      })
      .then(console.log(this.response));
  },
  methods: {
    editPO() {
      var id = this.$route.query.id;
      var POForm = this.POForm;
      var product = this.product;
      Axios.post("http://mciexport.herokuapp.com/api/po/update?id=" + id, {
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
        this.$router.go(-1);
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
      if (this.productsNumber.includes(e)) {
        this.errorMessage = "This number is already taken";
      } else {
        this.errorMessage = "";
      }
    }
  }
};
</script>