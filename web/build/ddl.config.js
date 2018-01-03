const webpack = require('webpack');

const vendors = [
  'element-ui',
  'gameley-elementui',
  'echarts',
  // ...其它库
];

module.exports = {
  output: {
    path: 'build',
    filename: '[name].js',
    library: '[name]',
  },
  entry: {
    "lib": vendors,
  },
};
