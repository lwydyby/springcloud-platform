(function(window){var svgSprite='<svg><symbol id="icon-tixing" viewBox="0 0 1024 1024"><path d="M806.28 651.8h-39.336V333.624c0-124.984-91.168-229.632-210.272-250.912V44.664C556.664 20.104 536.568 0 512 0s-44.664 20.104-44.664 44.664v38.032c-119.104 21.288-210.272 125.936-210.272 250.912V651.784h-39.336C168.376 651.8 128 692.176 128 741.52s40.376 89.72 89.72 89.72h588.56c49.344 0 89.72-40.376 89.72-89.72s-40.376-89.72-89.72-89.72zM512 1024c79.528 0 144-64.472 144-144H368c0 79.528 64.472 144 144 144z"  ></path></symbol><symbol id="icon-geren" viewBox="0 0 1024 1024"><path d="M237.655 923.821h540.559c41.992 0 104.216-18.264 104.216-90.342 0-121.199-272.873-86.797-272.873-211.48 0-77.028 120.652-148.02 120.652-326.715 0-150.89-98.912-237.566-222.287-237.566-123.373 0-222.286 86.677-222.286 237.566 0 178.695 120.652 249.687 120.652 326.714 0 124.684-272.874 90.283-272.874 211.48 0.002 101.819 104.241 90.343 104.241 90.343z" fill="" ></path></symbol><symbol id="icon-jieshu" viewBox="0 0 1024 1024"><path d="M507.302752 1021.83204c-270.272406 0-489.236415-216.073394-489.236415-482.732534C18.066337 354.822865 122.851094 195.116443 276.776288 113.456598v65.761468C156.093155 255.096683 75.155963 387.342272 75.155963 539.099506c0 234.862385 193.671136 425.642908 432.146789 425.642908S939.449541 773.961891 939.449541 539.099506c0-151.757234-80.937191-284.002823-201.620324-359.88144v-65.761468C891.754411 194.39379 997.261821 354.822865 997.261821 539.099506c-0.722653 266.659139-219.686662 482.732534-489.959069 482.732534z m0-539.099506c-15.898377 0-28.90614-13.007763-28.906139-28.183487v-426.365561c0-15.898377 13.007763-28.183486 28.906139-28.183486s28.90614 13.007763 28.90614 28.183486v425.642908c0 15.898377-13.007763 28.90614-28.90614 28.90614z" fill="#040000" ></path></symbol></svg>';var script=function(){var scripts=document.getElementsByTagName("script");return scripts[scripts.length-1]}();var shouldInjectCss=script.getAttribute("data-injectcss");var ready=function(fn){if(document.addEventListener){if(~["complete","loaded","interactive"].indexOf(document.readyState)){setTimeout(fn,0)}else{var loadFn=function(){document.removeEventListener("DOMContentLoaded",loadFn,false);fn()};document.addEventListener("DOMContentLoaded",loadFn,false)}}else if(document.attachEvent){IEContentLoaded(window,fn)}function IEContentLoaded(w,fn){var d=w.document,done=false,init=function(){if(!done){done=true;fn()}};var polling=function(){try{d.documentElement.doScroll("left")}catch(e){setTimeout(polling,50);return}init()};polling();d.onreadystatechange=function(){if(d.readyState=="complete"){d.onreadystatechange=null;init()}}}};var before=function(el,target){target.parentNode.insertBefore(el,target)};var prepend=function(el,target){if(target.firstChild){before(el,target.firstChild)}else{target.appendChild(el)}};function appendSvg(){var div,svg;div=document.createElement("div");div.innerHTML=svgSprite;svgSprite=null;svg=div.getElementsByTagName("svg")[0];if(svg){svg.setAttribute("aria-hidden","true");svg.style.position="absolute";svg.style.width=0;svg.style.height=0;svg.style.overflow="hidden";prepend(svg,document.body)}}if(shouldInjectCss&&!window.__iconfont__svg__cssinject__){window.__iconfont__svg__cssinject__=true;try{document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>")}catch(e){console&&console.log(e)}}ready(appendSvg)})(window)