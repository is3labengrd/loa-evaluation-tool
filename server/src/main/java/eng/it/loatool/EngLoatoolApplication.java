package eng.it.loatool;


import eng.it.loatool.var.service.VARServiceWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EngLoatoolApplication {

    public static void main(String[] args) {
        //SpringApplication.run(EngLoatoolApplication.class, args);
        System.out.println(VARServiceWrapper.getProcessesSegmentListDYNAMIC());
    }
}
