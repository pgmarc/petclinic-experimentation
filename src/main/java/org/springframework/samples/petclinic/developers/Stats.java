package org.springframework.samples.petclinic.developers;

public record Stats(long timestamp, Memory memory, long diskSize, double cpuUsage) {

}
