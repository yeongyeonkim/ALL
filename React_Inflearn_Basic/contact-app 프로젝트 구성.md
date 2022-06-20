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

* App.js 에서 handleCreate 메소드를 만들고, 이를 PhoneForm 한테 전달한다. 

```react
import React, { Component } from "react";
import PhoneForm from "./components/PhoneForm";

class App extends Component {
    handleCreate = (data) => {
        console.log(data);
    }
    render() {
        return (
            <div>
                <PhoneForm
                    onCreate={this.handleCreate}
                />
            </div>
        );
    }
}

export default App;
```

* 그리고, PhoneForm 쪽에서 버튼을 만들어서 submit 이 발생하면 props 로 받은 함수를 호출하여 App 에서 파라미터로 받은 값을 사용 할 수 있도록 한다.

```react
// 버튼 및 onSubmit 이벤트를 설정
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

    handleSubmit = (e) => {
        // 페이지 리로딩 방지
        e.preventDefault();
        // 상태 값을 onCreate를 통하여 부모에게 전달
        this.props.onCreate(this.state);
        // 상태 초기화
        this.setState({
            name: '',
            phone: '',
        });
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
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
                <button type={"submit"}>등록</button>
            </form>
        );
    }
}

export default PhoneForm;
```

* `e.preventDefault()`: form에서 submit이 발생하면 페이지를 다시 불러오게 되는데 그럴 경우 가지고 있는 상태 값을 전부 잃기 때문에 해당 이벤트로 일어나는 작업을 방지하는 역할.
* `this.props.onCreate`: 이후 props로 받은 onCreate 함수를 호출하고, 상태 값을 초기화한다.
* render 안에 submit 버튼 및 form 안에 onSubmit 이벤트를 등록.

![3](img/3.png)

---

#### 배열 다루기

* 리액트에서는 state 내부의 값을 직접적으로 수정하면 안된다.
* 기존의 배열에 기반하여 새 배열을 만들어내는 `concat`, `slice`, `map`, `filter` 와 같은 함수를 사용해야 한다.

##### 데이터 추가

* App 컴포넌트의 state에 information이라는 배열을 만들고, 동적으로 데이터를 화면에서 넣어서 잘 입력 되는지 확인
* 각 전화번호 정보는 다음과 같은 형식으로 담는다.
  * id는 각 데이터를 식별하기 위함이고, 데이터를 추가할 때마다 숫자를 1씩 더해준다.

```react
{
	id: 0,
	name: '김영연',
	phone: '010-2651-7556'
}
```

##### 데이터 렌더링

* info라는 객체를 props로 받아와서 렌더링 할 예정이다.
  * 그런데, 우리가 실수로 info 값을 전달해주지 않았다면 컴포넌트가 crash 된다. 
    info가 undefined일 때는 비구조화 할당을 통해 내부의 값을 받아올 수 없기 때문
  * 따라서 defaultProps를 통해 info의 기본 값을 설정해주었다.

```react
// PhoneInfo
import React, { Component } from 'react';

class PhoneInfo extends Component {
  static defaultProps = {
    info: {
      name: '이름',
      phone: '010-0000-0000',
      id: 0
    }
  }
  
  render() {
    const style = {
      border: '1px solid black',
      padding: '8px',
      margin: '8px'
    };

    const {
      name, phone, id
    } = this.props.info;
    
    return (
      <div style={style}>
        <div><b>{name}</b></div>
        <div>{phone}</div>
      </div>
    );
  }
}

export default PhoneInfo;
```

* data라는 배열을 가져와서 map을 통해 JSX로 변환을 해준다.
  * 이 과정에서, key 라는 값을 설정하였는데, 리액트에서 배열을 렌더링 할 때 **꼭** 필요한 값이다.

