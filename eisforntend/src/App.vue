<template>
  <v-app>
    <template>
      <v-container grid-list-md text-xs-center>
        <core-toolbar/>
        <v-layout row wrap>
          <v-flex xs2>
            <router-view name="nav"></router-view>
          </v-flex>
          <v-flex xs10>
            <router-view></router-view>
          </v-flex>
        </v-layout>
      </v-container>
    </template>
  </v-app>
</template>

<style lang="scss">
@import '@/styles/index.scss';
/* Remove in 1.2 */
.v-datatable thead th.column.sortable i {
  vertical-align: unset;
}
</style>