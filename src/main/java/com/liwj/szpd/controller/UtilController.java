package com.liwj.szpd.controller;

import com.liwj.szpd.mapper.*;
import com.liwj.szpd.model.*;
import com.liwj.szpd.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/util")
public class UtilController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectManagerMapper projectManagerMapper;

    @Autowired
    private ProjectFeeMapper projectFeeMapper;


    @Autowired
    private ProjectScheduleMapper projectScheduleMapper;

    @Autowired
    private ProjectLeaderMapper projectLeaderMapper;

    @Autowired
    private ProjectMemberMapper projectMemberMapper;

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public JsonResult excel(@RequestHeader(value = "token") String token,
                            @RequestParam(value = "type") Integer type) throws Exception {
        ExcelData data = new ExcelData();

        switch (type){
            case 0:
                data.setName("项目总表");
                break;
            case 1:
                data.setName("项目概括");
                break;
            case 2:
                data.setName("生产管理");
                break;
            case 3:
                data.setName("财务管理");
                break;
        }


        List<String> titles = new ArrayList();
        titles.add("项目名称");
        titles.add("项目编号");
        if (type==0||type==1){
            titles.add("合同编号");
            titles.add("项目甲方");
            titles.add("合同约定-项目启动");
            titles.add("合同约定-项目中期");
            titles.add("合同约定-初步成果");
            titles.add("合同约定-评审验收");
            titles.add("合同约定-最终成果");
        }

        if (type==0||type==2){
            titles.add("项目负责人");
            titles.add("项目成员");

            titles.add("项目开工时间");
            titles.add("项目计划-项目启动");
            titles.add("项目计划-项目中期");
            titles.add("项目计划-初步成果");
            titles.add("项目计划-评审验收");
            titles.add("项目计划-最终成果");
            titles.add("项目进度-项目启动");
            titles.add("项目进度-项目中期");
            titles.add("项目进度-初步成果");
            titles.add("项目进度-评审验收");
            titles.add("项目进度-最终成果");
        }

        if (type==0||type==3){
            titles.add("总开票情况");
            titles.add("总到账情况");
            titles.add("开票未到账情况");
            titles.add("基于结点未到账情况");
            titles.add("待催款数额");
        }

        List<List<Object>> rows = new ArrayList();

        User user = userMapper.findByToken(token);
        List<Integer> projectIds = new ArrayList<>();
        ProjectManagerExample managerExample = new ProjectManagerExample();
        managerExample.createCriteria().andUserIdEqualTo(user.getId());
        List<ProjectManager> projectManagers = projectManagerMapper.selectByExample(managerExample);
        for (ProjectManager manager : projectManagers) {
            projectIds.add(manager.getProjectId());
        }

        ProjectExample projectExample = new ProjectExample();
        projectExample.createCriteria().andIdIn(projectIds);
        List<Project> projects = projectMapper.selectByExample(projectExample);
        for (Project project:projects){
            List<Object> row = new ArrayList();


            row.add(project.getProjectNumber());
            row.add(project.getName());

            if (type==0||type==1){
                row.add(project.getContractNumber());
                row.add(project.getPartyA());

                ProjectFeeExample feeExample = new ProjectFeeExample();
                feeExample.createCriteria().andProjectIdEqualTo(project.getId());
                List<ProjectFee> projectFees = projectFeeMapper.selectByExample(feeExample);
                ProjectFee fee = projectFees.get(0);
                row.add(fee.getStartPercent()==null?"":fee.getStartPercent().setScale(2));
                row.add(fee.getMiddlePercent()==null?"":fee.getMiddlePercent().setScale(2));
                row.add(fee.getPreliminaryResultPercent()==null?"":fee.getPreliminaryResultPercent().setScale(2));
                row.add(fee.getReviewPercent()==null?"":fee.getReviewPercent().setScale(2));
                row.add(fee.getFinalPercent()==null?"":fee.getFinalPercent().setScale(2));
            }

            if (type==0||type==2){

                ProjectLeaderExample leaderExample = new ProjectLeaderExample();
                leaderExample.createCriteria().andProjectIdEqualTo(project.getId());
                List<ProjectLeader> projectLeaders = projectLeaderMapper.selectByExample(leaderExample);
                String leaderNames = "";
                for (ProjectLeader leader:projectLeaders){
                    User tmp = userMapper.selectByPrimaryKey(leader.getUserId());
                    leaderNames+=tmp.getName()+";";
                }
                row.add(leaderNames);


                ProjectMemberExample memberExample = new ProjectMemberExample();
                memberExample.createCriteria().andProjectIdEqualTo(project.getId());
                List<ProjectMember> projectMembers = projectMemberMapper.selectByExample(memberExample);
                String memberNames = "";
                for (ProjectMember member: projectMembers){
                    User tmp = userMapper.selectByPrimaryKey(member.getUserId());
                    memberNames+=tmp.getName()+";";
                }
                row.add(memberNames);


                ProjectScheduleExample scheduleExample = new ProjectScheduleExample();
                scheduleExample.createCriteria().andProjectIdEqualTo(project.getId());
                List<ProjectSchedule> projectSchedules = projectScheduleMapper.selectByExample(scheduleExample);
                ProjectSchedule schedule = projectSchedules.get(0);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                row.add(schedule.getStartDate()==null?"":sdf.format(schedule.getStartDate()));

                row.add(schedule.getPlanStartUpDate()==null?"":sdf.format(schedule.getPlanStartUpDate()));

                row.add(schedule.getPlanMiddleDate()==null?"":sdf.format(schedule.getPlanMiddleDate()));

                row.add(schedule.getPlanPreliminaryResult()==null?"":sdf.format(schedule.getPlanPreliminaryResult()));

                row.add(schedule.getPlanReviewDate()==null?"":sdf.format(schedule.getPlanReviewDate()));

                row.add(schedule.getPlanFinalDate()==null?"":sdf.format(schedule.getPlanFinalDate()));

                row.add(schedule.getActualStartUpDate()==null?"":sdf.format(schedule.getActualStartUpDate()));

                row.add(schedule.getActualMiddleDate()==null?"":sdf.format(schedule.getActualMiddleDate()));

                row.add(schedule.getActualPreliminaryResult()==null?"":sdf.format(schedule.getActualPreliminaryResult()));

                row.add(schedule.getActualReviewDate()==null?"":sdf.format(schedule.getActualReviewDate()));

                row.add(schedule.getActualFinalDate()==null?"":sdf.format(schedule.getActualFinalDate()));
            }

            if (type==0||type==3){

            }

            rows.add(row);
        }

        data.setTitles(titles);
        data.setRows(rows);


        //生成本地
        SimpleDateFormat fdate=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String fileName=fdate.format(new Date())+".xlsx";
        File f = new File("/tmp/"+fileName);
        FileOutputStream out = new FileOutputStream(f);
        ExcelUtils.exportExcel(data, out);
        out.close();

//        ExcelUtils.exportExcel(response,fileName,data);

        FileSystemResource resource = new FileSystemResource(f);
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("file", resource);

        JsonResult result = PostHTTP.sendPostRequest("https://www.shared-parking.cn/fs/upload_file",param);
        return JsonResult.renderSuccess(Constants.SUCCESS,result.getData());
    }
}
