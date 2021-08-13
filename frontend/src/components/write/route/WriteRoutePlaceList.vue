<template>
	<div class="wirte-route-place-list">

		<div class = "write-route-place-list-label">
			장소 목록
		</div>
		<SearchPlace class="search-modal" v-if="showModal" @close="showModal = false" @place="addPlace"/>
		<div class="place-list">
			<div class="write-route-place-item" v-for="place in placeList" :key="place">
				<span class="write-route-place-item-name">
					{{place.placeName}}
				</span>
			</div>
		</div>
		<button class="write-route-add-place-btn" @click="showModal = true" v-if="placeList.length < 9">+</button>
	</div>
</template>

<script>
import SearchPlace from '@/components/search/SearchPlace.vue'
import "@/components/css/write/route/writeRoutePlaceList.css"
export default {
	emits: ["routePid", "routePlace"],
	components: {
    SearchPlace
	},
	methods: {
		addPlace(place){
			this.showModal=false;
			if(this.placePidList.includes(place.pid)){
				alert('이미 추가한 장소 입니다.');
				return;
			}
			this.placePidList.push(place.pid);
			this.placeList.push(place);
			this.$emit('routePlace', place);
			this.$emit('routePid', place.pid);
		},
	},
	data: function(){
		return{
			showModal : false,
			placeList : [],
			placePidList: [],
		}
	}
}

</script>

