<template>
  <div>
    <div v-on:click="click()">버튼</div>
    <div id="map"></div>
  </div>
</template>

<script>
export default {
  mounted() { 
    this.makeStyle();
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript(); 
  }, 
  props: {
    containSpots: {
      type: Array,
    },
  },
  data() {
    return {
      mapview: {
        lattitude: 33,
        langtitude: 126.562554,
        latbig: 0,
        latsmall: 0,
        lngbig: 0,
        lngsmall: 0,
        level:7,
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
      console.log("이닛맵");
      var container = document.getElementById('map'); 
      var options = { 
        center: new kakao.maps.LatLng(this.mapview.lattitude, this.mapview.langtitude), 
        level: this.mapview.level }; 
        var map = new kakao.maps.Map(container, options); 
        
        this.containSpots.forEach(spot => {
          var marker = new kakao.maps.Marker({ 
            map: map,
            position: new kakao.maps.LatLng(spot.place.placeLat,spot.place.placeLng) ,
          }); 
          marker.setMap(map); 
        });
    }, 
    addScript() { 
      console.log("애드스크립트");
      const script = document.createElement('script'); 
      /* global kakao */ 
      script.onload = () => kakao.maps.load(this.initMap); 
      script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=3024c56c5a90d08aa30c4d941e4beba1'; 
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
      console.log(h_meter);
      console.log(h_px);
      console.log(meter_per_px);
      /* 3레벨에서 1m=1px */
      var level = 1;
      var mpp_thislevel = 0.25;
      while (mpp_thislevel<meter_per_px) {
        level++;
        mpp_thislevel *=2;
        console.log(level);
      }
      console.log("결정");
      return level;
    }, 
    click(){
      this.levelMeasure();
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