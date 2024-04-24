import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GameLibraryComponent } from './game-library/game-library.component';
import { AddGameComponent } from './add-game/add-game.component';
import { UpdateGameComponent } from './update-game/update-game.component';
import { DeleteGameComponent } from './delete-game/delete-game.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: 'game-library', component: GameLibraryComponent },
  { path: 'add-game', component: AddGameComponent },
  { path: 'update-game/:id', component: UpdateGameComponent },
  { path: 'delete-game/:id', component: DeleteGameComponent },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: 'game-library', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
