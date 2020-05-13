import React from 'react';
import {logout} from '/Users/Daniel/p-l/src/connection';
import {Link} from 'react-router-dom';

export class Nav extends React.Component {
    constructor(props) {
        super(props);
        this.goBack = this.goBack.bind(this);
      }
    
    goBack = () => {
        const callback = (data) =>{
            let logedout = false;
            if(data !=undefined && data == 0x02){
                logedout=true
            }
          }
        logout(callback)
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
                        <div className="log">
                            <Link to="/">
                                <button onClick={this.goBack}>logout</button>
                            </Link>
                        </div>
                    </div>
                </nav>
            </div>
        )
    }
}

