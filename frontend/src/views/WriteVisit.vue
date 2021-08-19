<template>
	<div class="write-visit">
		<WriteVisitHeader/>
		<WriteVisitTitle @title="getTitle"/>
		<WriteVisitPlace @pid="getPid"/>
		<WriteVisitContent @visitContent="getVisitContent" @visitCost="getVisitCost" @visitTime="getVisitTime"/>
		<WriteVisitImage @visitImage="getVisitImage"/>
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

import {writeVisit} from '@/compositions/write';

import "@/components/css/write/visit/writeVisit.css"

export default {
	name: 'WritePlace',
	mounted() {
		this.rid = this.$route.query.rid;
	}, 
	components: {
			WriteVisitHeader,
			WriteVisitTitle,
			WriteVisitPlace,
			WriteVisitContent,
			WriteVisitImage
	},
	methods: {
		async write(){
      console.log("============ 글 작성 ==============\n" + 
      "제목 : " + this.title + "\n" +
			"PID : " + this.visitPid + "\n" +
			"RID : " + this.rid + "\n" + 
      "비용 : " + this.visitCost + "\n" +
      "시간 : " + this.visitTime + "\n" +
      "내용 : " + this.visitContent + "\n" +
			"img1 : " + this.visitImage1  + "\n" +
			"img2 : " + this.visitImage2  + "\n" +
			"img3 : " + this.visitImage3  + "\n" +
      "=============================")

      if(!this.title){
        alert("제목을 입력하세요.");
        return;
      } else if(!this.visitCost){
        alert("비용을 입력하세요.");
        return;
      } else if(!this.visitTime){
        alert("보낸 시간을 입력하세요.");
        return;
      } else if(!this.visitImage1){
        alert("사진을 1개 이상 첨부하세요.");
        return;
      } else{
        await writeVisit(this.rid,this.title, this.visitPid, this.visitCost, this.visitTime, this.visitContent, this.visitImage1, this.visitImage2, this.visitImage3);
		this.$router.push({name: 'Newsfeed'});
      }


		},
		getTitle(title){
      this.title = title;
    },
		getVisitCost(visitCost){
			this.visitCost = visitCost;
			console.log(this.visitCost);
		},
		getVisitTime(visitTime){
			this.visitTime = visitTime;
		},
    getVisitContent(visitContent){
			this.visitContent = visitContent;
    },
		getPid(pid){
			this.visitPid = pid;
    },
    getVisitImage(image){
			console.log(image);
      if(!this.visitImage1){
				this.visitImage1 = image;
			} else if(!this.visitImage2){
				this.visitImage2 = image;
			} else{
				this.visitImage3 = image;
			}
    }
	},
	data: function(){
		return{
			rid : "",
			title : "",
			visitPid : "",
			visitCost : "",
			visitTime : "",
			visitContent : "",
			visitImage1 : null,
			visitImage2 : null,
			visitImage3 : null,
		}
	}
}
</script>

<style>

</style>