import React from "react";
import {store} from '/Users/Daniel/p-l/src/connection';

export class storeSearch extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            store:'',
            list:[]
        };
    }

    handleName = (e) => {
        this.setState({
            store: e.target.value
        })
      }

    goBack = () => {
        this.props.history.goBack();
    }

    getStore(name){
        if(name!='' && name!=undefined){
            const callback = (data)=>{
                console.log(data)
                if(data !=undefined && data == 0x03){
                    alert('no such store')
                    this.setState({
                        list: []
                    })
                }else{
                    let count =0;
                    for(var i in data)
                        count++;
                    var result = new Uint8Array(count);
                    for(var i in data)
                        result[i]=data[''+i];
                    console.log(result);
                    var dec = new TextDecoder();
                    let sep1 = 0;
                    for(let i=0;i<result.length;i++){
                        if(result[i]==0x00){
                            sep1 = i;
                            break;
                        }
                    }
                    const byteName = result.slice(0,sep1)
                    const name = dec.decode(byteName)
                    console.log(name);
                    let sep2 = 0
                    for(let i=sep1+1;i<result.length;i++){
                        if(result[i]==0x00){
                            sep2 = i;
                            break;
                        }
                    }
                    const byteAddress = result.slice(sep1+1,sep2)
                    const address = dec.decode(byteAddress)
                    const rating = result[sep2+1]
                    let list = [{name:name, address:address , rating:rating}];
                    this.setState({
                        list: list
                    })
                }
            }
            store(name,callback);
        }else{
            alert('must eneter field first');
        }
    }

    render() {
        return (
            <div className="base-container">
                <div className="search-bar">
                    <h1 className="enter-search">enter store name: </h1>
                    <div className="space"></div>
                    <div className="input">
                        <input className="search-input" name="store" type="text" onChange={this.handleName.bind(this)}/>
                    </div>
                    <button className="enter" onClick={()=>this.getStore(this.state.store)}>enter</button>
                </div>
                <section className="section" style={{marginTop: '70px'}}>
                    {this.state.list.map(item => (
                        <div key="1">store name : {item.name}</div>
                    ))}
                    {this.state.list.map(item => (
                        <div key="2">address : {item.address}</div>
                    ))}
                    {this.state.list.map(item => (
                        <div key="3">rating : {item.rating}</div>
                    ))}
                    {this.state.list.map(item => (
                        <button key="4">watch products</button>
                    ))}
                 </section>
                 <div className="B" style={{marginTop: '20px'}}>
                    <button type="button" className="back" onClick={this.goBack}>
                    back
                    </button>
                </div>
            </div>
        );
    }
}