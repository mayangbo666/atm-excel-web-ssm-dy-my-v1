<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>


</head>

<body data-type="index">

<div class="am-g tpl-g">
    <!-- 头部 -->
    <jsp:include page="common/header.jsp"></jsp:include>
    <!-- 风格切换 -->

    <!-- 侧边导航栏 -->
    <jsp:include page="common/left.jsp"></jsp:include>


    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">

        <div class="row-content am-cf">
            <div class="row  am-cf">

                <div class="row">

                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">流水操作</div>
                                <div class="widget-function am-fr">
                                    <a href="javascript:;" class="am-icon-cog"></a>
                                </div>
                            </div>
                            <div class="widget-body am-fr">

                                <form class="am-form tpl-form-line-form">


                                    <div class="am-form-group">
                                        <label for="user-phone" class="am-u-sm-3 am-form-label">银行卡 <span
                                                class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <select data-am-selected="{searchBox: 1}" style="display: none;" id="cardDiv">
                                            </select>
                                            <button type="button" class="am-btn am-btn-default am-radius" onclick="listFlow(1);">查询</button>
                                            <a id="downFlowDom" href="###" onclick="downFlow();">下载流水</a>
                                        </div>

                                    </div>


                                    <div class="am-form-group">
                                        <label for="pwd" class="am-u-sm-3 am-form-label">密码 <span
                                                class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="password" class="tpl-form-input" id="pwd"
                                                   placeholder="请输入对应6位银行卡密码, 进行流水操作">
                                            <small></small>
                                        </div>
                                    </div>


                                </form>


                                <div class="widget am-cf">
                                    <div class="widget-head am-cf">
                                        <div class="widget-title am-fl"></div>
                                        <div class="widget-function am-fr">

                                        </div>
                                    </div>
                                    <div class="widget-body  widget-body-lg am-fr">

                                        <table width="100%"
                                               class="am-table am-table-compact am-table-striped tpl-table-black "
                                               id="example-r">
                                            <thead>
                                            <tr>
                                                <th>卡号</th>
                                                <th>金额</th>
                                                <th>备注</th>
                                                <th>时间</th>

                                            </tr>
                                            </thead>
                                            <tbody id="flowListDiv">
                                            <tr class="gradeX">
                                                <td>6222*****196</td>
                                                <td>800.00</td>
                                                <td>存款</td>
                                                <td>2017-12-19</td>
                                            </tr>
                                            <!-- more data -->
                                            </tbody>
                                        </table>

                                        <%--<label for="user-phone" class="am-u-sm-3 am-form-label">每页条数 <span
                                                class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                        <select data-am-selected="{searchBox: 1}" style="display: none;" id="pageNum" >
                                            <option value="-1" >1</option>
                                            <option value="-1" >2</option>
                                        </select>
                                        </div>--%>



                                        <ul class="am-pagination">
                                            <label>
                                                每页条数
                                            </label>
                                            <select id="pageNum" onchange="getPageNum($(this).val())">
                                                <option id="1">1</option>
                                                <option id="2">2</option>
                                                <option id="3">3</option>
                                                <option id="5">5</option>
                                                <option id="10">10</option>
                                            </select>
                                            <br/>
                                            <li><a href="###" onclick="first();">&laquo; 首页</a></li>
                                            <li><a href="###" onclick="pre();">&laquo; 上一页</a></li>
                                            <li><a href="###" onclick="next();">下一页 &raquo;</a></li>
                                            <li><a href="###" onclick="last();">尾页 &raquo;</a></li>
                                            <li id="curPage_totalPage">
                                                1 / 1

                                            </li>
                                        </ul>


                                    </div>
                                </div>


                            </div>

                        </div>
                    </div>
                </div>


            </div>


        </div>


    </div>
</div>
</div>
</div>
<script src="/static/js/amazeui.min.js"></script>
<script src="/static/js/amazeui.datatables.min.js"></script>
<script src="/static/js/dataTables.responsive.min.js"></script>
<script src="/static/js/app.js"></script>

<%--<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"> </script>--%>

<script type="application/javascript" id="my">
    // author mayangbo666
    // 作者 mayangbo666

    var curPage = 1
    var pageNum = 1 // 每页条数
    var totalPage = 1

    // 首页
    function first() {
        curPage = 1
        listFlow(curPage)
    }

    // 尾页
    function last() {
        curPage = totalPage
        listFlow(curPage)
    }

    // 上一页
    function pre() {
        if (1 >= curPage){
            return false
        }
        curPage -= 1
        listFlow(curPage)
    }

    // 下一页
    function next() {
        if (curPage >= totalPage) {
            return false
        }
        curPage += 1
        listFlow(curPage)
    }

    // 当前页 / 总页数
    function curPageAndTotalPage(a, b) {
      $('#curPage_totalPage').html(a + '/' + b)
    }

    function getPageNum(pageNumN) {
        pageNum = pageNumN
        listFlow(curPage)
    }

    function listFlow(curPage) {
        $.ajax({
            type: 'post',
            dataType: 'json',
            url: '/listFlow',
            data: {
                cardNum: $("#cardDiv").val(),
                password: $("#pwd").val(),
                curPage: curPage,
                pageNum: pageNum
            },
            success: function (dataResult) {
                if (!dataResult.success){
                    alert(dataResult.message)
                    return false
                }

                var flows = dataResult.data.data

                totalPage = dataResult.data.totalPage
                curPageAndTotalPage(curPage, totalPage)

                var msg = '该卡还没有交易数据'

                for (var i=0; i< flows.length; i++){
                    var flow = flows[i]
                    msg += '<tr class="gradeX">'
                    msg += '<td>'+ flow.cardNum +'</td>'
                    msg += '<td>'+ flow.tradeAmount +'</td>'
                    msg += '<td>'+ flow.tradeType +'</td>'
                    msg += '<td>'+ flow.createTime +'</td>'
                    msg += '</tr>'
                }
                $("#flowListDiv").html(msg)
            }
        })
    }

    $(function () {
       $.ajax({
           type: 'post',
           dataType: 'json',
           url: '/listBankCard',
           // data:{},
           success: function (dataResult) {
               if (!dataResult.success) {
                   alert(dataResult.message);
                   return false;
               }
               cardList = dataResult.data;
               var msg = '<option value="-1">请选择银行卡</option>'
               for (var i = 0; i < cardList.length; i++) {
                   msg += '<option value="' + cardList[i].cardNum + '">' + cardList[i].cardNum + '</option>'
               }
               $('#cardDiv').html(msg);
           }
       });
    });

    function downFlow() {
        $("#downFlowDom").attr('href', '/downFlow?cardNum=' + $('#cardDiv').val() +'&password=' + $('#pwd').val());
    }
</script>
</body>

</html>