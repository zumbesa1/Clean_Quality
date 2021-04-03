import {Component, Injector, OnInit} from "@angular/core";
import {CustomPageElement} from "path-framework/app/path-framework/page/element/custom/custom-container.component";
import {PathService} from "path-framework/app/path-framework/service/path.service";

@Component({
    templateUrl: "order-summary-component.html"
})
export class OrderSummaryComponent extends CustomPageElement implements OnInit {

    private summaryItems = [];
    private questionnaireResponseKey: string;
    private pathService: PathService;

    constructor(private injector: Injector) {
        super();
        this.pathService = injector.get(PathService);
    }

    public displayGraph = false;
    public graph = {
        data: [{
            type: "scatterpolar",
            r: [15, 60, 80, 7, 15],
            theta: ["Somatiker", "Psychosomatiker", "Süchtiger", "Unauffälliger", "Somatiker"],
            fill: "toself",
            connectgaps: true
        }],
        layout: {
            polar: {
                radialaxis: {
                    visible: true,
                    range: [0, 100],
                    showticklabels: false,
                    ticks: "",
                    showline: false
                }
            },
            width: 600,
            height: 600,
            showlegend: false
        }
    };

    public getText(): string {
        if (this.pageElement != null && this.pageElement.parentPageElement != null) {
            return this.pageElement.parentPageElement.name;
        }
        return null;
    }

    ngOnInit() {
        console.log(this.pageElement.parentPageElement.key);
        if (this.pageElement.parentPageElement.key.getName() === "questionnaireResponseKey") {
            this.questionnaireResponseKey = this.pageElement.parentPageElement.key.getKey();
        } else {
            const interactionStepKey: string = this.pageElement.parentPageElement.key.getKey();
            const parts: string[] = interactionStepKey.split("-----");
            this.questionnaireResponseKey = parts[3];
        }
        const url = "/questionnaireResponse/" + this.questionnaireResponseKey + "/summary";
        this.pathService.serverGet(this.pageElement.app.getBackendUrl(), url, (data) => {
            if (data != null) {
                this.summaryItems = data;
            }
            for (const item of this.summaryItems) {
                if (item.key === "BMI") {
                    this.displayGraph = true;
                }
            }
        }, (err) => {
            console.log(err);
        });
    }

    public download(key: string): void {
        window.location.assign(this.pageElement.app.getBackendUrl() + "/upload/" + key);
    }
}
