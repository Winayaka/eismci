<template>
  <v-flex xs12 md6>
    <v-card color="blue-grey darken-2" class="white--text">
      <v-card-title primary-title>
        <div>
          <div class="headline">Welcome to EIS for Manager</div>
          <span>Your friendly export system .</span>
        </div>
      </v-card-title>
    </v-card>
  </v-flex>
</template>
<script>
export default {};
</script>

<style>
</style>

<script>
import Axios from "axios";

export default {
  data: () => ({}),

  beforeCreate: function() {
    if (!this.$session.exists()) {
      this.$router.push("/");
      console.log("belum login");
    }
  }
};
</script>
