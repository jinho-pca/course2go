<template>
  <div>
    <!-- <div v-on:click="click()">버튼</div> -->
    <div id="map"></div>
  </div>
</template>

<script>
import { mapkey } from '../../../../mapkey.js';
export default {
  mounted() { 
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript(); 
  }, 
  props: {
    containSpots: {
      type: Array,
    },
  },
  watch: {
    containSpots: {
      deep: true,
      handler(){
        this.click();
      }
    }
  },
  data() {
    return {
      mapview: {
        lattitude: 33.37,
        langtitude: 126.54,
        latbig: 0,
        latsmall: 0,
        lngbig: 0,
        lngsmall: 0,
        level:11,
        height: 300,
        heightcss: "500px",
        start: true,
      },
    }
  },
  methods : { 
    makeStyle(){
      /* 상하좌우 극좌표 구하기 */
      var latbig = 0;
      var latsmall = 0;
      var lngbig = 0;
      var lngsmall = 0;
      this.containSpots.forEach(spot => {
        if (this.mapview.start) {
          latbig = spot.place.placeLat;
          latsmall = spot.place.placeLat;
          lngbig = spot.place.placeLng;
          lngsmall = spot.place.placeLng;
          this.mapview.start = false;
        }
        if(latbig<spot.place.placeLat) latbig = spot.place.placeLat;
        if(latsmall>spot.place.placeLat) latsmall = spot.place.placeLat;
        if(lngbig<spot.place.placeLng) lngbig = spot.place.placeLng;
        if(lngsmall>spot.place.placeLng) lngsmall = spot.place.placeLng;
      });

      this.mapview.latbig = latbig;
      this.mapview.latsmall = latsmall;
      this.mapview.lngbig = lngbig;
      this.mapview.lngsmall = lngsmall;

      /* 극좌표의 중점을 잡아 적절한 중앙점 잡기 */
      this.mapview.lattitude = (latbig+latsmall)/2;
      this.mapview.langtitude = (lngbig+lngsmall)/2;
      
      /* 레벨 측정 */
      this.levelMeasure();
    },
    initMap() { 
      // console.log("이닛맵");
      var container = document.getElementById('map'); 
      var options = { 
        center: new kakao.maps.LatLng(this.mapview.lattitude, this.mapview.langtitude), 
        level: this.mapview.level }; 
        var map = new kakao.maps.Map(container, options); 
        
        var linePath = []; /* 선긋기용 */

        const size = this.containSpots.length;
        var index = 1;

        this.containSpots.forEach(spot => {
          var point = new kakao.maps.LatLng(spot.place.placeLat,spot.place.placeLng)
          this.setmarker(map,point,index,size);
          // var marker = new kakao.maps.Marker({ 
          //   map: map,
          //   position: point,
          // }); 
          // marker.setMap(map); 
          linePath.push(point)
          index++;
        });
        /* 선긋기 */
        var polyline = new kakao.maps.Polyline({
          path: linePath, // 선을 구성하는 좌표배열 입니다
          strokeWeight: 5, // 선의 두께 입니다
          strokeColor: '#dd5230', // 선의 색깔입니다
          strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
          strokeStyle: 'solid' // 선의 스타일입니다
        });
        polyline.setMap(map);
    }, 
    setmarker(map,point,index,size){
      var image = 'https://ssafy-5th.s3.ap-northeast-2.amazonaws.com/visitimage/9653b759-51c1-431b-b858-8c5bfa48e672grayheadhareubangping.png'
      if (index==size) {
        image = 'https://ssafy-5th.s3.ap-northeast-2.amazonaws.com/visitimage/2ac2b486-34df-4170-a8be-30865020bd96blueheadhareubangping.png'
      }
      if (index==1) {
        image = 'https://ssafy-5th.s3.ap-northeast-2.amazonaws.com/visitimage/7e31e78c-4e06-448e-8aff-7aff2a1cea57redheadhareubangping.png'
      }
      const height = document.getElementById('map').clientHeight/6;

      var imageSize = new kakao.maps.Size(height/2, height*10/11),
        imageOptions = {  
        spriteOrigin: new kakao.maps.Point(0, 0),    
        spriteSize: new kakao.maps.Size(height/2, height)  
      };     

      var marker = new kakao.maps.Marker({ 
        map: map,
        position: point,
        image: new kakao.maps.MarkerImage(image,imageSize,imageOptions),
      }); 
      marker.setMap(map);
    },
    addScript() { 
      // console.log("애드스크립트");
      const script = document.createElement('script'); 
      const { jskey } = mapkey()
      /* global kakao */ 
      script.onload = () => kakao.maps.load(this.initMap); 
      script.src = '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=' + jskey; 
      document.head.appendChild(script); 
    },
    levelMeasure() {
      /* 반응형 지도 크기 받아오기 */
      var element = document.getElementById('map');
      var h_px = element.clientHeight;
      // var w_px = element.clientWidth;

      /* 지도 레벨 정하기 */
      var height = this.mapview.latbig-this.mapview.latsmall;
      var width = this.mapview.lngbig-this.mapview.lngsmall;
      if (height < width*0.618) {
        height = width*0.618;
      }
      this.mapview.level = this.getLevel(height,h_px);
    },
    getLevel(height,h_px){
      /* h 픽셀 높이 안에 위도차 height가 들어가도록 하는 level의 값을 구하는 함수 */
      var h_meter = height*88800;
      var meter_per_px = h_meter/(h_px*0.95);
      // console.log(h_meter);
      // console.log(h_px);
      // console.log(meter_per_px);
      /* 3레벨에서 1m=1px */
      var level = 1;
      var mpp_thislevel = 0.25;
      while (mpp_thislevel<meter_per_px) {
        level++;
        mpp_thislevel *=2;
      }
      // console.log("결정");
      return level>14?14:level;
    }, 
    click(){
      this.makeStyle();
      this.initMap();
    }
  } 
}
</script>

<style>
#map {
  width: 100%;
  height: 0;
  padding-top: 61.8%;
}
</style>