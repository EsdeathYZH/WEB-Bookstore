webpackJsonp([4],{"35Wy":function(t,a){},CSUR:function(t,a){},J56h:function(t,a,s){"use strict";function e(t){s("NFLk")}var r=s("mvHQ"),i=s.n(r),o=s("7t+N"),n=s.n(o),l={name:"navbar",data:function(){return{isLogin:!1,currentUser:{},user:{username:"",password:""},registerUser:{username:"",password:"",password2:""}}},mounted:function(){var t=this;n.a.ajax({type:"get",url:"/profile",success:function(a){"false"===a?t.isLogin=!1:(t.isLogin=!0,t.currentUser=JSON.parse(a))}})},methods:{login:function(){var t=this;return n.a.ajax({type:"post",url:"/login",data:{username:t.user.username,password:t.user.password},success:function(t){"failed"===t?alert("登录失败,请检查用户名或密码！"):(alert("登录成功！"),window.location.reload())},error:function(t){var a=i()(t);alert(a)}}),!1},logout:function(){n.a.ajax({type:"get",url:"/logout",success:function(t){alert("成功注销！"),window.location.reload()},error:function(){alert("服务器错误!")}})},register:function(){var t=this;if(t.registerUser.password!==t.registerUser.password2)return void alert("两次输入密码不一致!");n.a.ajax({type:"post",url:"/register",data:{username:t.registerUser.username,password:t.registerUser.password},success:function(t){"success"===t?(alert("注册成功！"),window.location.reload()):"duplicate"===t?alert("该用户名已存在!"):alert(t)},error:function(){alert("服务器错误!")}})}}},d=function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("nav",{staticClass:"navbar navbar-default navbar-fixed-top",attrs:{role:"navigation"}},[s("div",{staticClass:"container"},[t._m(0,!1,!1),t._v(" "),t._m(1,!1,!1),t._v(" "),s("div",{staticClass:"navbar-collapse collapse"},[s("ul",{staticClass:"nav navbar-nav navbar-right"},[t._m(2,!1,!1),t._v(" "),t._m(3,!1,!1),t._v(" "),t._m(4,!1,!1),t._v(" "),s("li",[t.isLogin?t._e():s("a",{attrs:{href:"#","data-toggle":"modal","data-target":"#register"}},[s("span",{staticClass:"fa fa-user-circle fa-lg",staticStyle:{"font-size":"16px"},attrs:{"aria-hidden":"true"}},[t._v(" 注册")])])]),t._v(" "),s("li",[t.isLogin?t._e():s("a",{attrs:{href:"#","data-toggle":"modal","data-target":"#login"}},[s("span",{staticClass:"fa fa-sign-in fa-lg",staticStyle:{"font-size":"16px"},attrs:{"aria-hidden":"true"}},[t._v(" 登录")])])]),t._v(" "),s("li",[t.isLogin?s("a",{attrs:{href:"../../user_page.html"}},[s("span",{staticClass:"fa fa-user fa-lg",attrs:{"aria-hidden":"true"}},[t._v(" "+t._s(t.currentUser.username))])]):t._e()]),t._v(" "),s("li",[t.isLogin?s("a",{attrs:{href:"#","data-toggle":"modal"},on:{click:t.logout}},[s("span",{staticClass:"fa fa-sign-out fa-lg",staticStyle:{"font-size":"16px"},attrs:{"aria-hidden":"true"}},[t._v(" 注销")])]):t._e()])])])]),t._v(" "),s("div",{staticClass:"modal fade",attrs:{id:"register",role:"dialog","aria-labelledby":"myModalLabel","aria-hidden":"true"}},[s("div",{staticClass:"modal-dialog"},[s("div",{staticClass:"modal-content"},[t._m(5,!1,!1),t._v(" "),s("div",{staticClass:"modal-body"},[s("form",{staticClass:"form-group",attrs:{action:""}},[s("div",{staticClass:"form-group"},[s("label",{attrs:{for:"name"}},[t._v("用户名")]),t._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:t.registerUser.username,expression:"registerUser.username"}],staticClass:"form-control",attrs:{id:"name",type:"text",placeholder:"6-15位字母或数字"},domProps:{value:t.registerUser.username},on:{input:function(a){a.target.composing||t.$set(t.registerUser,"username",a.target.value)}}})]),t._v(" "),s("div",{staticClass:"form-group"},[s("label",{attrs:{for:"password"}},[t._v("密码")]),t._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:t.registerUser.password,expression:"registerUser.password"}],staticClass:"form-control",attrs:{id:"password",type:"password",placeholder:"至少6位字母或数字"},domProps:{value:t.registerUser.password},on:{input:function(a){a.target.composing||t.$set(t.registerUser,"password",a.target.value)}}})]),t._v(" "),s("div",{staticClass:"form-group"},[s("label",{attrs:{for:"password2"}},[t._v("再次输入密码")]),t._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:t.registerUser.password2,expression:"registerUser.password2"}],staticClass:"form-control",attrs:{id:"password2",type:"password",placeholder:"至少6位字母或数字"},domProps:{value:t.registerUser.password2},on:{input:function(a){a.target.composing||t.$set(t.registerUser,"password2",a.target.value)}}})]),t._v(" "),s("div",{staticClass:"text-right"},[s("button",{staticClass:"btn btn-primary",staticStyle:{"margin-top":"0px"},on:{click:t.register}},[t._v("提交")]),t._v(" "),s("button",{staticClass:"btn btn-danger",attrs:{"data-dismiss":"modal"}},[t._v("取消")])]),t._v(" "),s("a",{attrs:{href:"","data-toggle":"modal","data-dismiss":"modal","data-target":"#login"}},[t._v("已有账号？点我登录")])])])])])]),t._v(" "),s("div",{staticClass:"modal fade",attrs:{id:"login"}},[s("div",{staticClass:"modal-dialog"},[s("div",{staticClass:"modal-content"},[t._m(6,!1,!1),t._v(" "),t._m(7,!1,!1),t._v(" "),s("div",{staticClass:"modal-body"},[s("form",{staticClass:"form-group"},[s("div",{staticClass:"form-group"},[s("label",{attrs:{for:"log-name"}},[t._v("用户名")]),t._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:t.user.username,expression:"user.username"}],staticClass:"form-control",attrs:{id:"log-name",type:"text",placeholder:""},domProps:{value:t.user.username},on:{input:function(a){a.target.composing||t.$set(t.user,"username",a.target.value)}}})]),t._v(" "),s("div",{staticClass:"form-group"},[s("label",{attrs:{for:"log-password"}},[t._v("密码")]),t._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:t.user.password,expression:"user.password"}],staticClass:"form-control",attrs:{id:"log-password",type:"password",placeholder:""},domProps:{value:t.user.password},on:{input:function(a){a.target.composing||t.$set(t.user,"password",a.target.value)}}})]),t._v(" "),s("div",{staticClass:"text-right"},[s("button",{staticClass:"btn btn-primary",staticStyle:{"margin-top":"0px"},on:{click:function(a){t.login()}}},[t._v("登录")]),t._v(" "),s("button",{staticClass:"btn btn-danger",attrs:{"data-dismiss":"modal"}},[t._v("取消")])]),t._v(" "),s("a",{attrs:{href:"","data-toggle":"modal","data-dismiss":"modal","data-target":"#register"}},[t._v("还没有账号？点我注册")])])])])])])])},c=[function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"navbar-header"},[s("a",{staticClass:"navbar-brand",attrs:{href:"index.html"}},[s("em",[s("strong",[t._v("BookStore")])])])])},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("form",{staticClass:"navbar-form navbar-left",attrs:{role:"search"}},[s("div",{staticClass:"form-group"},[s("input",{staticClass:"form-control",attrs:{type:"text",placeholder:"搜索图书"}})]),t._v(" "),s("button",{staticClass:"btn btn-default",attrs:{type:"button","aria-label":"Left Align"}},[s("span",{staticClass:"fa fa-search",attrs:{"aria-hidden":"true"}})])])},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("li",{staticClass:"active"},[s("a",{attrs:{href:"../../index.html"}},[s("span",{staticClass:"fa fa-home fa-lg",attrs:{"aria-hidden":"true"}})])])},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("li",[s("a",{attrs:{href:"../../discover.html"}},[s("span",{staticClass:"fa fa-compass fa-lg",attrs:{"aria-hidden":"true"}})])])},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("li",[s("a",{attrs:{href:"../../shopping_cart.html"}},[s("span",{staticClass:"fa fa-shopping-cart fa-lg",attrs:{"aria-hidden":"true"}})])])},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"modal-header"},[s("button",{staticClass:"close",attrs:{"data-dismiss":"modal","aria-hidden":"true"}},[s("span",[t._v("×")])]),t._v(" "),s("h1",{staticClass:"text-center"},[t._v("注册")])])},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"modal-body"},[s("button",{staticClass:"close",attrs:{"data-dismiss":"modal"}},[s("span",[t._v("×")])])])},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"modal-title"},[s("h1",{staticClass:"text-center"},[t._v("登录")])])}],u={render:d,staticRenderFns:c},v=u,m=s("VU/8"),p=e,f=m(l,v,!1,p,"data-v-2fd1bed2",null);a.a=f.exports},Mx7Y:function(t,a,s){"use strict";function e(t){s("35Wy")}var r={name:"book-order",props:["imagePath","name","price","amount","address","receiver","time","isPay"],data:function(){return{datetime:""}},mounted:function(){this.datetime=new Date(time).format("dd/MM/yyyy hh:mm:ss")}},i=function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"container",attrs:{id:"order"}},[s("div",{staticClass:"order-info row"},[s("div",{staticClass:"thumbnail col-md-4"},[s("img",{attrs:{src:t.imagePath}})]),t._v(" "),s("div",{staticClass:"col-md-3"},[s("div",{staticClass:"book-name"},[s("a",{attrs:{href:"#"}},[t._v(t._s(t.name))])]),t._v(" "),s("div",{staticClass:"book-price",staticStyle:{color:"red"}},[t._v(t._s(t.price))]),t._v(" "),s("div",{staticClass:"book-amount"},[t._v(t._s("数量："+t.amount+" 本"))])]),t._v(" "),s("div",{staticClass:"receive-info col-md-5"},[s("div",{staticClass:"location"},[t._v("收货地址:"+t._s(t.address))]),t._v(" "),s("div",{staticClass:"receiver"},[t._v("收件人:"+t._s(t.receiver))]),t._v(" "),t.isPay?s("div",{staticClass:"time"},[t._v("下单时间:"+t._s(t.time.year+1900+"年"+(t.time.month+1)+"月"+t.time.date+"日"+t.time.hours+"时"+t.time.minutes+"分"))]):t._e()])])])},o=[],n={render:i,staticRenderFns:o},l=n,d=s("VU/8"),c=e,u=d(r,l,!1,c,"data-v-7610ab76",null);a.a=u.exports},NFLk:function(t,a){},Ozve:function(t,a,s){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var e=s("7+uW"),r=s("J56h"),i=s("bux7");e.a.config.productionTip=!1,new e.a({el:"#navbar",template:"<navbar/>",components:{navbar:r.a}}),new e.a({el:"#shopping_cart",template:"<shopping_cart/>",components:{shopping_cart:i.a}})},bc9N:function(t,a,s){"use strict";(function(t){var e=s("Mx7Y");a.a={name:"shopping-cart",data:function(){return{items:[],isLogin:!1,currentUser:{},order:{receiver:"",address:"",amount:""}}},mounted:function(){var a=this;t.ajax({type:"get",url:"/profile",success:function(s){"false"===s?a.isLogin=!1:(a.isLogin=!0,a.currentUser=JSON.parse(s),t.ajax({type:"get",url:"/shoppingcart",success:function(t){if("false"===t)alert("获取购物车数据失败!");else{a.items=JSON.parse(t);for(var s=0;s<a.items.length;s++)a.items[s].receiver=a.currentUser.username}},error:function(t){alert("服务器错误!获取购物车信息失败!")}}))}})},methods:{pay:function(a,s){var e=this;t.ajax({type:"post",url:"/pay",data:{book_id:a.book.id,user_id:e.currentUser.id,amount:a.amount,receiver:a.receiver,address:a.address},success:function(t){"success"===t?(alert("付款成功!"),e.items.splice(s,1)):alert("购买失败!")},error:function(){alert("服务器错误!")}})},modify:function(a,s){var e=this;t.ajax({type:"post",url:"/shoppingcart",data:{method:"modify",book_id:a.book.id,user_id:e.currentUser.id,amount:a.amount,receiver:a.receiver,address:a.address},success:function(t){"success"===t?alert("修改成功!"):alert("修改失败!")},error:function(){alert("服务器错误!")}})},remove:function(a,s){var e=this;t.ajax({type:"post",url:"/shoppingcart",data:{method:"remove",id:a.book.id},success:function(t){"success"===t?(alert("删除成功!"),e.items.splice(s,1)):alert("删除失败!")},error:function(){alert("服务器错误!")}})}},components:{BookOrder:e.a}}}).call(a,s("7t+N"))},bux7:function(t,a,s){"use strict";function e(t){s("CSUR")}var r=s("bc9N"),i=function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{attrs:{id:"shopping_cart"}},[t.isLogin?t._e():s("h1",{staticStyle:{"padding-top":"100px","padding-left":"400px"}},[t._v("请先登录哦~")]),t._v(" "),0===t.items.length&&t.isLogin?s("h1",{staticStyle:{"padding-top":"100px","padding-left":"400px"}},[t._v("购物车为空~")]):t._e(),t._v(" "),t._l(t.items,function(a,e){return t.isLogin?s("div",{staticClass:"order-row row"},[s("div",{staticClass:"col-md-9"},[s("BookOrder",{attrs:{name:a.book.bookname,receiver:a.receiver,imagePath:a.book.imagePath,price:a.book.price,amount:a.amount,address:a.address,isPay:!1}})],1),t._v(" "),s("div",{staticClass:"col-md-3"},[s("div",{staticClass:"btn btn-success",on:{click:function(s){t.pay(a,e)}}},[t._v("付款")]),t._v(" "),s("div",{staticClass:"btn btn-danger",on:{click:function(s){t.remove(a,e)}}},[t._v("删除订单")]),t._v(" "),s("div",{staticClass:"btn btn-info",attrs:{"data-toggle":"modal","data-target":"#modify"}},[t._v("修改订单")])])]):t._e()}),t._v(" "),s("div",{staticClass:"modal fade",attrs:{id:"modify"}},[s("div",{staticClass:"modal-dialog"},[s("div",{staticClass:"modal-content"},[t._m(0,!1,!1),t._v(" "),t._m(1,!1,!1),t._v(" "),s("div",{staticClass:"modal-body"},[s("form",{staticClass:"form-group",attrs:{action:""}},[s("div",{staticClass:"form-group"},[s("label",{attrs:{for:"amount"}},[t._v("数量")]),t._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:t.order.amount,expression:"order.amount"}],staticClass:"form-control",attrs:{id:"amount",type:"text",placeholder:""},domProps:{value:t.order.amount},on:{input:function(a){a.target.composing||t.$set(t.order,"amount",a.target.value)}}})]),t._v(" "),s("div",{staticClass:"form-group"},[s("label",{attrs:{for:"address"}},[t._v("收货地址")]),t._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:t.order.address,expression:"order.address"}],staticClass:"form-control",attrs:{id:"address",type:"text",placeholder:""},domProps:{value:t.order.address},on:{input:function(a){a.target.composing||t.$set(t.order,"address",a.target.value)}}})]),t._v(" "),s("div",{staticClass:"form-group"},[s("label",{attrs:{for:"receiver"}},[t._v("收件人")]),t._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:t.order.receiver,expression:"order.receiver"}],staticClass:"form-control",attrs:{id:"receiver",type:"text",placeholder:""},domProps:{value:t.order.receiver},on:{input:function(a){a.target.composing||t.$set(t.order,"receiver",a.target.value)}}})]),t._v(" "),s("div",{staticClass:"text-right"},[s("button",{staticClass:"btn btn-primary",staticStyle:{"margin-top":"0px"},on:{click:t.modify}},[t._v("修改")]),t._v(" "),s("button",{staticClass:"btn btn-danger",attrs:{"data-dismiss":"modal"}},[t._v("取消")])])])])])])])],2)},o=[function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"modal-body"},[s("button",{staticClass:"close",attrs:{"data-dismiss":"modal"}},[s("span",[t._v("×")])])])},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"modal-title"},[s("h1",{staticClass:"text-center"},[t._v("修改订单")])])}],n={render:i,staticRenderFns:o},l=n,d=s("VU/8"),c=e,u=d(r.a,l,!1,c,"data-v-1b3a9b1e",null);a.a=u.exports}},["Ozve"]);
//# sourceMappingURL=shopping_cart.9906616115db40e357c4.js.map