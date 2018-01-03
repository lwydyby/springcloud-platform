<template>
      <ul class="treeview-menu">
        <template v-for="item in subMenus">
          <template v-if="item.menu_url">
            <li>
              <router-link :to="{ path: item.menu_path, exact: true}" >
                <a @click="getNav(item.menu_id)"><i class="fa fa-circle-o"></i>&nbsp;&nbsp;{{item.menu_name}}</a>
              </router-link>
            </li>
          </template>
          <template v-else>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-square"></i><span>{{item.menu_name}}</span>
                <span class="pull-right-container">
                  <!--<i class="fa fa-angle-left pull-right"></i>-->
                </span>
              </a>
              <subMenu :sub-menus="item.sub_menus" :menu-name="item.menu_name" :nav.sync="nav"></subMenu>
            </li>
          </template>
        </template>
      </ul>
</template>
<style>
    /*body{
        background-color:#ff0000;
    }*/
</style>
<script>
    import {menuMixin} from './assets/util/menuMixin';
    export default{
        data(){
            return{
                msg:'hello vue'
            }
        },
        name: 'subMenu',
        props: ['subMenus','menuName','nav'],
        mixins:[menuMixin]
    }
</script>
