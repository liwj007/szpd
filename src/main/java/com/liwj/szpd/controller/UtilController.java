package com.liwj.szpd.controller;

import com.liwj.szpd.mapper.*;
import com.liwj.szpd.model.*;
import com.liwj.szpd.service.ProjectService;
import com.liwj.szpd.utils.*;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipOutputStream;

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

    @Autowired
    private ProjectFileMapper projectFileMapper;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectTreasurerMapper projectTreasurerMapper;

    @Autowired
    private InvoiceFileMapper invoiceFileMapper;

    private final static String uploadPath = "/Users/liwj/Documents/uploads/tmp/";

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public JsonResult excel(@RequestHeader(value = "token") String token,
                            @RequestParam(value = "type") Integer type) throws Exception {
        ExcelData data = new ExcelData();

        switch (type) {
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
        if (type == 0 || type == 1) {
            titles.add("合同编号");
            titles.add("项目甲方");
            titles.add("合同约定-项目启动");
            titles.add("合同约定-项目中期");
            titles.add("合同约定-初步成果");
            titles.add("合同约定-评审验收");
            titles.add("合同约定-最终成果");
        }

        if (type == 0 || type == 2) {
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

        if (type == 0 || type == 3) {
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
        for (Project project : projects) {
            List<Object> row = new ArrayList();


            row.add(project.getProjectNumber());
            row.add(project.getName());

            if (type == 0 || type == 1) {
                row.add(project.getContractNumber());
                row.add(project.getPartyA());

                ProjectFeeExample feeExample = new ProjectFeeExample();
                feeExample.createCriteria().andProjectIdEqualTo(project.getId());
                List<ProjectFee> projectFees = projectFeeMapper.selectByExample(feeExample);
                ProjectFee fee = projectFees.get(0);
                row.add(fee.getStartPercent() == null ? "" : fee.getStartPercent().setScale(2));
                row.add(fee.getMiddlePercent() == null ? "" : fee.getMiddlePercent().setScale(2));
                row.add(fee.getPreliminaryResultPercent() == null ? "" : fee.getPreliminaryResultPercent().setScale(2));
                row.add(fee.getReviewPercent() == null ? "" : fee.getReviewPercent().setScale(2));
                row.add(fee.getFinalPercent() == null ? "" : fee.getFinalPercent().setScale(2));
            }

            if (type == 0 || type == 2) {

                ProjectLeaderExample leaderExample = new ProjectLeaderExample();
                leaderExample.createCriteria().andProjectIdEqualTo(project.getId());
                List<ProjectLeader> projectLeaders = projectLeaderMapper.selectByExample(leaderExample);
                String leaderNames = "";
                for (ProjectLeader leader : projectLeaders) {
                    User tmp = userMapper.selectByPrimaryKey(leader.getUserId());
                    leaderNames += tmp.getName() + ";";
                }
                row.add(leaderNames);


                ProjectMemberExample memberExample = new ProjectMemberExample();
                memberExample.createCriteria().andProjectIdEqualTo(project.getId());
                List<ProjectMember> projectMembers = projectMemberMapper.selectByExample(memberExample);
                String memberNames = "";
                for (ProjectMember member : projectMembers) {
                    User tmp = userMapper.selectByPrimaryKey(member.getUserId());
                    memberNames += tmp.getName() + ";";
                }
                row.add(memberNames);


                ProjectScheduleExample scheduleExample = new ProjectScheduleExample();
                scheduleExample.createCriteria().andProjectIdEqualTo(project.getId());
                List<ProjectSchedule> projectSchedules = projectScheduleMapper.selectByExample(scheduleExample);
                ProjectSchedule schedule = projectSchedules.get(0);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                row.add(schedule.getStartDate() == null ? "" : sdf.format(schedule.getStartDate()));

                row.add(schedule.getPlanStartUpDate() == null ? "" : sdf.format(schedule.getPlanStartUpDate()));

                row.add(schedule.getPlanMiddleDate() == null ? "" : sdf.format(schedule.getPlanMiddleDate()));

                row.add(schedule.getPlanPreliminaryResult() == null ? "" : sdf.format(schedule.getPlanPreliminaryResult()));

                row.add(schedule.getPlanReviewDate() == null ? "" : sdf.format(schedule.getPlanReviewDate()));

                row.add(schedule.getPlanFinalDate() == null ? "" : sdf.format(schedule.getPlanFinalDate()));

                row.add(schedule.getActualStartUpDate() == null ? "" : sdf.format(schedule.getActualStartUpDate()));

                row.add(schedule.getActualMiddleDate() == null ? "" : sdf.format(schedule.getActualMiddleDate()));

                row.add(schedule.getActualPreliminaryResult() == null ? "" : sdf.format(schedule.getActualPreliminaryResult()));

                row.add(schedule.getActualReviewDate() == null ? "" : sdf.format(schedule.getActualReviewDate()));

                row.add(schedule.getActualFinalDate() == null ? "" : sdf.format(schedule.getActualFinalDate()));
            }

            if (type == 0 || type == 3) {

            }

            rows.add(row);
        }

        data.setTitles(titles);
        data.setRows(rows);


        //生成本地
        SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String fileName = fdate.format(new Date()) + ".xlsx";
        File f = new File("/tmp/" + fileName);
        FileOutputStream out = new FileOutputStream(f);
        ExcelUtils.exportExcel(data, out);
        out.close();

//        ExcelUtils.exportExcel(response,fileName,data);

        FileSystemResource resource = new FileSystemResource(f);
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("file", resource);

        JsonResult result = PostHTTP.sendPostRequest("https://www.shared-parking.cn/fs/upload_file", param);
        return JsonResult.renderSuccess(Constants.SUCCESS, result.getData());
    }

    @RequestMapping(value = "/export_finance", method = RequestMethod.GET)
    public void exportProjectFinanceFiles(@RequestParam(value = "token") String token,
                                   @RequestParam(value = "id") Integer id,
                                   HttpServletResponse response) throws Exception {

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String baseFilePath = uploadPath + uuid +"/";
        File baseFileDir = new File(baseFilePath);
        if (!baseFileDir.exists()) {
            baseFileDir.mkdir();
        }

        InvoiceFileExample invoiceFileExample = new InvoiceFileExample();
        invoiceFileExample.createCriteria().andProjectIdEqualTo(id);
        List<InvoiceFile> invoiceFiles = invoiceFileMapper.selectByExample(invoiceFileExample);
        for (InvoiceFile invoiceFile: invoiceFiles){
            URL httpurl = new URL(invoiceFile.getPath());
            String[] tmp = invoiceFile.getPath().split("/");
            FileUtils.copyURLToFile(httpurl, new File(baseFilePath + tmp[tmp.length - 1]));
        }

        String outFilePath = uploadPath + uuid + ".zip";
        FileOutputStream fos = new FileOutputStream(outFilePath);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(baseFilePath);

        ZipUtils.zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();

        download(response, outFilePath, uuid + ".zip");

        FileUtil.DeleteFolder(outFilePath);
        FileUtil.DeleteFolder(baseFilePath);
    }

    @RequestMapping(value = "/export_file", method = RequestMethod.GET)
    public void exportProjectFiles(@RequestParam(value = "token") String token,
                                   @RequestParam(value = "startDate") String startDate,
                                   @RequestParam(value = "endDate") String endDate,
                                   HttpServletResponse response) throws Exception {
        User user = userMapper.findByToken(token);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        ProjectManagerExample managerExample = new ProjectManagerExample();
        managerExample.createCriteria().andUserIdEqualTo(user.getId());
        List<ProjectManager> projectManagers = projectManagerMapper.selectByExample(managerExample);

        List<Integer> projectIds = new ArrayList<>();
        for (ProjectManager manager : projectManagers) {
            projectIds.add(manager.getProjectId());
        }

        ProjectFileExample fileExample = new ProjectFileExample();
        fileExample.createCriteria().andProjectIdIn(projectIds).andCreatedTimeGreaterThanOrEqualTo(sdf.parse(startDate)).andCreatedTimeLessThan(sdf.parse(endDate));
        List<ProjectFile> projectFiles = projectFileMapper.selectByExample(fileExample);
        HashSet<Integer> ids = new HashSet<>();
        for (ProjectFile file : projectFiles) {
            ids.add(file.getProjectId());
        }


        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String baseFilePath = uploadPath + uuid;
        File baseFileDir = new File(baseFilePath);
        if (!baseFileDir.exists()) {
            baseFileDir.mkdir();
        }

        for (Integer id : ids) {
            Project project = projectMapper.selectByPrimaryKey(id);
            String projectPath = baseFilePath + "/" + project.getName();
            File projectDir = new File(projectPath);
            if (!projectDir.exists()) {
                projectDir.mkdir();
            }

            //签到表
            fileExample = new ProjectFileExample();
            fileExample.createCriteria().andProjectIdEqualTo(id).andCategoryEqualTo(Constants.FILE_CHECK)
                    .andCreatedTimeGreaterThanOrEqualTo(sdf.parse(startDate)).andCreatedTimeLessThan(sdf.parse(endDate));
            projectFiles = projectFileMapper.selectByExample(fileExample);
            String checkFilePath = projectPath + "/签到表/";
            File checkDir = new File(checkFilePath);
            if (!checkDir.exists()) {
                checkDir.mkdir();
            }
            for (ProjectFile file : projectFiles) {
                URL httpurl = new URL(file.getPath());
                String[] tmp = file.getPath().split("/");
                FileUtils.copyURLToFile(httpurl, new File(checkFilePath + tmp[tmp.length - 1]));
            }

            //会议照片
            fileExample = new ProjectFileExample();
            fileExample.createCriteria().andProjectIdEqualTo(id).andCategoryEqualTo(Constants.FILE_MEETING_PHOTO)
                    .andCreatedTimeGreaterThanOrEqualTo(sdf.parse(startDate)).andCreatedTimeLessThan(sdf.parse(endDate));
            projectFiles = projectFileMapper.selectByExample(fileExample);
            String photoFilePath = projectPath + "/会议照片/";
            File photoDir = new File(photoFilePath);
            if (!photoDir.exists()) {
                photoDir.mkdir();
            }
            for (ProjectFile file : projectFiles) {
                URL httpurl = new URL(file.getPath());
                String[] tmp = file.getPath().split("/");
                FileUtils.copyURLToFile(httpurl, new File(photoFilePath + tmp[tmp.length - 1]));
            }

            //会议纪要
            fileExample = new ProjectFileExample();
            fileExample.createCriteria().andProjectIdEqualTo(id).andCategoryEqualTo(Constants.FILE_MEETING_SUMMARY)
                    .andCreatedTimeGreaterThanOrEqualTo(sdf.parse(startDate)).andCreatedTimeLessThan(sdf.parse(endDate));
            projectFiles = projectFileMapper.selectByExample(fileExample);
            String summaryFilePath = projectPath + "/会议纪要/";
            File summaryDir = new File(summaryFilePath);
            if (!summaryDir.exists()) {
                summaryDir.mkdir();
            }
            for (ProjectFile file : projectFiles) {
                URL httpurl = new URL(file.getPath());
                String[] tmp = file.getPath().split("/");
                FileUtils.copyURLToFile(httpurl, new File(summaryFilePath + tmp[tmp.length - 1]));
            }
        }

        //这个是文件夹的绝对路径，如果想要相对路径就自行了解写法
//        String sourceFile = "E:\\2-project\\15-henanhuagong\\CEPOP\\trunk\\zhnh\\uploadFile\\hnec";
        //这个是压缩之后的文件绝对路径
        String outFilePath = uploadPath + uuid + ".zip";
        FileOutputStream fos = new FileOutputStream(outFilePath);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(baseFilePath);

        ZipUtils.zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();

        download(response, outFilePath, startDate + ".zip");

        FileUtil.DeleteFolder(outFilePath);
        FileUtil.DeleteFolder(baseFilePath);
    }

    public String projectRole(Integer projectID, Integer userID) {
        ProjectManagerExample managerExample = new ProjectManagerExample();
        managerExample.createCriteria().andProjectIdEqualTo(projectID).andUserIdEqualTo(userID);
        long c = projectManagerMapper.countByExample(managerExample);
        if (c > 0) {
            return "项目管理员";
        } else {
            ProjectLeaderExample leaderExample = new ProjectLeaderExample();
            leaderExample.createCriteria().andProjectIdEqualTo(projectID).andUserIdEqualTo(userID);
            c = projectLeaderMapper.countByExample(leaderExample);
            if (c > 0) {
                return "项目负责人";
            } else {
                ProjectMemberExample memberExample = new ProjectMemberExample();
                memberExample.createCriteria().andProjectIdEqualTo(projectID).andUserIdEqualTo(userID);
                c = projectMemberMapper.countByExample(memberExample);
                if (c > 0) {
                    return "项目成员";
                } else {
                    ProjectTreasurerExample treasurerExample = new ProjectTreasurerExample();
                    treasurerExample.createCriteria().andProjectIdEqualTo(projectID).andUserIdEqualTo(userID);
                    c = projectTreasurerMapper.countByExample(treasurerExample);
                    if (c>0){
                        return  "财务人员";
                    }else{
                        return "其他";
                    }
                }
            }
        }
    }

    @RequestMapping(value = "/export_user", method = RequestMethod.GET)
    public void exportUserProcess(@RequestParam(value = "token") String token,
                                  @RequestParam(value = "startDate") String startDate,
                                  @RequestParam(value = "endDate") String endDate,
                                  HttpServletResponse response) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = null;


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);

        UserExample userExample = new UserExample();
        userExample.createCriteria().andStatusEqualTo(Constants.USER_STATE_ACTIVE);
        List<User> allUsers = userMapper.selectByExample(userExample);

        for (User targetUser : allUsers) {
            sheet = wb.createSheet(targetUser.getName());

            HSSFRow row = sheet.createRow(0);
            HSSFCell cell = row.createCell(0);
            cell.setCellValue("项目名称");
            cell = row.createCell(1);
            cell.setCellValue("项目身份");
            cell = row.createCell(2);
            cell.setCellValue("新增结点");

            List<Integer> projectIds = projectService.getUserProjectIds(targetUser);
            if (projectIds.size()==0)
                continue;
            ProjectExample projectExample = new ProjectExample();
            projectExample.createCriteria().andIdIn(projectIds).andStatusNotEqualTo(Constants.PROJECT_CLOSE);
            List<Project> openProjects = projectMapper.selectByExample(projectExample);

            int index = 1;
            for (Project project : openProjects) {
                row = sheet.createRow(index);

                String role = projectRole(project.getId(), targetUser.getId());
                row.createCell(0).setCellValue(project.getName());
                row.createCell(1).setCellValue(role);

                ProjectScheduleExample scheduleExample = new ProjectScheduleExample();
                scheduleExample.createCriteria().andProjectIdEqualTo(project.getId());
                List<ProjectSchedule> projectScheduleList = projectScheduleMapper.selectByExample(scheduleExample);
                ProjectSchedule schedule = projectScheduleList.get(0);

                int cellIndex = 2;
                if (schedule.getActualStartUpDate() != null && schedule.getActualStartUpDate().after(start) && schedule.getActualStartUpDate().before(end)) {
                    row.createCell(cellIndex).setCellValue("项目启动");
                    cellIndex++;
                }
                if (schedule.getActualMiddleDate() != null && schedule.getActualMiddleDate().after(start) && schedule.getActualMiddleDate().before(end)) {
                    row.createCell(cellIndex).setCellValue("项目期中");
                    cellIndex++;
                }
                if (schedule.getActualPreliminaryResult() != null && schedule.getActualPreliminaryResult().after(start) && schedule.getActualPreliminaryResult().before(end)) {
                    row.createCell(cellIndex).setCellValue("初步成果");
                    cellIndex++;
                }
                if (schedule.getActualReviewDate() != null && schedule.getActualReviewDate().after(start) && schedule.getActualReviewDate().before(end)) {
                    row.createCell(cellIndex).setCellValue("验收评审");
                    cellIndex++;
                }
                if (schedule.getActualFinalDate() != null && schedule.getActualFinalDate().after(start) && schedule.getActualFinalDate().before(end)) {
                    row.createCell(cellIndex).setCellValue("最终成果");
                    cellIndex++;
                }

                index++;
            }
        }
        try {
            String path = uploadPath + startDate + ".xls";
            FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            fout.close();
            download(response, path, startDate + ".xls");

            FileUtil.DeleteFolder(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void download(HttpServletResponse response, String filePath, String fileName) {
        File downFile = new File(filePath);
        if (downFile.exists()) {
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

            // 实现文件下载
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(downFile);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
