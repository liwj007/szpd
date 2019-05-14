package com.liwj.szpd.service.impl;

import com.liwj.szpd.mapper.ProjectManagerMapper;
import com.liwj.szpd.mapper.ProjectMapper;
import com.liwj.szpd.mapper.UserMapper;
import com.liwj.szpd.model.Project;
import com.liwj.szpd.model.ProjectManager;
import com.liwj.szpd.model.ProjectManagerExample;
import com.liwj.szpd.model.User;
import com.liwj.szpd.service.ProjectManagerService;
import com.liwj.szpd.service.UserService;
import com.liwj.szpd.vo.UserItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectManagerMapper projectManagerMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<UserItemVO> getManagers(Integer projectID) {
        ProjectManagerExample example = new ProjectManagerExample();
        example.createCriteria().andProjectIdEqualTo(projectID);
        List<ProjectManager> lst = projectManagerMapper.selectByExample(example);

        List<UserItemVO> res = new ArrayList<>();
        for (ProjectManager manager: lst){
            res.add(generate(manager));
        }
        return res;
    }

    @Override
    public List<UserItemVO> searchUsersForManager(Integer projectID, String content) {


        List<UserItemVO> res = new ArrayList<>();
        if (content==null||"".equals(content))
            return res;
        List<User> userList = userService.searchUser(content);
        if (userList.size()==0)
            return res;
        for (User user: userList){
            UserItemVO vo = generate(user);
            ProjectManagerExample example = new ProjectManagerExample();
            example.createCriteria().andProjectIdEqualTo(projectID).andUserIdEqualTo(user.getId());
            long count = projectManagerMapper.countByExample(example);
            if (count==1){
                vo.setSelected(true);
            }else{
                vo.setSelected(false);
            }

            res.add(vo);
        }
        return res;
    }

    @Override
    public boolean selectUserForManager(String token, Integer projectID, Integer userID) {
        User user = userMapper.findByToken(token);
        ProjectManager projectManager = new ProjectManager();
        projectManager.setCreatedBy(user.getId());
        projectManager.setCreatedTime(new Date());
        projectManager.setUserId(userID);
        projectManager.setProjectId(projectID);
        projectManager.setRevision(0);
        projectManagerMapper.insert(projectManager);
        return true;
    }

    @Override
    public boolean deleteUserForManager(Integer id) {
        ProjectManagerExample example = new ProjectManagerExample();
        example.createCriteria().andIdEqualTo(id);
        projectManagerMapper.deleteByExample(example);
        return true;
    }

    private UserItemVO generate(ProjectManager manager){
        UserItemVO vo = new UserItemVO();
        vo.setId(manager.getId());
        User user =userMapper.selectByPrimaryKey(manager.getUserId());
        vo.setName(user.getName());
        vo.setPhone(user.getPhone());
        vo.setSelected(true);
        vo.setAvatar(user.getAvatar());

        Project project = projectMapper.selectByPrimaryKey(manager.getProjectId());
        if (project.getCreatedBy()==user.getId()){
            vo.setCanDelete(false);
        }else{
            vo.setCanDelete(true);
        }

        return vo;
    }

    private UserItemVO generate(User user){
        UserItemVO vo = new UserItemVO();
        vo.setId(user.getId());
        vo.setName(user.getName());
        vo.setPhone(user.getPhone());
        vo.setAvatar(user.getAvatar());
        return vo;
    }
}
