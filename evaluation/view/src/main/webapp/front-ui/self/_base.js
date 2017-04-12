function List() {
	this.arrys = [];
	this.position = -1;

	function equals($_this, $_another) {
		if ($_this.equals && $.isFunction($_this.equals)) {
			return $_this.equals.apply($_this, [ $_another ]);
		} else {
			if ($.type($_this) === 'string' || $.type($_this) === 'boolean'
					|| $.type($_this) === 'number' || $.type($_this) === 'date') {
				return $_this == $_another;
			} else {
				for ( var i in $_this) {
					equals($_this[i], [ $_another[i] ]);
				}
			}
		}
	}

	this.size = function() {
		return this.position + 1;
	};

	this.length = function() {
		return this.arrys.length;
	};

	this.resize = function() {
		tempArr = [ this.size() * 1.25 + 5 ];
		for (var i = 0; i < this.size(); i++) {
			tempArr[i] = this.arrys[i];
		}
		this.arrys = tempArr;
	};

	this.exists = function(id) {
		exists = false;
		var i = 0;
		for (; i < this.size(); i++) {
			if (equals(this.arrys[i], id)) {
				exists = true;
				break;
			}
		}
		return exists;
	};

	this.indexOf = function(id) {
		exists = false;
		var i = 0;
		for (; i < this.size(); i++) {
			if (equals(this.arrys[i], id)) {
				exists = true;
				break;
			}
		}
		return exists ? i : -1;
	};

	this.add = function(id) {
		if (this.size() == this.length()) {
			this.resize();
		}
		this.position++;
		this.arrys[this.position] = id;
	};

	this.compress = function() {
		var tempPosition = -1;
		for (var i = 0; i <= this.position; i++) {
			if (this.get(i) == null) {
				var j = i + 1;
				while (j <= this.position) {
					if (this.get(j) != null) {
						this.arrys[i] = this.get(j);
						this.arrys[j] = null;
						tempPosition = i;
						break;
					} else {
						j++;
					}
				}
			} else {
				tempPosition = i;
			}
		}
		this.position = tempPosition;
	};

	this.get = function(index) {
		return this.arrys[index];
	}

	this.remove = function(id) {
		var index = this.indexOf(id);
		if (index != -1) {
			this.arrys[index] = null;
		}
		this.compress();
	};

	this.val = function() {
		var tempArr = [];
		for (var i = 0; i < this.size(); i++) {
			if (this.get(i) != null) {
				tempArr[i] = this.get(i);
			}
		}
		return tempArr;
	}

}
function ListMap(){

	this.entries = new List();

	function genEntry(key, val) {
		return {
			key : key,
			value : val,
			equals : function($obj) {
				return this.key == $obj.key;
			}
		}
	}

	this.put = function(key, val) {
		var entry = genEntry(key, val);
		if (this.entries.exists(entry)) {
			this.entries.remove(entry);
		}
		this.entries.add(entry);
	}

	this.get = function(key) {
		var entry = genEntry(key, null);
		var index = this.entries.indexOf(entry);
		if (index != -1) {
			return this.entries.get(index).value;
		}
	}

	this.exists = function(key) {
		var entry = genEntry(key, null);
		return this.entries.exists(entry);
	}

	this.remove = function(key) {
		var entry = genEntry(key, null);
		return this.entries.remove(entry);
	}

	this.val = function() {
		return this.entries.val();
	}

	this.size = function() {
		return this.entries.size();
	}

	this.keys = function() {
		var keys = [];
		var keyVals = this.val();
		for (var i = 0; i < keyVals.length; i++) {
			keys[i] = keyVals[i].key;
		}
		return keys;
	}

	this.values = function() {
		var values = [];
		var keyVals = this.val();
		for (var i = 0; i < keyVals.length; i++) {
			values[i] = keyVals[i].value;
		}
		return values;
	}

}

if(!window.cjs){
	cjs={};
	VIEW_ROOT='/view';
	WEB_ROOT='/web';
}
cjs.modal={
		/**
		 * modalOpts : {
		 * id : //modal id,
		 * title : // title,
		 * hidden :  // fn(e(事件对象),array(弹出页面的返回参数))  
		 * 监听函数，当弹出页面关闭的时候能够获得通知
		 * opt : {}  //modal options
		 * }
		 */
		open : function(modalOpts,url,params){
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
			$('#'+divId).remove();
			$('body').append($(template));
			var $dom=$('#'+divId);
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
		close : function(modalOpts){
			var divId=modalOpts.id;
			var $dom=$('#'+divId);
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
		registerReturn : function(modalId,fn){
			$('#'+modalId)
				.data('modalReturnFn',fn);
		},
		
		modalId : function($dom){
			return $($dom).parents('.modal.fade').attr('id');
		},
		
		params : function(modalId){
			var params=$('#'+modalId).data('params');
			if(params){
				return params;
			}else{
				return {};
			}
		},
		param : function(modalId,key){
			var params=$('#'+modalId).data('params');
			if(params){
				return params[key];
			}else{
				return null;
			}
		}
		
		
		
	}