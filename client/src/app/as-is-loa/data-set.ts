import { LoaInfo } from './loa-info';

function RGB2Color(r,g,b) {
    return '#' + byte2Hex(r) + byte2Hex(g) + byte2Hex(b);
}

function byte2Hex(n) {
    var nybHexString = "0123456789ABCDEF";
    return String(nybHexString.substr((n >> 4) & 0x0F,1)) + nybHexString.substr(n & 0x0F,1);
}

function *makeColorGradient(
    frequency1, frequency2, frequency3,
    phase1, phase2, phase3,
    center, width, len
) {
    if (center == undefined)
        center = 128;
    if (width == undefined)
        width = 127;
    if (len == undefined)
        len = 50;

    for (var i = 0; i < len; ++i) {
        var red = Math.sin(frequency1*i + phase1) * width + center;
        var grn = Math.sin(frequency2*i + phase2) * width + center;
        var blu = Math.sin(frequency3*i + phase3) * width + center;
        yield RGB2Color(red,grn,blu);
    }
}

export class DataSet {

    private static createColorSequence = () => makeColorGradient(
        2.4, 2.4, 2.25,
        0, 2, 4,
        128, 127, 50
    );

    private static colorIterator = DataSet.createColorSequence();

    label;
    backgroundColor;
    borderColor: 'black';
    borderWidth: 1;
    data;

    constructor(
        loaInfo:LoaInfo,
        label:String
    ) {
        this.backgroundColor = DataSet.colorIterator.next().value;
        this.label = label;
        this.data = [{r:10}];
        this.data[0].x = loaInfo.physicalLoa;
        this.data[0].y = loaInfo.cognitiveLoa;
    }

    public static resetSeed = () => {
        DataSet.colorIterator = DataSet.createColorSequence();
    }

}