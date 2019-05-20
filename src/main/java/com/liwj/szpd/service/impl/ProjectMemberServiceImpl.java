package com.liwj.szpd.service.impl;

import com.liwj.szpd.mapper.ProjectMemberMapper;
import com.liwj.szpd.mapper.UserMapper;
import com.liwj.szpd.model.ProjectMember;
import com.liwj.szpd.model.ProjectMemberExample;
import com.liwj.szpd.model.User;
import com.liwj.szpd.service.ProjectMemberService;
import com.liwj.szpd.vo.UserItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectMemberMapper projectMemberMapper;

    @Override
    public List<UserItemVO> getMembers(Integer projectID) {
        ProjectMemberExample example = new ProjectMemberExample();
        example.createCriteria().andProjectIdEqualTo(projectID);
        List<ProjectMember> lst = projectMemberMapper.selectByExample(example);

        List<UserItemVO> res = new ArrayList<>();
        for (ProjectMember member : lst) {
            res.add(generate(member));
        }
        return res;
    }


    @Override
    public boolean selectUserForMember(String token, Integer projectID, Integer userID) {
        User user = userMapper.findByToken(token);
        ProjectMember projectMember = new ProjectMember();
        projectMember.setCreatedBy(user.getId());
        projectMember.setCreatedTime(new Date());
        projectMember.setUserId(userID);
        projectMember.setProjectId(projectID);
        projectMember.setRevision(0);
        projectMemberMapper.insert(projectMember);
        return true;
    }

    @Override
    public boolean deleteUserForMember(Integer id) {
        ProjectMemberExample example = new ProjectMemberExample();
        example.createCriteria().andIdEqualTo(id);
        projectMemberMapper.deleteByExample(example);
        return true;
    }

    private UserItemVO generate(ProjectMember member) {
        UserItemVO vo = new UserItemVO();
        vo.setId(member.getId());
        User user = userMapper.selectByPrimaryKey(member.getUserId());
        vo.setName(user.getName());
        vo.setPhone(user.getPhone());
        vo.setSelected(true);
        vo.setAvatar(user.getAvatar());
        return vo;
    }
}
