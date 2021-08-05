<template>
	<div class="write-visit">
		<WriteVisitHeader/>
		<WriteVisitTitle/>
		<WriteVisitPlace/>
		<WriteVisitContent/>
		<WriteVisitImage/>
		<div style="text-align: center">
			<button class="write-visit-btn" @click="write">글 쓰기</button>
		</div>
		<div class="empty-box">
				
		</div>
	</div>
</template>

<script>
import WriteVisitHeader from '@/components/write/visit/WriteVisitHeader.vue'
import WriteVisitTitle from '@/components/write/visit/WriteVisitTitle.vue'
import WriteVisitPlace from '@/components/write/visit/WriteVisitPlace.vue'
import WriteVisitContent from '@/components/write/visit/WriteVisitContent.vue'
import WriteVisitImage from '@/components/write/visit/WriteVisitImage.vue'

import "@/components/css/write/visit/writeVisit.css"

export default {
	name: 'WritePlace',
	components: {
			WriteVisitHeader,
			WriteVisitTitle,
			WriteVisitPlace,
			WriteVisitContent,
			WriteVisitImage
	},
	data: function(){
		return{
			title : "",
			visitPid : "",
			visitCost : "",
			visitTime : "",
			visitContent : "",
		}
	}
}
</script>

<style>

</style>