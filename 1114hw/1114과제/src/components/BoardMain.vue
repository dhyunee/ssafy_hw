<template>
  <div class="container">
    <h4 class="text-center mt-3">게시판</h4>

    <div class="input-group mb-3 mt-3">
      <input
        type="text"
        class="form-control"
        v-model="searchWord"
        placeholder="Search"
        @keydown.enter="boardList"
      />
      <button class="btn btn-success" type="button" @click="boardList">Search</button>
    </div>

    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">작성자 :</th>
          <th scope="col">제목</th>
          <th scope="col">작성일자</th>
          <th scope="col">조회수</th>
        </tr>
      </thead>
      <tbody>
        <tr style="cursor: pointer" v-for="(board, index) in list" :key="index">
          <td>{{ board.boardId }}</td>
          <td>{{ board.userName }}</td>
          <td>{{ board.title }}</td>
          <td>{{ board.regDt.date | makeDateStr(".") }}</td>
          <td>{{ board.readCount }}</td>
        </tr>
      </tbody>
    </table>

    <!-- pagenationUI component 4개 props 필요-->
    <pagenation-ui
      v-bind:listRowCount="listRowCount"
      v-bind:pageLinkCount="pageLinkCount"
      v-bind:currentPageIndex="currentPageIndex"
      v-bind:totalListItemCount="totalListItemCount"
      v-on:call-parent-move-page="movePage"
    ></pagenation-ui>

    <button class="btn btn-primary" type="button" @click="showInsertModal">글쓰기</button>
    <insert-modal v-on:call-parent-insert="closeAfterInsert"></insert-modal>
  </div>
</template>
<script>
import http from "@/common/axios.js";
import util from "@/common/util.js";
import PagenationUI from "@/components/PagenationUI.vue";
import InsertModal from "@/components/modals/InsertModal.vue"; //vue component
import { Modal } from "bootstrap"; //vue component에서 bootstrap modal을 사용하기 위함

export default {
  components: {
    "pagenation-ui": PagenationUI,
    InsertModal,
  },
  data() {
    return {
      limit: 10,
      offeset: 0,
      searchWord: "",

      //list
      list: [],
      totalListItemCount: 0, //pagination에 사용
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      //modal
      insertModal: null, //bootstrap Modal 객체를 할당(ui component를 전달)
    };
  },
  methods: {
    async boardList() {
      let params = {
        limit: this.limit,
        offeset: this.offeset,
        searchWord: this.searchWord,
      };
      try {
        //axios가 json으로 parameter를 보내는 방법? 객체를 전달
        let response = await http.get("/boards", { params });
        let { data } = response;
        console.log(data);

        //interceptor session check fail
        if (data.result == "login") {
          this.$router.push("/login");
        } else {
          this.list = data.list; //목록
          this.totalListItemCount = data.count; //총건수
        }
      } catch (error) {
        console.error(error);

        this.$alertify.error("이메일 또는 비밀번호를 확인하세요");
      }
    },
    movePage(pageIndex) {
      this.offset = (pageIndex - 1) * this.listRowCount;
      this.currentPageIndex = pageIndex;
      this.boardList();
    },
    showInsertModal() {
      this.insertModal.show();
    },
    closeAfterInsert() {
      this.insertModal.hide();
      this.boardList();
    },
  },
  created() {
    this.boardList();
  },

  mounted() {
    //모달 갤체를 생성해서 data의 변수에 할당
    this.insertModal = new Modal(document.querySelector("#insertModal"));
  },
  filters: {
    makeDateStr: function (date, type) {
      return util.makeDateStr(date.year, date.month, date.day, type);
    },
  },
};
</script>
<style></style>
