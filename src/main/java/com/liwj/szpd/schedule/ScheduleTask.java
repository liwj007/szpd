package com.liwj.szpd.schedule;

import com.liwj.szpd.mapper.ProjectManagerMapper;
import com.liwj.szpd.mapper.ProjectMapper;
import com.liwj.szpd.model.Project;
import com.liwj.szpd.model.ProjectExample;
import com.liwj.szpd.model.ProjectManager;
import com.liwj.szpd.model.ProjectManagerExample;
import com.liwj.szpd.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
@Configuration
@EnableScheduling
public class ScheduleTask {
    @Autowired
    private ProjectManagerMapper projectManagerMapper;

    @Autowired
    private ProjectMapper projectMapper;

    //    @Scheduled(cron = "* * * 1 * ?")
    @Scheduled(cron = "* 1 * * * ?")
    private void configureTasks() {
        HashSet<Integer> userIDs = new HashSet<>();

        ProjectExample projectExample = new ProjectExample();
        projectExample.createCriteria().andStatusNotEqualTo(Constants.PROJECT_CLOSE);
        List<Project> projectList = projectMapper.selectByExample(projectExample);
        List<Integer> projectIDs = new ArrayList<>();
        for (Project project: projectList){
            projectIDs.add(project.getId());
        }

        if (projectIDs.size()==0){
            return;
        }

        ProjectManagerExample managerExample = new ProjectManagerExample();
        managerExample.createCriteria().andProjectIdIn(projectIDs);
        List<ProjectManager> projectManagers = projectManagerMapper.selectByExample(managerExample);
        for (ProjectManager projectMapper: projectManagers){
            userIDs.add(projectMapper.getUserId());
        }



    }
}
