import React from 'react';
import {createStore} from '/Users/Daniel/p-l/src/connection';

export class addStore extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            store:'',
            address:'',
        };
    }

    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    goSubmit(name,address){
        if(name!=''&&address!=''){
            const callback = (data) =>{
              let created = false;
              if(data !=undefined && data == 0x02){
                created=true
              }
              if(created == false){
                alert('did not created')
              }else{
                this.props.history.goBack();
                alert('store was opened successfully')
              }
            }
            createStore(name,address,callback)
        }else{
            alert('must enter fields first');
        }
    }

    goBack = () => {
        this.props.history.goBack();
      }
    

    render() {
        return (
            <div className="base-container">
                <h1 className="enter-name">enter store name: </h1>
                <div className="input1">
                    <input className="name-input" name="store" type="text" onChange={this.handleChange.bind(this)}/>
                </div>
                <h1 className="enter-address">enter store address: </h1>
                <div className="input2">
                    <input className="address-input" name="address" type="text" onChange={this.handleChange.bind(this)}/>
                </div>
                <div className="buttoms">
                    <div className="back">
                        <button className="b" onClick={this.goBack}>back</button>
                    </div>
                    <div className="create">
                        <button className="c" onClick={()=>this.goSubmit(this.state.store,this.state.address)}>submit</button>
                    </div>
                </div>
            </div>
        );
    }
}