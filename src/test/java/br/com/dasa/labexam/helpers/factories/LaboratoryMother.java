package br.com.dasa.labexam.helpers.factories;

import br.com.dasa.labexam.entities.Laboratory;
import br.com.dasa.labexam.entities.Status;

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
