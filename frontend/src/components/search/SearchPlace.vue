<template>
	<transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
					<div class="modal-title">
						<span class="modal-title-left" ></span>
						<span class="modal-title-center">장소 검색</span>
						<span class="modal-title-right" @click="$emit('close')">X</span>
					</div>
          <div class="modal-header">
            <slot name="header">
							<input class="modal-default-input" v-model="searchKey" type="text" @keyup="keyupSearch" id="search-input">
							<button class="modal-default-button" @click="search">검색</button>
            </slot>
          </div>

          <div class="modal-body">
            <slot name="body">
							<div class="place-item-list">
								<div class="place-item"
								@click="select(index)"
								v-for="(place, index) in placeList" :key="place"
								:style="setColor(index)">
									<div class="place-name">{{place.placeName}}</div>
									<div class="place-address" v-if="selectBox == index">{{place.placeAddress}}</div>
									<div class="place-pid" style="display:none">{{place.pid}}</div>
									<div class="place-index" style="display:none">{{index}}</div>
								</div>
							</div>

							<div class="place-page">
								<div class="place-page-btn" @click="prevPage" :style="prevClickable()">
									&lt; 
								</div>
								<div class="place-page-num">
									{{page+1}}
								</div>
								<div class="place-page-btn" @click="nextPage" :style="nextClickable()">
									&gt;
								</div>
							</div>

            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
							<button class="modal-default-button modal-button-submit" 
							@click="$emit('place',this.placeList[this.selectBox]);"
							style="justify-content:center; width:50%">
								추가
							</button>
							<!-- <button class="modal-default-button modal-button-cancel" @click="$emit('close')">
                취소
              </button> -->
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
		search(){
			this.page = 0;
			searchPlace(this.searchKey, this.page).then( res => {
				this.placeList = res;
			});	
		},

		keyupSearch(){
			this.page = 0;
			if (this.searchKey) {
				searchPlace(this.searchKey, this.page).then( res => {
					this.placeList = res;
				});
			}
		},

		nextPage(){
			if(this.placeList.length < 7){
				return;
			}
			this.page++;
			searchPlace(this.searchKey, this.page).then( res => {
				this.placeList = res;
			});	
		},

		prevPage(){
			if(this.page <= 0){
				return;
			}
			this.page--;
			searchPlace(this.searchKey, this.page).then( res => {
				this.placeList = res;
			});	
		},

		prevClickable(){
			if(this.page <= 0){
				return{
					color : '#dddddd'
				}
			} else{
				return{
					color : 'black'
				}
			}
		},

		nextClickable(){
			if(this.placeList.length < 7){
				return{
					color : '#dddddd'
				}
			} else{
				return{
					color : 'black'
				}
			}
		},


		select(index){
			this.selectBox = index;
			return index;
		},

		setColor(index){
			if(this.selectBox == index){
				return{
					backgroundColor : '#dddddd',
					color : 'black'
				}
			} else{
				return{
					backgroundColor : 'white'
				}
			}
		}
	},

	data() {
		return{
			searchKey : "",
			placeList : [],
			selectBox : -1,
			page : 0,
		}
	}
}
</script>
