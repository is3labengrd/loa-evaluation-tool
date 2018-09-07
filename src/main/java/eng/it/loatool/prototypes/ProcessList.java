package eng.it.loatool.prototypes;

import com.fasterxml.jackson.annotation.JsonRawValue;

import eng.it.loatool.var.service.VARServiceWrapper;

public class ProcessList {

    @JsonRawValue
    public String getList() {
        return (new VARServiceWrapper()).getProcessesSegmentList();
    }

}
