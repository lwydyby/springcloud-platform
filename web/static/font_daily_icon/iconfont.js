(function(window){var svgSprite='<svg><symbol id="icon-wenhao" viewBox="0 0 1024 1024"><path d="M552.782699 759.075041H451.707324v-48.124611c-3.217435-89.814353 38.506535-165.218705 125.137681-226.213057 99.398202-67.394993 144.408062-139.58191 134.789985-216.594979-12.835512-93.031788-70.612428-144.408062-173.29652-154.026139-131.606779-6.43487-210.194338 59.385634-235.865361 197.324598L201.397734 277.760471C243.053247 101.314971 358.572852 16.258315 547.956546 22.658956c170.079085 9.618077 261.536384 93.100244 274.371896 250.30959 6.43487 99.500886-51.342046 190.958184-173.29652 274.371896-67.394993 44.941405-99.500886 101.075375-96.283451 168.470368l0.034228 43.264231z m9.618076 240.657285v-120.345756H437.263095v120.345756h125.13768z"  ></path></symbol><symbol id="icon-duihao" viewBox="0 0 1365 1024"><path d="M8.061712 595.237445a32.523283 32.523283 0 0 1-2.318222-39.887045l36.136982-48.341735a26.727729 26.727729 0 0 1 36.818811-5.795554l311.459868 220.640134c23.795861 16.841197 60.819221 15.272988 82.296861-3.272783L1299.379235 6.4092a27.614108 27.614108 0 0 1 38.250653 1.840941l19.091236 20.318529a28.295938 28.295938 0 0 1 0.204549 38.250654L469.046863 1008.08541a48.546284 48.546284 0 0 1-72.069414-0.545464L8.061712 595.237445z" fill="#09BB07" ></path></symbol><symbol id="icon-gantanhao" viewBox="0 0 1024 1024"><path d="M573.944325 698.381156L600.256959 0H427.03212l26.312634 698.381156h120.599571zM511.45182 1024c58.107066 0 105.250535-47.143469 105.250535-107.443255 0-59.203426-47.143469-106.346895-105.250535-106.346895S407.297645 857.353319 407.297645 916.556745c0 57.010707 43.85439 107.443255 104.154175 107.443255z" fill="#FF6600" ></path></symbol></svg>';var script=function(){var scripts=document.getElementsByTagName("script");return scripts[scripts.length-1]}();var shouldInjectCss=script.getAttribute("data-injectcss");var ready=function(fn){if(document.addEventListener){if(~["complete","loaded","interactive"].indexOf(document.readyState)){setTimeout(fn,0)}else{var loadFn=function(){document.removeEventListener("DOMContentLoaded",loadFn,false);fn()};document.addEventListener("DOMContentLoaded",loadFn,false)}}else if(document.attachEvent){IEContentLoaded(window,fn)}function IEContentLoaded(w,fn){var d=w.document,done=false,init=function(){if(!done){done=true;fn()}};var polling=function(){try{d.documentElement.doScroll("left")}catch(e){setTimeout(polling,50);return}init()};polling();d.onreadystatechange=function(){if(d.readyState=="complete"){d.onreadystatechange=null;init()}}}};var before=function(el,target){target.parentNode.insertBefore(el,target)};var prepend=function(el,target){if(target.firstChild){before(el,target.firstChild)}else{target.appendChild(el)}};function appendSvg(){var div,svg;div=document.createElement("div");div.innerHTML=svgSprite;svgSprite=null;svg=div.getElementsByTagName("svg")[0];if(svg){svg.setAttribute("aria-hidden","true");svg.style.position="absolute";svg.style.width=0;svg.style.height=0;svg.style.overflow="hidden";prepend(svg,document.body)}}if(shouldInjectCss&&!window.__iconfont__svg__cssinject__){window.__iconfont__svg__cssinject__=true;try{document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>")}catch(e){console&&console.log(e)}}ready(appendSvg)})(window)