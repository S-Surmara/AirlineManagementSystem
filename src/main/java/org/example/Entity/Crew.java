package org.example.Entity;

import org.example.Enums.JobTitle;
import org.example.Enums.Sex;

public class Crew extends User{

    JobTitle jobTitle;
    public Crew(String crewId, String crewName, Sex sex,JobTitle jobTitle){
        super(crewId,crewName,sex);
        this.jobTitle = jobTitle;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }
}
