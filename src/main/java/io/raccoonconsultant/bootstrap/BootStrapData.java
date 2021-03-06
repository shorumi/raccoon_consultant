package io.raccoonconsultant.bootstrap;

import io.raccoonconsultant.entities.Exam;
import io.raccoonconsultant.entities.Laboratory;
import io.raccoonconsultant.entities.Status;
import io.raccoonconsultant.entities.Type;
import io.raccoonconsultant.repositories.ExamRepository;
import io.raccoonconsultant.repositories.LaboratoryRepository;
import io.raccoonconsultant.repositories.TypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
  private final LaboratoryRepository laboratoryRepository;
  private final TypeRepository typeRepository;
  private final ExamRepository examRepository;

  public BootStrapData(
          LaboratoryRepository laboratoryRepository,
          TypeRepository typeRepository,
          ExamRepository examRepository
  ) {
    this.laboratoryRepository = laboratoryRepository;
    this.typeRepository = typeRepository;
    this.examRepository = examRepository;
  }

  @Override
    public void run(String... args) throws Exception {

    Type type = new Type();
    type.setName("Blood");
    typeRepository.save(type);

    Laboratory laboratory = new Laboratory();
    laboratory.setName("ABC");
    laboratory.setAddress("ABC");
    laboratory.setStatus(Status.ACTIVE);

    laboratoryRepository.save(laboratory);

    Exam exams = new Exam();
    exams.setName("Hadoken Exam");
    exams.setStatus(Status.ACTIVE);
    exams.setType(type);
    exams.setLaboratory(laboratory);

    examRepository.save(exams);

  }
}
