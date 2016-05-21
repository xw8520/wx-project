<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav style="float: right">
    <ul class="pager">
        <li><a href="javascript:void(0)" id="btnBackward" class="glyphicon glyphicon-step-backward pager-ctl"></a></li>
        <li><a href="javascript:void(0)" id="btnLeft" class="glyphicon glyphicon-chevron-left pager-ctl"></a></li>
        <li><a class="page-info" id="pageInfo">1/2</a></li>
        <li><a href="javascript:void(0)" id="btnRight" class="glyphicon glyphicon-chevron-right pager-ctl"></a></li>
        <li><a href="javascript:void(0)" id="btnForward" class="glyphicon glyphicon glyphicon-step-forward pager-ctl"></a></li>
        <%--<li><a href="javascript:void(0)" id="btnRefresh" class="glyphicon glyphicon-refresh"></a></li>--%>

        <li>
            跳转到:
            <input type="number" id="txtPageIndex" total="1" style="width:40px;" value="1"/>
            <input type="button" id="btnGo" class="btn btn-success btn-sm" value="Go"/>
            每页记录: <input type="number" id="txtPageSize" style="width:40px;" value="15"/>
        </li>
    </ul>
</nav>
