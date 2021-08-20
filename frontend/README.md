# frontend

## Project setup

```
yarn install
```

### Compiles and hot-reloads for development

```
yarn serve
```



### Settings

```bash
$ vue add vuex

$ vue add router

 WARN  There are uncommitted changes in the current repository, it's recommended to commit or stash them first.
? Still proceed?
Y

? Use history mode for router? (Requires proper server setup for index fallback in production)
Y

$ yarn add axios # npm install axios
$ yarn add password-validator # npm install password-validator
$ yarn add email-validator # npm install email-validator
$ yarn add jsonwebtoken # npm install jsonwebtoken
```



### Run Server

```bash
# 포트 변경을 위해서는 yarn 필요
$ yarn server --port 3000
```



## Vue 3

* `ref`는 보통 primitive type, `reactive`는 기존 vue에서 data 속성의 느낌이다.

  * ref는 ref.value / reactive는 reactive.count로 접근한다
  * reactive는 primitive type에 대해서는 반응형으로 동작하지 않기 때문에 ref 사용이 권장된다(?)

* watch vs watchEffect

  * watchEffect는 이전 데이터에 상관 없이 변화에만 신경쓸 때 사용하면 된다.

  ```js
  watchEffect(() => checkForm({ password: password.value, email: email.value}))
  ```

  > password.value나 email.value가 변경되면 checkForm 실행

  

* Actions vs Mutations

  * 변경 순서 파악을 위해 setTimeout() 이나 서버와의 http 통신 처리 같이 결과를 받아올 타이밍이 예측되지 않은 비동기적 로직은 Actions 에 선언한다.
  * Mutations은 State의 관리에 중점을 둔다.
    * `state.count++` 같은 단순한 로직의 경우 바로 `commit`를 이용해 Mutation으로 이동해도 된다.




* **CORS 에러**(Front)

```javascript
Error: Network Error    at createError (createError.js?2d83:16)    at XMLHttpRequest.handleError (xhr.js?b50d:84)
```

> 로그인 시, cors 에러가 나타났을 때 headers를 추가해줘야 한다.

```javascript
headers:{    'Content-Type': 'application/x-www-form-urlencoded',        'Accept': 'application/json'}})
```



* `mapState` in compositions

```js
const user = computed(() => store.state.user)
```






## Router 4

### Composition API

> setup()의 사용

```js
import {useRouter, useRoute} from 'vue-router'
export default {    
    setup() {        
        const username = ref('')        
        const password = red('')        
        const router = useRouter()        
        const route = useRoute()       	
        const login = ()=>{            
            // this.username => username.value            
            window.user = username.value            
            const redirectPath = route.query.redirect || '/protected'            					router.push(redirectPath)        
        }        
        return {username, password, login}    
    }
}
```



###  Scroll Behavior

```js
const router = createRouter({  
    history: createWebHashHistory(),
    routes: [...],  
    scrollBehavior (to, from, savedPosition) {      
             return savedPosition || { top:0 }	
             /* return {top: null, left: null, behavior: null} */  
             }
})
export default router 
```

> 여기서 0.3s의 fade 효과 때문에 UX에 문제가 생긴다. 이를 해결하기 위해 `Timeout`을 사용한다.

```js
const router = createRouter({  
    history: createWebHashHistory(),  
    routes: [...], 
    scrollBehavior (to, from, savedPosition) {      
        return savedPosition || new Promise((resolve)=>{    	
    	    setTimeOut(()=> resolve({ 
    			top:0, behavior: 'smooth' 
			}), 300)})  
		}
	})
export default router 
```

> behavior: 'smooth'를 사용해 스크롤이 매끄럽게 올라가도록 한다



* 특정한 페이지에서만 이것이 일어나도록 하고 싶다면

```js
const router = createRouter({  
    scrollBehavior(to, from, savedPosition) {    
        // always scroll 10px above the element #main    
        return {      
            // could also be      
            // el: document.getElementById('main'),      
            el: '#main',      
            top: -10,    
        }  
    },
})
```



### Lazy Loading Routes

> app.js라는 entire bundle을 계속 load하는 것보다 그때그때 필요한 것을 가져오는 것이 속도 면에서 우수하다.
>
> code splitting, lazy loading => path를 dynamic import하면 된다
>
> 위에 전부 import하는 대신 하나씩 그때그때 import

```js
{    	
    path: 'login',	    
    name: 'login',    	
    component: ()=> import(/* webpackChunckName: "login" */ '@/views/Login.vue')    
},
```

> F12(개발자 도구)에서 'Network' 탭에서 js를 눌렀을 때, 위와 같이 `/* webpackChunckName: "login" */`을 작성해주면 해당 이름(`login.js`)으로 보인다.



### Navigation Failures

```js
const navigationResult = await router.push('/my-profile')
if (navigationResult) {  
    // navigation prevented
} else {  
    // navigation succeeded (this includes the case of a redirection)  
    this.isMenuOpen = false
}
```



* `isNavigationFailure` 사용

```js
import { NavigationFailureType, isNavigationFailure } from 'vue-router'
// trying to leave the editing page of an article without savingconst 
failure = await router.push('/articles/2')
if (isNavigationFailure(failure, NavigationFailureType.aborted)) {  
    // show a small notification to the user  
    showToast('You have unsaved changes, discard and leave anyway?'
             )
}
```



### Dynamic Routing

#### Adding Routes

```js
router.addRoute({ path: '/about', component: About })
// we could also use this.$route or route = useRoute() (inside a setup)router.
replace(router.currentRoute.value.fullPath)
```



#### Adding Routes inside navigation guards

```js
router.beforeEach(to => {
    if (!hasNecessaryRoute(to)) {    
        router.addRoute(generateRoute(to))    
        // trigger a redirection    
        return to.fullPath  
    }
})
```

> 같은 route로 무한 redirection 되는 것을 피하기 위해 `to` location과 대조한다.



#### Removing routes

* `name`은 Unique하기 때문에 같은 route를 add하면 덮어쓰기된다.

```js
router.addRoute({ 
    path: '/about', 
    name: 'about', 
    component: About 
})
// this will remove the previously added route because they have the same name and names are uniquerouter.
addRoute({ 
    path: '/other', 
    name: 'about', 
    component: Other 
})
```

* removeRoute를 사용한다.

```js
router.addRoute({ 
    path: '/about', 
    name: 'about', 
    component: About 
})
// remove the routerouter.
removeRoute('about')
```



## Vuex

### Getters

```js
const store = createStore({  
    state: {    
        todos: [      
            { 
                id: 1, 
             	text: '...', 
             	done: true 
            },      
            { 
                id: 2, 
                text: '...', 
                done: false 
            }    
        ]  
    },  
    getters: {    
        doneTodos (state) {      
            return state.todos.filter(todo => todo.done)    
        }  
    }
})
```



* 아무 component에서나 사용 가능하다

```js
computed: {  
    doneTodosCount () {    
        return this.$store.getters.doneTodosCount  
    }
}
```



#### Method-Style Access

```js
getters: {  
    getTodoById: (state) => (id) => {    
    	return state.todos.find(todo => todo.id === id)  
	}
}
```

```js
store.getters.getTodoById(2) // -> { id: 2, text: '...', done: false }
```



### Strict Mode

> 디버깅을 강력하게 도와준다. 사용하더라도 Development Mode일 때만 true로 허용하도록 한다.

```js
const store = createStore({  // ...  strict: process.env.NODE_ENV !== 'production'})
```
