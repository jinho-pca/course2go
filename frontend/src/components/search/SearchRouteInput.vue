<template>
  <div class="add-route-box" style="border:1px solid rgba(68, 65, 65, 0.192); border-radius:15px;">
    <div style="text-align:center;">검색 장소</div>
    <br />
    <SearchPlace
      class="search-modal"
      v-if="showModal"
      @close="showModal = false"
      @place="addPlace"
    />
    <div class="added-route" v-for="(place, idx) in placeList" :key="idx" :place="place">
      <span class="added-place">{{ place.placeName }}</span>
      <span class="remove-route" @click="removeRoute">x</span>
    </div>
    <div>
      <button class="add-button" @click="showModal = true" v-if="placeList.length < 9">
        +
      </button>
    </div>
    <!-- <i class="fas fa-search" @click="getWord"></i> -->
  </div>
</template>

<script>
import "@/components/css/search/search-route-input.css";
import SearchPlace from "@/components/search/SearchPlace.vue";
import { search } from "@/compositions/search.js";

export default {
  name: "SearchInput",
  components: {
    SearchPlace,
  },
  setup(_, { emit }) {
    const { showModal, placeList, placePidList } = search();
    async function addPlace(place) {
      if (placePidList.value.includes(place.pid)) {
        alert("이미 추가한 장소 입니다.");
        return
      }
      showModal.value = false;
      placePidList.value.push(place.pid);
      placeList.value.push(place);
      emit("routePid", placePidList);
    }

    const removeRoute = () => {
      const div = document.querySelectorAll(".added-route");
      for (let i = 0; i < div.length; i++) {
        div[i].onclick = function() {
          placeList.value.splice(i, 1);
          placePidList.value.splice(i, 1);
          emit("routePid", placePidList);
        };
      }
    };
    return { showModal, placeList, placePidList, addPlace, removeRoute };
  },
  emits: ["routePid"],
};
</script>
