<template>
  <div>

    <RouteContent />
    <RouteDetailCard />
  </div>
</template>

<script>
import '@/assets/css/user/email.css';
import RouteContent from '@/components/newsfeed/RouteContent.vue'
import RouteDetailCard from '@/components/newsfeed/RouteDetailCard.vue'

export default {
  name: 'RouteDetail',
  components: {
    RouteContent,
    RouteDetailCard
  },
  setup() {
    const back = () => {
      history.back()
    }
    return back
  }
}
</script>