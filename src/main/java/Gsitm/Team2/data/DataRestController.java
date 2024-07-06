package Gsitm.Team2.data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataRestController {

    DataService dataService;

    public DataRestController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping ("/data")
    List<DataResponse> findAll (){
        return dataService.findAll();
    }

}
