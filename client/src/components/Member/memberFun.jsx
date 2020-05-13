import React from "react";
import {Link} from 'react-router-dom';

export class MemberFun extends React.Component {
    constructor(props) {
      super(props);
    }
  
    render() {
      return (
        <div className="footer">
            <div className="s">
                <Link to="/create">
                    <button className="store">add store</button>
                </Link>
            </div>
        </div>
      );
    }
  }