<template>
    <div>
        <RouteDetailCard :routeReadResponse="routeReadResponse"/>
        <RouteComment :bid="bid"/>
    </div>
</template>

<script>
import { read } from '@/compositions/route/read.js';
import RouteDetailCard from '@/components/article/RouteDetailCard.vue'
import RouteComment from '@/components/article/RouteComment.vue'
export default {
    name: 'RouteArticle',
    created() {
        read(this.bid).then( res => {
				this.routeReadResponse = res;
			});	
        console.log(this.routeReadResponse);
    },
    components: {
        RouteDetailCard,
        RouteComment,
    },
    data() {
        return {
            bid: 36,
            routeReadResponse: {
                routeResponse: {
                    routeStartDate: "2021-07-24",
                    routeEndDate: "2021-07-24",
                    routeContent: "우와여행너무재밌다",
                },
                boardResponse: {
                    boardTitle: "즐거운 제주도 여행",
                    boardLike: 100,
                    boardStar: 50,
                    boardTid: 1,
                    boardType: true,
                    boardTime: "2021-07-24 17:00:00",
                },
                containSpots: [
                    {
                    place: {
                        pid: 3,
                        placeName: "동방파제",
                        placeAddress: "제주특별자치도 서귀포시 서귀동 758-2",
                        placeLat: 33.236546,
                        placeLng: 126.568803,
                        placeDataDate: "2015-12-31",
                        placeType: "교통시설",
                    },
                    visit: {
                        vid: 3,
                        visitPid: 3,
                        visitContent: "동방파제 재밌당",
                        visitTime: 2,
                        visitCost: 10000,
                        visitImage1: "",
                        visitImage2: null,
                        visitImage3: null,
                    },
                    },
                    {
                    place: {
                        pid: 4,
                        placeName: "새섬",
                        placeAddress: "제주특별자치도 서귀포시 서귀동 산 3-3",
                        placeLat: 33.235071,
                        placeLng: 126.562554,
                        placeDataDate: "2015-12-31",
                        placeType: "지명관련",
                    },
                    visit: {
                        vid: 4,
                        visitPid: 4,
                        visitContent: "",
                        visitTime: 1,
                        visitCost: 50000,
                        visitImage1: "",
                        visitImage2: null,
                        visitImage3: null,
                    },
                    },
                    {
                    place: {
                        pid: 5,
                        placeName: "섶섬",
                        placeAddress: "제주특별자치도 서귀포시 보목동 산 1",
                        placeLat: 33.230314,
                        placeLng: 126.599675,
                        placeDataDate: "2015-12-31",
                        placeType: "지명관련",
                    },
                    visit: null,
                    }
                ],
            },
        }
    },
}
</script>

<style>

</style>