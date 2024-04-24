import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Game } from './game';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private baseUrl = 'http://localhost:8080/api/game-library';

  constructor(private httpClient: HttpClient,
    private router: Router
  ) { }

  goToLibrary() {
    this.router.navigate(['/game-library']);
  }

  getGames(): Observable<Game[]> {
    return this.httpClient.get<Game[]>(`${this.baseUrl}`);
  }

  addGame(game: Game): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}`, game);
  }
  
  getGameById(id: number): Observable<Game> {
    return this.httpClient.get<Game>(`${this.baseUrl}/${id}`);
  }

  updateGame(id: number, game: Game): Observable<Object> {
    return this.httpClient.put(`${this.baseUrl}/${id}`, game);
  }

  deleteGame(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }

}
