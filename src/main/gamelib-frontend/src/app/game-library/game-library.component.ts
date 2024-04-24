import { Component, OnInit } from '@angular/core';
import { Game } from '../game';
import { GameService } from '../game.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game-library',
  templateUrl: './game-library.component.html',
  styleUrl: './game-library.component.css'
})

export class GameLibraryComponent implements OnInit {



  games!: Game[];

  constructor(private gameService: GameService, private router: Router) { }

  ngOnInit(): void {
    this.getLibrary();
  }

  ngOnRefresh(): void {
    this.getLibrary();

  }

  private getLibrary() {
    this.gameService.getGames().subscribe(data => { 
      this.games = data; 
    }, error => {
      console.error('Failed to get library:', error);
    });
  }

  updateGame(id: number) {
    this.router.navigate(['update-game', id]);
  }

  deleteGame(id: number) {
    this.router.navigate(['delete-game', id]);
  // this.gameService.deleteGame(id).subscribe(data => {
  //   console.log(data);
  //   this.getLibrary();
  // })
  }
  

}
