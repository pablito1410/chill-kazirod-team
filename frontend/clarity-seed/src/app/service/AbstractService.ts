import {HttpClient} from '@angular/common/http';
import {AbstractItem} from '../data/AbstractItem';
import {environment} from '../../environments/environment';

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
        return this.http.post(destUrl, body);
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
