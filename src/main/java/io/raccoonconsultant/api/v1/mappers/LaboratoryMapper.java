package io.raccoonconsultant.api.v1.mappers;

import io.raccoonconsultant.api.v1.models.LaboratoryDTO;
import io.raccoonconsultant.entities.Laboratory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LaboratoryMapper {
  LaboratoryMapper INSTANCE = Mappers.getMapper(LaboratoryMapper.class);

  @Mapping(source = "id", target = "id")
  LaboratoryDTO laboratoryToLaboratoryDTO(Laboratory laboratory);

  @Mapping(source = "id", target = "id")
  Laboratory laboratoryDtoToLaboratory(LaboratoryDTO laboratoryDTO);
}
