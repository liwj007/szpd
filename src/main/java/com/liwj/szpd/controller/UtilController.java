package com.liwj.szpd.controller;

import com.liwj.szpd.mapper.*;
import com.liwj.szpd.model.*;
import com.liwj.szpd.service.ProjectFeeService;
import com.liwj.szpd.service.ProjectService;
import com.liwj.szpd.utils.*;
import com.liwj.szpd.vo.FinanceStatisticVO;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private ProjectFeeService projectFeeService;

    private final static String uploadPath = "/Users/liwj/Documents/uploads/tmp/";

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void excel(@RequestParam(value = "token") String token,
                      @RequestParam(value = "type") Integer type,
                      HttpServletResponse response) throws Exception {
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
            titles.add("基于结点未开票金额");
            titles.add("开票金额");
            titles.add("到账金额");
            titles.add("开票未到账金额");
            titles.add("待催款金额");
        }

        List<List<Object>> rows = new ArrayList();


        List<Project> projects = getMyManageProjects(token);
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

                row.add(getLeaderNames(project.getId()));


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
                FinanceStatisticVO statisticVO = projectFeeService.statisticFinanceInfo(project.getId());

                row.add(statisticVO.getStepNoInvoice());
                row.add(statisticVO.getInvoice());
                row.add(statisticVO.getAccount());
                row.add(statisticVO.getInvoiceNoAccount());
                row.add(statisticVO.getDebt());
            }

            rows.add(row);
        }

        data.setTitles(titles);
        data.setRows(rows);


        //生成本地
        SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String fileName = fdate.format(new Date()) + ".xlsx";
        File f = new File("/tmp/" + fileName);
//        FileOutputStream out = new FileOutputStream(f);
//        ExcelUtils.exportExcel(data, out);
//        out.close();

        ExcelUtils.exportExcel(response, fileName, data);

    }

    @RequestMapping(value = "/export_finance", method = RequestMethod.GET)
    public void exportProjectFinanceFiles(@RequestParam(value = "token") String token,
                                          @RequestParam(value = "id") Integer id,
                                          HttpServletResponse response) throws Exception {

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String baseFilePath = uploadPath + uuid + "/";
        File baseFileDir = new File(baseFilePath);
        if (!baseFileDir.exists()) {
            baseFileDir.mkdir();
        }

        InvoiceFileExample invoiceFileExample = new InvoiceFileExample();
        invoiceFileExample.createCriteria().andProjectIdEqualTo(id);
        List<InvoiceFile> invoiceFiles = invoiceFileMapper.selectByExample(invoiceFileExample);
        for (InvoiceFile invoiceFile : invoiceFiles) {
            URL httpUrl = new URL(invoiceFile.getPath());
            String[] tmp = invoiceFile.getPath().split("/");
            FileUtils.copyURLToFile(httpUrl, new File(baseFilePath + tmp[tmp.length - 1]));
        }

        String outFilePath = uploadPath + uuid + ".zip";
        dealZipFile(response, outFilePath, baseFilePath, uuid);
    }

    @RequestMapping(value = "/export_file", method = RequestMethod.GET)
    public void exportProjectFiles(@RequestParam(value = "token") String token,
                                   @RequestParam(value = "month") String monthStr,
                                   HttpServletResponse response) throws Exception {
        User user = userMapper.findByToken(token);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date now = sdf.parse(monthStr);
        Date startDate = getFirstDayOfThisMonth(now);
        Date endDate = getFirstDayOfNextMonth(now);

        ProjectManagerExample managerExample = new ProjectManagerExample();
        managerExample.createCriteria().andUserIdEqualTo(user.getId());
        List<ProjectManager> projectManagers = projectManagerMapper.selectByExample(managerExample);

        List<Integer> projectIds = new ArrayList<>();
        for (ProjectManager manager : projectManagers) {
            projectIds.add(manager.getProjectId());
        }

        ProjectFileExample fileExample = new ProjectFileExample();
        fileExample.createCriteria().andProjectIdIn(projectIds).andCreatedTimeGreaterThanOrEqualTo(startDate).andCreatedTimeLessThan(endDate);
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
                    .andCreatedTimeGreaterThanOrEqualTo(startDate).andCreatedTimeLessThan(endDate);
            projectFiles = projectFileMapper.selectByExample(fileExample);
            String checkFilePath = projectPath + "/签到表/";
            batchFile(checkFilePath, projectFiles);


            //会议照片
            fileExample = new ProjectFileExample();
            fileExample.createCriteria().andProjectIdEqualTo(id).andCategoryEqualTo(Constants.FILE_MEETING_PHOTO)
                    .andCreatedTimeGreaterThanOrEqualTo(startDate).andCreatedTimeLessThan(endDate);
            projectFiles = projectFileMapper.selectByExample(fileExample);
            String photoFilePath = projectPath + "/会议照片/";
            batchFile(photoFilePath, projectFiles);


            //会议纪要
            fileExample = new ProjectFileExample();
            fileExample.createCriteria().andProjectIdEqualTo(id).andCategoryEqualTo(Constants.FILE_MEETING_SUMMARY)
                    .andCreatedTimeGreaterThanOrEqualTo(startDate).andCreatedTimeLessThan(endDate);
            projectFiles = projectFileMapper.selectByExample(fileExample);
            String summaryFilePath = projectPath + "/会议纪要/";
            batchFile(summaryFilePath, projectFiles);
        }

        //这个是文件夹的绝对路径，如果想要相对路径就自行了解写法
