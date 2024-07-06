package Gsitm.Team2.data;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper {

    List<DataResponse> findAll();
}
