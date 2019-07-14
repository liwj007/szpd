package com.liwj.szpd.service;

import com.liwj.szpd.form.ProjectBaseForm;
import com.liwj.szpd.model.User;
import com.liwj.szpd.utils.PageResult;
import com.liwj.szpd.vo.MembersVO;
import com.liwj.szpd.vo.ProjectItemVO;
import com.liwj.szpd.vo.UserItemVO;

import java.util.List;

public interface ProjectService {
    boolean create(String token, ProjectBaseForm form);

    PageResult<ProjectItemVO> getMyProjects(String token, String content, Integer status, Integer page, Integer size);

    ProjectBaseForm getProjectInfo(String token, Integer projectId);

    List<Integer> getUserProjectIds(User user);

    boolean update(String token, ProjectBaseForm form);

    boolean close(String token, Integer id);

    String getProjectRemark(String token, Integer projectId);

    MembersVO getAllMembers(String token, Integer projectID);

    List<UserItemVO> searchUsersForProject(Integer projectID, String content);

    String getProjectName(String token, Integer projectId);

    boolean delete(String token, Integer id);
}
