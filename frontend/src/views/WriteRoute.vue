<template>
    <WriteRouteHeader/>
    <WriteRouteTitle/>
    <WriteRoutePlaceList/>
    <WriteRouteMap/>
    <WriteRouteContent/>
    <button @click="wirteRoute">글 쓰기</button>
</template>

<script>
import WriteRouteHeader from '@/components/write/route/WriteRouteHeader.vue'
import WriteRouteTitle from '@/components/write/route/WriteRouteTitle.vue'
import WriteRoutePlaceList from '@/components/write/route/WriteRoutePlaceList.vue'
import WriteRouteMap from '@/components/write/route/WriteRouteMap.vue'
import WriteRouteContent from '@/components/write/route/WriteRouteContent.vue'

export default {
  name: 'WriteRoute',
  components: {
    WriteRouteHeader,
    WriteRouteTitle,
    WriteRoutePlaceList,
    WriteRouteMap,
    WriteRouteContent
  },
  method: {
      writeRoute(){

      }
  }

}
</script>
