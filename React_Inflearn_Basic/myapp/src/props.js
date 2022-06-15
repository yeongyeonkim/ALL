import React, { Component } from 'react';

class props extends Component {
    //방식 1
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
//방식 2
/*
props.defaultProps = {
    name: 'default'
};
 */

export default props;