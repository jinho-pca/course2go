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
		<div class="write-visit-image-box " v-if="imageNum < 3" style="border: none">
			<label class="write-visit-image-add" for="write-visit-image-btn">
				+
			</label>
			<input 
				type="file"
				id="write-visit-image-btn" 
				ref="files"
				style="display:none"
				accept="image/jpeg, image/jpg, image/png"
				@change="addImage"
				/>
		</div>
  </div>
</template>

<script>
import "@/components/css/write/visit/writeVisitImage.css"
export default {
	emits: ["visitImage"],
	methods: {
		addImage(){
			// console.log(this.$refs.files);
			// var photoFile = document.getElementById("file");
			// console.log(photoFile);

			let file = 
			{
				file: this.$refs.files.files[0],
				preview: URL.createObjectURL(this.$refs.files.files[0]),
				number: this.imageNum
			}
			this.imageAddress.push(file);
			this.imageNum++;
			this.$emit('visitImage', file.file);
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