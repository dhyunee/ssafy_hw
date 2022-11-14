<template>
  <!-- Modal Detail-->
  <div
    class="modal fade"
    id="boardDetailModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">글 상세</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <table class="table table-hover">
            <tbody>
              <tr>
                <td>글 번호</td>
                <td id="boardIdDetail">#</td>
              </tr>
              <tr>
                <td>제목</td>
                <td id="titleDetail">#</td>
              </tr>
              <tr>
                <td>내용</td>
                <td id="contentDetail">#</td>
              </tr>
              <tr>
                <td>작성자</td>
                <td id="userNameDetail">#</td>
              </tr>
              <tr>
                <td>작성일시</td>
                <td id="regDtDetail">#</td>
              </tr>
              <tr>
                <td>조회수</td>
                <td id="readCountDetail">#</td>
              </tr>

              <!-- 첨부 파일 -->
              <tr>
                <td colspan="2">첨부파일</td>
              </tr>
              <tr>
                <td colspan="2" id="fileListDetail">#</td>
              </tr>
            </tbody>
          </table>

          <button id="btnBoardUpdateForm" type="button" class="btn btn-sm btn-primary">수정</button>
          <button
            id="btnBoardDeleteConfirm"
            type="button"
            class="btn btn-sm btn-warning"
            data-bs-dismiss="modal"
          >
            삭제
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {};
</script>
<style></style>
