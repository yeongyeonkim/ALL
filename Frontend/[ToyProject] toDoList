#### React TypeScript

##### [디렉토리 구조]

##### public

* React 프로젝트의 정적(Static) 파일들이 저장된 폴더
* 리액트는 SPA(Single Page Application) 이기 때문에 모든 페이지가 Public 폴더의 index.html으로 모여 처리된다.
* `index.html`: 브라우저에 나타내는 HTML 파일
* `manifest.json`: PWS(Progressive Web Apps)에 필수로 포함되어야 하는 파일

##### src

* `App.test.tsx`: DOM을 테스트하기 위한 도구. React Testing Library나 Jest 등 테스트 라이브러리를 사용

* `App.tsx`: CRA(Create React App)가 제공하는 기본 예제 파일
* `index.tsx`: App.tsx에 모인 컴포넌트들을 public/index.html과 연결해주는 파일



##### javascript:void(0)

* 보통 html의 `<a>` 태그를 사용하고 클릭하게 되면, 그 속성 중 하나인 href에 지정된 url로 페이지가 바뀌면서 이동을 하게된다. 이처럼 페이지가 바뀌면서 이동을 하지 않게 하고 싶은 경우 사용한다.
* void 연산자가 undefined 값을 반환하면서 a 태그의 href 속성 값에 undefined 값이 지정되고 아무런 동작을 하지 않게된다.
  * `<a href="javascript:undefined ~~ "` 와 같이 직접 값을 지정해서 사용해도 되지만, 구버전 브라우저의 경우 global 변수로 사용되어 다시 재 사용될 수 있는 문제점 등이 있어 void(0)으로 사용한다.
