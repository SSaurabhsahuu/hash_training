import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-servers',
  templateUrl: './servers.component.html',
  styleUrls: ['./servers.component.css']
})
export class ServersComponent implements OnInit {
  allowNewServer = false;
  serverCreationStatus = "No server was Created!";
  serverName="";
  serverStatus: string = "offline";
  serverCreated=false;
  servers=['server 1','server 2']

  constructor() {
    setTimeout(()=>{
      this.allowNewServer = true;
    },2000)
    this.serverStatus = Math.random() > 0.5  ? "online" : "offline";
   }

   getColor()
   {
     return this.serverStatus === "online" ? "green" : "red";
   }
  ngOnInit(): void {
  }
  onCreateServer(){
    this.serverCreated=true;
    this.serverCreationStatus = "Server was Created!";
    this.servers.push(this.serverName);
    this.serverStatus = Math.random() > 0.5  ? "online" : "offline";
  }
  onUpdateServerName(event)
  {
    this.serverName=event.target.value;
  }


}
