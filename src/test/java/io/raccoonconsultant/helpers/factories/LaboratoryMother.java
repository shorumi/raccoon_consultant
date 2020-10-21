package io.raccoonconsultant.helpers.factories;

import io.raccoonconsultant.entities.Laboratory;
import io.raccoonconsultant.entities.Status;

import java.util.Random;

import static org.mockito.ArgumentMatchers.anyLong;

public class LaboratoryMother {
  public static Laboratory.LaboratoryBuilder complete() {
    return Laboratory.builder()
            .id(new Random().nextLong())
            .name("Mr. Pickles")
            .address("Veggies street")
            .status(Status.ACTIVE)
            .deleted(false);
  }
}
