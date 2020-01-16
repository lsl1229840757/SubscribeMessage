package cn.dx.bean;

import net.sf.json.JSONObject;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WxQuartzJobBean extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        JSONObject dataJson = JSONObject.fromObject(jobDataMap.get("data"));
        System.out.println("msg:"+dataJson.getString("msg"));
        System.out.println("当前时间:"+simpleDateFormat.format(new Date()));
        System.out.println("执行时间:"+simpleDateFormat.format((Date)jobDataMap.get("sendTime")));
    }

}
