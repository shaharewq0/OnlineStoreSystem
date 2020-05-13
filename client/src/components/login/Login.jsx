import React from "react";
import {log} from '/Users/Daniel/p-l/src/connection'

export class Log_in extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
    };
    this.goLog = this.goLog.bind(this)
  }

  
  handleChange = (e) => {
    this.setState({
        [e.target.name]: e.target.value
    })
  }

  goBack = () => {
    this.props.history.goBack();
  }

  goLog = (username,password) => {
    if(username!=''&&password!=''){
      const callback = (data) =>{
        let logedin = false;
        if(data !=undefined && data == 0x02){
          logedin=true
        }
        if(logedin == false){
          alert('did not loged')
        }else{
          this.props.history.push('/member');
        }
      }
      log(username,password,callback)
    }else{
      alert('must enter fields first');
    }
  }

  render() {
    return (
      <div className="base-container" ref={this.props.containerRef}>
        <div className="header">Login</div>
        <div className="content">
          <div className="form">
            <div className="form-group">
              <label htmlFor="username">Username</label>
              <input type="text" name="username" value={this.state.username} onChange={e => this.handleChange(e)} />
            </div>
            <div className="form-group">
              <label htmlFor="password">Password</label>
              <input type="password" name="password" value={this.state.password} onChange={e => this.handleChange(e)} />
            </div>
          </div>
        </div>
        <div className="footer">
          <div className="B">
            <button type="button" className="back" onClick={this.goBack}>
              back
            </button>
          </div>
          <div className="L">
              <button type="button" className="btn" onClick={()=>this.goLog(this.state.username,this.state.password)}>
                Login
              </button>
          </div>
        </div>
      </div>
    );
  }
}


