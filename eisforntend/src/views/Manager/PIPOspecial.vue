<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout justify-center wrap>
      <v-flex md12>
        <v-breadcrumbs :items="bredcrumbs" divider=">"></v-breadcrumbs>
        <material-card color="green" title="List PI-PO">
          <div>
            <v-data-table :headers="headers" :items="konten" class="elevation-1">
              <v-progress-linear v-slot:progress color="blue" indeterminate></v-progress-linear>
              <template v-slot:items="props">
                <td>{{ props.index + 1}}</td>
                <td class="text-xs-left">{{ props.item.piNum }}</td>
                <td class="text-xs-left">{{ props.item.customername }}</td>
                <td class="text-xs-left">{{ props.item.finalDestination }}</td>
                <td class="text-xs-left">
                  <p :key="i" v-for="(itemss, i) in props.item.pipoSpecial2">
                    {{ itemss.productName }}
                    <v-divider v-if="i + 1 < itemss.length" :key="`divider-${i}`"></v-divider>
                  </p>
                </td>
                <td class="text-xs-right">
                  <p :key="i" v-for="(itemss, i) in props.item.pipoSpecial2">
                    {{ itemss.container }}
                    <v-divider v-if="i + 1 < itemss.length" :key="`divider-${i}`"></v-divider>
                  </p>
                </td>
                <td class="text-xs-right">
                  <p :key="i" v-for="(itemss, i) in props.item.pipoSpecial2">
                    {{ itemss.price }}
                    <v-divider v-if="i + 1 < itemss.length" :key="`divider-${i}`"></v-divider>
                  </p>
                </td>
                <td class="text-xs-right">
                  <div :key="i" v-for="(itemss, i) in props.item.pipoSpecial2">
                    <p
                      :key="j"
                      v-for="(items, j) in props.item.pipoSpecial2[i].pipoSpecial3"
                    >{{ items.poNum }}</p>
                    <v-divider v-if="i + 1 < itemss.length" :key="`divider-${i}`"></v-divider>
                  </div>
                </td>

                <td class="text-xs-right">
                  <div :key="i" v-for="(itemss, i) in props.item.pipoSpecial2">
                    <p
                      :key="j"
                      v-for="(items, j) in props.item.pipoSpecial2[i].pipoSpecial3"
                    >{{ items.poSupplierNum }}</p>
                    <v-divider v-if="i + 1 < itemss.length" :key="`divider-${i}`"></v-divider>
                  </div>
                </td>
                <td class="text-xs-right">
                  <div :key="i" v-for="(itemss, i) in props.item.pipoSpecial2">
                    <p
                      :key="j"
                      v-for="(items, j) in props.item.pipoSpecial2[i].pipoSpecial3"
                    >{{ items.supplierName }}</p>
                    <v-divider v-if="i + 1 < itemss.length" :key="`divider-${i}`"></v-divider>
                  </div>
                </td>
                <td class="text-xs-right">
                  <div :key="i" v-for="(itemss, i) in props.item.pipoSpecial2">
                    <p
                      :key="j"
                      v-for="(items, j) in props.item.pipoSpecial2[i].pipoSpecial3"
                    >{{ items.poPrice }}</p>
                    <v-divider v-if="i + 1 < itemss.length" :key="`divider-${i}`"></v-divider>
                  </div>
                </td>
                <td class="text-xs-right">
                  <div :key="i" v-for="(itemss, i) in props.item.pipoSpecial2">
                    <p
                      :key="j"
                      v-for="(items, j) in props.item.pipoSpecial2[i].pipoSpecial3"
                    >{{ items.containerQuantityPO }}</p>
                    <v-divider v-if="i + 1 < itemss.length" :key="`divider-${i}`"></v-divider>
                  </div>
                </td>

                <td class="text-xs-right">
                  <div :key="i" v-for="(itemss, i) in props.item.pipoSpecial2">
                    <div :key="j" v-for="(items, j) in props.item.pipoSpecial2[i].pipoSpecial3">
                      <p
                        :key="k"
                        v-for="(item, k) in props.item.pipoSpecial2[i].pipoSpecial3[j].pipoSpecial4"
                      >{{ item.noSI}}</p>
                    </div>
                    <v-divider v-if="i + 1 < itemss.length" :key="`divider-${i}`"></v-divider>
                  </div>
                </td>

                <td class="text-xs-right">
                  <div :key="i" v-for="(itemss, i) in props.item.pipoSpecial2">
                    <div :key="j" v-for="(items, j) in props.item.pipoSpecial2[i].pipoSpecial3">
                      <p
                        :key="k"
                        v-for="(item, k) in props.item.pipoSpecial2[i].pipoSpecial3[j].pipoSpecial4"
                      >{{ item.staffName}}</p>
                    </div>
                    <v-divider v-if="i + 1 < itemss.length" :key="`divider-${i}`"></v-divider>
                  </div>
                </td>

                <td class="text-xs-right">
                  <div :key="i" v-for="(itemss, i) in props.item.pipoSpecial2">
                    <div :key="j" v-for="(items, j) in props.item.pipoSpecial2[i].pipoSpecial3">
                      <p
                        :key="k"
                        v-for="(item, k) in props.item.pipoSpecial2[i].pipoSpecial3[j].pipoSpecial4"
                      >{{ item.invoiceNum}}</p>
                    </div>
                    <v-divider v-if="i + 1 < itemss.length" :key="`divider-${i}`"></v-divider>
                  </div>
                </td>
                <td class="text-xs-right">
                  <div :key="i" v-for="(itemss, i) in props.item.pipoSpecial2">
                    <div :key="j" v-for="(items, j) in props.item.pipoSpecial2[i].pipoSpecial3">
                      <p
                        :key="k"
                        v-for="(item, k) in props.item.pipoSpecial2[i].pipoSpecial3[j].pipoSpecial4"
                      >{{ item.quantity }}</p>
                    </div>
                    <v-divider v-if="i + 1 < itemss.length" :key="`divider-${i}`"></v-divider>
                  </div>
                </td>
              </template>
            </v-data-table>
          </div>
        </material-card>
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
        text: "PI-PO",
        disabled: true,
        href: "/manager/pi-po"
      }
    ],
    konten: [],
    headers: [
      {
        sortable: false,
        text: "No."
      },
      {
        sortable: false,
        text: "No. PI",
        value: "piNum"
      },
      {
        sortable: false,
        text: "Customer",
        value: "customername"
      },
      {
        sortable: false,
        text: "Final Destination",
        value: "finalDestination"
      },
      {
        sortable: false,
        text: "Product",
        value: "productName"
      },
      {
        sortable: false,
        text: "container",
        value: "container"
      },
      {
        sortable: false,
        text: "Total Price",
        value: "price"
      },

      {
        sortable: false,
        text: "No. PO",
        value: "poNum"
      },
      {
        sortable: false,
        text: "PO Supplier",
        value: "poSupplierNum"
      },
      {
        sortable: false,
        text: "Supplier",
        value: "supplierName"
      },

      {
        sortable: false,
        text: "PO Price",
        value: "poPrice"
      },
      {
        sortable: false,
        text: "PO Container",
        value: "containerQuantityPO"
      },

      //
      {
        sortable: false,
        text: "No. SI",
        value: "noSI"
      },
      {
        sortable: false,
        text: "Staff",
        value: "staffName"
      },
      {
        sortable: false,
        text: "Invoice",
        value: "invoiceNum"
      },

      {
        sortable: false,
        text: "Quantity",
        value: "quantity"
      }
    ]
  }),
  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/login");
      console.log("belon login");
    }
    //rolenya sokap?
    if (!this.$session.get("role") === "StaffExport") {
      this.$router.push("/");
      console.log("bukan staff");
    }
  },
  mounted() {
    Axios.get("http://localhost:8099/api/special")
      .then(response => {
        this.konten = response.data.result;
        this.sp2 = response.data.result.pipoSpecial2;
        console.log(response.data.result);
        console.log(this.sp2);
      })
      .then(console.log(this.konten));
  }
};
</script>

