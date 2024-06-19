package org.springframework.samples.petclinic.developers;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class MonitorService {

    @PersistenceContext
    EntityManager em;

    public Stats getStats() {

        String metricQuery = "SELECT SUM(DATA_LENGTH + INDEX_LENGTH) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'petclinic'";

        // bytes
        long jvmMemory = Runtime.getRuntime().totalMemory();
        long freeHeapMemory = Runtime.getRuntime().freeMemory();
        // Hibernate returns BigDecimal for Oracle by default
        Query q = em.createNativeQuery(metricQuery);
        BigDecimal result = ((BigDecimal) q.getSingleResult());
        System.out.println(result.longValue());

        // Unsupported
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(osBean.getArch());
        System.out.println(osBean.getSystemLoadAverage());
        // double cpuUsage = osBean.getArch() * 100;

        return new Stats(jvmMemory, freeHeapMemory, result.longValue(), osBean.getSystemLoadAverage());

    }

}
