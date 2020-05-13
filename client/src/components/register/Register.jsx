/* Register.jsx */
import React from "react";
import {reg} from '/Users/Daniel/p-l/src/connection';

export class Register extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
    };
  }

  handleName = (e) => {
    this.setState({
        username: e.target.value
    })
  }
  handlePass = (e) => {
    this.setState({
      password: e.target.value
    })
  }

  goBack = () => {
    this.props.history.goBack();
  }


  goReg (username,password) {
    if(username!= '' && password !=''){
      const callback = (data) =>{
        let registered = false;
        if(data !=undefined && data == 0x02){
          registered=true
        }
        if(registered == false){
          alert('did not register')
        }else{
          this.props.history.goBack();
          alert('login in order to enter as a member')
        }
      }
      reg(username,password,callback)
    }else{
      alert('must enter fields first');
    }
  }

  render() {
    return (
      <div className="base-container" ref={this.props.containerRef}>
        <div className="header">Register</div>
        <div className="content">
          <div className="form">
            <div className="form-group">
              <label htmlFor="username">Username</label>
              <input type="text" name="username" username={this.state.username} onChange={this.handleName.bind(this)} />
            </div>
            <div className="form-group">
              <label htmlFor="password">Password</label>
              <input type="password" name="password" password={this.state.password} onChange={this.handlePass.bind(this)} />
            </div>
          </div>
        </div>
        <div className="footer">
          <div className="B">
            <button type="button" className="back" onClick={this.goBack}>
              back
            </button>
          </div>
          <div className="R">
              <button type="button" className="btn" onClick={()=>this.goReg(this.state.username,this.state.password)}>
                Register
              </button>
          </div>
        </div>
      </div>
    );
  }
}