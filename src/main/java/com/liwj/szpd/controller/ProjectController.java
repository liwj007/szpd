package com.liwj.szpd.controller;

import com.liwj.szpd.form.ProjectBaseForm;
import com.liwj.szpd.service.ProjectService;
import com.liwj.szpd.utils.ErrorCode;
import com.liwj.szpd.utils.JsonResult;
import com.liwj.szpd.utils.PageResult;
import com.liwj.szpd.utils.ResponseData;
import com.liwj.szpd.vo.ProjectItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult createProject(@RequestParam(value = "token") String token,
                                      @RequestBody ProjectBaseForm form) {
        boolean flag = projectService.create(token, form);
        if (flag) {
            return JsonResult.renderSuccess();
        } else {
            return JsonResult.renderFail("创建项目失败");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResult getMyProjects(@RequestParam(value = "token") String token,
                                    @RequestParam(value = "content") String content,
                                    @RequestParam(value = "page") Integer page,
                                    @RequestParam(value = "size") Integer size) {
        PageResult<ProjectItemVO> res = projectService.getMyProjects(token, content, page, size);

        return JsonResult.renderSuccess(res);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public JsonResult getProjectInfo(@RequestParam(value = "token") String token,
                                     @RequestParam(value = "id") Integer projectId) {
        ProjectBaseForm res = projectService.getProjectInfo(token, projectId);

        return JsonResult.renderSuccess(res);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult updateProject(@RequestParam(value = "token") String token,
                                    @RequestBody ProjectBaseForm form) {
        boolean flag = projectService.update(token, form);
        if (flag) {
            return JsonResult.renderSuccess();
        } else {
            return JsonResult.renderFail("更新项目失败");
        }
    }

    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public JsonResult closeProject(@RequestParam(value = "token") String token,
                                   @RequestParam(value = "id") Integer id) {
        boolean flag = projectService.close(token, id);
        if (flag) {
            return JsonResult.renderSuccess();
        } else {
            return JsonResult.renderFail("更新项目失败");
        }
    }
}
