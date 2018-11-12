import {Component, OnInit} from '@angular/core';
import {PaymentDataModel} from "../../model/model.paymentdata";
import {AppComponent} from "../../app.component";
import {AccountService} from "../../services/account.service";

@Component({
    selector: 'app-payment-failure',
    templateUrl: './payment-failure.component.html',
    styleUrls: ['./payment-failure.component.css']
})
export class PaymentFailureComponent implements OnInit {
    paymentData: PaymentDataModel = new PaymentDataModel();
    paymentUrl: string = AppComponent.PAYMENT_URL;

    constructor(public accountService: AccountService) {
    }

    ngOnInit() {
        this.accountService.getPaymentData().subscribe((paymentData: PaymentDataModel) => {
            this.paymentData = paymentData;
            console.log(this.paymentData);
        });
    }

}
