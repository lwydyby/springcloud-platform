<template>
  <div>
    <div>
      <el-menu :router="true" class="el-menu-vertical-demo">
        <template v-for="item in menus">
          <template v-if="item.url">
            <router-link :to="{ path: item.url, exact: true}" >
              <el-menu-item  :index="item.url"><i class="el-icon-menu"></i>{{item.name}}</el-menu-item>
            </router-link>
          </template>
          <template v-else>
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>{{item.name}}</span>
              </template>
              <template v-for="items in item.subMenu">
                <router-link :to="{ path: items.url, exact: true}" >
                  <el-menu-item  :index="items.url"><i class="el-icon-menu"></i>{{items.name}}</el-menu-item>
                </router-link>
              </template>
            </el-submenu>
          </template>
        </template>
      </el-menu>
    </div>
  </div>
</template>
<script>
  import {menuMixin} from './assets/util/menuMixin'
  import subMenu from './subMenu.vue'
  export default{
    data () {
      return {
      }
    },
    name: 'menus',
    props: ['menus'],
    created: function () {
    },
    components: {
      subMenu
    },
    mixins: [menuMixin]
  }
</script>
<style>

</style>
