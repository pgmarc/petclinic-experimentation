package org.springframework.samples.petclinic.developers;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class MonitorService {

    @PersistenceContext
    EntityManager em;

    public Stats getStats() {

        // String metricQuery = "SELECT SUM(DATA_LENGTH + INDEX_LENGTH) FROM
        // INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'petclinic'";

        Date date = new Date();
        long milis = date.getTime();
        // bytes

        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long occupiedMemory = totalMemory - freeMemory;
        Memory memory = new Memory("bytes", totalMemory, occupiedMemory, freeMemory);

        // Hibernate returns BigDecimal for Oracle by default
        // Query q = em.createNativeQuery(metricQuery);
        // BigDecimal result = ((BigDecimal) q.getSingleResult());
        // long diskSize = result.longValue();

        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        long processCpuTime = osBean.getProcessCpuTime();
        long freeMemoryOs = osBean.getFreeMemorySize();
        long totalMemoryOs = osBean.getTotalMemorySize();
        double cpuLoad = osBean.getCpuLoad();
        double processCpuLoad = osBean.getProcessCpuLoad();

        OsBean beanStats = new OsBean(cpuLoad, processCpuLoad, processCpuTime, totalMemoryOs, freeMemoryOs);

        return new Stats(milis, memory, beanStats);

    }

}
