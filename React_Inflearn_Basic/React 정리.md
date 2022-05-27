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

























