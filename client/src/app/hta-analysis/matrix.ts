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
      description: 'The rules for assembly-compliant component design indicate that non-stable components are to be avoided because they have poor properties for both handling and joining. The effort for implementing an automated solution increases with decreasing stability.',
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
      description: 'Components are classified as insensitive if even high forces (greater than twice the weight force) can be applied without visible or function-relevant damage. Hardly sensitive components are those for which slight visible damage is tolerable since the function of the component remains intact. Components are classified as sensitive if either the optical impression of the components is relevant, such as components lying in the visible area with a painted surface, or components that are damaged in function. In the case of very sensitive components, external forces can easily cause damage which could lead to the destruction of the component (e.g. components with surface coatings or filter membranes)',
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
      description: 'If the joining component can be gripped from the outside with a two- or three-finger gripper and the existing gripping surfaces are sufficiently large, the handling of the component can be realised with a simple gripper. These components are assigned to the external grip surfaces. If the gripping surfaces are located inside the component, have an irregular contour, are difficult to access or must be produced in at least one direction form-fit, the components are assigned to the internal grip surfaces. Insofar as the component requires the use of vacuum or magnetic grippers, they are classified as magnetic grippers. If the component has to be handled and joined using a material-closed or non-contact gripping method, it is assigned to the type of gripper with fabric closure (e. g. cryogenic gripper).',
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
      description: 'The number of process-relevant variants of the joining component influences the required flexibility of the system. However, every flexibility is associated with additional effort in planning and implementing an assembly process. Information on the number of process-relevant variants of the joining component is taken from the bills of material created by the product design department.',
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
      description: 'The assignment to the displayed characteristics can be done by analysing the components or construction drawings. Theoretical methods for the determination of stable component layers from the ratio of moments of inertia around the spatial axes can be taken from Boothroyd, for example.',
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
      description: 'The criterion has the specifications shown in the figure below. An assignment to the respective characteristics is made by analysing the components or their construction drawings.',
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
      description: 'The tendency of components to catch, clamp and overlap due to their geometric shape influences the complexity and execution of the separation process. The ability to hook is described as a combination of geometric and center-of-mass related features. They are derived from internal contours and other geometric relations related to the center of gravity.',
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
      description: 'The quality level of the joining components can be determined in different ways. One possibility is the percentage of still tolerable faulty components (AQL = acceptable quality level) or the specification of the tolerable number of faulty components in fpm (faults per million). Another possibility is to place the defective parts in relation to the functioning parts. For components that have passed a 100% check, for example, the AQL value is 0% and therefore cannot be assigned to the specification. The exact values for the assignment in the other specifications depending on the company and the components under consideration.',
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
      description: 'The accessibility “Qₚ” of the positioning range is defined as the ratio of the available to the required range of motion. Provided that the available space for movement is considerably larger than required, accessibility is very good. The closer the ratio approaches the value 1, the worse the accessibility to the positioning range becomes. For values smaller than 1, the positioning range is not accessible and the joining component cannot be mounted without structural changes to the product. The specification Qₚ < 1 is therefore not considered.',
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
      description: 'The more axes required for orientation of the joining piece before joining, the more freedom an automated system must have and the greater the effort required for the safe realisation of an automated solution.',
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
      description: 'With the increasing complexity of the joining movement, more axes are necessary for an automated realisation.',
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
      description: 'The force or torque required for the joining process has an influence on the necessary operating resources and thus a direct influence on the expenditure for automated assembly. The joining force or torque is specified by the product designer. For manual assembly, the maximum possible forces and torques can be taken from the standard DIN 33411 parts 1 to 5. If the forces or torques are higher than those specified in the standard, a mechanised or automated station must be provided for the execution of the process. [16] If only the weight force of the joining component is effective during a joining process, such as placing on, the characteristic “none” or “low” can be used. During insertion, the forces are in the range between “low”, “medium” and “high”. The exact range of values for the assignment to the specifications depends on the product range produced.',
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
      description: 'Joining aids reduce the required accuracy of positioning and orientation and thus also the effort for the function carriers to be used. The assignment of the components to their characteristics is done by analysing the components or the technical drawings.',
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
      description: 'The greater the number of basic components into which a joining component is to be joined, the greater the effort required to make the connection. Different tolerances add up and therefore a higher positioning accuracy for the joining component is necessary. As a result, the cost of implementing an automated assembly is also increased. The assignment is made by analysing the product structure.',
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
      description: 'The less secure a component is, the greater the necessary additional security and thus the automation expenditure increases.',
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
      description: 'If “special operations” such as adjustment, cleaning, greasing or marking have to be integrated into an automated assembly process, an additional effort is to be expected during the realisation and thus a worse economic efficiency.',
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