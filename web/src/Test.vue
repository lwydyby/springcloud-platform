<template>
  <div>
    <div id="a1">{{ message }}</div>
    <div id="app-2">
          <span :title="stop">
            鼠标悬停此处几秒，
            可以看到此处动态绑定的 title！
          </span>
    </div>
    <div id="example-2">
      <simple-counter></simple-counter>
      <simple-counter></simple-counter>
      <simple-counter></simple-counter>
    </div>
    <div id="ch1">
      <child message="hello!"></child>
    </div>
    <div id="app-3" @click="seen=!seen">
      <transition name="fade" mode="out-in">
        <p v-if="seen" key="yes">你能看到我</p>
        <p v-else="!seen" key="no">你看不到我</p>
      </transition>
    </div>
    <div id="app-5">
      <button @click="reversemessage">{{ testmess }}</button>
    </div>
    <div id="app-6">
      <p>{{ change }}</p>
      <input v-model="change">
    </div>
    <div>
      <ol id="zujian">
        <own-item></own-item>
      </ol>
    </div>
    <div>
      <ul id="ex2">
        <li v-for="(item,index) in items">
          {{ parentmess }} - {{ index }} - {{ item.mess }}
        </li>
      </ul>
    </div>
    <div id="parentMsg">
      <input v-model="parentMsg">
      <br>
      <child :message="parentMsg"></child>
    </div>
    <div id="counter-event-example">
      <p>{{ total }}</p>
      <button-counter v-on:increment="incrementTotal"></button-counter>
      <button-counter v-on:increment="incrementTotal"></button-counter>
    </div>
    <div id="demo1">
      <button v-on:click="show = !show" @click="changeactiveName2">
        Toggle
      </button>
      <transition name="fade" :duration="1000" appear>
        <p v-if="show">hello! I appeare.</p>
      </transition>
    </div>
    <div id="app10" >
      <el-upload
        class="upload-demo"
        action="https://jsonplaceholder.typicode.com/posts/"
        :on-preview="handlePictureCardPreview"
        :on-remove="handleRemove"
        :file-list="fileList"
        list-type="picture-card">
        <i class="el-icon-plus"></i>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>
      <!--<el-dialog v-model="dialogVisible" size="tiny">-->
        <!--<img width="100%" :src="dialogImageUrl" alt="">-->
      <!--</el-dialog>-->
      <div ref="img" v-if="dialogVisible == true">
        <img width="100%" :src="dialogImageUrl" alt="" @mouseenter="changesize" @mouseout="changesize1" @click="dialogVisible=false">
      </div>
    </div>
    <div class="charts">
      <div id="myChart" :style="{width:'500px',height:'500px',margin:'auto'}"></div>
    </div>
    <template><vue-event-calendar></vue-event-calendar></template>
  </div>
</template>
<script>
  import Vue from 'vue'
//  import echarts from 'echarts'
  export default {
    data () {
      return {
        message: a1.message,
        seen: app3.seen,
        stop: app2.message,
        testmess: app5.testmess,
        change: app6.message,
        parentmess: 'parent',
        items: ex2.items,
        parentMsg: parentMsg.parentMsg,
        show: demo1.show,
        dialogImageUrl: this.dialogImageUrl,
        dialogVisible: this.dialogVisible,
        fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}]
      }
    },
    mounted () {
      var echarts = require('echarts')
      let myChart = echarts.init(document.getElementById('myChart'))
      myChart.setOption(options)
    },
    methods: {
      reversemessage: function () {
        this.testmess = this.testmess.split('').reverse().join('')
      },
      handleRemove (file, fileList) {
        console.log(file, fileList)
      },
      handlePictureCardPreview (file) {
        this.dialogImageUrl = file.url
        this.dialogVisible = true
      },
      changesize: function () {
        this.$refs.img.style.width = '100px'
      },
      changesize1: function () {
        this.$refs.img.style.width = '500px'
      },
      changeactiveName2: function () {
        this.$store.commit('newAuthor', 'first')
      }
    }
  }
  var options = {
    title: {
      text: 'ECharts 入门示例',
      x: 'center'
    },
    tooltip: {},
    legend: {
      orient: 'vertical',
      left: 'left',
      data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
    },
    series: [
      {
        name: '访问来源',
        type: 'pie',
        radius: '55%',
        center: ['50%', '60%'],
        data: [
          {value: 235, name: '视频广告'},
          {value: 274, name: '联盟广告'},
          {value: 310, name: '邮件营销'},
          {value: 335, name: '直接访问'},
          {value: 400, name: '搜索引擎'}
        ]
      }
    ]
  }
