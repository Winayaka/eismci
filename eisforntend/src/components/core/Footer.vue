<template>
  <v-footer
    id="core-footer"
    absolute
    height="82"
  >
    <v-spacer/>
    <span class="font-weight-light copyright">
      &copy;
      {{ (new Date()).getFullYear() }}
      <a>C6</a>, made with
      <v-icon
        color="tertiary"
        size="17">mdi-heart</v-icon>
      for a Information System
    </span>
  </v-footer>
</template>

<script>
export default {

}
</script>

<style>
#core-footer {
  z-index: 0;
}
</style>
