import { Subscription } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-hta-analysis',
  templateUrl: './hta-analysis.component.html',
  styleUrls: ['./hta-analysis.component.css']
})
export class HTAAnalysisComponent implements OnInit {

  rows = (() => {
    var self = this;
    var valueSymbol = Symbol();

    var requestModifierFactory = (prefix, values) => {
      return (number) => {
        for (let i=0; i<4; i++) {
          if (i == number) {
            self.request[prefix + values[i]] = 1;
          } else {
            self.request[prefix + values[i]] = 0;
          }
        }
      }
    }
    return [
      {
        strong: 'Dimensional stabilty',
        criteria1: 'Stable',
        criteria2: 'Reduced stability',
        criteria3: 'Hardly stable',
        criteria4: 'Unstable',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "ds", [
              "Stable",
              "ReducedStability",
              "HandlyStable",
              "Unstable"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'Sensitivity',
        criteria1: 'Insesitive',
        criteria2: 'Handly sensitive',
        criteria3: 'Sensitive',
        criteria4: 'Very sensitive',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "", [
              "stInsensitive",
              "stHardlySensitive",
              "stSensitive",
              "stVerySensitive"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'Grip',
        criteria1: 'External grip surfaces',
        criteria2: 'Internal grip surfaces',
        criteria3: 'Magnetic grip surfaces',
        criteria4: 'Fabric closure gripper',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "", [
              "grExGripSurface",
              "grIntGripSurface",
              "grMagneticGripper",
              "grFabClosureGripper"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'No. of variants',
        criteria1: 'No futher variants',
        criteria2: 'One further variant',
        criteria3: 'Two further variants',
        criteria4: 'More than two variants',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "", [
              "nvNoFurtherVariants",
              "nvOneFurtherVariants",
              "nvTwoFurtherVariants",
              "nvMoreFurtherVariants"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'Stable positions',
        criteria1: 'Up until four',
        criteria2: 'More than four',
        criteria3: 'Stable and unstable',
        criteria4: 'Only unstable',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "", [
              "spUp",
              "spMore",
              "spStableUnstable",
              "spOnlyUnstable"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'Symmetry',
        criteria1: 'Rotationally symmetrical',
        criteria2: 'Areal symmetry',
        criteria3: 'Markedly asymmetrical',
        criteria4: 'Seemingly symmetrical',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "", [
              "smRotSymmetrical",
              "smArSymmetry",
              "smMaAsymmetrical",
              "smSeSymmetrical"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'Hook up or adhesion',
        criteria1: 'None',
        criteria2: 'Sticking or jamming possible',
        criteria3: 'Component penetration possible',
        criteria4: 'Seemingly symmetry',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "", [
              "haNone",
              "haStiJamPossible",
              "haComPenPossibile",
              "haSeeSymmetrical"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'Faulty joining',
        criteria1: 'Never',
        criteria2: 'Occasionaly',
        criteria3: 'Rarely',
        criteria4: 'Often',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "", [
              "fjNever",
              "fjOcasionally",
              "fjRarely",
              "fjOften"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'Accessibility',
        criteria1: 'Very good',
        criteria2: 'Good',
        criteria3: 'Satisfactory',
        criteria4: 'Sufficient',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "", [
              "acVeryGood",
              "acGood",
              "acSatisfactory",
              "acSufficient"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'Orienation',
        criteria1: 'No axis',
        criteria2: 'One axis',
        criteria3: 'Two axis',
        criteria4: 'Three axis',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "", [
              "orNoAxes",
              "orOneAxis",
              "orTwoAxes",
              "orThreeAxes"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'Joining mov.',
        criteria1: 'Linear',
        criteria2: 'Rotation',
        criteria3: 'Linear-rotatory',
        criteria4: 'Path movement',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "", [
              "jmLinear",
              "jmRotation",
              "jmLinearRotatory",
              "jmPathMovement"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'Joining force',
        criteria1: 'None',
        criteria2: 'Low',
        criteria3: 'Medium',
        criteria4: 'High',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "jf", [
              "None",
              "Low",
              "Medium",
              "High"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'Joining aid',
        criteria1: 'Joining and basic component',
        criteria2: 'Joining component',
        criteria3: 'Basic component',
        criteria4: 'None',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "ja", [
              "JoinBasicComp",
              "JoinComp",
              "BasicComp",
              "MoreComp"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'Number of basic components',
        criteria1: 'One basic component',
        criteria2: 'Two basic components',
        criteria3: 'Three basic components',
        criteria4: 'More than three basic components',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "nc", [
              "OneBasicComp",
              "TwoBasicComp",
              "ThreeBasicComp",
              "MoreComp"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'Joining security',
        criteria1: 'Secured in all directions',
        criteria2: 'Gravity and form fit',
        criteria3: 'Gravity and rubbing',
        criteria4: 'Additional securing necessary',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "js", [
              "SecAllDirection",
              "GravityFit",
              "GravityRubbing",
              "AdditionalSec"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      },
      {
        strong: 'Special operations',
        criteria1: 'None',
        criteria2: 'One',
        criteria3: 'Two',
        criteria4: 'More than two',
        set value(number) {
          this[valueSymbol] = number;
          void requestModifierFactory(
            "so", [
              "None",
              "One",
              "Two",
              "More"
            ]
          )(number);
        },
        get value() {
          return this[valueSymbol];
        }
      }
    ];
  })();

  request = {
    "dsStable": 0,
    "dsReducedStability": 0,
    "dsHandlyStable": 0,
    "dsUnstable": 0,
    "stInsensitive": 0,
    "stHardlySensitive": 0,
    "stSensitive": 0,
    "stVerySensitive": 0,
    "grExGripSurface": 0,
    "grIntGripSurface": 0,
    "grMagneticGripper": 0,
    "grFabClosureGripper": 0,
    "nvNoFurtherVariants": 0,
    "nvOneFurtherVariants": 0,
    "nvTwoFurtherVariants": 0,
    "nvMoreFurtherVariants": 0,
    "spUp": 0,
    "spMore": 0,
    "spStableUnstable": 0,
    "spOnlyUnstable": 0,
    "smRotSymmetrical": 0,
    "smArSymmetry": 0,
    "smMaAsymmetrical": 0,
    "smSeSymmetrical": 0,
    "haNone": 0,
    "haStiJamPossible": 0,
    "haComPenPossibile": 0,
    "haSeeSymmetrical": 0,
    "fjNever": 0,
    "fjOcasionally": 0,
    "fjRarely": 0,
    "fjOften": 0,
    "acVeryGood": 0,
    "acGood": 0,
    "acSatisfactory": 0,
    "acSufficient": 0,
    "orNoAxes": 0,
    "orOneAxis": 0,
    "orTwoAxes": 0,
    "orThreeAxes": 0,
    "jmLinear": 0,
    "jmRotation": 0,
    "jmLinearRotatory": 0,
    "jmPathMovement": 0,
    "jfNone": 0,
    "jfLow": 0,
    "jfMedium": 0,
    "jfHigh": 0,
    "jaJoinBasicComp": 0,
    "jaJoinComp": 0,
    "jaBasicComp": 0,
    "jaMoreComp": 0,
    "ncOneBasicComp": 0,
    "ncTwoBasicComp": 0,
    "ncThreeBasicComp": 0,
    "ncMoreComp": 0,
    "jsSecAllDirection": 0,
    "jsGravityFit": 0,
    "jsGravityRubbing": 0,
    "jsAdditionalSec": 0,
    "soNone": 0,
    "soOne": 0,
    "soTwo": 0,
    "soMore": 0,
    "ecAEleConsumFun": 0
  }

  matrixId;

  constructor(private http:HttpClient) { }

  ngOnInit() {
    var subscription:Subscription = this.http
      .post(environment.apiUrl + "/v1/criteria-matrices", this.request)
      .subscribe(
        (matrix:any) => {
          this.matrixId = matrix.pkTbId;
          subscription.unsubscribe();
        }
      );
  }

  updateMatrix() {
    var subscription:Subscription = this.http
      .put(
        `${environment.apiUrl}/v1/criteria-matrices/${this.matrixId}`,
        this.request
      )
      .subscribe(
        (matrix) => {
          subscription.unsubscribe();
        }
      );
  }

}
