const url = 'wss://workshopv2.ddnsking.com/mall'
const socket = new WebSocket(url)


var socketQueueId = 0;
var socketQueue = {};

function sendData(data,onReturn){
    socketQueueId++;
    socketQueue['i_'+socketQueueId] = onReturn;
    jsonData = JSON.stringify({'cmd_id':socketQueueId, 'json_data':data});
    try{
        socket.send(jsonData);
    }catch(e){
        console.log(e);
    }
    return socketQueueId;
}

socket.onmessage = function(e) {

    try{
        data = JSON.parse(e.data);
    }catch(er){
        console.log('socket parse error: '+e.data);
    }

    if (typeof(data['cmd_id']) != 'undefined'){
        execFunc = socketQueue['i_'+data['cmd_id']];
        execFunc(data['result']);
        delete socketQueue['i_'+data['cmd_id']];
    }
}

function RL(opCode,username,pass,onReturn){
    var enc = new TextEncoder();
    const un = enc.encode(username)
    const p = enc.encode(pass)
    var bufView = new Uint8Array(un.length+p.length+2);
    i=1;
    bufView[0]=opCode;
    for(j=0;j<un.length;j++){
        bufView[i]=un[j];
        i++;
    }
    bufView[i]=0x00;
    i++;
    for(j=0;j<p.length;j++){
        bufView[i]=p[j];
        i++;
    }
    return sendData(bufView,onReturn)
}

function reg(username,pass,onReturn){
    return  RL(0x22,username,pass,onReturn)
}

function log(username,pass,onReturn){
    return  RL(0x23,username,pass,onReturn)
}

function logout(onReturn){
    var bufView = new Uint8Array(1);
    bufView[0]=0x31;
    return sendData(bufView,onReturn)
}

function store(name,onReturn){
    var enc = new TextEncoder();
    const sn = enc.encode(name)
    var bufView = new Uint8Array(sn.length+1);
    i=1;
    bufView[0]=0x14;
    for(j=0;j<sn.length;j++){
        bufView[i]=sn[j];
        i++;
    }
    return sendData(bufView,onReturn)
}

function createStore(name,address,onReturn){
    var enc = new TextEncoder();
    const un = enc.encode(name)
    const add = enc.encode(address)
    var bufView = new Uint8Array(un.length+add.length+2);
    i=1;
    bufView[0]=0x32;
    for(j=0;j<un.length;j++){
        bufView[i]=un[j];
        i++;
    }
    bufView[i]=0x00;
    i++;
    for(j=0;j<add.length;j++){
        bufView[i]=add[j];
        i++;
    }
    return sendData(bufView,onReturn)
}

module.exports = {
    logout:logout,
    reg: reg,
    log:log,
    store:store,
    createStore:createStore
}