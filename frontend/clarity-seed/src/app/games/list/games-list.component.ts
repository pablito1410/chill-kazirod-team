import {Component, OnInit} from '@angular/core';
import {GameService} from '../../services/game.service';
import {Game} from '../../data/game';

@Component({
    templateUrl: './games-list.component.html',
    styleUrls: ['./games-list.component.css']
})
export class GamesListComponent implements OnInit {
    games: Game[] = [];

    constructor(protected gameService: GameService) {
    }

    ngOnInit() {
        this.gameService.get()
            .subscribe((val: Game[]) => {
                val.map(game => this.games.push(game), this);
            })
    }

}
