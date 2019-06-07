package com.liwj.szpd.service.impl;

import com.liwj.szpd.mapper.*;
import com.liwj.szpd.model.*;
import com.liwj.szpd.service.UserService;
import com.liwj.szpd.third.api.AliyunSMS;
import com.liwj.szpd.utils.Constants;
import com.liwj.szpd.utils.PermitEnum;
import com.liwj.szpd.utils.RoleEnum;
import com.liwj.szpd.utils.TokenProcessor;
import com.liwj.szpd.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PhoneCodeMapper phoneCodeMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermitMapper rolePermitMapper;

    @Autowired
    private PermitMapper permitMapper;

    @Autowired
    private ProjectManagerMapper projectManagerMapper;

    @Autowired
    private ProjectLeaderMapper projectLeaderMapper;

    @Autowired
    private ProjectTreasurerMapper projectTreasurerMapper;

    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private DockerMapper dockerMapper;

    @Override
    public String login(String openid, String session_key, String name, String avatar) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openid);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 0) {
            User user = new User();
            user.setOpenId(openid);
            user.setSessionKey(session_key);
            user.setStatus(Constants.USER_STATE_OPEN);
            user.setCreatedTime(new Date());
            user.setRevision(0);
            user.setAvatar(avatar);
            user.setName(name);
            userMapper.insert(user);

            String token = TokenProcessor.getInstance().sign(user.getName(),user.getId().toString());
            if (token == null) {
                return null;
            }
            Calendar c = Calendar.getInstance();
            setUserToken(user, token, c);
            userMapper.updateByPrimaryKeySelective(user);
            return token;
        } else if (users.size() == 1) {
            User user = users.get(0);
            Calendar c = Calendar.getInstance();
            if (c.getTimeInMillis() > user.getToeknExperie()) {
                String token = TokenProcessor.getInstance().sign(user.getName(),user.getId().toString());
                if (token == null) {
                    return null;
                }
                setUserToken(user, token, c);
            }
            user.setSessionKey(session_key);
            user.setUpdatedTime(new Date());
            user.setAvatar(avatar);
            user.setName(name);

            userMapper.updateByPrimaryKeySelective(user);
            return user.getToken();
        } else {
            return null;
        }
    }

    @Override
    public String checkActive(String token) {
        User user = userMapper.findByToken(token);
        if (user == null)
            return "no_user";
        if (user.getPhone() == null || user.getPhone().equals(""))
            return null;
        Calendar c = Calendar.getInstance();
        if (user.getToeknExperie()==null||c.getTimeInMillis() > user.getToeknExperie()) {
            String t = TokenProcessor.getInstance().sign(user.getName(),user.getId().toString());
            setUserToken(user, t, c);
            userMapper.updateByPrimaryKeySelective(user);
        }
        return user.getToken();
    }

    @Override
    public String generateVerifyCode(String token, String phone) {
        boolean flag = false;
        PhoneCode phoneCode = phoneCodeMapper.findByPhone(phone);
        if (phoneCode == null) {
            phoneCode = new PhoneCode();
            flag = true;
        }
        String verifyCode = String
                .valueOf(new Random().nextInt(899999) + 100000);
        phoneCode.setCode(verifyCode);
        phoneCode.setToken(token);
        phoneCode.setPhone(phone);
        phoneCode.setStatus(Constants.VERIFY_CODE_STATE_OPEN);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 5);
        phoneCode.setExpire(c.getTimeInMillis());

        if (flag)
            phoneCodeMapper.insert(phoneCode);
        else
            phoneCodeMapper.updateByPrimaryKey(phoneCode);

        AliyunSMS.sendVerificationCode(phone, phoneCode.getCode());

        return phoneCode.getCode();
    }

    @Override
    public boolean bindPhone(String token, String phone, String code) {
        PhoneCode verification = phoneCodeMapper.findByPhone(phone);

        if (verification == null)
            return false;

        User user = userMapper.findByToken(token);
        if (user == null) {
            return false;
        }

        if (verification.getStatus() == Constants.VERIFY_CODE_STATE_CLOSE
                || !verification.getToken().equals(token) || !verification.getCode().equals(code))
            return false;

        verification.setStatus(Constants.VERIFY_CODE_STATE_CLOSE);
        phoneCodeMapper.updateByPrimaryKey(verification);

        user.setStatus(Constants.USER_STATE_ACTIVE);
        user.setPhone(phone);
        userMapper.updateByPrimaryKeySelective(user);


        DockerExample dockerExample = new DockerExample();
        dockerExample.createCriteria().andPhoneEqualTo(phone);
        List<Docker> dockers = dockerMapper.selectByExample(dockerExample);
        Docker docker;
        if (dockers.size()!=Constants.ROLE_CREATOR){
             docker = dockers.get(0);
            if (docker.getOrg()!=null){
                OrgExample orgExample = new OrgExample();
                orgExample.createCriteria().andFullNameEqualTo(docker.getOrg());
                List<Org> orgs = orgMapper.selectByExample(orgExample);
                if (orgs.size()!=0){
                    user.setOrgId(orgs.get(0).getId());
                    userMapper.updateByPrimaryKeySelective(user);
                }
            }
        }else{
            docker=new Docker();
            docker.setIdentity(Constants.ROLE_COMMON);
        }

        RoleExample roleExample = new RoleExample();
        if (docker.getIdentity()==Constants.ROLE_CREATOR){
            roleExample.createCriteria().andTypeEqualTo(RoleEnum.CREATOR.getType());
        }else if (docker.getIdentity()==Constants.ROLE_COMMON){
            roleExample.createCriteria().andTypeEqualTo(RoleEnum.ORDINARY.getType());
        }else if (docker.getIdentity()==Constants.ROLE_FINANCE){
            roleExample.createCriteria().andTypeEqualTo(RoleEnum.TREASURER.getType());
        }
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        if (roleList.size()!=0){
            Role role = roleList.get(0);
            UserRole  userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());
            userRole.setOrgId(user.getOrgId());
            userRole.setRevision(0);
            userRole.setCreatedTime(new Date());
            userRoleMapper.insert(userRole);
        }
        return true;
    }



    private void setUserToken(User user, String token, Calendar c) {
        c.add(Calendar.DAY_OF_MONTH, 1);
        user.setToken(token);
        user.setToeknExperie(c.getTimeInMillis());
    }

    @Override
    public boolean checkAddProjectRight(String token) {
        User user = userMapper.findByToken(token);
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(user.getId()).andOrgIdEqualTo(user.getOrgId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        if (userRoles.size() == 0)
            return false;
        List<Integer> roleIds = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            roleIds.add(userRole.getRoleId());
        }

        RolePermitExample rolePermitExample = new RolePermitExample();
        rolePermitExample.createCriteria().andRoleIdIn(roleIds);
        List<RolePermit> rolePermits = rolePermitMapper.selectByExample(rolePermitExample);
        if (rolePermits.size() == 0)
            return false;
        List<Integer> permitIds = new ArrayList<>();
        for (RolePermit rolePermit : rolePermits) {
            permitIds.add(rolePermit.getPermitId());
        }

        PermitExample permitExample = new PermitExample();
        List<String> permitCodes = new ArrayList<>();
        permitCodes.add(PermitEnum.ADDPROJECT.getCode());
        permitExample.createCriteria().andCodeIn(permitCodes);
        List<Permit> permits = permitMapper.selectByExample(permitExample);

        if (permits.size() > 0)
            return true;

        return false;
    }

    @Override
    public boolean checkMangerRole(String token, Integer projectId) {
        User user = userMapper.findByToken(token);
        if (projectId!=null){
            ProjectManagerExample example = new ProjectManagerExample();
            example.createCriteria().andProjectIdEqualTo(projectId).andUserIdEqualTo(user.getId());
            long c = projectManagerMapper.countByExample(example);
            if (c == 0)
                return false;
            else
                return true;
        }else{
            ProjectManagerExample example = new ProjectManagerExample();
            example.createCriteria().andUserIdEqualTo(user.getId());
            long c = projectManagerMapper.countByExample(example);
            if (c == 0)
                return false;
            else
                return true;
        }
    }

    @Override
    public boolean checkLeaderRole(String token, Integer projectId) {
        User user = userMapper.findByToken(token);
        ProjectLeaderExample example = new ProjectLeaderExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andUserIdEqualTo(user.getId());
        long c = projectLeaderMapper.countByExample(example);
        if (c == 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean checkTreasurerRole(String token, Integer projectId) {
        User user = userMapper.findByToken(token);
        ProjectTreasurerExample example = new ProjectTreasurerExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andUserIdEqualTo(user.getId());
        long c = projectTreasurerMapper.countByExample(example);
        if (c == 0)
            return false;
        else
            return true;
    }

    @Override
    public UserVO getInfo(String token) {
        User user = userMapper.findByToken(token);
        UserVO vo = new UserVO();
        vo.setName(user.getName());
        vo.setAvatar(user.getAvatar());
        vo.setPhone(user.getPhone());

        if (user.getOrgId()!=null){
            Org org = orgMapper.selectByPrimaryKey(user.getOrgId());
            if (org!=null){
                vo.setDepart(org.getName());
            }
        }

        return vo;
    }

    @Override
    public String webLogin(String username, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andPhoneEqualTo(username);
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size()==0){
            return null;
        }
        User user = userList.get(0);
        return user.getToken();
    }
}