//  let chartBox = document.getElementsByClassName('charts')[0]
//  let myChart = document.getElementById('myChart')
//
//  // 用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
//  function resizeCharts () {
//    myChart.style.width = chartBox.style.width + 'px'
//    myChart.style.height = chartBox.style.height + 'px'
//  }
//  // 设置容器高宽
//  resizeCharts()
  var activeName2 = 'second'
  Vue.component('child', {
    // 声明 props
    props: ['message'],
    // 就像 data 一样，prop 可以在组件模板内部使用，
    // 并且，还可以在 vm 实例中通过 this.message 访问
    template: '<span>{{ message }}{{ message }}</span>'
  })
  Vue.component('simple-counter', {
    template: '<button v-on:click="counter += 1">{{ counter }}</button>',
    // 严格来看 data 是一个函数，
    // 因此 Vue 不会警告，但是我们为每个组件实例
    // 返回同一个对象的引用
    data: function () {
      return {
        counter: 0
      }
    }
  })
  Vue.component('own-item', {
    template: '<li>这是自创的组件</li>'
  })
  var a1 = new Vue({// eslint-disable-line no-unused-vars
    el: '#a1',
    data: {
      message: 'aaa'
    }
  })
  var example2 = new Vue({// eslint-disable-line no-unused-vars
    el: '#example-2'
  })
  var app2 = new Vue({// eslint-disable-line no-unused-vars
    el: '#app-2',
    data: {
      message: '页面加载于 ' + new Date()
    }
  })
  var app3 = new Vue({// eslint-disable-line no-unused-vars
    el: '#app-3',
    data: {
      seen: true
    }
  })
  var app5 = new Vue({
    el: '#app-5',
    data: {
      testmess: 'this is the test message!'
    },
    methods: {
      reversemessage: function () {
        this.testmess = this.testmess.split('').reverse().join('')
      }
    }
  })
  var app6 = new Vue({
    el: '#app-6',
    data: {
      message: 'asdf'
    }
  })
  var zujian = new Vue({
    el: '#zujian'
  })
  var ch1 = new Vue({
    el: '#ch1'
  })
  var ex2 = new Vue({// eslint-disable-line no-unused-vars
    el: '#ex2',
    data: {
      parentmess: 'parent',
      items: [
        {mess: 'aa'},
        {mess: 'bb'}
      ]
    }
  })
  var parentMsg = new Vue({
    el: '#parentMsg',
    data: {
      parentMsg: 'aaa'
    }
  })
  Vue.component('button-counter', {
    template: '<button v-on:click="incrementCounter">{{ counter }}</button>',
    data: function () {
      return {
        counter: 0
      }
    },
    methods: {
      incrementCounter: function () {
        this.counter += 1
        this.$emit('increment')
      }
    }
  })
  var countereventexample = new Vue({
    el: '#counter-event-example',
    data: {
      total: 0
    },
    methods: {
      incrementTotal: function () {
        this.total += 1
      }
    }
  })
  var demo1 = new Vue({
    el: '#demo1',
    data: {
      show: true
    }
  })
  var Main = {
    data () {
      return {
        fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}]
      }
    },
    methods: {
      handleRemove (file, fileList) {
        console.log(file, fileList)
      },
      handlePreview (file) {
        console.log(file)
      }
    }
  }
  var Ctor = Vue.extend(Main)
  new Ctor().$mount('#app10')
  Vue.use({app5, app6, zujian, ch1, parentMsg, countereventexample, demo1})
</script>
<style>
  .fade-enter-active, .fade-leave-active {
    transition: opacity .5s
  }
  .fade-enter, .fade-leave-to /* .fade-leave-active 在低于版本 2.1.8 中 */ {
    opacity: 0
  }
</style>
