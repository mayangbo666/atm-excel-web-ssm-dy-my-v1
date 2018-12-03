<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" %>

<div class="left-sidebar">
    <!-- 用户信息 -->
    <div class="tpl-sidebar-user-panel">
        <div class="tpl-user-panel-slide-toggleable">

            <div class="am-form-group am-form-file">
                <div class="tpl-user-panel-profile-picture">
                    <img id="avatarImg" src="/avatar/1" alt="" onerror="/static/img/avatar.jpg">
                </div>

                <form id="avatarForm" action="/upload2" method="post" enctype="multipart/form-data" target="fileIframe">
                    <input id="doc-form-file" type="file" name="myFile" onchange="uploadAvatar();">
                </form>
            </div>

        </div>
    </div>

    <!-- 菜单 -->
    <ul class="sidebar-nav">

        <li class="sidebar-nav-link">
            <a href="/toMainPage" class="active">
                <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
            </a>
        </li>
        <li class="sidebar-nav-link">
            <a href="/toOpenAccount">
                <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 开户
            </a>
        </li>
        <li class="sidebar-nav-link">
            <a href="/toDraw">
                <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 取款
            </a>
        </li>
        <li class="sidebar-nav-link">
            <a href="/toDeposit">
                <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 存款

            </a>
        </li>
        <li class="sidebar-nav-link">
            <a href="/toTransfer">
                <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 转账

            </a>
        </li>

        <li class="sidebar-nav-link">
            <a href="/toFlow">
                <i class="am-icon-bar-chart sidebar-nav-link-logo"></i> 流水

            </a>
        </li>

    </ul>
    <iframe id="fileIframe" name="fileIframe" style="display: none"></iframe>
</div>

<script type="application/javascript" id="my">
    function uploadAvatar() {
        $('#avatarForm').submit();
    }

    function reloadAvatar() {
        $('#avatarImg').attr('src', '/avatar/1?' + new Date().getTime());
    }
</script>

<script>
    // function uploadAvatar() {
    //     $('#avatarForm').submit();
    // }
    //
    // function reloadAvatar() {
    //     $('#avatarImg').attr('src', '/avatar/1?' + new Date().getTime());
    // }
</script>