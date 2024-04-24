import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GameLibraryComponent } from './game-library/game-library.component';
import { HttpClientModule } from '@angular/common/http';
import { AddGameComponent } from './add-game/add-game.component';
import { FormsModule } from '@angular/forms';
import { UpdateGameComponent } from './update-game/update-game.component';
import { DeleteGameComponent } from './delete-game/delete-game.component';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    GameLibraryComponent,
    AddGameComponent,
    UpdateGameComponent,
    DeleteGameComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
