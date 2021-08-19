<template>
  <div>
    <details open>
      <summary><span class="centersummary">세부사항</span></summary>
      <hr />
      <div class="content">
        <span id="date">기간</span> <br />
        {{ articleRoute.routeResponse.routeStartDate }} ~
        {{ articleRoute.routeResponse.routeEndDate }}<br /><br />
        <span>동선</span>
        <RouteMap :containSpots="articleRoute.containSpots" />
        <div v-for="(spot, index) in articleRoute.containSpots" :key="index">

          <details v-if="spot.visit==null">
            <summary class="novisitsummery">
              <span>
                {{substringof(spot.place.placeName)}}
              </span>
              <router-link v-if="articleRoute.userDto.userNickname == token " :to="{path:'/write/visit/', query:{pid: spot.place.pid, placeName: spot.place.placeName, rid:articleRoute.boardResponse.boardTid}}">
                &nbsp; <i class="fas fa-pen"></i>
              </router-link>
            </summary>
          </details>
          <details v-else>
            <summary><span class="centersummary">{{substringof(spot.place.placeName)}}</span></summary>
            <hr>
            소요비용 : {{ spot.visit.visitCost }}원<br />
            소요시간 : {{ spot.visit.visitTime }}시간<br />
            <span v-on:click="goVisit(spot.visit.vid)">글 보러가기</span>

          </details>
        </div>
      </div>
    </details>
  </div>
</template>

<script>
import '../css/article/route-detail.css'
import jwt from 'jsonwebtoken'
import RouteMap from '@/components/maps/routemap/RouteMap.vue'
import {BASE_URL} from '@/compositions/global.js'

export default {
  name: "routedetail",
  props: {
    articleRoute: {
      type: Object,
    },
  },
  // created() {
  //   console.log("카드")
  //   console.log(this.routeReadResponse)
  //   console.log("카드")
  // },
  components: {
    RouteMap,
  },
  methods: {
    goVisit(vid) {
      this.$router.push({name: 'VisitArticle', query:{vid : vid}});
    },
    substringof(text){
      if (text.length>12) {
        return text.substr(0,11)+"..."
      }
      return text
    }
  },
  data() {
    return {
      token : jwt.decode(BASE_URL().token.substr(7)).userNickname,
    }
  },
}
</script>
