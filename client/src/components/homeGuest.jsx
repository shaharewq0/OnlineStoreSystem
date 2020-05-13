import React from "react";
import {Nav} from './navGuest/Index';
import {GuestFun} from './Guest/Index';


export class HomePageGuest extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="base-container">
        <Nav/>
        <main style={{marginTop: '70px'}}>
          <GuestFun/>
        </main>
      </div>
    );
  }
}