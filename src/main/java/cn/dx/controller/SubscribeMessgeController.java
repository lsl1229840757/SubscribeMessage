package cn.dx.controller;

import cn.dx.bean.WxQuartzJobBean;
import cn.dx.service.QuartzService;
import cn.dx.util.QuartzCronDateUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 消息订阅控制层
 */
@Controller
@RequestMapping("/subscribeMessage/*")
public class SubscribeMessgeController {
    @Autowired
    private QuartzService quartzService;

    /**
     * 微信端存入消息提醒
     * @param touser 用户
     * @param templateId 订阅模板id
     * @param data 用户设置的提醒数据
     * @param createTime 订阅创建时间
     * @param sendTime 订阅推送时间
     * @return 不跳转页面
     */
    @RequestMapping("wx/addMessage")
    public ModelAndView addWxMessage(String touser, String templateId, @RequestBody JSONObject data,
                                     Date createTime, Date sendTime){
        /**
         * 存入数据
         */
        Map<String, Object> jobData = new HashMap<>();
        jobData.put("touser", touser); //存入用户openid
        jobData.put("templateId", templateId); //存入模板消息id
        jobData.put("createTime", createTime); //存入创建时间
        jobData.put("sendTime", sendTime); //存入发送时间
        jobData.put("data", data); //存入用户数据
        //添加job
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.getDefault());
        String jobName = UUID.randomUUID().toString()+ sdf.format(new Date());
        quartzService.addJob(WxQuartzJobBean.class, jobName,
                 QuartzService.WX_SUBSCRIBEMESSAGE_GROUP, QuartzCronDateUtil.getCron(sendTime), jobData);
        return null;
    }

    @ResponseBody
    @RequestMapping("queryAllMessage")
    public List<Map<String, Object>> queryAllMessage(){
        return quartzService.queryAllJob();
    }

    @ResponseBody
    @RequestMapping("queryMessageByGroup")
    public List<Map<String, Object>> queryMessageByGroup(String jobGroupName){
        return quartzService.queryJobByGroup(jobGroupName);
    }

    /**
     * 绑定日期解析格式
     * @param binder 数据绑定器
     */
    @InitBinder
    public void bindDate(WebDataBinder binder){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        binder.registerCustomEditor(Date.class,new CustomDateEditor(simpleDateFormat, false));
    }

}
