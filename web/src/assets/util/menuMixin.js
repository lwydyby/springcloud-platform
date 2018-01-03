import utils from './utils'
import { mapGetters } from 'vuex'
import {GET_ALLMENUS} from '../../store/mutations'

var menuMixin = {
  data () {
  },
  computed: {
    ...mapGetters([
      GET_ALLMENUS
    ])
  },
  methods: {
    getNav: function (menuId) {
      let modelName = utils.getModelNameByMenuId(this.getAllMenus, menuId)
      this.nav.modelName = modelName
      let menuNav = utils.getMenuNamesByMenuId(this.getAllMenus, menuId)
      this.nav.menuNav = menuNav
    }
  }
}
export {menuMixin}
