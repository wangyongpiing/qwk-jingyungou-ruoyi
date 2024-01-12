package com.ruoyi.qwk.enumeration;

/**
 * @author cjf
 * @Description:
 * @date 2023-12-23
 */
public enum PersonalTasksGameType {
    game_dts(1,"task_login_dts","首次进入生肖大逃杀","生肖大逃杀"),

    game_mtr(5,"task_login_mtr","首次进入生肖木头人","生肖木头人");


    private int gameId;
    private String taskCode;
    private String taskName;
    private String gameName;

    private PersonalTasksGameType(int gameId,String taskCode,String taskName,String gameName){
        this.gameId = gameId;
        this.gameName = gameName;
        this.taskCode = taskCode;
        this.taskName = taskName;
    }

    // 普通方法
    public static PersonalTasksGameType getName(int gameId) {
        for (PersonalTasksGameType c : PersonalTasksGameType.values()) {
            if (c.getGameId() == gameId) {
                return c;
            }
        }
        return null;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
