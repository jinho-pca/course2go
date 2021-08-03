<template>
	<div>
		장소 목록
	</div>
  <!-- v-for -->
  <WriteRoutePlace/>
  <button @click="showModal = true">+</button>
	<SearchPlace class="search-modal" v-if="showModal" @close="showModal = false" >
		
	</SearchPlace>
</template>

<script>
import WriteRoutePlace from '@/components/write/route/WriteRoutePlace.vue'
import SearchPlace from '@/components/search/SearchPlace.vue'
export default {
	components: {
		WriteRoutePlace,
    SearchPlace
  },
	data: function(){
		return{
			showModal : false
		}
	}
}

</script>

