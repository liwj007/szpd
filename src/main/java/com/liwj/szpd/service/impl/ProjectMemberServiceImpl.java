package com.liwj.szpd.service.impl;

import com.liwj.szpd.mapper.ProjectMemberMapper;
import com.liwj.szpd.mapper.UserMapper;
import com.liwj.szpd.model.ProjectMember;
import com.liwj.szpd.model.ProjectMemberExample;
import com.liwj.szpd.model.User;
import com.liwj.szpd.service.ProjectMemberService;
import com.liwj.szpd.service.UserService;
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

    @Autowired
    private UserService userService;

    @Override
    public List<UserItemVO> getMembers(Integer projectID) {
        ProjectMemberExample example = new ProjectMemberExample();
        example.createCriteria().andProjectIdEqualTo(projectID);
        List<ProjectMember> lst = projectMemberMapper.selectByExample(example);

        List<UserItemVO> res = new ArrayList<>();
        for (ProjectMember member: lst){
            res.add(generate(member));
        }
        return res;
    }

    @Override
    public List<UserItemVO> searchUsersForMember(Integer projectID, String content) {
        List<User> userList = userService.searchUser(content);

        List<UserItemVO> res = new ArrayList<>();
        if (userList.size()==0)
            return res;
        for (User user: userList){
            UserItemVO vo = generate(user);
            ProjectMemberExample example = new ProjectMemberExample();
            example.createCriteria().andProjectIdEqualTo(projectID).andUserIdEqualTo(user.getId());
            long count = projectMemberMapper.countByExample(example);
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

    private UserItemVO generate(ProjectMember member){
        UserItemVO vo = new UserItemVO();
        vo.setId(member.getId());
        User user =userMapper.selectByPrimaryKey(member.getUserId());
        vo.setName(user.getName());
        vo.setPhone(user.getPhone());
        vo.setSelected(true);
        vo.setAvatar(user.getAvatar());
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
