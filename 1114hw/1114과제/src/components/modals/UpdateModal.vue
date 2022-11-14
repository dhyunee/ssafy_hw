<template>
  <!-- Modal Update-->
  <div
    class="modal fade"
    id="boardUpdateModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">글 수정하기</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label for="titleUpdate" class="form-label">제목</label>
            <input type="text" class="form-control" id="titleUpdate" />
          </div>
          <div class="mb-3">
            <label for="contentUpdate" class="form-label">내용</label>
            <!-- CKEditor update -->
            <div id="divEditorUpdate"></div>
          </div>
          <div class="mb-3">
            <div class="form-check">
              <input class="form-check-input" type="checkbox" value="" id="chkFileUploadUpdate" />
              <label class="form-check-label" for="chkFileUploadUpdate">파일 변경</label>
            </div>
          </div>
          <div class="mb-3" style="display: none" id="imgFileUploadUpdateWrapper">
            <input type="file" id="inputFileUploadUpdate" multiple />
            <div id="imgFileUploadUpdateThumbnail" class="thumbnail-wrapper"></div>
          </div>
          <button
            id="btnBoardUpdate"
            type="button"
            class="btn btn-sm btn-primary float-end"
            data-bs-dismiss="modal"
          >
            수정
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
