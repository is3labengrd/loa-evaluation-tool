import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ProcessListComponent} from './process-list/process-list.component';
import { AddProcessComponent } from './add-process/add-process.component';
import { EditProcessComponent } from './edit-process/edit-process.component';
import { AsIsLOAComponent } from './as-is-loa/as-is-loa.component';
import { ResourceListComponent } from './resource-list/resource-list.component';
import { MainAnalysisComponent } from './main-analysis/main-analysis.component';
import { HTAAnalysisComponent } from './hta-analysis/hta-analysis.component';
import { AddResourceComponent } from './add-resource/add-resource.component';
import { EditResourceComponent } from './edit-resource/edit-resource.component';
import { SubScenariosComponent } from './sub-scenarios/sub-scenarios.component';
import { ScenariosComponent } from './scenarios/scenarios.component';
import { AddScenarioComponent } from './add-scenario/add-scenario.component';
import { EditScenarioComponent } from './edit-scenario/edit-scenario.component';


const ROUTES: Routes = [
  { path: '', redirectTo: '/process-list', pathMatch: 'full' },
  { path: 'process-list', component: ProcessListComponent },
  { path: 'add-process', component: AddProcessComponent },
  { path: 'edit-process', component: EditProcessComponent },
  { path: 'as-is-loa', component: AsIsLOAComponent },
  { path: 'resource-list', component: ResourceListComponent },
  { path: 'main-analysis', component: MainAnalysisComponent},
  { path: 'hta-analysis', component: HTAAnalysisComponent },
  { path: 'add-resource', component: AddResourceComponent },
  { path: 'edit-resource', component: EditResourceComponent },
  { path: 'sub-scenarios', component: SubScenariosComponent },
  { path: 'scenarios', component: ScenariosComponent },
  { path: 'add-scenario', component: AddScenarioComponent },
  { path: 'edit-scenario', component: EditScenarioComponent }

];

@NgModule({
  imports: [
    RouterModule.forRoot(ROUTES)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
