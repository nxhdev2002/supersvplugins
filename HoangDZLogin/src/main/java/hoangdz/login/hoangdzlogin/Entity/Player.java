package hoangdz.login.hoangdzlogin.Entity;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "players")
public class Player {
    @DatabaseField(id = true)
    private String Guid;
    @DatabaseField(canBeNull = false)
    private String Username;
    @DatabaseField(canBeNull = false)
    private String Password;
    @DatabaseField(canBeNull = false)
    private Float Balance;
    @DatabaseField(canBeNull = false)
    private String IpAddress;


    public Float getBalance() {
        return Balance;
    }

    public void setBalance(Float balance) {
        Balance = balance;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
