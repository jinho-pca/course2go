<template>
  <div>
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
    places: {
      type: Array,
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
      },
    }
  },
  watch: {
    places: {
      deep: true,
      handler(){
        this.click();
      }
    }
  },
  methods : { 
    makeStyle(){
      /* 상하좌우 극좌표 구하기 */
      var latbig = 0;
      var latsmall = 0;
      var lngbig = 0;
      var lngsmall = 0;
      var start = true;
      this.places.forEach(place => {
        console.log(place);
        if (start) {
          latbig = place.placeLat;
          latsmall = place.placeLat;
          lngbig = place.placeLng;
          lngsmall = place.placeLng;
          start = false;
        }
        if(latbig<place.placeLat) latbig = place.placeLat;
        if(latsmall>place.placeLat) latsmall = place.placeLat;
        if(lngbig<place.placeLng) lngbig = place.placeLng;
        if(lngsmall>place.placeLng) lngsmall = place.placeLng;
      });

      // console.log(latbig);
      // console.log(latsmall);
      // console.log(lngbig);
      // console.log(lngsmall);
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
      console.log("이닛맵");
      var container = document.getElementById('map'); 
      var options = { 
        center: new kakao.maps.LatLng(this.mapview.lattitude, this.mapview.langtitude), 
        level: this.mapview.level }; 
        var map = new kakao.maps.Map(container, options); 

        var linePath = []; /* 선긋기용 */

        this.places.forEach(place => {
          var point = new kakao.maps.LatLng(place.placeLat,place.placeLng)
          var marker = new kakao.maps.Marker({ 
            map: map,
            position: point,
          }); 
          marker.setMap(map); 
          linePath.push(point)
        });
        /* 선긋기 */
        var polyline = new kakao.maps.Polyline({
          path: linePath, // 선을 구성하는 좌표배열 입니다
          strokeWeight: 5, // 선의 두께 입니다
          strokeColor: '#FFAE00', // 선의 색깔입니다
          strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
          strokeStyle: 'solid' // 선의 스타일입니다
        });
        polyline.setMap(map);
    }, 
    addScript() { 
      console.log("애드스크립트");
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
      
      console.log(this.mapview.level);
    },
    getLevel(height,h_px){
      /* h 픽셀 높이 안에 위도차 height가 들어가도록 하는 level의 값을 구하는 함수 */
      var h_meter = height*88800;
      var meter_per_px = h_meter/(h_px*0.95);
      console.log(h_meter);
      console.log(h_px);
      console.log(meter_per_px);
      /* 3레벨에서 1m=1px */
      var level = 1;
      var mpp_thislevel = 0.25;
      while (mpp_thislevel<meter_per_px) {
        level++;
        mpp_thislevel *=2;
      }
      console.log("level");
      console.log(level);
      return level>14?14:level;
    },
    click(){
      this.makeStyle();
    //   this.addScript()
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