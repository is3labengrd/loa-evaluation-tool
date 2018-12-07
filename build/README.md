# LoA Evaluation Tool

## Description of the components
Each folder contains a specific version of LoA Evaluation Tool. The project consists of the components:
* LoAService.war - representing the server side
* LoAEvaluationTool.war - representing the client side.

To deploy and run the whole project please refer to the following [instruction](https://github.com/Engineering-Research-and-Development/loa-evaluation-tool/blob/497db9ae37fc6d43b09d0e3107ac45d1e13a2079/A4BLUE_Tool_Web_Porting_Installation_instructions.docx).

### v0.0
This folder contains a fake version of LoA Evaluation Tool. There is not any comunication between the client (LoAEvaluationTool.war) and server (LoAService.war) components.

### v1.0
This forder contains the version 1.0 of LoA Evaluation Tool. An environment variable **ENV_SAR_URL** containing the url of the CAMService (e.g. *http://[host]:[port]/CAMService*) must be set before deploy and run the application.