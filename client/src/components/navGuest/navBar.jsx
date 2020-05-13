import React from 'react';
import {Link} from 'react-router-dom';

export class Nav extends React.Component {
    constructor(props) {
        super(props);
      }
    
    
    render(){
        return(
            <div className="base">
                <nav className="navigator">
                    <div className="logo">
                        <h1>Ecorp</h1>
                    </div>
                    <div className="space"></div>
                    <div className="buttoms">
                        <div className="cart">
                            <button>cart</button>
                        </div>
                        <div className="reg">
                            <Link to="/register">
                                <button>register</button>
                            </Link>
                        </div>
                        <div className="log">
                            <Link to="/login">
                                <button>login</button>
                            </Link>
                        </div>
                    </div>
                </nav>
            </div>
        )
    }
}

