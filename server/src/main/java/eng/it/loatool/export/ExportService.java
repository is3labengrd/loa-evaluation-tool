package eng.it.loatool.export;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class ExportService {

    public void export() throws IOException {
        synchronized (this.monitor) {
            Runtime runtime = Runtime.getRuntime();
            File dump = new File("dump.sql");
            if (dump.exists()) {
                dump.delete();
            }
            Process p = runtime.exec(
                "mysqldump --databases loa_evaluation_tool " +
                "--user=root " +
                "--password=root " +
                "--skip-add-drop-table --column-statistics=0 " +
                "--result-file=" + dump.getAbsolutePath()
            );
        }
    }

    private final Object monitor = new Object();

}
