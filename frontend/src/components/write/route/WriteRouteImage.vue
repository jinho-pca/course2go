<template>
	<div class="write-route-images">
		<div class="write-route-image-box" v-if="hasimage">
			<img class="write-route-image" :src="image" alt="">
		</div>
		<div class="write-route-image-box" v-if="!hasimage" style="border: none">
			<label class="write-route-image-add" for="write-route-image-btn">
				+
			</label>
			<input 
				type="file"
				id="write-route-image-btn" 
				ref="files"
				style="display:none"
				accept="image/jpeg, image/jpg, image/png"
				@change="addImage"
				/>
		</div>
	</div>
</template>

<script>
import "@/components/css/write/route/writeRouteImage.css"
export default {
	emits: ["routeImage"],
	methods: {
		addImage(){
			// console.log(this.$refs.files);
			// var photoFile = document.getElementById("file");
			// console.log(photoFile);

			let file = 
			{
				file: this.$refs.files.files[0],
				preview: URL.createObjectURL(this.$refs.files.files[0]),
			}
			this.image = file.preview;
			this.hasimage = true;
			this.$emit('routeImage', file.file);
			console.log(this.image)
		},
	}	,
	data: function(){
		return{
			hasimage : false,
			image : null,
		}
	}

}
</script>

<style>

</style>