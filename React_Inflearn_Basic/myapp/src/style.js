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