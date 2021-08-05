<template>
  <div class="write-visit-images">
		<div class="write-visit-image-box" v-if="imageNum > 0">
			<img class="write-visit-image" :src="imageAddress[0].preview" alt="">
		</div>
		<div class="write-visit-between-block" v-if="imageNum > 0">

		</div>
		<div class="write-visit-image-box" v-if="imageNum > 1">
			<img class="write-visit-image" :src="imageAddress[1].preview" alt="">
		</div>
		<div class="write-visit-between-block" v-if="imageNum > 1">
			
		</div>
		<div class="write-visit-image-box" v-if="imageNum > 2">
			<img class="write-visit-image" :src="imageAddress[2].preview" alt="">
		</div>
		
		<input class="write-visit-image-box write-visit-image-add" type="file" id="file" ref="files" @change="addImage" v-if="imageNum < 3"/>
  </div>
</template>

<script>
import "@/components/css/write/visit/writeVisitImage.css"
export default {
	methods: {
		addImage(){
			console.log(this.$refs.files.files);
			console.log(this.imageNum);
			let file = 
			{
				file: this.$refs.files.files[0],
				preview: URL.createObjectURL(this.$refs.files.files[0]),
				number: this.imageNum
			}
			this.imageAddress.push(file);
			this.imageNum++;
		},
	}	,
	data: function(){
		return{
			imageNum : 0,
			imageAddress : [],
		}
	}
}
</script>

<style>

</style>