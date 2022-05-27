import React, { Component } from "react";

class JSXInJS extends Component {
    render() {
        const name = 'react';
        return (
            <div>
                hello {name}!
            </div>
        );
    }
}

export default JSXInJS;