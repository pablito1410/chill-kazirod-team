import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AbstractItem} from '../data/abstract-item';
import {environment} from '../../environments/environment';
import {Http, RequestOptions} from '@angular/http';

export abstract class AbstractService<S extends AbstractItem> {

    protected url = '';

    constructor(protected http: HttpClient) {
    }

    get() {
        const destUrl = environment.envUrl + this.url;
        return this.http.get(destUrl);
    }

    post(item: S) {
        const destUrl = environment.envUrl + this.url;
        const body = JSON.stringify(item);
        const headers = {
            "Content-Type": "application/json",
            "Accept": "application/json"
        };
        //headers.set("Content-Type", "application/json;charset=UTF-8");
        return this.http.post(destUrl, body, {headers: headers});
    }

    put(id: number, item: S) {
        const destUrl = environment.envUrl + this.url + '/' + +id;
        const body = JSON.stringify(item);
        return this.http.put(destUrl, body);
    }

    delete(id: number, item: S) {
        const destUrl = environment.envUrl + this.url + '/' + +id;
        return this.http.delete(destUrl);
    }

}
