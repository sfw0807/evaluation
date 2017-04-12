// 初始化httpservice对象
if (!window.httpservice) {
	httpservice = {}
}

// 为httpservice对象初始化成员工具类
httpservice.tools = {
	alert : function(level, msg) {
		try {
			eval("alertTool." + level + "('" + msg + "')");
		} catch (e) {
			alert(msg);
		}
	}
}

//为httpservice对象初始化成员对象html
httpservice.html = (function(data) {
	var _internal;

	// 定义内置对象，html对象实例化后返回
	var internalFunction = function(obj) {
		_internal = obj;
	};

	// 为html内置对象初始化成员方法
	internalFunction.prototype = {
		// 初始化
		skiphtml : function(url) {
			load(url);
		},
		_dom:function(){
			return _internal.dom;
		}
		
	}

	function params(url){
		var _params={};
		url.substring(url.indexOf(".html?")+6)
				.split('&')
				.forEach(function(item){
					var _itm=item.split('=');
					_params[_itm[0]]=_itm[1];
				})
		return _params;
	}
	
	// 初始化html
	function load(url) {
		// 拉取页面
		url += (url.indexOf('?') == -1 ? '?' : '&') + "timestamp="
		+ new Date().getTime();
		$("#" + _internal.dom).data("_param_",params(url));
		$("#" + _internal.dom).load(VIEW_ROOT + url, function(response, status, xhr) {
			if(xhr.status == 400){
				// window.location.href= VIEW_ROOT;
				alertTool.error("请求发送失败!");
			}
		});
	}

	return internalFunction;
})(window);

// 为httpservice初始化成员对象 ajax
httpservice.ajax = (function(data) {
	var _internal = null;

	// ajax 内置返回对象
	var internalFunction = (function(obj) {
		
	});

	// 为内置对象初始化成员方法
	internalFunction.prototype = {
		submitForm : function(opt) { // 提交form

			// 转换参数
			var _opt = {
				async : opt.async !== false,
				method : 'POST',
				success : opt.success,
				failure : opt.failure,
				formObj : opt.formObj,
				endpoint : opt.endpoint
			}

			var form = _opt.formObj;
			var axajData = opt.data;
			var data = {};
			if(axajData){
				$.extend(data,form.serializeJson(),axajData);
			}else{
				data = form.serializeJson();
			}
			_opt.data = data;

			// submit时固定发送post请求
			doAjaxRequest(_opt);
		},
		getRequest : function(opt) { // 请求

			if (!!!opt.endpoint) {
				httpservice.tools.alert('error', 'endpoint 属性不能为空!');
				return;
			}

			var _opt = {
				async : opt.async !== false,
				//method : 'GET',
				method : 'POST',
				success : opt.success,
				failure : opt.failure,
				endpoint : opt.endpoint
			};
			
			var data = opt.data;
			if(!data){
				data = {};
			}
			data.page = opt.page || 0;
			data.size = opt.size || 0;
			_opt.data = data;

			doAjaxRequest(_opt);
		},
		doPost : function(opt) { // 请求

			if (!!!opt.endpoint) {
				httpservice.tools.alert('error', 'endpoint 属性不能为空!');
				return;
			}

			var _opt = {
				async : opt.async !== false,
				method : 'POST',
				success : opt.success,
				failure : opt.failure,
				endpoint : opt.endpoint
			};

			var data = opt.data;
			data.page = opt.page || 0;
			data.size = opt.size || 0;

			_opt.data = data;

			doAjaxRequest(_opt);
		}
	}

	// 发送ajax 请求
	function doAjaxRequest(opt) {
		// 初始化发送ajax请求的一些必要参数信息
		var async = opt.async !== false, data = opt.data || {}, success = opt.success
				|| doSuccess, failure = opt.failure || doFailure;

		var _url = ROOT + opt.endpoint;

		// 不管了,无论POST GET 都增加时间戳吧
		_url += (_url.indexOf('?') == -1 ? '?' : '&') + "timestamp="
				+ new Date().getTime();

		// 发送ajax请求
		$.ajax({
			async : async,
			type : opt.method,
			url : _url,
			data : data,
			dataType : 'json',
			success : function(msg) {
				// 每次请求完成之后,
				if (msg.success) {
					success(msg.data);
				} else {
					failure(msg.errorMessage);
				}
			},
			error : function(textStatus) {
				if(textStatus.status == 400){
					//TODO  window.location.href= ROOT;
				}
				failure("请求发送失败!");
			}
		});
	}

	// 默认的成功回调函数
	function doSuccess(data) {
		httpservice.tools.alert("info", "操作成功!");
	}

	// 默认的失败回调函数
	function doFailure(str) {
		httpservice.tools.alert("error", "发送请求失败,请联系系统管理员!");
	}
	return internalFunction;
})(window);

// 
httpservice.ajaxEnt = new httpservice.ajax({

});

httpservice.htmlEnt = new httpservice.html({
	dom : "rightRefreshDiv"
});

(function($) {
	$.fn.serializeJson = function() {
		var serializeObj = {};
		var array = this.serializeArray();
		var str = this.serialize();
		$(array).each(
				function() {
					if (serializeObj[this.name]) {
						if ($.isArray(serializeObj[this.name])) {
							serializeObj[this.name].push(this.value);
						} else {
							serializeObj[this.name] = [
									serializeObj[this.name], this.value ];
						}
					} else {
						serializeObj[this.name] = this.value;
					}
				});
		return serializeObj;
	};
})(jQuery);

