package eng.it.loatool.var.service;

import java.util.function.Supplier;

public class VARFailureInformer {

    Object filter(Supplier supplier) throws Exception {
        try {
            return supplier.get();
        } catch (Throwable t) {
            throw camNotReachableException;
        }
    }

    private Exception camNotReachableException = new Exception(
        "Unable to reach the CAM. Check the connection with the component"
    );

}
