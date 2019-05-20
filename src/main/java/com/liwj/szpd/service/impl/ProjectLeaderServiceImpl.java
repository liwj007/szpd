package com.liwj.szpd.service.impl;

import com.liwj.szpd.mapper.ProjectLeaderMapper;
import com.liwj.szpd.mapper.UserMapper;
import com.liwj.szpd.model.ProjectLeader;
import com.liwj.szpd.model.ProjectLeaderExample;
import com.liwj.szpd.model.User;
import com.liwj.szpd.service.ProjectLeaderService;
import com.liwj.szpd.vo.UserItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectLeaderServiceImpl implements ProjectLeaderService {
    @Autowired
    private ProjectLeaderMapper projectLeaderMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<UserItemVO> getLeaders(Integer projectID) {
        ProjectLeaderExample example = new ProjectLeaderExample();
        example.createCriteria().andProjectIdEqualTo(projectID);
        List<ProjectLeader> lst = projectLeaderMapper.selectByExample(example);

        List<UserItemVO> res = new ArrayList<>();
        for (ProjectLeader leader : lst) {
            res.add(generate(leader));
        }
        return res;
    }


    @Override
    public boolean selectUserForLeader(String token, Integer projectID, Integer userID) {
        User user = userMapper.findByToken(token);
        ProjectLeader projectLeader = new ProjectLeader();
        projectLeader.setCreatedBy(user.getId());
        projectLeader.setCreatedTime(new Date());
        projectLeader.setUserId(userID);
        projectLeader.setProjectId(projectID);
        projectLeader.setRevision(0);
        projectLeaderMapper.insert(projectLeader);
        return true;
    }

    @Override
    public boolean deleteUserForLeader(Integer id) {
        ProjectLeaderExample example = new ProjectLeaderExample();
        example.createCriteria().andIdEqualTo(id);
        projectLeaderMapper.deleteByExample(example);
        return true;
    }

    private UserItemVO generate(ProjectLeader leader) {
        UserItemVO vo = new UserItemVO();
        vo.setId(leader.getId());
        User user = userMapper.selectByPrimaryKey(leader.getUserId());
        vo.setName(user.getName());
        vo.setPhone(user.getPhone());
        vo.setSelected(true);
        vo.setAvatar(user.getAvatar());
        return vo;
    }


}
