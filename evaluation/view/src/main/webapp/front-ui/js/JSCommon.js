$.fn.extend({ajaxLoading:function(){var C=this.parent().outerWidth(true);var A=this.parent().outerHeight(true);var B="";B+='<div class="datagrid-mask-side" style="opacity:0.4;position:absolute;left: '+(C/2)+"px; top: "+(A/2+50)+'px;width:160px;height:50px;background-color:#28b0c8;">&nbsp;</div>';B+='<div class="datagrid-mask-side" style="padding:5px 10px;color:#fff;font:bold 13px normal;position:absolute;left: '+(C/2)+"px; top: "+(A/2+50)+'px;"><img style="padding-left:60px" src="/Images/icons/loading.gif" /><br/>正在处理，请稍候。。。</div>';this.append(B)},ajaxDialogLoading:function(){var C=this.parent().outerWidth(true)-160;var A=120;var B="";B+='<div class="datagrid-mask-side" style="opacity:0.4;position:absolute;left: '+(C/2)+"px; top: "+(A)+'px;width:160px;height:50px;background-color:#28b0c8;">&nbsp;</div>';B+='<div class="datagrid-mask-side" style="padding:5px 10px;color:#fff;font:bold 13px normal;position:absolute;left: '+(C/2)+"px; top: "+(A)+'px;"><img style="padding-left:60px" src="/Images/icons/loading.gif" /><br/>正在处理，请稍候。。。</div>';this.append(B)},ajaxLoadEnd:function(){this.parent().find(".datagrid-mask-side").remove();setTimeout($.SEM.doResize,500)},serializeJson:function(){var A={};$(this.serializeArray()).each(function(){A[this.name]=this.value});return A},getUrlValues:function(){var A="";var B=this.find("input[type=text],input[type=number],input[type=password],input[type=radio]:checked,input[type=checkbox]:checked,input[type=hidden],select,textarea");B.each(function(){switch($(this).attr("type")){case"checkbox":A+=$(this).attr("name")+"=99&";break;default:A+=$(this).attr("name")+"="+$(this).val()+"&";break}});if(A.length>0){A=A.substr(0,A.length-1)}this.pageValues=A;return this},getUrlText:function(){this.pageValue=this.val();return this},requestType:function(A){this.reqType=A;return this},callBackFunction:function(A){this.callBackFunction=A;return this},doAjax:function(G){var A=this;var F;var E=A.pageValues;var B=typeof(this.reqType)==="undefined"?"GET":this.reqType.toLocaleUpperCase();if(E){var H=E.split("&");$.each(H,function(K,J){if(J.indexOf("</script")!=-1||J.indexOf("script/>")!=-1){var I=J.split("=");if(I.length>1){F=I[1]}return false}})}else{E=A.jsonValues;$.each(A.jsonValues,function(I,J){str=""+J;if(str.indexOf("</script")!=-1||str.indexOf("script/>")!=-1){F=str;return false}})}if(F){F="有异常字符："+F.replace(">","&gt;").replace("<","&lt;");A.callBackFunction(F);return}if(B!=="GET"){if(typeof(E)=="string"&&E.lastIndexOf("=")==E.indexOf("=")){E=E.substring(E.indexOf("="))}if(typeof(E)==="object"){var D="{";for(var C in E){D+=C+=':"'+E[C]+'",'}E=(D.length>0?D.substr(0,D.length-1):"")+"}"}if(typeof(this.jsonValues)!=="undefined"){E=this.jsonValues}}$.ajax({cache:false,headers:{"Content-Type":typeof(this.requestHeaderType)==="undefined"?"application/x-www-form-urlencoded":this.requestHeaderType},type:B,url:$.rootURL+G+(B==="GET"?"?"+E:""),data:B==="GET"?"{}":E,dataType:"json",success:function(I){if(A.callBackFunction){A.callBackFunction(I)}},error:function(I,J){switch(J){case"error":if(I.status===401){alert($.parseJSON(I.responseText).Message);window.location=$.rootURL}else{if(typeof(I)==="string"){alert(I)}else{$("div.datagrid-mask-side").remove();alert(I.responseText)}}break;default:break}}});return this},selectTreeNode:function(B,E){var C=$(this);if($(this).attr("class")=="treeview"){var D=C.treeview("getUnselected");var A=[];$.each(D,function(H,G){var F="";switch(B){case"text":F=G.text;break;case"href":F=G.href;break;case"tags":F=G.tags;break}if(F==E){A.push(G)}});C.treeview("selectNode",[A,{silent:false}])}},bindNodeClick:function(A){$(this).find("jmnode").bind("click",A)},validNumber:function(){$(this).css("ime-mode","disabled");$(this).bind("keydown",function(A){var B=A.keyCode;if(B>=48&&B<=57){return true}if(B>=96&&B<=105){return true}if(B==8||B==190){return true}return false})},clearValidNameStatus:function(A){$(this).bootstrapValidator("updateStatus",A,"NOT_VALIDATED")}});$.fn.modal.Constructor.prototype.enforceFocus=function(){};Array.prototype.indexOf=function(B){for(var A=0,C;C=this[A];A++){if(C==B){return A}}return -1};Array.prototype.lastIndexOf=function(B){for(var A=this.length-1,C;C=this[A];A--){if(C==B){return A}}return -1};String.prototype.trim=function(){return this.replace(/(^\s*)|(\s*$)/g,"")};(function(A){A.extend({rootURL:"/",pageSize:10,pageList:[10,20,50,100],getServerJson:function(E,H,B,F,G){var C=jQuery.extend(true,{},H);for(var D in C){if(isNaN(C[D])||D.toLowerCase()=="password"){C[D]="%"+A.base64_encode(C[D])+"%"}}A.ajax({async:G==null?true:G,type:"Get",contentType:"application/json; charset=utf-8",url:A.rootURL+E,data:C==null?"{}":C,dataType:"json",success:function(I){if(B){B(I);setTimeout(A.SEM.doResize,500)}},error:function(I,J){if(F){F(I,J)}else{if(I.status==401){alert(A.parseJSON(I.responseText).Message);window.location=A.rootURL}else{}}}})},base64_encode:function(C){var D=CryptoJS.enc.Utf8.parse(C);var B=CryptoJS.enc.Base64.stringify(D);return B},base64_decode:function(C){var B=CryptoJS.enc.Base64.parse(C);return B.toString(CryptoJS.enc.Utf8)},plupload:function(H,I,J,C,B){var F=[{title:"files",extensions:"jpeg,jpg,gif,png,rar,zip,txt,doc,docx,xls,xlsx,ppt,pptx,pdf"}];var D=["？","~","！","#","￥","%","…","&","*","$","^","《","》","，","。"," ","：","；","、"];if(C&&C!=""){F[0].extensions=C}var G=A("#"+J);var E=new plupload.Uploader({runtimes:"html5,flash,silverlight,html4",browse_button:H,url:I,flash_swf_url:"../Content/plupload/js/Moxie.swf",silverlight_xap_url:"../Content/plupload/js/Moxie.xap",multi_selection:false,filters:{max_file_size:"100mb",mime_types:F},init:{FilesAdded:function(M,L){var K="";plupload.each(L,function(N){if(N.name.lastIndexOf(".")!=N.name.indexOf(".")){M.removeFile(N);K="文件名"+N.name+"包含多余的."}A.each(D,function(P,O){if(N.name.indexOf(O)!=-1){M.removeFile(N);K="文件名"+N.name+'不能包含字符"'+O+'"'}})});if(K.length>0){A.ymessager.alert("提示",K,"info");return}plupload.each(L,function(N){var O='<table class="uploadProgress"><tr style="line-height:16px;"><td style="border:none;padding:0px;">';O+='<div id="'+N.id+'" class="progress progress-striped active" style="width:200px;height:14px;margin:0px;">';O+='<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: '+N.percent+'%;">';O+='<div class="progress-bar-label" style="color:#fff;">'+N.percent+"%</div>";O+="</div>";O+="</div>";O+='</td><td style="border:none;padding:0px;">'+N.name+" ("+plupload.formatSize(N.size)+")</td></tr></table>";G.html(O+G.html())});E.start()},FileUploaded:function(O,L,M){if(B){B(O,L,M)}else{var N=jQuery.parseJSON(M.response);if(N==null||N.result==""){A.ymessager.popupTip("上传成功","success");if(G.find("#h5"+L.id).size()==0){A("#"+L.id).parents("table").remove();var K='<h5 id="h5'+L.id+'" style="margin-left:50px;color:blue;">'+L.name+"</h5>";G.html(K+G.html())}}else{A.ymessager.popupTip(N.result,"fail")}}},UploadProgress:function(M,K){var L=G.find("#"+K.id);A(L).find(".progress-bar").width(K.percent+"%");A(L).find(".progress-bar-label").html(K.percent+"%")},UploadComplete:function(L,K){},Error:function(L,K){A.ymessager.popupTip(K.message,"fail")}}});E.init();return E}})})(jQuery);(function(A){A.BootValidFormater={Numberic:/^-?[0-9]{0,9}(\.[0-9]{1,3})?$/,Integer:/^-?[0-9]{0,9}$/};A.SEM={ComboAllValue:"--请选择--",doResize:function(){var B=A(window).height()-95;var C=A("#mainFrameContainer").parent().height();if(B<C){B=C+20}A("#mainMenuContainer").height(B);var D=A(document).width();if((A("#mainFrameContainer").width()+A("#mainMenuContainer").width())<A(window).width()){D=A(window).width()}A(".masterHeader").width(D);A(".masterHeaderSpliter").width(D)},changeDate:function(B,C){if(A("#"+B).find("input[name="+C+"]").val().length>0){A("#"+B).data("bootstrapValidator").updateStatus(C,"VALID",null)}},setContainerDisable:function(B){if(A("#"+B).find(".containeritem").length==0){var C=A("#"+B).find("input[type=text],input[type=number],input[type=password],input[type=radio],input[type=checkbox],select,textarea,.select2-selection__rendered,.select2-selection");C.each(function(E,D){if(A(D).attr("disabled")){A(D).addClass(" containerdisable")}else{A(D).attr("disabled",true)}A(D).addClass(" containeritem")})}},cleanDisableContainer:function(B){if(A("#"+B).find(".containeritem").length>0){var C=A("#"+B).find("input[type=text],input[type=number],input[type=password],input[type=radio],input[type=checkbox],select,textarea,.select2-selection__rendered,.select2-selection");C.each(function(E,D){if(A(D).hasClass("containerdisable")){A(D).removeClass("containerdisable")}else{A(D).removeAttr("disabled")}A(D).removeClass("containeritem")})}}};window.history.forward(1);window.onresize=function(){setTimeout(A.SEM.doResize,500)}})(jQuery);