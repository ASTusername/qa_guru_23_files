package guru.qa.model;

public class Squad {
    private String squadName;
    private String homeTown;
    private Integer formed;
    private String secretBase;
    private Boolean active;
    private Member[] members;

    public String getSquadName() {
        return squadName;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public Integer getFormed() {
        return formed;
    }

    public String getSecretBase() {
        return secretBase;
    }

    public Boolean getActive() {
        return active;
    }

    public Member[] getMembers() {
        return members;
    }
}
