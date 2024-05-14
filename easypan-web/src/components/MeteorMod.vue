<template>
  <div class="rain">
    <div
        v-for="(item, index) in rainNumber"
        :key="index"
        class="rain-item"
        ref="rainItem"
        :style="`transform: rotate(${rotateDeg}deg); width: ${w}px; height: ${h}px; top: ${item.top}px; left: ${item.left}px;`"
    >
      <div class="line" :style="`animationDelay: ${index * 100}ms`"></div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    rainNumber: {
      type: Number,
      default: 0,
    },
    rotateDeg: {
      type: Number,
      default: 0,
    },
    w: {
      type: Number,
      default: 0,
    },
    h: {
      type: Number,
      default: 0,
    },
  },
  mounted() {
    this.randomRain();
  },
  methods: {
    randomRain() {
      let rainItems = this.$refs.rainItem;
      rainItems.forEach((item) => {
        item.style.top = Math.floor(Math.random() * 100 + 1) + "px";
        item.style.left = Math.floor(Math.random() * 2300 + 1) + "px";
      });
    },
  },
};
</script>

<style lang="less" scoped>
.rain {
  width: 100%;
  height: 100vh;
  position: relative;
  .rain-item {
    position: absolute;
    width: 2px;
    height: 30px;
    display: inline-block;
    .line {
      animation: raining 2s infinite linear;
      position: absolute;
      content: "";
      top: -30px;
      left: 0;
      width: 100%;
      height: 100%;
      box-shadow: 0px 5px 20px 0px #fcfcfc;
      background: linear-gradient(to top, rgb(249, 249, 249), rgba(11, 36, 66, 0.1));
    }
  }
}
@keyframes raining {
  0% {
    top: -30px;
    opacity: 0;
  }
  10% {
    top: 10px;
    opacity: 0.5;
  }
  25% {
    top: 200px;
    opacity: 0.5;
  }
  50% {
    top: 400px;
    opacity: 1;
  }
  75% {
    top: 600px;
    opacity: 0.5;
  }
  100% {
    top: 800px;
    opacity: 0;
  }
}
</style>
