import React, { Component } from "react";

class Conditional extends Component {
    render() {
        //1
        // return(
        //   1 + 1 === 2
        //     ? (<div>Right_1</div>)
        //     : (<div>Wrong_1</div>)
        // );
        //2
        // return (
        //   <div>
        //       {
        //           1 + 1 === 2 && (<div>Right_2</div>)
        //       }
        //   </div>
        // );
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