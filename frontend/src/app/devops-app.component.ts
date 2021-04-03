/* angular/path imports */
import {Component, Type} from "@angular/core";
import {PathAppComponent} from "path-framework/app/path-framework/path-app.component";
import {PathService} from "path-framework/app/path-framework/service/path.service";
import {TranslationService} from "path-framework/app/path-framework/service/translation.service";

/* model imports */
import {GuiModel} from "./gui-model/guimodel";
import {DevOpsTranslationService} from "./devops-translation-service";
import {OrderSummaryComponent} from "./custom-components/order-summary/order-summary-component";
import {CustomPageElement} from "path-framework/app/path-framework/page/element/custom/custom-container.component";

@Component({
    selector: "path-application",
    templateUrl: "./../../node_modules/path-framework/app/path-framework/path-app.component.html",
    // providers: [{ provide: path.PathService, useClass: path.PathMockService }]
    providers: [PathService, {provide: TranslationService, useClass: DevOpsTranslationService}]
})
export class DevOpsAppComponent extends PathAppComponent {

    private _appConfig = new GuiModel();

    constructor(pathService: PathService, translationService: TranslationService) {
        super(pathService, translationService);
    }

    protected getFrontendVersion(): string {
        return "0.0.2-SNAPSHOT";
    }

    protected getStartPage(): string {
        return "mainMenu";
    }

    protected getApplicationLogo(): string {
        return null;
    }

    protected getOwnUserForm(): string {
        return "OwnUserForm";
    }

    protected getGuiModel() {
        if (this._appConfig != null) {
            return this._appConfig.getGuiModel();
        }
        return null;
    }

    public getBackendUrl() {
        if (window.location.hostname.indexOf("localhost") !== -1 && window.location.port === "4200") {
            return "http://localhost:4567/services";
        } else if (window.location.hostname.indexOf("gitpod.io") !== -1) {
            let gitpodUrl = window.location.href;
            gitpodUrl = gitpodUrl.replace("https://4200", "https://4567");
            return gitpodUrl + "services";
        }
        let url: string = window.location.href;
        url = url.replace("/#", "");
        if (url.endsWith("/")) {
            return url + "services";
        }
        return url + "/services";
    }

    protected getCustomComponentClass(componentType: string): Type<CustomPageElement> {
        if (componentType === "OrderSummaryComponent") {
            return OrderSummaryComponent;
        }
        return super.getCustomComponentClass(componentType);
    }

    protected getBeans() {
        return {};
    }

    protected getHandlers() {
        return {};
    }
}
