<%@include file="taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="errorAlert" class="swal2-container swal2-center swal2-backdrop-show" style="overflow-y: auto;
<c:if test="${messengeError == null}">
        display: none;
</c:if>
<c:if test="${messengeError != null}">
        display: unset;
</c:if>
        ">
    <div aria-labelledby="swal2-title" aria-describedby="swal2-content"
         class="swal2-popup swal2-modal swal2-icon-error swal2-show" tabindex="-1" role="dialog"
         aria-live="assertive" aria-modal="true" style="display: flex;">
        <div class="swal2-header">
            <div class="swal2-icon swal2-error swal2-icon-show" style="display: flex;"><span class="swal2-x-mark">
                            <span class="swal2-x-mark-line-left"></span>
                            <span class="swal2-x-mark-line-right"></span>
                        </span>
            </div>
            <h2 class="swal2-title" id="swal2-title" style="display: flex;">Bạn có chắc xóa không?</h2>
        </div>

        <div class="swal2-actions">
            <div class="swal2-loader"></div>
            <form method="post">
                <button type="submit" name="delete" value="${param.id}" class="swal2-confirm swal2-styled"
                        id="closeAlertError" aria-label=""
                        style="display: inline-block;">OK
                </button>
                <button type="submit" class="swal2-confirm swal2-styled" id="closeAlertError" aria-label=""
                        style="display: inline-block;">NO
                </button>
            </form>
        </div>
    </div>
</div>

<script>
    document.getElementById("closeAlertError").onclick = closeAlert;

    function closeAlert() {
        document.getElementById("errorAlert").style.display = 'none';
    }
</script>