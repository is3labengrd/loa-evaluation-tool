import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { ChartsModule } from 'ng2-charts/ng2-charts';

import { AppComponent } from './app.component';
import { ProcessListComponent } from './process-list/process-list.component';
import { AddProcessComponent } from './add-process/add-process.component';
import { EditProcessComponent } from './edit-process/edit-process.component';
import { HTAAnalysisComponent } from './hta-analysis/hta-analysis.component';
import { AsIsLOAComponent } from './as-is-loa/as-is-loa.component';
import { ResourceListComponent } from './resource-list/resource-list.component';
import { AddResourceComponent } from './add-resource/add-resource.component';
import { EditResourceComponent } from './edit-resource/edit-resource.component';
import { AddScenarioComponent } from './add-scenario/add-scenario.component';
import { EditScenarioComponent } from './edit-scenario/edit-scenario.component';
import { MainAnalysisComponent } from './main-analysis/main-analysis.component';
import { SubScenariosComponent } from './sub-scenarios/sub-scenarios.component';
import { ScenariosComponent } from './scenarios/scenarios.component';

@NgModule({
  declarations: [
    AppComponent,
    ProcessListComponent,
    AddProcessComponent,
    EditProcessComponent,
    HTAAnalysisComponent,
    AsIsLOAComponent,
    ResourceListComponent,
    AddResourceComponent,
    EditResourceComponent,
    AddScenarioComponent,
    EditScenarioComponent,
    MainAnalysisComponent,
    SubScenariosComponent,
    ScenariosComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
