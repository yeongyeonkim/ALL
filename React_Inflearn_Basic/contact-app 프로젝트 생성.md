##### 환경 설정

1. node js 설치 (LTS)
2. yarn 설치 (https://classic.yarnpkg.com/en/docs/install#windows-stable)
3. `npx create-react-app contact-app` 프로젝트 생성
4. `npm start`

---

#### 컴포넌트 작성

* Intellij `React snippet` 플러그인을 설치하여 사용
  * `rcc` 명령어를 입력하면, 클래스 형태로 컴포넌트를 자동 생성해준다.
  * `rsc` 명령어를 입력하면, 함수 형태로 컴포넌트를 자동 생성해준다.

* `src/components` 디렉토리 생성 후 `PhoneForm.js` 파일 생성

```react
import React, {Component} from 'react';

class PhoneForm extends Component {
    render() {
        return (
            <div>
                PhoneForm
            </div>
        );
    }
}

export default PhoneForm;
```

* 이후 해당 PhoneForm 컴포넌트를 사용하기 위해, 아래 사진상 2번째 라인의 import 구문을 작성할 필요 없이 코드 내에서 바로 렌더링을 해줄 수 있다.

![1](img/1.JPG)

``` react
import React, {Component} from 'react';

class PhoneForm extends Component {
    state = {
        name: '',
        phone: '',
    }

    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    render() {
        return (
            <form>
                <input
                    name={"name"}
                    placeholder="이름"
                    onChange={this.handleChange}
                    value={this.state.name}
                />
                <input
                    name={"phone"}
                    placeholder="전화번호"
                    onChange={this.handleChange}
                    value={this.state.phone}
                />
                <div>
                    {this.state.name} {this.state.phone}
                </div>
            </form>
        );
    }
}

export default PhoneForm;

```

* handleChange: input에서 변경이벤트가 발생될때 처리할 함수, e라는 이벤트 객체를 파라미터로 받는다.
* `<input onChange={this.handleChange}/>` input 에서 값이 들어올 때 마다 name이 변경될 것.

* `placeholder`: 아무것도 입력되지 않았을 때 회색글씨로 input 창에 보여지는 값.

* `[e.target.name]`: input 태그 안의 name 값이 들어오게 됨.

---

#### 자식 컴포넌트가 부모한테 값 전달하기

![2](img/2.png)

* PhoneForm.js -> App.js
* App.js 에서 handleCreate 메소드를 만들고, 이를 PhoneForm 한테 전달한다. 
* 그리고, PhoneForm 쪽에서 버튼을 만들어서 submit 이 발생하면 props 로 받은 함수를 호출하여 App 에서 파라미터로 받은 값을 사용 할 수 있도록 한다.

