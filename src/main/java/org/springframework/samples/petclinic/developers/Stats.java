package org.springframework.samples.petclinic.developers;

public record Stats(long totalMemory, long freeMemory, long occupiedMemory, long diskSizeInBytes, double cpuUsage) {

    public Stats(long totalMemory, long freeMemory, long diskSizeInBytes, double cpuUsage) {
        this(totalMemory, freeMemory, totalMemory - freeMemory, diskSizeInBytes, cpuUsage);
    }

}
