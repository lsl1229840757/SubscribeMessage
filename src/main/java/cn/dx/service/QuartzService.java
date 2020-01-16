package cn.dx.service;


import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface QuartzService {
    /**
     * 定义任务组常量
     */
    String WX_SUBSCRIBEMESSAGE_GROUP = "wxSubscribeMessage";

    String QQ_SUBSCRIBEMESSAGE_GROUP = "qqSubscribeMessage";
    /**
     * 增加一个job
     *
     * @param jobClass     任务实现类
     * @param jobName      任务名称(建议唯一)
     * @param jobGroupName 任务组名
     * @param jobTime      时间表达式 (这是每隔多少秒为一次任务)
     * @param jobTimes     运行的次数 （<0:表示不限次数）
     * @param jobData      参数
     */
    void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, int jobTime,
                       int jobTimes, Map jobData);


    /**
     * 增加一个job
     *
     * @param jobClass     任务实现类
     * @param jobName      任务名称(建议唯一)
     * @param jobGroupName 任务组名
     * @param jobTime      时间表达式 （如：0/5 * * * * ? ）
     * @param jobData      参数
     */
    void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName,
                       String jobTime, Map jobData);


    /**
     * 修改 一个job的 时间表达式
     *
     * @param jobName
     * @param jobGroupName
     * @param jobTime
     */
    void updateJob(String jobName, String jobGroupName, String jobTime);

    /**
     * 删除任务一个job
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务组名
     */
    void deleteJob(String jobName, String jobGroupName);

    /**
     * 暂停一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    void pauseJob(String jobName, String jobGroupName);

    /**
     * 恢复一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    void resumeJob(String jobName, String jobGroupName);


    /**
     * 立即执行一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    void runAJobNow(String jobName, String jobGroupName);

    /**
     * 根据name，group 查询某一个任务
     *
     * @return 某个任务
     */
    Map<String, Object> getJob(String name, String group);

    /**
     * 获取所有计划中的任务列表
     *
     * @return 任务的列表
     */
    List<Map<String, Object>> queryAllJob();

    /**
     * 获取所有正在运行的job
     *
     * @return 正在运行的任务列表
     */
    List<Map<String, Object>> queryRunJob();

    /**
     * 获取所有计划中的任务列表
     *
     * @return 任务的列表
     */
    List<Map<String, Object>> queryJobByGroup(String jobGroupName);

}