//        String sourceFile = "E:\\2-project\\15-henanhuagong\\CEPOP\\trunk\\zhnh\\uploadFile\\hnec";
        //这个是压缩之后的文件绝对路径
        String outFilePath = uploadPath + monthStr + ".zip";
        dealZipFile(response, outFilePath, baseFilePath, monthStr);
    }

    public void dealZipFile(HttpServletResponse response, String outFilePath, String baseFilePath, String name) {
        try {
            FileOutputStream fos = new FileOutputStream(outFilePath);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(baseFilePath);

            ZipUtils.zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();

            download(response, outFilePath, name + ".zip");

            FileUtil.DeleteFolder(outFilePath);
            FileUtil.DeleteFolder(baseFilePath);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void batchFile(String filePath, List<ProjectFile> projectFiles) {
        try {
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            for (ProjectFile file : projectFiles) {
                URL httpurl = new URL(file.getPath());
                String[] tmp = file.getPath().split("/");
                FileUtils.copyURLToFile(httpurl, new File(filePath + tmp[tmp.length - 1]));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String projectRole(Integer projectID, Integer userID) {
        String res = "";
        ProjectManagerExample managerExample = new ProjectManagerExample();
        managerExample.createCriteria().andProjectIdEqualTo(projectID).andUserIdEqualTo(userID);
        long c = projectManagerMapper.countByExample(managerExample);
        if (c > 0) {
            res += "项目管理员;";
        }
        ProjectLeaderExample leaderExample = new ProjectLeaderExample();
        leaderExample.createCriteria().andProjectIdEqualTo(projectID).andUserIdEqualTo(userID);
        c = projectLeaderMapper.countByExample(leaderExample);
        if (c > 0) {
            res += "项目负责人;";
        }

        ProjectMemberExample memberExample = new ProjectMemberExample();
        memberExample.createCriteria().andProjectIdEqualTo(projectID).andUserIdEqualTo(userID);
        c = projectMemberMapper.countByExample(memberExample);
        if (c > 0) {
            res += "项目成员;";
        }

        ProjectTreasurerExample treasurerExample = new ProjectTreasurerExample();
        treasurerExample.createCriteria().andProjectIdEqualTo(projectID).andUserIdEqualTo(userID);
        c = projectTreasurerMapper.countByExample(treasurerExample);
        if (c > 0) {
            res += "财务人员";
        }
        if (res.equals(""))
            res = "其他";
        return res;
    }

    @RequestMapping(value = "/export_user", method = RequestMethod.GET)
    public void exportUserProcess(@RequestParam(value = "token") String token,
                                  @RequestParam(value = "month") String monthStr,
                                  HttpServletResponse response) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date now = sdf.parse(monthStr);
        Date startDate = getFirstDayOfThisMonth(now);
        Date endDate = getFirstDayOfNextMonth(now);

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
            if (projectIds.size() == 0)
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

                HelpItem item = getActualSteps(project, schedule, startDate, endDate);

                int cellIndex = 2;
                for (String step : item.steps) {
                    row.createCell(cellIndex).setCellValue(step);
                    cellIndex++;
                }
                index++;
            }
        }
        dealFile(response, wb, monthStr);
    }

    public void dealFile(HttpServletResponse response, HSSFWorkbook wb, String name) {
        try {
            String path = uploadPath + name + ".xls";
            FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            fout.close();
            download(response, path, name + ".xls");

            FileUtil.DeleteFolder(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/export_month_plan", method = RequestMethod.GET)
    public void exportMonthPlan(@RequestParam(value = "token") String token,
                                @RequestParam(value = "month") String monthStr,
                                HttpServletResponse response) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date now = sdf.parse(monthStr);
        Date start = getFirstDayOfThisMonth(now);
        Date end = getFirstDayOfNextMonth(now);

        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        String month = sdf2.format(now);

        HSSFRow row = sheet.createRow(0);
        row.setRowStyle(cellStyle);
        row.createCell(0).setCellValue("合同编号");
        row.createCell(1).setCellValue("项目编号");
        row.createCell(2).setCellValue("项目名称");
        row.createCell(3).setCellValue("合同金额");
        row.createCell(4).setCellValue(month + "月已完成节点");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 8));
        row.createCell(9).setCellValue("计划完成结点金额(元)");
        row.createCell(10).setCellValue(month + "月计划完成节点");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 10, 14));
        row.createCell(15).setCellValue("已计划完成金额(元)");
        row.createCell(16).setCellValue("备注");

        HSSFCellStyle cs = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();
        cs.setDataFormat(format.getFormat("#,##0.00"));

        double total = 0.0;
        List<Project> projects = getMyManageProjects(token);
        int index = 1;
        for (Project project : projects) {
            row = sheet.createRow(index);
            row.setRowStyle(cellStyle);

            row.createCell(0).setCellValue(project.getContractNumber());
            row.createCell(1).setCellValue(project.getProjectNumber());
            row.createCell(2).setCellValue(project.getName());

            Cell cell = row.createCell(3);
            cell.setCellStyle(cs);
            cell.setCellValue(project.getTotalFee().setScale(6).doubleValue() * 10000);

            ProjectSchedule schedule = getProjectSchedule(project.getId());

            if (schedule != null) {

                HelpItem item = getActualSteps(project, schedule, start, end);
                int columnIndex = 4;
                for (String step : item.steps) {
                    row.createCell(columnIndex).setCellValue(step);
                    columnIndex++;
                }

                cell = row.createCell(9);
                cell.setCellStyle(cs);
                cell.setCellValue(item.totalFee);

                columnIndex = 10;

                item = getPlanSteps(project, schedule, start, end);
                for (String step : item.steps) {
                    row.createCell(columnIndex).setCellValue(step);
                    columnIndex++;
                }
                cell = row.createCell(15);
                cell.setCellStyle(cs);
                cell.setCellValue(item.totalFee);

                total += item.totalFee;
            }

            index++;
        }
        sheet.createRow(index);
        index++;
        row = sheet.createRow(index);
        row.createCell(0).setCellValue("小计");
        Cell cell = row.createCell(15);
        cell.setCellStyle(cs);
        cell.setCellValue(total);

        for (int i = 0; i <= 16; i++) {
            sheet.autoSizeColumn(i);
        }
        dealFile(response, wb, monthStr);
    }

    @RequestMapping(value = "/export_year_progress", method = RequestMethod.GET)
    public void exportYearProgress(@RequestParam(value = "token") String token,
                                   @RequestParam(value = "year") String yearStr,
                                   HttpServletResponse response) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);

        Integer year = Integer.parseInt(yearStr);
        Integer lastYear = year - 1;
        String d1 = lastYear + "-01-01";
        String d2 = year + "-01-01";
        String d3 = (year + 1) + "-01-01";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        HSSFRow row1 = sheet.createRow(0);
        HSSFRow row2 = sheet.createRow(1);
        row1.setRowStyle(cellStyle);
        row2.setRowStyle(cellStyle);

        row1.createCell(0).setCellValue("项目核算");
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
        row1.createCell(1).setCellValue("应收账款核算用");
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
        row1.createCell(2).setCellValue("设计项目名称");
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));
        row1.createCell(3).setCellValue("项目负责人");
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 3, 3));
        row1.createCell(4).setCellValue("合同金额A");
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 4, 4));
        row1.createCell(5).setCellValue("决算金额B");
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 5, 5));
        row1.createCell(6).setCellValue("合同信息");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 10));
        row2.createCell(6).setCellValue("节点1：项目启动%");
        row2.createCell(7).setCellValue("节点2：项目中期%");
        row2.createCell(8).setCellValue("节节点3：初步成果");
        row2.createCell(9).setCellValue("节点4：评审验收%");
        row2.createCell(10).setCellValue("节点5：最终成果%");
        row1.createCell(11).setCellValue("节点时间");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 11, 15));
        row2.createCell(11).setCellValue("节点1：项目启动%");
        row2.createCell(12).setCellValue("节点2：项目中期%");
        row2.createCell(13).setCellValue("节节点3：初步成果");
        row2.createCell(14).setCellValue("节点4：评审验收%");
        row2.createCell(15).setCellValue("节点5：最终成果%");
        row1.createCell(16).setCellValue("截止" + lastYear + "年12月收入确认比重%T");
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 16, 16));
        row1.createCell(17).setCellValue("截止" + year + "年12月收入确认比重%T");
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 17, 17));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        List<Project> projects = getMyManageProjects(token);
        int index = 2;
        HSSFRow row;
        for (Project project : projects) {
            row = sheet.createRow(index);
            row.setRowStyle(cellStyle);

            row.createCell(0).setCellValue(project.getProjectNumber());
            row.createCell(1).setCellValue(project.getPartyA());
            row.createCell(2).setCellValue(project.getName());
            row.createCell(3).setCellValue(getLeaderNames(project.getId()));
            row.createCell(4).setCellValue(project.getTotalFee().setScale(6).doubleValue() * 10000);
            row.createCell(5).setCellValue("");

            ProjectFee projectFee = getProjectFee(project.getId());
            row.createCell(6).setCellValue(projectFee.getStartPercent() != null ? projectFee.getStartPercent().setScale(2).doubleValue() + "" : "");
            row.createCell(7).setCellValue(projectFee.getMiddlePercent() != null ? projectFee.getMiddlePercent().setScale(2).doubleValue() + "" : "");
            row.createCell(8).setCellValue(projectFee.getPreliminaryResultPercent() != null ? projectFee.getPreliminaryResultPercent().setScale(2).doubleValue() + "" : "");
            row.createCell(9).setCellValue(projectFee.getReviewPercent() != null ? projectFee.getReviewPercent().setScale(2).doubleValue() + "" : "");
            row.createCell(10).setCellValue(projectFee.getFinalPercent() != null ? projectFee.getFinalPercent().setScale(2).doubleValue() + "" : "");


            ProjectSchedule schedule = getProjectSchedule(project.getId());
            row.createCell(11).setCellValue(schedule.getActualStartUpDate() != null ?
                    sdf.format(schedule.getActualStartUpDate()) : schedule.getPlanStartUpDate() != null ? "预计" + sdf.format(schedule.getPlanStartUpDate()) + "完成" : "");
            row.createCell(12).setCellValue(schedule.getActualMiddleDate() != null ?
                    sdf.format(schedule.getActualMiddleDate()) : schedule.getPlanMiddleDate() != null ? "预计" + sdf.format(schedule.getPlanMiddleDate()) + "完成" : "");
            row.createCell(13).setCellValue(schedule.getActualPreliminaryResult() != null ?
                    sdf.format(schedule.getActualPreliminaryResult()) : schedule.getPlanPreliminaryResult() != null ? "预计" + sdf.format(schedule.getPlanPreliminaryResult()) + "完成" : "");
            row.createCell(14).setCellValue(schedule.getActualReviewDate() != null ?
                    sdf.format(schedule.getActualReviewDate()) : schedule.getPlanReviewDate() != null ? "预计" + sdf.format(schedule.getPlanReviewDate()) + "完成" : "");
            row.createCell(15).setCellValue(schedule.getActualFinalDate() != null ?
                    sdf.format(schedule.getActualFinalDate()) : schedule.getPlanFinalDate() != null ? "预计" + sdf.format(schedule.getPlanFinalDate()) + "完成" : "");

            row.createCell(16).setCellValue(accountPercent(project.getId(), sdf2.parse(d1), sdf2.parse(d2)));
            row.createCell(17).setCellValue(accountPercent(project.getId(), sdf2.parse(d2), sdf2.parse(d3)));

            index++;
        }

        for (int i = 0; i <= 17; i++) {
            sheet.autoSizeColumn(i, true);
        }
        dealFile(response, wb, yearStr);
    }

    @Autowired
    private ProjectFinanceStepMapper projectFinanceStepMapper;

    public String accountPercent(Integer projectId, Date start, Date end) {
        ProjectFinanceStepExample example = new ProjectFinanceStepExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andAccountDateGreaterThanOrEqualTo(start).andAccountDateLessThan(end);
        List<ProjectFinanceStep> list = projectFinanceStepMapper.selectByExample(example);
        double total = 0.0;
        for (ProjectFinanceStep step : list) {
            total += step.getAccountPercent();
        }
        return total == 0.0 ? "-" : total + "";
    }

    public String getLeaderNames(Integer projectId) {
        ProjectLeaderExample leaderExample = new ProjectLeaderExample();
        leaderExample.createCriteria().andProjectIdEqualTo(projectId);
        List<ProjectLeader> projectLeaders = projectLeaderMapper.selectByExample(leaderExample);
        String leaderNames = "";
        for (ProjectLeader leader : projectLeaders) {
            User tmp = userMapper.selectByPrimaryKey(leader.getUserId());
            leaderNames += tmp.getName() + ";";
        }
        return leaderNames;
    }

    public class HelpItem {
        public List<String> steps = new ArrayList<>();
        public double totalFee = 0.0;
    }

    public ProjectFee getProjectFee(Integer projectId) {
        ProjectFeeExample feeExample = new ProjectFeeExample();
        feeExample.createCriteria().andProjectIdEqualTo(projectId);
        List<ProjectFee> projectFees = projectFeeMapper.selectByExample(feeExample);
        ProjectFee projectFee = projectFees.get(0);
        return projectFee;
    }

    public HelpItem getPlanSteps(Project project, ProjectSchedule schedule, Date start, Date end) {
        ProjectFee projectFee = getProjectFee(project.getId());

        List<String> res = new ArrayList<>();
        double total = 0.0;
        if (schedule.getPlanStartUpDate() != null && schedule.getPlanStartUpDate().after(start) && schedule.getPlanStartUpDate().before(end)) {
            if (projectFee.getStartPercent() != null) {
                res.add(Constants.PROJECT_START_STEP_NAME + "(" + projectFee.getStartPercent().setScale(2).doubleValue() + "%)");
                total += projectFee.getStartPercent().setScale(2).doubleValue();
            } else {
                res.add(Constants.PROJECT_START_STEP_NAME + "(0%)");
            }
        }
        if (schedule.getPlanMiddleDate() != null && schedule.getPlanMiddleDate().after(start) && schedule.getPlanMiddleDate().before(end)) {
            if (projectFee.getMiddlePercent() != null) {
                res.add(Constants.PROJECT_MIDDLE_STEP_NAME + "(" + projectFee.getMiddlePercent().setScale(2).doubleValue() + "%)");
                total += projectFee.getMiddlePercent().setScale(2).doubleValue();
            } else {
                res.add(Constants.PROJECT_MIDDLE_STEP_NAME + "(0%)");
            }
        }
        if (schedule.getPlanPreliminaryResult() != null && schedule.getPlanPreliminaryResult().after(start) && schedule.getPlanPreliminaryResult().before(end)) {
            if (projectFee.getPreliminaryResultPercent() != null) {
                res.add(Constants.PROJECT_PRELIMINARY_STEP_NAME + "(" + projectFee.getPreliminaryResultPercent().setScale(2).doubleValue() + "%)");
                total += projectFee.getPreliminaryResultPercent().setScale(2).doubleValue();
            } else {
                res.add(Constants.PROJECT_PRELIMINARY_STEP_NAME + "(0%)");
            }
        }
        if (schedule.getPlanReviewDate() != null && schedule.getPlanReviewDate().after(start) && schedule.getPlanReviewDate().before(end)) {
            if (projectFee.getReviewPercent() != null) {
                res.add(Constants.PROJECT_REVIEW_STEP_NAME + "(" + projectFee.getReviewPercent().setScale(2).doubleValue() + "%)");
                total += projectFee.getReviewPercent().setScale(2).doubleValue();
            } else {
                res.add(Constants.PROJECT_REVIEW_STEP_NAME + "(0%)");
            }
        }
        if (schedule.getPlanFinalDate() != null && schedule.getPlanFinalDate().after(start) && schedule.getPlanFinalDate().before(end)) {
            if (projectFee.getFinalPercent() != null) {
                res.add(Constants.PROJECT_FINAL_STEP_NAME + "(" + projectFee.getFinalPercent().setScale(2).doubleValue() + "%)");
                total += projectFee.getFinalPercent().setScale(2).doubleValue();
            } else {
                res.add(Constants.PROJECT_FINAL_STEP_NAME + "(0%)");
            }
        }
        HelpItem item = new HelpItem();
        item.steps = res;
        item.totalFee = total / 100 * project.getTotalFee().setScale(6).doubleValue() * 10000;
        return item;
    }

    public HelpItem getActualSteps(Project project, ProjectSchedule schedule, Date start, Date end) {
        ProjectFee projectFee = getProjectFee(project.getId());

        List<String> res = new ArrayList<>();
        double total = 0.0;
        if (schedule.getActualStartUpDate() != null && schedule.getActualStartUpDate().after(start) && schedule.getActualStartUpDate().before(end)) {
            if (projectFee.getStartPercent() != null) {
                res.add(Constants.PROJECT_START_STEP_NAME + "(" + projectFee.getStartPercent().setScale(2).doubleValue() + "%)");
                total += projectFee.getStartPercent().setScale(2).doubleValue();
            } else {
                res.add(Constants.PROJECT_START_STEP_NAME + "(0%)");
            }
        }
        if (schedule.getActualMiddleDate() != null && schedule.getActualMiddleDate().after(start) && schedule.getActualMiddleDate().before(end)) {
            if (projectFee.getMiddlePercent() != null) {
                res.add(Constants.PROJECT_MIDDLE_STEP_NAME + "(" + projectFee.getMiddlePercent().setScale(2).doubleValue() + "%)");
                total += projectFee.getMiddlePercent().setScale(2).doubleValue();
            } else {
                res.add(Constants.PROJECT_MIDDLE_STEP_NAME + "(0%)");
            }
        }
        if (schedule.getActualPreliminaryResult() != null && schedule.getActualPreliminaryResult().after(start) && schedule.getActualPreliminaryResult().before(end)) {
            if (projectFee.getPreliminaryResultPercent() != null) {
                res.add(Constants.PROJECT_PRELIMINARY_STEP_NAME + "(" + projectFee.getPreliminaryResultPercent().setScale(2).doubleValue() + "%)");
                total += projectFee.getPreliminaryResultPercent().setScale(2).doubleValue();
            } else {
                res.add(Constants.PROJECT_PRELIMINARY_STEP_NAME + "(0%)");
            }
        }
        if (schedule.getActualReviewDate() != null && schedule.getActualReviewDate().after(start) && schedule.getActualReviewDate().before(end)) {
            if (projectFee.getReviewPercent() != null) {
                res.add(Constants.PROJECT_REVIEW_STEP_NAME + "(" + projectFee.getReviewPercent().setScale(2).doubleValue() + "%)");
                total += projectFee.getReviewPercent().setScale(2).doubleValue();
            } else {
                res.add(Constants.PROJECT_REVIEW_STEP_NAME + "(0%)");
            }
        }
        if (schedule.getActualFinalDate() != null && schedule.getActualFinalDate().after(start) && schedule.getActualFinalDate().before(end)) {
            if (projectFee.getFinalPercent() != null) {
                res.add(Constants.PROJECT_FINAL_STEP_NAME + "(" + projectFee.getFinalPercent().setScale(2).doubleValue() + "%)");
                total += projectFee.getFinalPercent().setScale(2).doubleValue();
            } else {
                res.add(Constants.PROJECT_FINAL_STEP_NAME + "(0%)");
            }
        }
        HelpItem item = new HelpItem();
        item.steps = res;
        item.totalFee = total / 100 * project.getTotalFee().setScale(6).doubleValue() * 10000;
        return item;
    }

    public ProjectSchedule getProjectSchedule(Integer projectId) {
        ProjectScheduleExample scheduleExample = new ProjectScheduleExample();
        scheduleExample.createCriteria().andProjectIdEqualTo(projectId);
        List<ProjectSchedule> projectScheduleList = projectScheduleMapper.selectByExample(scheduleExample);
        if (projectScheduleList.size() > 0)
            return projectScheduleList.get(0);
        else
            return null;
    }

    public List<Project> getMyManageProjects(String token) {
        User user = userMapper.findByToken(token);
        List<Integer> projectIds = new ArrayList<>();
        ProjectManagerExample managerExample = new ProjectManagerExample();
        managerExample.createCriteria().andUserIdEqualTo(user.getId());
        List<ProjectManager> projectManagers = projectManagerMapper.selectByExample(managerExample);
        for (ProjectManager manager : projectManagers) {
            Project project = projectMapper.selectByPrimaryKey(manager.getProjectId());
            if (project.getStatus() != Constants.PROJECT_DELETE)
                projectIds.add(manager.getProjectId());
        }

        ProjectExample projectExample = new ProjectExample();
        projectExample.createCriteria().andIdIn(projectIds);
        return projectMapper.selectByExample(projectExample);
    }

    public Date getFirstDayOfThisMonth(Date now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public Date getFirstDayOfNextMonth(Date now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
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
