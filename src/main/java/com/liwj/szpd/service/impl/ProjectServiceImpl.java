package com.liwj.szpd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liwj.szpd.form.ProjectBaseForm;
import com.liwj.szpd.mapper.*;
import com.liwj.szpd.model.*;
import com.liwj.szpd.service.ProjectService;
import com.liwj.szpd.utils.ChinesePinyinUtil;
import com.liwj.szpd.utils.Constants;
import com.liwj.szpd.utils.PageResult;
import com.liwj.szpd.utils.RoleEnum;
import com.liwj.szpd.vo.ProjectItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectFeeMapper projectFeeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectLeaderMapper projectLeaderMapper;

    @Autowired
    private ProjectMemberMapper projectMemberMapper;

    @Autowired
    private ProjectManagerMapper projectManagerMapper;

    @Autowired
    private ProjectScheduleMapper projectScheduleMapper;

    @Autowired
    private ProjectFinanceMapper projectFinanceMapper;

    @Autowired
    private ProjectTreasurerMapper projectTreasurerMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional
    public boolean create(String token, ProjectBaseForm form) {
        if (form.getName() == null || form.getName().equals("")) {
            return false;
        }

        User user = userMapper.findByToken(token);

        Project project = new Project();
        project.setName(form.getName());
        project.setContractNumber(form.getContract());
        project.setProjectNumber(form.getCode());
        project.setPartyA(form.getParty());
        project.setStatus(Constants.PROJECT_OPEN);
        project.setCreatedBy(user.getId());
        project.setCreatedTime(new Date());
        project.setUpdatedTime(project.getCreatedTime());
        projectMapper.insert(project);

        ProjectFee fee = new ProjectFee();
        fee.setCreatedBy(user.getId());
        fee.setCreatedTime(new Date());
        fee.setProjectId(project.getId());
        if (form.getStartPercent() != null)
            fee.setStartPercent(new BigDecimal(form.getStartPercent()));
        if (form.getMiddlePercent() != null)
            fee.setMiddlePercent(new BigDecimal(form.getMiddlePercent()));
        if (form.getFinalPercent() != null)
            fee.setFinalPercent(new BigDecimal(form.getFinalPercent()));
        if (form.getReviewPercent() != null)
            fee.setReviewPercent(new BigDecimal(form.getReviewPercent()));
        if (form.getPreliminaryPercent() != null)
            fee.setPreliminaryResultPercent(new BigDecimal(form.getPreliminaryPercent()));
        projectFeeMapper.insert(fee);

        ProjectSchedule projectSchedule = new ProjectSchedule();
        projectSchedule.setProjectId(project.getId());
        projectSchedule.setCreatedBy(user.getId());
        projectSchedule.setCreatedTime(new Date());
        projectSchedule.setRevision(0);
        projectScheduleMapper.insert(projectSchedule);

        ProjectManager manager = new ProjectManager();
        manager.setProjectId(project.getId());
        manager.setUserId(user.getId());
        manager.setCreatedTime(new Date());
        manager.setCreatedBy(user.getId());
        projectManagerMapper.insert(manager);

        ProjectFinance finance = new ProjectFinance();
        finance.setProjectId(project.getId());
        finance.setCreatedBy(user.getId());
        finance.setCreatedTime(new Date());
        projectFinanceMapper.insert(finance);

        generateProjectTreasureMapper(project, user);

        return true;
    }

    private void generateProjectTreasureMapper(Project project, User creator) {


        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andTypeEqualTo(RoleEnum.TREASURER.getType());
        List<Role> roles = roleMapper.selectByExample(roleExample);
        if (roles.size() == 0)
            return;
        Role treasurerRole = roles.get(0);

        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andOrgIdEqualTo(creator.getOrgId()).andRoleIdEqualTo(treasurerRole.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        if (userRoles.size() == 0)
            return;
        for (UserRole userRole : userRoles) {
            ProjectTreasurer treasurer = new ProjectTreasurer();
            treasurer.setProjectId(project.getId());
            treasurer.setCreatedBy(creator.getId());
            treasurer.setCreatedTime(new Date());
            treasurer.setUserId(userRole.getUserId());
            projectTreasurerMapper.insert(treasurer);
        }
    }

    @Override
    public PageResult<ProjectItemVO> getMyProjects(String token, String content, Integer page, Integer size) {
        User user = userMapper.findByToken(token);
        if (user == null)
            return null;
        List<Integer> projectIds = getUserProjectIds(user);

        if (projectIds.size() == 0)
            return null;

        PageHelper.startPage(page, size);

        ProjectExample projectExample = new ProjectExample();
        projectExample.setOrderByClause("created_time desc");
        ProjectExample.Criteria criteria = projectExample.createCriteria();
        criteria.andIdIn(projectIds);
        if (content != null && !"".equals(content))
            criteria.andNameLikeInsensitive("%" + content + "%");
        List<Project> projects = projectMapper.selectByExample(projectExample);


        List<ProjectItemVO> res = new ArrayList<>();
        if (projects.size() == 0)
            return new PageResult<>(page, size, 0, res);
        PageInfo<Project> pageInfo = new PageInfo<>(projects);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        for (Project project : projects) {
            ProjectItemVO vo = new ProjectItemVO();
            vo.setId(project.getId());
            vo.setDate(sdf.format(project.getUpdatedTime()));
            vo.setName(project.getName());
            vo.setIconName(ChinesePinyinUtil.getPinYinFirstHeadChar(project.getName()));

            ProjectManagerExample managerExample = new ProjectManagerExample();
            managerExample.createCriteria().andUserIdEqualTo(user.getId()).andProjectIdEqualTo(project.getId());
            long c = projectManagerMapper.countByExample(managerExample);
            if (c==1){
                vo.getRights().setManager(true);
                vo.getRights().setUpdate(true);
                vo.getRights().setClose(true);
                vo.getRights().setDelete(true);
                vo.getRights().setLeader(true);
            }else{
                ProjectLeaderExample leaderExample = new ProjectLeaderExample();
                leaderExample.createCriteria().andUserIdEqualTo(user.getId()).andProjectIdEqualTo(project.getId());
                c = projectLeaderMapper.countByExample(leaderExample);
                if (c==1){
                    vo.getRights().setMember(true);
                }
            }

            res.add(vo);
        }
        return new PageResult<>(pageInfo.getPageNum(), pageInfo.getSize(), pageInfo.getTotal(), res);
    }

    @Override
    public ProjectBaseForm getProjectInfo(String token, Integer projectId) {
        Project project = projectMapper.selectByPrimaryKey(projectId);

        if (project == null)
            return null;

        User user = userMapper.findByToken(token);
        List<Integer> projectIds = getUserProjectIds(user);
        if (!projectIds.contains(projectId)) {
            return null;
        }

        ProjectBaseForm form = new ProjectBaseForm();
        form.setId(projectId);
        form.setName(project.getName());
        form.setCode(project.getProjectNumber());
        form.setContract(project.getContractNumber());
        form.setParty(project.getPartyA());

        ProjectFeeExample feeExample = new ProjectFeeExample();
        feeExample.createCriteria().andProjectIdEqualTo(projectId);
        List<ProjectFee> lst = projectFeeMapper.selectByExample(feeExample);
        if (lst.size() != 1) {
            return null;
        }
        ProjectFee fee = lst.get(0);
        form.setStartPercent(fee.getStartPercent() != null ? fee.getStartPercent().setScale(2).toString() : null);
        form.setMiddlePercent(fee.getMiddlePercent() != null ? fee.getMiddlePercent().setScale(2).toString() : null);
        form.setFinalPercent(fee.getFinalPercent() != null ? fee.getFinalPercent().setScale(2).toString() : null);
        form.setReviewPercent(fee.getReviewPercent() != null ? fee.getReviewPercent().setScale(2).toString() : null);
        form.setPreliminaryPercent(fee.getPreliminaryResultPercent() != null ? fee.getPreliminaryResultPercent().setScale(2).toString() : null);

        ProjectManagerExample managerExample = new ProjectManagerExample();
        managerExample.createCriteria().andUserIdEqualTo(user.getId()).andProjectIdEqualTo(projectId);
        long c = projectManagerMapper.countByExample(managerExample);
        if (c==0)
            form.setEditable(false);
        else
            form.setEditable(true);

        return form;
    }

    private List<Integer> getUserProjectIds(User user) {
        List<Integer> projectIds = new ArrayList<>();

        ProjectLeaderExample leaderExample = new ProjectLeaderExample();
        leaderExample.createCriteria().andUserIdEqualTo(user.getId());
        List<ProjectLeader> projectLeaders = projectLeaderMapper.selectByExample(leaderExample);
        for (ProjectLeader leader : projectLeaders) {
            projectIds.add(leader.getProjectId());
        }


        ProjectMemberExample memberExample = new ProjectMemberExample();
        memberExample.createCriteria().andUserIdEqualTo(user.getId());
        List<ProjectMember> projectMembers = projectMemberMapper.selectByExample(memberExample);
        for (ProjectMember member : projectMembers) {
            projectIds.add(member.getProjectId());
        }

        ProjectManagerExample managerExample = new ProjectManagerExample();
        managerExample.createCriteria().andUserIdEqualTo(user.getId());
        List<ProjectManager> projectManagers = projectManagerMapper.selectByExample(managerExample);
        for (ProjectManager manager : projectManagers) {
            projectIds.add(manager.getProjectId());
        }

        ProjectTreasurerExample treasurerExample = new ProjectTreasurerExample();
        treasurerExample.createCriteria().andUserIdEqualTo(user.getId());
        List<ProjectTreasurer> projectTreasurers = projectTreasurerMapper.selectByExample(treasurerExample);
        for (ProjectTreasurer treasurer : projectTreasurers) {
            projectIds.add(treasurer.getProjectId());
        }

        return projectIds;
    }

    @Override
    @Transactional
    public boolean update(String token, ProjectBaseForm form) {
        if (form.getName() == null || form.getName().equals("")) {
            return false;
        }

        User user = userMapper.findByToken(token);

        Project project = projectMapper.selectByPrimaryKey(form.getId());
        if (project == null)
            return false;
        project.setContractNumber(form.getContract());
        project.setProjectNumber(form.getCode());
        project.setPartyA(form.getParty());
        project.setUpdatedBy(user.getId());
        project.setUpdatedTime(new Date());
        projectMapper.updateByPrimaryKey(project);

        ProjectFeeExample feeExample = new ProjectFeeExample();
        feeExample.createCriteria().andProjectIdEqualTo(project.getId());
        ProjectFee fee = projectFeeMapper.selectByExample(feeExample).get(0);
        fee.setUpdatedBy(user.getId());
        fee.setUpdatedTime(new Date());
        if (form.getStartPercent() != null)
            fee.setStartPercent(new BigDecimal(form.getStartPercent()));
        if (form.getMiddlePercent() != null)
            fee.setMiddlePercent(new BigDecimal(form.getMiddlePercent()));
        if (form.getFinalPercent() != null)
            fee.setFinalPercent(new BigDecimal(form.getFinalPercent()));
        if (form.getReviewPercent() != null)
            fee.setReviewPercent(new BigDecimal(form.getReviewPercent()));
        if (form.getPreliminaryPercent() != null)
            fee.setPreliminaryResultPercent(new BigDecimal(form.getPreliminaryPercent()));
        projectFeeMapper.updateByPrimaryKey(fee);

        return true;
    }

    @Override
    public boolean close(String token, Integer id) {
        Project project = projectMapper.selectByPrimaryKey(id);
        project.setStatus(Constants.PROJECT_CLOSE);
        projectMapper.updateByPrimaryKeySelective(project);
        return true;
    }
}
