<template>
	<div>
		장소 목록
	</div>
  <!-- v-for -->
 
	<SearchPlace class="search-modal" v-if="showModal" @close="showModal = false" @place="addPlace"/>
	<div class="place-list" v-for="place in placeList" :key="place">
		<div class="place-items">
			{{place}}
		</div>
	</div>
  <button @click="showModal = true">+</button>
</template>

<script>
import SearchPlace from '@/components/search/SearchPlace.vue'
export default {
	components: {
    SearchPlace
  },
	methods: {
		addPlace(place){
			this.showModal=false;
			this.placeList.push(place);
			console.log(this.placeList[0]);
		}
	}
	,
	data: function(){
		return{
			showModal : false,
			placeList : []
		}
	}
}

</script>

