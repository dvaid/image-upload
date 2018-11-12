import {Component, OnInit} from '@angular/core';
import {AccountService} from "../../services/account.service";
import {PaymentDataModel} from '../../model/model.paymentdata';
import {AppComponent} from "../../app.component";
import {FormBuilder, FormControl, FormGroup, NgForm} from "@angular/forms";


@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    constructor() {
    }

    ngOnInit() {

    }



}