$.fn.extend({
	namespace : function() {
		var api = {
			currentLayout : function() {
				var dom = this.root;
				while (!dom.attr('data-layout-id')) {
					dom = dom.parent();
				}
				
				return dom;
			},
			getViewParam : function() {
				var dom = this.currentLayout();
				
				return dom.data('vp');
			},
			skiphtml : function(url) {
				window.httpservice.htmlEnt.skiphtml(url);
			},
			submitForm : function(opt) {
				window.httpservice.ajaxEnt.submitForm(opt);
			},
			getRequest : function(opt) {
				window.httpservice.ajaxEnt.getRequest(opt);
			},
			getParam:function(name){
				//"_param_"
				//$("#" + _internal.dom).data   我们从这个数据缓存上拿到参数
				return $("#" + window.httpservice.htmlEnt._dom()).data("_param_")[name];
			},
			confirm:function(fn,title){
				$.confirm({
    			    title: false,
    			    content: title?title:'确定删除？',
    			    confirm: function(){
    			    	fn();
    			    },
    			    cancel: function(){
    			    },
    			    confirmButton: '确定',
    			    cancelButton: '撤销',
    			    onOpen: function(){
    			    }
    			});
			},
			getCodes:function(fn,code,type){
				this.getRequest({
					endpoint : "/dictionary/getDictionaryByCode",
					data : {"code":code},
					success : function(data) {
						 return fn(data);
					},
					failure : function(data) {
						alertTool.error(data);
						return fn({});
					}
				});
			},
			modal : function(){
				return {
					parent : {},
					/**
					 * modalOpts : {
					 * id : //modal id,
					 * title : // title,
					 * hidden :  // fn(e(事件对象),array(弹出页面的返回参数))  
					 * 监听函数，当弹出页面关闭的时候能够获得通知
					 * opt : {}  //modal options
					 * }
					 */
					open : function(page,modalOpts,url,params){
						var divId=modalOpts.id;
						var template='<div id="'+divId +'" class="modal fade" tabindex="-1" role="dialog">'
						+'<div class="modal-dialog" style="width:80%" role="document">'
					    +'<div class="modal-content">'
					    +'<div class="modal-header">'
				        +'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
				        +'<span aria-hidden="true">&times;</span></button>'
				        +'<h4 class="modal-title" >'+modalOpts.title+'</h4>'
				        +'</div>'
						+'<div class="modal-body">'
					    +'</div>'
					    +'</div>'
						+'</div>';
						page.namespace.root.find('#'+divId).remove();
						page.namespace.root.append($(template));
						var $dom=page.namespace.root.find('#'+divId);
						$dom.data('parentPage',page);
						$dom.data('params',params);
						$dom.modal({
							show : true,
							keyboard: false,
							backdrop : 'static'
						});
						$dom.find('.modal-content > .modal-body').load(VIEW_ROOT+url);
						$dom.on('hidden.bs.modal',function(e){
							try{
								if($dom.data('modalHiddenFn')){
									$dom.data('modalHiddenFn')(e);
								}
							}catch (e) {
							}
							if($dom.data('modalSkip')){
								return ;
							}
							var modalReturnFn=$dom.data('modalReturnFn');
							var result=[];
							if(modalReturnFn){
								result[0]=modalReturnFn();
							}
							try{
								modalOpts.hidden(e,result);
							}catch (e) {
							}
							$dom.remove();
						});
					},
					/**
					 * modalOpts : {
					 * id : //modal id,
					 * title : // title,
					 * hidden : // fn 监听函数，当弹出页面关闭的时候能够获得通知
					 * returnFn :  //fn   注册返回参数函数，此参数会作为父页面注册的关闭函数的入参
					 * skip : true/false // skip hide function  父页面的监听函数不会收到通知（true）
					 * opt : {}  //modal options
					 * }
					 */
					close : function(page,modalOpts){
						var divId=modalOpts.id;
						var $dom=this.parent.namespace.root.find('#'+divId);
						$dom.data('modalHiddenFn',modalOpts.hidden);
						this.registerReturn(divId,modalOpts.returnFn);
						$dom.data('modalSkip',modalOpts.skip);
						/*
						if(modalOpts.skip){
							$dom.off('hidden.bs.modal');
						}*/
						$dom.modal('hide');
					},
					
					/**
					 * 注册返回参数函数
					 */
					registerReturn : function(page,modalId,fn){
						this.parent.namespace.root.find('#'+modalId)
							.data('modalReturnFn',fn);
					},
					/**
					 * 注册当前弹出页面的父页面
					 */
					registerParent : function(page,modalId){
						this.parent=page.namespace.root.parents('#'+modalId).data('parentPage');
					},
					
					params : function(page,modalId){
						var params=this.parent.namespace.root.find('#'+modalId).data('params');
						if(params){
							return params;
						}else{
							return {};
						}
					},
					
					param : function(page,modalId,key){
						var params=this.parent.namespace.root.find('#'+modalId).data('params');
						if(params){
							return params[key];
						}else{
							return null;
						}
					}
				}
			}()
		};
		
		api.root = $(this);
		return api;
	}
});