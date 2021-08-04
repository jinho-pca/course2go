<template>
  <div> 글쓰기 </div>
  <div>
    <span class="route-start-date">
      <span>시작일</span>
      <input type="date" v-model="routeStartDate" @input="changeStartDate">
    </span>
    <span class="route-end-date">
      <span>종료일</span>
      <input type="date" v-model="routeEndDate" @input="changeEndDate">
    </span>
  </div>
</template>

<script>
export default {
  emits: ["routeStartDate", "routeEndDate"],
  methods: {
    changeStartDate(){
      this.$emit('routeStartDate', this.routeStartDate);
    },
    changeEndDate(){
      this.$emit('routeEndDate', this.routeEndDate);
    },
  },

  data: function(){
		return{
      routeStartDate : "",
      routeEndDate : "",
		}
	}
}
</script>
