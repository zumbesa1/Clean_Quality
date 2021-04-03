import {NgModule} from "@angular/core";
import {DevOpsAppComponent} from "./devops-app.component";
import {AppModule} from "path-framework/app/app.module";
import {OrderSummaryComponent} from "./custom-components/order-summary/order-summary-component";
import * as PlotlyJS from "plotly.js/dist/plotly.js";
import { PlotlyModule } from "angular-plotly.js";

PlotlyModule.plotlyjs = PlotlyJS;

@NgModule({
    imports:      [AppModule, PlotlyModule],
    declarations: [DevOpsAppComponent, OrderSummaryComponent],
    bootstrap:    [DevOpsAppComponent],
    entryComponents: [OrderSummaryComponent]
})
export class DevOpsAppModule {}
