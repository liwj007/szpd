package com.liwj.szpd.service;

import com.liwj.szpd.form.ProjectBaseForm;
import com.liwj.szpd.utils.PageResult;
import com.liwj.szpd.vo.ProjectItemVO;

public interface ProjectService {
    boolean create(String token, ProjectBaseForm form);

    PageResult<ProjectItemVO> getMyProjects(String token, String content,Integer page, Integer size);

    ProjectBaseForm getProjectInfo(String token, Integer projectId);

    boolean update(String token, ProjectBaseForm form);

    boolean close(String token, Integer id);
}
