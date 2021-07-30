<template>
  <div id="map" style="width:500px; height:500px; border:1px solid black;"></div>
</template>

<script>
import { ref, onMounted } from 'vue'
export default {
  name: 'map',
  setup() {
    const initMap = ()=> {
      const container = ref(document.getElementById('map'));
      const options = ref({ 
        center: new kakao.maps.LatLng(33.450701, 126.570667), 
        level: 3 
        });
      const map = ref(new kakao.maps.Map(container.value, options.value));

      const marker = ref(new kakao.maps.Marker({
        position: map.value.getCenter()
      }));
      marker.setMap(map.value);
    };

    const addScript = ()=> {
      const script = ref(document.createElement('script'));
      script.value.onload = () => kakao.maps.load(initMap);
      script.value.src = '주소'
      document.head.appendChild(script.value);
    }
    onMounted(window.kakao && window.kakao.maps ? initMap() : addScript())

    return {
      addScript,
      initMap
    }
  }
}
</script>