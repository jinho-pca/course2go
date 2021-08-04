<template>
  <div class="newsfeed-card">
    <div class="newsfeed-card-top">
      <div class="newsfeed-title">
        Goki 님의 08.19 ~ 08.24 여행
      </div>
      <i class="far fa-star">23</i>
    </div>
    <img src="@/assets/images/map.jpg" alt="map image" class="newsfeed-image">
    <div class="newsfeed-description">
      이번 제주도 여행은 반시계 방향의 동선으로 선택했는데 재밌었다.
    </div>
    <!-- comment -->
    <div class="newsfeed-card-bottom">
      <div class="newsfeed-comment">
        <span class="comment-writer">
          jinho_pca
        </span>
        <span class="comment-content">
          와~~ 부러워용
        </span>
      </div>
      <div>
        <i class="fas fa-thumbs-up">23</i>
        <i class="fas fa-thumbs-down">23</i>
      </div>
    </div>
    <!-- comment -->
    <div style="font-size:10px; margin: 5px 10px 10px 0;">...더보기</div>
  </div>
</template>

<script>
import '../css/newsfeed/newsfeed-card.css'
export default {
  name: 'newsfeedcard'
}
</script>