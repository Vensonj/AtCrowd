<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/23
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div id="confirmModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">尚筹网弹窗系统</h4>
            </div>
            <div class="modal-body">
                <h4>请确认是否要删除一下角色信息：</h4>
                <div id="confirmDeleteMessage"></div>
            </div>
            <div class="modal-footer">
                <button id="confirmDeleteRoleBtn" type="button" class="btn btn-danger">确认删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
