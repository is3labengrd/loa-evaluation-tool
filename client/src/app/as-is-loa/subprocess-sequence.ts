import { LoaInfo } from './loa-info';

export class SubprocessSequence {

    public subprocessArray;
    public loaInfo;
    public loaInfoCognitive;

    public constructor(
        subprocessArray: Array<any>
    ) {
        this.subprocessArray = subprocessArray;

        let index = subprocessArray.length;
        while (index--) {
            if (subprocessArray[index].loaInfo) {
                this.loaInfo = new LoaInfo(
                    subprocessArray[index].loaInfo,
                    subprocessArray[index].loaInfoCognitive
                );
                break;
            }
        }
    }

    public toString() {
        JSON.stringify(this);
    }

}