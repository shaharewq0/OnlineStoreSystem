import React from "react";
import {Link} from 'react-router-dom';

export class GuestFun extends React.Component {
    constructor(props) {
      super(props);
    }
  
    render() {
      return (
        <div className="footer">
            <div className="s">
              <Link to="/store">
                <button className="store">Search Stores</button>
              </Link>
            </div>
            <div className="p">
                <button className="product">Search products</button>
            </div>
        </div>
      );
    }
  }