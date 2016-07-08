/**
 * 
 * 公用的创建一个Dialog的对话框
 * 
 * @param id
 *            渲染对话框的div对应的ID，要求唯一性
 * @param title
 *            对话框的标题
 * @param width
 *            对话框宽
 * @param height
 *            对话框高
 * @param url
 *            对话框要加载的页面地址
 * @param bottons
 *            自定义按钮功能(按照按钮生成方式写)
 */
function globleCreateDialog(id, title, width, height, url, func, bottons) {
	var bottonIcons = bottons ? bottons : [ {
		text : '关闭',
		iconCls : 'icon-cancel',
		plain : true,
		handler : function() {
			$('#' + newID).dialog('close');
		}
	} ];
	var newID = id;
	var hasElement = $("#" + newID).length;
	if (hasElement == 0) {
		$(document.body).append(
				'<div id="' + newID + '" style="overflow:auto;"></div>');
	}
	$('#' + newID).dialog({
		title : title,
		width : width,
		height : height,
		modal : true,
		shadow : false,
		closed : false,
		minimizable : false,
		collapsible : false,
		maximizable : true,
		resizable : false,
		onBeforeClose : function(node) {
			if (func != undefined)
				func();
		},
		buttons : bottonIcons
	});
	$("#" + newID).dialog('refresh', url);
}

$.extend($.fn.pagination.defaults, {
	layout : [ 'list', 'sep', 'first', 'prev', 'links', 'next', 'last', 'sep',
			'refresh' ],
	links : 10
});

/**
 * 24小时日期转换：2014年6月3日 20:11:44(新)
 * 
 * @param value
 *            不需要传 datagrid 直接调函数就行
 * @returns {String}
 */
