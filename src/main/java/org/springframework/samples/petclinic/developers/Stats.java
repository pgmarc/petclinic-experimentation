package org.springframework.samples.petclinic.developers;

public record Stats(long timestamp, Memory jvm, OsBean osBean) {

}
