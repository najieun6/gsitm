package Gsitm.Team2.data;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    DataRepository dataRepository;
    DataMapper dataMapper;

    public DataService(DataRepository dataRepository, DataMapper dataMapper) {
        this.dataRepository = dataRepository;
        this.dataMapper = dataMapper;
    }

    public List<DataResponse> findAll() {
        return dataMapper.findAll();
    }

    public List<DataResponse> findAllByFilter(String codename, String guname) {
        return dataMapper.findAllByFilter(codename, guname);

    }

    public List<EventListResponseDto> findByTitle(String title) {
        List<Data> events = dataRepository.findAllByTitle(title);
        List<EventListResponseDto> eventList = events.stream()
                .map(e -> new EventListResponseDto(
                        e.getTitle(),
                        e.getDate(),
                        e.getPlace(),
                        e.getMain_img())).toList();
        return eventList;
    }
}
