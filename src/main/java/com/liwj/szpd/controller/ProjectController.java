package com.liwj.szpd.controller;

import com.liwj.szpd.form.ProjectBaseForm;
import com.liwj.szpd.service.ProjectService;
import com.liwj.szpd.utils.Constants;
import com.liwj.szpd.utils.JsonResult;
import com.liwj.szpd.utils.PageResult;
import com.liwj.szpd.utils.ResponseData;
import com.liwj.szpd.vo.MembersVO;
import com.liwj.szpd.vo.ProjectItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult createProject(@RequestHeader(value = "token") String token,
                                      @RequestBody ProjectBaseForm form) {
        boolean flag = projectService.create(token, form);
        if (flag) {
            return JsonResult.renderSuccess();
        } else {
            return JsonResult.renderFail("创建项目失败");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResult getMyProjects(@RequestHeader(value = "token") String token,
                                    @RequestParam(value = "content") String content,
                                    @RequestParam(value = "filter", required = false) Integer status,
                                    @RequestParam(value = "pageNo") Integer page,
                                    @RequestParam(value = "pageSize") Integer size) {
        PageResult<ProjectItemVO> res = projectService.getMyProjects(token, content,status, page, size);

        return JsonResult.renderSuccess(res);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public JsonResult getProjectInfo(@RequestHeader(value = "token") String token,
                                     @RequestParam(value = "id") Integer projectId) {
        ProjectBaseForm res = projectService.getProjectInfo(token, projectId);

        return JsonResult.renderSuccess(res);
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public JsonResult getProjectName(@RequestHeader(value = "token") String token,
                                     @RequestParam(value = "id") Integer projectId) {
        String res = projectService.getProjectName(token, projectId);

        return JsonResult.renderSuccess(Constants.SUCCESS,res);
    }

    @RequestMapping(value = "/remark", method = RequestMethod.GET)
    public JsonResult getProjectRemark(@RequestHeader(value = "token") String token,
                                     @RequestParam(value = "id") Integer projectId) {
        String res = projectService.getProjectRemark(token, projectId);

        return JsonResult.renderSuccess(Constants.SUCCESS,res);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult updateProject(@RequestHeader(value = "token") String token,
                                    @RequestBody ProjectBaseForm form) {
        boolean flag = projectService.update(token, form);
        if (flag) {
            return JsonResult.renderSuccess();
        } else {
            return JsonResult.renderFail("更新项目失败");
        }
    }

    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public JsonResult closeProject(@RequestHeader(value = "token") String token,
                                   @RequestParam(value = "id") Integer id) {
        boolean flag = projectService.close(token, id);
        if (flag) {
            return JsonResult.renderSuccess();
        } else {
            return JsonResult.renderFail("更新项目失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult deleteProject(@RequestHeader(value = "token") String token,
                                   @RequestParam(value = "id") Integer id) {
        boolean flag = projectService.delete(token, id);
        if (flag) {
            return JsonResult.renderSuccess();
        } else {
            return JsonResult.renderFail("更新项目失败");
        }
    }

    @RequestMapping(value = "/all_members", method = RequestMethod.GET)
    public ResponseData getAllMembers(@RequestHeader(value = "token") String token,
                                      @RequestParam(value = "pid") Integer projectID) {
        ResponseData responseData = new ResponseData();
        MembersVO res = projectService.getAllMembers(token,projectID);
        return responseData.setSuccessData(res);
    }

    @RequestMapping(value = "/export_project_to_wang", method = RequestMethod.GET)
    public ResponseData exportProjectToWang() {
        ResponseData responseData = new ResponseData();
        projectService.exportProjectToWang();
        return responseData.setSuccessData(null);
    }
}
