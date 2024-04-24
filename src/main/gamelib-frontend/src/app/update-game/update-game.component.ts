import { Component } from '@angular/core';
import { Game } from '../game';
import { GameService } from '../game.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-game',
  templateUrl: './update-game.component.html',
  styleUrl: './update-game.component.css'
})

export class UpdateGameComponent {
  game!: Game;
  id!: number;

  constructor(private gameService: GameService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  

  ngOnInit(): void {
    this.game = new Game();
    this.id = this.route.snapshot.params['id'];
    this.gameService.getGameById(this.id).subscribe(data => {
      console.log(data);
      this.game = data;
    },
    error => {
      console.error('Failed to find game with this ID:', error);
    });
  }

  onSubmit() {
    console.log(this.game);
    this.gameService.updateGame(this.id, this.game).subscribe(data => {
      console.log(data);
      this.gameService.goToLibrary();
    },
    error => {
      console.error('Failed to update game:', error);
    });
  }

}
