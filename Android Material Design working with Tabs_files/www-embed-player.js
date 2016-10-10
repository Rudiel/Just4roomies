(function(){var g,aa=aa||{},l=this;function p(a){return void 0!==a}
function q(a,b,c){a=a.split(".");c=c||l;a[0]in c||!c.execScript||c.execScript("var "+a[0]);for(var d;a.length&&(d=a.shift());)!a.length&&p(b)?c[d]=b:c[d]?c=c[d]:c=c[d]={}}
function r(a,b){for(var c=a.split("."),d=b||l,e;e=c.shift();)if(null!=d[e])d=d[e];else return null;return d}
function t(){}
function u(){throw Error("unimplemented abstract method");}
function ba(a){a.getInstance=function(){return a.U?a.U:a.U=new a}}
function ca(a){var b=typeof a;if("object"==b)if(a){if(a instanceof Array)return"array";if(a instanceof Object)return b;var c=Object.prototype.toString.call(a);if("[object Window]"==c)return"object";if("[object Array]"==c||"number"==typeof a.length&&"undefined"!=typeof a.splice&&"undefined"!=typeof a.propertyIsEnumerable&&!a.propertyIsEnumerable("splice"))return"array";if("[object Function]"==c||"undefined"!=typeof a.call&&"undefined"!=typeof a.propertyIsEnumerable&&!a.propertyIsEnumerable("call"))return"function"}else return"null";
else if("function"==b&&"undefined"==typeof a.call)return"object";return b}
function v(a){return"array"==ca(a)}
function da(a){var b=ca(a);return"array"==b||"object"==b&&"number"==typeof a.length}
function w(a){return"string"==typeof a}
function ea(a){return"number"==typeof a}
function fa(a){return"function"==ca(a)}
function ha(a){var b=typeof a;return"object"==b&&null!=a||"function"==b}
function ia(a){return a[ja]||(a[ja]=++ka)}
var ja="closure_uid_"+(1E9*Math.random()>>>0),ka=0;function la(a,b,c){return a.call.apply(a.bind,arguments)}
function ma(a,b,c){if(!a)throw Error();if(2<arguments.length){var d=Array.prototype.slice.call(arguments,2);return function(){var c=Array.prototype.slice.call(arguments);Array.prototype.unshift.apply(c,d);return a.apply(b,c)}}return function(){return a.apply(b,arguments)}}
function x(a,b,c){x=Function.prototype.bind&&-1!=Function.prototype.bind.toString().indexOf("native code")?la:ma;return x.apply(null,arguments)}
function na(a,b){var c=Array.prototype.slice.call(arguments,1);return function(){var b=c.slice();b.push.apply(b,arguments);return a.apply(this,b)}}
var y=Date.now||function(){return+new Date};
function z(a,b){function c(){}
c.prototype=b.prototype;a.B=b.prototype;a.prototype=new c;a.prototype.constructor=a;a.Re=function(a,c,f){for(var h=Array(arguments.length-2),k=2;k<arguments.length;k++)h[k-2]=arguments[k];return b.prototype[c].apply(a,h)}}
;function oa(a){if(Error.captureStackTrace)Error.captureStackTrace(this,oa);else{var b=Error().stack;b&&(this.stack=b)}a&&(this.message=String(a))}
z(oa,Error);oa.prototype.name="CustomError";var pa;var qa=String.prototype.trim?function(a){return a.trim()}:function(a){return a.replace(/^[\s\xa0]+|[\s\xa0]+$/g,"")};
function ra(a){return decodeURIComponent(a.replace(/\+/g," "))}
var sa=/&/g,ta=/</g,ua=/>/g,va=/"/g,wa=/'/g,xa=/\x00/g,ya=/[\x00&<>"']/;function za(a){var b={"&amp;":"&","&lt;":"<","&gt;":">","&quot;":'"'},c;c=l.document.createElement("div");return a.replace(Aa,function(a,e){var f=b[a];if(f)return f;if("#"==e.charAt(0)){var h=Number("0"+e.substr(1));isNaN(h)||(f=String.fromCharCode(h))}f||(c.innerHTML=a+" ",f=c.firstChild.nodeValue.slice(0,-1));return b[a]=f})}
function Ba(a){return a.replace(/&([^;]+);/g,function(a,c){switch(c){case "amp":return"&";case "lt":return"<";case "gt":return">";case "quot":return'"';default:if("#"==c.charAt(0)){var d=Number("0"+c.substr(1));if(!isNaN(d))return String.fromCharCode(d)}return a}})}
var Aa=/&([^;\s<&]+);?/g,Ca={"\x00":"\\0","\b":"\\b","\f":"\\f","\n":"\\n","\r":"\\r","\t":"\\t","\x0B":"\\x0B",'"':'\\"',"\\":"\\\\","<":"<"},Da={"'":"\\'"};
function Ea(a,b){for(var c=0,d=qa(String(a)).split("."),e=qa(String(b)).split("."),f=Math.max(d.length,e.length),h=0;0==c&&h<f;h++){var k=d[h]||"",m=e[h]||"";do{k=/(\d*)(\D*)(.*)/.exec(k)||["","","",""];m=/(\d*)(\D*)(.*)/.exec(m)||["","","",""];if(0==k[0].length&&0==m[0].length)break;c=Fa(0==k[1].length?0:parseInt(k[1],10),0==m[1].length?0:parseInt(m[1],10))||Fa(0==k[2].length,0==m[2].length)||Fa(k[2],m[2]);k=k[3];m=m[3]}while(0==c)}return c}
function Fa(a,b){return a<b?-1:a>b?1:0}
function Ga(a){for(var b=0,c=0;c<a.length;++c)b=31*b+a.charCodeAt(c)>>>0;return b}
;var Ha=Array.prototype.indexOf?function(a,b,c){return Array.prototype.indexOf.call(a,b,c)}:function(a,b,c){c=null==c?0:0>c?Math.max(0,a.length+c):c;
if(w(a))return w(b)&&1==b.length?a.indexOf(b,c):-1;for(;c<a.length;c++)if(c in a&&a[c]===b)return c;return-1},A=Array.prototype.forEach?function(a,b,c){Array.prototype.forEach.call(a,b,c)}:function(a,b,c){for(var d=a.length,e=w(a)?a.split(""):a,f=0;f<d;f++)f in e&&b.call(c,e[f],f,a)},Ia=Array.prototype.filter?function(a,b,c){return Array.prototype.filter.call(a,b,c)}:function(a,b,c){for(var d=a.length,e=[],f=0,h=w(a)?a.split(""):a,k=0;k<d;k++)if(k in h){var m=h[k];
b.call(c,m,k,a)&&(e[f++]=m)}return e},Ja=Array.prototype.map?function(a,b,c){return Array.prototype.map.call(a,b,c)}:function(a,b,c){for(var d=a.length,e=Array(d),f=w(a)?a.split(""):a,h=0;h<d;h++)h in f&&(e[h]=b.call(c,f[h],h,a));
return e},Ka=Array.prototype.some?function(a,b,c){return Array.prototype.some.call(a,b,c)}:function(a,b,c){for(var d=a.length,e=w(a)?a.split(""):a,f=0;f<d;f++)if(f in e&&b.call(c,e[f],f,a))return!0;
return!1},La=Array.prototype.every?function(a,b,c){return Array.prototype.every.call(a,b,c)}:function(a,b,c){for(var d=a.length,e=w(a)?a.split(""):a,f=0;f<d;f++)if(f in e&&!b.call(c,e[f],f,a))return!1;
return!0};
function Ma(a,b,c){b=Na(a,b,c);return 0>b?null:w(a)?a.charAt(b):a[b]}
function Na(a,b,c){for(var d=a.length,e=w(a)?a.split(""):a,f=0;f<d;f++)if(f in e&&b.call(c,e[f],f,a))return f;return-1}
function B(a,b){return 0<=Ha(a,b)}
function Oa(a,b){B(a,b)||a.push(b)}
function Pa(a,b){var c=Ha(a,b),d;(d=0<=c)&&Array.prototype.splice.call(a,c,1);return d}
function Qa(a,b){var c=Na(a,b,void 0);0<=c&&Array.prototype.splice.call(a,c,1)}
function Ra(a){return Array.prototype.concat.apply(Array.prototype,arguments)}
function Sa(a){var b=a.length;if(0<b){for(var c=Array(b),d=0;d<b;d++)c[d]=a[d];return c}return[]}
function Ta(a,b){for(var c=1;c<arguments.length;c++){var d=arguments[c];if(da(d)){var e=a.length||0,f=d.length||0;a.length=e+f;for(var h=0;h<f;h++)a[e+h]=d[h]}else a.push(d)}}
function Ua(a,b,c,d){return Array.prototype.splice.apply(a,Va(arguments,1))}
function Va(a,b,c){return 2>=arguments.length?Array.prototype.slice.call(a,b):Array.prototype.slice.call(a,b,c)}
function Wa(a,b){return a>b?1:a<b?-1:0}
;function Xa(a,b,c){for(var d in a)b.call(c,a[d],d,a)}
function Ya(a,b,c){var d={},e;for(e in a)b.call(c,a[e],e,a)&&(d[e]=a[e]);return d}
function Za(a){var b=0,c;for(c in a)b++;return b}
function $a(a,b){return ab(a,b)}
function bb(a){var b=[],c=0,d;for(d in a)b[c++]=a[d];return b}
function cb(a){var b=[],c=0,d;for(d in a)b[c++]=d;return b}
function ab(a,b){for(var c in a)if(a[c]==b)return!0;return!1}
function db(a){var b=eb,c;for(c in b)if(a.call(void 0,b[c],c,b))return c}
function fb(a){for(var b in a)return!1;return!0}
function gb(a,b){if(null!==a&&b in a)throw Error('The object already contains the key "'+b+'"');a[b]=!0}
function hb(a){var b={},c;for(c in a)b[c]=a[c];return b}
function ib(a){var b=ca(a);if("object"==b||"array"==b){if(fa(a.clone))return a.clone();var b="array"==b?[]:{},c;for(c in a)b[c]=ib(a[c]);return b}return a}
var jb="constructor hasOwnProperty isPrototypeOf propertyIsEnumerable toLocaleString toString valueOf".split(" ");function kb(a,b){for(var c,d,e=1;e<arguments.length;e++){d=arguments[e];for(c in d)a[c]=d[c];for(var f=0;f<jb.length;f++)c=jb[f],Object.prototype.hasOwnProperty.call(d,c)&&(a[c]=d[c])}}
;var lb;a:{var mb=l.navigator;if(mb){var nb=mb.userAgent;if(nb){lb=nb;break a}}lb=""}function C(a){return-1!=lb.indexOf(a)}
;function ob(){return(C("Chrome")||C("CriOS"))&&!C("Edge")}
;function pb(){this.b="";this.f=qb}
pb.prototype.Ob=!0;pb.prototype.Jb=function(){return this.b};
function rb(a){if(a instanceof pb&&a.constructor===pb&&a.f===qb)return a.b;ca(a);return"type_error:SafeUrl"}
var sb=/^(?:(?:https?|mailto|ftp):|[^&:/?#]*(?:[/?#]|$))/i;function tb(a){if(a instanceof pb)return a;a=a.Ob?a.Jb():String(a);sb.test(a)||(a="about:invalid#zClosurez");return ub(a)}
var qb={};function ub(a){var b=new pb;b.b=a;return b}
ub("about:blank");function vb(){this.b="";this.f=wb}
vb.prototype.Ob=!0;vb.prototype.Jb=function(){return this.b};
var wb={};function xb(){this.b="";this.f=yb}
xb.prototype.Ob=!0;xb.prototype.Jb=function(){return this.b};
function zb(a){if(a instanceof xb&&a.constructor===xb&&a.f===yb)return a.b;ca(a);return"type_error:SafeHtml"}
var yb={};function Ab(a){var b=new xb;b.b=a;return b}
Ab("<!DOCTYPE html>");Ab("");Ab("<br>");function Bb(a,b){var c;c=b instanceof pb?b:tb(b);a.href=rb(c)}
function Cb(a,b){a.rel="stylesheet";var c;b instanceof vb&&b.constructor===vb&&b.f===wb?c=b.b:(ca(b),c="type_error:TrustedResourceUrl");a.href=c}
;function Db(a,b,c){a&&(a.dataset?a.dataset[Eb(b)]=c:a.setAttribute("data-"+b,c))}
function D(a,b){return a?a.dataset?a.dataset[Eb(b)]:a.getAttribute("data-"+b):null}
function Fb(a,b){a&&(a.dataset?delete a.dataset[Eb(b)]:a.removeAttribute("data-"+b))}
var Gb={};function Eb(a){return Gb[a]||(Gb[a]=String(a).replace(/\-([a-z])/g,function(a,c){return c.toUpperCase()}))}
;function Hb(a){l.setTimeout(function(){throw a;},0)}
var Ib;
function Jb(){var a=l.MessageChannel;"undefined"===typeof a&&"undefined"!==typeof window&&window.postMessage&&window.addEventListener&&!C("Presto")&&(a=function(){var a=document.createElement("IFRAME");a.style.display="none";a.src="";document.documentElement.appendChild(a);var b=a.contentWindow,a=b.document;a.open();a.write("");a.close();var c="callImmediate"+Math.random(),d="file:"==b.location.protocol?"*":b.location.protocol+"//"+b.location.host,a=x(function(a){if(("*"==d||a.origin==d)&&a.data==
c)this.port1.onmessage()},this);
b.addEventListener("message",a,!1);this.port1={};this.port2={postMessage:function(){b.postMessage(c,d)}}});
if("undefined"!==typeof a&&!C("Trident")&&!C("MSIE")){var b=new a,c={},d=c;b.port1.onmessage=function(){if(p(c.next)){c=c.next;var a=c.dc;c.dc=null;a()}};
return function(a){d.next={dc:a};d=d.next;b.port2.postMessage(0)}}return"undefined"!==typeof document&&"onreadystatechange"in document.createElement("SCRIPT")?function(a){var b=document.createElement("SCRIPT");
b.onreadystatechange=function(){b.onreadystatechange=null;b.parentNode.removeChild(b);b=null;a();a=null};
document.documentElement.appendChild(b)}:function(a){l.setTimeout(a,0)}}
;function Kb(a,b,c){this.i=c;this.g=a;this.j=b;this.f=0;this.b=null}
Kb.prototype.get=function(){var a;0<this.f?(this.f--,a=this.b,this.b=a.next,a.next=null):a=this.g();return a};
function Lb(a,b){a.j(b);a.f<a.i&&(a.f++,b.next=a.b,a.b=b)}
;function Mb(){this.f=this.b=null}
var Ob=new Kb(function(){return new Nb},function(a){a.reset()},100);
Mb.prototype.remove=function(){var a=null;this.b&&(a=this.b,this.b=this.b.next,this.b||(this.f=null),a.next=null);return a};
function Nb(){this.next=this.scope=this.b=null}
Nb.prototype.set=function(a,b){this.b=a;this.scope=b;this.next=null};
Nb.prototype.reset=function(){this.next=this.scope=this.b=null};function Pb(a,b){Qb||Rb();Sb||(Qb(),Sb=!0);var c=Tb,d=Ob.get();d.set(a,b);c.f?c.f.next=d:c.b=d;c.f=d}
var Qb;function Rb(){if(l.Promise&&l.Promise.resolve){var a=l.Promise.resolve(void 0);Qb=function(){a.then(Ub)}}else Qb=function(){var a=Ub;
!fa(l.setImmediate)||l.Window&&l.Window.prototype&&!C("Edge")&&l.Window.prototype.setImmediate==l.setImmediate?(Ib||(Ib=Jb()),Ib(a)):l.setImmediate(a)}}
var Sb=!1,Tb=new Mb;function Ub(){for(var a;a=Tb.remove();){try{a.b.call(a.scope)}catch(b){Hb(b)}Lb(Ob,a)}Sb=!1}
;function E(){this.Ia=this.Ia;this.R=this.R}
E.prototype.Ia=!1;E.prototype.C=function(){return this.Ia};
E.prototype.dispose=function(){this.Ia||(this.Ia=!0,this.w())};
function Vb(a,b){a.Ia?p(void 0)?b.call(void 0):b():(a.R||(a.R=[]),a.R.push(p(void 0)?x(b,void 0):b))}
E.prototype.w=function(){if(this.R)for(;this.R.length;)this.R.shift()()};
function F(a){a&&"function"==typeof a.dispose&&a.dispose()}
function Wb(a){for(var b=0,c=arguments.length;b<c;++b){var d=arguments[b];da(d)?Wb.apply(null,d):F(d)}}
;function G(a){E.call(this);this.i=1;this.f=[];this.g=0;this.b=[];this.fa={};this.j=!!a}
z(G,E);g=G.prototype;g.subscribe=function(a,b,c){var d=this.fa[a];d||(d=this.fa[a]=[]);var e=this.i;this.b[e]=a;this.b[e+1]=b;this.b[e+2]=c;this.i=e+3;d.push(e);return e};
g.unsubscribe=function(a,b,c){if(a=this.fa[a]){var d=this.b;if(a=Ma(a,function(a){return d[a+1]==b&&d[a+2]==c}))return this.ka(a)}return!1};
g.ka=function(a){var b=this.b[a];if(b){var c=this.fa[b];0!=this.g?(this.f.push(a),this.b[a+1]=t):(c&&Pa(c,a),delete this.b[a],delete this.b[a+1],delete this.b[a+2])}return!!b};
g.u=function(a,b){var c=this.fa[a];if(c){for(var d=Array(arguments.length-1),e=1,f=arguments.length;e<f;e++)d[e-1]=arguments[e];if(this.j)for(e=0;e<c.length;e++){var h=c[e];Xb(this.b[h+1],this.b[h+2],d)}else{this.g++;try{for(e=0,f=c.length;e<f;e++)h=c[e],this.b[h+1].apply(this.b[h+2],d)}finally{if(this.g--,0<this.f.length&&0==this.g)for(;c=this.f.pop();)this.ka(c)}}return 0!=e}return!1};
function Xb(a,b,c){Pb(function(){a.apply(b,c)})}
g.clear=function(a){if(a){var b=this.fa[a];b&&(A(b,this.ka,this),delete this.fa[a])}else this.b.length=0,this.fa={}};
g.S=function(a){if(a){var b=this.fa[a];return b?b.length:0}a=0;for(b in this.fa)a+=this.S(b);return a};
g.w=function(){G.B.w.call(this);this.clear();this.f.length=0};var Yb=window.yt&&window.yt.config_||window.ytcfg&&window.ytcfg.data_||{};q("yt.config_",Yb,void 0);q("yt.tokens_",window.yt&&window.yt.tokens_||{},void 0);var Zb=window.yt&&window.yt.msgs_||r("window.ytcfg.msgs")||{};q("yt.msgs_",Zb,void 0);function $b(a){ac(Yb,arguments)}
function H(a,b){return a in Yb?Yb[a]:b}
function I(a,b){fa(a)&&(a=bc(a));return window.setTimeout(a,b)}
function J(a){window.clearTimeout(a)}
function bc(a){return a&&window.yterr?function(){try{return a.apply(this,arguments)}catch(b){throw cc(b),b;}}:a}
function cc(a,b){var c=r("yt.logging.errors.log");c?c(a,b,void 0,void 0,void 0):(c=H("ERRORS",[]),c.push([a,b,void 0,void 0,void 0]),$b("ERRORS",c))}
function ac(a,b){if(1<b.length){var c=b[0];a[c]=b[1]}else{var d=b[0];for(c in d)a[c]=d[c]}}
var dc=window.performance&&window.performance.timing&&window.performance.now?function(){return window.performance.timing.navigationStart+window.performance.now()}:function(){return(new Date).getTime()},ec="Microsoft Internet Explorer"==navigator.appName;var fc=r("yt.pubsub.instance_")||new G;G.prototype.subscribe=G.prototype.subscribe;G.prototype.unsubscribeByKey=G.prototype.ka;G.prototype.publish=G.prototype.u;G.prototype.clear=G.prototype.clear;q("yt.pubsub.instance_",fc,void 0);var gc=r("yt.pubsub.subscribedKeys_")||{};q("yt.pubsub.subscribedKeys_",gc,void 0);var hc=r("yt.pubsub.topicToKeys_")||{};q("yt.pubsub.topicToKeys_",hc,void 0);var ic=r("yt.pubsub.isSynchronous_")||{};q("yt.pubsub.isSynchronous_",ic,void 0);
var jc=r("yt.pubsub.skipSubId_")||null;q("yt.pubsub.skipSubId_",jc,void 0);function kc(a,b,c){var d=lc();if(d){var e=d.subscribe(a,function(){if(!jc||jc!=e){var d=arguments,h;h=function(){gc[e]&&b.apply(c||window,d)};
try{ic[a]?h():I(h,0)}catch(k){cc(k)}}},c);
gc[e]=!0;hc[a]||(hc[a]=[]);hc[a].push(e);return e}return 0}
function mc(a){var b=lc();b&&("number"==typeof a?a=[a]:"string"==typeof a&&(a=[parseInt(a,10)]),A(a,function(a){b.unsubscribeByKey(a);delete gc[a]}))}
function K(a,b){var c=lc();return c?c.publish.apply(c,arguments):!1}
function nc(a,b){ic[a]=!0;var c=lc();c&&c.publish.apply(c,arguments);ic[a]=!1}
function oc(a){hc[a]&&(a=hc[a],A(a,function(a){gc[a]&&delete gc[a]}),a.length=0)}
function pc(a){var b=lc();if(b)if(b.clear(a),a)oc(a);else for(var c in hc)oc(c)}
function lc(){return r("yt.pubsub.instance_")}
;function qc(a,b){if(window.spf){var c="";if(a){var d=a.indexOf("jsbin/"),e=a.lastIndexOf(".js"),f=d+6;-1<d&&-1<e&&e>f&&(c=a.substring(f,e),c=c.replace(rc,""),c=c.replace(sc,""),c=c.replace("debug-",""),c=c.replace("tracing-",""))}spf.script.load(a,c,b)}else tc(a,b)}
function tc(a,b){var c=uc(a),d=document.getElementById(c),e=d&&D(d,"loaded"),f=d&&!e;if(e){b&&b();return}if(b){var e=kc(c,b),h=""+ia(b);vc[h]=e}if(f)return;d=wc(a,c,function(){D(d,"loaded")||(Db(d,"loaded","true"),K(c),I(na(pc,c),0))})}
function wc(a,b,c){var d=document.createElement("script");d.id=b;d.onload=function(){c&&setTimeout(c,0)};
d.onreadystatechange=function(){switch(d.readyState){case "loaded":case "complete":d.onload()}};
d.src=a;a=document.getElementsByTagName("head")[0]||document.body;a.insertBefore(d,a.firstChild);return d}
function xc(a,b){if(a&&b){var c=""+ia(b);(c=vc[c])&&mc(c)}}
function uc(a){var b=document.createElement("a");Bb(b,a);a=b.href.replace(/^[a-zA-Z]+:\/\//,"//");return"js-"+Ga(a)}
var rc=/\.vflset|-vfl[a-zA-Z0-9_+=-]+/,sc=/-[a-zA-Z]{2,3}_[a-zA-Z]{2,3}(?=(\/|$))/,vc={};var yc=null;function zc(){var a=H("BG_I",null),b=H("BG_IU",null),c=H("BG_P",void 0);b?qc(b,function(){yc=new botguard.bg(c)}):a&&(eval(a),yc=new botguard.bg(c))}
function Ac(){return null!=yc}
function Bc(){return yc?yc.invoke():null}
;var Cc="StopIteration"in l?l.StopIteration:{message:"StopIteration",stack:""};function Dc(){}
Dc.prototype.next=function(){throw Cc;};
Dc.prototype.ma=function(){return this};
function Ec(a){if(a instanceof Dc)return a;if("function"==typeof a.ma)return a.ma(!1);if(da(a)){var b=0,c=new Dc;c.next=function(){for(;;){if(b>=a.length)throw Cc;if(b in a)return a[b++];b++}};
return c}throw Error("Not implemented");}
function Fc(a,b,c){if(da(a))try{A(a,b,c)}catch(d){if(d!==Cc)throw d;}else{a=Ec(a);try{for(;;)b.call(c,a.next(),void 0,a)}catch(d){if(d!==Cc)throw d;}}}
function Gc(a){if(da(a))return Sa(a);a=Ec(a);var b=[];Fc(a,function(a){b.push(a)});
return b}
;function Hc(a,b){this.f={};this.b=[];this.xa=this.g=0;var c=arguments.length;if(1<c){if(c%2)throw Error("Uneven number of arguments");for(var d=0;d<c;d+=2)this.set(arguments[d],arguments[d+1])}else if(a){a instanceof Hc?(c=a.na(),d=a.T()):(c=cb(a),d=bb(a));for(var e=0;e<c.length;e++)this.set(c[e],d[e])}}
g=Hc.prototype;g.S=function(){return this.g};
g.T=function(){Ic(this);for(var a=[],b=0;b<this.b.length;b++)a.push(this.f[this.b[b]]);return a};
g.na=function(){Ic(this);return this.b.concat()};
g.Ya=function(a){for(var b=0;b<this.b.length;b++){var c=this.b[b];if(Jc(this.f,c)&&this.f[c]==a)return!0}return!1};
g.equals=function(a,b){if(this===a)return!0;if(this.g!=a.S())return!1;var c=b||Kc;Ic(this);for(var d,e=0;d=this.b[e];e++)if(!c(this.get(d),a.get(d)))return!1;return!0};
function Kc(a,b){return a===b}
g.isEmpty=function(){return 0==this.g};
g.clear=function(){this.f={};this.xa=this.g=this.b.length=0};
g.remove=function(a){return Jc(this.f,a)?(delete this.f[a],this.g--,this.xa++,this.b.length>2*this.g&&Ic(this),!0):!1};
function Ic(a){if(a.g!=a.b.length){for(var b=0,c=0;b<a.b.length;){var d=a.b[b];Jc(a.f,d)&&(a.b[c++]=d);b++}a.b.length=c}if(a.g!=a.b.length){for(var e={},c=b=0;b<a.b.length;)d=a.b[b],Jc(e,d)||(a.b[c++]=d,e[d]=1),b++;a.b.length=c}}
g.get=function(a,b){return Jc(this.f,a)?this.f[a]:b};
g.set=function(a,b){Jc(this.f,a)||(this.g++,this.b.push(a),this.xa++);this.f[a]=b};
g.forEach=function(a,b){for(var c=this.na(),d=0;d<c.length;d++){var e=c[d],f=this.get(e);a.call(b,f,e,this)}};
g.clone=function(){return new Hc(this)};
g.ma=function(a){Ic(this);var b=0,c=this.xa,d=this,e=new Dc;e.next=function(){if(c!=d.xa)throw Error("The map has changed since the iterator was created");if(b>=d.b.length)throw Cc;var e=d.b[b++];return a?e:d.f[e]};
return e};
function Jc(a,b){return Object.prototype.hasOwnProperty.call(a,b)}
;function Lc(a){return a.S&&"function"==typeof a.S?a.S():da(a)||w(a)?a.length:Za(a)}
function Mc(a){if(a.T&&"function"==typeof a.T)return a.T();if(w(a))return a.split("");if(da(a)){for(var b=[],c=a.length,d=0;d<c;d++)b.push(a[d]);return b}return bb(a)}
function Nc(a){if(a.na&&"function"==typeof a.na)return a.na();if(!a.T||"function"!=typeof a.T){if(da(a)||w(a)){var b=[];a=a.length;for(var c=0;c<a;c++)b.push(c);return b}return cb(a)}}
function Oc(a,b,c){if(a.forEach&&"function"==typeof a.forEach)a.forEach(b,c);else if(da(a)||w(a))A(a,b,c);else for(var d=Nc(a),e=Mc(a),f=e.length,h=0;h<f;h++)b.call(c,e[h],d&&d[h],a)}
function Pc(a,b){if("function"==typeof a.every)return a.every(b,void 0);if(da(a)||w(a))return La(a,b,void 0);for(var c=Nc(a),d=Mc(a),e=d.length,f=0;f<e;f++)if(!b.call(void 0,d[f],c&&c[f],a))return!1;return!0}
;function Rc(a){this.b=new Hc;a&&Sc(this,a)}
function Tc(a){var b=typeof a;return"object"==b&&a||"function"==b?"o"+ia(a):b.substr(0,1)+a}
g=Rc.prototype;g.S=function(){return this.b.S()};
function Sc(a,b){for(var c=Mc(b),d=c.length,e=0;e<d;e++){var f=c[e];a.b.set(Tc(f),f)}}
g.remove=function(a){return this.b.remove(Tc(a))};
g.clear=function(){this.b.clear()};
g.isEmpty=function(){return this.b.isEmpty()};
g.contains=function(a){a=Tc(a);return Jc(this.b.f,a)};
g.T=function(){return this.b.T()};
g.clone=function(){return new Rc(this)};
g.equals=function(a){return this.S()==Lc(a)&&Uc(this,a)};
function Uc(a,b){var c=Lc(b);if(a.S()>c)return!1;!(b instanceof Rc)&&5<c&&(b=new Rc(b));return Pc(a,function(a){var c=b;return c.contains&&"function"==typeof c.contains?c.contains(a):c.Ya&&"function"==typeof c.Ya?c.Ya(a):da(c)||w(c)?B(c,a):ab(c,a)})}
g.ma=function(){return this.b.ma(!1)};function Vc(){return C("iPhone")&&!C("iPod")&&!C("iPad")}
;function Wc(a){Wc[" "](a);return a}
Wc[" "]=t;function Xc(a,b){var c=Yc;return Object.prototype.hasOwnProperty.call(c,a)?c[a]:c[a]=b(a)}
;var Zc=C("Opera"),L=C("Trident")||C("MSIE"),$c=C("Edge"),ad=C("Gecko")&&!(-1!=lb.toLowerCase().indexOf("webkit")&&!C("Edge"))&&!(C("Trident")||C("MSIE"))&&!C("Edge"),bd=-1!=lb.toLowerCase().indexOf("webkit")&&!C("Edge"),cd=C("Windows");function dd(){var a=l.document;return a?a.documentMode:void 0}
var ed;a:{var fd="",gd=function(){var a=lb;if(ad)return/rv\:([^\);]+)(\)|;)/.exec(a);if($c)return/Edge\/([\d\.]+)/.exec(a);if(L)return/\b(?:MSIE|rv)[: ]([^\);]+)(\)|;)/.exec(a);if(bd)return/WebKit\/(\S+)/.exec(a);if(Zc)return/(?:Version)[ \/]?(\S+)/.exec(a)}();
gd&&(fd=gd?gd[1]:"");if(L){var hd=dd();if(null!=hd&&hd>parseFloat(fd)){ed=String(hd);break a}}ed=fd}var id=ed,Yc={};function jd(a){return Xc(a,function(){return 0<=Ea(id,a)})}
function kd(a){return Number(ld)>=a}
var md=l.document,ld=md&&L?dd()||("CSS1Compat"==md.compatMode?parseInt(id,10):5):void 0;function nd(a){return/^\s*$/.test(a)?!1:/^[\],:{}\s\u2028\u2029]*$/.test(a.replace(/\\["\\\/bfnrtu]/g,"@").replace(/(?:"[^"\\\n\r\u2028\u2029\x00-\x08\x0a-\x1f]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)[\s\u2028\u2029]*(?=:|,|]|}|$)/g,"]").replace(/(?:^|:|,)(?:[\s\u2028\u2029]*\[)+/g,""))}
function od(a){a=String(a);if(nd(a))try{return eval("("+a+")")}catch(b){}throw Error("Invalid JSON string: "+a);}
function pd(a){return eval("("+a+")")}
function M(a){return qd(new rd(void 0),a)}
function rd(a){this.b=a}
function qd(a,b){var c=[];sd(a,b,c);return c.join("")}
function sd(a,b,c){if(null==b)c.push("null");else{if("object"==typeof b){if(v(b)){var d=b;b=d.length;c.push("[");for(var e="",f=0;f<b;f++)c.push(e),e=d[f],sd(a,a.b?a.b.call(d,String(f),e):e,c),e=",";c.push("]");return}if(b instanceof String||b instanceof Number||b instanceof Boolean)b=b.valueOf();else{c.push("{");f="";for(d in b)Object.prototype.hasOwnProperty.call(b,d)&&(e=b[d],"function"!=typeof e&&(c.push(f),td(d,c),c.push(":"),sd(a,a.b?a.b.call(b,d,e):e,c),f=","));c.push("}");return}}switch(typeof b){case "string":td(b,
c);break;case "number":c.push(isFinite(b)&&!isNaN(b)?String(b):"null");break;case "boolean":c.push(String(b));break;case "function":c.push("null");break;default:throw Error("Unknown type: "+typeof b);}}}
var ud={'"':'\\"',"\\":"\\\\","/":"\\/","\b":"\\b","\f":"\\f","\n":"\\n","\r":"\\r","\t":"\\t","\x0B":"\\u000b"},vd=/\uffff/.test("\uffff")?/[\\\"\x00-\x1f\x7f-\uffff]/g:/[\\\"\x00-\x1f\x7f-\xff]/g;function td(a,b){b.push('"',a.replace(vd,function(a){var b=ud[a];b||(b="\\u"+(a.charCodeAt(0)|65536).toString(16).substr(1),ud[a]=b);return b}),'"')}
;function wd(a){return H("EXPERIMENT_FLAGS",{})[a]}
;var xd=/^(?:([^:/?#.]+):)?(?:\/\/(?:([^/?#]*)@)?([^/#?]*?)(?::([0-9]+))?(?=[/#?]|$))?([^?#]+)?(?:\?([^#]*))?(?:#([\s\S]*))?$/;function yd(a){return a?decodeURI(a):a}
function zd(a,b){return b.match(xd)[a]||null}
function Ad(a,b){if(a)for(var c=a.split("&"),d=0;d<c.length;d++){var e=c[d].indexOf("="),f,h=null;0<=e?(f=c[d].substring(0,e),h=c[d].substring(e+1)):f=c[d];b(f,h?ra(h):"")}}
function Bd(a){if(a[1]){var b=a[0],c=b.indexOf("#");0<=c&&(a.push(b.substr(c)),a[0]=b=b.substr(0,c));c=b.indexOf("?");0>c?a[1]="?":c==b.length-1&&(a[1]=void 0)}return a.join("")}
function Cd(a,b,c){if(v(b))for(var d=0;d<b.length;d++)Cd(a,String(b[d]),c);else null!=b&&c.push("&",a,""===b?"":"=",encodeURIComponent(String(b)))}
function Dd(a,b,c){for(c=c||0;c<b.length;c+=2)Cd(b[c],b[c+1],a);return a}
function Ed(a,b){for(var c in b)Cd(c,b[c],a);return a}
function Fd(a){a=Ed([],a);a[0]="";return a.join("")}
function Gd(a,b){return Bd(2==arguments.length?Dd([a],arguments[1],0):Dd([a],arguments,1))}
function Hd(a,b){return Bd(Ed([a],b))}
;function Id(a){"?"==a.charAt(0)&&(a=a.substr(1));a=a.split("&");for(var b={},c=0,d=a.length;c<d;c++){var e=a[c].split("=");if(1==e.length&&e[0]||2==e.length){var f=ra(e[0]||""),e=ra(e[1]||"");f in b?v(b[f])?Ta(b[f],e):b[f]=[b[f],e]:b[f]=e}}return b}
function Jd(a,b){var c=a.split("#",2);a=c[0];var c=1<c.length?"#"+c[1]:"",d=a.split("?",2);a=d[0];var d=Id(d[1]||""),e;for(e in b)d[e]=b[e];return Hd(a,d)+c}
;var Kd=null;"undefined"!=typeof XMLHttpRequest?Kd=function(){return new XMLHttpRequest}:"undefined"!=typeof ActiveXObject&&(Kd=function(){return new ActiveXObject("Microsoft.XMLHTTP")});
function Ld(a){switch(a&&"status"in a?a.status:-1){case 200:case 201:case 202:case 203:case 204:case 205:case 206:case 304:return!0;default:return!1}}
;function Md(a,b,c,d,e,f,h){function k(){4==(m&&"readyState"in m?m.readyState:0)&&b&&bc(b)(m)}
var m=Kd&&Kd();if(!("open"in m))return null;"onloadend"in m?m.addEventListener("loadend",k,!1):m.onreadystatechange=k;c=(c||"GET").toUpperCase();d=d||"";m.open(c,a,!0);f&&(m.responseType=f);h&&(m.withCredentials=!0);f="POST"==c;if(e=Nd(a,e))for(var n in e)m.setRequestHeader(n,e[n]),"content-type"==n.toLowerCase()&&(f=!1);f&&m.setRequestHeader("Content-Type","application/x-www-form-urlencoded");m.send(d);return m}
function Nd(a,b){b=b||{};var c;c||(c=window.location.href);var d=zd(1,a),e=yd(zd(3,a));d&&e?(d=c,c=a.match(xd),d=d.match(xd),c=c[3]==d[3]&&c[1]==d[1]&&c[4]==d[4]):c=e?yd(zd(3,c))==e&&(Number(zd(4,c))||null)==(Number(zd(4,a))||null):!0;for(var f in Od){if((e=d=H(Od[f]))&&!(e=c)){var e=f,h=H("CORS_HEADER_WHITELIST")||{},k=yd(zd(3,a));e=k?(h=h[k])?B(h,e):!1:!0}e&&(b[f]=d)}return b}
function Pd(a,b){var c=H("XSRF_FIELD_NAME",void 0),d;b.headers&&(d=b.headers["Content-Type"]);return!b.Te&&(!yd(zd(3,a))||b.withCredentials||yd(zd(3,a))==document.location.hostname)&&"POST"==b.method&&(!d||"application/x-www-form-urlencoded"==d)&&!(b.V&&b.V[c])}
function Qd(a,b){var c=b.format||"JSON";b.Ue&&(a=document.location.protocol+"//"+document.location.hostname+(document.location.port?":"+document.location.port:"")+a);var d=H("XSRF_FIELD_NAME",void 0),e=H("XSRF_TOKEN",void 0),f=b.Ub;f&&(f[d]&&delete f[d],a=Jd(a,f||{}));var h=b.postBody||"",f=b.V;Pd(a,b)&&(f||(f={}),f[d]=e);f&&w(h)&&(d=Id(h),kb(d,f),h=b.ce&&"JSON"==b.ce?JSON.stringify(d):Fd(d));var k=!1,m,n=Md(a,function(a){if(!k){k=!0;m&&J(m);var d=Ld(a),e=null;if(d||400<=a.status&&500>a.status)e=
Rd(c,a,b.Se);if(d)a:if(wd("ajax_204_success")&&204==a.status)d=!0;else{switch(c){case "XML":d=0==parseInt(e&&e.return_code,10);break a;case "RAW":d=!0;break a}d=!!e}var e=e||{},f=b.context||l;d?b.aa&&b.aa.call(f,a,e):b.onError&&b.onError.call(f,a,e);b.Rb&&b.Rb.call(f,a,e)}},b.method,h,b.headers,b.responseType,b.withCredentials);
b.Ga&&0<b.timeout&&(m=I(function(){k||(k=!0,n.abort(),J(m),b.Ga.call(b.context||l,n))},b.timeout));
return n}
function Rd(a,b,c){var d=null;switch(a){case "JSON":a=b.responseText;b=b.getResponseHeader("Content-Type")||"";a&&0<=b.indexOf("json")&&(d=pd(a));break;case "XML":if(b=(b=b.responseXML)?Sd(b):null)d={},A(b.getElementsByTagName("*"),function(a){d[a.tagName]=Td(a)})}c&&Ud(d);
return d}
function Ud(a){if(ha(a))for(var b in a){var c;(c="html_content"==b)||(c=b.length-5,c=0<=c&&b.indexOf("_html",c)==c);if(c){c=b;var d;d=Ab(a[b]);a[c]=d}else Ud(a[b])}}
function Sd(a){return a?(a=("responseXML"in a?a.responseXML:a).getElementsByTagName("root"))&&0<a.length?a[0]:null:null}
function Td(a){var b="";A(a.childNodes,function(a){b+=a.nodeValue});
return b}
var Od={"X-YouTube-Client-Name":"INNERTUBE_CONTEXT_CLIENT_NAME","X-YouTube-Client-Version":"INNERTUBE_CONTEXT_CLIENT_VERSION","X-Youtube-Identity-Token":"ID_TOKEN","X-YouTube-Page-CL":"PAGE_CL","X-YouTube-Page-Label":"PAGE_BUILD_LABEL","X-YouTube-Variants-Checksum":"VARIANTS_CHECKSUM"};var Vd={},Wd=0;function Xd(a,b){isNaN(b)&&(b=void 0);var c=r("yt.scheduler.instance.addJob");return c?c(a,1,b):void 0===b?(a(),NaN):I(a,b||0)}
;var Yd=[],Zd=!1;function $d(){function a(){Zd=!0;"google_ad_status"in window?$b("DCLKSTAT",1):$b("DCLKSTAT",2)}
qc("//static.doubleclick.net/instream/ad_status.js",a);Yd.push(Xd(function(){Zd||"google_ad_status"in window||(xc("//static.doubleclick.net/instream/ad_status.js",a),$b("DCLKSTAT",3))},5E3))}
function ae(){return parseInt(H("DCLKSTAT",0),10)}
;function be(a){if(a.classList)return a.classList;a=a.className;return w(a)&&a.match(/\S+/g)||[]}
function ce(a,b){return a.classList?a.classList.contains(b):B(be(a),b)}
function de(a,b){a.classList?a.classList.add(b):ce(a,b)||(a.className+=0<a.className.length?" "+b:b)}
function ee(a,b){a.classList?a.classList.remove(b):ce(a,b)&&(a.className=Ia(be(a),function(a){return a!=b}).join(" "))}
function fe(a,b,c){c?de(a,b):ee(a,b)}
;function ge(a,b){this.H=p(a)?a:0;this.I=p(b)?b:0}
ge.prototype.clone=function(){return new ge(this.H,this.I)};
ge.prototype.ceil=function(){this.H=Math.ceil(this.H);this.I=Math.ceil(this.I);return this};
ge.prototype.floor=function(){this.H=Math.floor(this.H);this.I=Math.floor(this.I);return this};
ge.prototype.round=function(){this.H=Math.round(this.H);this.I=Math.round(this.I);return this};function he(a,b){this.width=a;this.height=b}
g=he.prototype;g.clone=function(){return new he(this.width,this.height)};
g.bd=function(){return this.width*this.height};
g.aspectRatio=function(){return this.width/this.height};
g.isEmpty=function(){return!this.bd()};
g.ceil=function(){this.width=Math.ceil(this.width);this.height=Math.ceil(this.height);return this};
g.floor=function(){this.width=Math.floor(this.width);this.height=Math.floor(this.height);return this};
g.round=function(){this.width=Math.round(this.width);this.height=Math.round(this.height);return this};!ad&&!L||L&&kd(9)||ad&&jd("1.9.1");var ie=L&&!jd("9");function je(a){return a?new ke(le(a)):pa||(pa=new ke)}
function me(a){var b=document;return w(a)?b.getElementById(a):a}
function ne(a){var b=document;return b.querySelectorAll&&b.querySelector?b.querySelectorAll("."+a):oe(a)}
function oe(a){var b,c,d,e;b=document;if(b.querySelectorAll&&b.querySelector&&a)return b.querySelectorAll(""+(a?"."+a:""));if(a&&b.getElementsByClassName){var f=b.getElementsByClassName(a);return f}f=b.getElementsByTagName("*");if(a){e={};for(c=d=0;b=f[c];c++){var h=b.className;"function"==typeof h.split&&B(h.split(/\s+/),a)&&(e[d++]=b)}e.length=d;return e}return f}
function pe(a){var b=a.scrollingElement?a.scrollingElement:!bd&&qe(a)?a.documentElement:a.body||a.documentElement;a=a.parentWindow||a.defaultView;return L&&jd("10")&&a.pageYOffset!=b.scrollTop?new ge(b.scrollLeft,b.scrollTop):new ge(a.pageXOffset||b.scrollLeft,a.pageYOffset||b.scrollTop)}
function qe(a){return"CSS1Compat"==a.compatMode}
function re(a){for(var b;b=a.firstChild;)a.removeChild(b)}
function se(a){if(!a)return null;if(a.firstChild)return a.firstChild;for(;a&&!a.nextSibling;)a=a.parentNode;return a?a.nextSibling:null}
function te(a){if(!a)return null;if(!a.previousSibling)return a.parentNode;for(a=a.previousSibling;a&&a.lastChild;)a=a.lastChild;return a}
function le(a){return 9==a.nodeType?a:a.ownerDocument||a.document}
function ue(a,b){if("textContent"in a)a.textContent=b;else if(3==a.nodeType)a.data=b;else if(a.firstChild&&3==a.firstChild.nodeType){for(;a.lastChild!=a.firstChild;)a.removeChild(a.lastChild);a.firstChild.data=b}else re(a),a.appendChild(le(a).createTextNode(String(b)))}
var ve={SCRIPT:1,STYLE:1,HEAD:1,IFRAME:1,OBJECT:1},we={IMG:" ",BR:"\n"};function xe(a){if(ie&&null!==a&&"innerText"in a)a=a.innerText.replace(/(\r\n|\r|\n)/g,"\n");else{var b=[];ye(a,b,!0);a=b.join("")}a=a.replace(/ \xAD /g," ").replace(/\xAD/g,"");a=a.replace(/\u200B/g,"");ie||(a=a.replace(/ +/g," "));" "!=a&&(a=a.replace(/^\s*/,""));return a}
function ye(a,b,c){if(!(a.nodeName in ve))if(3==a.nodeType)c?b.push(String(a.nodeValue).replace(/(\r\n|\r|\n)/g,"")):b.push(a.nodeValue);else if(a.nodeName in we)b.push(we[a.nodeName]);else for(a=a.firstChild;a;)ye(a,b,c),a=a.nextSibling}
function ze(a){var b=Ae.Sc;return b?Be(a,function(a){return!b||w(a.className)&&B(a.className.split(/\s+/),b)},!0,void 0):null}
function Be(a,b,c,d){c||(a=a.parentNode);for(c=0;a&&(null==d||c<=d);){if(b(a))return a;a=a.parentNode;c++}return null}
function ke(a){this.b=a||l.document||document}
g=ke.prototype;g.getElementsByTagName=function(a,b){return(b||this.b).getElementsByTagName(a)};
g.createElement=function(a){return this.b.createElement(String(a))};
g.appendChild=function(a,b){a.appendChild(b)};
g.isElement=function(a){return ha(a)&&1==a.nodeType};
g.contains=function(a,b){if(!a||!b)return!1;if(a.contains&&1==b.nodeType)return a==b||a.contains(b);if("undefined"!=typeof a.compareDocumentPosition)return a==b||!!(a.compareDocumentPosition(b)&16);for(;b&&a!=b;)b=b.parentNode;return b==a};function Ce(a){De();var b=new vb;b.b=a;return b}
var De=t;var Ee=r("yt.dom.getNextId_");if(!Ee){Ee=function(){return++Fe};
q("yt.dom.getNextId_",Ee,void 0);var Fe=0}function Ge(){var a=document,b;Ka(["fullscreenElement","webkitFullscreenElement","mozFullScreenElement","msFullscreenElement"],function(c){b=a[c];return!!b});
return b}
;function He(a){this.type="";this.state=this.source=this.data=this.currentTarget=this.relatedTarget=this.target=null;this.charCode=this.keyCode=0;this.shiftKey=this.ctrlKey=this.altKey=!1;this.clientY=this.clientX=0;this.changedTouches=null;if(a=a||window.event){this.event=a;for(var b in a)b in Ie||(this[b]=a[b]);(b=a.target||a.srcElement)&&3==b.nodeType&&(b=b.parentNode);this.target=b;if(b=a.relatedTarget)try{b=b.nodeName?b:null}catch(c){b=null}else"mouseover"==this.type?b=a.fromElement:"mouseout"==
this.type&&(b=a.toElement);this.relatedTarget=b;this.clientX=void 0!=a.clientX?a.clientX:a.pageX;this.clientY=void 0!=a.clientY?a.clientY:a.pageY;this.keyCode=a.keyCode?a.keyCode:a.which;this.charCode=a.charCode||("keypress"==this.type?this.keyCode:0);this.altKey=a.altKey;this.ctrlKey=a.ctrlKey;this.shiftKey=a.shiftKey}}
He.prototype.preventDefault=function(){this.event&&(this.event.returnValue=!1,this.event.preventDefault&&this.event.preventDefault())};
He.prototype.stopPropagation=function(){this.event&&(this.event.cancelBubble=!0,this.event.stopPropagation&&this.event.stopPropagation())};
He.prototype.stopImmediatePropagation=function(){this.event&&(this.event.cancelBubble=!0,this.event.stopImmediatePropagation&&this.event.stopImmediatePropagation())};
var Ie={stopImmediatePropagation:1,stopPropagation:1,preventMouseEvent:1,preventManipulation:1,preventDefault:1,layerX:1,layerY:1,scale:1,rotation:1,webkitMovementX:1,webkitMovementY:1};var eb=r("yt.events.listeners_")||{};q("yt.events.listeners_",eb,void 0);var Je=r("yt.events.counter_")||{count:0};q("yt.events.counter_",Je,void 0);function Ke(a,b,c,d){a.addEventListener&&("mouseenter"!=b||"onmouseenter"in document?"mouseleave"!=b||"onmouseenter"in document?"mousewheel"==b&&"MozBoxSizing"in document.documentElement.style&&(b="MozMousePixelScroll"):b="mouseout":b="mouseover");return db(function(e){return e[0]==a&&e[1]==b&&e[2]==c&&e[4]==!!d})}
function N(a,b,c,d){if(!a||!a.addEventListener&&!a.attachEvent)return"";d=!!d;var e=Ke(a,b,c,d);if(e)return e;var e=++Je.count+"",f=!("mouseenter"!=b&&"mouseleave"!=b||!a.addEventListener||"onmouseenter"in document),h;h=f?function(d){d=new He(d);if(!Be(d.relatedTarget,function(b){return b==a},!0))return d.currentTarget=a,d.type=b,c.call(a,d)}:function(b){b=new He(b);
b.currentTarget=a;return c.call(a,b)};
h=bc(h);a.addEventListener?("mouseenter"==b&&f?b="mouseover":"mouseleave"==b&&f?b="mouseout":"mousewheel"==b&&"MozBoxSizing"in document.documentElement.style&&(b="MozMousePixelScroll"),a.addEventListener(b,h,d)):a.attachEvent("on"+b,h);eb[e]=[a,b,c,h,d];return e}
function Le(a){a&&("string"==typeof a&&(a=[a]),A(a,function(a){if(a in eb){var c=eb[a],d=c[0],e=c[1],f=c[3],c=c[4];d.removeEventListener?d.removeEventListener(e,f,c):d.detachEvent&&d.detachEvent("on"+e,f);delete eb[a]}}))}
;function Me(){if(null==r("_lact",window)){var a=parseInt(H("LACT"),10),a=isFinite(a)?y()-Math.max(a,0):-1;q("_lact",a,window);-1==a&&Ne();N(document,"keydown",Ne);N(document,"keyup",Ne);N(document,"mousedown",Ne);N(document,"mouseup",Ne);kc("page-mouse",Ne);kc("page-scroll",Ne);kc("page-resize",Ne)}}
function Ne(){null==r("_lact",window)&&(Me(),r("_lact",window));var a=y();q("_lact",a,window);K("USER_ACTIVE")}
function Oe(){var a=r("_lact",window);return null==a?-1:Math.max(y()-a,0)}
;function Pe(){}
Pe.prototype.set=u;Pe.prototype.get=u;Pe.prototype.remove=u;function Qe(){}
z(Qe,Pe);Qe.prototype.S=function(){var a=0;Fc(this.ma(!0),function(){a++});
return a};
Qe.prototype.ma=u;Qe.prototype.clear=function(){var a=Gc(this.ma(!0)),b=this;A(a,function(a){b.remove(a)})};function Re(a){this.b=a}
z(Re,Qe);g=Re.prototype;g.isAvailable=function(){if(!this.b)return!1;try{return this.b.setItem("__sak","1"),this.b.removeItem("__sak"),!0}catch(a){return!1}};
g.set=function(a,b){try{this.b.setItem(a,b)}catch(c){if(0==this.b.length)throw"Storage mechanism: Storage disabled";throw"Storage mechanism: Quota exceeded";}};
g.get=function(a){a=this.b.getItem(a);if(!w(a)&&null!==a)throw"Storage mechanism: Invalid value was encountered";return a};
g.remove=function(a){this.b.removeItem(a)};
g.S=function(){return this.b.length};
g.ma=function(a){var b=0,c=this.b,d=new Dc;d.next=function(){if(b>=c.length)throw Cc;var d=c.key(b++);if(a)return d;d=c.getItem(d);if(!w(d))throw"Storage mechanism: Invalid value was encountered";return d};
return d};
g.clear=function(){this.b.clear()};
g.key=function(a){return this.b.key(a)};function Se(){var a=null;try{a=window.localStorage||null}catch(b){}this.b=a}
z(Se,Re);function Te(){var a=null;try{a=window.sessionStorage||null}catch(b){}this.b=a}
z(Te,Re);function Ue(a){this.b=a}
Ue.prototype.set=function(a,b){p(b)?this.b.set(a,M(b)):this.b.remove(a)};
Ue.prototype.get=function(a){var b;try{b=this.b.get(a)}catch(c){return}if(null!==b)try{return od(b)}catch(c){throw"Storage: Invalid value was encountered";}};
Ue.prototype.remove=function(a){this.b.remove(a)};function Ve(a){this.b=a}
z(Ve,Ue);function We(a){this.data=a}
function Xe(a){return!p(a)||a instanceof We?a:new We(a)}
Ve.prototype.set=function(a,b){Ve.B.set.call(this,a,Xe(b))};
Ve.prototype.f=function(a){a=Ve.B.get.call(this,a);if(!p(a)||a instanceof Object)return a;throw"Storage: Invalid value was encountered";};
Ve.prototype.get=function(a){if(a=this.f(a)){if(a=a.data,!p(a))throw"Storage: Invalid value was encountered";}else a=void 0;return a};function Ye(a){this.b=a}
z(Ye,Ve);function Ze(a){var b=a.creation;a=a.expiration;return!!a&&a<y()||!!b&&b>y()}
Ye.prototype.set=function(a,b,c){if(b=Xe(b)){if(c){if(c<y()){Ye.prototype.remove.call(this,a);return}b.expiration=c}b.creation=y()}Ye.B.set.call(this,a,b)};
Ye.prototype.f=function(a,b){var c=Ye.B.f.call(this,a);if(c)if(!b&&Ze(c))Ye.prototype.remove.call(this,a);else return c};function $e(a){this.b=a}
z($e,Ye);function af(a,b){var c=[];Fc(b,function(a){var b;try{b=$e.prototype.f.call(this,a,!0)}catch(f){if("Storage: Invalid value was encountered"==f)return;throw f;}p(b)?Ze(b)&&c.push(a):c.push(a)},a);
return c}
function bf(a,b){var c=af(a,b);A(c,function(a){$e.prototype.remove.call(this,a)},a)}
function cf(){var a=df;bf(a,a.b.ma(!0))}
;function O(a,b,c){var d=c&&0<c?c:0;c=d?y()+1E3*d:0;if((d=d?df:ef)&&window.JSON){w(b)||(b=JSON.stringify(b,void 0));try{d.set(a,b,c)}catch(e){d.remove(a)}}}
function P(a){if(!ef&&!df||!window.JSON)return null;var b;try{b=ef.get(a)}catch(c){}if(!w(b))try{b=df.get(a)}catch(c){}if(!w(b))return null;try{b=JSON.parse(b,void 0)}catch(c){}return b}
function ff(a){ef&&ef.remove(a);df&&df.remove(a)}
var df,gf=new Se;df=gf.isAvailable()?new $e(gf):null;var ef,hf=new Te;ef=hf.isAvailable()?new $e(hf):null;var jf=C("Firefox"),kf=Vc()||C("iPod"),lf=C("iPad"),mf=C("Android")&&!(ob()||C("Firefox")||C("Opera")||C("Silk")),nf=ob(),of=C("Safari")&&!(ob()||C("Coast")||C("Opera")||C("Edge")||C("Silk")||C("Android"))&&!(Vc()||C("iPad")||C("iPod"));function pf(){}
;var qf={log_event:"events",log_interaction:"interactions"},rf={},sf={},tf=0,uf=r("yt.logging.transport.logsQueue_")||{};q("yt.logging.transport.logsQueue_",uf,void 0);
function vf(){J(tf);if(!fb(uf)){for(var a in uf){var b=rf[a];if(!b){b=sf[a];if(!b)continue;b=new b;rf[a]=b}var c=b.b,c={client:{hl:c.ud,gl:c.td,clientName:c.sd,clientVersion:c.innertubeContextClientVersion}};H("DELEGATED_SESSION_ID")&&(c.user={onBehalfOfUser:H("DELEGATED_SESSION_ID")});c={context:c};c.requestTimeMs=Math.round(dc());c[qf[a]]=uf[a];wf(b,a,c);delete uf[a]}fb(uf)||xf()}}
function xf(){J(tf);tf=I(vf,H("LOGGING_BATCH_TIMEOUT",1E4))}
;var yf={};function zf(a){this.b=a||{cookie:""}}
var Af=/\s*;\s*/;g=zf.prototype;g.isEnabled=function(){return navigator.cookieEnabled};
g.set=function(a,b,c,d,e,f){if(/[;=\s]/.test(a))throw Error('Invalid cookie name "'+a+'"');if(/[;\r\n]/.test(b))throw Error('Invalid cookie value "'+b+'"');p(c)||(c=-1);e=e?";domain="+e:"";d=d?";path="+d:"";f=f?";secure":"";c=0>c?"":0==c?";expires="+(new Date(1970,1,1)).toUTCString():";expires="+(new Date(y()+1E3*c)).toUTCString();this.b.cookie=a+"="+b+e+d+c+f};
g.get=function(a,b){for(var c=a+"=",d=(this.b.cookie||"").split(Af),e=0,f;f=d[e];e++){if(0==f.lastIndexOf(c,0))return f.substr(c.length);if(f==a)return""}return b};
g.remove=function(a,b,c){var d=p(this.get(a));this.set(a,"",0,b,c);return d};
g.na=function(){return Bf(this).keys};
g.T=function(){return Bf(this).values};
g.isEmpty=function(){return!this.b.cookie};
g.S=function(){return this.b.cookie?(this.b.cookie||"").split(Af).length:0};
g.Ya=function(a){for(var b=Bf(this).values,c=0;c<b.length;c++)if(b[c]==a)return!0;return!1};
g.clear=function(){for(var a=Bf(this).keys,b=a.length-1;0<=b;b--)this.remove(a[b])};
function Bf(a){a=(a.b.cookie||"").split(Af);for(var b=[],c=[],d,e,f=0;e=a[f];f++)d=e.indexOf("="),-1==d?(b.push(""),c.push(e)):(b.push(e.substring(0,d)),c.push(e.substring(d+1)));return{keys:b,values:c}}
var Cf=new zf("undefined"==typeof document?null:document);Cf.f=3950;function Df(a,b,c){Cf.set(""+a,b,c,"/","youtube.com")}
;function Ef(a,b,c){var d=H("EVENT_ID");d&&(b||(b={}),b.ei||(b.ei=d));if(b){var d=H("VALID_SESSION_TEMPDATA_DOMAINS",[]),e=yd(zd(3,window.location.href));e&&d.push(e);e=yd(zd(3,a));if(B(d,e)||!e&&0==a.lastIndexOf("/",0)){var f=a.match(xd),d=f[5],e=f[6],f=f[7],h="";d&&(h+=d);e&&(h+="?"+e);f&&(h+="#"+f);d=h;e=d.indexOf("#");if(d=0>e?d:d.substr(0,e))wd("enable_more_related_ve_logging")&&(b.itct||b.ved)&&((e=b.csn)||(e=H("client-screen-nonce",void 0))||(e=H("EVENT_ID",void 0)),b.csn=e),d="ST-"+Ga(d).toString(36),
e=b?Fd(b):"",Df(d,e,5),b&&(b=b.itct||b.ved,d=r("yt.logging.screenreporter.storeParentElement"),b&&d&&d(new pf))}}if(c)return!1;(window.ytspf||{}).enabled?spf.navigate(a):(c=window.location,a=Hd(a,{})+"",ha(c)&&(c.constructor.displayName||c.constructor.name||Object.prototype.toString.call(c)),a=a instanceof pb?a:tb(a),c.href=rb(a));return!0}
;function Ff(a){a=a||{};this.url=a.url||"";this.urlV9As2=a.url_v9as2||"";this.args=a.args||hb(Gf);this.assets=a.assets||{};this.attrs=a.attrs||hb(Hf);this.params=a.params||hb(If);this.minVersion=a.min_version||"8.0.0";this.fallback=a.fallback||null;this.fallbackMessage=a.fallbackMessage||null;this.html5=!!a.html5;this.disable=a.disable||{};this.loaded=!!a.loaded;this.messages=a.messages||{}}
var Gf={enablejsapi:1},Hf={},If={allowscriptaccess:"always",allowfullscreen:"true",bgcolor:"#000000"};function Jf(a){a instanceof Ff||(a=new Ff(a));return a}
Ff.prototype.clone=function(){var a=new Ff,b;for(b in this)if(this.hasOwnProperty(b)){var c=this[b];"object"==ca(c)?a[b]=hb(c):a[b]=c}return a};function Kf(a,b,c,d){this.top=a;this.right=b;this.bottom=c;this.left=d}
g=Kf.prototype;g.getHeight=function(){return this.bottom-this.top};
g.clone=function(){return new Kf(this.top,this.right,this.bottom,this.left)};
g.contains=function(a){return this&&a?a instanceof Kf?a.left>=this.left&&a.right<=this.right&&a.top>=this.top&&a.bottom<=this.bottom:a.H>=this.left&&a.H<=this.right&&a.I>=this.top&&a.I<=this.bottom:!1};
g.ceil=function(){this.top=Math.ceil(this.top);this.right=Math.ceil(this.right);this.bottom=Math.ceil(this.bottom);this.left=Math.ceil(this.left);return this};
g.floor=function(){this.top=Math.floor(this.top);this.right=Math.floor(this.right);this.bottom=Math.floor(this.bottom);this.left=Math.floor(this.left);return this};
g.round=function(){this.top=Math.round(this.top);this.right=Math.round(this.right);this.bottom=Math.round(this.bottom);this.left=Math.round(this.left);return this};function Lf(a,b,c,d){this.left=a;this.top=b;this.width=c;this.height=d}
g=Lf.prototype;g.clone=function(){return new Lf(this.left,this.top,this.width,this.height)};
g.contains=function(a){return a instanceof ge?a.H>=this.left&&a.H<=this.left+this.width&&a.I>=this.top&&a.I<=this.top+this.height:this.left<=a.left&&this.left+this.width>=a.left+a.width&&this.top<=a.top&&this.top+this.height>=a.top+a.height};
g.ceil=function(){this.left=Math.ceil(this.left);this.top=Math.ceil(this.top);this.width=Math.ceil(this.width);this.height=Math.ceil(this.height);return this};
g.floor=function(){this.left=Math.floor(this.left);this.top=Math.floor(this.top);this.width=Math.floor(this.width);this.height=Math.floor(this.height);return this};
g.round=function(){this.left=Math.round(this.left);this.top=Math.round(this.top);this.width=Math.round(this.width);this.height=Math.round(this.height);return this};function Mf(a,b){var c=le(a);return c.defaultView&&c.defaultView.getComputedStyle&&(c=c.defaultView.getComputedStyle(a,null))?c[b]||c.getPropertyValue(b)||"":""}
function Nf(a,b){return Mf(a,b)||(a.currentStyle?a.currentStyle[b]:null)||a.style&&a.style[b]}
function Of(a){var b;try{b=a.getBoundingClientRect()}catch(c){return{left:0,top:0,right:0,bottom:0}}L&&a.ownerDocument.body&&(a=a.ownerDocument,b.left-=a.documentElement.clientLeft+a.body.clientLeft,b.top-=a.documentElement.clientTop+a.body.clientTop);return b}
function Pf(a,b){"number"==typeof a&&(a=(b?Math.round(a):a)+"px");return a}
function Qf(a){var b=Rf;if("none"!=Nf(a,"display"))return b(a);var c=a.style,d=c.display,e=c.visibility,f=c.position;c.visibility="hidden";c.position="absolute";c.display="inline";a=b(a);c.display=d;c.position=f;c.visibility=e;return a}
function Rf(a){var b=a.offsetWidth,c=a.offsetHeight,d=bd&&!b&&!c;return p(b)&&!d||!a.getBoundingClientRect?new he(b,c):(a=Of(a),new he(a.right-a.left,a.bottom-a.top))}
function Sf(a,b){if(/^\d+px?$/.test(b))return parseInt(b,10);var c=a.style.left,d=a.runtimeStyle.left;a.runtimeStyle.left=a.currentStyle.left;a.style.left=b;var e=a.style.pixelLeft;a.style.left=c;a.runtimeStyle.left=d;return e}
function Tf(a,b){var c=a.currentStyle?a.currentStyle[b]:null;return c?Sf(a,c):0}
var Uf={thin:2,medium:4,thick:6};function Vf(a,b){if("none"==(a.currentStyle?a.currentStyle[b+"Style"]:null))return 0;var c=a.currentStyle?a.currentStyle[b+"Width"]:null;return c in Uf?Uf[c]:Sf(a,c)}
;function Wf(){this.g=this.f=this.b=0;this.i="";var a=r("window.navigator.plugins"),b=r("window.navigator.mimeTypes"),a=a&&a["Shockwave Flash"],b=b&&b["application/x-shockwave-flash"],b=a&&b&&b.enabledPlugin&&a.description||"";if(a=b){var c=a.indexOf("Shockwave Flash");0<=c&&(a=a.substr(c+15));for(var c=a.split(" "),d="",a="",e=0,f=c.length;e<f;e++)if(d)if(a)break;else a=c[e];else d=c[e];d=d.split(".");c=parseInt(d[0],10)||0;d=parseInt(d[1],10)||0;e=0;if("r"==a.charAt(0)||"d"==a.charAt(0))e=parseInt(a.substr(1),
10)||0;a=[c,d,e]}else a=[0,0,0];this.i=b;b=a;this.b=b[0];this.f=b[1];this.g=b[2];if(0>=this.b){var h,k,m,n;if(ec)try{h=new ActiveXObject("ShockwaveFlash.ShockwaveFlash")}catch(ga){h=null}else m=document.body,n=document.createElement("object"),n.setAttribute("type","application/x-shockwave-flash"),h=m.appendChild(n);if(h&&"GetVariable"in h)try{k=h.GetVariable("$version")}catch(ga){k=""}m&&n&&m.removeChild(n);(h=k||"")?(h=h.split(" ")[1].split(","),h=[parseInt(h[0],10)||0,parseInt(h[1],10)||0,parseInt(h[2],
10)||0]):h=[0,0,0];this.b=h[0];this.f=h[1];this.g=h[2]}}
ba(Wf);function Xf(a,b,c,d){b="string"==typeof b?b.split("."):[b,c,d];b[0]=parseInt(b[0],10)||0;b[1]=parseInt(b[1],10)||0;b[2]=parseInt(b[2],10)||0;return a.b>b[0]||a.b==b[0]&&a.f>b[1]||a.b==b[0]&&a.f==b[1]&&a.g>=b[2]}
;function Yf(a){if(window.spf){var b=a.match(Zf);spf.style.load(a,b?b[1]:"",void 0)}else $f(a)}
function $f(a){var b=ag(a),c=document.getElementById(b),d=c&&D(c,"loaded");d||c&&!d||(c=bg(a,b,function(){D(c,"loaded")||(Db(c,"loaded","true"),K(b),I(na(pc,b),0))}))}
function bg(a,b,c){var d=document.createElement("link");d.id=b;d.onload=function(){c&&setTimeout(c,0)};
a=Ce(a);Cb(d,a);(document.getElementsByTagName("head")[0]||document.body).appendChild(d);return d}
function ag(a){var b=document.createElement("a");Bb(b,a);a=b.href.replace(/^[a-zA-Z]+:\/\//,"//");return"css-"+Ga(a)}
var Zf=/cssbin\/(?:debug-)?([a-zA-Z0-9_-]+?)(?:-2x|-web|-rtl|-vfl|.css)/;var cg;var dg=lb,dg=dg.toLowerCase();if(-1!=dg.indexOf("android")){var eg=dg.match(/android\D*(\d\.\d)[^\;|\)]*[\;\)]/);if(eg)cg=Number(eg[1]);else{var fg={cupcake:1.5,donut:1.6,eclair:2,froyo:2.2,gingerbread:2.3,honeycomb:3,"ice cream sandwich":4,jellybean:4.1},gg=dg.match("("+cb(fg).join("|")+")");cg=gg?fg[gg[0]]:0}}else cg=void 0;var hg=['video/mp4; codecs="avc1.42001E, mp4a.40.2"','video/webm; codecs="vp8.0, vorbis"'],ig=['audio/mp4; codecs="mp4a.40.2"'];function jg(a){E.call(this);this.b=[];this.f=a||this}
z(jg,E);function kg(a,b,c,d){d=bc(x(d,a.f));b.addEventListener(c,d);a.b.push({target:b,name:c,Db:d})}
jg.prototype.zb=function(a){for(var b=0;b<this.b.length;b++)if(this.b[b]==a){this.b.splice(b,1);a.target.removeEventListener(a.name,a.Db);break}};
function lg(a){for(;a.b.length;){var b=a.b.pop();b.target.removeEventListener(b.name,b.Db)}}
jg.prototype.w=function(){lg(this);jg.B.w.call(this)};function mg(a){a.prototype.then=a.prototype.then;a.prototype.$goog_Thenable=!0}
;function ng(a,b){this.b=0;this.l=void 0;this.i=this.f=this.g=null;this.j=this.o=!1;if(a!=t)try{var c=this;a.call(b,function(a){og(c,2,a)},function(a){og(c,3,a)})}catch(d){og(this,3,d)}}
function pg(){this.next=this.context=this.f=this.g=this.b=null;this.i=!1}
pg.prototype.reset=function(){this.context=this.f=this.g=this.b=null;this.i=!1};
var qg=new Kb(function(){return new pg},function(a){a.reset()},100);
function rg(a,b,c){var d=qg.get();d.g=a;d.f=b;d.context=c;return d}
function sg(a){if(a instanceof ng)return a;var b=new ng(t);og(b,2,a);return b}
function tg(a){return new ng(function(b,c){c(a)})}
ng.prototype.then=function(a,b,c){return ug(this,fa(a)?a:null,fa(b)?b:null,c)};
mg(ng);ng.prototype.cancel=function(a){0==this.b&&Pb(function(){var b=new vg(a);wg(this,b)},this)};
function wg(a,b){if(0==a.b)if(a.g){var c=a.g;if(c.f){for(var d=0,e=null,f=null,h=c.f;h&&(h.i||(d++,h.b==a&&(e=h),!(e&&1<d)));h=h.next)e||(f=h);e&&(0==c.b&&1==d?wg(c,b):(f?(d=f,d.next==c.i&&(c.i=d),d.next=d.next.next):xg(c),yg(c,e,3,b)))}a.g=null}else og(a,3,b)}
function zg(a,b){a.f||2!=a.b&&3!=a.b||Ag(a);a.i?a.i.next=b:a.f=b;a.i=b}
function ug(a,b,c,d){var e=rg(null,null,null);e.b=new ng(function(a,h){e.g=b?function(c){try{var e=b.call(d,c);a(e)}catch(n){h(n)}}:a;
e.f=c?function(b){try{var e=c.call(d,b);!p(e)&&b instanceof vg?h(b):a(e)}catch(n){h(n)}}:h});
e.b.g=a;zg(a,e);return e.b}
ng.prototype.R=function(a){this.b=0;og(this,2,a)};
ng.prototype.F=function(a){this.b=0;og(this,3,a)};
function og(a,b,c){if(0==a.b){a===c&&(b=3,c=new TypeError("Promise cannot resolve to itself"));a.b=1;var d;a:{var e=c,f=a.R,h=a.F;if(e instanceof ng)zg(e,rg(f||t,h||null,a)),d=!0;else{var k;if(e)try{k=!!e.$goog_Thenable}catch(n){k=!1}else k=!1;if(k)e.then(f,h,a),d=!0;else{if(ha(e))try{var m=e.then;if(fa(m)){Bg(e,m,f,h,a);d=!0;break a}}catch(n){h.call(a,n);d=!0;break a}d=!1}}}d||(a.l=c,a.b=b,a.g=null,Ag(a),3!=b||c instanceof vg||Cg(a,c))}}
function Bg(a,b,c,d,e){function f(a){k||(k=!0,d.call(e,a))}
function h(a){k||(k=!0,c.call(e,a))}
var k=!1;try{b.call(a,h,f)}catch(m){f(m)}}
function Ag(a){a.o||(a.o=!0,Pb(a.A,a))}
function xg(a){var b=null;a.f&&(b=a.f,a.f=b.next,b.next=null);a.f||(a.i=null);return b}
ng.prototype.A=function(){for(var a;a=xg(this);)yg(this,a,this.b,this.l);this.o=!1};
function yg(a,b,c,d){if(3==c&&b.f&&!b.i)for(;a&&a.j;a=a.g)a.j=!1;if(b.b)b.b.g=null,Dg(b,c,d);else try{b.i?b.g.call(b.context):Dg(b,c,d)}catch(e){Eg.call(null,e)}Lb(qg,b)}
function Dg(a,b,c){2==b?a.g.call(a.context,c):a.f&&a.f.call(a.context,c)}
function Cg(a,b){a.j=!0;Pb(function(){a.j&&Eg.call(null,b)})}
var Eg=Hb;function vg(a){oa.call(this,a)}
z(vg,oa);vg.prototype.name="cancel";function Fg(){this.b={apiaryHost:H("APIARY_HOST",void 0),$c:H("APIARY_HOST_FIRSTPARTY",void 0),gapiHintOverride:H("GAPI_HINT_OVERRIDE"),gapiHintParams:H("GAPI_HINT_PARAMS",void 0),innertubeApiKey:H("INNERTUBE_API_KEY",void 0),innertubeApiVersion:H("INNERTUBE_API_VERSION",void 0),sd:H("INNERTUBE_CONTEXT_CLIENT_NAME","WEB"),innertubeContextClientVersion:H("INNERTUBE_CONTEXT_CLIENT_VERSION",void 0),ud:H("INNERTUBE_CONTEXT_HL",void 0),td:H("INNERTUBE_CONTEXT_GL",void 0),We:H("XHR_APIARY_HOST",void 0)};
Gg||(Gg=Hg(this.b))}
var Gg=null;function Hg(a){return(new ng(function(b){qc(H("GAPI_LOADER_URL",void 0),function(){try{r("yt.gapi.load")("client",{gapiHintOverride:a.gapiHintOverride,_c:{jsl:{h:a.gapiHintParams}},callback:b})}catch(c){cc(c)}})})).then(function(){})}
Fg.prototype.f=function(){var a=r("gapi.config.update");a("googleapis.config/auth/useFirstPartyAuth",!0);var b=this.b.apiaryHost;/^[\s\xa0]*$/.test(null==b?"":String(b))||a("googleapis.config/root",(-1==b.indexOf("://")?"//":"")+b);b=this.b.$c;/^[\s\xa0]*$/.test(null==b?"":String(b))||a("googleapis.config/root-1p",(-1==b.indexOf("://")?"//":"")+b);a("googleapis.config/sessionIndex",H("SESSION_INDEX"));r("gapi.client.setApiKey")(this.b.innertubeApiKey)};
function wf(a,b,c){var d={},e,f=!1;0<d.timeout&&(e=I(function(){f||(f=!0,d.Ga&&d.Ga())},d.timeout));
Ig(a,b,c,function(a){if(!f)if(f=!0,e&&J(e),a)d.aa&&d.aa(a);else if(d.onError)d.onError()})}
function Ig(a,b,c,d){var e={path:"/youtubei/"+a.b.innertubeApiVersion+"/"+b,headers:{"X-Goog-Visitor-Id":H("VISITOR_DATA")},method:"POST",body:M(c)},f=x(a.f,a);Gg.then(function(){f();r("gapi.client.request")(e).execute(d||t)})}
;function Jg(a,b,c){var d={};d.eventTimeMs=Math.round(c||dc());d[a]=b;uf.log_event=uf.log_event||[];a=uf.log_event;a.push(d);sf.log_event=Fg;20<=a.length?vf():xf()}
;function Kg(a,b){this.f=this.A=this.i="";this.l=null;this.j=this.b="";this.o=!1;var c;a instanceof Kg?(this.o=p(b)?b:a.o,Lg(this,a.i),this.A=a.A,Mg(this,a.f),Ng(this,a.l),this.b=a.b,Og(this,a.g.clone()),this.j=a.j):a&&(c=String(a).match(xd))?(this.o=!!b,Lg(this,c[1]||"",!0),this.A=Pg(c[2]||""),Mg(this,c[3]||"",!0),Ng(this,c[4]),this.b=Pg(c[5]||"",!0),Og(this,c[6]||"",!0),this.j=Pg(c[7]||"")):(this.o=!!b,this.g=new Qg(null,0,this.o))}
Kg.prototype.toString=function(){var a=[],b=this.i;b&&a.push(Rg(b,Sg,!0),":");var c=this.f;if(c||"file"==b)a.push("//"),(b=this.A)&&a.push(Rg(b,Sg,!0),"@"),a.push(encodeURIComponent(String(c)).replace(/%25([0-9a-fA-F]{2})/g,"%$1")),c=this.l,null!=c&&a.push(":",String(c));if(c=this.b)this.f&&"/"!=c.charAt(0)&&a.push("/"),a.push(Rg(c,"/"==c.charAt(0)?Tg:Ug,!0));(c=this.g.toString())&&a.push("?",c);(c=this.j)&&a.push("#",Rg(c,Vg));return a.join("")};
Kg.prototype.resolve=function(a){var b=this.clone(),c=!!a.i;c?Lg(b,a.i):c=!!a.A;c?b.A=a.A:c=!!a.f;c?Mg(b,a.f):c=null!=a.l;var d=a.b;if(c)Ng(b,a.l);else if(c=!!a.b){if("/"!=d.charAt(0))if(this.f&&!this.b)d="/"+d;else{var e=b.b.lastIndexOf("/");-1!=e&&(d=b.b.substr(0,e+1)+d)}e=d;if(".."==e||"."==e)d="";else if(-1!=e.indexOf("./")||-1!=e.indexOf("/.")){for(var d=0==e.lastIndexOf("/",0),e=e.split("/"),f=[],h=0;h<e.length;){var k=e[h++];"."==k?d&&h==e.length&&f.push(""):".."==k?((1<f.length||1==f.length&&
""!=f[0])&&f.pop(),d&&h==e.length&&f.push("")):(f.push(k),d=!0)}d=f.join("/")}else d=e}c?b.b=d:c=""!==a.g.toString();c?Og(b,Pg(a.g.toString())):c=!!a.j;c&&(b.j=a.j);return b};
Kg.prototype.clone=function(){return new Kg(this)};
function Lg(a,b,c){a.i=c?Pg(b,!0):b;a.i&&(a.i=a.i.replace(/:$/,""))}
function Mg(a,b,c){a.f=c?Pg(b,!0):b}
function Ng(a,b){if(b){b=Number(b);if(isNaN(b)||0>b)throw Error("Bad port number "+b);a.l=b}else a.l=null}
function Og(a,b,c){b instanceof Qg?(a.g=b,Wg(a.g,a.o)):(c||(b=Rg(b,Xg)),a.g=new Qg(b,0,a.o))}
function Q(a,b,c){a.g.set(b,c)}
function Yg(a,b,c){v(c)||(c=[String(c)]);Zg(a.g,b,c)}
function $g(a){Q(a,"zx",Math.floor(2147483648*Math.random()).toString(36)+Math.abs(Math.floor(2147483648*Math.random())^y()).toString(36));return a}
function ah(a){return a instanceof Kg?a.clone():new Kg(a,void 0)}
function bh(a,b,c,d){var e=new Kg(null,void 0);a&&Lg(e,a);b&&Mg(e,b);c&&Ng(e,c);d&&(e.b=d);return e}
function Pg(a,b){return a?b?decodeURI(a.replace(/%25/g,"%2525")):decodeURIComponent(a):""}
function Rg(a,b,c){return w(a)?(a=encodeURI(a).replace(b,ch),c&&(a=a.replace(/%25([0-9a-fA-F]{2})/g,"%$1")),a):null}
function ch(a){a=a.charCodeAt(0);return"%"+(a>>4&15).toString(16)+(a&15).toString(16)}
var Sg=/[#\/\?@]/g,Ug=/[\#\?:]/g,Tg=/[\#\?]/g,Xg=/[\#\?@]/g,Vg=/#/g;function Qg(a,b,c){this.f=this.b=null;this.g=a||null;this.i=!!c}
function dh(a){a.b||(a.b=new Hc,a.f=0,a.g&&Ad(a.g,function(b,c){eh(a,ra(b),c)}))}
g=Qg.prototype;g.S=function(){dh(this);return this.f};
function eh(a,b,c){dh(a);a.g=null;b=fh(a,b);var d=a.b.get(b);d||a.b.set(b,d=[]);d.push(c);a.f=a.f+1}
g.remove=function(a){dh(this);a=fh(this,a);return Jc(this.b.f,a)?(this.g=null,this.f=this.f-this.b.get(a).length,this.b.remove(a)):!1};
g.clear=function(){this.b=this.g=null;this.f=0};
g.isEmpty=function(){dh(this);return 0==this.f};
function gh(a,b){dh(a);b=fh(a,b);return Jc(a.b.f,b)}
g.Ya=function(a){var b=this.T();return B(b,a)};
g.na=function(){dh(this);for(var a=this.b.T(),b=this.b.na(),c=[],d=0;d<b.length;d++)for(var e=a[d],f=0;f<e.length;f++)c.push(b[d]);return c};
g.T=function(a){dh(this);var b=[];if(w(a))gh(this,a)&&(b=Ra(b,this.b.get(fh(this,a))));else{a=this.b.T();for(var c=0;c<a.length;c++)b=Ra(b,a[c])}return b};
g.set=function(a,b){dh(this);this.g=null;a=fh(this,a);gh(this,a)&&(this.f=this.f-this.b.get(a).length);this.b.set(a,[b]);this.f=this.f+1;return this};
g.get=function(a,b){var c=a?this.T(a):[];return 0<c.length?String(c[0]):b};
function Zg(a,b,c){a.remove(b);0<c.length&&(a.g=null,a.b.set(fh(a,b),Sa(c)),a.f=a.f+c.length)}
g.toString=function(){if(this.g)return this.g;if(!this.b)return"";for(var a=[],b=this.b.na(),c=0;c<b.length;c++)for(var d=b[c],e=encodeURIComponent(String(d)),d=this.T(d),f=0;f<d.length;f++){var h=e;""!==d[f]&&(h+="="+encodeURIComponent(String(d[f])));a.push(h)}return this.g=a.join("&")};
g.clone=function(){var a=new Qg;a.g=this.g;this.b&&(a.b=this.b.clone(),a.f=this.f);return a};
function fh(a,b){var c=String(b);a.i&&(c=c.toLowerCase());return c}
function Wg(a,b){b&&!a.i&&(dh(a),a.g=null,a.b.forEach(function(a,b){var e=b.toLowerCase();b!=e&&(this.remove(b),Zg(this,e,a))},a));
a.i=b}
g.extend=function(a){for(var b=0;b<arguments.length;b++)Oc(arguments[b],function(a,b){eh(this,b,a)},this)};var hh="corp.google.com googleplex.com youtube.com youtube-nocookie.com youtubeeducation.com borg.google.com prod.google.com sandbox.google.com books.googleusercontent.com docs.google.com drive.google.com mail.google.com photos.google.com plus.google.com lh2.google.com picasaweb.google.com play.google.com googlevideo.com talkgadget.google.com survey.g.doubleclick.net youtube.googleapis.com vevo.com".split(" "),ih="";
function jh(a){return a&&a==ih?!0:(new RegExp("^(https?:)?//([a-z0-9-]{1,63}\\.)*("+hh.join("|").replace(/\./g,".")+")(:[0-9]+)?([/?#]|$)","i")).test(a)?(ih=a,!0):!1}
;var kh={},lh=0;function mh(a){var b=new Image,c=""+lh++;kh[c]=b;b.onload=b.onerror=function(){delete kh[c]};
b.src=a}
;function R(a,b){this.version=a;this.args=b}
function nh(a){if(!a.xa){var b={};a.call(b);a.xa=b.version}return a.xa}
function oh(a,b){function c(){a.apply(this,b.args)}
if(!b.args||!b.version)throw Error("yt.pubsub2.Data.deserialize(): serializedData is incomplete.");var d;try{d=nh(a)}catch(e){}if(!d||b.version!=d)throw Error("yt.pubsub2.Data.deserialize(): serializedData version is incompatible.");c.prototype=a.prototype;try{return new c}catch(e){throw e.message="yt.pubsub2.Data.deserialize(): "+e.message,e;}}
function S(a,b){this.topic=a;this.b=b}
S.prototype.toString=function(){return this.topic};var ph=r("yt.pubsub2.instance_")||new G;G.prototype.subscribe=G.prototype.subscribe;G.prototype.unsubscribeByKey=G.prototype.ka;G.prototype.publish=G.prototype.u;G.prototype.clear=G.prototype.clear;q("yt.pubsub2.instance_",ph,void 0);var qh=r("yt.pubsub2.subscribedKeys_")||{};q("yt.pubsub2.subscribedKeys_",qh,void 0);var rh=r("yt.pubsub2.topicToKeys_")||{};q("yt.pubsub2.topicToKeys_",rh,void 0);var sh=r("yt.pubsub2.isAsync_")||{};q("yt.pubsub2.isAsync_",sh,void 0);
q("yt.pubsub2.skipSubKey_",null,void 0);function T(a,b){var c=th();c&&c.publish.call(c,a.toString(),a,b)}
function uh(a,b,c){var d=th();if(!d)return 0;var e=d.subscribe(a.toString(),function(d,h){if(!window.yt.pubsub2.skipSubKey_||window.yt.pubsub2.skipSubKey_!=e){var k=function(){if(qh[e])try{if(h&&a instanceof S&&a!=d)try{h=oh(a.b,h)}catch(k){throw k.message="yt.pubsub2 cross-binary conversion error for "+a.toString()+": "+k.message,k;}b.call(c||window,h)}catch(k){cc(k)}};
sh[a.toString()]?r("yt.scheduler.instance")?Xd(k,void 0):I(k,0):k()}});
qh[e]=!0;rh[a.toString()]||(rh[a.toString()]=[]);rh[a.toString()].push(e);return e}
function vh(a){var b=th();b&&(ea(a)&&(a=[a]),A(a,function(a){b.unsubscribeByKey(a);delete qh[a]}))}
function th(){return r("yt.pubsub2.instance_")}
;var wh=y().toString();var xh=window.performance||window.mozPerformance||window.msPerformance||window.webkitPerformance||{};function yh(a){R.call(this,1,arguments)}
z(yh,R);var zh=new S("timing-sent",yh);var Ah={vc:!0},Bh=/^mark_/i,Ch={ad_at:"adType",cpn:"clientPlaybackNonce",csn:"clientScreenNonce",yt_lt:"loadType",yt_ad:"isMonetized",yt_ad_pr:"prerollAllowed",yt_red:"isRedSubscriber",yt_vis:"isVisible"},Dh=["isMonetized","prerollAllowed","isRedSubscriber","isVisible"],Eh=x(xh.clearResourceTimings||xh.webkitClearResourceTimings||xh.mozClearResourceTimings||xh.msClearResourceTimings||xh.oClearResourceTimings||t,xh);
function Fh(a){if("_"!=a[0]){var b=a;xh.mark&&(Bh.test(b)||(b="mark_"+b),xh.mark(b))}var b=Gh(),c=dc();b[a]&&(b["_"+a]=b["_"+a]||[b[a]],b["_"+a].push(c));b[a]=c;Hh()["tick_"+a]=void 0;wd("csi_on_gel")?(b=Ih(),"_start"==a?Jg("latencyActionBaselined",{clientActionNonce:b},void 0):Jg("latencyActionTicked",{tickName:a,clientActionNonce:b},void 0),a=!0):a=!1;a||(a=!!r("yt.timing.pingSent_")&&!!wd("navigation_only_csi_reset"));if(!a&&(b=H("TIMING_ACTION",void 0),a=Gh(),r("yt.timing.ready_")&&b&&a._start&&
Jh())){b=!0;c=H("TIMING_WAIT",[]);if(c.length)for(var d=0,e=c.length;d<e;++d)if(!(c[d]in a)){b=!1;break}b&&Kh()}}
function Lh(){var a=Mh().info.yt_lt="hot_bg";Hh().info_yt_lt=a;if(wd("csi_on_gel"))if("yt_lt"in Ch){var b={},c=Ch.yt_lt;B(Dh,c)&&(a=!!a);b[c]=a;a=Ih();b.clientActionNonce=a;Jg("latencyActionInfo",b)}else cc(Error("Unknown label yt_lt logged with GEL CSI."))}
function Jh(){var a=Gh();if(a.aft)return a.aft;for(var b=H("TIMING_AFT_KEYS",["ol"]),c=b.length,d=0;d<c;d++){var e=a[b[d]];if(e)return e}return NaN}
function Kh(){var a=Gh(),b=Mh().info,c=a._start,d;for(d in a)if(0==d.lastIndexOf("_",0)&&v(a[d])){var e=d.slice(1);if(e in Ah){var f=Ja(a[d],function(a){return Math.round(a-c)});
b["all_"+e]=f.join()}delete a[d]}e=!!b.ap;if(f=r("yt.timing.reportbuilder_")){if(f=f(a,b,void 0))Nh(f,e),Oh(),Eh(),Ph(!1,void 0)}else{var h=H("CSI_SERVICE_NAME","youtube"),f={v:2,s:h,action:H("TIMING_ACTION",void 0)},k=b.srt;delete b.srt;void 0===a.srt&&(k||0===k||(k=xh.timing||{},k=Math.max(0,k.responseStart-k.navigationStart),isNaN(k)&&b.pt&&(k=b.pt)),k||0===k)&&(b.srt=Math.round(k));if(b.h5jse){var m=window.location.protocol+r("ytplayer.config.assets.js");(m=xh.getEntriesByName?xh.getEntriesByName(m)[0]:
null)?b.h5jse=Math.round(b.h5jse-m.responseEnd):delete b.h5jse}a.aft=Jh();Qh()&&"youtube"==h&&(Lh(),h=a.vc,m=a.pbs,delete a.aft,b.aft=Math.round(m-h));for(var n in b)"_"!=n.charAt(0)&&(f[n]=b[n]);a.ps=dc();b={};n=[];for(d in a)"_"!=d.charAt(0)&&(h=Math.round(a[d]-c),b[d]=h,n.push(d+"."+h));f.rt=n.join(",");(a=r("ytdebug.logTiming"))&&a(f,b);wd("navigation_only_csi_reset")||(Oh(),Eh(),Ph(!1,void 0));Nh(f,e,void 0);T(zh,new yh(b.aft+(k||0)))}}
function Nh(a,b,c){if(wd("debug_csi_data")){var d=r("yt.timing.csiData");d||(d=[],q("yt.timing.csiData",d,void 0));d.push({page:location.href,time:new Date,args:a})}var d="",e;for(e in a)d+="&"+e+"="+a[e];a="/csi_204?"+d.substring(1);if(window.navigator&&window.navigator.sendBeacon&&b)try{window.navigator&&window.navigator.sendBeacon&&window.navigator.sendBeacon(a,"")||a&&mh(a)}catch(f){a&&mh(a)}else a&&mh(a);Ph(!0,c)}
function Ih(){var a=Mh().nonce;if(!a){var b;a:{if(window.crypto&&window.crypto.getRandomValues)try{var c=Array(16),d=new Uint8Array(16);window.crypto.getRandomValues(d);for(a=0;a<c.length;a++)c[a]=d[a];b=c;break a}catch(e){}b=Array(16);for(c=0;16>c;c++){d=y();for(a=0;a<d%23;a++)b[c]=Math.random();b[c]=Math.floor(256*Math.random())}if(wh)for(c=1,d=0;d<wh.length;d++)b[c%16]=b[c%16]^b[(c-1)%16]/4^wh.charCodeAt(d),c++}c=[];for(d=0;d<b.length;d++)c.push("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_".charAt(b[d]&
63));a=c.join("");Mh().nonce=a}return a}
function Gh(){return Mh().tick}
function Hh(){var a=Mh();"gel"in a||(a.gel={});return a.gel}
function Mh(){return r("ytcsi.data_")||Oh()}
function Oh(){var a={tick:{},info:{}};q("ytcsi.data_",a,void 0);return a}
function Ph(a,b){q("yt.timing."+(b||"")+"pingSent_",a,void 0)}
function Qh(){var a=Gh(),b=a.pbr,c=a.vc,a=a.pbs;return b&&c&&a&&b<c&&c<a&&1==Mh().info.yt_pvis}
;var Rh={"api.invalidparam":2,auth:150,"drm.auth":150,heartbeat:150,"html5.unsupportedads":5,"fmt.noneavailable":5,"fmt.decode":5,"fmt.unplayable":5,"html5.missingapi":5,"html5.unsupportedlive":5,"drm.unavailable":5};function Sh(a,b){E.call(this);this.o=this.j=a;this.Y=b;this.A=!1;this.f={};this.Ja=this.X=null;this.ca=new G;Vb(this,na(F,this.ca));this.i={};this.F=this.Ka=this.g=this.Wa=this.b=null;this.la=!1;this.G=this.l=this.N=this.O=null;this.La={};this.Yc=["onReady"];this.oa=new jg(this);Vb(this,na(F,this.oa));this.Cb=null;this.$b=NaN;this.sa={};Th(this);this.pa("onDetailedError",x(this.Id,this));this.pa("onTabOrderChange",x(this.cd,this));this.pa("onTabAnnounce",x(this.ac,this));this.pa("WATCH_LATER_VIDEO_ADDED",
x(this.Jd,this));this.pa("WATCH_LATER_VIDEO_REMOVED",x(this.Kd,this));jf||(this.pa("onMouseWheelCapture",x(this.Fd,this)),this.pa("onMouseWheelRelease",x(this.Gd,this)));this.pa("onAdAnnounce",x(this.ac,this));this.K=new jg(this);Vb(this,na(F,this.K));this.Va=!1;this.Ua=null}
z(Sh,E);var Uh=["drm.unavailable","fmt.noneavailable","html5.missingapi","html5.unsupportedads","html5.unsupportedlive"];g=Sh.prototype;g.Wb=function(a,b){this.C()||(Vh(this,a),Wh(this,b),this.A&&Xh(this))};
function Vh(a,b){a.Wa=b;a.b=b.clone();a.g=a.b.attrs.id||a.g;"video-player"==a.g&&(a.g=a.Y,a.b.attrs.id=a.Y);a.o.id==a.g&&(a.g+="-player",a.b.attrs.id=a.g);a.b.args.enablejsapi="1";a.b.args.playerapiid=a.Y;a.Ka||(a.Ka=Yh(a,a.b.args.jsapicallback||"onYouTubePlayerReady"));a.b.args.jsapicallback=null;var c=a.b.attrs.width;c&&(a.o.style.width=Pf(Number(c)||c,!0));if(c=a.b.attrs.height)a.o.style.height=Pf(Number(c)||c,!0)}
g.kd=function(){return this.Wa};
function Xh(a){a.b.loaded||(a.b.loaded=!0,"0"!=a.b.args.autoplay?a.f.loadVideoByPlayerVars(a.b.args):a.f.cueVideoByPlayerVars(a.b.args))}
function Zh(a){if(!p(a.b.disable.flash)){var b=a.b.disable,c;c=Xf(Wf.getInstance(),a.b.minVersion);b.flash=!c}return!a.b.disable.flash}
function $h(a,b){if((!b||(5!=(Rh[b.errorCode]||5)?0:-1!=Uh.indexOf(b.errorCode)))&&Zh(a)){var c=ai(a);c&&c.stopVideo&&c.stopVideo();var d=a.b;c&&c.getUpdatedConfigurationData&&(c=c.getUpdatedConfigurationData(),d=Jf(c));d.args.autoplay=1;d.args.html5_unavailable="1";Vh(a,d);Wh(a,"flash")}}
function Wh(a,b){if(!a.C()){if(!b){var c;if(!(c=!a.b.html5&&Zh(a))){if(!p(a.b.disable.html5)){var d;c=!0;void 0!=a.b.args.deviceHasDisplay&&(c=a.b.args.deviceHasDisplay);if(2.2==cg)d=!0;else{a:{var e=c;c=r("yt.player.utils.videoElement_");c||(c=document.createElement("video"),q("yt.player.utils.videoElement_",c,void 0));try{if(c.canPlayType)for(var e=e?hg:ig,f=0;f<e.length;f++)if(c.canPlayType(e[f])){d=null;break a}d="fmt.noneavailable"}catch(h){d="html5.missingapi"}}d=!d}d&&(d=bi(a)||a.b.assets.js);
a.b.disable.html5=!d;d||(a.b.args.html5_unavailable="1")}c=!!a.b.disable.html5}b=c?Zh(a)?"flash":"unsupported":"html5"}("flash"==b?a.se:a.te).call(a)}}
function bi(a){var b=!0,c=ai(a);c&&a.b&&(a=a.b,b=D(c,"version")==a.assets.js);return b&&!!r("yt.player.Application.create")}
g.te=function(){if(!this.la){var a=bi(this);a&&"html5"==ci(this)?(this.F="html5",this.A||this.Pa()):(di(this),this.F="html5",a&&this.N?(this.j.appendChild(this.N),this.Pa()):(this.b.loaded=!0,this.O=x(function(){var a=this.j,c=this.b.clone();r("yt.player.Application.create")(a,c);this.Pa()},this),this.la=!0,a?this.O():(qc(this.b.assets.js,this.O),Yf(this.b.assets.css))))}};
g.se=function(){var a=this.b.clone();if(!this.l){var b=ai(this);b&&(this.l=document.createElement("span"),this.l.tabIndex=0,kg(this.oa,this.l,"focus",this.nc),this.G=document.createElement("span"),this.G.tabIndex=0,kg(this.oa,this.G,"focus",this.nc),b.parentNode&&b.parentNode.insertBefore(this.l,b),b.parentNode&&b.parentNode.insertBefore(this.G,b.nextSibling))}a.attrs.width=a.attrs.width||"100%";a.attrs.height=a.attrs.height||"100%";if("flash"==ci(this))this.F="flash",this.A||this.Pa();else{di(this);
this.F="flash";this.b.loaded=!0;var b=Wf.getInstance(),c=(-1<b.i.indexOf("Gnash")&&-1==b.i.indexOf("AVM2")||9==b.b&&1==b.f||9==b.b&&0==b.f&&1==b.g?0:9<=b.b)||-1<navigator.userAgent.indexOf("Sony/COM2")&&!Xf(b,9,1,58)?a.url:a.urlV9As2;window!=window.top&&document.referrer&&(a.args.framer=document.referrer.substring(0,128));b=this.j;if(c){var b=w(b)?me(b):b,d=hb(a.attrs);d.tabindex=0;var e=hb(a.params);e.flashvars=Fd(a.args);if(ec){d.classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000";e.movie=c;var a=
document.createElement("object"),f;for(f in d)a.setAttribute(f,d[f]);for(f in e)d=document.createElement("param"),d.setAttribute("name",f),d.setAttribute("value",e[f]),a.appendChild(d)}else{d.type="application/x-shockwave-flash";d.src=c;a=document.createElement("embed");a.setAttribute("name",d.id);for(f in d)a.setAttribute(f,d[f]);for(f in e)a.setAttribute(f,e[f])}f=document.createElement("div");f.appendChild(a);b.innerHTML=f.innerHTML}this.Pa()}};
g.nc=function(){ai(this).focus()};
function ai(a){var b=me(a.g);!b&&a.o&&a.o.querySelector&&(b=a.o.querySelector("#"+a.g));return b}
g.Pa=function(){if(!this.C()){var a=ai(this),b=!1;try{a&&a.getApiInterface&&a.getApiInterface()&&(b=!0)}catch(f){}if(b)if(this.la=!1,a.isNotServable&&a.isNotServable(this.b.args.video_id))$h(this);else{Th(this);this.A=!0;a=ai(this);a.addEventListener&&(this.X=ei(this,a,"addEventListener"));a.removeEventListener&&(this.Ja=ei(this,a,"removeEventListener"));for(var b=a.getApiInterface(),b=b.concat(a.getInternalApiInterface()),c=0;c<b.length;c++){var d=b[c];this.f[d]||(this.f[d]=ei(this,a,d))}for(var e in this.i)this.X(e,
this.i[e]);Xh(this);this.Ka&&this.Ka(this.f);this.ca.u("onReady",this.f)}else this.$b=I(x(this.Pa,this),50)}};
function ei(a,b,c){var d=b[c];return function(){try{return a.Cb=null,d.apply(b,arguments)}catch(e){"Bad NPObject as private data!"!=e.message&&"sendAbandonmentPing"!=c&&(e.message+=" ("+c+")",a.Cb=e,cc(e,"WARNING"))}}}
function Th(a){a.A=!1;if(a.Ja)for(var b in a.i)a.Ja(b,a.i[b]);for(var c in a.sa)J(parseInt(c,10));a.sa={};a.X=null;a.Ja=null;for(var d in a.f)a.f[d]=null;a.f.addEventListener=x(a.pa,a);a.f.removeEventListener=x(a.fe,a);a.f.destroy=x(a.dispose,a);a.f.getLastError=x(a.ld,a);a.f.getPlayerType=x(a.md,a);a.f.getCurrentVideoConfig=x(a.kd,a);a.f.loadNewVideoConfig=x(a.Wb,a);a.f.isReady=x(a.Ee,a)}
g.Ee=function(){return this.A};
g.pa=function(a,b){if(!this.C()){var c=Yh(this,b);if(c){if(!B(this.Yc,a)&&!this.i[a]){var d=fi(this,a);this.X&&this.X(a,d)}this.ca.subscribe(a,c);"onReady"==a&&this.A&&I(na(c,this.f),0)}}};
g.fe=function(a,b){if(!this.C()){var c=Yh(this,b);c&&this.ca.unsubscribe(a,c)}};
function Yh(a,b){var c=b;if("string"==typeof b){if(a.La[b])return a.La[b];c=function(){var a=r(b);a&&a.apply(l,arguments)};
a.La[b]=c}return c?c:null}
function fi(a,b){var c="ytPlayer"+b+a.Y;a.i[b]=c;l[c]=function(c){var e=I(function(){if(!a.C()){a.ca.u(b,c);var f=a.sa,h=String(e);h in f&&delete f[h]}},0);
gb(a.sa,String(e))};
return c}
g.cd=function(a){a=a?te:se;for(var b=a(document.activeElement);b&&(1!=b.nodeType||b==this.l||b==this.G||(b.focus(),b!=document.activeElement));)b=a(b)};
g.ac=function(a){K("a11y-announce",a)};
g.Id=function(a){$h(this,a)};
g.Jd=function(a){K("WATCH_LATER_VIDEO_ADDED",a)};
g.Kd=function(a){K("WATCH_LATER_VIDEO_REMOVED",a)};
g.Fd=function(){this.Va||(nf?(this.Ua=pe(document),kg(this.K,window,"scroll",this.$d),kg(this.K,this.j,"touchmove",this.Ud)):(kg(this.K,this.j,"mousewheel",this.qc),kg(this.K,this.j,"wheel",this.qc)),this.Va=!0)};
g.Gd=function(){lg(this.K);this.Va=!1};
g.qc=function(a){a=a||window.event;a.returnValue=!1;a.preventDefault&&a.preventDefault()};
g.$d=function(){window.scrollTo(this.Ua.H,this.Ua.I)};
g.Ud=function(a){a.preventDefault()};
g.md=function(){return this.F||ci(this)};
g.ld=function(){return this.Cb};
function ci(a){return(a=ai(a))?"div"==a.tagName.toLowerCase()?"html5":"flash":null}
function di(a){Fh("dcp");a.cancel();Th(a);a.F=null;a.b&&(a.b.loaded=!1);var b=ai(a);"html5"==ci(a)?a.N=b:b&&b.destroy&&b.destroy();re(a.j);lg(a.oa);a.l=null;a.G=null}
g.cancel=function(){this.O&&xc(this.b.assets.js,this.O);J(this.$b);this.la=!1};
g.w=function(){di(this);if(this.N&&this.b)try{this.N.destroy()}catch(b){cc(b)}this.La=null;for(var a in this.i)l[this.i[a]]=null;this.Wa=this.b=this.f=null;delete this.j;delete this.o;Sh.B.w.call(this)};var gi={},hi="player_uid_"+(1E9*Math.random()>>>0);function ii(a,b){a=w(a)?me(a):a;b=Jf(b);var c=hi+"_"+ia(a),d=gi[c];if(d)return d.Wb(b),d.f;d=new Sh(a,c);gi[c]=d;K("player-added",d.f);Vb(d,na(ji,d));I(function(){d.Wb(b)},0);
return d.f}
function ji(a){gi[a.Y]=null}
function ki(a){a=me(a);if(!a)return null;var b=hi+"_"+ia(a),c=gi[b];c||(c=new Sh(a,b),gi[b]=c);return c.f}
;var li=r("yt.abuse.botguardInitialized")||Ac;q("yt.abuse.botguardInitialized",li,void 0);var mi=r("yt.abuse.invokeBotguard")||Bc;q("yt.abuse.invokeBotguard",mi,void 0);var ni=r("yt.abuse.dclkstatus.checkDclkStatus")||ae;q("yt.abuse.dclkstatus.checkDclkStatus",ni,void 0);var oi=r("yt.player.exports.navigate")||Ef;q("yt.player.exports.navigate",oi,void 0);var pi=r("yt.player.embed")||ii;q("yt.player.embed",pi,void 0);var qi=r("yt.player.getPlayerByElement")||ki;q("yt.player.getPlayerByElement",qi,void 0);
var ri=r("yt.util.activity.init")||Me;q("yt.util.activity.init",ri,void 0);var si=r("yt.util.activity.getTimeSinceActive")||Oe;q("yt.util.activity.getTimeSinceActive",si,void 0);var ti=r("yt.util.activity.setTimestamp")||Ne;q("yt.util.activity.setTimestamp",ti,void 0);function ui(a){R.call(this,1,arguments);this.b=a}
z(ui,R);function vi(a){R.call(this,1,arguments);this.b=a}
z(vi,R);function wi(a,b,c,d){R.call(this,2,arguments);this.g=a;this.b=null===b?null:!!b;this.receivePostUpdates=null===c?null:!!c;this.f=null===d?null:!!d}
z(wi,R);function xi(a,b,c,d,e){R.call(this,2,arguments);this.f=a;this.b=b;this.i=c||null;this.g=d||null;this.source=e||null}
z(xi,R);function yi(a,b,c){R.call(this,1,arguments);this.b=a;this.subscriptionId=b}
z(yi,R);function zi(a,b,c,d,e,f,h){R.call(this,1,arguments);this.f=a;this.subscriptionId=b;this.b=c;this.j=d||null;this.i=e||null;this.g=f||null;this.source=h||null}
z(zi,R);
var Ai=new S("subscription-batch-subscribe",ui),Bi=new S("subscription-batch-unsubscribe",ui),Ci=new S("subscription-pref-email",wi),Di=new S("subscription-subscribe",xi),Ei=new S("subscription-subscribe-loading",vi),Fi=new S("subscription-subscribe-loaded",vi),Gi=new S("subscription-subscribe-success",yi),Hi=new S("subscription-subscribe-external",xi),Ii=new S("subscription-unsubscribe",zi),Ji=new S("subscription-unsubscirbe-loading",vi),Ki=new S("subscription-unsubscribe-loaded",vi),Li=new S("subscription-unsubscribe-success",
vi),Mi=new S("subscription-external-unsubscribe",zi),Ni=new S("subscription-enable-ypc",vi),Oi=new S("subscription-disable-ypc",vi);function Pi(a,b){var c=document.location.protocol+"//"+document.domain+"/post_login";b&&(c=Gd(c,"mode",b));c=Gd("/signin?context=popup","next",c);c=Gd(c,"feature","sub_button");if(c=window.open(c,"loginPopup","width=375,height=440,resizable=yes,scrollbars=yes",!0)){var d=kc("LOGGED_IN",function(b){mc(H("LOGGED_IN_PUBSUB_KEY",void 0));$b("LOGGED_IN",!0);a(b)});
$b("LOGGED_IN_PUBSUB_KEY",d);c.moveTo((screen.width-375)/2,(screen.height-440)/2)}}
q("yt.pubsub.publish",K,void 0);function Qi(){var a=H("PLAYER_CONFIG");return a&&a.args&&void 0!==a.args.authuser?!0:!(!H("SESSION_INDEX")&&!H("LOGGED_IN"))}
;function Ri(){var a=Ge();return a?a:null}
;function Si(a,b){(a=me(a))&&a.style&&(a.style.display=b?"":"none",fe(a,"hid",!b))}
function Ti(a){A(arguments,function(a){!da(a)||a instanceof Element?Si(a,!0):A(a,function(a){Ti(a)})})}
function Ui(a){A(arguments,function(a){!da(a)||a instanceof Element?Si(a,!1):A(a,function(a){Ui(a)})})}
;var Vi={},Wi="ontouchstart"in document;function Xi(a,b,c){var d;switch(a){case "mouseover":case "mouseout":d=3;break;case "mouseenter":case "mouseleave":d=9}return Be(c,function(a){return ce(a,b)},!0,d)}
function Yi(a){var b="mouseover"==a.type&&"mouseenter"in Vi||"mouseout"==a.type&&"mouseleave"in Vi,c=a.type in Vi||b;if("HTML"!=a.target.tagName&&c){if(b){var b="mouseover"==a.type?"mouseenter":"mouseleave",c=Vi[b],d;for(d in c.fa){var e=Xi(b,d,a.target);e&&!Be(a.relatedTarget,function(a){return a==e},!0)&&c.u(d,e,b,a)}}if(b=Vi[a.type])for(d in b.fa)(e=Xi(a.type,d,a.target))&&b.u(d,e,a.type,a)}}
N(document,"blur",Yi,!0);N(document,"change",Yi,!0);N(document,"click",Yi);N(document,"focus",Yi,!0);N(document,"mouseover",Yi);N(document,"mouseout",Yi);N(document,"mousedown",Yi);N(document,"keydown",Yi);N(document,"keyup",Yi);N(document,"keypress",Yi);N(document,"cut",Yi);N(document,"paste",Yi);Wi&&(N(document,"touchstart",Yi),N(document,"touchend",Yi),N(document,"touchcancel",Yi));function Zi(a){this.j=a;this.g={};this.ub=[];this.i=[]}
function U(a,b){return"yt-uix"+(a.j?"-"+a.j:"")+(b?"-"+b:"")}
Zi.prototype.register=u;Zi.prototype.unregister=function(){mc(this.ub);this.ub.length=0;vh(this.i);this.i.length=0};
Zi.prototype.init=t;Zi.prototype.dispose=t;function $i(a,b,c){a.i.push(uh(b,c,a))}
function aj(a,b,c){var d=U(a,void 0),e=x(c,a);b in Vi||(Vi[b]=new G);Vi[b].subscribe(d,e);a.g[c]=e}
function bj(a,b,c){if(b in Vi){var d=Vi[b];d.unsubscribe(U(a,void 0),a.g[c]);0>=d.S()&&(d.dispose(),delete Vi[b])}delete a.g[c]}
function cj(a,b){Db(a,"tooltip-text",b)}
;function dj(){Zi.call(this,"tooltip");this.b=0;this.f={}}
z(dj,Zi);ba(dj);g=dj.prototype;g.register=function(){aj(this,"mouseover",this.rb);aj(this,"mouseout",this.Fa);aj(this,"focus",this.hc);aj(this,"blur",this.cc);aj(this,"click",this.Fa);aj(this,"touchstart",this.Hc);aj(this,"touchend",this.yb);aj(this,"touchcancel",this.yb)};
g.unregister=function(){bj(this,"mouseover",this.rb);bj(this,"mouseout",this.Fa);bj(this,"focus",this.hc);bj(this,"blur",this.cc);bj(this,"click",this.Fa);bj(this,"touchstart",this.Hc);bj(this,"touchend",this.yb);bj(this,"touchcancel",this.yb);this.dispose();dj.B.unregister.call(this)};
g.dispose=function(){for(var a in this.f)this.Fa(this.f[a]);this.f={}};
g.rb=function(a){if(!(this.b&&1E3>y()-this.b)){var b=parseInt(D(a,"tooltip-hide-timer"),10);b&&(Fb(a,"tooltip-hide-timer"),J(b));var b=x(function(){ej(this,a);Fb(a,"tooltip-show-timer")},this),c=parseInt(D(a,"tooltip-show-delay"),10)||0,b=I(b,c);
Db(a,"tooltip-show-timer",b.toString());a.title&&(cj(a,fj(a)),a.title="");b=ia(a).toString();this.f[b]=a}};
g.Fa=function(a){var b=parseInt(D(a,"tooltip-show-timer"),10);b&&(J(b),Fb(a,"tooltip-show-timer"));b=x(function(){if(a){var b=me(gj(this,a));b&&(hj(b),b&&b.parentNode&&b.parentNode.removeChild(b),Fb(a,"content-id"));(b=me(gj(this,a,"arialabel")))&&b.parentNode&&b.parentNode.removeChild(b)}Fb(a,"tooltip-hide-timer")},this);
b=I(b,50);Db(a,"tooltip-hide-timer",b.toString());if(b=D(a,"tooltip-text"))a.title=b;b=ia(a).toString();delete this.f[b]};
g.hc=function(a){this.b=0;this.rb(a)};
g.cc=function(a){this.b=0;this.Fa(a)};
g.Hc=function(a,b,c){c.changedTouches&&(this.b=0,a=Xi(b,U(this),c.changedTouches[0].target),this.rb(a))};
g.yb=function(a,b,c){c.changedTouches&&(this.b=y(),a=Xi(b,U(this),c.changedTouches[0].target),this.Fa(a))};
function ij(a,b){cj(a,b);var c=D(a,"content-id");(c=me(c))&&ue(c,b)}
function fj(a){return D(a,"tooltip-text")||a.title}
function ej(a,b){if(b){var c=fj(b);if(c){var d=me(gj(a,b));if(!d){d=document.createElement("div");d.id=gj(a,b);d.className=U(a,"tip");var e=document.createElement("div");e.className=U(a,"tip-body");var f=document.createElement("div");f.className=U(a,"tip-arrow");var h=document.createElement("div");h.setAttribute("aria-hidden","true");h.className=U(a,"tip-content");var k=jj(a,b),m=gj(a,b,"content");h.id=m;Db(b,"content-id",m);e.appendChild(h);k&&d.appendChild(k);d.appendChild(e);d.appendChild(f);var n=
xe(b),m=gj(a,b,"arialabel"),f=document.createElement("div");de(f,U(a,"arialabel"));f.id=m;n=b.hasAttribute("aria-label")?b.getAttribute("aria-label"):"rtl"==document.body.getAttribute("dir")?c+" "+n:n+" "+c;ue(f,n);b.setAttribute("aria-labelledby",m);m=Ri()||document.body;m.appendChild(f);m.appendChild(d);ij(b,c);(c=parseInt(D(b,"tooltip-max-width"),10))&&e.offsetWidth>c&&(e.style.width=c+"px",de(h,U(a,"normal-wrap")));h=ce(b,U(a,"reverse"));kj(a,b,d,e,k,h)||kj(a,b,d,e,k,!h);var ga=U(a,"tip-visible");
I(function(){de(d,ga)},0)}}}}
function kj(a,b,c,d,e,f){fe(c,U(a,"tip-reverse"),f);var h=0;f&&(h=1);a=Qf(b);f=new ge((a.width-10)/2,f?a.height:0);var k=le(b),m=new ge(0,0),n;n=k?le(k):document;n=!L||kd(9)||qe(je(n).b)?n.documentElement:n.body;b!=n&&(n=Of(b),k=pe(je(k).b),m.H=n.left+k.H,m.I=n.top+k.I);f=new ge(m.H+f.H,m.I+f.I);f=f.clone();m=(h&8&&"rtl"==Nf(c,"direction")?h^4:h)&-9;h=Qf(c);k=h.clone();n=f.clone();k=k.clone();0!=m&&(m&4?n.H-=k.width+0:m&2&&(n.H-=k.width/2),m&1&&(n.I-=k.height+0));f=new Lf(0,0,0,0);f.left=n.H;f.top=
n.I;f.width=k.width;f.height=k.height;k=new ge(f.left,f.top);k instanceof ge?(m=k.H,k=k.I):(m=k,k=void 0);c.style.left=Pf(m,!1);c.style.top=Pf(k,!1);k=new he(f.width,f.height);if(!(h==k||h&&k&&h.width==k.width&&h.height==k.height))if(h=k,m=qe(je(le(c)).b),!L||jd("10")||m&&jd("8"))f=c.style,ad?f.MozBoxSizing="border-box":bd?f.WebkitBoxSizing="border-box":f.boxSizing="border-box",f.width=Math.max(h.width,0)+"px",f.height=Math.max(h.height,0)+"px";else if(f=c.style,m){if(L){m=Tf(c,"paddingLeft");k=Tf(c,
"paddingRight");n=Tf(c,"paddingTop");var ga=Tf(c,"paddingBottom"),m=new Kf(n,k,ga,m)}else m=Mf(c,"paddingLeft"),k=Mf(c,"paddingRight"),n=Mf(c,"paddingTop"),ga=Mf(c,"paddingBottom"),m=new Kf(parseFloat(n),parseFloat(k),parseFloat(ga),parseFloat(m));if(L&&!kd(9)){k=Vf(c,"borderLeft");n=Vf(c,"borderRight");var ga=Vf(c,"borderTop"),Qc=Vf(c,"borderBottom"),k=new Kf(ga,n,Qc,k)}else k=Mf(c,"borderLeftWidth"),n=Mf(c,"borderRightWidth"),ga=Mf(c,"borderTopWidth"),Qc=Mf(c,"borderBottomWidth"),k=new Kf(parseFloat(ga),
parseFloat(n),parseFloat(Qc),parseFloat(k));f.pixelWidth=h.width-k.left-m.left-m.right-k.right;f.pixelHeight=h.height-k.top-m.top-m.bottom-k.bottom}else f.pixelWidth=h.width,f.pixelHeight=h.height;h=window.document;h=qe(h)?h.documentElement:h.body;f=new he(h.clientWidth,h.clientHeight);1==c.nodeType?(c=Of(c),k=new ge(c.left,c.top)):(c=c.changedTouches?c.changedTouches[0]:c,k=new ge(c.clientX,c.clientY));c=Qf(d);n=Math.floor(c.width/2);h=!!(f.height<k.I+a.height);a=!!(k.I<a.height);m=!!(k.H<n);f=!!(f.width<
k.H+n);k=(c.width+3)/-2- -5;b=D(b,"force-tooltip-direction");if("left"==b||m)k=-5;else if("right"==b||f)k=20-c.width-3;b=Math.floor(k)+"px";d.style.left=b;e&&(e.style.left=b,e.style.height=c.height+"px",e.style.width=c.width+"px");return!(h||a)}
function gj(a,b,c){a=U(a);var d=b.__yt_uid_key;d||(d=Ee(),b.__yt_uid_key=d);b=a+d;c&&(b+="-"+c);return b}
function jj(a,b){var c=null;cd&&ce(b,U(a,"masked"))&&((c=me("yt-uix-tooltip-shared-mask"))?(c.parentNode.removeChild(c),Ti(c)):(c=document.createElement("iframe"),c.src='javascript:""',c.id="yt-uix-tooltip-shared-mask",c.className=U(a,"tip-mask")));return c}
function hj(a){var b=me("yt-uix-tooltip-shared-mask"),c=b&&Be(b,function(b){return b==a},!1,2);
b&&c&&(b.parentNode.removeChild(b),Ui(b),document.body.appendChild(b))}
;function lj(){Zi.call(this,"subscription-button")}
z(lj,Zi);ba(lj);lj.prototype.register=function(){aj(this,"click",this.Fb);$i(this,Ei,this.pc);$i(this,Fi,this.oc);$i(this,Gi,this.Sd);$i(this,Ji,this.pc);$i(this,Ki,this.oc);$i(this,Li,this.Yd);$i(this,Ni,this.Ed);$i(this,Oi,this.Dd)};
lj.prototype.unregister=function(){bj(this,"click",this.Fb);lj.B.unregister.call(this)};
var Ae={Xb:"hover-enabled",Qc:"yt-uix-button-subscribe",Rc:"yt-uix-button-subscribed",Ge:"ypc-enabled",Sc:"yt-uix-button-subscription-container",Tc:"yt-subscription-button-disabled-mask-container"},mj={He:"channel-external-id",Uc:"subscriber-count-show-when-subscribed",Vc:"subscriber-count-tooltip",Wc:"subscriber-count-title",Je:"href",Yb:"is-subscribed",Le:"parent-url",Ne:"clicktracking",Xc:"style-type",Zb:"subscription-id",Qe:"target",Zc:"ypc-enabled"};g=lj.prototype;
g.Fb=function(a){var b=D(a,"href"),c=Qi();if(b)a=D(a,"target")||"_self",window.open(b,a);else if(c){var b=D(a,"channel-external-id"),c=D(a,"clicktracking"),d;if(D(a,"ypc-enabled")){d=D(a,"ypc-item-type");var e=D(a,"ypc-item-id");d={itemType:d,itemId:e,subscriptionElement:a}}else d=null;e=D(a,"parent-url");if(D(a,"is-subscribed")){var f=D(a,"subscription-id");T(Ii,new zi(b,f,d,a,c,e))}else T(Di,new xi(b,d,c,e))}else nj(this,a)};
g.pc=function(a){this.Ma(a.b,this.Ec,!0)};
g.oc=function(a){this.Ma(a.b,this.Ec,!1)};
g.Sd=function(a){this.Ma(a.b,this.Fc,!0,a.subscriptionId)};
g.Yd=function(a){this.Ma(a.b,this.Fc,!1)};
g.Ed=function(a){this.Ma(a.b,this.fd)};
g.Dd=function(a){this.Ma(a.b,this.ed)};
g.Fc=function(a,b,c){b?(Db(a,mj.Yb,"true"),c&&Db(a,mj.Zb,c)):(Fb(a,mj.Yb),Fb(a,mj.Zb));oj(a)};
g.Ec=function(a,b){var c;c=ze(a);fe(c,Ae.Tc,b);a.setAttribute("aria-busy",b?"true":"false");a.disabled=b};
function oj(a){var b=D(a,mj.Xc),c=!!D(a,"is-subscribed"),b="-"+b,d=Ae.Rc+b;fe(a,Ae.Qc+b,!c);fe(a,d,c);D(a,mj.Vc)&&!D(a,mj.Uc)&&(b=U(dj.getInstance()),fe(a,b,!c),a.title=c?"":D(a,mj.Wc));c?I(function(){de(a,Ae.Xb)},1E3):ee(a,Ae.Xb)}
g.fd=function(a){var b=!!D(a,"ypc-item-type"),c=!!D(a,"ypc-item-id");!D(a,"ypc-enabled")&&b&&c&&(de(a,"ypc-enabled"),Db(a,mj.Zc,"true"))};
g.ed=function(a){D(a,"ypc-enabled")&&(ee(a,"ypc-enabled"),Fb(a,"ypc-enabled"))};
function pj(a,b){var c=ne(U(a));return Ia(c,function(a){return b==D(a,"channel-external-id")},a)}
g.ad=function(a,b,c){var d=Va(arguments,2);A(a,function(a){b.apply(this,Ra(a,d))},this)};
g.Ma=function(a,b,c){var d=pj(this,a);this.ad.apply(this,Ra([d],Va(arguments,1)))};
function nj(a,b){var c=x(function(a){a.discoverable_subscriptions&&$b("SUBSCRIBE_EMBED_DISCOVERABLE_SUBSCRIPTIONS",a.discoverable_subscriptions);this.Fb(b)},a);
Pi(c,"subscribe")}
;var qj=window.yt&&window.yt.uix&&window.yt.uix.widgets_||{};q("yt.uix.widgets_",qj,void 0);function rj(a,b){this.source=null;this.j=a||null;this.origin="*";this.A=window.document.location.protocol+"//"+window.document.location.hostname;this.o=b;this.g=this.b=this.f=this.channel=this.i=null;N(window,"message",x(this.l,this))}
rj.prototype.l=function(a){var b=this.o||H("POST_MESSAGE_ORIGIN",void 0)||this.A;if("*"!=b&&a.origin!=b)window.console&&window.console.warn("Untrusted origin: "+a.origin);else if(!this.j||a.source==this.j)if(this.source=a.source,this.origin="null"==a.origin?this.origin:a.origin,a=a.data,w(a)){try{a=od(a)}catch(c){return}this.i=a.id;switch(a.event){case "listening":this.b&&(this.b(),this.b=null);break;case "command":this.f&&(this.g&&!B(this.g,a.func)||this.f(a.func,a.args))}}};
rj.prototype.sendMessage=function(a){this.source&&(a.id=this.i,this.channel&&(a.channel=this.channel),a=M(a),this.source.postMessage(a,this.origin))};function sj(a){return(0==a.search("cue")||0==a.search("load"))&&"loadModule"!=a}
function tj(a,b,c){w(a)&&(a={mediaContentUrl:a,startSeconds:b,suggestedQuality:c});b=/\/([ve]|embed)\/([^#?]+)/.exec(a.mediaContentUrl);a.videoId=b&&b[2]?b[2]:null;return uj(a)}
function uj(a,b,c){if(ha(a)){b="endSeconds startSeconds mediaContentUrl suggestedQuality videoId two_stage_token".split(" ");c={};for(var d=0;d<b.length;d++){var e=b[d];a[e]&&(c[e]=a[e])}return c}return{videoId:a,startSeconds:b,suggestedQuality:c}}
function vj(a,b,c,d){if(ha(a)&&!v(a)){b="playlist list listType index startSeconds suggestedQuality".split(" ");c={};for(d=0;d<b.length;d++){var e=b[d];a[e]&&(c[e]=a[e])}return c}c={index:b,startSeconds:c,suggestedQuality:d};w(a)&&16==a.length?c.list="PL"+a:c.playlist=a;return c}
function wj(a){var b=a.video_id||a.videoId;if(w(b)){var c=P("yt-player-two-stage-token")||{},d=P("yt-player-two-stage-token")||{};p(void 0)?d[b]=void 0:delete d[b];O("yt-player-two-stage-token",d,300);(b=c[b])&&(a.two_stage_token=b)}}
;function xj(){var a=window.navigator.userAgent.match(/Chrome\/([0-9]+)/);return a?50<=parseInt(a[1],10):!1}
function yj(a){return document.currentScript&&(-1!=document.currentScript.src.indexOf("?"+a)||-1!=document.currentScript.src.indexOf("&"+a))}
var zj=yj("loadGamesSDK")?"/cast_game_sender.js":"/cast_sender.js",Aj=yj("loadCastFramework");function Bj(){return"function"==typeof window.__onGCastApiAvailable?window.__onGCastApiAvailable:null}
var Cj=["boadgeojelhgndaghljhdicfkmllpafd","dliochdbjfkdbacpmhlcpmleaejidimm","enhhojjnijigcajfphajepfemndkmdlo","fmfcbgogabcbclcofgocippekhfcmgfj"],Dj=["pkedcjkdefgpdelpbcmbmeomcjbeemfm","fjhoaacokmgbjemoflkofnenfaiekifl"],Ej=xj()?Dj.concat(Cj):Cj.concat(Dj);function Fj(a,b){var c=new XMLHttpRequest;c.onreadystatechange=function(){4==c.readyState&&200==c.status&&b(!0)};
c.onerror=function(){b(!1)};
try{c.open("GET",a,!0),c.send()}catch(d){b(!1)}}
function Gj(a){if(a>=Ej.length)Hj();else{var b=Ej[a],c="chrome-extension://"+b+zj;0<=Cj.indexOf(b)?Fj(c,function(d){d?(window.chrome.cast=window.chrome.cast||{},window.chrome.cast.extensionId=b,Ij(c,Hj)):Gj(a+1)}):Ij(c,function(){Gj(a+1)})}}
function Ij(a,b,c){var d=document.createElement("script");d.onerror=b;c&&(d.onload=c);d.src=a;(document.head||document.documentElement).appendChild(d)}
function Hj(){var a=Bj();a&&a(!1,"No cast extension found")}
function Jj(){if(Aj){var a=2,b=Bj(),c=function(){a--;0==a&&b&&b(!0)};
window.__onGCastApiAvailable=c;Ij("//www.gstatic.com/cast/sdk/libs/sender/0.1/cast_framework.js",Hj,c)}}
function Kj(){if(0<=window.navigator.userAgent.indexOf("CriOS")){var a=window.__gCrWeb&&window.__gCrWeb.message&&window.__gCrWeb.message.invokeOnHost;if(a){Jj();a({command:"cast.sender.init"});return}}window.chrome?(Jj(),a=window.navigator.userAgent,0<=a.indexOf("Android")&&0<=a.indexOf("Chrome/")&&window.navigator.presentation?(a=xj()?"50":"",Ij("//www.gstatic.com/eureka/clank"+a+zj,Hj)):Gj(0)):Hj()}
;var Lj=y(),Mj=null,Nj=Array(50),Oj=-1,Pj=!1;function Qj(){var a=Rj;Sj();Mj.push(a);Tj(Mj)}
function Uj(a,b){Sj();var c=Mj,d=Vj(a,String(b));0==c.length?Wj(d):(Tj(c),A(c,function(a){a(d)}))}
function Sj(){Mj||(Mj=r("yt.mdx.remote.debug.handlers_")||[],q("yt.mdx.remote.debug.handlers_",Mj,void 0))}
function Wj(a){var b=(Oj+1)%50;Oj=b;Nj[b]=a;Pj||(Pj=49==b)}
function Tj(a){var b=Nj;if(b[0]){var c=Oj,d=Pj?c:-1;do{var d=(d+1)%50,e=b[d];A(a,function(a){a(e)})}while(d!=c);
Nj=Array(50);Oj=-1;Pj=!1}}
function Vj(a,b){var c=(y()-Lj)/1E3;c.toFixed&&(c=c.toFixed(3));var d=[];d.push("[",c+"s","] ");d.push("[","yt.mdx.remote","] ");d.push(a+": "+b,"\n");return d.join("")}
;function Xj(a){a=a||{};this.name=a.name||"";this.id=a.id||a.screenId||"";this.token=a.token||a.loungeToken||"";this.uuid=a.uuid||a.dialId||""}
function Yj(a,b){return!!b&&(a.id==b||a.uuid==b)}
function Zj(a){return{name:a.name,screenId:a.id,loungeToken:a.token,dialId:a.uuid}}
function ak(a){return new Xj(a)}
function bk(a){return v(a)?Ja(a,ak):[]}
function ck(a){return a?'{name:"'+a.name+'",id:'+a.id.substr(0,6)+"..,token:"+(a.token?".."+a.token.slice(-6):"-")+",uuid:"+(a.uuid?".."+a.uuid.slice(-6):"-")+"}":"null"}
function dk(a){return v(a)?"["+Ja(a,ck).join(",")+"]":"null"}
;var ek={Fe:"atp",Pe:"ska",Me:"que",Ke:"mus",Oe:"sus"};function fk(a){this.i=this.g="";this.b="/api/lounge";this.f=!0;a=a||document.location.href;var b=Number(zd(4,a))||null||"";b&&(this.i=":"+b);this.g=yd(zd(3,a))||"";a=lb;0<=a.search("MSIE")&&(a=a.match(/MSIE ([\d.]+)/)[1],0>Ea(a,"10.0")&&(this.f=!1))}
function gk(a,b,c,d){var e=a.b;if(p(d)?d:a.f)e="https://"+a.g+a.i+a.b;return Hd(e+b,c||{})}
function hk(a,b,c,d,e){a={format:"JSON",method:"POST",context:a,timeout:5E3,withCredentials:!1,aa:na(a.o,d,!0),onError:na(a.j,e),Ga:na(a.l,e)};c&&(a.V=c,a.headers={"Content-Type":"application/x-www-form-urlencoded"});return Qd(b,a)}
fk.prototype.o=function(a,b,c,d){b?a(d):a({text:c.responseText})};
fk.prototype.j=function(a,b){a(Error("Request error: "+b.status))};
fk.prototype.l=function(a){a(Error("request timed out"))};function ik(){return"xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g,function(a){var b=16*Math.random()|0;return("x"==a?b:b&3|8).toString(16)})}
function jk(a,b){return Ma(a,function(a){return a.key==b})}
function kk(a){return Ja(a,function(a){return{key:a.id,name:a.name}})}
function lk(a,b){return Ma(a,function(a){return a||b?!a!=!b?!1:a.id==b.id:!0})}
function mk(a,b){return Ma(a,function(a){return Yj(a,b)})}
;function V(){E.call(this);this.o=new G;Vb(this,na(F,this.o))}
z(V,E);V.prototype.subscribe=function(a,b,c){return this.C()?0:this.o.subscribe(a,b,c)};
V.prototype.unsubscribe=function(a,b,c){return this.C()?!1:this.o.unsubscribe(a,b,c)};
V.prototype.ka=function(a){return this.C()?!1:this.o.ka(a)};
V.prototype.u=function(a,b){return this.C()?!1:this.o.u.apply(this.o,arguments)};function nk(a){V.call(this);this.l=a;this.screens=[]}
z(nk,V);g=nk.prototype;g.Z=function(){return this.screens};
g.contains=function(a){return!!lk(this.screens,a)};
g.get=function(a){return a?mk(this.screens,a):null};
g.start=u;g.jb=u;g.remove=u;g.hb=u;function ok(a,b){var c=a.get(b.uuid)||a.get(b.id);if(c){var d=c.name;c.id=b.id||c.id;c.name=b.name;c.token=b.token;c.uuid=b.uuid||c.uuid;return c.name!=d}a.screens.push(b);return!0}
function pk(a,b){var c=a.screens.length!=b.length;a.screens=Ia(a.screens,function(a){return!!lk(b,a)});
for(var d=0,e=b.length;d<e;d++)c=ok(a,b[d])||c;return c}
function qk(a,b){var c=a.screens.length;a.screens=Ia(a.screens,function(a){return!(a||b?!a!=!b?0:a.id==b.id:1)});
return a.screens.length<c}
g.info=function(a){Uj(this.l,a)};function rk(a,b,c,d){V.call(this);this.A=a;this.l=b;this.i=c;this.j=d;this.g=0;this.b=null;this.f=NaN}
z(rk,V);var sk=[2E3,2E3,1E3,1E3,1E3,2E3,2E3,5E3,5E3,1E4];g=rk.prototype;g.start=function(){!this.b&&isNaN(this.f)&&this.Ac()};
g.stop=function(){this.b&&(this.b.abort(),this.b=null);isNaN(this.f)||(J(this.f),this.f=NaN)};
g.w=function(){this.stop();rk.B.w.call(this)};
g.Ac=function(){this.f=NaN;this.b=Qd(gk(this.A,"/pairing/get_screen"),{method:"POST",V:{pairing_code:this.l},timeout:5E3,aa:x(this.we,this),onError:x(this.ve,this),Ga:x(this.xe,this)})};
g.we=function(a,b){this.b=null;var c=b.screen||{};c.dialId=this.i;c.name=this.j;this.u("pairingComplete",new Xj(c))};
g.ve=function(a){this.b=null;a.status&&404==a.status?this.g>=sk.length?this.u("pairingFailed",Error("DIAL polling timed out")):(a=sk[this.g],this.f=I(x(this.Ac,this),a),this.g++):this.u("pairingFailed",Error("Server error "+a.status))};
g.xe=function(){this.b=null;this.u("pairingFailed",Error("Server not responding"))};function tk(a){this.app=this.name=this.id="";this.type="REMOTE_CONTROL";this.avatar=this.username="";this.capabilities=new Rc;this.experiments=new Rc;this.theme="u";if(a){this.id=a.id||a.name;this.name=a.name;this.app=a.app;this.type=a.type||"REMOTE_CONTROL";this.username=a.user||"";this.avatar=a.userAvatarUri||"";this.theme=a.theme||"u";var b=a.capabilities||"";this.capabilities.clear();Sc(this.capabilities,Ia(b.split(","),na($a,ek)));a=a.experiments||"";this.experiments.clear();Sc(this.experiments,
a.split(","))}}
tk.prototype.equals=function(a){return a?this.id==a.id:!1};var uk;function vk(){var a=wk(),b=xk();B(a,b);if(yk()){var c=a,d;d=0;for(var e=c.length,f;d<e;){var h=d+e>>1,k;k=Wa(b,c[h]);0<k?d=h+1:(e=h,f=!k)}d=f?d:~d;0>d&&Ua(c,-(d+1),0,b)}a=zk(a);if(0==a.length)try{Cf.remove("remote_sid","/","youtube.com")}catch(m){}else try{Df("remote_sid",a.join(","),-1)}catch(m){}}
function wk(){var a=P("yt-remote-connected-devices")||[];a.sort(Wa);return a}
function zk(a){if(0==a.length)return[];var b=a[0].indexOf("#"),c=-1==b?a[0]:a[0].substring(0,b);return Ja(a,function(a,b){return 0==b?a:a.substring(c.length)})}
function Ak(a){O("yt-remote-connected-devices",a,86400)}
function xk(){if(Bk)return Bk;var a=P("yt-remote-device-id");a||(a=ik(),O("yt-remote-device-id",a,31536E3));for(var b=wk(),c=1,d=a;B(b,d);)c++,d=a+"#"+c;return Bk=d}
function Ck(){return P("yt-remote-session-browser-channel")}
function yk(){return P("yt-remote-session-screen-id")}
function Dk(a){5<a.length&&(a=a.slice(a.length-5));var b=Ja(Ek(),function(a){return a.loungeToken}),c=Ja(a,function(a){return a.loungeToken});
La(c,function(a){return!B(b,a)})&&Fk();
O("yt-remote-local-screens",a,31536E3)}
function Ek(){return P("yt-remote-local-screens")||[]}
function Fk(){O("yt-remote-lounge-token-expiration",!0,86400)}
function Gk(a,b){O("yt-remote-session-browser-channel",a);O("yt-remote-session-screen-id",b);var c=wk(),d=xk();B(c,d)||c.push(d);Ak(c);vk()}
function Hk(a){a||(ff("yt-remote-session-screen-id"),ff("yt-remote-session-video-id"));vk();a=wk();Pa(a,xk());Ak(a)}
function Ik(){if(!uk){var a;a=new Se;(a=a.isAvailable()?a:null)&&(uk=new Ue(a))}return uk?!!uk.get("yt-remote-use-staging-server"):!1}
var Bk="";function Jk(a){nk.call(this,"LocalScreenService");this.f=a;this.b=NaN;Kk(this);this.info("Initializing with "+dk(this.screens))}
z(Jk,nk);g=Jk.prototype;g.start=function(){Kk(this)&&this.u("screenChange");!P("yt-remote-lounge-token-expiration")&&Lk(this);J(this.b);this.b=I(x(this.start,this),1E4)};
g.jb=function(a,b){Kk(this);ok(this,a);Mk(this,!1);this.u("screenChange");b(a);a.token||Lk(this)};
g.remove=function(a,b){var c=Kk(this);qk(this,a)&&(Mk(this,!1),c=!0);b(a);c&&this.u("screenChange")};
g.hb=function(a,b,c,d){var e=Kk(this),f=this.get(a.id);f?(f.name!=b&&(f.name=b,Mk(this,!1),e=!0),c(a)):d(Error("no such local screen."));e&&this.u("screenChange")};
g.w=function(){J(this.b);Jk.B.w.call(this)};
function Lk(a){if(a.screens.length){var b=Ja(a.screens,function(a){return a.id}),c=gk(a.f,"/pairing/get_lounge_token_batch");
hk(a.f,c,{screen_ids:b.join(",")},x(a.pd,a),x(a.od,a))}}
g.pd=function(a){Kk(this);var b=this.screens.length;a=a&&a.screens||[];for(var c=0,d=a.length;c<d;++c){var e=a[c],f=this.get(e.screenId);f&&(f.token=e.loungeToken,--b)}Mk(this,!b);b&&Uj(this.l,"Missed "+b+" lounge tokens.")};
g.od=function(a){Uj(this.l,"Requesting lounge tokens failed: "+a)};
function Kk(a){var b=bk(Ek()),b=Ia(b,function(a){return!a.uuid});
return pk(a,b)}
function Mk(a,b){Dk(Ja(a.screens,Zj));b&&Fk()}
;function Nk(a,b){V.call(this);this.l=b;for(var c=P("yt-remote-online-screen-ids")||"",c=c?c.split(","):[],d={},e=this.l(),f=0,h=e.length;f<h;++f){var k=e[f].id;d[k]=B(c,k)}this.b=d;this.j=a;this.g=this.i=NaN;this.f=null;Ok("Initialized with "+M(this.b))}
z(Nk,V);g=Nk.prototype;g.start=function(){var a=parseInt(P("yt-remote-fast-check-period")||"0",10);(this.i=y()-144E5<a?0:a)?Pk(this):(this.i=y()+3E5,O("yt-remote-fast-check-period",this.i),this.Sb())};
g.isEmpty=function(){return fb(this.b)};
g.update=function(){Ok("Updating availability on schedule.");var a=this.l(),b=Ya(this.b,function(b,d){return b&&!!mk(a,d)},this);
Qk(this,b)};
function Rk(a,b,c){var d=gk(a.j,"/pairing/get_screen_availability");hk(a.j,d,{lounge_token:b.token},x(function(a){a=a.screens||[];for(var d=0,h=a.length;d<h;++d)if(a[d].loungeToken==b.token){c("online"==a[d].status);return}c(!1)},a),x(function(){c(!1)},a))}
g.w=function(){J(this.g);this.g=NaN;this.f&&(this.f.abort(),this.f=null);Nk.B.w.call(this)};
function Qk(a,b){var c;a:if(Za(b)!=Za(a.b))c=!1;else{c=cb(b);for(var d=0,e=c.length;d<e;++d)if(!a.b[c[d]]){c=!1;break a}c=!0}c||(Ok("Updated online screens: "+M(a.b)),a.b=b,a.u("screenChange"));Sk(a)}
function Pk(a){isNaN(a.g)||J(a.g);a.g=I(x(a.Sb,a),0<a.i&&a.i<y()?2E4:1E4)}
g.Sb=function(){J(this.g);this.g=NaN;this.f&&this.f.abort();var a=Tk(this);if(Za(a)){var b=gk(this.j,"/pairing/get_screen_availability");this.f=hk(this.j,b,{lounge_token:cb(a).join(",")},x(this.Qd,this,a),x(this.Pd,this))}else Qk(this,{}),Pk(this)};
g.Qd=function(a,b){this.f=null;var c;a:{c=cb(Tk(this));var d=cb(a);if(da(c)&&da(d)&&c.length==d.length){for(var e=c.length,f=0;f<e;f++)if(c[f]!==d[f]){c=!1;break a}c=!0}else c=!1}if(c){c=b.screens||[];d={};e=0;for(f=c.length;e<f;++e)d[a[c[e].loungeToken]]="online"==c[e].status;Qk(this,d);Pk(this)}else this.L("Changing Screen set during request."),this.Sb()};
g.Pd=function(a){this.L("Screen availability failed: "+a);this.f=null;Pk(this)};
function Ok(a){Uj("OnlineScreenService",a)}
g.L=function(a){Uj("OnlineScreenService",a)};
function Tk(a){var b={};A(a.l(),function(a){a.token?b[a.token]=a.id:this.L("Requesting availability of screen w/o lounge token.")});
return b}
function Sk(a){a=cb(Ya(a.b,function(a){return a}));
a.sort(Wa);a.length?O("yt-remote-online-screen-ids",a.join(","),60):ff("yt-remote-online-screen-ids")}
;function W(a){nk.call(this,"ScreenService");this.j=a;this.b=this.f=null;this.g=[];this.i={};Uk(this)}
z(W,nk);g=W.prototype;g.start=function(){this.f.start();this.b.start();this.screens.length&&(this.u("screenChange"),this.b.isEmpty()||this.u("onlineScreenChange"))};
g.jb=function(a,b,c){this.f.jb(a,b,c)};
g.remove=function(a,b,c){this.f.remove(a,b,c);this.b.update()};
g.hb=function(a,b,c,d){this.f.contains(a)?this.f.hb(a,b,c,d):(a="Updating name of unknown screen: "+a.name,Uj(this.l,a),d(Error(a)))};
g.Z=function(a){return a?this.screens:Ra(this.screens,Ia(this.g,function(a){return!this.contains(a)},this))};
g.Jc=function(){return Ia(this.Z(!0),function(a){return!!this.b.b[a.id]},this)};
function Vk(a,b,c,d,e,f){a.info("getAutomaticScreenByIds "+c+" / "+b);c||(c=a.i[b]);var h=a.Z();if(h=(c?mk(h,c):null)||mk(h,b)){h.uuid=b;var k=Wk(a,h);Rk(a.b,k,function(a){e(a?k:null)})}else c?Xk(a,c,x(function(a){var f=Wk(this,new Xj({name:d,
screenId:c,loungeToken:a,dialId:b||""}));Rk(this.b,f,function(a){e(a?f:null)})},a),f):e(null)}
g.Kc=function(a,b,c,d,e){this.info("getDialScreenByPairingCode "+a+" / "+b);var f=new rk(this.j,a,b,c);f.subscribe("pairingComplete",x(function(a){F(f);d(Wk(this,a))},this));
f.subscribe("pairingFailed",function(a){F(f);e(a)});
f.start();return x(f.stop,f)};
function Yk(a,b){for(var c=0,d=a.screens.length;c<d;++c)if(a.screens[c].name==b)return a.screens[c];return null}
g.ze=function(a,b,c,d){Qd(gk(this.j,"/pairing/get_screen"),{method:"POST",V:{pairing_code:a},timeout:5E3,aa:x(function(a,d){var h=new Xj(d.screen||{});if(!h.name||Yk(this,h.name)){var k;a:{k=h.name;for(var m=2,n=b(k,m);Yk(this,n);){m++;if(20<m)break a;n=b(k,m)}k=n}h.name=k}c(Wk(this,h))},this),
onError:x(function(a){d(Error("pairing request failed: "+a.status))},this),
Ga:x(function(){d(Error("pairing request timed out."))},this)})};
g.w=function(){F(this.f);F(this.b);W.B.w.call(this)};
function Xk(a,b,c,d){a.info("requestLoungeToken_ for "+b);var e={V:{screen_ids:b},method:"POST",context:a,aa:function(a,e){var k=e&&e.screens||[];k[0]&&k[0].screenId==b?c(k[0].loungeToken):d(Error("Missing lounge token in token response"))},
onError:function(){d(Error("Request screen lounge token failed"))}};
Qd(gk(a.j,"/pairing/get_lounge_token_batch"),e)}
function Zk(a){a.screens=a.f.Z();var b=a.i,c={},d;for(d in b)c[b[d]]=d;b=0;for(d=a.screens.length;b<d;++b){var e=a.screens[b];e.uuid=c[e.id]||""}a.info("Updated manual screens: "+dk(a.screens))}
g.qd=function(){Zk(this);this.u("screenChange");this.b.update()};
function Uk(a){$k(a);a.f=new Jk(a.j);a.f.subscribe("screenChange",x(a.qd,a));Zk(a);a.g=bk(P("yt-remote-automatic-screen-cache")||[]);$k(a);a.info("Initializing automatic screens: "+dk(a.g));a.b=new Nk(a.j,x(a.Z,a,!0));a.b.subscribe("screenChange",x(function(){this.u("onlineScreenChange")},a))}
function Wk(a,b){var c=a.get(b.id);c?(c.uuid=b.uuid,b=c):((c=mk(a.g,b.uuid))?(c.id=b.id,c.token=b.token,b=c):a.g.push(b),O("yt-remote-automatic-screen-cache",Ja(a.g,Zj)));$k(a);a.i[b.uuid]=b.id;O("yt-remote-device-id-map",a.i,31536E3);return b}
function $k(a){a.i=P("yt-remote-device-id-map")||{}}
W.prototype.dispose=W.prototype.dispose;function al(a,b,c){V.call(this);this.O=c;this.G=a;this.b=b;this.g=null}
z(al,V);g=al.prototype;g.tb=function(a){this.g=a;this.u("sessionScreen",this.g)};
g.$=function(a){this.C()||(a&&bl(this,""+a),this.g=null,this.u("sessionScreen",null))};
g.info=function(a){Uj(this.O,a)};
function bl(a,b){Uj(a.O,b)}
g.Ea=u;g.xb=u;g.Mc=function(){return null};
g.stop=u;g.Tb=function(a){var b=this.b;a?(b.displayStatus=new chrome.cast.ReceiverDisplayStatus(a,[]),b.displayStatus.showStop=!0):b.displayStatus=null;chrome.cast.setReceiverDisplayStatus(b,x(function(){this.info("Updated receiver status for "+b.friendlyName+": "+a)},this),x(function(){bl(this,"Failed to update receiver status for: "+b.friendlyName)},this))};
g.w=function(){this.Tb("");al.B.w.call(this)};function cl(a,b){al.call(this,a,b,"CastSession");this.f=null;this.i=0;this.l=x(this.Ae,this);this.j=x(this.ae,this);this.i=I(x(function(){dl(this,null)},this),12E4)}
z(cl,al);g=cl.prototype;g.xb=function(a){if(this.f){if(this.f==a)return;bl(this,"Overriding cast sesison with new session object");this.f.removeUpdateListener(this.l);this.f.removeMessageListener("urn:x-cast:com.google.youtube.mdx",this.j)}this.f=a;this.f.addUpdateListener(this.l);this.f.addMessageListener("urn:x-cast:com.google.youtube.mdx",this.j);el(this)};
g.Ea=function(a){this.info("launchWithParams no-op for Cast: "+M(a))};
g.stop=function(){this.f?this.f.stop(x(function(){this.$()},this),x(function(){this.$(Error("Failed to stop receiver app."))},this)):this.$(Error("Stopping cast device witout session."))};
g.Tb=t;g.w=function(){this.info("disposeInternal");J(this.i);this.i=0;this.f&&(this.f.removeUpdateListener(this.l),this.f.removeMessageListener("urn:x-cast:com.google.youtube.mdx",this.j));this.f=null;cl.B.w.call(this)};
function el(a){a.info("sendYoutubeMessage_: getMdxSessionStatus "+M(void 0));var b={type:"getMdxSessionStatus"};a.f?a.f.sendMessage("urn:x-cast:com.google.youtube.mdx",b,t,x(function(){bl(this,"Failed to send message: getMdxSessionStatus.")},a)):bl(a,"Sending yt message without session: "+M(b))}
g.ae=function(a,b){if(!this.C())if(b){var c=pd(b);if(c){var d=""+c.type,c=c.data||{};this.info("onYoutubeMessage_: "+d+" "+M(c));switch(d){case "mdxSessionStatus":dl(this,c.screenId);break;default:bl(this,"Unknown youtube message: "+d)}}else bl(this,"Unable to parse message.")}else bl(this,"No data in message.")};
function dl(a,b){J(a.i);if(b){if(a.info("onConnectedScreenId_: Received screenId: "+b),!a.g||a.g.id!=b){var c=x(a.tb,a),d=x(a.$,a);a.jc(b,c,d,5)}}else a.$(Error("Waiting for session status timed out."))}
g.jc=function(a,b,c,d){Vk(this.G,this.b.label,a,this.b.friendlyName,x(function(e){e?b(e):0<=d?(bl(this,"Screen "+a+" appears to be offline. "+d+" retries left."),I(x(this.jc,this,a,b,c,d-1),300)):c(Error("Unable to fetch screen."))},this),c)};
g.Mc=function(){return this.f};
g.Ae=function(a){this.C()||a||(bl(this,"Cast session died."),this.$())};function fl(a,b){al.call(this,a,b,"DialSession");this.i=this.F=null;this.K="";this.j=null;this.A=t;this.l=NaN;this.N=x(this.De,this);this.f=t}
z(fl,al);g=fl.prototype;g.xb=function(a){this.i=a;this.i.addUpdateListener(this.N)};
g.Ea=function(a){this.j=a;this.A()};
g.stop=function(){this.f();this.f=t;J(this.l);this.i?this.i.stop(x(this.$,this,null),x(this.$,this,"Failed to stop DIAL device.")):this.$()};
g.w=function(){this.f();this.f=t;J(this.l);this.i&&this.i.removeUpdateListener(this.N);this.i=null;fl.B.w.call(this)};
function gl(a){a.f=a.G.Kc(a.K,a.b.label,a.b.friendlyName,x(function(a){this.f=t;this.tb(a)},a),x(function(a){this.f=t;
this.$(a)},a))}
g.De=function(a){this.C()||a||(bl(this,"DIAL session died."),this.f(),this.f=t,this.$())};
function hl(a){var b={};b.pairingCode=a.K;if(a.j){var c=a.j.currentTime||0;b.v=a.j.videoId;b.t=c}Ik()&&(b.env_useStageMdx=1);return Fd(b)}
g.Pb=function(a){this.K=ik();if(this.j){var b=new chrome.cast.DialLaunchResponse(!0,hl(this));a(b);gl(this)}else this.A=x(function(){J(this.l);this.A=t;this.l=NaN;var b=new chrome.cast.DialLaunchResponse(!0,hl(this));a(b);gl(this)},this),this.l=I(x(function(){this.A()},this),100)};
g.rd=function(a,b){Vk(this.G,this.F.receiver.label,a,this.b.friendlyName,x(function(a){a&&a.token?(this.tb(a),b(new chrome.cast.DialLaunchResponse(!1))):this.Pb(b)},this),x(function(a){bl(this,"Failed to get DIAL screen: "+a);
this.Pb(b)},this))};function il(a,b){al.call(this,a,b,"ManualSession");this.f=I(x(this.Ea,this,null),150)}
z(il,al);il.prototype.stop=function(){this.$()};
il.prototype.xb=t;il.prototype.Ea=function(){J(this.f);this.f=NaN;var a=mk(this.G.Z(),this.b.label);a?this.tb(a):this.$(Error("No such screen"))};
il.prototype.w=function(){J(this.f);this.f=NaN;il.B.w.call(this)};function X(a){V.call(this);this.f=a;this.b=null;this.j=!1;this.g=[];this.i=x(this.Nd,this)}
z(X,V);g=X.prototype;
g.init=function(a,b){chrome.cast.timeout.requestSession=3E4;var c=new chrome.cast.SessionRequest("233637DE");c.dialRequest=new chrome.cast.DialRequest("YouTube");var d=chrome.cast.AutoJoinPolicy.TAB_AND_ORIGIN_SCOPED,e=a?chrome.cast.DefaultActionPolicy.CAST_THIS_TAB:chrome.cast.DefaultActionPolicy.CREATE_SESSION,c=new chrome.cast.ApiConfig(c,x(this.uc,this),x(this.Od,this),d,e);c.customDialLaunchCallback=x(this.Cd,this);chrome.cast.initialize(c,x(function(){this.C()||(chrome.cast.addReceiverActionListener(this.i),Qj(),
this.f.subscribe("onlineScreenChange",x(this.Lc,this)),this.g=jl(this),chrome.cast.setCustomReceivers(this.g,t,x(function(a){this.L("Failed to set initial custom receivers: "+M(a))},this)),this.u("yt-remote-cast2-availability-change",kl(this)),b(!0))},this),x(function(a){this.L("Failed to initialize API: "+M(a));
b(!1)},this))};
g.oe=function(a,b){ll("Setting connected screen ID: "+a+" -> "+b);if(this.b){var c=this.b.g;if(!a||c&&c.id!=a)ll("Unsetting old screen status: "+this.b.b.friendlyName),F(this.b),this.b=null}if(a&&b){if(!this.b){c=mk(this.f.Z(),a);if(!c){ll("setConnectedScreenStatus: Unknown screen.");return}var d=ml(this,c);d||(ll("setConnectedScreenStatus: Connected receiver not custom..."),d=new chrome.cast.Receiver(c.uuid?c.uuid:c.id,c.name),d.receiverType=chrome.cast.ReceiverType.CUSTOM,this.g.push(d),chrome.cast.setCustomReceivers(this.g,
t,x(function(a){this.L("Failed to set initial custom receivers: "+M(a))},this)));
ll("setConnectedScreenStatus: new active receiver: "+d.friendlyName);nl(this,new il(this.f,d),!0)}this.b.Tb(b)}else ll("setConnectedScreenStatus: no screen.")};
function ml(a,b){return b?Ma(a.g,function(a){return Yj(b,a.label)},a):null}
g.pe=function(a){this.C()?this.L("Setting connection data on disposed cast v2"):this.b?this.b.Ea(a):this.L("Setting connection data without a session")};
g.Ce=function(){this.C()?this.L("Stopping session on disposed cast v2"):this.b?(this.b.stop(),F(this.b),this.b=null):ll("Stopping non-existing session")};
g.requestSession=function(){chrome.cast.requestSession(x(this.uc,this),x(this.Rd,this))};
g.w=function(){this.f.unsubscribe("onlineScreenChange",x(this.Lc,this));window.chrome&&chrome.cast&&chrome.cast.removeReceiverActionListener(this.i);var a=Rj,b=r("yt.mdx.remote.debug.handlers_");Pa(b||[],a);F(this.b);X.B.w.call(this)};
function ll(a){Uj("Controller",a)}
g.L=function(a){Uj("Controller",a)};
function Rj(a){window.chrome&&chrome.cast&&chrome.cast.logMessage&&chrome.cast.logMessage(a)}
function kl(a){return a.j||!!a.g.length||!!a.b}
function nl(a,b,c){F(a.b);(a.b=b)?(c?a.u("yt-remote-cast2-receiver-resumed",b.b):a.u("yt-remote-cast2-receiver-selected",b.b),b.subscribe("sessionScreen",x(a.wc,a,b)),b.g?a.u("yt-remote-cast2-session-change",b.g):c&&a.b.Ea(null)):a.u("yt-remote-cast2-session-change",null)}
g.wc=function(a,b){this.b==a&&(b||nl(this,null),this.u("yt-remote-cast2-session-change",b))};
g.Nd=function(a,b){if(!this.C())if(a)switch(ll("onReceiverAction_ "+a.label+" / "+a.friendlyName+"-- "+b),b){case chrome.cast.ReceiverAction.CAST:if(this.b)if(this.b.b.label!=a.label)ll("onReceiverAction_: Stopping active receiver: "+this.b.b.friendlyName),this.b.stop();else{ll("onReceiverAction_: Casting to active receiver.");this.b.g&&this.u("yt-remote-cast2-session-change",this.b.g);break}switch(a.receiverType){case chrome.cast.ReceiverType.CUSTOM:nl(this,new il(this.f,a));break;case chrome.cast.ReceiverType.DIAL:nl(this,
new fl(this.f,a));break;case chrome.cast.ReceiverType.CAST:nl(this,new cl(this.f,a));break;default:this.L("Unknown receiver type: "+a.receiverType)}break;case chrome.cast.ReceiverAction.STOP:this.b&&this.b.b.label==a.label?this.b.stop():this.L("Stopping receiver w/o session: "+a.friendlyName)}else this.L("onReceiverAction_ called without receiver.")};
g.Cd=function(a){if(this.C())return Promise.reject(Error("disposed"));var b=a.receiver;b.receiverType!=chrome.cast.ReceiverType.DIAL&&(this.L("Not DIAL receiver: "+b.friendlyName),b.receiverType=chrome.cast.ReceiverType.DIAL);var c=this.b?this.b.b:null;if(!c||c.label!=b.label)return this.L("Receiving DIAL launch request for non-clicked DIAL receiver: "+b.friendlyName),Promise.reject(Error("illegal DIAL launch"));if(c&&c.label==b.label&&c.receiverType!=chrome.cast.ReceiverType.DIAL){if(this.b.g)return ll("Reselecting dial screen."),
this.u("yt-remote-cast2-session-change",this.b.g),Promise.resolve(new chrome.cast.DialLaunchResponse(!1));this.L('Changing CAST intent from "'+c.receiverType+'" to "dial" for '+b.friendlyName);nl(this,new fl(this.f,b))}b=this.b;b.F=a;return b.F.appState==chrome.cast.DialAppState.RUNNING?new Promise(x(b.rd,b,(b.F.extraData||{}).screenId||null)):new Promise(x(b.Pb,b))};
g.uc=function(a){if(!this.C()){ll("New cast session ID: "+a.sessionId);var b=a.receiver;if(b.receiverType!=chrome.cast.ReceiverType.CUSTOM){if(!this.b)if(b.receiverType==chrome.cast.ReceiverType.CAST)ll("Got resumed cast session before resumed mdx connection."),nl(this,new cl(this.f,b),!0);else{this.L("Got non-cast session without previous mdx receiver event, or mdx resume.");return}var c=this.b.b,d=mk(this.f.Z(),c.label);d&&Yj(d,b.label)&&c.receiverType!=chrome.cast.ReceiverType.CAST&&b.receiverType==
chrome.cast.ReceiverType.CAST&&(ll("onSessionEstablished_: manual to cast session change "+b.friendlyName),F(this.b),this.b=new cl(this.f,b),this.b.subscribe("sessionScreen",x(this.wc,this,this.b)),this.b.Ea(null));this.b.xb(a)}}};
g.Be=function(){return this.b?this.b.Mc():null};
g.Rd=function(a){this.C()||(this.L("Failed to estabilish a session: "+M(a)),a.code!=chrome.cast.ErrorCode.CANCEL&&nl(this,null))};
g.Od=function(a){ll("Receiver availability updated: "+a);if(!this.C()){var b=kl(this);this.j=a==chrome.cast.ReceiverAvailability.AVAILABLE;kl(this)!=b&&this.u("yt-remote-cast2-availability-change",kl(this))}};
function jl(a){var b=a.f.Jc(),c=a.b&&a.b.b;a=Ja(b,function(a){c&&Yj(a,c.label)&&(c=null);var b=a.uuid?a.uuid:a.id,f=ml(this,a);f?(f.label=b,f.friendlyName=a.name):(f=new chrome.cast.Receiver(b,a.name),f.receiverType=chrome.cast.ReceiverType.CUSTOM);return f},a);
c&&(c.receiverType!=chrome.cast.ReceiverType.CUSTOM&&(c=new chrome.cast.Receiver(c.label,c.friendlyName),c.receiverType=chrome.cast.ReceiverType.CUSTOM),a.push(c));return a}
g.Lc=function(){if(!this.C()){var a=kl(this);this.g=jl(this);ll("Updating custom receivers: "+M(this.g));chrome.cast.setCustomReceivers(this.g,t,x(function(){this.L("Failed to set custom receivers.")},this));
var b=kl(this);b!=a&&this.u("yt-remote-cast2-availability-change",b)}};
X.prototype.setLaunchParams=X.prototype.pe;X.prototype.setConnectedScreenStatus=X.prototype.oe;X.prototype.stopSession=X.prototype.Ce;X.prototype.getCastSession=X.prototype.Be;X.prototype.requestSession=X.prototype.requestSession;X.prototype.init=X.prototype.init;X.prototype.dispose=X.prototype.dispose;function ol(a,b,c){pl()?rl(a)&&(sl(!0),window.chrome&&chrome.cast&&chrome.cast.isAvailable?tl(b):(window.__onGCastApiAvailable=function(a,c){a?tl(b):(ul("Failed to load cast API: "+c),vl(!1),sl(!1),ff("yt-remote-cast-available"),ff("yt-remote-cast-receiver"),wl(),b(!1))},c?qc("https://www.gstatic.com/cv/js/sender/v1/cast_sender.js"):Kj())):ql("Cannot initialize because not running Chrome")}
function wl(){ql("dispose");var a=xl();a&&a.dispose();yl=null;q("yt.mdx.remote.cloudview.instance_",null,void 0);zl(!1);mc(Al);Al.length=0}
function Bl(){return!!P("yt-remote-cast-installed")}
function Cl(){var a=P("yt-remote-cast-receiver");a?(a=a.friendlyName,a=-1!=a.indexOf("&")?"document"in l?za(a):Ba(a):a):a=null;return a}
function Dl(){ql("clearCurrentReciever");ff("yt-remote-cast-receiver")}
function El(){Bl()?xl()?Fl()?(ql("Requesting cast selector."),yl.requestSession()):(ql("Wait for cast API to be ready to request the session."),Al.push(kc("yt-remote-cast2-api-ready",El))):ul("requestCastSelector: Cast is not initialized."):ul("requestCastSelector: Cast API is not installed!")}
function Gl(a){Fl()?xl().setLaunchParams(a):ul("setLaunchParams called before ready.")}
function Hl(a,b){Fl()?xl().setConnectedScreenStatus(a,b):ul("setConnectedScreenStatus called before ready.")}
var yl=null;function pl(){var a;a=0<=lb.search(/\ (CrMo|Chrome|CriOS)\//);return nf||a}
function rl(a){var b=!1;if(!yl){var c=r("yt.mdx.remote.cloudview.instance_");c||(c=new X(a),c.subscribe("yt-remote-cast2-availability-change",function(a){O("yt-remote-cast-available",a);K("yt-remote-cast2-availability-change",a)}),c.subscribe("yt-remote-cast2-receiver-selected",function(a){ql("onReceiverSelected: "+a.friendlyName);
O("yt-remote-cast-receiver",a);K("yt-remote-cast2-receiver-selected",a)}),c.subscribe("yt-remote-cast2-receiver-resumed",function(a){ql("onReceiverResumed: "+a.friendlyName);
O("yt-remote-cast-receiver",a)}),c.subscribe("yt-remote-cast2-session-change",function(a){ql("onSessionChange: "+ck(a));
a||ff("yt-remote-cast-receiver");K("yt-remote-cast2-session-change",a)}),q("yt.mdx.remote.cloudview.instance_",c,void 0),b=!0);
yl=c}ql("cloudview.createSingleton_: "+b);return b}
function xl(){yl||(yl=r("yt.mdx.remote.cloudview.instance_"));return yl}
function tl(a){vl(!0);sl(!1);yl.init(!0,function(b){b?(zl(!0),K("yt-remote-cast2-api-ready")):(ul("Failed to initialize cast API."),vl(!1),ff("yt-remote-cast-available"),ff("yt-remote-cast-receiver"),wl());a(b)})}
function ql(a){Uj("cloudview",a)}
function ul(a){Uj("cloudview",a)}
function vl(a){ql("setCastInstalled_ "+a);O("yt-remote-cast-installed",a)}
function Fl(){return!!r("yt.mdx.remote.cloudview.apiReady_")}
function zl(a){ql("setApiReady_ "+a);q("yt.mdx.remote.cloudview.apiReady_",a,void 0)}
function sl(a){q("yt.mdx.remote.cloudview.initializing_",a,void 0)}
var Al=[];function Il(a,b){this.action=a;this.params=b||null}
;function Jl(){this.b=y()}
new Jl;Jl.prototype.set=function(a){this.b=a};
Jl.prototype.reset=function(){this.set(y())};
Jl.prototype.get=function(){return this.b};function Kl(a,b){this.type=a;this.b=this.target=b;this.defaultPrevented=this.f=!1;this.Cc=!0}
Kl.prototype.stopPropagation=function(){this.f=!0};
Kl.prototype.preventDefault=function(){this.defaultPrevented=!0;this.Cc=!1};var Ll=!L||kd(9),Ml=L&&!jd("9");!bd||jd("528");ad&&jd("1.9b")||L&&jd("8")||Zc&&jd("9.5")||bd&&jd("528");ad&&!jd("8")||L&&jd("9");function Nl(a,b){Kl.call(this,a?a.type:"");this.relatedTarget=this.b=this.target=null;this.charCode=this.keyCode=this.button=this.clientY=this.clientX=0;this.shiftKey=this.altKey=this.ctrlKey=!1;this.g=this.state=null;a&&this.init(a,b)}
z(Nl,Kl);
Nl.prototype.init=function(a,b){var c=this.type=a.type,d=a.changedTouches?a.changedTouches[0]:null;this.target=a.target||a.srcElement;this.b=b;var e=a.relatedTarget;if(e){if(ad){var f;a:{try{Wc(e.nodeName);f=!0;break a}catch(h){}f=!1}f||(e=null)}}else"mouseover"==c?e=a.fromElement:"mouseout"==c&&(e=a.toElement);this.relatedTarget=e;null===d?(this.clientX=void 0!==a.clientX?a.clientX:a.pageX,this.clientY=void 0!==a.clientY?a.clientY:a.pageY):(this.clientX=void 0!==d.clientX?d.clientX:d.pageX,this.clientY=
void 0!==d.clientY?d.clientY:d.pageY);this.button=a.button;this.keyCode=a.keyCode||0;this.charCode=a.charCode||("keypress"==c?a.keyCode:0);this.ctrlKey=a.ctrlKey;this.altKey=a.altKey;this.shiftKey=a.shiftKey;this.state=a.state;this.g=a;a.defaultPrevented&&this.preventDefault()};
Nl.prototype.stopPropagation=function(){Nl.B.stopPropagation.call(this);this.g.stopPropagation?this.g.stopPropagation():this.g.cancelBubble=!0};
Nl.prototype.preventDefault=function(){Nl.B.preventDefault.call(this);var a=this.g;if(a.preventDefault)a.preventDefault();else if(a.returnValue=!1,Ml)try{if(a.ctrlKey||112<=a.keyCode&&123>=a.keyCode)a.keyCode=-1}catch(b){}};var Ol="closure_listenable_"+(1E6*Math.random()|0),Pl=0;function Ql(a,b,c,d,e){this.listener=a;this.b=null;this.src=b;this.type=c;this.lb=!!d;this.pb=e;this.key=++Pl;this.Qa=this.kb=!1}
function Rl(a){a.Qa=!0;a.listener=null;a.b=null;a.src=null;a.pb=null}
;function Sl(a){this.src=a;this.b={};this.f=0}
function Tl(a,b,c,d,e){var f=b.toString();b=a.b[f];b||(b=a.b[f]=[],a.f++);var h=Ul(b,c,d,e);-1<h?(a=b[h],a.kb=!1):(a=new Ql(c,a.src,f,!!d,e),a.kb=!1,b.push(a));return a}
Sl.prototype.remove=function(a,b,c,d){a=a.toString();if(!(a in this.b))return!1;var e=this.b[a];b=Ul(e,b,c,d);return-1<b?(Rl(e[b]),Array.prototype.splice.call(e,b,1),0==e.length&&(delete this.b[a],this.f--),!0):!1};
function Vl(a,b){var c=b.type;c in a.b&&Pa(a.b[c],b)&&(Rl(b),0==a.b[c].length&&(delete a.b[c],a.f--))}
function Wl(a,b,c,d,e){a=a.b[b.toString()];b=-1;a&&(b=Ul(a,c,d,e));return-1<b?a[b]:null}
function Ul(a,b,c,d){for(var e=0;e<a.length;++e){var f=a[e];if(!f.Qa&&f.listener==b&&f.lb==!!c&&f.pb==d)return e}return-1}
;var Xl="closure_lm_"+(1E6*Math.random()|0),Yl={},Zl=0;
function $l(a,b,c,d,e){if(v(b)){for(var f=0;f<b.length;f++)$l(a,b[f],c,d,e);return null}c=am(c);if(a&&a[Ol])a=a.qb(b,c,d,e);else{if(!b)throw Error("Invalid event type");var f=!!d,h=bm(a);h||(a[Xl]=h=new Sl(a));c=Tl(h,b,c,d,e);if(!c.b){d=cm();c.b=d;d.src=a;d.listener=c;if(a.addEventListener)a.addEventListener(b.toString(),d,f);else if(a.attachEvent)a.attachEvent(dm(b.toString()),d);else throw Error("addEventListener and attachEvent are unavailable.");Zl++}a=c}return a}
function cm(){var a=em,b=Ll?function(c){return a.call(b.src,b.listener,c)}:function(c){c=a.call(b.src,b.listener,c);
if(!c)return c};
return b}
function fm(a,b,c,d,e){if(v(b))for(var f=0;f<b.length;f++)fm(a,b[f],c,d,e);else c=am(c),a&&a[Ol]?a.zb(b,c,d,e):a&&(a=bm(a))&&(b=Wl(a,b,c,!!d,e))&&gm(b)}
function gm(a){if(!ea(a)&&a&&!a.Qa){var b=a.src;if(b&&b[Ol])Vl(b.g,a);else{var c=a.type,d=a.b;b.removeEventListener?b.removeEventListener(c,d,a.lb):b.detachEvent&&b.detachEvent(dm(c),d);Zl--;(c=bm(b))?(Vl(c,a),0==c.f&&(c.src=null,b[Xl]=null)):Rl(a)}}}
function dm(a){return a in Yl?Yl[a]:Yl[a]="on"+a}
function hm(a,b,c,d){var e=!0;if(a=bm(a))if(b=a.b[b.toString()])for(b=b.concat(),a=0;a<b.length;a++){var f=b[a];f&&f.lb==c&&!f.Qa&&(f=im(f,d),e=e&&!1!==f)}return e}
function im(a,b){var c=a.listener,d=a.pb||a.src;a.kb&&gm(a);return c.call(d,b)}
function em(a,b){if(a.Qa)return!0;if(!Ll){var c=b||r("window.event"),d=new Nl(c,this),e=!0;if(!(0>c.keyCode||void 0!=c.returnValue)){a:{var f=!1;if(0==c.keyCode)try{c.keyCode=-1;break a}catch(m){f=!0}if(f||void 0==c.returnValue)c.returnValue=!0}c=[];for(f=d.b;f;f=f.parentNode)c.push(f);for(var f=a.type,h=c.length-1;!d.f&&0<=h;h--){d.b=c[h];var k=hm(c[h],f,!0,d),e=e&&k}for(h=0;!d.f&&h<c.length;h++)d.b=c[h],k=hm(c[h],f,!1,d),e=e&&k}return e}return im(a,new Nl(b,this))}
function bm(a){a=a[Xl];return a instanceof Sl?a:null}
var jm="__closure_events_fn_"+(1E9*Math.random()>>>0);function am(a){if(fa(a))return a;a[jm]||(a[jm]=function(b){return a.handleEvent(b)});
return a[jm]}
;function km(){E.call(this);this.g=new Sl(this);this.sa=this;this.Y=null}
z(km,E);km.prototype[Ol]=!0;g=km.prototype;g.addEventListener=function(a,b,c,d){$l(this,a,b,c,d)};
g.removeEventListener=function(a,b,c,d){fm(this,a,b,c,d)};
function lm(a,b){var c,d=a.Y;if(d){c=[];for(var e=1;d;d=d.Y)c.push(d),++e}var d=a.sa,e=b,f=e.type||e;if(w(e))e=new Kl(e,d);else if(e instanceof Kl)e.target=e.target||d;else{var h=e,e=new Kl(f,d);kb(e,h)}var h=!0,k;if(c)for(var m=c.length-1;!e.f&&0<=m;m--)k=e.b=c[m],h=mm(k,f,!0,e)&&h;e.f||(k=e.b=d,h=mm(k,f,!0,e)&&h,e.f||(h=mm(k,f,!1,e)&&h));if(c)for(m=0;!e.f&&m<c.length;m++)k=e.b=c[m],h=mm(k,f,!1,e)&&h}
g.w=function(){km.B.w.call(this);if(this.g){var a=this.g,b=0,c;for(c in a.b){for(var d=a.b[c],e=0;e<d.length;e++)++b,Rl(d[e]);delete a.b[c];a.f--}}this.Y=null};
g.qb=function(a,b,c,d){return Tl(this.g,String(a),b,c,d)};
g.zb=function(a,b,c,d){return this.g.remove(String(a),b,c,d)};
function mm(a,b,c,d){b=a.g.b[String(b)];if(!b)return!0;b=b.concat();for(var e=!0,f=0;f<b.length;++f){var h=b[f];if(h&&!h.Qa&&h.lb==c){var k=h.listener,m=h.pb||h.src;h.kb&&Vl(a.g,h);e=!1!==k.call(m,d)&&e}}return e&&0!=d.Cc}
;function nm(a,b){this.f=new rd(a);this.b=b?pd:od}
nm.prototype.stringify=function(a){return qd(this.f,a)};
nm.prototype.parse=function(a){return this.b(a)};function om(a,b){km.call(this);this.b=a||1;this.f=b||l;this.i=x(this.re,this);this.j=y()}
z(om,km);g=om.prototype;g.enabled=!1;g.da=null;function pm(a,b){a.b=b;a.da&&a.enabled?(a.stop(),a.start()):a.da&&a.stop()}
g.re=function(){if(this.enabled){var a=y()-this.j;0<a&&a<.8*this.b?this.da=this.f.setTimeout(this.i,this.b-a):(this.da&&(this.f.clearTimeout(this.da),this.da=null),lm(this,"tick"),this.enabled&&(this.da=this.f.setTimeout(this.i,this.b),this.j=y()))}};
g.start=function(){this.enabled=!0;this.da||(this.da=this.f.setTimeout(this.i,this.b),this.j=y())};
g.stop=function(){this.enabled=!1;this.da&&(this.f.clearTimeout(this.da),this.da=null)};
g.w=function(){om.B.w.call(this);this.stop();delete this.f};
function qm(a,b,c){if(fa(a))c&&(a=x(a,c));else if(a&&"function"==typeof a.handleEvent)a=x(a.handleEvent,a);else throw Error("Invalid listener argument");return 2147483647<Number(b)?-1:l.setTimeout(a,b||0)}
;function rm(a,b,c){E.call(this);this.i=null!=c?x(a,c):a;this.g=b;this.f=x(this.Td,this);this.b=[]}
z(rm,E);g=rm.prototype;g.Ra=!1;g.eb=0;g.Ba=null;g.hd=function(a){this.b=arguments;this.Ba||this.eb?this.Ra=!0:sm(this)};
g.stop=function(){this.Ba&&(l.clearTimeout(this.Ba),this.Ba=null,this.Ra=!1,this.b=[])};
g.pause=function(){this.eb++};
g.resume=function(){this.eb--;this.eb||!this.Ra||this.Ba||(this.Ra=!1,sm(this))};
g.w=function(){rm.B.w.call(this);this.stop()};
g.Td=function(){this.Ba=null;this.Ra&&!this.eb&&(this.Ra=!1,sm(this))};
function sm(a){a.Ba=qm(a.f,a.g);a.i.apply(null,a.b)}
;function tm(a){E.call(this);this.f=a;this.b={}}
z(tm,E);var um=[];tm.prototype.qb=function(a,b,c,d){v(b)||(b&&(um[0]=b.toString()),b=um);for(var e=0;e<b.length;e++){var f=$l(a,b[e],c||this.handleEvent,d||!1,this.f||this);if(!f)break;this.b[f.key]=f}return this};
tm.prototype.zb=function(a,b,c,d,e){if(v(b))for(var f=0;f<b.length;f++)this.zb(a,b[f],c,d,e);else c=c||this.handleEvent,e=e||this.f||this,c=am(c),d=!!d,b=a&&a[Ol]?Wl(a.g,String(b),c,d,e):a?(a=bm(a))?Wl(a,b,c,d,e):null:null,b&&(gm(b),delete this.b[b.key]);return this};
function vm(a){Xa(a.b,function(a,c){this.b.hasOwnProperty(c)&&gm(a)},a);
a.b={}}
tm.prototype.w=function(){tm.B.w.call(this);vm(this)};
tm.prototype.handleEvent=function(){throw Error("EventHandler.handleEvent not implemented");};function wm(){}
wm.prototype.f=null;wm.prototype.b=u;function xm(a){return a.f||(a.f=a.i())}
wm.prototype.i=u;var ym;function zm(){}
z(zm,wm);zm.prototype.b=function(){var a=Am(this);return a?new ActiveXObject(a):new XMLHttpRequest};
zm.prototype.i=function(){var a={};Am(this)&&(a[0]=!0,a[1]=!0);return a};
function Am(a){if(!a.g&&"undefined"==typeof XMLHttpRequest&&"undefined"!=typeof ActiveXObject){for(var b=["MSXML2.XMLHTTP.6.0","MSXML2.XMLHTTP.3.0","MSXML2.XMLHTTP","Microsoft.XMLHTTP"],c=0;c<b.length;c++){var d=b[c];try{return new ActiveXObject(d),a.g=d}catch(e){}}throw Error("Could not create ActiveXObject. ActiveX might be disabled, or MSXML might not be installed");}return a.g}
ym=new zm;function Bm(a,b,c,d,e){this.b=a;this.g=c;this.A=d;this.l=e||1;this.j=45E3;this.i=new tm(this);this.f=new om;pm(this.f,250)}
g=Bm.prototype;g.Ca=null;g.ia=!1;g.Ta=null;g.Vb=null;g.fb=null;g.Sa=null;g.ta=null;g.wa=null;g.Ha=null;g.M=null;g.ib=0;g.ja=null;g.Bb=null;g.Da=null;g.bb=-1;g.Dc=!0;g.ya=!1;g.Nb=0;g.vb=null;var Cm={},Dm={};g=Bm.prototype;g.setTimeout=function(a){this.j=a};
function Em(a,b,c){a.Sa=1;a.ta=$g(b.clone());a.Ha=c;a.o=!0;Fm(a,null)}
function Gm(a,b,c,d,e){a.Sa=1;a.ta=$g(b.clone());a.Ha=null;a.o=c;e&&(a.Dc=!1);Fm(a,d)}
function Fm(a,b){a.fb=y();Hm(a);a.wa=a.ta.clone();Yg(a.wa,"t",a.l);a.ib=0;a.M=a.b.Gb(a.b.gb()?b:null);0<a.Nb&&(a.vb=new rm(x(a.Ic,a,a.M),a.Nb));a.i.qb(a.M,"readystatechange",a.de);var c=a.Ca?hb(a.Ca):{};a.Ha?(a.Bb="POST",c["Content-Type"]="application/x-www-form-urlencoded",a.M.send(a.wa,a.Bb,a.Ha,c)):(a.Bb="GET",a.Dc&&!bd&&(c.Connection="close"),a.M.send(a.wa,a.Bb,null,c));a.b.ha(1)}
g.de=function(a){a=a.target;var b=this.vb;b&&3==Im(a)?b.hd():this.Ic(a)};
g.Ic=function(a){try{if(a==this.M)a:{var b=Im(this.M),c=this.M.j,d=this.M.getStatus();if(L&&!kd(10)||bd&&!jd("420+")){if(4>b)break a}else if(3>b||3==b&&!Zc&&!Jm(this.M))break a;this.ya||4!=b||7==c||(8==c||0>=d?this.b.ha(3):this.b.ha(2));Km(this);var e=this.M.getStatus();this.bb=e;var f=Jm(this.M);(this.ia=200==e)?(4==b&&Lm(this),this.o?(Mm(this,b,f),Zc&&this.ia&&3==b&&(this.i.qb(this.f,"tick",this.be),this.f.start())):Nm(this,f),this.ia&&!this.ya&&(4==b?this.b.sb(this):(this.ia=!1,Hm(this)))):(this.Da=
400==e&&0<f.indexOf("Unknown SID")?3:0,Y(),Lm(this),Om(this))}}catch(h){}finally{}};
function Mm(a,b,c){for(var d=!0;!a.ya&&a.ib<c.length;){var e=Pm(a,c);if(e==Dm){4==b&&(a.Da=4,Y(),d=!1);break}else if(e==Cm){a.Da=4;Y();d=!1;break}else Nm(a,e)}4==b&&0==c.length&&(a.Da=1,Y(),d=!1);a.ia=a.ia&&d;d||(Lm(a),Om(a))}
g.be=function(){var a=Im(this.M),b=Jm(this.M);this.ib<b.length&&(Km(this),Mm(this,a,b),this.ia&&4!=a&&Hm(this))};
function Pm(a,b){var c=a.ib,d=b.indexOf("\n",c);if(-1==d)return Dm;c=Number(b.substring(c,d));if(isNaN(c))return Cm;d+=1;if(d+c>b.length)return Dm;var e=b.substr(d,c);a.ib=d+c;return e}
function Qm(a,b){a.fb=y();Hm(a);var c=b?window.location.hostname:"";a.wa=a.ta.clone();Q(a.wa,"DOMAIN",c);Q(a.wa,"t",a.l);try{a.ja=new ActiveXObject("htmlfile")}catch(n){Lm(a);a.Da=7;Y();Om(a);return}var d="<html><body>";if(b){for(var e="",f=0;f<c.length;f++){var h=c.charAt(f);if("<"==h)e+="\\x3c";else if(">"==h)e+="\\x3e";else{if(h in Da)h=Da[h];else if(h in Ca)h=Da[h]=Ca[h];else{var k,m=h.charCodeAt(0);if(31<m&&127>m)k=h;else{if(256>m){if(k="\\x",16>m||256<m)k+="0"}else k="\\u",4096>m&&(k+="0");
k+=m.toString(16).toUpperCase()}h=Da[h]=k}e+=h}}d+='<script>document.domain="'+e+'"\x3c/script>'}c=Ab(d+"</body></html>");a.ja.open();a.ja.write(zb(c));a.ja.close();a.ja.parentWindow.m=x(a.Xd,a);a.ja.parentWindow.d=x(a.zc,a,!0);a.ja.parentWindow.rpcClose=x(a.zc,a,!1);c=a.ja.createElement("DIV");a.ja.parentWindow.document.body.appendChild(c);d=tb(a.wa.toString());d=rb(d);ya.test(d)&&(-1!=d.indexOf("&")&&(d=d.replace(sa,"&amp;")),-1!=d.indexOf("<")&&(d=d.replace(ta,"&lt;")),-1!=d.indexOf(">")&&(d=d.replace(ua,
"&gt;")),-1!=d.indexOf('"')&&(d=d.replace(va,"&quot;")),-1!=d.indexOf("'")&&(d=d.replace(wa,"&#39;")),-1!=d.indexOf("\x00")&&(d=d.replace(xa,"&#0;")));d=Ab('<iframe src="'+d+'"></iframe>');c.innerHTML=zb(d);a.b.ha(1)}
g.Xd=function(a){Rm(x(this.Wd,this,a),0)};
g.Wd=function(a){this.ya||(Km(this),Nm(this,a),Hm(this))};
g.zc=function(a){Rm(x(this.Vd,this,a),0)};
g.Vd=function(a){this.ya||(Lm(this),this.ia=a,this.b.sb(this),this.b.ha(4))};
g.cancel=function(){this.ya=!0;Lm(this)};
function Hm(a){a.Vb=y()+a.j;Sm(a,a.j)}
function Sm(a,b){if(null!=a.Ta)throw Error("WatchDog timer not null");a.Ta=Rm(x(a.Zd,a),b)}
function Km(a){a.Ta&&(l.clearTimeout(a.Ta),a.Ta=null)}
g.Zd=function(){this.Ta=null;var a=y();0<=a-this.Vb?(2!=this.Sa&&this.b.ha(3),Lm(this),this.Da=2,Y(),Om(this)):Sm(this,this.Vb-a)};
function Om(a){a.b.lc()||a.ya||a.b.sb(a)}
function Lm(a){Km(a);F(a.vb);a.vb=null;a.f.stop();vm(a.i);if(a.M){var b=a.M;a.M=null;Tm(b);b.dispose()}a.ja&&(a.ja=null)}
function Nm(a,b){try{a.b.tc(a,b),a.b.ha(4)}catch(c){}}
;function Um(a,b,c,d,e){if(0==d)c(!1);else{var f=e||0;d--;Vm(a,b,function(e){e?c(!0):l.setTimeout(function(){Um(a,b,c,d,f)},f)})}}
function Vm(a,b,c){var d=new Image;d.onload=function(){try{Wm(d),c(!0)}catch(a){}};
d.onerror=function(){try{Wm(d),c(!1)}catch(a){}};
d.onabort=function(){try{Wm(d),c(!1)}catch(a){}};
d.ontimeout=function(){try{Wm(d),c(!1)}catch(a){}};
l.setTimeout(function(){if(d.ontimeout)d.ontimeout()},b);
d.src=a}
function Wm(a){a.onload=null;a.onerror=null;a.onabort=null;a.ontimeout=null}
;function Xm(a){this.b=a;this.f=new nm(null,!0)}
g=Xm.prototype;g.Lb=null;g.ba=null;g.wb=!1;g.Gc=null;g.mb=null;g.Qb=null;g.Mb=null;g.ea=null;g.ra=-1;g.ab=null;g.Xa=null;g.connect=function(a){this.Mb=a;a=Ym(this.b,null,this.Mb);Y();this.Gc=y();var b=this.b.A;null!=b?(this.ab=b[0],(this.Xa=b[1])?(this.ea=1,Zm(this)):(this.ea=2,$m(this))):(Yg(a,"MODE","init"),this.ba=new Bm(this,0,void 0,void 0,void 0),this.ba.Ca=this.Lb,Gm(this.ba,a,!1,null,!0),this.ea=0)};
function Zm(a){var b=Ym(a.b,a.Xa,"/mail/images/cleardot.gif");$g(b);Um(b.toString(),5E3,x(a.dd,a),3,2E3);a.ha(1)}
g.dd=function(a){if(a)this.ea=2,$m(this);else{Y();var b=this.b;b.ga=b.ua.ra;an(b,9)}a&&this.ha(2)};
function $m(a){var b=a.b.R;if(null!=b)Y(),b?(Y(),bn(a.b,a,!1)):(Y(),bn(a.b,a,!0));else if(a.ba=new Bm(a,0,void 0,void 0,void 0),a.ba.Ca=a.Lb,b=a.b,b=Ym(b,b.gb()?a.ab:null,a.Mb),Y(),!L||kd(10))Yg(b,"TYPE","xmlhttp"),Gm(a.ba,b,!1,a.ab,!1);else{Yg(b,"TYPE","html");var c=a.ba;a=!!a.ab;c.Sa=3;c.ta=$g(b.clone());Qm(c,a)}}
g.Gb=function(a){return this.b.Gb(a)};
g.lc=function(){return!1};
g.tc=function(a,b){this.ra=a.bb;if(0==this.ea)if(b){try{var c=this.f.parse(b)}catch(d){c=this.b;c.ga=this.ra;an(c,2);return}this.ab=c[0];this.Xa=c[1]}else c=this.b,c.ga=this.ra,an(c,2);else if(2==this.ea)if(this.wb)Y(),this.Qb=y();else if("11111"==b){if(Y(),this.wb=!0,this.mb=y(),c=this.mb-this.Gc,!L||kd(10)||500>c)this.ra=200,this.ba.cancel(),Y(),bn(this.b,this,!0)}else Y(),this.mb=this.Qb=y(),this.wb=!1};
g.sb=function(){this.ra=this.ba.bb;if(this.ba.ia)0==this.ea?this.Xa?(this.ea=1,Zm(this)):(this.ea=2,$m(this)):2==this.ea&&((!L||kd(10)?!this.wb:200>this.Qb-this.mb)?(Y(),bn(this.b,this,!1)):(Y(),bn(this.b,this,!0)));else{0==this.ea?Y():2==this.ea&&Y();var a=this.b;a.ga=this.ra;an(a,2)}};
g.gb=function(){return this.b.gb()};
g.isActive=function(){return this.b.isActive()};
g.ha=function(a){this.b.ha(a)};function cn(a){km.call(this);this.headers=new Hc;this.K=a||null;this.f=!1;this.G=this.b=null;this.X="";this.j=0;this.o="";this.i=this.O=this.l=this.N=!1;this.F=0;this.A=null;this.oa="";this.ca=this.la=!1}
z(cn,km);var dn=/^https?$/i,en=["POST","PUT"];g=cn.prototype;
g.send=function(a,b,c,d){if(this.b)throw Error("[goog.net.XhrIo] Object is active with another request="+this.X+"; newUri="+a);b=b?b.toUpperCase():"GET";this.X=a;this.o="";this.j=0;this.N=!1;this.f=!0;this.b=this.K?this.K.b():ym.b();this.G=this.K?xm(this.K):xm(ym);this.b.onreadystatechange=x(this.sc,this);try{this.getStatus(),this.O=!0,this.b.open(b,String(a),!0),this.O=!1}catch(f){this.getStatus();fn(this,f);return}a=c||"";var e=this.headers.clone();d&&Oc(d,function(a,b){e.set(b,a)});
d=Ma(e.na(),gn);c=l.FormData&&a instanceof l.FormData;!B(en,b)||d||c||e.set("Content-Type","application/x-www-form-urlencoded;charset=utf-8");e.forEach(function(a,b){this.b.setRequestHeader(b,a)},this);
this.oa&&(this.b.responseType=this.oa);"withCredentials"in this.b&&this.b.withCredentials!==this.la&&(this.b.withCredentials=this.la);try{hn(this),0<this.F&&(this.ca=jn(this.b),this.getStatus(),this.ca?(this.b.timeout=this.F,this.b.ontimeout=x(this.kc,this)):this.A=qm(this.kc,this.F,this)),this.getStatus(),this.l=!0,this.b.send(a),this.l=!1}catch(f){this.getStatus(),fn(this,f)}};
function jn(a){return L&&jd(9)&&ea(a.timeout)&&p(a.ontimeout)}
function gn(a){return"content-type"==a.toLowerCase()}
g.kc=function(){"undefined"!=typeof aa&&this.b&&(this.o="Timed out after "+this.F+"ms, aborting",this.j=8,this.getStatus(),lm(this,"timeout"),Tm(this,8))};
function fn(a,b){a.f=!1;a.b&&(a.i=!0,a.b.abort(),a.i=!1);a.o=b;a.j=5;kn(a);ln(a)}
function kn(a){a.N||(a.N=!0,lm(a,"complete"),lm(a,"error"))}
function Tm(a,b){a.b&&a.f&&(a.getStatus(),a.f=!1,a.i=!0,a.b.abort(),a.i=!1,a.j=b||7,lm(a,"complete"),lm(a,"abort"),ln(a))}
g.w=function(){this.b&&(this.f&&(this.f=!1,this.i=!0,this.b.abort(),this.i=!1),ln(this,!0));cn.B.w.call(this)};
g.sc=function(){this.C()||(this.O||this.l||this.i?mn(this):this.Ld())};
g.Ld=function(){mn(this)};
function mn(a){if(a.f&&"undefined"!=typeof aa)if(a.G[1]&&4==Im(a)&&2==a.getStatus())a.getStatus();else if(a.l&&4==Im(a))qm(a.sc,0,a);else if(lm(a,"readystatechange"),4==Im(a)){a.getStatus();a.f=!1;try{var b=a.getStatus(),c;a:switch(b){case 200:case 201:case 202:case 204:case 206:case 304:case 1223:c=!0;break a;default:c=!1}var d;if(!(d=c)){var e;if(e=0===b){var f=zd(1,String(a.X));if(!f&&l.self&&l.self.location)var h=l.self.location.protocol,f=h.substr(0,h.length-1);e=!dn.test(f?f.toLowerCase():"")}d=
e}if(d)lm(a,"complete"),lm(a,"success");else{a.j=6;var k;try{k=2<Im(a)?a.b.statusText:""}catch(m){k=""}a.o=k+" ["+a.getStatus()+"]";kn(a)}}finally{ln(a)}}}
function ln(a,b){if(a.b){hn(a);var c=a.b,d=a.G[0]?t:null;a.b=null;a.G=null;b||lm(a,"ready");try{c.onreadystatechange=d}catch(e){}}}
function hn(a){a.b&&a.ca&&(a.b.ontimeout=null);ea(a.A)&&(l.clearTimeout(a.A),a.A=null)}
g.isActive=function(){return!!this.b};
function Im(a){return a.b?a.b.readyState:0}
g.getStatus=function(){try{return 2<Im(this)?this.b.status:-1}catch(a){return-1}};
function Jm(a){try{return a.b?a.b.responseText:""}catch(b){return""}}
;function nn(a,b,c){this.l=a||null;this.b=1;this.f=[];this.i=[];this.j=new nm(null,!0);this.A=b||null;this.R=null!=c?c:null}
function on(a,b){this.f=a;this.b=b;this.context=null}
g=nn.prototype;g.Za=null;g.W=null;g.J=null;g.Kb=null;g.nb=null;g.bc=null;g.ob=null;g.cb=0;g.wd=0;g.P=null;g.va=null;g.qa=null;g.Aa=null;g.ua=null;g.Ab=null;g.Oa=-1;g.mc=-1;g.ga=-1;g.$a=0;g.Na=0;g.za=8;var pn=new km;function qn(a){Kl.call(this,"statevent",a)}
z(qn,Kl);function rn(a,b){Kl.call(this,"timingevent",a);this.size=b}
z(rn,Kl);function sn(a){Kl.call(this,"serverreachability",a)}
z(sn,Kl);g=nn.prototype;g.connect=function(a,b,c,d,e){Y();this.Kb=b;this.Za=c||{};d&&p(e)&&(this.Za.OSID=d,this.Za.OAID=e);this.ua=new Xm(this);this.ua.Lb=null;this.ua.f=this.j;this.ua.connect(a)};
function tn(a){un(a);if(3==a.b){var b=a.cb++,c=a.nb.clone();Q(c,"SID",a.g);Q(c,"RID",b);Q(c,"TYPE","terminate");vn(a,c);b=new Bm(a,0,a.g,b,void 0);b.Sa=2;b.ta=$g(c.clone());(new Image).src=b.ta;b.fb=y();Hm(b)}wn(a)}
function un(a){if(a.ua){var b=a.ua;b.ba&&(b.ba.cancel(),b.ba=null);b.ra=-1;a.ua=null}a.J&&(a.J.cancel(),a.J=null);a.qa&&(l.clearTimeout(a.qa),a.qa=null);xn(a);a.W&&(a.W.cancel(),a.W=null);a.va&&(l.clearTimeout(a.va),a.va=null)}
function yn(a,b){if(0==a.b)throw Error("Invalid operation: sending map when state is closed");a.f.push(new on(a.wd++,b));2!=a.b&&3!=a.b||zn(a)}
g.lc=function(){return 0==this.b};
function zn(a){a.W||a.va||(a.va=Rm(x(a.yc,a),0),a.$a=0)}
g.yc=function(a){this.va=null;An(this,a)};
function An(a,b){if(1==a.b){if(!b){a.cb=Math.floor(1E5*Math.random());var c=a.cb++,d=new Bm(a,0,"",c,void 0);d.Ca=null;var e=Bn(a),f=a.nb.clone();Q(f,"RID",c);a.l&&Q(f,"CVER",a.l);vn(a,f);Em(d,f,e);a.W=d;a.b=2}}else 3==a.b&&(b?Cn(a,b):0!=a.f.length&&(a.W||Cn(a)))}
function Cn(a,b){var c,d;b?6<a.za?(a.f=a.i.concat(a.f),a.i.length=0,c=a.cb-1,d=Bn(a)):(c=b.A,d=b.Ha):(c=a.cb++,d=Bn(a));var e=a.nb.clone();Q(e,"SID",a.g);Q(e,"RID",c);Q(e,"AID",a.Oa);vn(a,e);c=new Bm(a,0,a.g,c,a.$a+1);c.Ca=null;c.setTimeout(Math.round(1E4)+Math.round(1E4*Math.random()));a.W=c;Em(c,e,d)}
function vn(a,b){if(a.P){var c=a.P.ic(a);c&&Xa(c,function(a,c){Q(b,c,a)})}}
function Bn(a){var b=Math.min(a.f.length,1E3),c=["count="+b],d;6<a.za&&0<b?(d=a.f[0].f,c.push("ofs="+d)):d=0;for(var e=0;e<b;e++){var f=a.f[e].f,h=a.f[e].b,f=6>=a.za?e:f-d;try{Oc(h,function(a,b){c.push("req"+f+"_"+b+"="+encodeURIComponent(a))})}catch(k){c.push("req"+f+"_type="+encodeURIComponent("_badmap"))}}a.i=a.i.concat(a.f.splice(0,b));
return c.join("&")}
function Dn(a){a.J||a.qa||(a.o=1,a.qa=Rm(x(a.xc,a),0),a.Na=0)}
function En(a){if(a.J||a.qa||3<=a.Na)return!1;a.o++;a.qa=Rm(x(a.xc,a),Fn(a,a.Na));a.Na++;return!0}
g.xc=function(){this.qa=null;this.J=new Bm(this,0,this.g,"rpc",this.o);this.J.Ca=null;this.J.Nb=0;var a=this.bc.clone();Q(a,"RID","rpc");Q(a,"SID",this.g);Q(a,"CI",this.Ab?"0":"1");Q(a,"AID",this.Oa);vn(this,a);if(!L||kd(10))Q(a,"TYPE","xmlhttp"),Gm(this.J,a,!0,this.ob,!1);else{Q(a,"TYPE","html");var b=this.J,c=!!this.ob;b.Sa=3;b.ta=$g(a.clone());Qm(b,c)}};
function bn(a,b,c){a.Ab=c;a.ga=b.ra;a.gd(1,0);a.nb=Ym(a,null,a.Kb);zn(a)}
g.tc=function(a,b){if(0!=this.b&&(this.J==a||this.W==a))if(this.ga=a.bb,this.W==a&&3==this.b)if(7<this.za){var c;try{c=this.j.parse(b)}catch(f){c=null}if(v(c)&&3==c.length)if(0==c[0])a:{if(!this.qa){if(this.J)if(this.J.fb+3E3<this.W.fb)xn(this),this.J.cancel(),this.J=null;else break a;En(this);Y()}}else this.mc=c[1],0<this.mc-this.Oa&&37500>c[2]&&this.Ab&&0==this.Na&&!this.Aa&&(this.Aa=Rm(x(this.xd,this),6E3));else an(this,11)}else b!=yf.Ie.b&&an(this,11);else if(this.J==a&&xn(this),!/^[\s\xa0]*$/.test(b)){c=
this.j.parse(b);v(c);for(var d=0;d<c.length;d++){var e=c[d];this.Oa=e[0];e=e[1];2==this.b?"c"==e[0]?(this.g=e[1],this.ob=e[2],e=e[3],null!=e?this.za=e:this.za=6,this.b=3,this.P&&this.P.gc(this),this.bc=Ym(this,this.gb()?this.ob:null,this.Kb),Dn(this)):"stop"==e[0]&&an(this,7):3==this.b&&("stop"==e[0]?an(this,7):"noop"!=e[0]&&this.P&&this.P.fc(this,e),this.Na=0)}}};
g.xd=function(){null!=this.Aa&&(this.Aa=null,this.J.cancel(),this.J=null,En(this),Y())};
function xn(a){null!=a.Aa&&(l.clearTimeout(a.Aa),a.Aa=null)}
g.sb=function(a){var b;if(this.J==a)xn(this),this.J=null,b=2;else if(this.W==a)this.W=null,b=1;else return;this.ga=a.bb;if(0!=this.b)if(a.ia)1==b?(y(),lm(pn,new rn(pn,a.Ha?a.Ha.length:0)),zn(this),this.i.length=0):Dn(this);else{var c=a.Da,d;if(!(d=3==c||7==c||0==c&&0<this.ga)){if(d=1==b)this.W||this.va||1==this.b||2<=this.$a?d=!1:(this.va=Rm(x(this.yc,this,a),Fn(this,this.$a)),this.$a++,d=!0);d=!(d||2==b&&En(this))}if(d)switch(c){case 1:an(this,5);break;case 4:an(this,10);break;case 3:an(this,6);
break;case 7:an(this,12);break;default:an(this,2)}}};
function Fn(a,b){var c=5E3+Math.floor(1E4*Math.random());a.isActive()||(c*=2);return c*b}
g.gd=function(a){if(!B(arguments,this.b))throw Error("Unexpected channel state: "+this.b);};
function an(a,b){if(2==b||9==b){var c=null;a.P&&(c=null);var d=x(a.qe,a);c||(c=new Kg("//www.google.com/images/cleardot.gif"),$g(c));Vm(c.toString(),1E4,d)}else Y();Gn(a,b)}
g.qe=function(a){a?Y():(Y(),Gn(this,8))};
function Gn(a,b){a.b=0;a.P&&a.P.ec(a,b);wn(a);un(a)}
function wn(a){a.b=0;a.ga=-1;if(a.P)if(0==a.i.length&&0==a.f.length)a.P.Eb(a);else{var b=Sa(a.i),c=Sa(a.f);a.i.length=0;a.f.length=0;a.P.Eb(a,b,c)}}
function Ym(a,b,c){var d=ah(c);if(""!=d.f)b&&Mg(d,b+"."+d.f),Ng(d,d.l);else var e=window.location,d=bh(e.protocol,b?b+"."+e.hostname:e.hostname,e.port,c);a.Za&&Xa(a.Za,function(a,b){Q(d,b,a)});
Q(d,"VER",a.za);vn(a,d);return d}
g.Gb=function(a){if(a)throw Error("Can't create secondary domain capable XhrIo object.");a=new cn;a.la=!1;return a};
g.isActive=function(){return!!this.P&&this.P.isActive(this)};
function Rm(a,b){if(!fa(a))throw Error("Fn must not be null and must be a function");return l.setTimeout(function(){a()},b)}
g.ha=function(){lm(pn,new sn(pn))};
function Y(){lm(pn,new qn(pn))}
g.gb=function(){return!(!L||kd(10))};
function Hn(){}
g=Hn.prototype;g.gc=function(){};
g.fc=function(){};
g.ec=function(){};
g.Eb=function(){};
g.ic=function(){return{}};
g.isActive=function(){return!0};function In(a,b){om.call(this);this.o=0;if(fa(a))b&&(a=x(a,b));else if(a&&fa(a.handleEvent))a=x(a.handleEvent,a);else throw Error("Invalid listener argument");this.A=a;$l(this,"tick",x(this.l,this));Jn(this)}
z(In,om);In.prototype.l=function(){if(500<this.b){var a=this.b;24E4>2*a&&(a*=2);pm(this,a)}this.A()};
In.prototype.start=function(){In.B.start.call(this);this.o=y()+this.b};
In.prototype.stop=function(){this.o=0;In.B.stop.call(this)};
function Jn(a){a.stop();pm(a,5E3+2E4*Math.random())}
;function Kn(a,b){this.G=a;this.o=b;this.g=new G;this.f=new In(this.ue,this);this.b=null;this.F=!1;this.j=null;this.R="";this.A=this.i=0;this.l=[]}
z(Kn,Hn);g=Kn.prototype;g.subscribe=function(a,b,c){return this.g.subscribe(a,b,c)};
g.unsubscribe=function(a,b,c){return this.g.unsubscribe(a,b,c)};
g.ka=function(a){return this.g.ka(a)};
g.u=function(a,b){return this.g.u.apply(this.g,arguments)};
g.dispose=function(){this.F||(this.F=!0,this.g.clear(),Ln(this),F(this.g))};
g.C=function(){return this.F};
function Mn(a){return{firstTestResults:[""],secondTestResults:!a.b.Ab,sessionId:a.b.g,arrayId:a.b.Oa}}
g.connect=function(a,b,c){if(!this.b||2!=this.b.b){this.R="";this.f.stop();this.j=a||null;this.i=b||0;a=this.G+"/test";b=this.G+"/bind";var d=new nn("1",c?c.firstTestResults:null,c?c.secondTestResults:null),e=this.b;e&&(e.P=null);d.P=this;this.b=d;e?this.b.connect(a,b,this.o,e.g,e.Oa):c?this.b.connect(a,b,this.o,c.sessionId,c.arrayId):this.b.connect(a,b,this.o)}};
function Ln(a,b){a.A=b||0;a.f.stop();a.b&&(3==a.b.b&&An(a.b),tn(a.b));a.A=0}
g.sendMessage=function(a,b){var c={_sc:a};b&&kb(c,b);this.f.enabled||2==(this.b?this.b.b:0)?this.l.push(c):Nn(this)&&yn(this.b,c)};
g.gc=function(){Jn(this.f);this.j=null;this.i=0;if(this.l.length){var a=this.l;this.l=[];for(var b=0,c=a.length;b<c;++b)yn(this.b,a[b])}this.u("handlerOpened")};
g.ec=function(a,b){var c=2==b&&401==this.b.ga;if(4!=b&&!c){if(6==b||410==this.b.ga)c=this.f,c.stop(),pm(c,500);this.f.start()}this.u("handlerError",b)};
g.Eb=function(a,b,c){if(!this.f.enabled)this.u("handlerClosed");else if(c)for(a=0,b=c.length;a<b;++a){var d=c[a].b;d&&this.l.push(d)}};
g.ic=function(){var a={v:2};this.R&&(a.gsessionid=this.R);0!=this.i&&(a.ui=""+this.i);0!=this.A&&(a.ui=""+this.A);this.j&&kb(a,this.j);return a};
g.fc=function(a,b){"S"==b[0]?this.R=b[1]:"gracefulReconnect"==b[0]?(Jn(this.f),this.f.start(),tn(this.b)):this.u("handlerMessage",new Il(b[0],b[1]))};
function Nn(a){return!!a.b&&3==a.b.b}
function On(a,b){(a.o.loungeIdToken=b)||a.f.stop()}
g.ue=function(){this.f.stop();var a=this.b,b=0;a.J&&b++;a.W&&b++;0!=b?this.f.start():this.connect(this.j,this.i)};function Pn(a){this.index=-1;this.videoId=this.listId="";this.volume=this.b=-1;this.j=!1;this.audioTrackId=null;this.i=this.f=0;this.g=null;this.reset(a)}
function Qn(a){a.audioTrackId=null;a.g=null;a.b=-1;a.f=0;a.i=y()}
Pn.prototype.reset=function(a){this.listId="";this.index=-1;this.videoId="";Qn(this);this.volume=-1;this.j=!1;a&&(this.index=a.index,this.listId=a.listId,this.videoId=a.videoId,this.b=a.playerState,this.volume=a.volume,this.j=a.muted,this.audioTrackId=a.audioTrackId,this.g=a.trackData,this.f=a.playerTime,this.i=a.playerTimeAt)};
function Rn(a){switch(a.b){case 1:return(y()-a.i)/1E3+a.f;case -1E3:return 0}return a.f}
function Sn(a){var b={};b.index=a.index;b.listId=a.listId;b.videoId=a.videoId;b.playerState=a.b;b.volume=a.volume;b.muted=a.j;b.audioTrackId=a.audioTrackId;b.trackData=ib(a.g);b.playerTime=a.f;b.playerTimeAt=a.i;return b}
Pn.prototype.clone=function(){return new Pn(Sn(this))};function Z(a,b,c){V.call(this);this.i=NaN;this.X=!1;this.K=this.G=this.N=this.O=NaN;this.Y=[];this.A=this.F=this.g=this.D=this.b=null;this.Va=a;this.Y.push(N(window,"beforeunload",x(this.nd,this)));this.f=[];this.D=new Pn;this.la=b.id;this.b=Tn(this,c);this.b.subscribe("handlerOpened",this.Bd,this);this.b.subscribe("handlerClosed",this.yd,this);this.b.subscribe("handlerError",this.zd,this);this.b.subscribe("handlerMessage",this.Ad,this);On(this.b,b.token);this.subscribe("remoteQueueChange",function(){var a=
this.D.videoId;yk()&&O("yt-remote-session-video-id",a)},this)}
z(Z,V);g=Z.prototype;g.connect=function(a,b){if(b){var c=b.listId,d=b.videoId,e=b.index,f=b.currentTime||0;5>=f&&(f=0);var h={videoId:d,currentTime:f};c&&(h.listId=c);p(e)&&(h.currentIndex=e);c&&(this.D.listId=c);this.D.videoId=d;this.D.index=e||0;this.D.state=3;c=this.D;c.f=f;c.i=y();this.A="UNSUPPORTED";Un("Connecting with setPlaylist and params: "+M(h));this.b.connect({method:"setPlaylist",params:M(h)},a,Ck())}else Un("Connecting without params"),this.b.connect({},a,Ck());Vn(this)};
g.dispose=function(){this.C()||(this.u("beforeDispose"),Wn(this,3));Z.B.dispose.call(this)};
g.w=function(){Xn(this);Yn(this);Zn(this);J(this.G);this.G=NaN;J(this.K);this.K=NaN;this.g=null;Le(this.Y);this.Y.length=0;this.b.dispose();Z.B.w.call(this);this.A=this.F=this.f=this.D=this.b=null};
function Un(a){Uj("conn",a)}
g.nd=function(){this.j(2)};
function Tn(a,b){return new Kn(gk(a.Va,"/bc",void 0,!1),b)}
function Wn(a,b){a.u("proxyStateChange",b)}
function Vn(a){a.i=I(x(function(){Un("Connecting timeout");this.j(1)},a),2E4)}
function Xn(a){J(a.i);a.i=NaN}
function Zn(a){J(a.O);a.O=NaN}
function $n(a){Yn(a);a.N=I(x(function(){ao(this,"getNowPlaying")},a),2E4)}
function Yn(a){J(a.N);a.N=NaN}
g.Bd=function(){Un("Channel opened");this.X&&(this.X=!1,Zn(this),this.O=I(x(function(){Un("Timing out waiting for a screen.");this.j(1)},this),15E3));
Gk(Mn(this.b),this.la)};
g.yd=function(){Un("Channel closed");isNaN(this.i)?Hk(!0):Hk();this.dispose()};
g.zd=function(a){Hk();isNaN(this.l())?(Un("Channel error: "+a+" without reconnection"),this.dispose()):(this.X=!0,Un("Channel error: "+a+" with reconnection in "+this.l()+" ms"),Wn(this,2))};
function bo(a,b){b&&(Xn(a),Zn(a));b==(Nn(a.b)&&isNaN(a.i))?b&&(Wn(a,1),ao(a,"getSubtitlesTrack")):b?(a.ca()&&a.D.reset(),Wn(a,1),ao(a,"getNowPlaying"),co(a)):a.j(1)}
function eo(a,b){var c=b.params.videoId;delete b.params.videoId;c==a.D.videoId&&(fb(b.params)?a.D.g=null:a.D.g=b.params,a.u("remotePlayerChange"))}
function fo(a,b){var c=b.params.videoId||b.params.video_id,d=parseInt(b.params.currentIndex,10);a.D.listId=b.params.listId||a.D.listId;var e=a.D,f=e.videoId;e.videoId=c;e.index=d;c!=f&&Qn(e);a.u("remoteQueueChange")}
function go(a,b){b.params=b.params||{};fo(a,b);ho(a,b)}
function ho(a,b){var c=parseInt(b.params.currentTime||b.params.current_time,10),d=a.D;d.f=isNaN(c)?0:c;d.i=y();c=parseInt(b.params.state,10);c=isNaN(c)?-1:c;-1==c&&-1E3==a.D.b&&(c=-1E3);a.D.b=c;1==a.D.b?$n(a):Yn(a);a.u("remotePlayerChange")}
function io(a,b){var c="true"==b.params.muted;a.D.volume=parseInt(b.params.volume,10);a.D.j=c;a.u("remotePlayerChange")}
g.Ad=function(a){a.params?Un("Received: action="+a.action+", params="+M(a.params)):Un("Received: action="+a.action+" {}");switch(a.action){case "loungeStatus":a=od(a.params.devices);this.f=Ja(a,function(a){return new tk(a)});
a=!!Ma(this.f,function(a){return"LOUNGE_SCREEN"==a.type});
bo(this,a);break;case "loungeScreenConnected":bo(this,!0);break;case "loungeScreenDisconnected":Qa(this.f,function(a){return"LOUNGE_SCREEN"==a.type});
bo(this,!1);break;case "remoteConnected":var b=new tk(od(a.params.device));Ma(this.f,function(a){return a.equals(b)})||Oa(this.f,b);
break;case "remoteDisconnected":b=new tk(od(a.params.device));Qa(this.f,function(a){return a.equals(b)});
break;case "gracefulDisconnect":break;case "playlistModified":fo(this,a);break;case "nowPlaying":go(this,a);break;case "onStateChange":ho(this,a);break;case "onVolumeChanged":io(this,a);break;case "onSubtitlesTrackChanged":eo(this,a);break;case "nowAutoplaying":this.F=a.params.videoId;break;case "autoplayDismissed":break;case "autoplayUpNext":this.F=a.params.videoId;break;case "onAutoplayModeChanged":this.A=a.params.autoplayMode;break;default:Un("Unrecognized action: "+a.action)}};
g.ge=function(){if(this.g){var a=this.g;this.g=null;this.D.videoId!=a&&ao(this,"getNowPlaying")}};
Z.prototype.subscribe=Z.prototype.subscribe;Z.prototype.unsubscribeByKey=Z.prototype.ka;Z.prototype.Ka=function(){var a=3;this.C()||(a=0,isNaN(this.l())?Nn(this.b)&&isNaN(this.i)&&(a=1):a=2);return a};
Z.prototype.getProxyState=Z.prototype.Ka;Z.prototype.j=function(a){Un("Disconnecting with "+a);Xn(this);this.u("beforeDisconnect",a);1==a&&Hk();Ln(this.b,a);this.dispose()};
Z.prototype.disconnect=Z.prototype.j;Z.prototype.Ja=function(){var a=this.D;if(this.g){var b=a=this.D.clone(),c=this.g,d=a.index,e=b.videoId;b.videoId=c;b.index=d;c!=e&&Qn(b)}return Sn(a)};
Z.prototype.getPlayerContextData=Z.prototype.Ja;Z.prototype.Ua=function(a){var b=new Pn(a);b.videoId&&b.videoId!=this.D.videoId&&(this.g=b.videoId,J(this.G),this.G=I(x(this.ge,this),5E3));var c=[];this.D.listId==b.listId&&this.D.videoId==b.videoId&&this.D.index==b.index||c.push("remoteQueueChange");this.D.b==b.b&&this.D.volume==b.volume&&this.D.j==b.j&&Rn(this.D)==Rn(b)&&M(this.D.g)==M(b.g)||c.push("remotePlayerChange");this.D.reset(a);A(c,function(a){this.u(a)},this)};
Z.prototype.setPlayerContextData=Z.prototype.Ua;Z.prototype.ca=function(){var a=this.b.o.id,b=Ma(this.f,function(b){return"REMOTE_CONTROL"==b.type&&b.id!=a});
return b?b.id:""};
Z.prototype.getOtherConnectedRemoteId=Z.prototype.ca;Z.prototype.l=function(){var a=this.b;return a.f.enabled?a.f.o-y():NaN};
Z.prototype.getReconnectTimeout=Z.prototype.l;Z.prototype.oa=function(){return this.A||"UNSUPPORTED"};
Z.prototype.getAutoplayMode=Z.prototype.oa;Z.prototype.sa=function(){return this.F||""};
Z.prototype.getAutoplayVideoId=Z.prototype.sa;Z.prototype.Wa=function(){if(!isNaN(this.l())){var a=this.b.f;a.enabled&&(a.stop(),a.start(),a.l())}};
Z.prototype.reconnect=Z.prototype.Wa;function co(a){J(a.K);a.K=I(x(a.j,a,1),864E5)}
function ao(a,b,c){c?Un("Sending: action="+b+", params="+M(c)):Un("Sending: action="+b);a.b.sendMessage(b,c)}
Z.prototype.La=function(a,b){ao(this,a,b);co(this)};
Z.prototype.sendMessage=Z.prototype.La;function jo(a){nk.call(this,"ScreenServiceProxy");this.U=a;this.b=[];this.b.push(this.U.$_s("screenChange",x(this.ye,this)));this.b.push(this.U.$_s("onlineScreenChange",x(this.Hd,this)))}
z(jo,nk);g=jo.prototype;g.Z=function(a){return this.U.$_gs(a)};
g.contains=function(a){return!!this.U.$_c(a)};
g.get=function(a){return this.U.$_g(a)};
g.start=function(){this.U.$_st()};
g.jb=function(a,b,c){this.U.$_a(a,b,c)};
g.remove=function(a,b,c){this.U.$_r(a,b,c)};
g.hb=function(a,b,c,d){this.U.$_un(a,b,c,d)};
g.w=function(){for(var a=0,b=this.b.length;a<b;++a)this.U.$_ubk(this.b[a]);this.b.length=0;this.U=null;jo.B.w.call(this)};
g.ye=function(){this.u("screenChange")};
g.Hd=function(){this.u("onlineScreenChange")};
W.prototype.$_st=W.prototype.start;W.prototype.$_gspc=W.prototype.ze;W.prototype.$_gsppc=W.prototype.Kc;W.prototype.$_c=W.prototype.contains;W.prototype.$_g=W.prototype.get;W.prototype.$_a=W.prototype.jb;W.prototype.$_un=W.prototype.hb;W.prototype.$_r=W.prototype.remove;W.prototype.$_gs=W.prototype.Z;W.prototype.$_gos=W.prototype.Jc;W.prototype.$_s=W.prototype.subscribe;W.prototype.$_ubk=W.prototype.ka;function ko(){var a={device:"Desktop",app:"youtube-desktop"};df&&cf();vk();lo||(lo=new fk,Ik()&&(lo.b="/api/loungedev"));mo||(mo=r("yt.mdx.remote.deferredProxies_")||[],q("yt.mdx.remote.deferredProxies_",mo,void 0));no();var b=oo();if(!b){var c=new W(lo);q("yt.mdx.remote.screenService_",c,void 0);b=oo();ol(c,function(a){a?po()&&Hl(po(),"YouTube TV"):c.subscribe("onlineScreenChange",function(){K("yt-remote-receiver-availability-change")})},!(!a||!a.loadCastApiSetupScript))}if(a&&!r("yt.mdx.remote.initialized_")){q("yt.mdx.remote.initialized_",
!0,void 0);
qo("Initializing: "+M(a));ro.push(kc("yt-remote-cast2-availability-change",function(){K("yt-remote-receiver-availability-change")}));
ro.push(kc("yt-remote-cast2-receiver-selected",function(){so(null);K("yt-remote-auto-connect","cast-selector-receiver")}));
ro.push(kc("yt-remote-cast2-session-change",to));ro.push(kc("yt-remote-connection-change",function(a){a?Hl(po(),"YouTube TV"):uo()||(Hl(null,null),Dl())}));
var d=vo();a.isAuto&&(d.id+="#dial");d.name=a.device;d.app=a.app;qo(" -- with channel params: "+M(d));wo(d);b.start();po()||xo()}}
function yo(){mc(ro);ro.length=0;F(zo);zo=null;mo&&(A(mo,function(a){a(null)}),mo.length=0,mo=null,q("yt.mdx.remote.deferredProxies_",null,void 0));
lo=null}
function Ao(){if(Bl()){var a=[];if(P("yt-remote-cast-available")||r("yt.mdx.remote.cloudview.castButtonShown_")||Bo())a.push({key:"cast-selector-receiver",name:Co()}),q("yt.mdx.remote.cloudview.castButtonShown_",!0,void 0);return a}return r("yt.mdx.remote.cloudview.initializing_")?[]:Do()}
function Do(){var a;a=oo().U.$_gos();var b=Eo();b&&Bo()&&(lk(a,b)||a.push(b));return kk(a)}
function Fo(){if(Bl()){var a=Cl();return a?{key:"cast-selector-receiver",name:a}:null}return Go()}
function Go(){var a=Do(),b=Eo();b||(b=uo());return Ma(a,function(a){return b&&Yj(b,a.key)?!0:!1})}
function Co(){if(Bl())return Cl();var a=Eo();return a?a.name:null}
function Eo(){var a=po();if(!a)return null;var b=oo().Z();return mk(b,a)}
function to(a){qo("remote.onCastSessionChange_: "+ck(a));if(a){var b=Eo();b&&b.id==a.id?Hl(b.id,"YouTube TV"):(b&&Ho(),Io(a,1))}else Ho()}
function Jo(a,b){qo("Connecting to: "+M(a));if("cast-selector-receiver"==a.key)so(b||null),Gl(b||null);else{Ho();so(b||null);var c=oo().Z();(c=mk(c,a.key))?Io(c,1):I(function(){Ko(null)},0)}}
function Ho(){Fl()?xl().stopSession():ul("stopSession called before API ready.");var a=Bo();a?a.disconnect(1):(nc("yt-remote-before-disconnect",1),nc("yt-remote-connection-change",!1));Ko(null)}
function qo(a){Uj("remote",a)}
function oo(){if(!zo){var a=r("yt.mdx.remote.screenService_");zo=a?new jo(a):null}return zo}
function po(){return r("yt.mdx.remote.currentScreenId_")}
function Lo(a){q("yt.mdx.remote.currentScreenId_",a,void 0)}
function so(a){q("yt.mdx.remote.connectData_",a,void 0)}
function Bo(){return r("yt.mdx.remote.connection_")}
function Ko(a){var b=Bo();so(null);a?Bo():Lo("");q("yt.mdx.remote.connection_",a,void 0);mo&&(A(mo,function(b){b(a)}),mo.length=0);
b&&!a?nc("yt-remote-connection-change",!1):!b&&a&&K("yt-remote-connection-change",!0)}
function uo(){var a=yk();if(!a)return null;var b=oo().Z();return mk(b,a)}
function Io(a,b){po();Lo(a.id);var c=new Z(lo,a,vo());c.connect(b,r("yt.mdx.remote.connectData_"));c.subscribe("beforeDisconnect",function(a){nc("yt-remote-before-disconnect",a)});
c.subscribe("beforeDispose",function(){Bo()&&(Bo(),Ko(null))});
Ko(c)}
function xo(){var a=uo();a?(qo("Resume connection to: "+ck(a)),Io(a,0)):(Hk(),Dl(),qo("Skipping connecting because no session screen found."))}
var lo=null,mo=null,zo=null;function no(){var a=vo();if(fb(a)){var a=xk(),b=P("yt-remote-session-name")||"",c=P("yt-remote-session-app")||"",a={device:"REMOTE_CONTROL",id:a,name:b,app:c,"mdx-version":3};q("yt.mdx.remote.channelParams_",a,void 0)}}
function vo(){return r("yt.mdx.remote.channelParams_")||{}}
function wo(a){a?(O("yt-remote-session-app",a.app),O("yt-remote-session-name",a.name)):(ff("yt-remote-session-app"),ff("yt-remote-session-name"));q("yt.mdx.remote.channelParams_",a,void 0)}
var ro=[];var Mo=null,No=[];function Oo(){Po();if(Fo()){var a=Mo;"html5"!=a.getPlayerType()&&a.loadNewVideoConfig(a.getCurrentVideoConfig(),"html5")}}
function Qo(a){"cast-selector-receiver"==a?El():Ro(a)}
function Ro(a){var b=Ao();if(a=jk(b,a)){var c=Mo;Jo(a,{listId:c.getVideoData().list,videoId:c.getVideoData().video_id,currentTime:c.getCurrentTime()});"html5"!=c.getPlayerType()?c.loadNewVideoConfig(c.getCurrentVideoConfig(),"html5"):c.updateRemoteReceivers&&c.updateRemoteReceivers(b,a)}}
function Po(){var a=Mo;a&&a.updateRemoteReceivers&&a.updateRemoteReceivers(Ao(),Fo())}
;var So=null,To=[];function Uo(a){return{externalChannelId:a.externalChannelId,vd:!!a.isChannelPaid,source:a.source,subscriptionId:a.subscriptionId}}
function Vo(a){Wo(Uo(a))}
function Wo(a){Qi()?(T(Di,new xi(a.externalChannelId,a.vd?{itemType:"U",itemId:a.externalChannelId}:null)),(a="/gen_204?"+Fd({event:"subscribe",source:a.source}))&&mh(a)):Xo(a)}
function Xo(a){Pi(function(b){b.subscription_ajax&&Wo(a)},null)}
function Yo(a){a=Uo(a);T(Ii,new zi(a.externalChannelId,a.subscriptionId,null));(a="/gen_204?"+Fd({event:"unsubscribe",source:a.source}))&&mh(a)}
function Zo(a){So&&So.channelSubscribed(a.b,a.subscriptionId)}
function $o(a){So&&So.channelUnsubscribed(a.b)}
;function ap(a){E.call(this);this.f=a;this.f.subscribe("command",this.Bc,this);this.g={};this.i=!1}
z(ap,E);g=ap.prototype;g.start=function(){this.i||this.C()||(this.i=!0,bp(this.f,"RECEIVING"))};
g.Nc=u;g.addEventListener=u;g.removeEventListener=u;g.Bc=function(a,b){if(this.i&&!this.C()){var c=b||{};switch(a){case "addEventListener":if(w(c.event)&&(c=c.event,!(c in this.g))){var d=x(this.ie,this,c);this.g[c]=d;this.addEventListener(c,d)}break;case "removeEventListener":w(c.event)&&cp(this,c.event);break;default:this.Nc(a,b)}}};
g.ie=function(a,b){this.i&&!this.C()&&bp(this.f,a,this.Hb(a,b))};
g.Hb=function(a,b){if(null!=b)return{value:b}};
function cp(a,b){b in a.g&&(a.removeEventListener(b,a.g[b]),delete a.g[b])}
g.w=function(){this.f.unsubscribe("command",this.Bc,this);this.f=null;for(var a in this.g)cp(this,a);ap.B.w.call(this)};function dp(a,b){ap.call(this,b);this.b=a;this.start()}
z(dp,ap);g=dp.prototype;g.addEventListener=function(a,b){this.b.addEventListener(a,b)};
g.removeEventListener=function(a,b){this.b.removeEventListener(a,b)};
g.Nc=function(a,b){if(this.b.isReady()&&this.b[a]){var c=ep(a,b||{}),c=this.b[a].apply(this.b,c);(c=fp(a,c))&&this.i&&!this.C()&&bp(this.f,a,c)}};
function ep(a,b){switch(a){case "loadVideoById":return b=uj(b),wj(b),[b];case "cueVideoById":return b=uj(b),wj(b),[b];case "loadVideoByPlayerVars":return wj(b),[b];case "cueVideoByPlayerVars":return wj(b),[b];case "loadPlaylist":return b=vj(b),wj(b),[b];case "cuePlaylist":return b=vj(b),wj(b),[b];case "seekTo":return[b.seconds,b.allowSeekAhead];case "playVideoAt":return[b.index];case "setVolume":return[b.volume];case "setPlaybackQuality":return[b.suggestedQuality];case "setPlaybackRate":return[b.suggestedRate];
case "setLoop":return[b.loopPlaylists];case "setShuffle":return[b.shufflePlaylist];case "getOptions":return[b.module];case "getOption":return[b.module,b.option];case "setOption":return[b.module,b.option,b.value];case "handleGlobalKeyDown":return[b.keyCode,b.shiftKey]}return[]}
function fp(a,b){switch(a){case "isMuted":return{muted:b};case "getVolume":return{volume:b};case "getPlaybackRate":return{playbackRate:b};case "getAvailablePlaybackRates":return{availablePlaybackRates:b};case "getVideoLoadedFraction":return{videoLoadedFraction:b};case "getPlayerState":return{playerState:b};case "getCurrentTime":return{currentTime:b};case "getPlaybackQuality":return{playbackQuality:b};case "getAvailableQualityLevels":return{availableQualityLevels:b};case "getDuration":return{duration:b};
case "getVideoUrl":return{videoUrl:b};case "getVideoEmbedCode":return{videoEmbedCode:b};case "getPlaylist":return{playlist:b};case "getPlaylistIndex":return{playlistIndex:b};case "getOptions":return{options:b};case "getOption":return{option:b}}}
g.Hb=function(a,b){switch(a){case "onReady":return;case "onStateChange":return{playerState:b};case "onPlaybackQualityChange":return{playbackQuality:b};case "onPlaybackRateChange":return{playbackRate:b};case "onError":return{errorCode:b}}return dp.B.Hb.call(this,a,b)};
g.w=function(){dp.B.w.call(this);delete this.b};function gp(){var a=this.f=new rj,b=x(this.ee,this);a.f=b;a.g=null;this.f.channel="widget";if(a=H("WIDGET_ID"))this.f.i=a;this.i=[];this.o=!1;this.j={}}
g=gp.prototype;g.ee=function(a,b){if("addEventListener"==a&&b){var c=b[0];this.j[c]||"onReady"==c||(this.addEventListener(c,hp(this,c)),this.j[c]=!0)}else this.Oc(a,b)};
g.Oc=function(){};
function hp(a,b){return x(function(a){this.sendMessage(b,a)},a)}
g.addEventListener=function(){};
g.jd=function(){this.o=!0;this.sendMessage("initialDelivery",this.Ib());this.sendMessage("onReady");A(this.i,this.Pc,this);this.i=[]};
g.Ib=function(){return null};
function ip(a,b){a.sendMessage("infoDelivery",b)}
g.Pc=function(a){this.o?this.f.sendMessage(a):this.i.push(a)};
g.sendMessage=function(a,b){this.Pc({event:a,info:void 0==b?null:b})};
g.dispose=function(){this.f=null};function jp(a){gp.call(this);this.b=a;this.g=[];this.addEventListener("onReady",x(this.Md,this));this.addEventListener("onVideoProgress",x(this.me,this));this.addEventListener("onVolumeChange",x(this.ne,this));this.addEventListener("onApiChange",x(this.he,this));this.addEventListener("onPlaybackQualityChange",x(this.je,this));this.addEventListener("onPlaybackRateChange",x(this.ke,this));this.addEventListener("onStateChange",x(this.le,this))}
z(jp,gp);g=jp.prototype;g.Oc=function(a,b){if(this.b[a]){b=b||[];if(0<b.length&&sj(a)){var c;c=b;if(ha(c[0])&&!v(c[0]))c=c[0];else{var d={};switch(a){case "loadVideoById":case "cueVideoById":d=uj.apply(window,c);break;case "loadVideoByUrl":case "cueVideoByUrl":d=tj.apply(window,c);break;case "loadPlaylist":case "cuePlaylist":d=vj.apply(window,c)}c=d}wj(c);b.length=1;b[0]=c}this.b[a].apply(this.b,b);sj(a)&&ip(this,this.Ib())}};
g.Md=function(){var a=x(this.jd,this);this.f.b=a};
g.addEventListener=function(a,b){this.g.push({eventType:a,listener:b});this.b.addEventListener(a,b)};
g.Ib=function(){if(!this.b)return null;var a=this.b.getApiInterface();Pa(a,"getVideoData");for(var b={apiInterface:a},c=0,d=a.length;c<d;c++){var e=a[c],f=e;if(0==f.search("get")||0==f.search("is")){var f=e,h=0;0==f.search("get")?h=3:0==f.search("is")&&(h=2);f=f.charAt(h).toLowerCase()+f.substr(h+1);try{var k=this.b[e]();b[f]=k}catch(m){}}}b.videoData=this.b.getVideoData();b.currentTimeLastUpdated_=y()/1E3;return b};
g.le=function(a){a={playerState:a,currentTime:this.b.getCurrentTime(),duration:this.b.getDuration(),videoData:this.b.getVideoData(),videoStartBytes:0,videoBytesTotal:this.b.getVideoBytesTotal(),videoLoadedFraction:this.b.getVideoLoadedFraction(),playbackQuality:this.b.getPlaybackQuality(),availableQualityLevels:this.b.getAvailableQualityLevels(),videoUrl:this.b.getVideoUrl(),playlist:this.b.getPlaylist(),playlistIndex:this.b.getPlaylistIndex(),currentTimeLastUpdated_:y()/1E3,playbackRate:this.b.getPlaybackRate()};
this.b.getProgressState&&(a.progressState=this.b.getProgressState());this.b.getStoryboardFormat&&(a.storyboardFormat=this.b.getStoryboardFormat());ip(this,a)};
g.je=function(a){ip(this,{playbackQuality:a})};
g.ke=function(a){ip(this,{playbackRate:a})};
g.he=function(){for(var a=this.b.getOptions(),b={namespaces:a},c=0,d=a.length;c<d;c++){var e=a[c],f=this.b.getOptions(e);b[e]={options:f};for(var h=0,k=f.length;h<k;h++){var m=f[h],n=this.b.getOption(e,m);b[e][m]=n}}this.sendMessage("apiInfoDelivery",b)};
g.ne=function(){ip(this,{muted:this.b.isMuted(),volume:this.b.getVolume()})};
g.me=function(a){a={currentTime:a,videoBytesLoaded:this.b.getVideoBytesLoaded(),videoLoadedFraction:this.b.getVideoLoadedFraction(),currentTimeLastUpdated_:y()/1E3,playbackRate:this.b.getPlaybackRate()};this.b.getProgressState&&(a.progressState=this.b.getProgressState());ip(this,a)};
g.dispose=function(){jp.B.dispose.call(this);for(var a=0;a<this.g.length;a++){var b=this.g[a];this.b.removeEventListener(b.eventType,b.listener)}this.g=[]};function kp(a,b,c){V.call(this);this.b=a;this.f=b;this.g=c}
z(kp,V);function bp(a,b,c){if(!a.C()){var d=a.b;d.C()||a.f!=d.b||(a={id:a.g,command:b},c&&(a.data=c),d.b.postMessage(M(a),d.g))}}
kp.prototype.w=function(){this.f=this.b=null;kp.B.w.call(this)};function lp(a,b,c){E.call(this);this.b=a;this.g=c;this.i=N(window,"message",x(this.j,this));this.f=new kp(this,a,b);Vb(this,na(F,this.f))}
z(lp,E);lp.prototype.j=function(a){var b;if(b=!this.C())if(b=a.origin==this.g)a:{b=this.b;do{var c;b:{c=a.source;do{if(c==b){c=!0;break b}if(c==c.parent)break;c=c.parent}while(null!=c);c=!1}if(c){b=!0;break a}b=b.opener}while(null!=b);b=!1}if(b&&(a=a.data,w(a))){try{a=od(a)}catch(d){return}a.command&&(b=this.f,b.C()||b.u("command",a.command,a.data))}};
lp.prototype.w=function(){Le(this.i);this.b=null;lp.B.w.call(this)};var mp=!1;function np(a){if(a=a.match(/[\d]+/g))a.length=3}
(function(){if(navigator.plugins&&navigator.plugins.length){var a=navigator.plugins["Shockwave Flash"];if(a&&(mp=!0,a.description)){np(a.description);return}if(navigator.plugins["Shockwave Flash 2.0"]){mp=!0;return}}if(navigator.mimeTypes&&navigator.mimeTypes.length&&(a=navigator.mimeTypes["application/x-shockwave-flash"],mp=!(!a||!a.enabledPlugin))){np(a.enabledPlugin.description);return}try{var b=new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7");mp=!0;np(b.GetVariable("$version"));return}catch(c){}try{b=
new ActiveXObject("ShockwaveFlash.ShockwaveFlash.6");mp=!0;return}catch(c){}try{b=new ActiveXObject("ShockwaveFlash.ShockwaveFlash"),mp=!0,np(b.GetVariable("$version"))}catch(c){}})();function op(a){return(a=a.exec(lb))?a[1]:""}
(function(){if(jf)return op(/Firefox\/([0-9.]+)/);if(L||$c||Zc)return id;if(nf)return op(/Chrome\/([0-9.]+)/);if(of&&!(Vc()||C("iPad")||C("iPod")))return op(/Version\/([0-9.]+)/);if(kf||lf){var a=/Version\/(\S+).*Mobile\/(\S+)/.exec(lb);if(a)return a[1]+"."+a[2]}else if(mf)return(a=op(/Android\s+([0-9.]+)/))?a:op(/Version\/([0-9.]+)/);return""})();function pp(){var a=qp;return new ng(function(b,c){a.aa=function(a){Ld(a)?b(a):c(a)};
a.onError=c;a.Ga=c;Qd("//googleads.g.doubleclick.net/pagead/id",a)})}
;function rp(a,b){this.f=a;this.b=b}
rp.prototype.then=function(a,b,c){try{if(p(this.f))return a?sg(a.call(c,this.f)):sg(this.f);if(p(this.b)){if(!b)return tg(this.b);var d=b.call(c,this.b);return!p(d)&&this.b instanceof vg?tg(this.b):sg(d)}throw Error("Invalid Result_ state");}catch(e){return tg(e)}};
mg(rp);var qp={format:"RAW",method:"GET",timeout:5E3,withCredentials:!0},sp=null;function tp(a){a=a.responseText;if(0!=a.lastIndexOf(")]}'",0))return up(""),sp=new rp(""),"";a=JSON.parse(a.substr(4)).id;up(a);sp=new rp(a);vp(18E5,2);return a}
function wp(a){var b=Error("Unable to load /pagead/id");up("");sp=new rp(void 0,b);0<a&&vp(12E4,a-1);throw b;}
function vp(a,b){I(function(){var a=x(wp,l,b),a=pp().then(tp,a);ug(a,null,t,void 0)},a)}
function up(a){q("yt.www.ads.biscotti.lastId_",a,void 0)}
;function xp(){}
;function yp(){var a;if(a=Cf.get("PREF",void 0)){a=unescape(a).split("&");for(var b=0;b<a.length;b++){var c=a[b].split("="),d=c[0];(c=c[1])&&(zp[d]=c.toString())}}}
ba(yp);var zp=r("yt.prefs.UserPrefs.prefs_")||{};q("yt.prefs.UserPrefs.prefs_",zp,void 0);function Ap(a){if(/^f([1-9][0-9]*)$/.test(a))throw"ExpectedRegexMatch: "+a;}
function Bp(a){if(!/^\w+$/.test(a))throw"ExpectedRegexMismatch: "+a;}
function Cp(a){a=void 0!==zp[a]?zp[a].toString():null;return null!=a&&/^[A-Fa-f0-9]+$/.test(a)?parseInt(a,16):null}
yp.prototype.get=function(a,b){Bp(a);Ap(a);var c=void 0!==zp[a]?zp[a].toString():null;return null!=c?c:b?b:""};
yp.prototype.set=function(a,b){Bp(a);Ap(a);if(null==b)throw"ExpectedNotNull";zp[a]=b.toString()};
yp.prototype.remove=function(a){Bp(a);Ap(a);delete zp[a]};
yp.prototype.clear=function(){zp={}};function Dp(a){for(var b=0;b<a.length;b++){var c=a[b];"send_follow_on_ping_action"==c.name&&c.data&&c.data.follow_on_url&&(c=c.data.follow_on_url)&&mh(c)}}
;function Ep(a){R.call(this,1,arguments);this.Db=a}
z(Ep,R);function Fp(a,b){R.call(this,2,arguments);this.f=a;this.b=b}
z(Fp,R);function Gp(a,b,c,d){R.call(this,1,arguments);this.b=b;this.f=c||null;this.itemId=d||null}
z(Gp,R);function Hp(a,b){R.call(this,1,arguments);this.f=a;this.b=b||null}
z(Hp,R);function Ip(a){R.call(this,1,arguments)}
z(Ip,R);var Jp=new S("ypc-core-load",Ep),Kp=new S("ypc-guide-sync-success",Fp),Lp=new S("ypc-purchase-success",Gp),Mp=new S("ypc-subscription-cancel",Ip),Np=new S("ypc-subscription-cancel-success",Hp),Op=new S("ypc-init-subscription",Ip);var Pp=!1,Qp=[],Rp=[];function Sp(a){a.b?Pp?T(Hi,a):T(Jp,new Ep(function(){T(Op,new Ip(a.b))})):Tp(a.f,a.i,a.g,a.source)}
function Up(a){a.b?Pp?T(Mi,a):T(Jp,new Ep(function(){T(Mp,new Ip(a.b))})):Vp(a.f,a.subscriptionId,a.i,a.g,a.source)}
function Wp(a){Xp(Sa(a.b))}
function Yp(a){Zp(Sa(a.b))}
function $p(a){aq(a.g,a,null)}
function bq(a,b,c,d){aq(a,b,c,d)}
function cq(a){var b=a.itemId,c=a.b.subscriptionId;b&&c&&T(Gi,new yi(b,c,a.b.channelInfo))}
function dq(a){var b=a.b;Xa(a.f,function(a,d){T(Gi,new yi(d,a,b[d]))})}
function eq(a){T(Li,new vi(a.f.itemId));a.b&&a.b.length&&(fq(a.b,Li),fq(a.b,Ni))}
function Tp(a,b,c,d){var e=new vi(a);T(Ei,e);var f={};f.c=a;c&&(f.eurl=c);d&&(f.source=d);c={};(d=H("PLAYBACK_ID"))&&(c.plid=d);(d=H("EVENT_ID"))&&(c.ei=d);b&&gq(b,c);Qd("/subscription_ajax?action_create_subscription_to_channel=1",{method:"POST",Ub:f,V:c,aa:function(b,c){var d=c.response;T(Gi,new yi(a,d.id,d.channel_info));d.show_feed_privacy_dialog&&K("SHOW-FEED-PRIVACY-SUBSCRIBE-DIALOG",a);d.actions&&Dp(d.actions)},
Rb:function(){T(Fi,e)}})}
function Vp(a,b,c,d,e){var f=new vi(a);T(Ji,f);var h={};d&&(h.eurl=d);e&&(h.source=e);d={};d.c=a;d.s=b;(a=H("PLAYBACK_ID"))&&(d.plid=a);(a=H("EVENT_ID"))&&(d.ei=a);c&&gq(c,d);Qd("/subscription_ajax?action_remove_subscriptions=1",{method:"POST",Ub:h,V:d,aa:function(a,b){var c=b.response;T(Li,f);c.actions&&Dp(c.actions)},
Rb:function(){T(Ki,f)}})}
function aq(a,b,c,d){if(null!==b&&(null!==b.b||null!==b.receivePostUpdates||null!==b.f)){var e={};a&&(e.channel_id=a);null!==b.b&&(e.receive_all_updates=b.b);null!==b.receivePostUpdates&&(e.receive_post_updates=b.receivePostUpdates);null!==b.f&&(e.receive_no_updates=b.f);Qd("/subscription_ajax?action_update_subscription_preferences=1",{method:"POST",V:e,onError:function(){c&&c()},
aa:function(){d&&d()}})}}
function Xp(a){if(a.length){var b=Ua(a,0,40);T("subscription-batch-subscribe-loading");fq(b,Ei);var c={};c.a=b.join(",");var d=function(){T("subscription-batch-subscribe-loaded");fq(b,Fi)};
Qd("/subscription_ajax?action_create_subscription_to_all=1",{method:"POST",V:c,aa:function(c,f){d();var h=f.response,k=h.id;if(v(k)&&k.length==b.length){var m=h.channel_info_map;A(k,function(a,c){var d=b[c];T(Gi,new yi(d,a,m[d]))});
a.length?Xp(a):T("subscription-batch-subscribe-finished")}},
onError:function(){d();T("subscription-batch-subscribe-failure")}})}}
function Zp(a){if(a.length){var b=Ua(a,0,40);T("subscription-batch-unsubscribe-loading");fq(b,Ji);var c={};c.c=b.join(",");var d=function(){T("subscription-batch-unsubscribe-loaded");fq(b,Ki)};
Qd("/subscription_ajax?action_remove_subscriptions=1",{method:"POST",V:c,aa:function(){d();fq(b,Li);a.length&&Zp(a)},
onError:function(){d()}})}}
function fq(a,b){A(a,function(a){T(b,new vi(a))})}
function gq(a,b){var c=Id(a),d;for(d in c)b[d]=c[d]}
;var hq,iq=null,jq=null,kq=null,lq=!1;
function mq(){var a=H("PLAYER_CONFIG",void 0);if("1"!=a.privembed)try{var b;try{var c=r("yt.www.ads.biscotti.getId_"),d;if(c)d=c();else{if(!sp){var e=x(wp,l,2);sp=pp().then(tp,e)}d=sp}b=d}catch(f){b=tg(f)}ug(b,null,xp,void 0)}catch(f){cc(f)}"gvn-experiment"==a.args.ps&&(document.body.style.backgroundColor="transparent");if(H("REQUEST_POST_MESSAGE_ORIGIN")){if(!hq){hq=new rj;hq.b=mq;return}hq.origin&&"*"!=hq.origin&&(a.args.post_message_origin=hq.origin)}d=document.referrer;b=H("POST_MESSAGE_ORIGIN");
c=!1;wd("legacy_cast2")&&w(d)&&w(b)&&-1<d.indexOf(b)&&jh(b)&&jh(d)&&(c=!0);window!=window.top&&d&&d!=document.URL&&(a.args.loaderUrl=d);H("LIGHTWEIGHT_AUTOPLAY")&&(a.args.autoplay="1");a.args.autoplay&&wj(a.args);iq=ii("player",a);d=H("POST_MESSAGE_ID","player");H("ENABLE_JS_API")?kq=new jp(iq):H("ENABLE_POST_API")&&w(d)&&w(b)&&(jq=new lp(window.parent,d,b),kq=new dp(iq,jq.f));wd("legacy_cast2")&&((lq=c&&!H("ENABLE_CAST_API"))?a.args.disableCast="1":(a=iq,ko(),Mo=a,Mo.addEventListener("onReady",Oo),
Mo.addEventListener("onRemoteReceiverSelected",Qo),No.push(kc("yt-remote-receiver-availability-change",Po)),No.push(kc("yt-remote-auto-connect",Ro))));H("BG_P")&&(H("BG_I")||H("BG_IU"))&&zc();$d();So=iq;So.addEventListener("SUBSCRIBE",Vo);So.addEventListener("UNSUBSCRIBE",Yo);To.push(uh(Gi,Zo),uh(Li,$o))}
;q("yt.setConfig",$b,void 0);q("yt.setMsg",function(a){ac(Zb,arguments)},void 0);
q("yt.logging.errors.log",function(a,b,c,d,e){c={name:c||H("INNERTUBE_CONTEXT_CLIENT_NAME","WEB"),version:d||H("INNERTUBE_CONTEXT_CLIENT_VERSION",void 0)};e=window&&window.yterr||e||!1;if(a&&e&&!(5<=Wd)){e=a.stacktrace;d=a.columnNumber;var f=r("window.location.href");if(w(a))a={message:a,name:"Unknown error",lineNumber:"Not available",fileName:f,stack:"Not available"};else{var h,k,m=!1;try{h=a.lineNumber||a.Ve||"Not available"}catch(Qc){h="Not available",m=!0}try{k=a.fileName||a.filename||a.sourceURL||
l.$googDebugFname||f}catch(Qc){k="Not available",m=!0}a=!m&&a.lineNumber&&a.fileName&&a.stack&&a.message&&a.name?a:{message:a.message||"Not available",name:a.name||"UnknownError",lineNumber:h,fileName:k,stack:a.stack||"Not available"}}e=e||a.stack;h=a.lineNumber.toString();isNaN(h)||isNaN(d)||(h=h+":"+d);if(!(Vd[a.message]||0<=e.indexOf("/YouTubeCenter.js")||0<=e.indexOf("/mytube.js"))){b={Ub:{a:"logerror",t:"jserror",type:a.name,msg:a.message.substr(0,1E3),line:h,level:b||"ERROR"},V:{url:H("PAGE_NAME",
window.location.href),file:a.fileName},method:"POST"};e&&(b.V.stack=e);for(var n in c)b.V["client."+n]=c[n];if(n=H("LATEST_ECATCHER_SERVICE_TRACKING_PARAMS",void 0))for(var ga in n)b.V[ga]=n[ga];Qd("/error_204",b);Vd[a.message]=!0;Wd++}}},void 0);
q("writeEmbed",mq,void 0);q("yt.www.watch.ads.restrictioncookie.spr",function(a){(a+="mac_204?action_fcts=1")&&mh(a);return!0},void 0);
var nq=bc(function(){Fh("ol");Pp=!0;Rp.push(uh(Di,Sp),uh(Ii,Up));Pp||(Rp.push(uh(Hi,Sp),uh(Mi,Up),uh(Ai,Wp),uh(Bi,Yp),uh(Ci,$p)),Qp.push(kc("subscription-prefs",bq)),Rp.push(uh(Lp,cq),uh(Np,eq),uh(Kp,dq)));yp.getInstance();var a=1<window.devicePixelRatio;if(!!((Cp("f"+(Math.floor(119/31)+1))||0)&67108864)!=a){var b="f"+(Math.floor(119/31)+1),c=Cp(b)||0,c=a?c|67108864:c&-67108865;0==c?delete zp[b]:(a=c.toString(16),zp[b]=a.toString());var b=[],d;for(d in zp)b.push(d+"="+escape(zp[d]));Df("PREF",b.join("&"),
63072E3)}}),oq=bc(function(){var a=iq;
a&&a.sendAbandonmentPing&&a.sendAbandonmentPing();H("PL_ATT")&&(yc=null);for(var a=0,b=Yd.length;a<b;a++){var c=Yd[a];if(!isNaN(c)){var d=r("yt.scheduler.instance.cancelJob");d?d(c):J(c)}}Yd.length=0;a=uc("//static.doubleclick.net/instream/ad_status.js");if(b=document.getElementById(a))pc(a),b.parentNode.removeChild(b);Zd=!1;$b("DCLKSTAT",0);mc(Qp);Qp.length=0;vh(Rp);Rp.length=0;Pp=!1;So&&(So.removeEventListener("SUBSCRIBE",Wo),So.removeEventListener("UNSUBSCRIBE",Yo));So=null;vh(To);To.length=0;
wd("legacy_cast2")&&!lq&&(mc(No),No.length=0,Mo&&(Mo.removeEventListener("onRemoteReceiverSelected",Qo),Mo.removeEventListener("onReady",Oo),Mo=null),yo());Wb(kq,jq);iq&&iq.destroy()});
window.addEventListener?(window.addEventListener("load",nq),window.addEventListener("unload",oq)):window.attachEvent&&(window.attachEvent("onload",nq),window.attachEvent("onunload",oq));var pq=lj.getInstance(),qq=U(pq);qq in qj||(pq.register(),pq.ub.push(kc("yt-uix-init-"+qq,pq.init,pq)),pq.ub.push(kc("yt-uix-dispose-"+qq,pq.dispose,pq)),qj[qq]=pq);})();
