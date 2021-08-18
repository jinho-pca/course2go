<template>
  <div class="write-route">
    <WriteRouteHeader/> 
    <WriteRouteTitle @title="getTitle"/>
    <WriteRouteDate @routeStartDate = "getRouteStartDate" @routeEndDate = "getRouteEndDate"/> 
    <WriteRoutePlaceList @routePid="pushRoutePid" @routePlace="pushRoutePlace"/>
    <WriteRouteMap :places="routePlaces" ref="printMe"/>
    <WriteRouteContent @routeContent="getRouteContent"/>
    <WriteRouteImage @routeImage="getRouteImage"/>
    <div style="text-align: center">
      <button class="write-route-btn" @click="write">글 쓰기</button>
      <!-- <button class="write-route-btn" @click="print">지도 캡쳐</button> -->
    </div>
    <div class="empty-box">
      
    </div>
  </div>
</template>

<script>
import WriteRouteHeader from '@/components/write/route/WriteRouteHeader.vue'
import WriteRouteTitle from '@/components/write/route/WriteRouteTitle.vue'
import WriteRoutePlaceList from '@/components/write/route/WriteRoutePlaceList.vue'
import WriteRouteMap from '@/components/maps/routemap/WriteRouteMap.vue'
import WriteRouteContent from '@/components/write/route/WriteRouteContent.vue'
import WriteRouteDate from '@/components/write/route/WriteRouteDate.vue'
import WriteRouteImage from '@/components/write/route/WriteRouteImage.vue'

import {writeRoute} from '@/compositions/write';

import "@/components/css/write/route/writeRoute.css"

export default {
  name: 'WriteRoute',
  components: {
    WriteRouteHeader,
    WriteRouteTitle,
    WriteRoutePlaceList,
    WriteRouteMap,
    WriteRouteContent,
    WriteRouteDate,
    WriteRouteImage
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
      // this.print()
      if(!this.title){
        alert("제목을 입력하세요.");
        return;
      } else if(!this.routeStartDate){
        alert("출발일을 입력하세요.");
        return;
      } else if(!this.routeEndDate){
        alert("출발일을 입력하세요.");
        return;
      } else if(!this.routePid){
        alert("여행 경로를 하나 이상 입력하세요.");
        return;
      } else{
        writeRoute(this.title, this.routeStartDate, this.routeEndDate, this.routeContent, this.routePid, this.routeImage);
        this.$router.push({name: 'Newsfeed'});
      }


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
      console.log(this.routePid);
    },
    pushRoutePlace(routePlace){
      this.routePlaces.push(routePlace);
    },
    getRouteImage(routeImage){
      this.routeImage = routeImage;
      console.log(this.routeImage);
    },
    // print() {
    //   const el = this.$refs.printMe;
    //   // add option type to get the image version
    //   // if not provided the promise will return 
    //   // the canvas.
    //   const options = {
    //     type: 'dataURL'
    //   }
    //   this.routeMapImage = this.$html2canvas(el, options);
    //   console.log(this.routeMapImage)
    // }
  },
  data: function(){
		return{
			title : "",
      routeStartDate : "",
      routeEndDate : "",
      routeContent : "",
      routePid : [],
      routePlaces : [],
      routeImage : null,
		}
	}
  

}
</script>
