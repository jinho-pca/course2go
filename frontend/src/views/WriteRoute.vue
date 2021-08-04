<template>
    
    <div class="write-route">
      <WriteRouteHeader/> 
      <WriteRouteTitle @title="getTitle"/>
      <WriteRouteDate @routeStartDate = "getRouteStartDate" @routeEndDate = "getRouteEndDate"/> 
      <WriteRoutePlaceList @routePid="pushRoutePid"/>
      <WriteRouteMap/>
      <WriteRouteContent @routeContent="getRouteContent"/>
      <div>
        <button @click="write">글 쓰기</button>
      </div>
    </div>
</template>

<script>
import WriteRouteHeader from '@/components/write/route/WriteRouteHeader.vue'
import WriteRouteTitle from '@/components/write/route/WriteRouteTitle.vue'
import WriteRoutePlaceList from '@/components/write/route/WriteRoutePlaceList.vue'
import WriteRouteMap from '@/components/write/route/WriteRouteMap.vue'
import WriteRouteContent from '@/components/write/route/WriteRouteContent.vue'
import WriteRouteDate from '@/components/write/route/WriteRouteDate.vue'

import { writeRoute} from '@/compositions/write';

export default {
  name: 'WriteRoute',
  components: {
    WriteRouteHeader,
    WriteRouteTitle,
    WriteRoutePlaceList,
    WriteRouteMap,
    WriteRouteContent,
    WriteRouteDate
  },
  methods: {
		write(){
      console.log("글 작성 ============================\n" + 
      "제목 : " + this.title + "\n" +
      "시작일 : " + this.routeStartDate + "\n" +
      "종료일 : " + this.routeEndDate + "\n" +
      "내  용 : " + this.routeContent + "\n" +
      "PID : " + this.routepid + "\n"
      + "=============================")

      writeRoute(this.title, this.routeStartDate, this.routeEndDate, this.routeContent, this.routePid);

		},
    getTitle(title){
      this.title = title;
    },
    getRouteStartDate(routeStartDate){
      this.routeStartDate = routeStartDate;
    },
    getRouteEndDate(routeEndDate){
      this.routeEndDate = routeEndDate;
    },
    getRouteContent(routeContent){
      this.routeContent = routeContent;
    },
    pushRoutePid(routePid){
      this.routePid.push(routePid);
    }
  },
  data: function(){
		return{
			title : "",
      routeStartDate : "",
      routeEndDate : "",
      routeContent : "",
      routePid : [],
		}
	}
  

}
</script>
