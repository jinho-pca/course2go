<template>
  <div>
    <div class="content">

      <!-- <div class="visit-title">
        {{articleVisit.boardResponse.boardTitle}}
      </div>
      <div class="visit-content">
        {{articleVisit.visitResponse.visitContent}}
      </div> -->

      <div class="images">
        <div class="slideshow-container">
          <!-- Full-width images with number and caption text -->
          
          <div class="mySlides fade image" :style="selectImage(1)">
            <div class="numbertext">1 / {{imageNum}}</div>
            <img :src="articleVisit.visitResponse.visitImage1" style="width:100%">
            <!-- <div class="text">Caption Text</div> -->
          </div>

          <div class="mySlides fade image" :style="selectImage(2)">
            <div class="numbertext">2 / {{imageNum}}</div>
            <img :src="articleVisit.visitResponse.visitImage2" style="width:100%">
            <!-- <div class="text">Caption Text</div> -->
          </div>

          <div class="mySlides fade image" :style="selectImage(3)">
            <div class="numbertext">3 / {{imageNum}}</div>
            <img :src="articleVisit.visitResponse.visitImage3" style="width:100%">
            <!-- <div class="text">Caption Text</div> -->
          </div>

          <!-- Next and previous buttons -->
          <a class="prev fade-btn" @click="plusSlides(-1)">&#10094;</a>
          <a class="next" @click="plusSlides(1)">&#10095;</a>
        </div>
        <br>

        <!-- The dots/circles -->
        <div style="text-align:center">
          <span class="dot" v-for="index in imageNum" :key="index" @click="currentSlide(index)"></span>
        </div>
      </div>
      <hr/>

    </div>
  </div>
</template>

<script>
import '@/components/css/article/visit-detail.css'
import jwt from 'jsonwebtoken'
import {BASE_URL} from '@/compositions/global.js'
export default {
  name: "visitdetail",
  props: {
    articleVisit: {
      type: Object,
    },
  },
  // created() {
  //   console.log("카드")
  //   console.log(this.routeReadResponse)
  //   console.log("카드")
  // },
  updated(){
    console.log(this.articleVisit.visitResponse.visitImage2)
    if(this.articleVisit.visitResponse.visitImage3){
      this.imageNum = 3;
    } else if(this.articleVisit.visitResponse.visitImage2){
      this.imageNum = 2;
    } else if(this.articleVisit.visitResponse.visitImage1){
      this.imageNum = 1;
    } else{
      this.imageNum = 0;
    }
  },
  methods: {
    currentSlide(page){
      this.imagePage = page;
    },
    selectImage(idx){
      if(idx == this.imagePage){
        return{
          'display' : 'block'
        }
      } else{
        return{
          'display' : 'none'
        }
      }
    },
    plusSlides(num){
      this.imagePage += num;
      if(this.imagePage > this.imageNum){
        this.imagePage = 1;
      } else if(this.imagePage < 1){
        this.imagePage = this.imageNum
      }
    }
  },
  data() {
    return {
      token : jwt.decode(BASE_URL().token.substr(7)).userNickname,
      imagePage : 1,
      imageNum : 3,
    }
  },
}
</script>

