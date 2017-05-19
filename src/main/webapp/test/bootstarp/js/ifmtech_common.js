(function(owner) {
	owner.approot = 'http://192.168.1.216:8080/merise/api/gateway';
	/**
	 * 公共ajax方法
	 */
	owner.ajax = function(opts, callback, errorback){
		var type = (opts.type == null ? "POST" : opts.type);
		var dataType = (opts.dataType == null ? "json" : opts.dataType);
		var _layerIndex;
		if(window.top !== window.self) {
			_layerIndex = parent.layer.load(1);
		} else {
			_layerIndex = layer.load(1);
		}
		$.ajax({
			type:type,
			url: owner.approot,
			data: opts.data,
			datatype: dataType,
			timeout: 10*1000,
			contentType: "text/html",
			success:function(data){
				//服务器返回响应，根据响应结果，分析是否登录成功；
				console.log(data);
				return callback(data);
			},
			error:function(xhr,type,errorThrown){
				//异常处理；
				if (errorback != undefined) {
					return errorback(xhr,type,errorThrown);
				}
			},
			complete: function() {
				if(window.top !== window.self) {
					parent.layer.close(_layerIndex);
				} else {
					layer.close(_layerIndex);
				}
			}
		});
	}
	
	/**
	 * 获取页面属性
	 */
	owner.getData = function(name) {
		var users = localStorage.getItem(name) || "{}";
		return JSON.parse(users);
	};
	
	/**
	 * 保存页面属性
	 */
	owner.setData = function(name,settings){
		settings = settings || {};
		localStorage.setItem(name, JSON.stringify(settings));
	};
	
}(window.ifmtech = {}));