```react
// PhoneInfoList
import React, { Component } from 'react';
import PhoneInfo from './PhoneInfo';

class PhoneInfoList extends Component {
  static defaultProps = {
    data: []
  }

  render() {
    const { data } = this.props;
    const list = data.map(
      info => (<PhoneInfo key={info.id} info={info}/>)
    );

    return (
      <div>
        {list}    
      </div>
    );
  }
}

export default PhoneInfoList;
```

* 이후 App 에서 렌더링해주고 data 값을 props로 전달한다.

```react
// App
// App.js
import React, {Component} from "react";
import PhoneForm from "./components/PhoneForm";
import PhoneInfoList from "./components/PhoneInfoList";

class App extends Component {
    id = 0;
    state = {
        information: [],
    }
    handleCreate = (data) => {
        const { information } = this.state;
        this.setState({
           information: information.concat(Object.assign({}, data, {
               id: this.id++
           }))
        });
    }

    render() {
        return (
            <div>
                <PhoneForm onCreate={this.handleCreate}/>
                <PhoneInfoList data={this.state.information} />
            </div>
        )
    }
}

export default App;
```

---

#### 데이터 제거

* 배열에는 **filter**라는 내장함수가 있다.

  * 특정 조건에 부합되는 원소들만 뽑아내서 새 배열을 만들어준다.

    ```react
    array.filter(num => num !== 2); // [1, 3, 4, 5]
    ```

* 추가한 데이터를 삭제하는 기능을 구현해본다.
  * id를 파라미터로 받아오는 `handleRemove`라는 함수를 만들고, PhoneInfoList에 전달.

```react
// App
// ~
  handleRemove = (id) => {
    const { information } = this.state;
    this.setState({
      information: information.filter(info => info.id !== id)
    })
  }
  render() {
    const { information } = this.state;
    return (
      <div>
        <PhoneForm
          onCreate={this.handleCreate}
        />
        <PhoneInfoList 
          data={information}
          onRemove={this.handleRemove}
        />
      </div>
    );
  }
}

export default App;
```

* PhoneInfoList에서는 props로 전달받은 onRemove를 그대로 전달해준다.

```react
// PhoneInfoList
import React, { Component } from 'react';
import PhoneInfo from './PhoneInfo';

class PhoneInfoList extends Component {
  static defaultProps = {
    list: [],
    onRemove: () => console.warn('onRemove not defined'),
  }

  render() {
    const { data, onRemove } = this.props;
    const list = data.map(
      info => (
        <PhoneInfo
          key={info.id}
          info={info}
          onRemove={onRemove}
        />)
    );

    return (
      <div>
        {list}    
      </div>
    );
  }
}

export default PhoneInfoList;
```

* PhoneInfo 쪽에서 삭제 기능을 구현

```react
// PhoneInfo
import React, { Component } from 'react';

class PhoneInfo extends Component {
  static defaultProps = {
    info: {
      name: '이름',
      phone: '010-0000-0000',
      id: 0
    },
  }

  handleRemove = () => {
    // 삭제 버튼이 클릭되면 onRemove 에 id 넣어서 호출
    const { info, onRemove } = this.props;
    onRemove(info.id);
  }

  render() {
    const style = {
      border: '1px solid black',
      padding: '8px',
      margin: '8px'
    };

    const {
      name, phone
    } = this.props.info;
    
    return (
      <div style={style}>
        <div><b>{name}</b></div>
        <div>{phone}</div>
        <button onClick={this.handleRemove}>삭제</button>
      </div>
    );
  }
}

export default PhoneInfo;
```

#### 데이터 수정

* id와 data라는 파라미터를 받아와서 정보를 업데이트하는 `handleUpdate` 함수를 만든다.
* PhoneInfoList의 onUpdate로 전달한다.

``` react
// App
// ~
  handleUpdate = (id, data) => {
    const { information } = this.state;
    this.setState({
      information: information.map(
        info => id === info.id
          ? { ...info, ...data } // 새 객체를 만들어서 기존의 값과 전달받은 data 을 덮어씀
          : info // 기존의 값을 그대로 유지
      )
    })
  }
  render() {
    const { information } = this.state;
    return (
      <div>
        <PhoneForm
          onCreate={this.handleCreate}
        />
        <PhoneInfoList 
          data={information}
          onRemove={this.handleRemove}
          onUpdate={this.handleUpdate}
        />
      </div>
    );
  }
}

export default App;
```

