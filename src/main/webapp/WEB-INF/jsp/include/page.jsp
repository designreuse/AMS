<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input type="hidden" name="size" id="size" value="${page.size }">
<input type="hidden" name="current" id="current"
	value="${page.current }">
<input type="hidden" name="total" id="total" value="${page.total }">
<input type="hidden" name="totalPage" id="totalPage"
	value="${page.totalPage }">
<input type="hidden" name="recount" id="recount"
	value="${page.recount }">
<ul class="vv-pages" id="fengpage">
	<li id="goprev"><a class="vv-pages-fye" href="javascript:">上一页</a>
	</li>
	<li id="gonext"><a class="vv-pages-fye" href="javascript:">下一页</a>
	</li>
	<li id="zysu"><input id="zysubtn" type="text"
		class="vv-pages-input" /></li>
	<li><a href="javascript:" onClick="zdbtn();returnFalse();"
		class="bs_btn">转到</a></li>
</ul>
