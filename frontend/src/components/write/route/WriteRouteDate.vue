<template>
  
  <div class="write-route-calendar">
    <span class="route-start-date">
      
      <input class="write-route-date" type="date" v-model="routeStartDate" @input="changeStartDate">
    </span>
    ~
    <span class="route-end-date">
      
      <input class="write-route-date" type="date" v-model="routeEndDate" @input="changeEndDate">
    </span>
  </div>
</template>

<script>
import "@/components/css/search/writeRouteDate.css"

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
