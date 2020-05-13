import React from "react";
import {Nav} from './navMember/Index';
import {GuestFun} from './Guest/Index';
import {MemberFun} from './Member/Index';


export class HomePageMember extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="base-container">
        <Nav/>
        <main style={{marginTop: '70px'}}>
          <GuestFun/>
          <MemberFun/>
        </main>
      </div>
    );
  }
}