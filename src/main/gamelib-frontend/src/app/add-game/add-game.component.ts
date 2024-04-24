import { Component, OnInit } from '@angular/core';
import { Game } from '../game';
import { GameService } from '../game.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-game',
  templateUrl: './add-game.component.html',
  styleUrl: './add-game.component.css'
})
export class AddGameComponent implements OnInit {
  
    game!: Game;

    constructor(private gameService: GameService) { }
  
    saveGame() {
      this.gameService.addGame(this.game).subscribe(data => {
        console.log(data);
        this.gameService.goToLibrary();
      },
      error => {
        console.error('Failed to save game:', error);
      });
    }

  
    ngOnInit(): void {
      this.game = new Game();
    }

    onSubmit() {
      console.log(this.game);
      this.saveGame();
    }

}
