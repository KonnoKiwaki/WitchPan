import{_ as F,G as z,o as t,c as o,i as n,N as c,l as S,a as s,k as r,F as f,t as _,r as G,y as L,z as D,e as p,$ as M,f as U,s as Z,E as x,h as H,j as k,w as v,q as J,Z as w}from"./index-cd067b4e.js";import{B as K}from"./Table-58a1515f.js";import{_ as I}from"./ShareFile.vue_vue_type_style_index_0_scoped_ae51d12c_lang-93d12c37.js";import{l as P}from"./share-d89e6911.js";const Q=[{label:"分享文件",prop:"filename",scopedSlots:"filename",ellipsis:!0,minWidth:"48%"},{label:"分享时间",prop:"createTime",minWidth:"25%"},{label:"操作",prop:"expireTime",scopedSlots:"RowAction",minWidth:"25%"},{label:"浏览次数",prop:"browseCount",scopedSlots:"BrowseCount",minWidth:"12%"}];const T=a=>(L("data-v-62f7c9b2"),a=a(),D(),a),X={class:"detail-main"},Y=T(()=>s("div",{class:"detail-title"},[s("span",null,"分享详情")],-1)),ee={key:0,class:"muti-file"},se={key:1,class:"detail-content"},te={class:"filename"},le={class:"text"},oe={key:0,class:"content-item"},ae={class:"label"},ie={class:"value"},ne={key:1,class:"divider"},ce={key:2,class:"detail-empty"},de=T(()=>s("img",{src:"https://nd-static.bdstatic.com/m-static/v20-main/home/img/empty-folder.55c81ea2.png",alt:""},null,-1)),re=T(()=>s("p",null,"选中文件，查看详情",-1)),_e=[de,re],ue={name:"ShareDetail"},he=Object.assign(ue,{props:{value:{type:Array,default:[]}},setup(a){const g=a,m=[{label:"分享时间",key:"createTime"},{label:"有效期",key:"expireTime"},{label:"提取码",key:"code"},{label:"divider",key:"divider"},{label:"浏览",key:"browseCount"},{label:"保存",key:"saveCount"},{label:"下载",key:" downloadCount"}],l=z(()=>g.value.length===1?g.value[0]:null);return(y,C)=>(t(),o("div",X,[Y,!l.value&&a.value.length>1?(t(),o("div",ee,[n(c,{"icon-name":"folder_big",width:128})])):S("",!0),l.value?(t(),o("div",se,[s("div",te,[l.value.cover?(t(),r(c,{key:0,cover:l.value.cover},null,8,["cover"])):(t(),o(f,{key:1},[l.value.folderType==0?(t(),r(c,{key:0,"file-type":l.value.fileType},null,8,["file-type"])):(t(),r(c,{key:1,"icon-name":"folder_2"}))],64)),s("span",le,_(l.value.filename),1)]),(t(),o(f,null,G(m,u=>(t(),o(f,null,[u.label!=="divider"?(t(),o("div",oe,[s("div",ae,_(u.label),1),s("div",ie,_(l.value[u.key]||0),1)])):(t(),o("div",ne))],64))),64))])):S("",!0),a.value.length===0?(t(),o("div",ce,_e)):S("",!0)]))}}),pe=F(he,[["__scopeId","data-v-62f7c9b2"]]);const $=a=>(L("data-v-f7872a27"),a=a(),D(),a),ve={class:"share-container"},fe={class:"share-header"},me={key:0,class:"top"},ye=$(()=>s("span",{class:"title"},"链接分享",-1)),be=$(()=>s("span",{class:"content"},"(分享失败超过1年以上的链接记录将被自动清理)",-1)),ke=[ye,be],we={key:1,class:"actions"},$e={class:"share-body"},ge={class:"left-container"},Ce={class:"nav"},Se=$(()=>s("span",{class:"txt"},"全部文件",-1)),xe={class:"count"},Te={class:"file-list"},Be={class:"file-item"},Ie={class:"filename"},Fe={class:"text",style:{"padding-left":"5px"}},Le={class:"hover-hidden"},De={class:"right-detail"},Ne=$(()=>s("div",{class:"delete-info"},[s("div",{class:"info"},[s("span",{class:"text"},"取消分享后，该条分享记录将被删除，好友将无法再访问此分享链接。 您确认要取消分享吗？")])],-1)),Re={name:"Share"},je=Object.assign(Re,{emits:["addFile"],setup(a,{emit:g}){const m=p(),l=p([]),y=p([]),C=p(0),{toClipboard:u}=M(),N=p(document.location.origin+"/shareCheck/"),B=[{title:"复制链接",label:"copyLink",showLabel:!0,icon:"link",isShow:e=>!w(e)||w(e)&&e.length==1,onClick:e=>{q(e)}},{title:"取消分享",label:"cancelShare",showLabel:!0,icon:"stop",onClick:e=>{E(e)}}],d=U({show:!1,title:"确认取消分享",showCancel:!0,buttons:[{type:"primary",text:"确认",click:()=>{V()}}]}),R=e=>P(e);function j({resultTotal:e}){C.value=e}function A(){var e;(e=m.value)==null||e.reload()}function W(e){l.value=e}const E=e=>{w(e)?y.value=e.map(b=>b.id).join(","):y.value=e.id,d.show=!0},V=()=>{Z.delete("/api/share/cancelShare/"+y.value).then(e=>{e.data&&(x.success("取消外链分享成功"),A(),d.show=!1)}).catch(e=>{x.error("取消外联分享失败")})},q=async e=>{w(e)&&(e=e[0]),await u(`链接: ${N.value}${e.id} 提取码: ${e.code}`),x.success("复制成功")};return(e,b)=>{const O=H("el-scrollbar");return t(),o(f,null,[s("div",ve,[s("div",fe,[l.value.length==0?(t(),o("div",me,ke)):(t(),o("div",we,[n(k(I),{value:l.value,mode:"btns",actions:B},null,8,["value"])]))]),s("div",$e,[s("div",ge,[s("div",Ce,[Se,s("span",xe,"已加载"+_(C.value)+"个",1)]),s("div",Te,[n(k(K),{ref_key:"shareTableRef",ref:m,columns:k(Q),"row-key":h=>h.id,request:R,onSelectChange:W,onFetchSuccess:j},{filename:v(({index:h,row:i})=>[s("div",Be,[(i.fileType==3||i.fileType==1)&&i.status==2?(t(),r(c,{key:0,cover:i.fileCover,width:32},null,8,["cover"])):(t(),o(f,{key:1},[i.folderType==0?(t(),r(c,{key:0,"file-type":i.fileType},null,8,["file-type"])):(t(),r(c,{key:1,"icon-name":"folder_2",width:40}))],64)),s("span",Ie,[s("span",Fe,_(i.filename),1)])])]),RowAction:v(({index:h,row:i})=>[n(k(I),{value:i,mode:"row",actions:B},null,8,["value"])]),BrowseCount:v(({index:h,row:i})=>[s("span",Le,_(i.browseCount),1)]),_:1},8,["columns","row-key"])])]),s("div",De,[n(O,{"wrap-style":"position: absolute;left:0;right:0;"},{default:v(()=>[n(pe,{value:l.value},null,8,["value"])]),_:1})])])]),n(J,{show:d.show,title:d.title,buttons:d.buttons,"show-cancel":!0,width:"400px",onClose:b[0]||(b[0]=h=>d.show=!1)},{default:v(()=>[Ne]),_:1},8,["show","title","buttons"])],64)}}}),qe=F(je,[["__scopeId","data-v-f7872a27"]]);export{qe as default};