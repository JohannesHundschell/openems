import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Edge, EdgeConfig, Service, Websocket } from '../../../shared/shared';
import { SetBatteryCellUnderVoltageProtectionRequest } from 'src/app/shared/jsonrpc/request/setBatteryCellUnderVoltageProtectionRequest';

@Component({
  selector: CommandsComponent.SELECTOR,
  templateUrl: './commands.component.html'
})
export class CommandsComponent {

  private static readonly SELECTOR = "commands";

  public edge: Edge = null;
  public config: EdgeConfig = null;
  public bmsId: string;
  public cuvp: string;
  public cuvpr: string;

  constructor(
    private service: Service,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.service.setCurrentComponent("Commands" /* TODO translate */, this.route).then(edge => {
      this.edge = edge;
    });
    this.service.getConfig().then(config => {
      this.config = config;
    })
  }

  setBatteryCellUnderVoltageProtection() {
    if (this.edge) {
      this.edge.sendRequest(
        this.service.websocket,
        new SetBatteryCellUnderVoltageProtectionRequest({
          bmsId: this.bmsId,
          cellUnderVoltageProtection: this.cuvp,
          cellUnderVoltageProtectionRecover: this.cuvpr
        })
      ).then(response => {
        this.service.toast("Erfolgreich rausgeballert", "success");
      }).catch(reason => {
        this.service.toast("Leider nein, leider garnicht.. :(", 'danger');
      });
    }
  }

  ngOnDestroy() { }
}