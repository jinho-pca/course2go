<template>
  <SearchRouteInput @routePid="routePid" />
  <br />
  <SearchFilter @getFilter="getFilter" />
  <SearchRouteList :articles="filteredArticles" />
</template>

<script>
import SearchRouteInput from "@/components/search/SearchRouteInput.vue";
import SearchRouteList from "@/components/search/SearchRouteList.vue";
import SearchFilter from "@/components/search/SearchFilter.vue";
import { search } from "@/compositions/search.js";

export default {
  name: "SearchRoute",
  components: {
    SearchRouteInput,
    SearchRouteList,
    SearchFilter,
  },
  setup() {
    const { searchRoute, articles, filteredArticles } = search();
    function routePid(pid) {
      searchRoute(pid);
    }
    function getFilter(data) {
      filteredArticles.value = articles.value;
      console.log(filteredArticles.value);
      // 게시물 종류
      if (data.article.value == 2) {
        filteredArticles.value = filteredArticles.value.filter((x) => {
          return x.boardResponse.boardType;
        });
      } else if (data.article.value == 3) {
        filteredArticles.value = filteredArticles.value.filter((x) => {
          return !x.boardResponse.boardType;
        });
      }

      // 좋아요 순
      if (data.like.value == 2) {
        filteredArticles.value = filteredArticles.value.sort(function(a, b) {
          console.log("a", a.boardResponse.boardLike);
          console.log("b", b.boardResponse.boardLike);
          return a.boardResponse.boardLike - b.boardResponse.boardLike;
        });
      } else if (data.like.value == 3) {
        filteredArticles.value = filteredArticles.value.sort(function(a, b) {
          return b.boardResponse.boardLike - a.boardResponse.boardLike;
        });
      }

      // 날짜 범위
      // new Date로 설정했지만 input type Date를 거치면서 object로 변하므로 이렇게 사용
      if (typeof data.startDate.value != typeof new Date()) {
        filteredArticles.value = filteredArticles.value.filter((x) => {
          return !x.boardResponse.boardType || (new Date(x.routeResponse.routeStartDate) >= new Date(data.startDate.value))
        });
      }

      if (typeof data.endDate.value != typeof new Date()) {
        filteredArticles.value = filteredArticles.value.filter((x) => {
          return !x.boardResponse.boardType || (new Date(x.routeResponse.routeEndDate) >= new Date(data.endDate.value))
        });
      }
    }
    return { routePid, articles, getFilter, filteredArticles };
  },
};
</script>