function formatDatebox(value) {
	if (value == null || value == '') {
		return '';
	}
	var dt;
	if (value instanceof Date) {
		dt = value;
	} else {
		dt = new Date(value);
		if (isNaN(dt)) {
			value = value.replace(/\/Date\((-?\d+)\)\//, '$1');
			dt = new Date();
			dt.setTime(value);
		}
		return dt.getFullYear() + "-" + ((dt.getMonth() + 1) < 10 ? "0" : "")
				+ (dt.getMonth() + 1) + "-" + (dt.getDate() < 10 ? "0" : "")
				+ dt.getDate() + " " + (dt.getHours() > 9 ? "" : "0")
				+ dt.getHours() + ":" + (dt.getMinutes() > 9 ? "" : "0")
				+ dt.getMinutes() + ":" + (dt.getSeconds() > 9 ? "" : "0")
				+ dt.getSeconds();
	}
}

/*
 * 将一般的JSON格式转为EasyUI TreeGrid树控件的JSON格式 @param rows:json数据对象 @param
 * idFieldName:表id的字段名 @param pidFieldName:表父级id的字段名 @param
 * fileds:要显示的字段,多个字段用逗号分隔
 */
function ConvertToTreeGridJson(rows, idFieldName, pidFieldName, fileds) {
	function exists(rows, ParentId) {
		for (var i = 0; i < rows.length; i++) {
			if (rows[i][idFieldName] == ParentId)
				return true;
		}
		return false;
	}
	var nodes = [];
	// get the top level nodes
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if (!exists(rows, row[pidFieldName])) {
			var data = {
				id : row[idFieldName]
			}
			var arrFiled = fileds.split(",");
			var len = arrFiled.length;
			for (var j = 0; j < arrFiled.length; j++) {
				if (arrFiled[j] != idFieldName)
					data[arrFiled[j]] = row[arrFiled[j]];
			}
			nodes.push(data);
		}
	}

	var toDo = [];
	for (var i = 0; i < nodes.length; i++) {
		toDo.push(nodes[i]);
	}
	while (toDo.length) {
		var node = toDo.shift(); // the parent node
		// get the children nodes
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (row[pidFieldName] == node.id) {
				var child = {
					id : row[idFieldName]
				};
				var arrFiled = fileds.split(",");
				for (var j = 0; j < arrFiled.length; j++) {
					if (arrFiled[j] != idFieldName) {
						child[arrFiled[j]] = row[arrFiled[j]];
					}
				}
				if (node.children) {
					node.children.push(child);
				} else {
					node.children = [ child ];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
};

/**
 * 24小时日期转换：2014年6月3日(新)
 * 
 * @param value
 *            不需要传 datagrid 直接调函数就行
 * @returns {String}
 */
function formatDateboxnoTime(value) {
	if (value == null || value == '') {
		return '';
	}
	var dt;
	if (value instanceof Date) {
		dt = value;
	} else {
		dt = new Date(value);
		if (isNaN(dt)) {
			value = value.replace(/\/Date\((-?\d+)\)\//, '$1');
			dt = new Date();
			dt.setTime(value);
		}
		return dt.getFullYear() + "-" + ((dt.getMonth() + 1) < 10 ? "0" : "")
				+ (dt.getMonth() + 1) + "-" + (dt.getDate() < 10 ? "0" : "")
				+ dt.getDate();
	}
}

function heigthChanger(gridid) {
	var data = $('#' + gridid).datagrid('getData');
	// alert('当前页数据量:' + data.rows.length);

	if (data.rows.length >= page) {
		$('#' + gridid).resizeDataGrid(heigth);
	} else if (data.rows.length < page) {
		// 其中85为添加按钮行和标题行的高度之和
		var a = data.rows.length * 25 + 85;
		$('#' + gridid).clearnDataGrid(a);
	}
}

function GetDateStr(AddDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate() + AddDayCount);// 获取AddDayCount天后的日期
	var y = dd.getFullYear();
	var m = dd.getMonth() + 1;// 获取当前月份的日期
	var d = dd.getDate();
	return y + "-" + m + "-" + d;
}

function formatContent(val, row) {
	if (val == null || val == 'undefined')
		val = '';
	return '<span title="' + val + '">' + val + '</span>';
}

function expexcel(url, data, method) {
	// 获取url和data
	if (url && data) {
		// data 是 string 或者 array/object
		data = typeof data == 'string' ? data : jQuery.param(data);
		// 把参数组装成 form的 input
		var inputs = '';

		jQuery.each(data.split('&'), function() {
			var pair = this.split('=');
			inputs += '<input type="hidden" name="' + pair[0] + '" value="' + pair[1] + '" />';
		});
		
		inputs += '<input type="hidden" name="exportMark" value="exportMark" />';
		// request发送请求
		jQuery('<form action="' + url + '" method="' + (method || 'post') + '">' + inputs + '</form>').appendTo('body').submit().remove();
	}
}

function covertNum(value) {
	var val = Number(value);
	if (!isNaN(val))
		return val;
	else
		return 0;
}
function formatDate(value, days) {
	if (value == null || value == '') {
		return '';
	}
	var dt;
	if (value instanceof Date) {
		dt = value;
	} else {
		dt = new Date(value);
	}
	if (isNaN(dt)) {
		value = value.replace(/\/Date\((-?\d+)\)\//, '$1');
		dt = new Date();
		dt.setTime(value);
	}
	if (days != null && days != '') {
		var k = days.split('-');
		for (var i = 0; i < k.length; i++) {
			k[i] = k[i].replace('/', '-');
			if (covertNum(k[i]) > 0) {
				switch (i) {
				case 0:
					dt.setYear(dt.getFullYear() + covertNum(k[i]));
					break;
				case 1:
					dt.setMonth(dt.getMonth() + covertNum(k[i]));
					break;
				case 2:
					dt.setDate(dt.getDate() + covertNum(k[i]));
					break;
				}
			}
		}
	}
	var dte = dt.getFullYear() + "-" + ((dt.getMonth() + 1) < 10 ? "0" : "")
			+ (dt.getMonth() + 1) + "-" + (dt.getDate() < 10 ? "0" : "")
			+ dt.getDate();
	// alert(dte);
	return dte;
}

function formatDateTime(value) {
	if (value == null || value == '') {
		return '';
	}
	var dt;
	if (value instanceof Date) {
		dt = value;
	} else {
		dt = new Date(value);
		if (isNaN(dt)) {
			value = value.replace(/\/Date\((-?\d+)\)\//, '$1');
			dt = new Date();
			dt.setTime(value);
		}
		return dt.getFullYear() + "-" + ((dt.getMonth() + 1) < 10 ? "0" : "")
				+ (dt.getMonth() + 1) + "-" + (dt.getDate() < 10 ? "0" : "")
				+ dt.getDate() + " " + (dt.getHours() > 9 ? "" : "0")
				+ dt.getHours() + ":" + (dt.getMinutes() > 9 ? "" : "0")
				+ dt.getMinutes() + ":" + (dt.getSeconds() > 9 ? "" : "0")
				+ dt.getSeconds();
	}
}

function DateAdd(interval, number, date) {
	switch (interval) {
	case "y": {
		date.setFullYear(date.getFullYear() + number);
		break;
	}
	case "q": {
		date.setMonth(date.getMonth() + number * 3);
		break;
	}
	case "m": {
		date.setMonth(date.getMonth() + number);
		break;
	}
	case "w": {
		date.setDate(date.getDate() + number * 7);
		break;
	}
	case "d": {
		date.setDate(date.getDate() + number);
		break;
	}
	case "h": {
		date.setHours(date.getHours() + number);
		break;
	}
	case "m": {
		date.setMinutes(date.getMinutes() + number);
		break;
	}
	case "s": {
		date.setSeconds(date.getSeconds() + number);
		break;
	}
	default: {
		date.setDate(date.getDate() + number);
		break;
	}
	}
	var dte = date.getFullYear() + "-"
			+ ((date.getMonth() + 1) < 10 ? "0" : "") + (date.getMonth() + 1)
			+ "-" + (date.getDate() < 10 ? "0" : "") + date.getDate();
	// alert(dte);
	return dte;
}

function formartColumn(val, id, mutil) {
	var result = "";
	if (val == 'null' ||  val == undefined)
		return result;
	var values = (val + "").split(",");
	for (var int = 0; int < values.length; int++) {
		if ( values[int] !='null'&& values[int] != undefined) {
			if (result.length > 0)
				result += " ";
			if($("#" + id + values[int]))
			result += $("#" + id + values[int]).val();
				
		}
		if (result.length > 0 && !mutil)
			break;
	}
	return result;
}
