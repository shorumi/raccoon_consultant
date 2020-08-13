package br.com.dasa.labexam.helpers.factories;

import br.com.dasa.labexam.entities.Laboratory;
import br.com.dasa.labexam.entities.Status;

public class LaboratoryMother {
  public static Laboratory.LaboratoryBuilder complete() {
    return Laboratory.builder()
            .id(1L)
            .name("Mr. Pickles")
            .address("Veggies street")
            .status(Status.ACTIVE)
            .deleted(false);
  }
}