```react
// PhoneInfoList
import React, { Component } from 'react';
import PhoneInfo from './PhoneInfo';

class PhoneInfoList extends Component {
  static defaultProps = {
    data: [],
    onRemove: () => console.warn('onRemove not defined'),
    onUpdate: () => console.warn('onUpdate not defined'),
  }

  render() {
    const { data, onRemove, onUpdate } = this.props;
    const list = data.map(
      info => (
        <PhoneInfo
          key={info.id}
          info={info}
          onRemove={onRemove}
          onUpdate={onUpdate}
        />)
    );

    return (
      <div>
        {list}    
      </div>
    );
  }
}

export default PhoneInfoList;
```

```react
// PhoneInfo
import React, { Component } from 'react';

class PhoneInfo extends Component {
  static defaultProps = {
    info: {
      name: '이름',
      phone: '010-0000-0000',
      id: 0
    },
  }

  state = {
    // 수정 버튼을 눌렀을 떄 editing 값을 true 로 설정해줄 것
    // 이 값이 true 일 때 기존에 텍스트 형태로 보여주던 값들을 input 형태로 보여주게 된다.
    editing: false,
    // input 의 값은 유동적이다. input 값을 담기 위해서 각 필드를 위한 값도 설정
    name: '',
    phone: '',
  }

  handleRemove = () => {
    // 삭제 버튼이 클릭되면 onRemove 에 id 넣어서 호출
    const { info, onRemove } = this.props;
    onRemove(info.id);
  }

  // editing 값을 반전시키는 함수
  // true -> false, false -> true
  handleToggleEdit = () => {
    const { editing } = this.state;
    this.setState({ editing: !editing });
  }

  // input 에서 onChange 이벤트가 발생 될 때 호출되는 함수
  handleChange = (e) => {
    const { name, value } = e.target;
    this.setState({
      [name]: value
    });
  }


  componentDidUpdate(prevProps, prevState) {
    // editing 값이 바뀔 때 처리 할 로직
    // 수정을 눌렀을 때 -> 기존의 값이 input에 나타남
    // 수정을 적용할 때 -> input의 값들을 부모한테 전달해줌

    const { info, onUpdate } = this.props;
    if(!prevState.editing && this.state.editing) {
      // editing 값이 false -> true 로 전환 될 때 info 의 값을 state 에 넣어준다
      this.setState({
        name: info.name,
        phone: info.phone
      })
    }

    if (prevState.editing && !this.state.editing) {
      // editing 값이 true -> false 로 전환될 때
      onUpdate(info.id, {
        name: this.state.name,
        phone: this.state.phone
      });
    }
  }
  
  render() {
    const style = {
      border: '1px solid black',
      padding: '8px',
      margin: '8px'
    };

    const { editing } = this.state;

    
    if (editing) { // 수정모드
      return (
        <div style={style}>
          <div>
            <input
              value={this.state.name}
              name="name"
              placeholder="이름"
              onChange={this.handleChange}
            />
          </div>
          <div>
            <input
              value={this.state.phone}
              name="phone"
              placeholder="전화번호"
              onChange={this.handleChange}
            />
          </div>
          <button onClick={this.handleToggleEdit}>적용</button>
          <button onClick={this.handleRemove}>삭제</button>
        </div>
      );
    }


    // 일반모드
    const {
      name, phone
    } = this.props.info;
    
    return (
      <div style={style}>
        <div><b>{name}</b></div>
        <div>{phone}</div>
        <button onClick={this.handleToggleEdit}>수정</button>
        <button onClick={this.handleRemove}>삭제</button>
      </div>
    );
  }
}

export default PhoneInfo;
```





