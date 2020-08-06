package br.com.dasa.labexam.api.v1.mappers;

import br.com.dasa.labexam.api.v1.models.LaboratoryDTO;
import br.com.dasa.labexam.entities.Laboratory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LaboratoryMapper {
  LaboratoryMapper INSTANCE = Mappers.getMapper(LaboratoryMapper.class);

  @Mapping(source = "id", target = "id")
  LaboratoryDTO laboratoryToLaboratoryDTO(Laboratory laboratory);
}
