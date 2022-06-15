```react
// React 모듈을 사용하기 위한 import
import React, {Component} from 'react';

// Component를 만들기 위한 방법으로 클래스와 함수가 있다.
class App extends Component {
    render() { // 메소드, JSX 형태의 코드를 return 해주어야 한다.
        return (
            <div>
                <h1>Hello React</h1>
            </div>
        );
    }
}

export default App;
```

##### JSX

* 리액트 개발을 쉽게하도록 HTML과 비슷한 문법으로 작성하면 이를 React.createElement를 사용하는 자바스크립트 형태로 변환시켜 준다.

* https://react-anyone.vlpt.us/03.html

##### JSX 문법

```
class App extends Component {
    render() {
        return (
            <div>
                <h1>Hello React</h1>
            </div>
            <div>
                <h1>element Test
                </h1>
            </div>
        );
    }
}
```

* 위와 같이 작성하면 두 개 이상의 엘리먼트가 감싸져있지 않기 때문에 에러가 발생한다.

  ` Parsing error: Adjacent JSX elements must be wrapped in an enclosing tag. Did you want a JSX fr
  agment <>...</>? `

```react
class App extends Component {
    render() {
        return (
            <div>
                <div>
                    <h1>Hello React</h1>
                </div>
                <div>
                    <h1>element Test
                    </h1>
                </div>
            </div>
        );
    }
}
```

* 하지만 위 방법은 오직 문법을 위한 불필요한 div 사용이기 때문에 `Fragment`를 사용한다.

```react
import React, { Component, Fragment } from 'react';

class App extends Component {
    render() {
        return (
            <Fragment>
                <div>
                    <h1>Hello React</h1>
                </div>
                <div>
                    <h1>element Test</h1>
                </div>
            </Fragment>
        );
    }
}

export default App;
```

##### JSX 내부에서 자바스크립트 사용하기

```react
import React, { Component } from 'react';

class App extends Component {
    render() {
        const name = 'react';
        return {
            <div>
                hello {name}!
            </div>
        };
    }
}

export default App;
```

##### var, const, let

* var는 ES6 이후 사용하지 않으며
* Const : 한 번 선언 후 고정적인 값
* let : 유동적인 값

```react
function foo() {
  let a = 'hello';
  if (true) {
    let a = 'bye';
    console.log(a); // bye
  }
  console.log(a); // hello
}
```

##### JSX 조건부

1. 삼항 연산자

```react
import React, { Component } from "react";

class Conditional extends Component {
    render() {
        return(
          1 + 1 === 2
            ? (<div>Right</div>)
            : (<div>Wrong</div>)
        );
    }
}

export default Conditional;
```

2. AND 연산자

```react
import React, { Component } from "react";

class Conditional extends Component {
    render() {
        return (
          <div>
              {
                  1 + 1 === 2 && (<div>Right</div>)
              }
          </div>
        );
    }
}

export default Conditional;
```

* 복잡한 조건식의 경우 JSX 밖에서 로직을 작성하는 것이 좋지만, 내부에서 작성해야하는 경우 아래처럼 사용할 수 있다.

```react
import React, { Component } from "react";

class Conditional extends Component {
    render() {
        const value = 1;
        return (
          <div>
              {
                  (() => {
                      if (value === 1) return <div> 1 </div>
                      if (value === 2) return <div> 2 </div>
                      if (value === 3) return <div> 3 </div>
                      return <div>없다</div>
                  })()
              }
          </div>
        );
    }
}

export default Conditional;
```

##### JSX style와 className 

```react
import React, { Component, Fragment } from 'react';

class style extends Component {
    render() {
        const style = {
            backgroundColor: 'black',
            padding: '16px',
            color: 'white',
            fontSize: '36px'
        };
        return (
          <div style={style}>
              안녕 Style!
          </div>
        );
    }
}

export default style;
```

##### Css 파일 적용하기

* App.css

```css
.AppStyle {
    background: black;
    color: aqua;
    font-size: 36px;
    padding: 1rem;
    font-weight: 600;
}
```

* style.js

```react
import React, { Component } from 'react';
import './App.css';

class style extends Component {
    render() {
        return (
          <div className="AppStyle"> // css 파일의 className과 일치시켜 주어야 한다
              안녕 Style!
          </div>
        );
    }
}

export default style;
```

