package org.springframework.samples.petclinic.developers;

public record OsBean(double cpuLoad, double processCpuLoad, long processCpuTime, long totalMemoryOs,
        long freeMemoryOs) {

}
