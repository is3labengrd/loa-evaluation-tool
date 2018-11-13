export function initializeMatrix(matrixRequestReference) {
  var valueSymbol = Symbol();

  var requestModifierFactory = (prefix, values) => {
    return (number) => {
      for (let i in values) {
        if (i == number) {
          matrixRequestReference[prefix + values[i]] = 1;
        } else {
          matrixRequestReference[prefix + values[i]] = 0;
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
      criteria1: 'Insensitive',
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
}

function propertyToIndexValuePair(property) {
  switch (property) {
    case "dsStable": return [0, 0];
    case "dsReducedStability": return [0, 1];
    case "dsHandlyStable": return [0, 2];
    case "dsUnstable": return [0, 3];
    case "stInsensitive": return [1, 0];
    case "stHardlySensitive": return [1, 1];
    case "stSensitive": return [1, 2];
    case "stVerySensitive": return [1, 3];
    case "grExGripSurface": return [2, 0];
    case "grIntGripSurface": return [2, 1];
    case "grMagneticGripper": return [2, 2];
    case "grFabClosureGripper": return [2, 3];
    case "nvNoFurtherVariants": return [3, 0];
    case "nvOneFurtherVariants": return [3, 1];
    case "nvTwoFurtherVariants": return [3, 2];
    case "nvMoreFurtherVariants": return [3, 3];
    case "spUp": return [4, 0];
    case "spMore": return [4, 1];
    case "spStableUnstable": return [4, 2];
    case "spOnlyUnstable": return [4, 3];
    case "smRotSymmetrical": return [5, 0];
    case "smArSymmetry": return [5, 1];
    case "smMaAsymmetrical": return [5, 2];
    case "smSeSymmetrical": return [5, 3];
    case "haNone": return [6, 0];
    case "haStiJamPossible": return [6, 1];
    case "haComPenPossibile": return [6, 2];
    case "haSeeSymmetrical": return [6, 3];
    case "fjNever": return [7, 0];
    case "fjOcasionally": return [7, 1];
    case "fjRarely": return [7, 2];
    case "fjOften": return [7, 3];
    case "acVeryGood": return [8, 0];
    case "acGood": return [8, 1];
    case "acSatisfactory": return [8, 2];
    case "acSufficient": return [8, 3];
    case "orNoAxes": return [9, 0];
    case "orOneAxis": return [9, 1];
    case "orTwoAxes": return [9, 2];
    case "orThreeAxes": return [9, 3];
    case "jmLinear": return [10, 0];
    case "jmRotation": return [10, 1];
    case "jmLinearRotatory": return [10, 2];
    case "jmPathMovement": return [10, 3];
    case "jfNone": return [11, 0];
    case "jfLow": return [11, 1];
    case "jfMedium": return [11, 2];
    case "jfHigh": return [11, 3];
    case "jaJoinBasicComp": return [12, 0];
    case "jaJoinComp": return [12, 1];
    case "jaBasicComp": return [12, 2];
    case "jaMoreComp": return [12, 3];
    case "ncOneBasicComp": return [13, 0];
    case "ncTwoBasicComp": return [13, 1];
    case "ncThreeBasicComp": return [13, 2];
    case "ncMoreComp": return [13, 3];
    case "jsSecAllDirection": return [14, 0];
    case "jsGravityFit": return [14, 1];
    case "jsGravityRubbing": return [14, 2];
    case "jsAdditionalSec": return [14, 3];
    case "soNone": return [15, 0];
    case "soOne": return [15, 1];
    case "soTwo": return [15, 2];
    case "soMore": return [15, 3];
  }
}

export function updateMatrix(matrix, matrixEntity: any) {
  for (var property in matrixEntity) {
    if (matrixEntity[property] == 1) {
      let indexValuePair = propertyToIndexValuePair(property);
      if (indexValuePair) {
        matrix[indexValuePair[0]].value = indexValuePair[1];
      }
    }
  }
}