##### JSX 내부에서 주석을 사용하는 방법 `{ }`

```react
import React, { Component } from 'react';
import './App.css';

class style extends Component {
    render() {
        return (
          <div>
            {/* 주석 처리를 위해 사용*/}
            <h1>리액트</h1>
          </div>
        );
    }
}

export default style;
```

---

##### Props와 state

props: 부모 컴포넌트가 자식 컴포넌트에게 주는 값. 자식 컴포넌트에서는 props를 받아올 수만 있고 직접 수정할 수 없다.

```react
import React, { Component } from 'react';

class props extends Component {
    render() {
        return (
            <div>
                안녕하세요 제 이름은 <b>{this.props.name} 입니다.</b>
            </div>
        );
    }
}

export default props;
```

```react
import React, { Component, Fragment } from 'react';
import PropsAndState from './props';

class App extends Component {
    render() {
        return (
            <PropsAndState name="리액트" />
        );
    }
}

export default App;
```

```react
import React from 'react';
import { render } from 'react-dom';
import App from './App';

render(<App />, document.getElementById('root'));
```

* props 값을 일부러 비워야할 때나 설정하지 않은 경우 오류가 발생하지 않도록 **기본 값**을 설정해준다.

```react
import React, { Component } from 'react';

class props extends Component {
    // 방식 1
    static defaultProps = {
        name: 'default'
    }
    render() {
        return (
            <div>
                안녕하세요 제 이름은 <b>{this.props.name}</b> 입니다.
            </div>
        );
    }
}
// 방식 2

export default props;
```

state: 반면에 컴포넌트 내부에서 선언하여 내부에서 값을 변경할 수 있다.

-> 동적인 데이터를 다룰 때 사용

```react
import React, { Component } from "react";

class state extends Component {
    state = {
        number: 0
    }

    handleIncrease = () => {
        this.setState({
            number: this.state.number + 1
        });
    }

    handleDecrease = () => {
        this.setState({
           number: this.state.number - 1
        });
    }

    render() {
        return (
            <div>
                <h1>state</h1>
                <div>값: {this.state.number}</div>
                <button onClick={this.handleIncrease}>+</button>
                <button onClick={this.handleDecrease}>-</button>
            </div>
        );
    }
}

export default state;
```

---

### LifeCycle API

*  컴포넌트가 브라우저에서 나타날 때, 사라질 때, 업데이트 될 때 호출되는 API

```react
import React, { Component } from 'react';

const Problematic = () => {
    throw (new Error('버그가 나타났다!'));
    return (
        <div>

        </div>
    );
};

class LifeCycleAPI extends Component{
    state = {
        number: 0
    }

    constructor(props) {
        super(props);
        console.log('constructor');
    }

    componentWillMount() {
        console.log('componentWillMount (deprecated)');
    }

    componentDidMount() {
        console.log('componentDidMount');
    }

    shouldComponentUpdate(nextProps, nextState) {
        // 5 의 배수라면 리렌더링 하지 않음
        console.log('shouldComponentUpdate');
        if (nextState.number % 5 === 0) return false;
        return true;
    }

    componentWillUpdate(nextProps, nextState) {
        console.log('componentWillUpdate');
    }

    componentDidUpdate(prevProps, prevState) {
        console.log('componentDidUpdate');
    }


    handleIncrease = () => {
        const { number } = this.state;
        this.setState({
            number: number + 1
        });
    }

    handleDecrease = () => {
        this.setState(
            ({ number }) => ({
                number: number - 1
            })
        );
    }

    componentDidCatch(error, info) {
        this.setState({
            error: true
        });
    }

    render() {
        if (this.state.error) return (<h1>에러발생!</h1>);

        return (
            <div>
                <h1>카운터</h1>
                <div>값: {this.state.number}</div>
                { this.state.number === 4 && <Promblematic /> }
                <button onClick={this.handleIncrease}>+</button>
                <button onClick={this.handleDecrease}>-</button>
            </div>
        );
    }
}

export default LifeCycleAPI;
```

* 개발자 도구 console에서 찍히는 순서를 확인하고, 특정 상황으로 렌더링 되었을 때 에러가 발생했음을 알리는 Problematic을 사용하여 웹 페이지에서 확인

---



















