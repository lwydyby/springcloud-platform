<template>
  <div>
    <div>
      <el-menu :router="true" class="el-menu-vertical-demo"   background-color="#1C2632"  text-color="#FBFDFF">
        <template v-for="item in menus">
          <el-menu-item v-if="item.model_url" :index="item.model_url"  @click="getNav(item.menu_id)" ><i class="el-icon-menu"></i>{{item.menu_name}}</el-menu-item>
          <el-menu-item v-else-if="item.menu_url" :index="item.menu_path" @click="getNav(item.menu_id)"><i class="el-icon-menu"></i>{{item.menu_name}}</el-menu-item>
          <el-submenu v-else :index="item.menu_name">
            <template slot="title"><i class="el-icon-message"></i>{{item.menu_name}}</template>
            <template v-for="i in item.sub_menus">
              <el-menu-item v-if="i.menu_path" :index="i.menu_path" @click="getNav(i.menu_id)"><i class="el-icon-menu"></i>{{i.menu_name}}</el-menu-item>
            </template>
          </el-submenu>
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
    props: ['menus', 'nav'],
    created: function () {
      console.log(this.menus)
    },
    components: {
      subMenu
    },
    mixins: [menuMixin]
  }
</script>
<style>

</style>
