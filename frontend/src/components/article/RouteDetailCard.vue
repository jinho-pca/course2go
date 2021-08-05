<template>
  <div>
    <details open>
      <summary><span class="centersummary">세부사항</span></summary>
      <hr>
      <div class="content">
        <span id="date">기간</span> <br>
        {{routeReadResponse.routeResponse.routeStartDate}}~{{routeReadResponse.routeResponse.routeEndDate}}<br><br>
        <span>동선</span>
        <RouteMap :containSpots="routeReadResponse.containSpots"/>
        <div v-for="(spot, index) in routeReadResponse.containSpots" :key="index">
          <details v-if="spot.visit==null">
            <summary class="novisitsummery"><span>{{spot.place.placeName}}</span></summary>
          </details>
          <details v-else>
            <summary><span class="centersummary">{{spot.place.placeName}}</span></summary>
            <hr>
            {{spot.visit.visitCost}}<br>
            {{spot.visit.visitTime}}<br>
            <span v-on:click="goVisit(index)">글 보러가기</span>
          </details>
        </div>
      </div>
    </details>
  </div>
</template>

<script>
import '../css/article/route-detail.css'
import RouteMap from '@/components/maps/routemap/RouteMap.vue'
export default {
    name: 'routedetail',
    props:{
      routeReadResponse:{
        type: Object
      },
    },
    // created() {
    //   console.log("카드")
    //   console.log(this.routeReadResponse)
    //   console.log("카드")
    // },
    components: {
      RouteMap
    },
    data() {
      return {
        // routeReadResponse: {
        //   routeResponse: {
        //     routeStartDate: "2021-07-24",
        //     routeEndDate: "2021-07-24",
        //     routeContent: "우와여행너무재밌다",
        //   },
        //   boardResponse: {
        //     boardTitle: "즐거운 제주도 여행",
        //     boardLike: 100,
        //     boardStar: 50,
        //     boardTid: 1,
        //     boardType: true,
        //     boardTime: "2021-07-24 17:00:00",
        //   },
        //   containSpots: [
        //     {
        //       place: {
        //         pid: 3,
        //         placeName: "동방파제",
        //         placeAddress: "제주특별자치도 서귀포시 서귀동 758-2",
        //         placeLat: 33.236546,
        //         placeLng: 126.568803,
        //         placeDataDate: "2015-12-31",
        //         placeType: "교통시설",
        //       },
        //       visit: {
        //         vid: 3,
        //         visitPid: 3,
        //         visitContent: "동방파제 재밌당",
        //         visitTime: 2,
        //         visitCost: 10000,
        //         visitImage1: "",
        //         visitImage2: null,
        //         visitImage3: null,
        //       },
        //     },
        //     {
        //       place: {
        //         pid: 4,
        //         placeName: "새섬",
        //         placeAddress: "제주특별자치도 서귀포시 서귀동 산 3-3",
        //         placeLat: 33.235071,
        //         placeLng: 126.562554,
        //         placeDataDate: "2015-12-31",
        //         placeType: "지명관련",
        //       },
        //       visit: {
        //         vid: 4,
        //         visitPid: 4,
        //         visitContent: "",
        //         visitTime: 1,
        //         visitCost: 50000,
        //         visitImage1: "",
        //         visitImage2: null,
        //         visitImage3: null,
        //       },
        //     },
        //     {
        //       place: {
        //         pid: 5,
        //         placeName: "섶섬",
        //         placeAddress: "제주특별자치도 서귀포시 보목동 산 1",
        //         placeLat: 33.230314,
        //         placeLng: 126.599675,
        //         placeDataDate: "2015-12-31",
        //         placeType: "지명관련",
        //       },
        //       visit: null,
        //     }
        //   ],
        // },
      }
    },
    methods: {
      goVisit(index) {
        console.log(index);
      },
    },
}
</script>