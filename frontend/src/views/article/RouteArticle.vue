<template>
    <div>
        <RouteDetailCard />
        <RouteComment :bid="bid"/>
    </div>
</template>

<script>
import {read} from '@/compositions/route/read.js';
import RouteDetailCard from '@/components/article/RouteDetailCard.vue'
import RouteComment from '@/components/article/RouteComment.vue'
export default {
    name: 'RouteArticle',
    created() {
        this.routeReadResponse = read(1);
    },
    components: {
        RouteDetailCard,
        RouteComment,
    },
    data() {
        return {
            routeReadResponse: {
                // routeResponse:{

                // },
                // boardResponse:{

                // },
                // containSpots:[
                //     {
                //         place:{
                //             pid: 0,
                //             placeName: "",
                //             placeAddress: "",
                //             placeLat: 0,
                //             placeLng: 0,
                //             placeDataDate: "",
                //             placeType: "",
                //         },
                //         visit:{

                //         },
                //     },
                // ],
                // extraData:{
                //     writerNickname: "",
                // }
            },
        }
    },
}
</script>

<style>

</style>