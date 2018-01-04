<template>
      <ul class="treeview-menu">
        <template v-for="item in subMenus">
          <template v-if="item.url">
            <li>
              <router-link :to="{ path: item.url, exact: true}" >
                <a><i class="fa fa-circle-o"></i>&nbsp;&nbsp;{{item.name}}</a>
              </router-link>
            </li>
          </template>
          <template v-else>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-square"></i><span>{{item.name}}</span>
                <span class="pull-right-container">
                  <!--<i class="fa fa-angle-left pull-right"></i>-->
                </span>
              </a>
              <subMenu :sub-menus="item.sub_menus" :menu-name="item.name"></subMenu>
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
        props: ['subMenus'],
        mixins:[menuMixin]
    }
</script>
