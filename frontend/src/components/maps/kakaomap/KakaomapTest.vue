<template>
    <div id="map"></div>
</template>

<script> export default { 
  mounted() { 
    this.makeStyle();
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript(); 
    }, 
  data() {
    return {
      mapview: {
        lattitude: 33.235801,
        langtitude: 126.566667,
        level:5,
        width: 500,
        height: 400,
        widthcss: "700px",
        heightcss: "500px",
      },
      pointers: [
        {
          id: 3,
          store: "동방파제",
          lattitude: 33.236546,
          langtitude: 126.568803,
          address: "제주특별자치도 서귀포시 서귀동 758-2",
          // latlng: new window.kakao.maps.LatLng(33.236546,126.568803),
        },
        {
          id: 4,
          store: "새섬",
          lattitude: 33.235071,
          langtitude: 126.562554,
          address: "제주특별자치도 서귀포시 서귀동 산 3-3",
          // latlng: new kakao.maps.LatLng(33.235071,126.562554),
        }
      ]
    }
  },
  methods : { 
    makeStyle(){
      this.mapview.widthcss = this.mapview.width + 'px'
      this.mapview.heightcss = this.mapview.height + 'px'
    },
    initMap() { 
      var container = document.getElementById('map'); 
      var options = { 
        center: new kakao.maps.LatLng(this.mapview.lattitude, this.mapview.langtitude), 
        level: this.mapview.level }; 
        var map = new kakao.maps.Map(container, options); 
        
        this.pointers.forEach(pos => {
          var marker = new kakao.maps.Marker({ 
            map: map,
            position: new kakao.maps.LatLng(pos.lattitude,pos.langtitude) ,
          }); 
          marker.setMap(map); 
        });
    }, 
    addScript() { 
      const script = document.createElement('script'); 
      /* global kakao */ 
      script.onload = () => kakao.maps.load(this.initMap); 
      script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=3024c56c5a90d08aa30c4d941e4beba1'; 
      document.head.appendChild(script); 
    } 
  } 
} 
</script>

<style> 
#map {
  width: v-bind(mapview.widthcss);
  height: v-bind(mapview.heightcss);
}
</style>
