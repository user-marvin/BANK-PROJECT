package com.acccenture.banktrialprojectbed.credentials;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalRepo {
    private String userName;
    private static LocalRepo localRepoInstance;
    private boolean isInstantiated = false;
    public static LocalRepo getInstance(){
        if(localRepoInstance == null){
            localRepoInstance = new LocalRepo();
        }
        return localRepoInstance;
    }

    public LocalRepo() {
        if(isInstantiated){
            throw new IllegalStateException("Already Instantiated");
        }
        isInstantiated = true;
    }
}
