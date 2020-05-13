import React from "react";
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import {HomePageGuest} from './components/homeGuest';
import {HomePageMember} from './components/homeMember';
import {Log_in} from './components/login/Index';
import {Register} from './components/register/Index';
import {storeSearch} from './components/searchStore/Index';
import {addStore} from './components/addStore/Index';

export default class App extends React.Component{

  render(){
    return (
      <BrowserRouter>
      <div>
        <Switch>
          <Route path="/" component={HomePageGuest} exact/>
          <Route path="/register" component={Register}/>
          <Route path="/login" component={Log_in}/>
          <Route path="/member" component={HomePageMember}/>
          <Route path="/store" component={storeSearch}/>
          <Route path="/create" component={addStore}/>
        </Switch>
      </div> 
    </BrowserRouter>
    );
  }
}
