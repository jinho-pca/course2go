<template>
	<transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">

          <div class="modal-header">
            <slot name="header">
							<input v-model="searchKey" type="text">
							<button class="modal-default-button"  @click="search(searchKey)">검색</button>
            </slot>
          </div>

          <div class="modal-body">
            <slot name="body">
							<div class="place-item" 
							@click="select(index)" 
							v-for="(place, index) in placeList" :key="place"
							:style="setColor(index)">
								<div class="place-pid" style="display:none">{{index}}</div>
								<div class="place-name">{{place.placeName}}</div>
								<div class="place-pid" style="display:none">{{place.pid}}</div>
								<div class="place-address" v-if="selectBox == index">{{place.placeAddress}}</div>
							</div>
            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
							<button class="modal-default-button modal-button-cancel" @click="$emit('close')">
                취소하기
              </button>
							<button class="modal-default-button modal-button-submit" @click="$emit('place',this.placeList[this.selectBox]);">
								추가하기
							</button>
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>



<script>
import "@/components/css/modal/modal.css"
import "@/components/css/search/searchPlace.css"
import { searchPlace } from '@/compositions/search';
export default {
	
	emits: ["close", "place"],
	
	methods: {
		search(searchKey){
			searchPlace(searchKey).then( res => {
				this.placeList = res;
			});	
		},

		select(index){
			this.selectBox = index;
			return index;
		},

		setColor(index){
			if(this.selectBox == index){
				return{
					backgroundColor : 'rgb(154, 122, 241)',
					color : '#fafafa'
				}
			} else{
				return{
					backgroundColor : '#fafafa'
				}
			}
		}
	},

	data() {
		return{
			searchKey : "",
			placeList : [],
			selectBox : -1,
		}
	}
}
</script>
