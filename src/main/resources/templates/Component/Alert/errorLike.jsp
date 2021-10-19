<%@include file="taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="errorLike" class="swal2-container swal2-center swal2-backdrop-show"
     style="display: none; overflow-y: auto; text-align: center;">
    <div aria-labelledby="swal2-title" aria-describedby="swal2-html-container"
         class="swal2-popup swal2-modal swal2-icon-warning swal2-show" tabindex="-1" role="dialog"
         aria-live="assertive" aria-modal="true" style="display: grid;">
        <div class="swal2-icon swal2-warning swal2-icon-show" style="display: flex;">
            <div class="swal2-icon-content">!</div>
        </div>
        <h2 class="swal2-title" id="swal2-title" style="display: block;">Chưa đăng nhập</h2>
        <div class="swal2-html-container" id="swal2-html-container" style="display: block;">Bạn phải đăng nhập để có thể
            bình luận hoặc thêm bộ phim vào mục ưa thích!
        </div>
        <div class="swal2-actions">
            <form action="<c:url value="/dang-nhap" />" method="get">
                <input type="hidden" value="" name="urlHistory">
                <button type="submit"
                        class="swal2-confirm swal2-styled swal2-default-outline btn-primary" aria-label=""
                        style="display: inline-block; background-color: rgb(48, 133, 214);">Đăng nhập
                </button>
                <div type="button" class="swal2-cancel swal2-styled swal2-default-outline" aria-label=""
                     style="display: inline-block; background-color: rgb(221, 51, 51);" onclick="closeErrorLike();">
                    Hủy bỏ
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function closeErrorLike() {
        document.getElementById('errorLike').style.display = 'none'
    }
</script>