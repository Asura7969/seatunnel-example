package org.example;

import com.hazelcast.client.config.ClientConfig;
import org.apache.seatunnel.common.utils.StringFormatUtils;
import org.apache.seatunnel.engine.client.SeaTunnelClient;
import org.apache.seatunnel.engine.client.job.JobMetricsRunner;
import org.apache.seatunnel.engine.common.config.ConfigProvider;

/**
 * @author asura7969
 * @create 2024-05-19-22:29
 */
public class JobInformation {

    public static void main(String[] args) {
        String clusterName = "";
        ClientConfig clientConfig = ConfigProvider.locateAndGetClientConfig();

        SeaTunnelClient engineClient = new SeaTunnelClient(clientConfig);
        long jobId = 0L;
        JobMetricsRunner.JobMetricsSummary jobMetricsSummary = (JobMetricsRunner.JobMetricsSummary) engineClient.getJobMetricsSummary(jobId);

        //LocalDateTime startTime = LocalDateTime.now();
        //LocalDateTime endTime = LocalDateTime.now();
        String jobMetric = StringFormatUtils.formatTable(
                "Job Statistic Information",
                //"Start Time",
                //DateTimeUtils.toString(
                //        startTime, DateTimeUtils.Formatter.YYYY_MM_DD_HH_MM_SS),
                //"End Time",
                //DateTimeUtils.toString(
                //        endTime, DateTimeUtils.Formatter.YYYY_MM_DD_HH_MM_SS),
                //"Total Time(s)",
                //Duration.between(startTime, endTime).getSeconds(),
                "Total Read Count",
                jobMetricsSummary.getSourceReadCount(),
                "Total Write Count",
                jobMetricsSummary.getSinkWriteCount(),
                "Total Failed Count",
                jobMetricsSummary.getSourceReadCount()
                        - jobMetricsSummary.getSinkWriteCount());
        System.out.println(jobMetric);

    }
}
